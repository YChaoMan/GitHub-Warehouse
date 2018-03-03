package com.modern;
/**
 * 2017-11-17 18:53:42
 * 该类主要功能是登录成功后，可实现增、删、改、查
 * 因为此处有两个面板，JPanel,JScrollPane所以都设置了不透明
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
    private String loginUser;   // 封装登录的用户，实现更新时动态变化
    
    public String getLoginUser() {
        return this.loginUser;
    }
    public void setLoginUser(String User) {
        this.loginUser = User;
    }
    
    protected void userManager(Tips tips, ContentPane content) {
        
        setTitle("用户管理");
        setBounds(450, 160, 750, 560);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭当前窗口，并结束程序
        
        // 事件类进行实例，这里包括了调用全部功能，增加用户、删除用户、更新用户
        DoListen listen = new DoListen();
        managerPan = new JPanel();
        // 窗口打开时触发窗口时间，触发计时器
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {   // 窗口打开事件
                listen.nowTime(e);
            }
        });
        // 设置JPanel的布局为绝对布局，它和GridBaglayout的区别在于前者要自己计算大小，后者直接把这份工作丢给程序
        managerPan.setLayout(null); 
        // 显示当前登录用户
        showUseText = new JTextField(10);
        showUseText.setBounds(25, 25, 200, 50);
        showUseText.setFont(new Font("Microsoft Yahei", Font.PLAIN, 13));
        showUseText.setEditable(false); // 不可编辑
        showUseText.setText(thisUser(getLoginUser()));
        showUseText.setHorizontalAlignment(SwingConstants.LEFT);
        showUseText.setOpaque(false);   // 隐藏背景，实现透明化背景
        managerPan.add(showUseText);
        // 显示当前时间
        nowTime = new JMenuItem();
        nowTime.setText("点击显示当前时间");
        nowTime.setBounds(250, 25, 500, 50);
        managerPan.add(nowTime);
        // 显示查询用户
        contentShow = new JTable();
        contentShow.setRowSelectionAllowed(false);  // 不允许选择该行
        contentShow.setColumnSelectionAllowed(false);   // 不允许选择该列
        // 查询显示的滚动面板
        tablePan = new JScrollPane();
        tablePan.setViewportView(contentShow);
        tablePan.setBounds(250, 100, 445, 380);
        managerPan.add(tablePan);
        
        ImageIcon add = new ImageIcon("src/add.jpg");   // 新增用户
        addButton = new JButton(add);
        addButton.setBounds(25, 120, 200, 50);
        addButton.addActionListener(new ActionListener() {    // 点击事件
            @Override
            public void actionPerformed(ActionEvent e) {
                listen.addValue(e, tips);
            }
        });
        managerPan.add(addButton);
        
        ImageIcon remove = new ImageIcon("src/remove.jpg"); // 移除用户
        removeButton = new JButton(remove);
        removeButton.setBounds(25, 220, 200, 50);
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    // 点击事件
                listen.removeValue(e, getLoginUser());
            }
        });
        managerPan.add(removeButton);
        
        ImageIcon update = new ImageIcon("src/update.jpg"); // 更新用户
        updateButton = new JButton(update);
        updateButton.setBounds(25, 320, 200, 50);
        updateButton.addActionListener(new ActionListener() {    // 点击事件
            @Override
            public void actionPerformed(ActionEvent e) {
                listen.updateValue(e, getLoginUser(), content, showUseText);
            }
        });
        managerPan.add(updateButton);
        
        ImageIcon select = new ImageIcon("src/select.jpg"); // 所有用户
        selectButton = new JButton(select);
        selectButton.setBounds(25, 420, 200, 50);
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    // 点击事件
                try {
                    listen.selectValue(e);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        managerPan.add(selectButton);
        ImageIcon icon = new ImageIcon("src/contentPanBackground.jpg");
        background =  new JLabel(icon); //  设置一张图片标签
        // 从窗口的左上角开始铺放，组件的大小由图片的大小决定
        background.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        // JFrame有三层，JRootPane、JLayerPane、JContentPane，这里获得的是第二层
        getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        managerPan.setOpaque(false);  // 设置JPanel为不显示
        setContentPane(managerPan);
        
    }
    
    protected String thisUser(String id) {
        return "当前登录用户:  " + student.get(id).getName();
    }
    
    private class DoListen {
        
      /**
       *  动态获取当前时间，因为要实现一打开窗口就出现时间，所以用了窗口事件和动作事件
       *  先触发窗口事件再触发动作事件
       *  
       */
        private void nowTime(WindowEvent e) {
            // 格式化当前时间
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
            // 设置计时器，执行间隔为1秒
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Date time = new Date(); // 获取当前时间
                    nowTime.setText("当前时间: " + format.format(time));    // 设置文本的内容
                }
            });
            timer.start();  // 计时器开始
        }
        /**
         *  查询用户
         */
        private void selectValue(ActionEvent e) {
            String[] columns = {"学号", "姓名"};
            String temp = null; // 用来保存集合的内容
            String[] temp2 = null;  // 用来接收将集合内容分割后的内容
            // 设置列的名称为学号和姓名，0行
            DefaultTableModel dtm = new DefaultTableModel(columns, 0);
            contentShow.setModel(dtm);  // 设置列表的模型
            Map<String,StudentInformation> selectStudent = student;
            Set<Entry<String, StudentInformation>> entry = selectStudent.entrySet();  // 获取集合的所有键值
            if (entry == null) {
                return;
            }
            // 遍历该集合，将内容显示在滚动面板
            for (Entry<String, StudentInformation> entry2 : entry) {
                temp = entry2.getKey() + ","+ entry2.getValue().getName();
                temp2 = temp.split(",");
                dtm.addRow(temp2);
            }
        }
        // 增加用户
        private void addValue(ActionEvent e, Tips tip) {
            AddUser add = new AddUser(tip);
            add.addUserGo("注册", "姓名：  ", "学号：  ");
            add.setVisible(true);
        }
        // 移除用户
        private void removeValue(ActionEvent e, String loginUser) {
            RemoveUser remove = new RemoveUser();
            remove.removeUser(loginUser);
            remove.setVisible(true);
            
        }
        // 更新用户
        private void updateValue(ActionEvent e, String loginUser, ContentPane content, JTextField showUser) {
            UpdateUser update = new UpdateUser();
            update.updateUser(loginUser, content, showUser);
            update.setVisible(true);
        }
    }
}
