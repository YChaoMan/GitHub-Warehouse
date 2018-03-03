package com.modern;
/**
 * 2017-11-17 22:15:39
 * �������Ҫ���þ��Ǹ����û����¶��û����к�ʵ���ڸ���Ҫ���ĵ����ݽ��и���
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
        setTitle("������Ϣ");
        setBounds(640, 260, 300, 300);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        ListenUpdate listen = new ListenUpdate();
        
        updatePanel = new JPanel();
        updatePanel.setLayout(null); // ʹ�þ��Բ��ֽ��в���
        
        tips = new JLabel("��ѡ����Ҫ���µ����� : ");
        tips.setBounds(20, 20, 150, 30);
        updatePanel.add(tips);
        
        
        idRadio = new JRadioButton("ѧ��");
        idRadio.setBounds(40, 50, 80, 30);
        idRadio.setActionCommand("ѧ��");
        updatePanel.add(idRadio);
        
        nameRadio = new JRadioButton("����");
        nameRadio.setBounds(150, 50, 80, 30);
        nameRadio.setActionCommand("����");
        updatePanel.add(nameRadio);
        
        choose = new ButtonGroup(); // ����һ����ť��
        choose.add(idRadio);
        choose.add(nameRadio);  // �����ť�Ž���ť��ʵ�ֵ�ѡ����
        
        beforeTips = new JLabel("ѧ�� :");
        beforeTips.setBounds(43, 90, 100, 30);
        updatePanel.add(beforeTips);
        
        beforeValue = new JTextField();
        beforeValue.setBounds(100, 90, 160, 30);
        updatePanel.add(beforeValue);
        
        afterTips = new JLabel("��ֵ :");
        afterTips.setBounds(43, 150, 100, 30 );
        updatePanel.add(afterTips);
        
        afterValue = new JTextField();
        afterValue.setBounds(100, 150, 160, 30);
        updatePanel.add(afterValue);
        
        ensure = new JButton("ȷ��");
        ensure.setBounds(50, 200, 70, 40);
        ensure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    // �����¼�
                listen.chooseUpdate(e, logoUser, content, showUser);
            }
        });
        updatePanel.add(ensure);
        
        cancel = new JButton("����");
        cancel.setBounds(150, 200, 70, 40);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    // �����¼�
                listen.clean(e);
            }
        });
        updatePanel.add(cancel);
        
        
        setContentPane(updatePanel);
    }
    
    private class ListenUpdate {
        
        private void chooseUpdate(ActionEvent e, String loginUser, ContentPane content, JTextField showUser) {
            // ��ȡ���µ�����(ѧ��)
            String idInput = beforeValue.getText(); 
            // ��ȡ��Ҫ���µ�ֵ
            String valueInput = afterValue.getText();   
            
            // �ж��Ƿ�ѡ���˸������ݰ�ť
            if (!(idRadio.isSelected() || nameRadio.isSelected())) {
                JOptionPane.showMessageDialog(null, "��ѡ����µ�����");
                return;
            }
            // �ж�ѧ�ŵ������ʽ
            if (!(idInput.matches("[0-9]+"))) {
                JOptionPane.showMessageDialog(null, "ѧ�Ÿ�ʽ����");
                return;
            }
            // �Ը������ݽ��и�ʽ�жϣ���ʽ�޶������Ļ�������
            if (!(valueInput.matches("[\u4e00-\u9fa5]+|[0-9]+"))) {
                JOptionPane.showMessageDialog(null, "�������ݴ���");
                return;
            }
            // ��ȡ��ť����ѡ��ť������
            String selectRadio = choose.getSelection().getActionCommand();
            // �ж����������Ƿ�Ϊ�ջ��Ƿ�����û�
            boolean idExists = updateStudent.containsKey(idInput);
            // ��ȡ���ĵ�ѧ���Ƿ��Ѿ�����
            boolean idExists2 = updateStudent.containsKey(valueInput);
//            StudentInformation loginUserId = updateStudent.get(loginUser);
//            String name = loginUserId.getName();
            // ������и������ݺʹ��ڸ�ѧ��
            if (!valueInput.isEmpty() && idExists) {
                // �ж���ѡ�İ�ť
                if (selectRadio.equals("ѧ��")) {
                    // �жϸ���ֵ�Ƿ������ѡ��ťҪ��
                    if (!(valueInput.matches("[0-9]+"))) {
                        JOptionPane.showMessageDialog(null, "�������ݴ���");
                        return;
                    }
                    if (idExists2) {
                        JOptionPane.showMessageDialog(null, "ѧ���Ѵ���");
                        return;
                    }
                    /*
                     * ��ΪMap���ܸ��ļ�������ֻ��ɾ��ԭ�еļ��ٽ����½�
                     * �Ȼ��ԭ�ȼ���ֵ
                     */
                    String tempStudentName = updateStudent.get(idInput).getName();
                    // �Ƴ���ǰ��
                    updateStudent.remove(idInput);
                    // �½���ֵ��
                    CreaseStudent crease = CreaseStudent.getInterface();
                    crease.createStudent(valueInput, new StudentInformation(valueInput, tempStudentName), updateStudent);
                    content.setLoginUser(valueInput);
                    JOptionPane.showMessageDialog(null, "�������");
                    beforeValue.setText("");
                    afterValue.setText("");
                } else {
                    // �жϸ���ֵ�Ƿ������ѡ��ťҪ��
                    if (!(valueInput.matches("[\u4e00-\u9fa5]+"))) {
                        JOptionPane.showMessageDialog(null, "�������ݴ���");
                        return;
                    }
                    StudentInformation updateStudentName = new StudentInformation(idInput, valueInput);
                    updateStudent.replace(idInput, updateStudentName);
                    if (idInput.equals(updateStudent.get(loginUser).getId())) {
                        showUser.setText(content.thisUser(idInput));
                    }
                    JOptionPane.showMessageDialog(null, "�������");
                    beforeValue.setText("");
                    afterValue.setText("");
                }
            // �û������ڻ��߸���ֵ����Ϊ��
            } else {
                JOptionPane.showMessageDialog(null, "�û�Ϊ��");
            }
            
        }
        // ����������ò���
        private void clean(ActionEvent e) {
            beforeValue.setText("");
            afterValue.setText("");
        }
    }
}
