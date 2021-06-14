/*
 * Created by JFormDesigner on Fri Jun 11 15:31:26 CST 2021
 */

package com.singerw.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.singerw.dao.GoodsDao;
import com.singerw.entity.GoodsEntity;
import java.util.List;
import java.awt.event.ActionEvent;


/**
 * @author unknown
 */
public class MainFrame extends JFrame {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public MainFrame() {
        initComponents();
    }

    /**
     * @Author CodeSleep
     * @Date: 2021-06-14 22:29
     * @Description: //TODO 搜索按钮监听事件
     * @param e
     */
    private void fssSouSuoActionPerformed(ActionEvent e) {
        // 获取用户输入的关键字
        String keywords = txtKeywords.getText();
        GoodsDao gdao = new GoodsDao();
        // 先查
        List<GoodsEntity> list = gdao.getGoodsByLike("%" + keywords + "%");
        // 记录条数
        int size = list.size();
        // 创建保存数据的二维数组
        Object obj[][] = new Object[size][5];
        // 循环
        for (int i = 0; i < size; i++) {
            GoodsEntity g = list.get(i);
            obj[i][0] = g.getGid();
            obj[i][1] = g.getGname();
            obj[i][2] = g.getGprice();
            obj[i][3] = g.getGstock();
            obj[i][4] = g.getGstate() == 1 ? "上架" : "下架";
        }
        // 设置setModel => new Object[][] =>要展示的数据 new String[]:列名
        tableGoods.setModel(new DefaultTableModel(
                obj,
                new String[] { "\u7F16\u53F7", "\u5546\u54C1\u540D\u79F0", "\u5546\u54C1\u4EF7\u683C",
                        "\u5E93\u5B58", "\u72B6\u6001" }));
        // setViewportView 设置table和scrollpane关联
        scrollPaneGoods.setViewportView(tableGoods);
    }

    /**
     * @Author CodeSleep
     * @Date: 2021-06-14 23:52
     * @Description: //TODO 菜单===》退出系统
     * @param e
     */
    private void menuItemExit(ActionEvent e) {
        System.exit(0);
    }

    /**
     * @Author CodeSleep
     * @Date: 2021-06-14 23:55
     * @Description: //TODO 菜单===》返回上一级
     * @param e
     */
    private void menuItemGoBack(ActionEvent e) {
        // TODO add your code here
    }

    /**
     * @Author CodeSleep
     * @Date: 2021-06-14 23:40
     * @Description: //TODO 搜索界面UI界面
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItemExit = new JMenuItem();
        fssSouSuo = new JButton();
        label1 = new JLabel();
        tabbedPane = new JTabbedPane();
        scrollPaneUser = new JScrollPane();
        tableUser = new JTable();
        scrollPaneGoods = new JScrollPane();
        tableGoods = new JTable();
        scrollPaneOrder = new JScrollPane();
        tableOrder = new JTable();
        scrollPaneMessages = new JScrollPane();
        tableMessages = new JTable();
        txtKeywords = new JTextField();
        table5 = new JTable();

        //======== this ========
        setTitle("\u8d85\u5e02\u7ba1\u7406\u7cfb\u7edf");
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/com/singerw/ui/img/icoimage.png")).getImage());
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("\u83dc\u5355");
                menu1.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 14));

                //---- menuItem1 ----
                menuItem1.setText("\u8fd4\u56de\u4e0a\u4e00\u7ea7");
                menuItem1.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 14));
                menuItem1.addActionListener(e -> menuItemGoBack(e));
                menu1.add(menuItem1);

                //---- menuItemExit ----
                menuItemExit.setText("\u9000\u51fa\u7cfb\u7edf");
                menuItemExit.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 14));
                menuItemExit.addActionListener(e -> menuItemExit(e));
                menu1.add(menuItemExit);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //---- fssSouSuo ----
        fssSouSuo.setText("\u641c \u7d22");
        fssSouSuo.setFont(new Font("\u9ed1\u4f53", fssSouSuo.getFont().getStyle(), 16));
        fssSouSuo.addActionListener(e -> fssSouSuoActionPerformed(e));
        contentPane.add(fssSouSuo);
        fssSouSuo.setBounds(685, 34, 85, 30);

        //---- label1 ----
        label1.setText("\u8bf7\u8f93\u5165\u5173\u952e\u5b57");
        label1.setFont(new Font("\u9ed1\u4f53", label1.getFont().getStyle(), 16));
        contentPane.add(label1);
        label1.setBounds(410, 38, label1.getPreferredSize().width, 25);

        //======== tabbedPane ========
        {
            tabbedPane.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));

            //======== scrollPaneUser ========
            {

                //---- tableUser ----
                tableUser.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                    },
                    new String[] {
                        "\u7528\u6237\u7f16\u53f7", "\u7528\u6237\u540d", "\u5bc6\u7801", "\u624b\u673a\u53f7", "\u5730\u5740", "\u6743\u9650\u72b6\u6001", "\u767b\u5f55\u65f6\u95f4"
                    }
                ));
                tableUser.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                tableUser.setRowHeight(25);
                scrollPaneUser.setViewportView(tableUser);
            }
            tabbedPane.addTab("\u7528\u6237\u4fe1\u606f", scrollPaneUser);

            //======== scrollPaneGoods ========
            {

                //---- tableGoods ----
                tableGoods.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                    },
                    new String[] {
                        "\u5546\u54c1\u7f16\u53f7", "\u5546\u54c1\u540d\u79f0", "\u4ef7\u683c", "\u5e93\u5b58", "\u8be6\u60c5", "\u72b6\u6001"
                    }
                ));
                tableGoods.setRowHeight(25);
                tableGoods.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                scrollPaneGoods.setViewportView(tableGoods);
            }
            tabbedPane.addTab("\u5546\u54c1\u4fe1\u606f", scrollPaneGoods);

            //======== scrollPaneOrder ========
            {

                //---- tableOrder ----
                tableOrder.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                    },
                    new String[] {
                        "\u8ba2\u5355\u7f16\u53f7", "\u7528\u6237\u7f16\u53f7", "\u4e0b\u5355\u65f6\u95f4", "\u6536\u8d27\u5730\u5740", "\u603b\u91d1\u989d"
                    }
                ));
                tableOrder.setRowHeight(25);
                tableOrder.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                scrollPaneOrder.setViewportView(tableOrder);
            }
            tabbedPane.addTab("\u8ba2\u5355\u4fe1\u606f", scrollPaneOrder);

            //======== scrollPaneMessages ========
            {

                //---- tableMessages ----
                tableMessages.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                    },
                    new String[] {
                        "\u7f16\u53f7", "\u5546\u54c1\u7f16\u53f7", "\u7559\u8a00\u4fe1\u606f", "\u7528\u6237\u7f16\u53f7", "\u53d1\u5e03\u65f6\u95f4"
                    }
                ));
                tableMessages.setFont(new Font("Microsoft YaHei UI", tableMessages.getFont().getStyle(), 14));
                tableMessages.setRowHeight(25);
                scrollPaneMessages.setViewportView(tableMessages);
            }
            tabbedPane.addTab("\u7559\u8a00\u4fe1\u606f", scrollPaneMessages);
        }
        contentPane.add(tabbedPane);
        tabbedPane.setBounds(10, 60, 765, 340);

        //---- txtKeywords ----
        txtKeywords.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
        contentPane.add(txtKeywords);
        txtKeywords.setBounds(515, 33, 160, 34);

        contentPane.setPreferredSize(new Dimension(785, 455));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItemExit;
    private JButton fssSouSuo;
    private JLabel label1;
    private JTabbedPane tabbedPane;
    private JScrollPane scrollPaneUser;
    private JTable tableUser;
    private JScrollPane scrollPaneGoods;
    private JTable tableGoods;
    private JScrollPane scrollPaneOrder;
    private JTable tableOrder;
    private JScrollPane scrollPaneMessages;
    private JTable tableMessages;
    private JTextField txtKeywords;
    private JTable table5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
