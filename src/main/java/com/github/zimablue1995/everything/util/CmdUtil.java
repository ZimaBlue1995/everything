//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.github.zimablue1995.everything.util;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.github.zimablue1995.everything.killport.entity.NetStatEntity;
import com.github.zimablue1995.everything.killport.entity.PortEntity;
import org.apache.commons.lang3.StringUtils;

public class CmdUtil {
    public static String netStat = " netstat -aon|findstr  %s ";
    public static String findPidName = " tasklist /fi \"pid eq %s\" ";
    public static String taskKill = " taskkill /f /t /pid  %s ";
    public static String jpsl = " jps -l ";

    public CmdUtil() {
    }

    public static List<NetStatEntity> getNetStat(String port) {
        List<NetStatEntity> results = new ArrayList<>();
        Set<String> inputStr = CommandUtil.commandRunStr(new String[]{"cmd", "/c", String.format(netStat, port)});
        inputStr.forEach((it) -> {
            String[] split = StringUtils.split(it, " ");
            NetStatEntity netStatEntity = new NetStatEntity();
            netStatEntity.setProtocol(split[0]);
            netStatEntity.setLocalAddress(split[1]);
            netStatEntity.setForeignAddress(split[2]);
            if (split.length == 5) {
                netStatEntity.setStatus(split[3]);
                netStatEntity.setPid(split[4]);
            } else {
                netStatEntity.setStatus("");
                netStatEntity.setPid(split[3]);
            }

            if (!results.contains(netStatEntity) && !netStatEntity.getPid().equals("0")) {
                results.add(netStatEntity);
            }

        });
        return results;
    }

    public static String getPidName(String pid) {
        try {
            Set<String> inputStr = CommandUtil.commandRunStr(new String[]{"cmd", "/c", String.format(findPidName, pid)});

            assert inputStr != null;

            List<String> asList = new ArrayList(inputStr);
            String pid_name = StringUtils.split((String)asList.get(3), " ")[0];
            return "java.exe".equalsIgnoreCase(pid_name) ? getJpsName(pid) : pid_name;
        } catch (Exception var4) {
            var4.printStackTrace();
            return "进程名未知";
        }
    }

    public static List<String> jspl() {
        Set<String> inputStr = CommandUtil.commandRunStr(new String[]{"cmd", "/c", jpsl});
        return new ArrayList(inputStr);
    }

    public static String getJpsName(String pid) {
        List<String> jspl = jspl();

        for(int i = 0; i < jspl.size(); ++i) {
            String[] javas = StringUtils.split((String)jspl.get(i), " ");
            if (javas[0].equals(pid)) {
                String imag = javas[1];
                return imag;
            }
        }

        return "进程名未知";
    }

    public static List<PortEntity> getPortEntity(String port) {
        List<PortEntity> results = new ArrayList<>();
        getNetStat(port).forEach((i) -> {
            PortEntity portEntity = new PortEntity(i.getPid(), i.getProtocol(), getPidName(i.getPid()), i.getStatus());
            results.add(portEntity);
        });
        return results;
    }

    public static String killPid(String pid) {
        Set<String> inputStr = CommandUtil.commandRunStr(Charset.forName("GBK"), new String[]{"cmd", "/c", String.format(taskKill, pid)});

        assert inputStr != null;

        return Arrays.toString(inputStr.toArray());
    }
}
