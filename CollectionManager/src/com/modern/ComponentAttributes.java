package com.modern;
/**
 * 2017-11-17 15:36:29
 * �������Ҫ���þ�����������ĸ�������
 * @author Administrator
 *
 */

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ComponentAttributes {
    
    private static ComponentAttributes position;
    private ComponentAttributes(){}
    /**
     * @param gridx ����������е�λ��֮x��
     * @param gridy ����������е�λ��֮y��
     * @param gridw ����Ŀ�ȣ�����
     * @param gridh ����ĸ߶ȣ�����
     * @return
     */
    protected GridBagConstraints componentSet(int gridx, int gridy, int gridw, int gridh) {
        
        GridBagConstraints gbc = new GridBagConstraints();
        Insets insets = new Insets(5, 5, 5, 5); // �������֮��ľ��룬�ϣ����£���
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridw;
        gbc.gridheight = gridh;
        // �����������䷽ʽ��BOTH��ʾ������HORIZONTAL��ʾ������䣬VERTICAL��ʾ�������
        gbc.fill = GridBagConstraints.BOTH; 
        gbc.anchor = GridBagConstraints.CENTER; // ����ڸ��ӵ�λ�ã��������õ��Ǿ���
        gbc.insets = insets;
        return gbc;
    }
    
    public static ComponentAttributes getInterface() {
        if (position == null) {
            position =  new ComponentAttributes();
        } 
        return position;
    }

}
