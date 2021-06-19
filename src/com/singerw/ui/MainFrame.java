/*
 * Created by JFormDesigner on Fri Jun 11 15:31:26 CST 2021
 */

package com.singerw.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.singerw.dao.GoodsDao;
import com.singerw.dao.MessageDao;
import com.singerw.dao.OrderDao;
import com.singerw.dao.UserDao;
import com.singerw.entity.GoodsEntity;
import com.singerw.entity.MessageEntity;
import com.singerw.entity.OrderAndUserEntity;
import com.singerw.entity.UserEntity;
import com.singerw.tools.CommonInfo;

import java.util.List;
import java.awt.event.ActionEvent;


/**
 * @author unknown
 */
public class MainFrame extends JFrame {


    public MainFrame() {
        initComponents();
    }


    /**
     * @param e
     * @Author CodeSleep
     * @Date: 2021-06-15 9:45
     * @Description: //TODO 菜单栏商品管理==>新增商品按钮监听事件
     */
    private void menuItemNewGoodsActionPerformed(ActionEvent e) {
        AddGoodsFrame addGoodsFrame = new AddGoodsFrame();
        addGoodsFrame.setVisible(true);
    }


    /**
     * @param e
     * @Author CodeSleep
     * @Date: 2021-06-15 9:42
     * @Description: //TODO 菜单栏商品管理==>查询商品按钮监听事件
     */
    private void menuItemChaXunGoodsActionPerformed(ActionEvent e) {
        // 切换选项卡的页  1 为选项卡页的顺序 从0算起
        tabbedPane.setSelectedIndex(0);
        // 自动查询并展示商品信息
        fillGoodsTable(scrollPaneGoods);
    }

    /**
     * 自动查询并展示商品信息方法
     *
     * @param scrollPaneGoods
     */
    private void fillGoodsTable(JScrollPane scrollPaneGoods) {
        // 获取用户输入的关键字
        String keywords = txtKeywords1.getText();
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
                new String[]{"\u7F16\u53F7", "\u5546\u54C1\u540D\u79F0", "\u5546\u54C1\u4EF7\u683C",
                        "\u5E93\u5B58", "\u72B6\u6001"}));
        // setViewportView 设置table和scrollpane关联
        scrollPaneGoods.setViewportView(tableGoods);
    }


    /**
     * @param e
     * @Author CodeSleep
     * @Date: 2021-06-15 9:44
     * @Description: //TODO 菜单栏用户管理===>新增用户按钮监听事件
     */
    private void menuItemNewUserActionPerformed(ActionEvent e) {
        AddUserFrame addUserFrame = new AddUserFrame();
        addUserFrame.setVisible(true);
    }


    /**
     * @param e
     * @Author CodeSleep
     * @Date: 2021-06-15 9:42
     * @Description: //TODO 菜单栏用户管理==>查询用户按钮监听事件
     */
    private void menuItemChaXunUserActionPerformed(ActionEvent e) {
        // 切换选项卡的页  1 为选项卡页的顺序 从0算起
        tabbedPane.setSelectedIndex(1);
        fillUsersTable(scrollPaneUsers);
    }

    /**
     * 自动查询并展示用户信息方法
     *
     * @param scrollPaneUser
     */
    private void fillUsersTable(JScrollPane scrollPaneUser) {
        // 获取用户输入的关键字
        String keywords = txtKeywords2.getText();
        UserDao userDao = new UserDao();
        // 先查
        List<UserEntity> list = userDao.getUserByLike("%" + keywords + "%");
        // 记录条数
        int size = list.size();
        // 创建保存数据的二维数组
        Object obj[][] = new Object[size][7];
        // 循环
        for (int i = 0; i < size; i++) {
            UserEntity ue = list.get(i);
            obj[i][0] = ue.getCid();
            obj[i][1] = ue.getCname();
            obj[i][2] = ue.getCpwd();
            obj[i][3] = ue.getCphone();
            obj[i][4] = ue.getCaddress();
            obj[i][5] = ue.getLevel() == 0 ? "普通用户" : "管理员";
            obj[i][6] = ue.getLastlogin();
        }
        // 设置setModel => new Object[][] =>要展示的数据 new String[]:列名
        tableUsers.setModel(new DefaultTableModel(
                obj,
                new String[]{"\u7528\u6237\u7f16\u53f7", "\u7528\u6237\u540d", "\u5bc6\u7801", "\u624b\u673a\u53f7", "\u5730\u5740", "\u6743\u9650\u72b6\u6001", "\u767b\u5f55\u65f6\u95f4"
                }));
        // setViewportView 设置table和scrollpane关联
        scrollPaneUsers.setViewportView(tableUsers);
    }


    /**
     * @param e
     * @Author CodeSleep
     * @Date: 2021-06-16 10:32
     * @Description: //TODO 菜单栏==》订单管理==》订单查询按钮监听事件
     */
    private void menuItemOrderChaXunActionPerformed(ActionEvent e) {
        // 切换选项卡的页  1 为选项卡页的顺序 从0算起
        tabbedPane.setSelectedIndex(2);
        fillOrderTable(scrollPaneOrder);
    }

    /**
     * 自动查询并展示订单信息方法
     *
     * @param scrollPaneOrder
     */
    private void fillOrderTable(JScrollPane scrollPaneOrder) {
        // 获取用户输入的关键字
        String keywords = txtKeywords3.getText();
        // 查询数据填充到jtable中来
        OrderDao orderDao = new OrderDao();
        // 先查
        List<OrderAndUserEntity> list = orderDao.getOrderByLike("%" + keywords + "%");
        // 记录条数
        int size = list.size();
        // 创建保存数据的二维数组
        Object obj[][] = new Object[size][6];
        // 循环
        for (int i = 0; i < size; i++) {
            OrderAndUserEntity oaue = list.get(i);
            obj[i][0] = oaue.getOid();
            obj[i][1] = oaue.getCid();
            obj[i][2] = oaue.getCname();
            obj[i][3] = oaue.getOdate();
            obj[i][4] = oaue.getAddress();
            obj[i][5] = oaue.getTotal();
        }
        // 设置setModel => new Object[][] =>要展示的数据 new String[]:列名
        tableOrder.setModel(new DefaultTableModel(
                obj,
                new String[]{
                        "\u8ba2\u5355\u7f16\u53f7", "\u7528\u6237\u7f16\u53f7", "\u7528\u6237\u540d", "\u4e0b\u5355\u65f6\u95f4", "\u6536\u8d27\u5730\u5740", "\u603b\u91d1\u989d"
                }
        ));
        // setViewportView 设置table和scrollpane关联
        scrollPaneOrder.setViewportView(tableOrder);
    }

    /**
     * @param e
     * @Author CodeSleep
     * @Date: 2021-06-17 1:46
     * @Description: //TODO  菜单栏==》留言管理==》留言查询按钮监听事件
     */
    private void menuItemMessagesActionPerformed(ActionEvent e) {
        // 切换选项卡的页  1 为选项卡页的顺序 从0算起
        tabbedPane.setSelectedIndex(3);
        fillMessagesTable(scrollPaneMessages);
    }

    private void fillMessagesTable(JScrollPane scrollPaneOrder) {
        // 获取用户输入的关键字
        String keywords = txtKeywords4.getText();
        MessageDao messageDao = new MessageDao();
        // 先查
        List<MessageEntity> list = messageDao.getMessageByLike("%" + keywords + "%");
        // 记录条数
        int size = list.size();
        // 创建保存数据的二维数组
        Object obj[][] = new Object[size][5];
        // 循环
        for (int i = 0; i < size; i++) {
            MessageEntity g = list.get(i);
            obj[i][0] = g.getMid();
            obj[i][1] = g.getGid();
            obj[i][2] = g.getMessage();
            obj[i][3] = g.getCid();
            obj[i][4] = g.getPdate();
        }
        // 设置setModel => new Object[][] =>要展示的数据 new String[]:列名
        tableMessages.setModel(new DefaultTableModel(
                obj,
                new String[]{
                        "\u7559\u8a00\u7f16\u53f7", "\u5546\u54c1\u7f16\u53f7", "\u7559\u8a00\u4fe1\u606f", "\u7528\u6237\u7f16\u53f7", "\u53d1\u5e03\u65f6\u95f4"
                }));
        // setViewportView 设置table和scrollpane关联
        scrollPaneMessages.setViewportView(tableMessages);
    }

    /**
     * @param e
     * @Author CodeSleep
     * @Date: 2021-06-14 23:52
     * @Description: //TODO 菜单===》导航===》退出系统
     */
    private void menuItemExit(ActionEvent e) {
        System.exit(0);
    }


    /**
     * @param e
     * @Author CodeSleep
     * @Date: 2021-06-14 23:55
     * @Description: //TODO 菜单===》返回上一级
     */
    private void menuItemGoBack(ActionEvent e) {
        // TODO add your code here
    }


    /**
     * @param e
     * @Author CodeSleep
     * @Date: 2021-06-20 4:53
     * @Description: //TODO 列表搜索按键监听事件
     */
    private void goodsSouSuoActionPerformed(ActionEvent e) {
        // 获取用户输入的关键字
        String keywords = txtKeywords1.getText();
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
                new String[]{"\u7F16\u53F7", "\u5546\u54C1\u540D\u79F0", "\u5546\u54C1\u4EF7\u683C",
                        "\u5E93\u5B58", "\u72B6\u6001"}));
        // setViewportView 设置table和scrollpane关联
        scrollPaneGoods.setViewportView(tableGoods);
    }

    private void usersSouSuoActionPerformed(ActionEvent e) {
        // 获取用户输入的关键字
        String keywords = txtKeywords2.getText();
        UserDao userDao = new UserDao();
        // 先查
        List<UserEntity> list = userDao.getUserByLike("%" + keywords + "%");
        // 记录条数
        int size = list.size();
        // 创建保存数据的二维数组
        Object obj[][] = new Object[size][7];
        // 循环
        for (int i = 0; i < size; i++) {
            UserEntity ue = list.get(i);
            obj[i][0] = ue.getCid();
            obj[i][1] = ue.getCname();
            obj[i][2] = ue.getCpwd();
            obj[i][3] = ue.getCphone();
            obj[i][4] = ue.getCaddress();
            obj[i][5] = ue.getLevel() == 0 ? "普通用户" : "管理员";
            obj[i][6] = ue.getLastlogin();
        }
        // 设置setModel => new Object[][] =>要展示的数据 new String[]:列名
        tableUsers.setModel(new DefaultTableModel(
                obj,
                new String[]{"\u7528\u6237\u7f16\u53f7", "\u7528\u6237\u540d", "\u5bc6\u7801", "\u624b\u673a\u53f7", "\u5730\u5740", "\u6743\u9650\u72b6\u6001", "\u767b\u5f55\u65f6\u95f4"
                }));
        // setViewportView 设置table和scrollpane关联
        scrollPaneUsers.setViewportView(tableUsers);
    }

    private void ordersSouSuoActionPerformed(ActionEvent e) {
        // 获取用户输入的关键字
        String keywords = txtKeywords3.getText();
        // 查询数据填充到jtable中来
        OrderDao orderDao = new OrderDao();
        // 先查
        List<OrderAndUserEntity> list = orderDao.getOrderByLike("%" + keywords + "%");
        // 记录条数
        int size = list.size();
        // 创建保存数据的二维数组
        Object obj[][] = new Object[size][6];
        // 循环
        for (int i = 0; i < size; i++) {
            OrderAndUserEntity oaue = list.get(i);
            obj[i][0] = oaue.getOid();
            obj[i][1] = oaue.getCid();
            obj[i][2] = oaue.getCname();
            obj[i][3] = oaue.getOdate();
            obj[i][4] = oaue.getAddress();
            obj[i][5] = oaue.getTotal();
        }
        // 设置setModel => new Object[][] =>要展示的数据 new String[]:列名
        tableOrder.setModel(new DefaultTableModel(
                obj,
                new String[]{
                        "\u8ba2\u5355\u7f16\u53f7", "\u7528\u6237\u7f16\u53f7", "\u7528\u6237\u540d", "\u4e0b\u5355\u65f6\u95f4", "\u6536\u8d27\u5730\u5740", "\u603b\u91d1\u989d"
                }
        ));
        // setViewportView 设置table和scrollpane关联
        scrollPaneOrder.setViewportView(tableOrder);
    }

    private void messagesSouSuoActionPerformed(ActionEvent e) {
        // 获取用户输入的关键字
        String keywords = txtKeywords4.getText();
        MessageDao messageDao = new MessageDao();
        // 先查
        List<MessageEntity> list = messageDao.getMessageByLike("%" + keywords + "%");
        // 记录条数
        int size = list.size();
        // 创建保存数据的二维数组
        Object obj[][] = new Object[size][5];
        // 循环
        for (int i = 0; i < size; i++) {
            MessageEntity g = list.get(i);
            obj[i][0] = g.getMid();
            obj[i][1] = g.getGid();
            obj[i][2] = g.getMessage();
            obj[i][3] = g.getCid();
            obj[i][4] = g.getPdate();
        }
        // 设置setModel => new Object[][] =>要展示的数据 new String[]:列名
        tableMessages.setModel(new DefaultTableModel(
                obj,
                new String[]{
                        "\u7559\u8a00\u7f16\u53f7", "\u5546\u54c1\u7f16\u53f7", "\u7559\u8a00\u4fe1\u606f", "\u7528\u6237\u7f16\u53f7", "\u53d1\u5e03\u65f6\u95f4"
                }));
        // setViewportView 设置table和scrollpane关联
        scrollPaneMessages.setViewportView(tableMessages);
    }


    /**
     * @Author CodeSleep
     * @Date: 2021-06-14 23:40
     * @Description: //TODO UI界面
     */
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        systemMenuBar = new JMenuBar();
        menuUserMenu = new JMenu();
        menuItemNewUser = new JMenuItem();
        menuItemChaXunUser = new JMenuItem();
        menuGooodsMenu = new JMenu();
        menuItemNewGoods = new JMenuItem();
        menuItemChaXunGoods = new JMenuItem();
        menu1 = new JMenu();
        menuItem4 = new JMenuItem();
        menuItemOrderChaXun = new JMenuItem();
        menu2 = new JMenu();
        menuItemMessages = new JMenuItem();
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
        Goods = new JPanel();
        label1 = new JLabel();
        txtKeywords1 = new JTextArea();
        buttonSS1 = new JButton();
        scrollPaneGoods = new JScrollPane();
        tableGoods = new JTable();
        Users = new JPanel();
        label2 = new JLabel();
        txtKeywords2 = new JTextArea();
        buttonSS2 = new JButton();
        scrollPaneUsers = new JScrollPane();
        tableUsers = new JTable();
        Orders = new JPanel();
        label3 = new JLabel();
        txtKeywords3 = new JTextArea();
        buttonSS3 = new JButton();
        scrollPaneOrder = new JScrollPane();
        tableOrder = new JTable();
        Messages = new JPanel();
        label4 = new JLabel();
        txtKeywords4 = new JTextArea();
        buttonSS4 = new JButton();
        scrollPaneMessages = new JScrollPane();
        tableMessages = new JTable();

        //======== this ========
        setTitle("\u5546\u57ce\u7ba1\u7406\u7cfb\u7edf");
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/com/singerw/ui/img/icoimage.png")).getImage());
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== systemMenuBar ========
        {

            //======== menuUserMenu ========
            {
                menuUserMenu.setText("\u7528\u6237\u7ba1\u7406");
                menuUserMenu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                //---- menuItemNewUser ----
                menuItemNewUser.setText("\u65b0\u589e\u7528\u6237");
                menuItemNewUser.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                menuItemNewUser.addActionListener(e -> menuItemNewUserActionPerformed(e));
                menuUserMenu.add(menuItemNewUser);

                //---- menuItemChaXunUser ----
                menuItemChaXunUser.setText("\u67e5\u8be2\u7528\u6237");
                menuItemChaXunUser.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                menuItemChaXunUser.addActionListener(e -> menuItemChaXunUserActionPerformed(e));
                menuUserMenu.add(menuItemChaXunUser);
            }
            systemMenuBar.add(menuUserMenu);

            //======== menuGooodsMenu ========
            {
                menuGooodsMenu.setText("\u5546\u54c1\u7ba1\u7406");
                menuGooodsMenu.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 16));

                //---- menuItemNewGoods ----
                menuItemNewGoods.setText("\u65b0\u589e\u5546\u54c1");
                menuItemNewGoods.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                menuItemNewGoods.addActionListener(e -> menuItemNewGoodsActionPerformed(e));
                menuGooodsMenu.add(menuItemNewGoods);

                //---- menuItemChaXunGoods ----
                menuItemChaXunGoods.setText("\u67e5\u8be2\u5546\u54c1");
                menuItemChaXunGoods.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                menuItemChaXunGoods.addActionListener(e -> menuItemChaXunGoodsActionPerformed(e));
                menuGooodsMenu.add(menuItemChaXunGoods);
            }
            systemMenuBar.add(menuGooodsMenu);

            //======== menu1 ========
            {
                menu1.setText("\u8ba2\u5355\u7ba1\u7406");
                menu1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                //---- menuItem4 ----
                menuItem4.setText("\u8ba2\u5355\u4fee\u6539");
                menuItem4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                menu1.add(menuItem4);

                //---- menuItemOrderChaXun ----
                menuItemOrderChaXun.setText("\u8ba2\u5355\u67e5\u8be2");
                menuItemOrderChaXun.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                menuItemOrderChaXun.addActionListener(e -> menuItemOrderChaXunActionPerformed(e));
                menu1.add(menuItemOrderChaXun);
            }
            systemMenuBar.add(menu1);

            //======== menu2 ========
            {
                menu2.setText("\u7559\u8a00\u7ba1\u7406");
                menu2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                //---- menuItemMessages ----
                menuItemMessages.setText("\u67e5\u8be2\u7559\u8a00");
                menuItemMessages.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                menuItemMessages.addActionListener(e -> menuItemMessagesActionPerformed(e));
                menu2.add(menuItemMessages);

                //---- menuItem3 ----
                menuItem3.setText("\u5220\u9664\u7559\u8a00");
                menuItem3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                menu2.add(menuItem3);
            }
            systemMenuBar.add(menu2);

            //======== menuNavigation ========
            {
                menuNavigation.setText("\u5bfc\u822a");
                menuNavigation.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                //---- menuItemIndex ----
                menuItemIndex.setText("\u8fd4\u56de\u4e3b\u9875");
                menuItemIndex.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                menuNavigation.add(menuItemIndex);

                //---- menuItemGoBack ----
                menuItemGoBack.setText("\u8fd4\u56de\u4e0a\u4e00\u7ea7");
                menuItemGoBack.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                menuItemGoBack.addActionListener(e -> menuItemGoBack(e));
                menuNavigation.add(menuItemGoBack);

                //---- menuItemExit ----
                menuItemExit.setText("\u5b89\u5168\u9000\u51fa");
                menuItemExit.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                menuItemExit.addActionListener(e -> menuItemExit(e));
                menuNavigation.add(menuItemExit);
            }
            systemMenuBar.add(menuNavigation);

            //======== menuHelp ========
            {
                menuHelp.setText("\u5e2e\u52a9");
                menuHelp.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                //---- menuItemHelp ----
                menuItemHelp.setText("?\u5e2e\u52a9");
                menuItemHelp.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                menuHelp.add(menuItemHelp);

                //---- menuItemXinShou ----
                menuItemXinShou.setText("\u5165\u95e8\u6307\u5357");
                menuItemXinShou.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                menuHelp.add(menuItemXinShou);

                //---- menuItemAbout ----
                menuItemAbout.setText("\u5173\u4e8e");
                menuItemAbout.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                menuHelp.add(menuItemAbout);
            }
            systemMenuBar.add(menuHelp);
        }
        setJMenuBar(systemMenuBar);

        //======== tabbedPane ========
        {
            tabbedPane.setTabPlacement(SwingConstants.LEFT);
            tabbedPane.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

            //======== Goods ========
            {
                Goods.setMaximumSize(new Dimension(320, 320));
                Goods.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                Goods.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax
                        .swing.border.EmptyBorder(0, 0, 0, 0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax.swing
                        .border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.
                        Font("Dia\u006cog", java.awt.Font.BOLD, 12), java.awt.Color.red
                ), Goods.getBorder()));
                Goods.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                    @Override
                    public void propertyChange(java.beans.PropertyChangeEvent e) {
                        if ("\u0062ord\u0065r".equals(e.getPropertyName(
                        ))) throw new RuntimeException();
                    }
                });
                Goods.setLayout(null);

                //---- label1 ----
                label1.setText("\u8bf7\u8f93\u5165\u8981\u67e5\u8be2\u7684\u5173\u952e\u5b57");
                label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                Goods.add(label1);
                label1.setBounds(263, 15, label1.getPreferredSize().width, 20);

                //---- txtKeywords1 ----
                txtKeywords1.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
                Goods.add(txtKeywords1);
                txtKeywords1.setBounds(434, 15, 158, 20);

                //---- buttonSS1 ----
                buttonSS1.setText("\u641c \u7d22");
                buttonSS1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                buttonSS1.addActionListener(e -> goodsSouSuoActionPerformed(e));
                Goods.add(buttonSS1);
                buttonSS1.setBounds(599, 10, buttonSS1.getPreferredSize().width, 30);

                //======== scrollPaneGoods ========
                {
                    scrollPaneGoods.setDoubleBuffered(true);

                    //---- tableGoods ----
                    tableGoods.setModel(new DefaultTableModel(
                            new Object[][]{
                                    {"", null, null, null, null},
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
                                    {null, null, null, null, null},
                                    {null, null, null, null, null},
                            },
                            new String[]{
                                    "\u5546\u54c1\u7f16\u53f7", "\u5546\u54c1\u540d\u79f0", "\u5546\u54c1\u4ef7\u683c", "\u5546\u54c1\u5e93\u5b58", "\u5546\u54c1\u72b6\u6001"
                            }
                    ));
                    tableGoods.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                    tableGoods.setRowHeight(25);
                    scrollPaneGoods.setViewportView(tableGoods);
                }
                Goods.add(scrollPaneGoods);
                scrollPaneGoods.setBounds(0, 50, 700, 320);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < Goods.getComponentCount(); i++) {
                        Rectangle bounds = Goods.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = Goods.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    Goods.setMinimumSize(preferredSize);
                    Goods.setPreferredSize(preferredSize);
                }
            }
            tabbedPane.addTab("\u5546\u54c1\u5217\u8868", Goods);

            //======== Users ========
            {
                Users.setMaximumSize(new Dimension(320, 320));
                Users.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                Users.setLayout(null);

                //---- label2 ----
                label2.setText("\u8bf7\u8f93\u5165\u8981\u67e5\u8be2\u7684\u5173\u952e\u5b57");
                label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                Users.add(label2);
                label2.setBounds(263, 15, label2.getPreferredSize().width, 20);

                //---- txtKeywords2 ----
                txtKeywords2.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
                Users.add(txtKeywords2);
                txtKeywords2.setBounds(434, 15, 158, 20);

                //---- buttonSS2 ----
                buttonSS2.setText("\u641c \u7d22");
                buttonSS2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                buttonSS2.addActionListener(e -> usersSouSuoActionPerformed(e));
                Users.add(buttonSS2);
                buttonSS2.setBounds(599, 10, buttonSS2.getPreferredSize().width, 30);

                //======== scrollPaneUsers ========
                {
                    scrollPaneUsers.setDoubleBuffered(true);

                    //---- tableUsers ----
                    tableUsers.setModel(new DefaultTableModel(
                            new Object[][]{
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
                                    {null, null, null, null, null, null, null},
                                    {null, null, null, null, null, null, null},
                                    {null, null, null, null, null, null, null},
                            },
                            new String[]{
                                    "\u7f16\u53f7", "\u7528\u6237\u540d", "\u5bc6\u7801", "\u624b\u673a\u53f7", "\u5730\u5740", "\u89d2\u8272", "\u767b\u5f55\u65f6\u95f4"
                            }
                    ));
                    tableUsers.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                    tableUsers.setRowHeight(25);
                    scrollPaneUsers.setViewportView(tableUsers);
                }
                Users.add(scrollPaneUsers);
                scrollPaneUsers.setBounds(0, 50, 700, 320);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < Users.getComponentCount(); i++) {
                        Rectangle bounds = Users.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = Users.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    Users.setMinimumSize(preferredSize);
                    Users.setPreferredSize(preferredSize);
                }
            }
            tabbedPane.addTab("\u7528\u6237\u5217\u8868", Users);

            //======== Orders ========
            {
                Orders.setMaximumSize(new Dimension(320, 320));
                Orders.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                Orders.setLayout(null);

                //---- label3 ----
                label3.setText("\u8bf7\u8f93\u5165\u8981\u67e5\u8be2\u7684\u5173\u952e\u5b57");
                label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                Orders.add(label3);
                label3.setBounds(263, 15, label3.getPreferredSize().width, 20);

                //---- txtKeywords3 ----
                txtKeywords3.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
                Orders.add(txtKeywords3);
                txtKeywords3.setBounds(434, 15, 158, 20);

                //---- buttonSS3 ----
                buttonSS3.setText("\u641c \u7d22");
                buttonSS3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                buttonSS3.addActionListener(e -> ordersSouSuoActionPerformed(e));
                Orders.add(buttonSS3);
                buttonSS3.setBounds(599, 10, buttonSS3.getPreferredSize().width, 30);

                //======== scrollPaneOrder ========
                {
                    scrollPaneOrder.setDoubleBuffered(true);

                    //---- tableOrder ----
                    tableOrder.setModel(new DefaultTableModel(
                            new Object[][]{
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
                                    {null, null, null, null, null, null},
                                    {null, null, null, null, null, null},
                                    {null, null, null, null, null, null},
                            },
                            new String[]{
                                    "\u8ba2\u5355\u7f16\u53f7", "\u7528\u6237\u7f16\u53f7", "\u7528\u6237\u540d", "\u4e0b\u5355\u65f6\u95f4", "\u6536\u8d27\u5730\u5740", "\u603b\u91d1\u989d"
                            }
                    ));
                    tableOrder.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                    tableOrder.setRowHeight(25);
                    scrollPaneOrder.setViewportView(tableOrder);
                }
                Orders.add(scrollPaneOrder);
                scrollPaneOrder.setBounds(0, 50, 700, 320);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < Orders.getComponentCount(); i++) {
                        Rectangle bounds = Orders.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = Orders.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    Orders.setMinimumSize(preferredSize);
                    Orders.setPreferredSize(preferredSize);
                }
            }
            tabbedPane.addTab("\u8ba2\u5355\u5217\u8868", Orders);

            //======== Messages ========
            {
                Messages.setMaximumSize(new Dimension(320, 320));
                Messages.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                Messages.setLayout(null);

                //---- label4 ----
                label4.setText("\u8bf7\u8f93\u5165\u8981\u67e5\u8be2\u7684\u5173\u952e\u5b57");
                label4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                Messages.add(label4);
                label4.setBounds(263, 15, label4.getPreferredSize().width, 20);

                //---- txtKeywords4 ----
                txtKeywords4.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
                Messages.add(txtKeywords4);
                txtKeywords4.setBounds(434, 15, 158, 20);

                //---- buttonSS4 ----
                buttonSS4.setText("\u641c \u7d22");
                buttonSS4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                buttonSS4.addActionListener(e -> messagesSouSuoActionPerformed(e));
                Messages.add(buttonSS4);
                buttonSS4.setBounds(599, 10, buttonSS4.getPreferredSize().width, 30);

                //======== scrollPaneMessages ========
                {
                    scrollPaneMessages.setDoubleBuffered(true);

                    //---- tableMessages ----
                    tableMessages.setModel(new DefaultTableModel(
                            new Object[][]{
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
                                    {null, null, null, null, null},
                                    {null, null, null, null, null},
                                    {null, null, null, null, null},
                            },
                            new String[]{
                                    "\u8bc4\u8bba\u7f16\u53f7", "\u5546\u54c1\u7f16\u53f7", "\u8bc4\u8bba\u4fe1\u606f", "\u7528\u6237\u7f16\u53f7", "\u53d1\u5e03\u65f6\u95f4"
                            }
                    ));
                    tableMessages.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                    tableMessages.setRowHeight(25);
                    scrollPaneMessages.setViewportView(tableMessages);
                }
                Messages.add(scrollPaneMessages);
                scrollPaneMessages.setBounds(0, 50, 700, 320);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < Messages.getComponentCount(); i++) {
                        Rectangle bounds = Messages.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = Messages.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    Messages.setMinimumSize(preferredSize);
                    Messages.setPreferredSize(preferredSize);
                }
            }
            tabbedPane.addTab("\u8bc4\u8bba\u5217\u8868", Messages);
        }
        contentPane.add(tabbedPane);
        tabbedPane.setBounds(0, 5, 795, 380);

        contentPane.setPreferredSize(new Dimension(800, 440));
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JMenuBar systemMenuBar;
    private JMenu menuUserMenu;
    private JMenuItem menuItemNewUser;
    private JMenuItem menuItemChaXunUser;
    private JMenu menuGooodsMenu;
    private JMenuItem menuItemNewGoods;
    private JMenuItem menuItemChaXunGoods;
    private JMenu menu1;
    private JMenuItem menuItem4;
    private JMenuItem menuItemOrderChaXun;
    private JMenu menu2;
    private JMenuItem menuItemMessages;
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
    private JPanel Goods;
    private JLabel label1;
    private JTextArea txtKeywords1;
    private JButton buttonSS1;
    private JScrollPane scrollPaneGoods;
    private JTable tableGoods;
    private JPanel Users;
    private JLabel label2;
    private JTextArea txtKeywords2;
    private JButton buttonSS2;
    private JScrollPane scrollPaneUsers;
    private JTable tableUsers;
    private JPanel Orders;
    private JLabel label3;
    private JTextArea txtKeywords3;
    private JButton buttonSS3;
    private JScrollPane scrollPaneOrder;
    private JTable tableOrder;
    private JPanel Messages;
    private JLabel label4;
    private JTextArea txtKeywords4;
    private JButton buttonSS4;
    private JScrollPane scrollPaneMessages;
    private JTable tableMessages;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
