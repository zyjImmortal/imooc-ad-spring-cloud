package com.imooc.ad.service.impl;

import com.imooc.ad.constant.CommonStatus;
import com.imooc.ad.constant.Constants;
import com.imooc.ad.dao.AdPlanRepository;
import com.imooc.ad.dao.AdUserRepository;
import com.imooc.ad.entity.AdPlan;
import com.imooc.ad.entity.AdUser;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.service.IAdPlanService;
import com.imooc.ad.utils.CommonUtils;
import com.imooc.ad.vo.AdPlanGetRequest;
import com.imooc.ad.vo.AdPlanRequest;
import com.imooc.ad.vo.AdPlanResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author : zhouyajun
 * @date : 2020-04-18
 */
@Slf4j
@Service
public class AdPlanServiceImpl implements IAdPlanService {

    private final AdUserRepository userRepository;

    private final AdPlanRepository adPlanRepository;

    private final DataSourceTransactionManager transactionManager;

    @Autowired
    public AdPlanServiceImpl(AdPlanRepository adPlanRepository, AdUserRepository userRepository,
                             DataSourceTransactionManager transactionManager) {
        this.adPlanRepository = adPlanRepository;
        this.userRepository = userRepository;
        this.transactionManager = transactionManager;
    }


    @Override
    public AdPlanResponse createAdPlan(AdPlanRequest request)
            throws AdException {
        if (!request.createValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        Optional<AdUser> adUser = userRepository.findById(request.getUserId());
        if (!adUser.isPresent()) {
            throw new AdException(Constants.ErrorMsg.USER_NOT_EXIST);
        }
        AdPlan pldPlan = adPlanRepository.findByUserIdAndPlanName(
                request.getUserId(), request.getPlanName()
        );
        if (pldPlan != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_PLAN_ERROR);
        }
        AdPlan newAdPlan = adPlanRepository.save(
                new AdPlan(request.getPlanName(), request.getUserId(),
                        CommonUtils.parseStringDate(request.getStartDate()),
                        CommonUtils.parseStringDate(request.getEndDate())
                )
        );

        return new AdPlanResponse(newAdPlan.getId(), newAdPlan.getPlanName());
    }

    @Override
    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request)
            throws AdException {
        if (!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        return adPlanRepository.findAllByIdAndUserId(request.getIds(), request.getUserId());
    }

    @Override
    @Transactional
    public AdPlanResponse updateAdPlan(AdPlanRequest request)
            throws AdException {
        if (!request.updateValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        AdPlan adPlan = adPlanRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if (adPlan == null) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_PLAN_RECORD);
        }

        if (request.getPlanName() != null) {
            adPlan.setPlanName(request.getPlanName());
        }

        if (request.getStartDate() != null) {
            adPlan.setStartDate(CommonUtils.parseStringDate(request.getStartDate()));
        }

        if (request.getEndDate() != null) {
            adPlan.setEndDate(CommonUtils.parseStringDate(request.getEndDate()));
        }

        adPlan.setUpdateTime(new Date());
        adPlan = adPlanRepository.save(adPlan);
        return new AdPlanResponse(adPlan.getId(), adPlan.getPlanName());
    }

    @Override
    @Transactional
    public void deleteAdPlan(AdPlanRequest request) throws AdException {
        if (!request.deleteValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdPlan adPlan = adPlanRepository.findByIdAndUserId(
                request.getId(), request.getUserId()
        );

        if (adPlan == null) {
            throw new AdException(Constants.ErrorMsg.DELETE_PLAN_NOT_EXIST);
        }

        adPlan.setPlanStatus(CommonStatus.INVALID.getStatus());
        adPlan.setUpdateTime(new Date());


        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("delete ad plan");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            adPlanRepository.save(adPlan);
        } catch (Exception ex){
            transactionManager.rollback(status);
            throw new AdException(ex.getMessage());
        }

    }
}
