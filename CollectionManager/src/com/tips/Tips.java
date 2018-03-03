package com.tips;

/**
 * ���������ṩ��������뿪ʱ����ʾЧ��
 */
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

public class Tips {

    private final String nameTips = "������������";
    private final String idTips = "��ʽ��1601134XX";
    private static Tips tips;

    private Tips() {
    }

    /**
     * ��������������ǩ��Ч��
     * �������ʱ�жϵ����ı����Ƿ�������
     */
    public void onclickText(MouseEvent e, JTextField name) {
        String tempNameString = name.getText();
        if (nameTips.equals(tempNameString)) {
            name.setText("");
        } else {
            name.setText(tempNameString);
        }
    }

    // ������뿪ʱ�жϵ����ı����Ƿ�������
    public void exitText(MouseEvent e, JTextField name) {
        String tempNameString = name.getText();
        if (tempNameString.isEmpty()) {
            name.setText(nameTips);
        }

    }

    /**
     * ��������ѧ�ŵ�Ч��
     */
    public void onclickText2(MouseEvent e, JTextField id) {
        String tempIdString = id.getText();
        if (idTips.equals(tempIdString)) {
            id.setText("");
        } else {
            id.setText(tempIdString);
        }

    }

    public void exitText2(MouseEvent e, JTextField id) {
        String tempIDString = id.getText();
        if (tempIDString.isEmpty()) {
            id.setText(idTips);
        }
    }

    public String getName() {
        return nameTips;
    }

    public String getId() {
        return idTips;
    }

    public static Tips getInterface() {
        if (tips == null) {
            tips = new Tips();
        }
        return tips;
    }
}
