package com.modern;
/**
 * 2017-11-17 15:36:29
 * 该类的主要作用就是设置组件的各类属性
 * @author Administrator
 *
 */

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ComponentAttributes {
    
    private static ComponentAttributes position;
    private ComponentAttributes(){}
    /**
     * @param gridx 组件在容器中的位置之x轴
     * @param gridy 组件在容器中的位置之y轴
     * @param gridw 组件的宽度，横向
     * @param gridh 组件的高度，纵向
     * @return
     */
    protected GridBagConstraints componentSet(int gridx, int gridy, int gridw, int gridh) {
        
        GridBagConstraints gbc = new GridBagConstraints();
        Insets insets = new Insets(5, 5, 5, 5); // 设置组件之间的距离，上，左，下，右
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridw;
        gbc.gridheight = gridh;
        // 设置组件的填充方式，BOTH表示填满，HORIZONTAL表示横向填充，VERTICAL表示纵向填充
        gbc.fill = GridBagConstraints.BOTH; 
        gbc.anchor = GridBagConstraints.CENTER; // 组件在格子的位置，这里设置的是居中
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
