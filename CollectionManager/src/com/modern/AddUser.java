package com.modern;
/**
 * 2017-11-17 22:06:15
 * ������Ҫ������������û������Ȼ�ȡ�����ѧ�š������ٽ��в�ѯ����������
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
    Map<String,StudentInformation> addStudent = LogPane.student;    // ��ȡ��¼����е�student����
    private Tips tips = null;
    
    public AddUser(Tips tip) {
    	this.tips = tip;
    }
    
    protected void addUserGo(String title, String firstLabel, String secondLabel) {
        setTitle(title);
        setBounds(640, 260, 300, 300);
        setVisible(true);   // ��ʾ����
        setResizable(false);    // ���ܸ��Ĵ����С
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Ĭ�Ϲرոô��壬������������
        // �¼���ʵ����
        doAction listen = new doAction();
        // �������ʵ��������ö���
        ComponentAttributes ca = ComponentAttributes.getInterface();
        // ����ʵ����
        Font font = new Font("Microsofr-Yahei", Font.PLAIN, 16);
        content = new JPanel();
        GridBagLayout gbl = new GridBagLayout();    // ���ô���Ĳ��֡�ע��������GridBagLayoutȻ��������GridBagConstraints
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
            public void mouseClicked(MouseEvent e) {    // ������¼�
                tips.onclickText2(e, id);
            }
            @Override
            public void mouseExited(MouseEvent e) { // ����뿪�¼�
                tips.exitText2(e, id);
            }
        });
        gbl.setConstraints(id, ca.componentSet(1, 1, 3, 1));
        content.add(id);
        
        ensure = new JButton("ȷ��");
        ensure.setFont(font);
        gbl.setConstraints(ensure, ca.componentSet(1, 2, 1, 1));
        ensure.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listen.do_ensure(e);    // �����������������ô�����µĴ���
//                setVisible(false);  // Ȼ�����ظô���
            }
        });
        content.add(ensure);
        
        cancel = new JButton("����");
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
     * �ڲ����д���ڴ����¼�
     * @author Administrator
     *
     */
    private class doAction {
        
        private Tips tips;
        
    	public doAction() {
    		this.tips = Tips.getInterface();
    	}
    	
        private void do_ensure(ActionEvent e) {
            String user = name.getText();   // ��ȡ������û���
            String pass = id.getText(); // ��ȡ�����ѧ��
            JudgeExists judge = JudgeExists.getInterface();
            boolean judgeTrue = judge.judge(id, name, user, pass, tips);
            if (judgeTrue) {
                // �жϸü��Ƿ���ڣ������������
                boolean idExists = addStudent.containsKey(pass);
                if (!idExists) {
                    CreaseStudent crease = CreaseStudent.getInterface();
                    crease.createStudent(pass, new StudentInformation(pass, user), addStudent);
                    JOptionPane.showMessageDialog(null, "��ӳɹ�");
                    name.setText("");
                    id.setText("");
                    return;
                } else {    // ���򵯳���ʾ����������ǰ����
                    JOptionPane.showMessageDialog(null, "ѧ���ѱ�ռ��");
                    return;
                }
            }
        }
        // ����
        private void do_cancel(ActionEvent e) {
            id.setText(tips.getId());
            name.setText(tips.getName());
        }
    }
}
