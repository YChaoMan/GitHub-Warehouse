package com.tips;

/**
 * 该类用于提供鼠标点击及离开时的显示效果
 */
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

public class Tips {

    private final String nameTips = "这里输入姓名";
    private final String idTips = "格式：1601134XX";
    private static Tips tips;

    private Tips() {
    }

    /**
     * 这是设置姓名标签的效果
     * 当鼠标点击时判断单行文本域是否有内容
     */
    public void onclickText(MouseEvent e, JTextField name) {
        String tempNameString = name.getText();
        if (nameTips.equals(tempNameString)) {
            name.setText("");
        } else {
            name.setText(tempNameString);
        }
    }

    // 当鼠标离开时判断单行文本域是否有内容
    public void exitText(MouseEvent e, JTextField name) {
        String tempNameString = name.getText();
        if (tempNameString.isEmpty()) {
            name.setText(nameTips);
        }

    }

    /**
     * 这是设置学号的效果
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
