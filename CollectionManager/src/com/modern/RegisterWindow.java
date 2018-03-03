package com.modern;
/**
 * 2017-11-17 15:48:29
 * 该类实现的功能主要是创建注册窗口也就是新开窗口
 * 背景图片功能实现，因为JFrame有三层
 * 第一层JRootPane、第二层JLayerPane、第三层ContentPane
 * 方法一：把背景标签放置第一层（JRootPane）然后第二三层透明（JLayerPane、ContentPane）
 * 或者放置在第二层最上层透明(setOpaque(false))
 * 方法二： 重写ContentPane的方法，也就是构造JPanel的时候重写
 * JPanel的paintComponent(Graphics g)方法
 */
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.increase.CreaseStudent;
import com.tips.JudgeExists;
import com.tips.Tips;



public class RegisterWindow extends JFrame{

    private JPanel content;
    private JLabel label,label_2, background;
    private JTextField id,name;
    private JButton ensure,cancel;
    
    public RegisterWindow(){}
    
    protected void addUserGo(String title, String firstLabel, String secondLabel, Tips tips) {
        setTitle(title);
        setBounds(640, 260, 300, 300);
        setVisible(true);
        setResizable(false);    // 不可编辑窗体大小
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // 实例事件类
        EventHand listen = new EventHand(tips);
        // 实例化组件的属性设置
        ComponentAttributes componentSet = ComponentAttributes.getInterface();
        Font font = new Font("Microsofr-Yahei", Font.PLAIN, 16);    // 设置字体类
        content = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        content.setLayout(gbl);
        
        ImageIcon icon = new ImageIcon("src/RegisterBackground.jpg");
        background = new JLabel(icon);  
        background.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        content.setOpaque(false);   // 将面板设置透明
        
        label = new JLabel(firstLabel);
        label.setFont(font);
        gbl.setConstraints(label, componentSet.componentSet(0, 0, 1, 1));
        content.add(label);
        
        name = new JTextField(10);
        name.setText(tips.getName());
        name.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {    // 鼠标点击事件
                tips.onclickText(e, name);
            }
            @Override
            public void mouseExited(MouseEvent e) { // 鼠标离开事件
                tips.exitText(e, name);
            }
        });
        gbl.setConstraints(name, componentSet.componentSet(1, 0, 3, 1));
        content.add(name);
        
        label_2 = new JLabel(secondLabel);
        label_2.setFont(font);
        gbl.setConstraints(label_2, componentSet.componentSet(0, 1, 1, 1));
        content.add(label_2);
        
        id = new JTextField(10);
        id.setText(tips.getId());
        id.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {    // 鼠标点击事件
                tips.onclickText2(e, id);
            }
            @Override
            public void mouseExited(MouseEvent e) { // 鼠标离开事件
                tips.exitText2(e, id);
            }
        });
        gbl.setConstraints(id, componentSet.componentSet(1, 1, 3, 1));
        content.add(id);
        
        ensure = new JButton("确定");
        ensure.setFont(font);
        gbl.setConstraints(ensure, componentSet.componentSet(1, 2, 1, 1));
        ensure.addActionListener(new ActionListener() { // 动作事件
            public void actionPerformed(ActionEvent e) {
                listen.do_ensure(e);
            }
        });
        content.add(ensure);
        
        cancel = new JButton("重置");
        cancel.setFont(font);
        gbl.setConstraints(cancel,componentSet.componentSet(2, 2, 1, 1));
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {    // 动作事件
                listen.do_cancel(e);
            }
        });
        content.add(cancel);
        setContentPane(content);
    }
    
    private class EventHand {
    	
    	private Tips tips = null;
    	
    	public EventHand(Tips tis) {
    		this.tips = tis;
    	}
    	
        private void do_ensure(ActionEvent e) {
            String user = name.getText();   // 获取输入的姓名
            String pass = id.getText();     // 获取输入的学号
            // 限制输入格式，姓名规定在中文范围，学号规定在数字
            JudgeExists judge = JudgeExists.getInterface();
            boolean judgeTrue = judge.judge(id, name, user, pass, tips);
            if (judgeTrue) {
                // 获取学生集合
                Map<String,StudentInformation> student = LogPane.student;
                if (!student.containsKey(pass)) {   // 查询是否存在该键值,是否存在该学号
                    CreaseStudent create = CreaseStudent.getInterface();
                    create.createStudent(pass, new StudentInformation(pass, user), student);
                    JOptionPane.showMessageDialog(null, "注册成功"); //　弹出提示框告知用户
                    id.setText("");  // 随后清空当前文本域
                    name.setText("");
//                    setVisible(false);   // 再隐藏该面板
                    return;
                } else {    // 否则提示信息
                    JOptionPane.showMessageDialog(null, "学号已被占用");
                    return;
                }
            }
        }
        // 重置，对单行文本域进行清空
        private void do_cancel(ActionEvent e) {
            id.setText(tips.getId());
            name.setText(tips.getName());
        }
    }
}
