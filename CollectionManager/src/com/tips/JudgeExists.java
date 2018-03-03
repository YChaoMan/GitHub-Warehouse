package com.tips;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class JudgeExists {

    private static JudgeExists judge;

    private JudgeExists() {
    }

    public boolean judge(JTextField idText, JTextField nameText, String name, String id, Tips tips) {
        // 输入格式检测，姓名限制在中文，学号限制为数字,利用正则表达式实现
        if (!(id.matches("[0-9]+") && name.matches("[\u4e00-\u9fa5]+"))) {
            JOptionPane.showMessageDialog(null, "用户名/学号格式错误");
            idText.setText(tips.getId());
            nameText.setText(tips.getName());
            return false;
        }
        if (tips.getName().equals(name) || tips.getId().equals(id)) {
            JOptionPane.showMessageDialog(null, "请按要求输入");
            idText.setText(tips.getId());
            nameText.setText(tips.getName());
            return false;
        }
        return true;
    }

    public boolean judge(JTextField idText, String id) {
        // 输入格式检测，姓名限制在中文，学号限制为数字
        if (!(id.matches("[0-9]+"))) {
            JOptionPane.showMessageDialog(null, "学号格式错误");
            idText.setText(Tips.getInterface().getId());
            return false;
        }
        return true;
    }

    // 单例模式，控制对象的生成
    public static JudgeExists getInterface() {
        if (judge == null) {
            judge = new JudgeExists();
        }
        return judge;
    }
}
