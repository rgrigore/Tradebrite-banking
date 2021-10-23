package com.codecool.banking.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Service
public class MathUtils {

    private static final DecimalFormat df = new DecimalFormat();
    static {
        df.setMinimumFractionDigits(2);
        df.setGroupingUsed(false);
    }

    public String addStrings(String s1, String s2) {
        BigDecimal result = (new BigDecimal(s1)).add(new BigDecimal(s2)).stripTrailingZeros();
        return df.format(result);
    }

    public String subtractStrings(String s1, String s2) {
        BigDecimal result = (new BigDecimal(s1)).subtract(new BigDecimal(s2)).stripTrailingZeros();
        return df.format(result);
    }
}
