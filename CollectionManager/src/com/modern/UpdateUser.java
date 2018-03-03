package com.modern;
/**
 * 2017-11-17 22:15:39
 * 该类的主要作用就是更新用户，新对用户进行核实，在根据要更改的内容进行更改
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.increase.CreaseStudent;


public class UpdateUser extends JFrame{

    private JPanel updatePanel;
    private JLabel tips, beforeTips, afterTips;
    private JTextField beforeValue, afterValue;
    private JRadioButton idRadio, nameRadio;
    private JButton ensure, cancel;
    private ButtonGroup choose;
    private Map<String, StudentInformation> updateStudent = LogPane.student;
    
    protected void updateUser(String logoUser, ContentPane content, JTextField showUser) {
        setTitle("更新信息");
        setBounds(640, 260, 300, 300);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        ListenUpdate listen = new ListenUpdate();
        
        updatePanel = new JPanel();
        updatePanel.setLayout(null); // 使用绝对布局进行布局
        
        tips = new JLabel("请选择需要更新的内容 : ");
        tips.setBounds(20, 20, 150, 30);
        updatePanel.add(tips);
        
        
        idRadio = new JRadioButton("学号");
        idRadio.setBounds(40, 50, 80, 30);
        idRadio.setActionCommand("学号");
        updatePanel.add(idRadio);
        
        nameRadio = new JRadioButton("姓名");
        nameRadio.setBounds(150, 50, 80, 30);
        nameRadio.setActionCommand("姓名");
        updatePanel.add(nameRadio);
        
        choose = new ButtonGroup(); // 创建一个按钮组
        choose.add(idRadio);
        choose.add(nameRadio);  // 多个按钮放进按钮组实现单选功能
        
        beforeTips = new JLabel("学号 :");
        beforeTips.setBounds(43, 90, 100, 30);
        updatePanel.add(beforeTips);
        
        beforeValue = new JTextField();
        beforeValue.setBounds(100, 90, 160, 30);
        updatePanel.add(beforeValue);
        
        afterTips = new JLabel("新值 :");
        afterTips.setBounds(43, 150, 100, 30 );
        updatePanel.add(afterTips);
        
        afterValue = new JTextField();
        afterValue.setBounds(100, 150, 160, 30);
        updatePanel.add(afterValue);
        
        ensure = new JButton("确定");
        ensure.setBounds(50, 200, 70, 40);
        ensure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    // 动作事件
                listen.chooseUpdate(e, logoUser, content, showUser);
            }
        });
        updatePanel.add(ensure);
        
        cancel = new JButton("重置");
        cancel.setBounds(150, 200, 70, 40);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    // 动作事件
                listen.clean(e);
            }
        });
        updatePanel.add(cancel);
        
        
        setContentPane(updatePanel);
    }
    
    private class ListenUpdate {
        
        private void chooseUpdate(ActionEvent e, String loginUser, ContentPane content, JTextField showUser) {
            // 获取更新的条件(学号)
            String idInput = beforeValue.getText(); 
            // 获取将要更新的值
            String valueInput = afterValue.getText();   
            
            // 判断是否选择了更改内容按钮
            if (!(idRadio.isSelected() || nameRadio.isSelected())) {
                JOptionPane.showMessageDialog(null, "请选择更新的内容");
                return;
            }
            // 判断学号的输入格式
            if (!(idInput.matches("[0-9]+"))) {
                JOptionPane.showMessageDialog(null, "学号格式错误");
                return;
            }
            // 对更新内容进行格式判断，格式限定在中文或者数字
            if (!(valueInput.matches("[\u4e00-\u9fa5]+|[0-9]+"))) {
                JOptionPane.showMessageDialog(null, "更新内容错误");
                return;
            }
            // 获取按钮组中选择按钮的文字
            String selectRadio = choose.getSelection().getActionCommand();
            // 判断输入内容是否为空或是否存在用户
            boolean idExists = updateStudent.containsKey(idInput);
            // 获取更改的学号是否已经存在
            boolean idExists2 = updateStudent.containsKey(valueInput);
//            StudentInformation loginUserId = updateStudent.get(loginUser);
//            String name = loginUserId.getName();
            // 如果含有更新内容和存在该学号
            if (!valueInput.isEmpty() && idExists) {
                // 判断所选的按钮
                if (selectRadio.equals("学号")) {
                    // 判断更改值是否符合所选按钮要求
                    if (!(valueInput.matches("[0-9]+"))) {
                        JOptionPane.showMessageDialog(null, "更新内容错误");
                        return;
                    }
                    if (idExists2) {
                        JOptionPane.showMessageDialog(null, "学号已存在");
                        return;
                    }
                    /*
                     * 因为Map不能更改键，所以只能删除原有的键再进行新建
                     * 先获得原先键的值
                     */
                    String tempStudentName = updateStudent.get(idInput).getName();
                    // 移除当前键
                    updateStudent.remove(idInput);
                    // 新建键值对
                    CreaseStudent crease = CreaseStudent.getInterface();
                    crease.createStudent(valueInput, new StudentInformation(valueInput, tempStudentName), updateStudent);
                    content.setLoginUser(valueInput);
                    JOptionPane.showMessageDialog(null, "更新完成");
                    beforeValue.setText("");
                    afterValue.setText("");
                } else {
                    // 判断更改值是否符合所选按钮要求
                    if (!(valueInput.matches("[\u4e00-\u9fa5]+"))) {
                        JOptionPane.showMessageDialog(null, "更新内容错误");
                        return;
                    }
                    StudentInformation updateStudentName = new StudentInformation(idInput, valueInput);
                    updateStudent.replace(idInput, updateStudentName);
                    if (idInput.equals(updateStudent.get(loginUser).getId())) {
                        showUser.setText(content.thisUser(idInput));
                    }
                    JOptionPane.showMessageDialog(null, "更新完成");
                    beforeValue.setText("");
                    afterValue.setText("");
                }
            // 用户不存在或者更改值内容为空
            } else {
                JOptionPane.showMessageDialog(null, "用户为空");
            }
            
        }
        // 进行清空重置操作
        private void clean(ActionEvent e) {
            beforeValue.setText("");
            afterValue.setText("");
        }
    }
}
