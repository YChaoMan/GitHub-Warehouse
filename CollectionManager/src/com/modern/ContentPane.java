package com.modern;
/**
 * 2017-11-17 18:53:42
 * ������Ҫ�����ǵ�¼�ɹ��󣬿�ʵ������ɾ���ġ���
 * ��Ϊ�˴���������壬JPanel,JScrollPane���Զ������˲�͸��
 * @author Administrator
 *
 */

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import com.tips.Tips;


public class ContentPane extends JFrame{

    private JPanel managerPan;
    private JScrollPane tablePan;
    private JTextField showUseText;
    private JLabel background;
    private JMenuItem nowTime;
    private JTable contentShow;
    private JButton addButton, removeButton, updateButton, selectButton;
    private Map<String,StudentInformation> student = LogPane.student;
    private String loginUser;   // ��װ��¼���û���ʵ�ָ���ʱ��̬�仯
    
    public String getLoginUser() {
        return this.loginUser;
    }
    public void setLoginUser(String User) {
        this.loginUser = User;
    }
    
    protected void userManager(Tips tips, ContentPane content) {
        
        setTitle("�û�����");
        setBounds(450, 160, 750, 560);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �رյ�ǰ���ڣ�����������
        
        // �¼������ʵ������������˵���ȫ�����ܣ������û���ɾ���û��������û�
        DoListen listen = new DoListen();
        managerPan = new JPanel();
        // ���ڴ�ʱ��������ʱ�䣬������ʱ��
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {   // ���ڴ��¼�
                listen.nowTime(e);
            }
        });
        // ����JPanel�Ĳ���Ϊ���Բ��֣�����GridBaglayout����������ǰ��Ҫ�Լ������С������ֱ�Ӱ���ݹ�����������
        managerPan.setLayout(null); 
        // ��ʾ��ǰ��¼�û�
        showUseText = new JTextField(10);
        showUseText.setBounds(25, 25, 200, 50);
        showUseText.setFont(new Font("Microsoft Yahei", Font.PLAIN, 13));
        showUseText.setEditable(false); // ���ɱ༭
        showUseText.setText(thisUser(getLoginUser()));
        showUseText.setHorizontalAlignment(SwingConstants.LEFT);
        showUseText.setOpaque(false);   // ���ر�����ʵ��͸��������
        managerPan.add(showUseText);
        // ��ʾ��ǰʱ��
        nowTime = new JMenuItem();
        nowTime.setText("�����ʾ��ǰʱ��");
        nowTime.setBounds(250, 25, 500, 50);
        managerPan.add(nowTime);
        // ��ʾ��ѯ�û�
        contentShow = new JTable();
        contentShow.setRowSelectionAllowed(false);  // ������ѡ�����
        contentShow.setColumnSelectionAllowed(false);   // ������ѡ�����
        // ��ѯ��ʾ�Ĺ������
        tablePan = new JScrollPane();
        tablePan.setViewportView(contentShow);
        tablePan.setBounds(250, 100, 445, 380);
        managerPan.add(tablePan);
        
        ImageIcon add = new ImageIcon("src/add.jpg");   // �����û�
        addButton = new JButton(add);
        addButton.setBounds(25, 120, 200, 50);
        addButton.addActionListener(new ActionListener() {    // ����¼�
            @Override
            public void actionPerformed(ActionEvent e) {
                listen.addValue(e, tips);
            }
        });
        managerPan.add(addButton);
        
        ImageIcon remove = new ImageIcon("src/remove.jpg"); // �Ƴ��û�
        removeButton = new JButton(remove);
        removeButton.setBounds(25, 220, 200, 50);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    // ����¼�
                listen.removeValue(e, getLoginUser());
            }
        });
        managerPan.add(removeButton);
        
        ImageIcon update = new ImageIcon("src/update.jpg"); // �����û�
        updateButton = new JButton(update);
        updateButton.setBounds(25, 320, 200, 50);
        updateButton.addActionListener(new ActionListener() {    // ����¼�
            @Override
            public void actionPerformed(ActionEvent e) {
                listen.updateValue(e, getLoginUser(), content, showUseText);
            }
        });
        managerPan.add(updateButton);
        
        ImageIcon select = new ImageIcon("src/select.jpg"); // �����û�
        selectButton = new JButton(select);
        selectButton.setBounds(25, 420, 200, 50);
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    // ����¼�
                try {
                    listen.selectValue(e);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        managerPan.add(selectButton);
        ImageIcon icon = new ImageIcon("src/contentPanBackground.jpg");
        background =  new JLabel(icon); //  ����һ��ͼƬ��ǩ
        // �Ӵ��ڵ����Ͻǿ�ʼ�̷ţ�����Ĵ�С��ͼƬ�Ĵ�С����
        background.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        // JFrame�����㣬JRootPane��JLayerPane��JContentPane�������õ��ǵڶ���
        getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        managerPan.setOpaque(false);  // ����JPanelΪ����ʾ
        setContentPane(managerPan);
        
    }
    
    protected String thisUser(String id) {
        return "��ǰ��¼�û�:  " + student.get(id).getName();
    }
    
    private class DoListen {
        
      /**
       *  ��̬��ȡ��ǰʱ�䣬��ΪҪʵ��һ�򿪴��ھͳ���ʱ�䣬�������˴����¼��Ͷ����¼�
       *  �ȴ��������¼��ٴ��������¼�
       *  
       */
        private void nowTime(WindowEvent e) {
            // ��ʽ����ǰʱ��
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
            // ���ü�ʱ����ִ�м��Ϊ1��
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Date time = new Date(); // ��ȡ��ǰʱ��
                    nowTime.setText("��ǰʱ��: " + format.format(time));    // �����ı�������
                }
            });
            timer.start();  // ��ʱ����ʼ
        }
        /**
         *  ��ѯ�û�
         */
        private void selectValue(ActionEvent e) {
            String[] columns = {"ѧ��", "����"};
            String temp = null; // �������漯�ϵ�����
            String[] temp2 = null;  // �������ս��������ݷָ�������
            // �����е�����Ϊѧ�ź�������0��
            DefaultTableModel dtm = new DefaultTableModel(columns, 0);
            contentShow.setModel(dtm);  // �����б��ģ��
            Map<String,StudentInformation> selectStudent = student;
            Set<Entry<String, StudentInformation>> entry = selectStudent.entrySet();  // ��ȡ���ϵ����м�ֵ
            if (entry == null) {
                return;
            }
            // �����ü��ϣ���������ʾ�ڹ������
            for (Entry<String, StudentInformation> entry2 : entry) {
                temp = entry2.getKey() + ","+ entry2.getValue().getName();
                temp2 = temp.split(",");
                dtm.addRow(temp2);
            }
        }
        // �����û�
        private void addValue(ActionEvent e, Tips tip) {
            AddUser add = new AddUser(tip);
            add.addUserGo("ע��", "������  ", "ѧ�ţ�  ");
            add.setVisible(true);
        }
        // �Ƴ��û�
        private void removeValue(ActionEvent e, String loginUser) {
            RemoveUser remove = new RemoveUser();
            remove.removeUser(loginUser);
            remove.setVisible(true);
            
        }
        // �����û�
        private void updateValue(ActionEvent e, String loginUser, ContentPane content, JTextField showUser) {
            UpdateUser update = new UpdateUser();
            update.updateUser(loginUser, content, showUser);
            update.setVisible(true);
        }
    }
}
