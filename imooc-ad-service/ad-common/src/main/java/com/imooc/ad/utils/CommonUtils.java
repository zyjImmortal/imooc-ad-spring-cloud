package com.imooc.ad.utils;


import com.imooc.ad.exception.AdException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * @author : zhouyajun
 * @date : 2020-04-18
 */
public class CommonUtils {

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd"
    };

    public static String md5(String value) {
        return DigestUtils.md5Hex(value).toUpperCase();
    }

    public static Date parseStringDate(String dateString)
            throws AdException {
        try {
            return DateUtils.parseDate(dateString, parsePatterns);
        } catch (Exception ex) {
            throw new AdException(ex.getMessage());
        }
    }
}
