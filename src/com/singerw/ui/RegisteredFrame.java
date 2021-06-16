/*
 * Created by JFormDesigner on Mon Jun 14 22:07:40 CST 2021
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
public class RegisteredFrame extends JFrame {

    public RegisteredFrame() {
        initComponents();
    }

    /**
     * @param e
     * @Author CodeSleep
     * @Date: 2021-06-14 23:59
     * @Description: //TODO �û�ע�ᰴť�����¼�
     */
    private void RegisteredButtonActionPerformed(ActionEvent e) {
        // ��ť����¼�
        String cname = txtcname.getText();
        System.out.println(cname);
        String cpwd = new String(txtcpassword.getPassword());
        System.out.println(cpwd);
        String cphone = txtcphone.getText();
        System.out.println(cphone);
        String caddress = txtcadress.getText();
        System.out.println(caddress);
        // Ҫ�Եõ����ݽ��������ж�
        if (cname.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "�û�������Ϊ��!");
            return;
        }
        if (cpwd.isEmpty()) {
            JOptionPane.showMessageDialog(null, "���벻��Ϊ��!");
            return;
        }
        if (cphone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "�ֻ��Ų���Ϊ��!");
            return;
        }
        // ����dao
        UserDao udao = new UserDao();
        // ����getUserByNameAndPwd�������ز�ѯ���ݽ��
        UserEntity userEntity = new UserEntity(cname, cpwd, cphone, caddress);
        boolean flag = udao.addUser(userEntity);
        if (flag) {
            // ������ʾ��Ϣ
            JOptionPane.showMessageDialog(null, "��ϲ�㣬ע��ɹ�");
        } else {
            JOptionPane.showMessageDialog(null, "ע��ʧ��");
        }
    }

    /**
     * @param e
     * @Author CodeSleep
     * @Date: 2021-06-15 0:00
     * @Description: //TODO ���ص�¼��ť�����¼�
     */
    private void backHomeButtonActionPerformed(ActionEvent e) {
        // ��ת����һ������ (�����´��ڶ��󣬲���ʾ)
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
        // ���ص�ǰ��¼���洰��
        setVisible(false);
    }

    /**
     * @Author CodeSleep
     * @Date: 2021-06-15 0:00
     * @Description: //TODO ע��ҳ��UI
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        title1 = compFactory.createTitle("text");
        txtcname = new JFormattedTextField();
        titleID = compFactory.createTitle("\u8d26\u53f7\uff1a");
        titlepAssword = compFactory.createTitle("\u5bc6\u7801\uff1a");
        txtcpassword = new JPasswordField();
        RegisteredButton = new JButton();
        backHomeButton = new JButton();
        titlelogin = compFactory.createTitle("\u7528 \u6237 \u6ce8 \u518c");
        titlePhone = compFactory.createTitle("\u624b\u673a\uff1a");
        titleAdress = compFactory.createTitle("\u5730\u5740\uff1a");
        txtcphone = new JFormattedTextField();
        txtcadress = new JFormattedTextField();

        //======== this ========
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/com/singerw/ui/img/icoimage.png")).getImage());
        setTitle("\u7528\u6237\u6ce8\u518c");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(title1);
        title1.setBounds(new Rectangle(new Point(30, -20), title1.getPreferredSize()));
        contentPane.add(txtcname);
        txtcname.setBounds(106, 79, 180, 35);

        //---- titleID ----
        titleID.setFont(titleID.getFont().deriveFont(titleID.getFont().getSize() + 3f));
        contentPane.add(titleID);
        titleID.setBounds(56, 89, 50, 20);

        //---- titlepAssword ----
        titlepAssword.setFont(titlepAssword.getFont().deriveFont(titlepAssword.getFont().getSize() + 3f));
        contentPane.add(titlepAssword);
        titlepAssword.setBounds(56, 134, 50, 20);
        contentPane.add(txtcpassword);
        txtcpassword.setBounds(106, 129, 180, 35);

        //---- RegisteredButton ----
        RegisteredButton.setText("\u6ce8 \u518c");
        RegisteredButton.setFont(new Font("\u9ed1\u4f53", RegisteredButton.getFont().getStyle(), 16));
        RegisteredButton.addActionListener(e -> RegisteredButtonActionPerformed(e));
        contentPane.add(RegisteredButton);
        RegisteredButton.setBounds(95, 300, 175, 35);

        //---- backHomeButton ----
        backHomeButton.setText("\u8fd4 \u56de \u767b \u5f55");
        backHomeButton.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 16));
        backHomeButton.addActionListener(e -> backHomeButtonActionPerformed(e));
        contentPane.add(backHomeButton);
        backHomeButton.setBounds(95, 350, 175, 35);

        //---- titlelogin ----
        titlelogin.setFont(titlelogin.getFont().deriveFont(titlelogin.getFont().getSize() + 8f));
        contentPane.add(titlelogin);
        titlelogin.setBounds(127, 30, 115, 30);

        //---- titlePhone ----
        titlePhone.setFont(titlePhone.getFont().deriveFont(titlePhone.getFont().getSize() + 3f));
        contentPane.add(titlePhone);
        titlePhone.setBounds(56, 185, 50, 20);

        //---- titleAdress ----
        titleAdress.setFont(titleAdress.getFont().deriveFont(titleAdress.getFont().getSize() + 3f));
        contentPane.add(titleAdress);
        titleAdress.setBounds(56, 235, 50, 20);
        contentPane.add(txtcphone);
        txtcphone.setBounds(105, 180, 180, 35);
        contentPane.add(txtcadress);
        txtcadress.setBounds(105, 230, 180, 35);

        contentPane.setPreferredSize(new Dimension(350, 485));
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel title1;
    private JFormattedTextField txtcname;
    private JLabel titleID;
    private JLabel titlepAssword;
    private JPasswordField txtcpassword;
    private JButton RegisteredButton;
    private JButton backHomeButton;
    private JLabel titlelogin;
    private JLabel titlePhone;
    private JLabel titleAdress;
    private JFormattedTextField txtcphone;
    private JFormattedTextField txtcadress;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
