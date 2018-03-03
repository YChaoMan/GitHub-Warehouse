package com.tips;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class JudgeExists {

    private static JudgeExists judge;

    private JudgeExists() {
    }

    public boolean judge(JTextField idText, JTextField nameText, String name, String id, Tips tips) {
        // �����ʽ��⣬�������������ģ�ѧ������Ϊ����,����������ʽʵ��
        if (!(id.matches("[0-9]+") && name.matches("[\u4e00-\u9fa5]+"))) {
            JOptionPane.showMessageDialog(null, "�û���/ѧ�Ÿ�ʽ����");
            idText.setText(tips.getId());
            nameText.setText(tips.getName());
            return false;
        }
        if (tips.getName().equals(name) || tips.getId().equals(id)) {
            JOptionPane.showMessageDialog(null, "�밴Ҫ������");
            idText.setText(tips.getId());
            nameText.setText(tips.getName());
            return false;
        }
        return true;
    }

    public boolean judge(JTextField idText, String id) {
        // �����ʽ��⣬�������������ģ�ѧ������Ϊ����
        if (!(id.matches("[0-9]+"))) {
            JOptionPane.showMessageDialog(null, "ѧ�Ÿ�ʽ����");
            idText.setText(Tips.getInterface().getId());
            return false;
        }
        return true;
    }

    // ����ģʽ�����ƶ��������
    public static JudgeExists getInterface() {
        if (judge == null) {
            judge = new JudgeExists();
        }
        return judge;
    }
}
