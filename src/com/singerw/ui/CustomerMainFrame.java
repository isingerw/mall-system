/*
 * Created by JFormDesigner on Tue Jun 15 11:20:57 CST 2021
 */

package com.singerw.ui;

import com.singerw.dao.GoodsDao;
import com.singerw.entity.GoodsEntity;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import net.miginfocom.swing.*;

/**
 * @author unknown
 */
public class CustomerMainFrame extends JFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    CustomerMainFrame customerMainFrame = new CustomerMainFrame();
                    customerMainFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public CustomerMainFrame() {
        initComponents();
    }

    /**
     * @Author CodeSleep
     * @Date: 2021-06-15 15:30
     * @Description: //TODO 菜单栏Exit退出系统按键监听事件
     * @param e
     */
    private void menuItemExit(ActionEvent e) {
        System.exit(0);
    }





    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        popupMenu1 = new JPopupMenu();
        systemMenuBar = new JMenuBar();
        menuGooodsMenu = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menuUserMenu = new JMenu();
        menuItem4 = new JMenuItem();
        menuItem3 = new JMenuItem();
        menuNavigation = new JMenu();
        menuItemIndex = new JMenuItem();
        menuItemGoBack = new JMenuItem();
        menuItemExit = new JMenuItem();
        menuHelp = new JMenu();
        menuItemHelp = new JMenuItem();
        menuItemXinShou = new JMenuItem();
        menuItemAbout = new JMenuItem();
        tabbedPane = new JTabbedPane();
        scrollPaneGoods = new JScrollPane();
        tableGoods = new JTable();
        scrollPaneShopping = new JScrollPane();
        tableShopping = new JTable();
        scrollPaneOrders = new JScrollPane();
        tableOrders = new JTable();

        //======== this ========
        setTitle("\u8d85\u5e02\u7ba1\u7406\u7cfb\u7edf");
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/com/singerw/ui/img/icoimage.png")).getImage());
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== systemMenuBar ========
        {

            //======== menuGooodsMenu ========
            {
                menuGooodsMenu.setText("\u4e2a\u4eba\u4e2d\u5fc3");
                menuGooodsMenu.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 14));

                //---- menuItem1 ----
                menuItem1.setText("\u6211\u7684\u8d2d\u7269\u8f66");
                menuGooodsMenu.add(menuItem1);

                //---- menuItem2 ----
                menuItem2.setText("\u6211\u7684\u8ba2\u5355");
                menuGooodsMenu.add(menuItem2);
            }
            systemMenuBar.add(menuGooodsMenu);

            //======== menuUserMenu ========
            {
                menuUserMenu.setText("\u8bbe\u7f6e");

                //---- menuItem4 ----
                menuItem4.setText("\u4fee\u6539\u4fe1\u606f");
                menuUserMenu.add(menuItem4);

                //---- menuItem3 ----
                menuItem3.setText("\u4fee\u6539\u5bc6\u7801");
                menuUserMenu.add(menuItem3);
            }
            systemMenuBar.add(menuUserMenu);

            //======== menuNavigation ========
            {
                menuNavigation.setText("\u5bfc\u822a");

                //---- menuItemIndex ----
                menuItemIndex.setText("\u8fd4\u56de\u4e3b\u9875");
                menuNavigation.add(menuItemIndex);

                //---- menuItemGoBack ----
                menuItemGoBack.setText("\u8fd4\u56de\u4e0a\u4e00\u7ea7");
                menuItemGoBack.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 14));
                menuNavigation.add(menuItemGoBack);

                //---- menuItemExit ----
                menuItemExit.setText("\u9000\u51fa\u7cfb\u7edf");
                menuItemExit.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 14));
                menuItemExit.addActionListener(e -> menuItemExit(e));
                menuNavigation.add(menuItemExit);
            }
            systemMenuBar.add(menuNavigation);

            //======== menuHelp ========
            {
                menuHelp.setText("\u5e2e\u52a9");

                //---- menuItemHelp ----
                menuItemHelp.setText("?\u5e2e\u52a9");
                menuHelp.add(menuItemHelp);

                //---- menuItemXinShou ----
                menuItemXinShou.setText("\u5165\u95e8\u6307\u5357");
                menuHelp.add(menuItemXinShou);

                //---- menuItemAbout ----
                menuItemAbout.setText("\u5173\u4e8e");
                menuHelp.add(menuItemAbout);
            }
            systemMenuBar.add(menuHelp);
        }
        setJMenuBar(systemMenuBar);

        //======== tabbedPane ========
        {
            tabbedPane.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));

            //======== scrollPaneGoods ========
            {

                //---- tableGoods ----
                tableGoods.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                    },
                    new String[] {
                        "\u5546\u54c1\u7f16\u53f7", "\u5546\u54c1\u540d\u79f0", "\u4ef7\u683c", "\u5e93\u5b58"
                    }
                ));
                tableGoods.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                tableGoods.setRowHeight(25);
                scrollPaneGoods.setViewportView(tableGoods);
            }
            tabbedPane.addTab("\u5546\u54c1", scrollPaneGoods);

            //======== scrollPaneShopping ========
            {

                //---- tableShopping ----
                tableShopping.setModel(new DefaultTableModel(
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
                tableShopping.setRowHeight(25);
                tableShopping.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                scrollPaneShopping.setViewportView(tableShopping);
            }
            tabbedPane.addTab("\u6211\u7684\u8d2d\u7269\u8f66", scrollPaneShopping);

            //======== scrollPaneOrders ========
            {

                //---- tableOrders ----
                tableOrders.setModel(new DefaultTableModel(
                    new Object[][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                    },
                    new String[] {
                        "\u5546\u54c1\u7f16\u53f7", "\u5546\u54c1\u540d\u79f0", "\u5546\u54c1\u4ef7\u683c", "\u5546\u54c1\u5e93\u5b58"
                    }
                ));
                tableOrders.setFont(new Font("Microsoft YaHei UI", tableOrders.getFont().getStyle(), 14));
                tableOrders.setRowHeight(25);
                tableOrders.setPreferredScrollableViewportSize(new Dimension(450, 350));
                scrollPaneOrders.setViewportView(tableOrders);
            }
            tabbedPane.addTab("\u6211\u7684\u8ba2\u5355", scrollPaneOrders);
        }
        contentPane.add(tabbedPane);
        tabbedPane.setBounds(13, 20, 772, 355);

        contentPane.setPreferredSize(new Dimension(795, 440));
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JPopupMenu popupMenu1;
    private JMenuBar systemMenuBar;
    private JMenu menuGooodsMenu;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenu menuUserMenu;
    private JMenuItem menuItem4;
    private JMenuItem menuItem3;
    private JMenu menuNavigation;
    private JMenuItem menuItemIndex;
    private JMenuItem menuItemGoBack;
    private JMenuItem menuItemExit;
    private JMenu menuHelp;
    private JMenuItem menuItemHelp;
    private JMenuItem menuItemXinShou;
    private JMenuItem menuItemAbout;
    private JTabbedPane tabbedPane;
    private JScrollPane scrollPaneGoods;
    private JTable tableGoods;
    private JScrollPane scrollPaneShopping;
    private JTable tableShopping;
    private JScrollPane scrollPaneOrders;
    private JTable tableOrders;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
