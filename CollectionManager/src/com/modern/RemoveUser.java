package com.modern;
/**
 * 2017-11-17 22:12:51
 * ������Ҫ������ɾ�������ڵ�Ԫ��
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
        setTitle("ɾ���û�");
        setBounds(640, 260, 300, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // �¼���ʵ����
        ListenDoMain listen = new ListenDoMain();
        // ���������ʵ����
        ComponentAttributes ca = ComponentAttributes.getInterface();
        // ����������
        Font font = new Font("����", Font.BOLD, 14);
        GridBagLayout gbl = new GridBagLayout();
        panel = new JPanel();
        panel.setLayout(gbl);

        id = new JLabel("ѧ��:  ");
        id.setFont(font);
        gbl.setConstraints(id, ca.componentSet(0, 0, 1, 1));
        panel.add(id);

        inputId = new JTextField(10);
        inputId.setText(tips.getId());
        inputId.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { // ������¼�
                tips.onclickText2(e, inputId);
            }

            @Override
            public void mouseExited(MouseEvent e) { // ����뿪�¼�
                tips.exitText2(e, inputId);
            }
        });
        gbl.setConstraints(inputId, ca.componentSet(1, 0, 2, 1));
        panel.add(inputId);

        ensure = new JButton("ȷ��");
        ensure.setFont(font);
        gbl.setConstraints(ensure, ca.componentSet(1, 1, 1, 1));
        ensure.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { // ����¼�
                listen.do_ensure(e, tips, user);
            }
        });
        panel.add(ensure);

        cancel = new JButton("����");
        cancel.setFont(font);
        gbl.setConstraints(cancel, ca.componentSet(2, 1, 1, 1));
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { // ����¼�
                listen.do_cancel(e);
            }
        });
        panel.add(cancel);
        setContentPane(panel);

    }
    /**
     * ����һ���ڲ��࣬���ڴ����¼� 
     */
    private class ListenDoMain {

        private void do_ensure(ActionEvent e, Tips tips, String logoUser) {
            String pass = inputId.getText(); // ��ȡ�û���Ҫɾ����ѧ��
            JudgeExists judge = JudgeExists.getInterface();
            boolean judgeTrue = judge.judge(inputId, pass);
            boolean idExists = removeStudent.containsKey(pass); // �ж��Ƿ��и�ѧ�ŵĴ���
            if (!judgeTrue) {
                inputId.setText(tips.getId());
            }
            if (idExists && judgeTrue) { // �������
                StudentInformation logoUserId = removeStudent.get(logoUser);
                String logoId = logoUserId.getId();
                if (pass != null && logoId.equals(pass)) {
                    JOptionPane.showMessageDialog(null, "�����Ƴ���ǰ��¼�û�");
                    return;
                }
                removeStudent.remove(pass); // �ڼ������Ƴ��ü�
                JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
                inputId.setText(""); // ���������
                return;
            } else { // ���������
                JOptionPane.showMessageDialog(null, "ѧ�Ų�����");
                return;
            }
        }

        // ����
        private void do_cancel(ActionEvent e) {
            inputId.setText(tips.getId());
        }
    }
}
