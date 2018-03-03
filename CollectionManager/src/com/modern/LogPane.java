package com.modern;

/**
 * 视图管理用户，从JDBC连接MySQL脱离出来，利用集合进行功能实现
 * 当前版本2.0
 * 实现目的，使用集合取代JDBC连接数据库
 * 完成时间：2017-11-17 15:22:58
 * 功能实现使用的是集合的Map类，但该小程序缺点在于耦合性太强，需求更改不易
 * 修改内容：扩展注释
 * 修改时间：2017-12-27 21:55:07
 * ------------------------
 * 默认用户        |     严朝满         |
 * 学号               |  1601134146 |
 * ````````````````````````
 * @author Administrator
 *
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.tips.JudgeExists;
import com.tips.Tips;

public class LogPane extends JFrame {

    // 更改UI设计，获得当前系统外观
    private static final String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
    /**
     * 定义该类所用组件
     */
    private JPanel content;
    private JLabel label, label_2, authCodeTips, background;
    private JButton log, cancel, register;
    private JTextField name, id, authCodeInput;
    private String save;
    private int count = 1;
    /**
     * 将该集合使用protected这样该报和它的子类都能使用，因为Map是使用键值对的形式存储数据，较为符合当前数据存储方式
     */
    protected static Map<String, StudentInformation> student = null;
    private Tips tips = Tips.getInterface();
    
    /**
     * 软件实例，启动
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(lookAndFeel);
        LogPane frame = new LogPane();
        frame.contentGo();
        frame.setVisible(true);

    }

    /**
     * 利用构造方法初始化
     */
    public LogPane() {
        student = new HashMap<String, StudentInformation>();
        // 测试用户
        String getTextId = "1601134146";
        String getTextName = "严朝满";
        // 实例出一个对象，姓名严朝满，学号1601134146
        StudentInformation newStudent = new StudentInformation(getTextId, getTextName);
        student.put(getTextId, newStudent); // 保存到集合中去
    }

    /**
     * 登陆界面，包括三个输入，三个按钮事件 1、姓名输入 2、学号输入 3、验证码输入
     * 
     */
    private void contentGo() {
        setTitle("管理登录");   // 设置JFrame窗体的大小、在屏幕中的显示位置、不可改变大小及关闭的方式
        setBounds(620, 220, 366, 412);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 匿名内部类操作事件，
        EventHand listen = new EventHand();
        // 实例组件设置的类，可使用该类对组件的位置进行设置
        ComponentAttributes componentSet = ComponentAttributes.getInterface();
        // 注册类实例化
        RegisterWindow registerListen = new RegisterWindow();
        Font font = new Font("Microsofr-Yahei", Font.PLAIN, 14);
        content = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        content.setLayout(gbl);
        /*
         * JFrame有三层 第一层JRootPane；第二层：JlayerPane；最上层ContentPane
         * 我们一般放置控件的是最上层也就是ContentPane，所以我们可以把背景图片放置在第二层(JLaberPane) 然后把最上层透明化
         */
        ImageIcon icon = new ImageIcon("src/LogPanebackground.jpg");
        background = new JLabel(icon);
        background.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        content.setOpaque(false);   // 将JPanel的最上层设置透明

        /*
         * 这里设置了标签的字体、 沿x轴的水平对齐、在JPanel中的显示位置
         */
        label = new JLabel("姓名：");
        label.setFont(font);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        gbl.setConstraints(label, componentSet.componentSet(0, 0, 1, 1));
        content.add(label);
        /*
         * 这是一个接收姓名的单行文本域，设置了文本域的列数，在JPanel中的位置、添加鼠标点击、离开事件
         */
        name = new JTextField(10);
        gbl.setConstraints(name, componentSet.componentSet(1, 0, 4, 1));
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
        content.add(name);
        // 学号标签
        label_2 = new JLabel("学号：");
        label_2.setFont(font);
        label_2.setHorizontalAlignment(SwingConstants.RIGHT);
        gbl.setConstraints(label_2, componentSet.componentSet(0, 1, 1, 1));
        content.add(label_2);
        // 接收学号的单行文本域
        id = new JTextField(10);
        gbl.setConstraints(id, componentSet.componentSet(1, 1, 4, 1));
        id.setText(tips.getId());
        id.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tips.onclickText2(e, id);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                tips.exitText2(e, id);
            }
        });
        content.add(id);
        // 验证码标签
        authCodeTips = new JLabel("验证码：");
        authCodeTips.setFont(font);
        authCodeTips.setHorizontalAlignment(SwingConstants.RIGHT);
        gbl.setConstraints(authCodeTips, componentSet.componentSet(0, 2, 1, 1));
        content.add(authCodeTips);

        // 接收验证码的单行文本框
        authCodeInput = new JTextField(10);
        gbl.setConstraints(authCodeInput, componentSet.componentSet(1, 2, 2, 1));
        content.add(authCodeInput);
        /*
         * 验证码显示，并随机更新,通过修改窗体的的大小，达到刷新的效果， 如果窗体得到刷新，那么将会重新画图，也就是达到了更新的目的
         */
        Captcha code = new Captcha();
        gbl.setConstraints(code, componentSet.componentSet(3, 2, 2, 1));
        content.add(code);
        
        // 注册事件，添加的是动作事件
        register = new JButton("注册");
        register.setFont(font);
        gbl.setConstraints(register, componentSet.componentSet(0, 3, 1, 1));
        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerListen.addUserGo("注册", "姓名：  ", "学号：  ", tips);
            }
        });
        content.add(register);

        // 登录按钮，添加的是动作事件
        log = new JButton("登录");
        log.setFont(font);
        gbl.setConstraints(log, componentSet.componentSet(1, 3, 1, 1));
        log.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listen.logGo(e);
            }
        });
        content.add(log);
        
        // 重置按钮，添加动作事件
        cancel = new JButton("重置");
        cancel.setFont(font);
        gbl.setConstraints(cancel, componentSet.componentSet(2, 3, 1, 1));
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listen.back(e);
            }
        });
        content.add(cancel);

        /**
         * 更新验证码，通过增加修改窗体大小
         */
        JButton update = new JButton("更新");
        gbl.setConstraints(update, componentSet.componentSet(3, 3, 2, 1));
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count(count);
//                System.out.println(count);
            }
        });
        content.add(update);
        
        // 将JPanel添加到JFrame中
        setContentPane(content);
    }

    /**
     * 这是画了一个验证码，重写paint(Graphics g)方法。来达到在面板中画出验证码的效果
     * @author Administrator
     *
     */
    private class Captcha extends JPanel {

        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        @Override
        public void paint(Graphics g) {
            Random rd = new Random();
            char[] c = "CWq2Pa8s345dfMhjkZxcTYUvbnNmMHF267ER89".toCharArray();
            String tempC;
            StringBuilder sb = new StringBuilder();
            g.setFont(new Font("Microsoft Yahei", Font.BOLD, 23));
            g.setColor(Color.gray);
            /*
             *  画出十条线随机位置，画线drawLine(int x1, int y1. int x2, int y2),
             *  drawLine(开始的x轴，开始的y轴，结束的x轴，结束的y轴)
             */
            for (int i = 0; i < 10; i++) {  
                g.drawLine(1, 1 + 3 * rd.nextInt(13), 65, 1 + 5 * rd.nextInt(13));
            }
            for (int i = 0; i < 4; i++) {   // 输出四个字符，每次输出一个就随机获取，包括颜色
                tempC = c[rd.nextInt(c.length)] + "";
                g.setColor(new Color(rd.nextInt(155), rd.nextInt(155), rd.nextInt(250)));
                g.drawString(tempC, i * 13 + 3, 17);
                sb.append(tempC);   // 追加到StringBuilder中，为了验证使用
            }
            save = sb.toString();   // 保存验证码的字符
            sb.delete(0, sb.length());  // 画完后清除StringBuilder的内容
            // System.out.println(save);
            g.dispose();    // 进行关闭
        }
    }

    /**
     * 2017-11-17 15:59:29 创建一个内部类，主要作用就是做事件处理，主要包括动作事件、鼠标事件及窗口事件
     */
    private class EventHand {
        // 注册事件
        private void logGo(ActionEvent e) {
            String pass = id.getText(); // 获取用户输入的学号
            String user = name.getText();   // 获取用户输入的姓名
            String authcode = authCodeInput.getText();  // 获取用户输入的验证码
            JudgeExists judge = JudgeExists.getInterface();
            boolean judgeTrue = judge.judge(id, name, user, pass, tips);
            if (!judgeTrue) {
                return;
            }
            // 将验证码变成小写，并和用户输入的进行匹配
            if (!(save.toLowerCase().equals(authcode.toLowerCase()))) {
                JOptionPane.showMessageDialog(null, "验证失败");
                count(count);
                authCodeInput.setText("");
                return;
            }
            
            boolean existsId = student.containsKey(pass);   // 在集合中判断是否存在该键
            // 先查找键，因为键具有唯一性
            if (existsId && judgeTrue) { // 如果存在该学号，这里我也学号为键
                // 再查找值
                String get = student.get(pass).getName();
                if (get.equals(user)) {
                    ContentPane contentPane = new ContentPane();    // 当验证码、学号、姓名都正确后登陆主界面
                    contentPane.setLoginUser(pass);
                    contentPane.userManager(tips, contentPane);
                    
                    setVisible(false);  // 弹出主界面后对当前窗口进行隐藏
                } else {    // 如果用户名错误，提示
                    JOptionPane.showMessageDialog(null, "用户名错误");
                    count(count);   //  更新验证码
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(null, "用户名/学号错误");
                count(count);
                return;
            }
        }

        // 重置按钮
        private void back(ActionEvent e) {  // 全部进行清空
            name.setText(tips.getName());
            id.setText(tips.getId());
            authCodeInput.setText("");
        }

    }

    /*
     * 修改窗体大小
     */
    private void count(int count) { // 让窗体大小的变化幅度控制在1px左右
        if (count == 1) {
            setSize(366 - count, 412);
            this.count--;
        } else {
            setSize(366, 412);
            this.count++;
        }
    }
}
