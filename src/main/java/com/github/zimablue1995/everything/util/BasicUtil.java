package com.github.zimablue1995.everything.util;

import java.util.Collection;
import java.util.Map;

public class BasicUtil {

    /**
     * 判断是否为空
     *
     * @param obj
     * @return
     */
    public static Boolean isEmpyt(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj == "" || obj == "null") {
            return true;
        }

        if (obj instanceof String) {
            return ((String) obj).trim().length() == 0;
        } else if (obj instanceof Collection) {
            return ((Collection) obj).size() == 0;
        } else if (obj instanceof Map) {
            return ((Map) obj).size() == 0;
        }

        return false;
    }
}