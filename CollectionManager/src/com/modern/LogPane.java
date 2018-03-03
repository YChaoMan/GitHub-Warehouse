package com.modern;

/**
 * ��ͼ�����û�����JDBC����MySQL������������ü��Ͻ��й���ʵ��
 * ��ǰ�汾2.0
 * ʵ��Ŀ�ģ�ʹ�ü���ȡ��JDBC�������ݿ�
 * ���ʱ�䣺2017-11-17 15:22:58
 * ����ʵ��ʹ�õ��Ǽ��ϵ�Map�࣬����С����ȱ�����������̫ǿ��������Ĳ���
 * �޸����ݣ���չע��
 * �޸�ʱ�䣺2017-12-27 21:55:07
 * ------------------------
 * Ĭ���û�        |     �ϳ���         |
 * ѧ��               |  1601134146 |
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

    // ����UI��ƣ���õ�ǰϵͳ���
    private static final String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
    /**
     * ��������������
     */
    private JPanel content;
    private JLabel label, label_2, authCodeTips, background;
    private JButton log, cancel, register;
    private JTextField name, id, authCodeInput;
    private String save;
    private int count = 1;
    /**
     * ���ü���ʹ��protected�����ñ����������඼��ʹ�ã���ΪMap��ʹ�ü�ֵ�Ե���ʽ�洢���ݣ���Ϊ���ϵ�ǰ���ݴ洢��ʽ
     */
    protected static Map<String, StudentInformation> student = null;
    private Tips tips = Tips.getInterface();
    
    /**
     * ���ʵ��������
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
     * ���ù��췽����ʼ��
     */
    public LogPane() {
        student = new HashMap<String, StudentInformation>();
        // �����û�
        String getTextId = "1601134146";
        String getTextName = "�ϳ���";
        // ʵ����һ�����������ϳ�����ѧ��1601134146
        StudentInformation newStudent = new StudentInformation(getTextId, getTextName);
        student.put(getTextId, newStudent); // ���浽������ȥ
    }

    /**
     * ��½���棬�����������룬������ť�¼� 1���������� 2��ѧ������ 3����֤������
     * 
     */
    private void contentGo() {
        setTitle("�����¼");   // ����JFrame����Ĵ�С������Ļ�е���ʾλ�á����ɸı��С���رյķ�ʽ
        setBounds(620, 220, 366, 412);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // �����ڲ�������¼���
        EventHand listen = new EventHand();
        // ʵ��������õ��࣬��ʹ�ø���������λ�ý�������
        ComponentAttributes componentSet = ComponentAttributes.getInterface();
        // ע����ʵ����
        RegisterWindow registerListen = new RegisterWindow();
        Font font = new Font("Microsofr-Yahei", Font.PLAIN, 14);
        content = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        content.setLayout(gbl);
        /*
         * JFrame������ ��һ��JRootPane���ڶ��㣺JlayerPane�����ϲ�ContentPane
         * ����һ����ÿؼ��������ϲ�Ҳ����ContentPane���������ǿ��԰ѱ���ͼƬ�����ڵڶ���(JLaberPane) Ȼ������ϲ�͸����
         */
        ImageIcon icon = new ImageIcon("src/LogPanebackground.jpg");
        background = new JLabel(icon);
        background.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        content.setOpaque(false);   // ��JPanel�����ϲ�����͸��

        /*
         * ���������˱�ǩ�����塢 ��x���ˮƽ���롢��JPanel�е���ʾλ��
         */
        label = new JLabel("������");
        label.setFont(font);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        gbl.setConstraints(label, componentSet.componentSet(0, 0, 1, 1));
        content.add(label);
        /*
         * ����һ�����������ĵ����ı����������ı������������JPanel�е�λ�á������������뿪�¼�
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
        // ѧ�ű�ǩ
        label_2 = new JLabel("ѧ�ţ�");
        label_2.setFont(font);
        label_2.setHorizontalAlignment(SwingConstants.RIGHT);
        gbl.setConstraints(label_2, componentSet.componentSet(0, 1, 1, 1));
        content.add(label_2);
        // ����ѧ�ŵĵ����ı���
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
        // ��֤���ǩ
        authCodeTips = new JLabel("��֤�룺");
        authCodeTips.setFont(font);
        authCodeTips.setHorizontalAlignment(SwingConstants.RIGHT);
        gbl.setConstraints(authCodeTips, componentSet.componentSet(0, 2, 1, 1));
        content.add(authCodeTips);

        // ������֤��ĵ����ı���
        authCodeInput = new JTextField(10);
        gbl.setConstraints(authCodeInput, componentSet.componentSet(1, 2, 2, 1));
        content.add(authCodeInput);
        /*
         * ��֤����ʾ�����������,ͨ���޸Ĵ���ĵĴ�С���ﵽˢ�µ�Ч���� �������õ�ˢ�£���ô�������»�ͼ��Ҳ���Ǵﵽ�˸��µ�Ŀ��
         */
        Captcha code = new Captcha();
        gbl.setConstraints(code, componentSet.componentSet(3, 2, 2, 1));
        content.add(code);
        
        // ע���¼�����ӵ��Ƕ����¼�
        register = new JButton("ע��");
        register.setFont(font);
        gbl.setConstraints(register, componentSet.componentSet(0, 3, 1, 1));
        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerListen.addUserGo("ע��", "������  ", "ѧ�ţ�  ", tips);
            }
        });
        content.add(register);

        // ��¼��ť����ӵ��Ƕ����¼�
        log = new JButton("��¼");
        log.setFont(font);
        gbl.setConstraints(log, componentSet.componentSet(1, 3, 1, 1));
        log.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listen.logGo(e);
            }
        });
        content.add(log);
        
        // ���ð�ť����Ӷ����¼�
        cancel = new JButton("����");
        cancel.setFont(font);
        gbl.setConstraints(cancel, componentSet.componentSet(2, 3, 1, 1));
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listen.back(e);
            }
        });
        content.add(cancel);

        /**
         * ������֤�룬ͨ�������޸Ĵ����С
         */
        JButton update = new JButton("����");
        gbl.setConstraints(update, componentSet.componentSet(3, 3, 2, 1));
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count(count);
//                System.out.println(count);
            }
        });
        content.add(update);
        
        // ��JPanel��ӵ�JFrame��
        setContentPane(content);
    }

    /**
     * ���ǻ���һ����֤�룬��дpaint(Graphics g)���������ﵽ������л�����֤���Ч��
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
             *  ����ʮ�������λ�ã�����drawLine(int x1, int y1. int x2, int y2),
             *  drawLine(��ʼ��x�ᣬ��ʼ��y�ᣬ������x�ᣬ������y��)
             */
            for (int i = 0; i < 10; i++) {  
                g.drawLine(1, 1 + 3 * rd.nextInt(13), 65, 1 + 5 * rd.nextInt(13));
            }
            for (int i = 0; i < 4; i++) {   // ����ĸ��ַ���ÿ�����һ���������ȡ��������ɫ
                tempC = c[rd.nextInt(c.length)] + "";
                g.setColor(new Color(rd.nextInt(155), rd.nextInt(155), rd.nextInt(250)));
                g.drawString(tempC, i * 13 + 3, 17);
                sb.append(tempC);   // ׷�ӵ�StringBuilder�У�Ϊ����֤ʹ��
            }
            save = sb.toString();   // ������֤����ַ�
            sb.delete(0, sb.length());  // ��������StringBuilder������
            // System.out.println(save);
            g.dispose();    // ���йر�
        }
    }

    /**
     * 2017-11-17 15:59:29 ����һ���ڲ��࣬��Ҫ���þ������¼�������Ҫ���������¼�������¼��������¼�
     */
    private class EventHand {
        // ע���¼�
        private void logGo(ActionEvent e) {
            String pass = id.getText(); // ��ȡ�û������ѧ��
            String user = name.getText();   // ��ȡ�û����������
            String authcode = authCodeInput.getText();  // ��ȡ�û��������֤��
            JudgeExists judge = JudgeExists.getInterface();
            boolean judgeTrue = judge.judge(id, name, user, pass, tips);
            if (!judgeTrue) {
                return;
            }
            // ����֤����Сд�������û�����Ľ���ƥ��
            if (!(save.toLowerCase().equals(authcode.toLowerCase()))) {
                JOptionPane.showMessageDialog(null, "��֤ʧ��");
                count(count);
                authCodeInput.setText("");
                return;
            }
            
            boolean existsId = student.containsKey(pass);   // �ڼ������ж��Ƿ���ڸü�
            // �Ȳ��Ҽ�����Ϊ������Ψһ��
            if (existsId && judgeTrue) { // ������ڸ�ѧ�ţ�������Ҳѧ��Ϊ��
                // �ٲ���ֵ
                String get = student.get(pass).getName();
                if (get.equals(user)) {
                    ContentPane contentPane = new ContentPane();    // ����֤�롢ѧ�š���������ȷ���½������
                    contentPane.setLoginUser(pass);
                    contentPane.userManager(tips, contentPane);
                    
                    setVisible(false);  // �����������Ե�ǰ���ڽ�������
                } else {    // ����û���������ʾ
                    JOptionPane.showMessageDialog(null, "�û�������");
                    count(count);   //  ������֤��
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(null, "�û���/ѧ�Ŵ���");
                count(count);
                return;
            }
        }

        // ���ð�ť
        private void back(ActionEvent e) {  // ȫ���������
            name.setText(tips.getName());
            id.setText(tips.getId());
            authCodeInput.setText("");
        }

    }

    /*
     * �޸Ĵ����С
     */
    private void count(int count) { // �ô����С�ı仯���ȿ�����1px����
        if (count == 1) {
            setSize(366 - count, 412);
            this.count--;
        } else {
            setSize(366, 412);
            this.count++;
        }
    }
}
