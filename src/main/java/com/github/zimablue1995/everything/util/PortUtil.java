//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.github.zimablue1995.everything.util;

import com.intellij.openapi.ui.MessageDialogBuilder;
import com.intellij.openapi.ui.Messages;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PortUtil {
    public PortUtil() {
    }

    public static Boolean verifyPort(String portStr) {
        Boolean ok = false;
        if ("".equals(portStr) || Objects.isNull(portStr)) {
            MessageDialogBuilder.yesNo("操作结果", "要输入值才能使用哦").show();
            ok = true;
        }

        if (!isNumber(portStr)) {
            MessageDialogBuilder.yesNo("操作结果", "端口是数字哦").show();
            ok = true;
        } else {
            Integer port = Integer.valueOf(portStr);
            Integer maxPort = 65535;
            Integer minPort = 0;
            if (port.compareTo(maxPort) > 0 || port.compareTo(minPort) < 0) {
                MessageDialogBuilder.yesNo("操作结果", "端口范围是0-65535哦").show();
                ok = true;
            }
        }

        return ok;
    }

    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }
}
