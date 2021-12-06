package com.shopby.utils;

public class StringSplitBuilder {
    public static String getSplitPrice(int price) {
        StringBuilder builder = new StringBuilder();
        String strPrice = Integer.toString(price);
        int i = strPrice.length() % 3;
        builder.append(strPrice, 0, i);

        while (i < strPrice.length()) {
            if (!builder.toString().equals("")) {
                builder.append(",");
            }
            builder.append(strPrice, i, i + 3);
            i += 3;
        }
        return builder.toString();
    }
}
