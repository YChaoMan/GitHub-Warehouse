package com.modern;
/**
 * 2017-11-17 22:12:51
 * 该类主要作用是删除集合内的元素
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

import com.tips.JudgeExists;
import com.tips.Tips;


public class RemoveUser extends JFrame{

    private JPanel panel;
    private JLabel id;
    private JTextField inputId;
    private JButton ensure, cancel;
    private Tips tips = Tips.getInterface();
    private Map<String, StudentInformation> removeStudent = LogPane.student;
    
    public RemoveUser () {}
    
    protected void removeUser(String user) {
        setTitle("删除用户");
        setBounds(640, 260, 300, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // 事件类实例化
        ListenDoMain listen = new ListenDoMain();
        // 组件属性类实例化
        ComponentAttributes ca = ComponentAttributes.getInterface();
        // 字体类声明
        Font font = new Font("宋体", Font.BOLD, 14);
        GridBagLayout gbl = new GridBagLayout();
        panel = new JPanel();
        panel.setLayout(gbl);

        id = new JLabel("学号:  ");
        id.setFont(font);
        gbl.setConstraints(id, ca.componentSet(0, 0, 1, 1));
        panel.add(id);

        inputId = new JTextField(10);
        inputId.setText(tips.getId());
        inputId.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { // 鼠标点击事件
                tips.onclickText2(e, inputId);
            }

            @Override
            public void mouseExited(MouseEvent e) { // 鼠标离开事件
                tips.exitText2(e, inputId);
            }
        });
        gbl.setConstraints(inputId, ca.componentSet(1, 0, 2, 1));
        panel.add(inputId);

        ensure = new JButton("确定");
        ensure.setFont(font);
        gbl.setConstraints(ensure, ca.componentSet(1, 1, 1, 1));
        ensure.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { // 点击事件
                listen.do_ensure(e, tips, user);
            }
        });
        panel.add(ensure);

        cancel = new JButton("重置");
        cancel.setFont(font);
        gbl.setConstraints(cancel, ca.componentSet(2, 1, 1, 1));
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { // 点击事件
                listen.do_cancel(e);
            }
        });
        panel.add(cancel);
        setContentPane(panel);

    }
    /**
     * 定义一个内部类，用于处理事件 
     */
    private class ListenDoMain {

        private void do_ensure(ActionEvent e, Tips tips, String logoUser) {
            String pass = inputId.getText(); // 获取用户需要删除的学号
            JudgeExists judge = JudgeExists.getInterface();
            boolean judgeTrue = judge.judge(inputId, pass);
            boolean idExists = removeStudent.containsKey(pass); // 判断是否有该学号的存在
            if (!judgeTrue) {
                inputId.setText(tips.getId());
            }
            if (idExists && judgeTrue) { // 如果存在
                StudentInformation logoUserId = removeStudent.get(logoUser);
                String logoId = logoUserId.getId();
                if (pass != null && logoId.equals(pass)) {
                    JOptionPane.showMessageDialog(null, "不能移除当前登录用户");
                    return;
                }
                removeStudent.remove(pass); // 在集合中移除该键
                JOptionPane.showMessageDialog(null, "删除成功！");
                inputId.setText(""); // 随后进行清空
                return;
            } else { // 如果不存在
                JOptionPane.showMessageDialog(null, "学号不存在");
                return;
            }
        }

        // 重置
        private void do_cancel(ActionEvent e) {
            inputId.setText(tips.getId());
        }
    }
}
