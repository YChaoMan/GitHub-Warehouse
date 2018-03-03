package com.modern;
/**
 * 2017-11-17 15:48:29
 * ����ʵ�ֵĹ�����Ҫ�Ǵ���ע�ᴰ��Ҳ�����¿�����
 * ����ͼƬ����ʵ�֣���ΪJFrame������
 * ��һ��JRootPane���ڶ���JLayerPane��������ContentPane
 * ����һ���ѱ�����ǩ���õ�һ�㣨JRootPane��Ȼ��ڶ�����͸����JLayerPane��ContentPane��
 * ���߷����ڵڶ������ϲ�͸��(setOpaque(false))
 * �������� ��дContentPane�ķ�����Ҳ���ǹ���JPanel��ʱ����д
 * JPanel��paintComponent(Graphics g)����
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
        setResizable(false);    // ���ɱ༭�����С
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // ʵ���¼���
        EventHand listen = new EventHand(tips);
        // ʵ�����������������
        ComponentAttributes componentSet = ComponentAttributes.getInterface();
        Font font = new Font("Microsofr-Yahei", Font.PLAIN, 16);    // ����������
        content = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        content.setLayout(gbl);
        
        ImageIcon icon = new ImageIcon("src/RegisterBackground.jpg");
        background = new JLabel(icon);  
        background.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        content.setOpaque(false);   // ���������͸��
        
        label = new JLabel(firstLabel);
        label.setFont(font);
        gbl.setConstraints(label, componentSet.componentSet(0, 0, 1, 1));
        content.add(label);
        
        name = new JTextField(10);
        name.setText(tips.getName());
        name.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {    // ������¼�
                tips.onclickText(e, name);
            }
            @Override
            public void mouseExited(MouseEvent e) { // ����뿪�¼�
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
            public void mouseClicked(MouseEvent e) {    // ������¼�
                tips.onclickText2(e, id);
            }
            @Override
            public void mouseExited(MouseEvent e) { // ����뿪�¼�
                tips.exitText2(e, id);
            }
        });
        gbl.setConstraints(id, componentSet.componentSet(1, 1, 3, 1));
        content.add(id);
        
        ensure = new JButton("ȷ��");
        ensure.setFont(font);
        gbl.setConstraints(ensure, componentSet.componentSet(1, 2, 1, 1));
        ensure.addActionListener(new ActionListener() { // �����¼�
            public void actionPerformed(ActionEvent e) {
                listen.do_ensure(e);
            }
        });
        content.add(ensure);
        
        cancel = new JButton("����");
        cancel.setFont(font);
        gbl.setConstraints(cancel,componentSet.componentSet(2, 2, 1, 1));
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {    // �����¼�
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
            String user = name.getText();   // ��ȡ���������
            String pass = id.getText();     // ��ȡ�����ѧ��
            // ���������ʽ�������涨�����ķ�Χ��ѧ�Ź涨������
            JudgeExists judge = JudgeExists.getInterface();
            boolean judgeTrue = judge.judge(id, name, user, pass, tips);
            if (judgeTrue) {
                // ��ȡѧ������
                Map<String,StudentInformation> student = LogPane.student;
                if (!student.containsKey(pass)) {   // ��ѯ�Ƿ���ڸü�ֵ,�Ƿ���ڸ�ѧ��
                    CreaseStudent create = CreaseStudent.getInterface();
                    create.createStudent(pass, new StudentInformation(pass, user), student);
                    JOptionPane.showMessageDialog(null, "ע��ɹ�"); //��������ʾ���֪�û�
                    id.setText("");  // �����յ�ǰ�ı���
                    name.setText("");
//                    setVisible(false);   // �����ظ����
                    return;
                } else {    // ������ʾ��Ϣ
                    JOptionPane.showMessageDialog(null, "ѧ���ѱ�ռ��");
                    return;
                }
            }
        }
        // ���ã��Ե����ı���������
        private void do_cancel(ActionEvent e) {
            id.setText(tips.getId());
            name.setText(tips.getName());
        }
    }
}
