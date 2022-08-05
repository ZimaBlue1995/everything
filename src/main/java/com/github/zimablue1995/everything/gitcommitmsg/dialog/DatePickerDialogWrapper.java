package com.github.zimablue1995.everything.gitcommitmsg.dialog;

import com.github.zimablue1995.everything.util.ToolUtil;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @version : 1.0
 * @auther : 齐马的作品
 * @date : 2022/7/7 23:16
 * @description:
 */
public class DatePickerDialogWrapper extends DialogWrapper {

    private static final Logger logger = Logger.getInstance(DatePickerDialogWrapper.class);

    private JPanel panel;
    private CustomOKAction okAction;
    private DialogWrapperExitAction exitAction;
    private Project project;

    public DatePickerDialogWrapper() {
        super(true);
        init();
        setTitle("选择日期");
    }

    public DatePickerDialogWrapper(@Nullable Project project) {
        super(true);
        init();
        this.project = project;
        setTitle("选择日期");
    }

    /**
     * 创建视图
     */
    @Override
    protected @Nullable JComponent createCenterPanel() {
        panel = new JPanel();
//        panel.setLayout(new BorderLayout());
//        JLabel label = new JLabel("日期");
//        label.setPreferredSize(new Dimension(100,100));
//        panel.add(label, BorderLayout.CENTER);

        JRadioButton rb1 = new JRadioButton("今天", true);
        JRadioButton rb2 = new JRadioButton("昨天");
        JRadioButton rb3 = new JRadioButton("前天");

        ButtonGroup group = new ButtonGroup();
        //添加JRadioButton到ButtonGroup中
        group.add(rb1);
        group.add(rb2);
        group.add(rb3);

        panel.add(rb1);
        panel.add(rb2);
        panel.add(rb3);

//        inputTextField = new JTextField();
//        panel.add(inputTextField, BorderLayout.NORTH);

        return panel;
    }

    /**
     * 校验数据
     *
     * @return 通过必须返回null，不通过返回一个 ValidationInfo 信息
     */
//    @Nullable
//    @Override
//    protected ValidationInfo doValidate() {
//        String text = inputTextField.getText();
//        if (StringUtils.isNotBlank(text)) {
//            return null;
//        } else {
//            return new ValidationInfo("校验不通过");
//        }
//    }

    /**
     * 覆盖默认的ok/cancel按钮
     */
    @Override
    protected Action @NotNull [] createActions() {
        exitAction = new DialogWrapperExitAction("关闭", CANCEL_EXIT_CODE);
        okAction = new CustomOKAction();
        // 设置默认的焦点按钮
        okAction.putValue(DialogWrapper.DEFAULT_ACTION, true);
        return new Action[]{okAction, exitAction};
    }

    /**
     * 自定义 ok Action
     */
    protected class CustomOKAction extends DialogWrapperAction {

        protected CustomOKAction() {
            super("拉取git记录");
        }

        @Override
        protected void doAction(ActionEvent e) {
            String time = "";
            for (Component c : panel.getComponents()) {
                if (c instanceof JRadioButton) {
                    JRadioButton jRadioButton = (JRadioButton) c;
                    if (jRadioButton.isSelected()) {
                        time += jRadioButton.getText();
                        break;
                    }
                }
            }

            Map<String, LocalDateTime> map = new HashMap<>();
            LocalDateTime now = LocalDateTime.now();
            map.put("今天", now);
            map.put("昨天", now.minusDays(1));
            map.put("前天", now.minusDays(2));

            String msg = getGitCommitMsgByDate(map.get(time));
            logger.warn("结果为:" + msg);
            if (msg == null || msg.trim().length() == 0) {
                msg = "【当前时间段无git提交记录！】";
            }

//            JOptionPane.showMessageDialog(null, "你选择了" + info);
//            Messages.showMessageDialog(project, msg, "git提交记录", null);

            String[] options = {"复制"};
            int i = Messages.showIdeaMessageDialog(project, msg, "git提交记录", options, 0, null, null);

            if (i == 0) {
                ToolUtil.setClipboardString(msg);
            }


//            Object[] options = {"复制"};
//            int m = JOptionPane.showOptionDialog(null, msg, "git提交记录", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

//            // 点击ok的时候进行数据校验
//            ValidationInfo validationInfo = doValidate();
//            if (validationInfo != null) {
//                Messages.showMessageDialog(validationInfo.message, "校验不通过", Messages.getInformationIcon());
//            } else {
//                close(CANCEL_EXIT_CODE);
//            }


        }
    }

    private String getGitCommitMsgByDate(LocalDateTime now) {

        // 获取当天日期
        LocalDateTime min = now.with(LocalTime.MIN);
        LocalDateTime max = now.with(LocalTime.MAX);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String minFormat = dateTimeFormatter.format(min);
        String maxFormat = dateTimeFormatter.format(max);


        String userCommand = "git config --get user.name";
        String user = execCMD(userCommand);
        logger.warn("用户名为：" + user);
        // try {
        //     user = new String(user.getBytes("gbk"), "utf-8");
        // } catch (UnsupportedEncodingException e) {
        //     logger.error(e);
        //     throw new RuntimeException(e);
        // }
        // logger.info("转码后的用户名为：" + user);
        String msgCommand = "git log --after=\"" + minFormat + "\" --before=\"" + maxFormat + "\" --author=\"" + user + "\" --pretty=format:%s --no-merges --reverse";

        String basePath = project.getBasePath();
        // String directoryStoreFolder = Project.DIRECTORY_STORE_FOLDER;
        // System.out.println("directoryStoreFolder = " + directoryStoreFolder);
        // System.out.println("project = " + project);
        // String projectFilePath = project.getProjectFilePath();
        // VirtualFile projectFile = project.getProjectFile();
        // VirtualFile workspaceFile = project.getWorkspaceFile();
        // VirtualFile baseDir = project.getBaseDir();
        logger.warn("项目路径为:" + basePath);
        logger.warn("git命令为:" + msgCommand);

//        String[] split = msg.split("\\r?\\n");
//
//        List<String> collect = Arrays.stream(split)
//                .collect(Collectors.toList());
//
//        System.out.println("msg=" + msg);
//        System.out.println("collect = " + collect);
        return execCMDWithPath(basePath, msgCommand);
    }

    //执行cmd命令，获取返回结果
    public static String execCMD(String command) {
        StringBuilder sb = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (Exception e) {
            return e.toString();
        }
        return sb.toString();
    }

    public static String execCMDWithPath(String path, String command) {
        StringBuilder sb = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec(command, null, new File(path));

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (Exception e) {
            logger.warn(e);
            return e.toString();
        }
        return sb.toString();
    }

}
