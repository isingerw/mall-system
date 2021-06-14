/*
 * Created by JFormDesigner on Mon Jun 14 22:07:40 CST 2021
 */

package com.singerw.ui;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.singerw.dao.UserDao;
import com.singerw.entity.UserEntity;

/**
 * @author unknown
 */
public class RegisteredFrame extends JFrame {
    public RegisteredFrame() {
        initComponents();
    }

    private void RegisteredButtonActionPerformed(ActionEvent e) {
        // 按钮点击事件
        String cname = txtcname.getText();
        System.out.println(cname);
        String cpwd = txtcpassword.getText();
        System.out.println(cpwd);
        String cphone = txtcphone.getText();
        System.out.println(cphone);
        String cadress = txtcadress.getText();
        System.out.println(cadress);
//        // 要对得到数据进行条件判断
//        if (cname.trim().isEmpty()) {
//            JOptionPane.showMessageDialog(null, "用户名不能为空!");
//            return;
//        }
//        if (cpwd.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "密码不能为空!");
//            return;
//        }
//        if (cphone.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "手机号不能为空!");
//            return;
//        }
//        if (cadress.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "地址不能为空!");
//            return;
//        }
////        // 调用dao
////        UserDao udao = new UserDao();
////        // 调用getUserByNameAndPwd方法返回查询数据结果
////        UserEntity user = udao.zhuceUser(cname,cpwd,cphone,cadress);
////        if (user == null) {
////            // 弹窗提示消息
////            JOptionPane.showMessageDialog(null, "用户名或者密码错误，登录失败!");
////        } else {
////            JOptionPane.showMessageDialog(null, "登录成功!");
////        }
    }

    private void backHomeButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
        // 跳转到下一个窗口 (创建新窗口对象，并显示)
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
        // 隐藏当前登录界面窗口
        setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        title1 = compFactory.createTitle("text");
        txtcname = new JFormattedTextField();
        title = compFactory.createTitle("\u8d26\u53f7\uff1a");
        titlepassword = compFactory.createTitle("\u5bc6\u7801\uff1a");
        txtcpassword = new JPasswordField();
        RegisteredButton = new JButton();
        backHomeButton = new JButton();
        titlelogin = compFactory.createTitle("\u7528 \u6237 \u6ce8 \u518c");
        txtcphone = new JPasswordField();
        titlepassword2 = compFactory.createTitle("\u624b\u673a\uff1a");
        titlepassword3 = compFactory.createTitle("\u5730\u5740\uff1a");
        txtcadress = new JPasswordField();

        //======== this ========
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/com/singerw/ui/img/icoimage.png")).getImage());
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(title1);
        title1.setBounds(new Rectangle(new Point(30, -20), title1.getPreferredSize()));
        contentPane.add(txtcname);
        txtcname.setBounds(81, 79, 180, 35);

        //---- title ----
        title.setFont(title.getFont().deriveFont(title.getFont().getSize() + 3f));
        contentPane.add(title);
        title.setBounds(31, 89, 50, 20);

        //---- titlepassword ----
        titlepassword.setFont(titlepassword.getFont().deriveFont(titlepassword.getFont().getSize() + 3f));
        contentPane.add(titlepassword);
        titlepassword.setBounds(31, 134, 50, 20);
        contentPane.add(txtcpassword);
        txtcpassword.setBounds(81, 129, 180, 35);

        //---- RegisteredButton ----
        RegisteredButton.setText("\u6ce8 \u518c");
        RegisteredButton.setFont(new Font("\u9ed1\u4f53", RegisteredButton.getFont().getStyle(), 16));
        RegisteredButton.addActionListener(e -> RegisteredButtonActionPerformed(e));
        contentPane.add(RegisteredButton);
        RegisteredButton.setBounds(70, 300, 175, 35);

        //---- backHomeButton ----
        backHomeButton.setText("\u8fd4 \u56de \u767b \u5f55");
        backHomeButton.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 16));
        backHomeButton.addActionListener(e -> backHomeButtonActionPerformed(e));
        contentPane.add(backHomeButton);
        backHomeButton.setBounds(70, 350, 175, 35);

        //---- titlelogin ----
        titlelogin.setFont(titlelogin.getFont().deriveFont(titlelogin.getFont().getSize() + 8f));
        contentPane.add(titlelogin);
        titlelogin.setBounds(115, 30, 115, 30);
        contentPane.add(txtcphone);
        txtcphone.setBounds(81, 180, 180, 35);

        //---- titlepassword2 ----
        titlepassword2.setFont(titlepassword2.getFont().deriveFont(titlepassword2.getFont().getSize() + 3f));
        contentPane.add(titlepassword2);
        titlepassword2.setBounds(31, 185, 50, 20);

        //---- titlepassword3 ----
        titlepassword3.setFont(titlepassword3.getFont().deriveFont(titlepassword3.getFont().getSize() + 3f));
        contentPane.add(titlepassword3);
        titlepassword3.setBounds(31, 235, 50, 20);
        contentPane.add(txtcadress);
        txtcadress.setBounds(81, 230, 180, 35);

        contentPane.setPreferredSize(new Dimension(325, 475));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel title1;
    private JFormattedTextField txtcname;
    private JLabel title;
    private JLabel titlepassword;
    private JPasswordField txtcpassword;
    private JButton RegisteredButton;
    private JButton backHomeButton;
    private JLabel titlelogin;
    private JPasswordField txtcphone;
    private JLabel titlepassword2;
    private JLabel titlepassword3;
    private JPasswordField txtcadress;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
