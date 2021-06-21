/*
 * Created by JFormDesigner on Tue Jun 15 09:46:16 CST 2021
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
public class AddUserFrame extends JFrame {
    public AddUserFrame() {
        initComponents();
    }

    /**
     * @param e
     * @Author CodeSleep
     * @Date: 2021-06-15 9:56
     * @Description: //TODO 用户新增按钮监听事件
     */
    private void AddUserButtonActionPerformed(ActionEvent e) {
        // 获取用户名、密码、手机号、地址等个人信息
        String cname = txtcname.getText();
        String cpassword = new String(txtcpassword.getPassword());
        String cphone = txtcphone.getText();
        String cadress = txtcadress.getText();
        // 前端校验
        if (cname.isEmpty()){
            JOptionPane.showMessageDialog(null,"账号不能为空");
            return;
        }
        if (cpassword.isEmpty()){
            JOptionPane.showMessageDialog(null,"密码不能为空");
            return;
        }
        if (cphone.isEmpty()){
            JOptionPane.showMessageDialog(null,"手机号不能为空");
            return;
        }
        if (cadress.isEmpty()){
            JOptionPane.showMessageDialog(null,"地址不能为空");
            return;
        }
        // 普通用户 1
        int level;
        if (radioButtonAdmin.isSelected()){
            // 管理员 1
            level = 1;
        }else {
            level = 0;
        }
        UserDao userDao = new UserDao();
        UserEntity userEntity = new UserEntity(0,cname,cpassword,cphone,cadress,level,null);
        boolean flag = userDao.addUser(userEntity);
        if (flag){
            JOptionPane.showMessageDialog(null,"添加成功");
        }else {
            JOptionPane.showMessageDialog(null,"添加失败");
        }
    }

    /**
     * @param e
     * @Author CodeSleep
     * @Date: 2021-06-15 10:04
     * @Description: //TODO 退出按钮监听事件
     */
    private void ExitButtonActionPerformed(ActionEvent e) {
        // 提示用户是否退出
        int n = JOptionPane.showConfirmDialog(null, "是否退出", "提示信息", JOptionPane.YES_OPTION);
        if (n == 0) {
            // 如果用户选择退出并确定
            dispose();
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        title1 = compFactory.createTitle("text");
        txtcname = new JFormattedTextField();
        titleID = compFactory.createTitle("\u8d26\u53f7\uff1a");
        titlepAssword = compFactory.createTitle("\u5bc6\u7801\uff1a");
        txtcpassword = new JPasswordField();
        AddUserButton = new JButton();
        ExitButton = new JButton();
        titlelogin = compFactory.createTitle("\u589e\u52a0\u7528\u6237");
        titlePhone = compFactory.createTitle("\u624b\u673a\uff1a");
        titleAdress = compFactory.createTitle("\u5730\u5740\uff1a");
        radioButtonUser = new JRadioButton();
        radioButtonAdmin = new JRadioButton();
        txtcphone = new JFormattedTextField();
        txtcadress = new JFormattedTextField();

        //======== this ========
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/com/singerw/ui/img/icoimage.png")).getImage());
        setTitle("\u6dfb\u52a0\u7528\u6237");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(title1);
        title1.setBounds(new Rectangle(new Point(30, -20), title1.getPreferredSize()));
        contentPane.add(txtcname);
        txtcname.setBounds(106, 79, 180, 35);

        //---- titleID ----
        titleID.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        contentPane.add(titleID);
        titleID.setBounds(56, 89, 50, 20);

        //---- titlepAssword ----
        titlepAssword.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        contentPane.add(titlepAssword);
        titlepAssword.setBounds(56, 134, 50, 20);
        contentPane.add(txtcpassword);
        txtcpassword.setBounds(106, 129, 180, 35);

        //---- AddUserButton ----
        AddUserButton.setText("\u65b0 \u589e");
        AddUserButton.setFont(new Font("\u9ed1\u4f53", AddUserButton.getFont().getStyle(), AddUserButton.getFont().getSize() + 3));
        AddUserButton.addActionListener(e -> AddUserButtonActionPerformed(e));
        contentPane.add(AddUserButton);
        AddUserButton.setBounds(95, 325, 175, 35);

        //---- ExitButton ----
        ExitButton.setText("\u9000 \u51fa");
        ExitButton.setFont(new Font("\u9ed1\u4f53", ExitButton.getFont().getStyle(), ExitButton.getFont().getSize() + 3));
        ExitButton.addActionListener(e -> ExitButtonActionPerformed(e));
        contentPane.add(ExitButton);
        ExitButton.setBounds(95, 370, 175, 35);

        //---- titlelogin ----
        titlelogin.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 26));
        contentPane.add(titlelogin);
        titlelogin.setBounds(135, 30, 115, 30);

        //---- titlePhone ----
        titlePhone.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        contentPane.add(titlePhone);
        titlePhone.setBounds(56, 185, 50, 20);

        //---- titleAdress ----
        titleAdress.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        contentPane.add(titleAdress);
        titleAdress.setBounds(56, 235, 50, 20);

        //---- radioButtonUser ----
        radioButtonUser.setText("\u666e\u901a\u7528\u6237");
        radioButtonUser.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        contentPane.add(radioButtonUser);
        radioButtonUser.setBounds(90, 280, 105, 35);

        //---- radioButtonAdmin ----
        radioButtonAdmin.setText("\u7ba1\u7406\u5458");
        radioButtonAdmin.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
        contentPane.add(radioButtonAdmin);
        radioButtonAdmin.setBounds(208, 281, 107, 34);
        contentPane.add(txtcphone);
        txtcphone.setBounds(105, 180, 180, 35);
        contentPane.add(txtcadress);
        txtcadress.setBounds(105, 230, 180, 35);

        contentPane.setPreferredSize(new Dimension(350, 485));
        pack();
        setLocationRelativeTo(null);

        //---- UserAndAdminButtonGroup ----
        ButtonGroup UserAndAdminButtonGroup = new ButtonGroup();
        UserAndAdminButtonGroup.add(radioButtonUser);
        UserAndAdminButtonGroup.add(radioButtonAdmin);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel title1;
    private JFormattedTextField txtcname;
    private JLabel titleID;
    private JLabel titlepAssword;
    private JPasswordField txtcpassword;
    private JButton AddUserButton;
    private JButton ExitButton;
    private JLabel titlelogin;
    private JLabel titlePhone;
    private JLabel titleAdress;
    private JRadioButton radioButtonUser;
    private JRadioButton radioButtonAdmin;
    private JFormattedTextField txtcphone;
    private JFormattedTextField txtcadress;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
