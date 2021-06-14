/*
 * Created by JFormDesigner on Fri Jun 11 15:31:13 CST 2021
 */

package com.singerw.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        txtcpassword = new JPasswordField();
        txtcname = new JFormattedTextField();
        titlepassword = compFactory.createTitle("\u5bc6\u7801\uff1a");
        title = compFactory.createTitle("\u8d26\u53f7\uff1a");
        flogin = new JButton();
        fzhuce = new JButton();
        fcheck = new JCheckBox();
        titlelogin = compFactory.createTitle("\u7528 \u6237 \u767b \u5f55");
        fexit = new JButton();
        // 设置绝对布局
        setLayout(null);
        // 居中显示
        setLocationRelativeTo(null);
        // 窗口关闭 应用程序本身行为
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //======== this ========
        setMinimumSize(new Dimension(140, 370));
        setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
        setResizable(false);
        setTitle("\u7528\u6237\u767b\u5f55");
        setIconImage(new ImageIcon(getClass().getResource("/com/singerw/ui/img/icoimage.png")).getImage());
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(txtcpassword);
        txtcpassword.setBounds(95, 145, 190, 35);
        contentPane.add(txtcname);
        txtcname.setBounds(95, 95, 190, 35);

        //---- titlepassword ----
        titlepassword.setFont(titlepassword.getFont().deriveFont(titlepassword.getFont().getSize() + 3f));
        contentPane.add(titlepassword);
        titlepassword.setBounds(45, 150, 50, 20);

        //---- title ----
        title.setFont(title.getFont().deriveFont(title.getFont().getSize() + 3f));
        contentPane.add(title);
        title.setBounds(45, 105, 50, 20);

        //---- flogin ----
        flogin.setText("\u767b \u5f55");
        contentPane.add(flogin);
        flogin.setBounds(85, 240, 195, 35);
        flogin.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed (ActionEvent e){

            //按钮点击事件
            String cname = txtcname.getText();
            System.out.println(cname);

            String cpwd = txtcpassword.getText();
            System.out.println(cpwd);

            //要对得到数据进行条件判断
            if (cname.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "用户名不能为空!");
                return;
            }
            if (cpwd.isEmpty()) {
                JOptionPane.showMessageDialog(null, "密码不能为空!");
                return;
            }

            //调用dao
            UserDao udao = new UserDao();

            //调用getUserByNameAndPwd方法返回查询数据结果
            UserEntity user = udao.getUserByNameAndPwd(cname, cpwd);

            if (user == null) {
                //弹窗提示消息
                JOptionPane.showMessageDialog(null, "用户名或者密码错误，登录失败!");
            } else {
                JOptionPane.showMessageDialog(null, "登录成功!");
                //跳转到下一个窗口 (创建新窗口对象，并显示)
                MainFrame mainframe = new MainFrame();
                mainframe.setVisible(true);
                //隐藏当前窗口
                setVisible(false);
            }
        }
    });

    //---- fzhuce ----
        fzhuce.setText("\u6ce8 \u518c");
        contentPane.add(fzhuce);
        fzhuce.setBounds(85,285,195,35);

    //---- fcheck ----
        fcheck.setText("\u540c\u610f\u7528\u6237\u534f\u8bae\u548c\u9690\u79c1\u4fdd\u62a4\u534f\u8bae");
        contentPane.add(fcheck);
        fcheck.setBounds(new

    Rectangle(new Point(75, 200),fcheck.

    getPreferredSize()));

    //---- titlelogin ----
        titlelogin.setFont(titlelogin.getFont().

    deriveFont(titlelogin.getFont().

    getSize() +6f));
        contentPane.add(titlelogin);
        titlelogin.setBounds(125,35,120,30);

    //---- fexit ----
        fexit.setText("\u9000 \u51fa");
        contentPane.add(fexit);
        fexit.setBounds(85,330,195,35);

        contentPane.setPreferredSize(new

    Dimension(350,485));

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
    private JButton flogin;
    private JButton fzhuce;
    private JCheckBox fcheck;
    private JLabel titlelogin;
    private JButton fexit;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
