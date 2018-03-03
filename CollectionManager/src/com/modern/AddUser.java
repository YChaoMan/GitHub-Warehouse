package com.modern;
/**
 * 2017-11-17 22:06:15
 * 该类主要作用在于添加用户，首先获取输入的学号、姓名再进行查询，接着条件
 */
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.increase.CreaseStudent;
import com.tips.JudgeExists;
import com.tips.Tips;


public class AddUser extends JFrame{

    private JPanel content;
    private JLabel label,label_2;
    private JTextField id,name;
    private JButton ensure,cancel;
    Map<String,StudentInformation> addStudent = LogPane.student;    // 获取登录面板中的student对象
    private Tips tips = null;
    
    public AddUser(Tips tip) {
    	this.tips = tip;
    }
    
    protected void addUserGo(String title, String firstLabel, String secondLabel) {
        setTitle(title);
        setBounds(640, 260, 300, 300);
        setVisible(true);   // 显示窗体
        setResizable(false);    // 不能更改窗体大小
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // 默认关闭该窗体，但不结束程序
        // 事件类实例化
        doAction listen = new doAction();
        // 组件属性实例化，获得对象
        ComponentAttributes ca = ComponentAttributes.getInterface();
        // 字体实例化
        Font font = new Font("Microsofr-Yahei", Font.PLAIN, 16);
        content = new JPanel();
        GridBagLayout gbl = new GridBagLayout();    // 设置窗体的布局。注意先声明GridBagLayout然后再声明GridBagConstraints
        content.setLayout(gbl);
        label = new JLabel(firstLabel);
        label.setFont(font);
        gbl.setConstraints(label, ca.componentSet(0, 0, 1, 1));
        content.add(label);
        
        name = new JTextField(10);
        name.setText(tips.getName());
        name.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tips.onclickText(e, name);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                tips.exitText(e, name);
            }
        });
        gbl.setConstraints(name, ca.componentSet(1, 0, 3, 1));
        content.add(name);
        
        label_2 = new JLabel(secondLabel);
        label_2.setFont(font);
        gbl.setConstraints(label_2, ca.componentSet(0, 1, 1, 1));
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
        gbl.setConstraints(id, ca.componentSet(1, 1, 3, 1));
        content.add(id);
        
        ensure = new JButton("确定");
        ensure.setFont(font);
        gbl.setConstraints(ensure, ca.componentSet(1, 2, 1, 1));
        ensure.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listen.do_ensure(e);    // 如果都符合条件，那么弹出新的窗体
//                setVisible(false);  // 然后隐藏该窗体
            }
        });
        content.add(ensure);
        
        cancel = new JButton("重置");
        cancel.setFont(font);
        gbl.setConstraints(cancel,ca.componentSet(2, 2, 1, 1));
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listen.do_cancel(e);
            }
        });
        content.add(cancel);
        add(content);
    }
    /**
     * 内部类编写用于处理事件
     * @author Administrator
     *
     */
    private class doAction {
        
        private Tips tips;
        
    	public doAction() {
    		this.tips = Tips.getInterface();
    	}
    	
        private void do_ensure(ActionEvent e) {
            String user = name.getText();   // 获取输入的用户名
            String pass = id.getText(); // 获取输入的学号
            JudgeExists judge = JudgeExists.getInterface();
            boolean judgeTrue = judge.judge(id, name, user, pass, tips);
            if (judgeTrue) {
                // 判断该键是否存在，不存在则添加
                boolean idExists = addStudent.containsKey(pass);
                if (!idExists) {
                    CreaseStudent crease = CreaseStudent.getInterface();
                    crease.createStudent(pass, new StudentInformation(pass, user), addStudent);
                    JOptionPane.showMessageDialog(null, "添加成功");
                    name.setText("");
                    id.setText("");
                    return;
                } else {    // 否则弹出提示，并结束当前程序
                    JOptionPane.showMessageDialog(null, "学号已被占用");
                    return;
                }
            }
        }
        // 重置
        private void do_cancel(ActionEvent e) {
            id.setText(tips.getId());
            name.setText(tips.getName());
        }
    }
}
