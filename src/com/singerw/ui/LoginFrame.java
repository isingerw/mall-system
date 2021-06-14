/*
 * Created by JFormDesigner on Fri Jun 11 15:31:13 CST 2021
 */

package com.singerw.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.singerw.dao.UserDao;
import com.singerw.entity.UserEntity;


/**
 * @author unknown
 */
public class LoginFrame extends JFrame {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    LoginFrame frame = new LoginFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LoginFrame() {
        initComponents();
    }

    /**
     * @Author CodeSleep
     * @Date: 2021-06-14 21:38
     * @Description: //TODO 登录按钮监听事件
     * @param e
     */
    private void floginButtonActionPerformed(ActionEvent e) {
        // 按钮点击事件
        String cname = txtcname.getText();
        System.out.println(cname);
        String cpwd = txtcpassword.getText();
        System.out.println(cpwd);
        // 要对得到数据进行条件判断
        if (cname.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "用户名不能为空!");
            return;
        }
        if (cpwd.isEmpty()) {
            JOptionPane.showMessageDialog(null, "密码不能为空!");
            return;
        }
        // 调用dao
        UserDao udao = new UserDao();
        // 调用getUserByNameAndPwd方法返回查询数据结果
        UserEntity user = udao.getUserByNameAndPwd(cname, cpwd);
        if (user == null) {
            // 弹窗提示消息
            JOptionPane.showMessageDialog(null, "用户名或者密码错误，登录失败!");
        } else {
            JOptionPane.showMessageDialog(null, "登录成功!");
            // 跳转到下一个窗口 (创建新窗口对象，并显示)
            MainFrame mainframe = new MainFrame();
            mainframe.setVisible(true);
            // 隐藏当前登录界面窗口
            setVisible(false);
        }
    }

    /**
     * @Author CodeSleep
     * @Date: 2021-06-14 21:39
     * @Description: //TODO 注册按钮监听事件
     * @param e
     */
    private void fzhuceButtonActionPerformed(ActionEvent e) {
        // 跳转到下一个窗口 (创建新窗口对象，并显示)
        RegisteredFrame zhuce = new RegisteredFrame();
        zhuce.setVisible(true);
        // 隐藏当前登录界面窗口
        setVisible(false);
    }

    /**
     * @Author CodeSleep
     * @Date: 2021-06-14 21:39
     * @Description: //TODO 退出按钮监听事件
     * @param e
     */
    private void fexitBottonActionPerformed(ActionEvent e) {
        System.exit(0);
    }


    /**
     * @Author CodeSleep
     * @Date: 2021-06-15 0:01
     * @Description: //TODO 用户登录页面UI
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        txtcpassword = new JPasswordField();
        txtcname = new JFormattedTextField();
        titlepassword = compFactory.createTitle("\u5bc6\u7801\uff1a");
        title = compFactory.createTitle("\u8d26\u53f7\uff1a");
        floginButton = new JButton();
        fzhuceButton = new JButton();
        fcheck = new JCheckBox();
        titlelogin = compFactory.createTitle("\u7528 \u6237 \u767b \u5f55");
        fexitBotton = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(140, 370));
        setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
        setResizable(false);
        setTitle("\u7528\u6237\u767b\u5f55");
        setIconImage(new ImageIcon(getClass().getResource("/com/singerw/ui/img/icoimage.png")).getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- txtcpassword ----
        txtcpassword.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
        contentPane.add(txtcpassword);
        txtcpassword.setBounds(95, 145, 190, 35);

        //---- txtcname ----
        txtcname.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 14));
        contentPane.add(txtcname);
        txtcname.setBounds(95, 95, 190, 35);

        //---- titlepassword ----
        titlepassword.setFont(new Font("\u9ed1\u4f53", titlepassword.getFont().getStyle() & ~Font.BOLD, titlepassword.getFont().getSize() + 3));
        contentPane.add(titlepassword);
        titlepassword.setBounds(45, 150, 50, 20);

        //---- title ----
        title.setFont(new Font("\u9ed1\u4f53", title.getFont().getStyle() & ~Font.BOLD, title.getFont().getSize() + 3));
        contentPane.add(title);
        title.setBounds(45, 105, 50, 20);

        //---- floginButton ----
        floginButton.setText("\u767b \u5f55");
        floginButton.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 16));
        floginButton.addActionListener(e -> floginButtonActionPerformed(e));
        contentPane.add(floginButton);
        floginButton.setBounds(85, 240, 195, 35);

        //---- fzhuceButton ----
        fzhuceButton.setText("\u6ce8 \u518c");
        fzhuceButton.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 16));
        fzhuceButton.addActionListener(e -> fzhuceButtonActionPerformed(e));
        contentPane.add(fzhuceButton);
        fzhuceButton.setBounds(85, 285, 195, 35);

        //---- fcheck ----
        fcheck.setText("\u540c\u610f\u7528\u6237\u534f\u8bae\u548c\u9690\u79c1\u4fdd\u62a4\u534f\u8bae");
        fcheck.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 14));
        contentPane.add(fcheck);
        fcheck.setBounds(80, 200, 230, fcheck.getPreferredSize().height);

        //---- titlelogin ----
        titlelogin.setFont(titlelogin.getFont().deriveFont(titlelogin.getFont().getSize() + 8f));
        contentPane.add(titlelogin);
        titlelogin.setBounds(125, 35, 120, 30);

        //---- fexitBotton ----
        fexitBotton.setText("\u9000 \u51fa");
        fexitBotton.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 16));
        fexitBotton.addActionListener(e -> fexitBottonActionPerformed(e));
        contentPane.add(fexitBotton);
        fexitBotton.setBounds(85, 330, 195, 35);

        contentPane.setPreferredSize(new Dimension(350, 485));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPasswordField txtcpassword;
    private JFormattedTextField txtcname;
    private JLabel titlepassword;
    private JLabel title;
    private JButton floginButton;
    private JButton fzhuceButton;
    private JCheckBox fcheck;
    private JLabel titlelogin;
    private JButton fexitBotton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
