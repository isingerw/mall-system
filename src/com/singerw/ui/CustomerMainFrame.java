/*
 * Created by JFormDesigner on Tue Jun 15 11:20:57 CST 2021
 */

package com.singerw.ui;

import com.singerw.dao.CartDao;
import com.singerw.dao.GoodsDao;
import com.singerw.dao.OrderDao;
import com.singerw.entity.CartBeanEntity;
import com.singerw.entity.CartEntity;
import com.singerw.entity.GoodsEntity;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import com.singerw.tools.CommonInfo;
import info.clearthought.layout.*;
import net.miginfocom.swing.*;
import sun.util.resources.LocaleData;

/**
 * @author unknown
 */
public class CustomerMainFrame extends JFrame {
    private static int gid;


//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    CustomerMainFrame customerMainFrame = new CustomerMainFrame();
//                    customerMainFrame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    public CustomerMainFrame() {
        initComponents();
    }

    /**
     * @param e
     * @Author CodeSleep
     * @Date: 2021-06-17 21:45
     * @Description: //TODO 商品列表鼠标右键点击事件,调用AddCartMouseRightClicked方法
     */
    private void tableGoodsMouseRightClicked(MouseEvent e) {
        AddCartMouseRightClicked(e);
    }

    /**
     * @param e
     * @Author CodeSleep
     * @Date: 2021-06-17 21:42
     * @Description: //TODO 鼠标右键点击事件，将所选商品通过右击添加到购物车中
     */
    private void AddCartMouseRightClicked(MouseEvent e) {
        //判断是否为鼠标的BUTTON3按钮，BUTTON3为鼠标右键
        if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
            //通过点击位置找到点击为表格中的行
            int focusedRowIndex = tableGoods.rowAtPoint(e.getPoint());
            gid =Integer.valueOf(tableGoods.getValueAt(tableGoods.rowAtPoint(e.getPoint()), 0) + "");
            System.out.println("gid：" + gid);
            if (focusedRowIndex == -1) {
                return;
            }
            //将表格所选项设为当前右键点击的行
            tableGoods.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
            //弹出菜单
            popupMenuCart.show(tableGoods, e.getX(), e.getY());


            // 将商品添加到购物车
            int gcount = 1;
            // 登录的用户信息 ==> 通用类中读取的静态属性，登录时候存储
            int cid = CommonInfo.cid;
            // 构造一个CartEntity对象
            CartEntity cartEntity = new CartEntity(0, gid, gcount, cid);
            CartDao cartDao = new CartDao();
            // 调用cartDao中添加到购物车的addCart方法
            boolean flag = cartDao.addCart(cartEntity);
            if (flag) {
                JOptionPane.showMessageDialog(null, "添加成功");
            } else {
                JOptionPane.showMessageDialog(null, "添加失败");
            }
        }
    }


    /**
     * @param e
     * @Author CodeSleep
     * @Date: 2021-06-17 22:01
     * @Description: //TODO 商品列表搜索按钮监听事件
     */
    private void goodsSouSouButtonActionPerformed(ActionEvent e) {
        // 获取用户输入的关键字
        String keywords = txtKeywordsGoods.getText();
        GoodsDao goodsDao = new GoodsDao();
        // 先查
        List<GoodsEntity> list = goodsDao.getGoodsByLikeAndState("%" + keywords + "%");
        // 记录条数
        int size = list.size();
        // 创建保存数据的二维数组
        Object obj[][] = new Object[size][4];

        // 循环
        for (int i = 0; i < size; i++) {
            GoodsEntity g = list.get(i);
            obj[i][0] = g.getGid();
            obj[i][1] = g.getGname();
            obj[i][2] = g.getGprice();
            obj[i][3] = g.getGstock();
        }

        // 设置setModel => new Object[][] =>要展示的数据 new String[]:列名
        tableGoods.setModel(new DefaultTableModel(
                obj,
                new String[]{
                        "\u5546\u54c1\u7f16\u53f7", "\u5546\u54c1\u540d\u79f0", "\u5546\u54c1\u4ef7\u683c", "\u5e93\u5b58"
                }));
        // setViewportView 设置table和scrollpane关联
        scrollPaneGoods.setViewportView(tableGoods);
    }


    /**
     * @param e
     * @Author CodeSleep
     * @Date: 2021-06-17 22:02
     * @Description: //TODO 购物车搜索按钮监听事件
     */
    private void cartSouSouButtonActionPerformed(ActionEvent e) {
        // 查询数据填充到jtable中来
        CartDao cartDao = new CartDao();

        // 传递cid
        List<CartBeanEntity> list = cartDao.getCartBycid(CommonInfo.cid);

        // 构造Object [][]

        // 记录条数
        int size = list.size();
        // 创建保存数据的二维数组
        Object obj[][] = new Object[size][5];

        double sum = 0;

        // 循环
        for (int i = 0; i < size; i++) {
            CartBeanEntity g = list.get(i);
            obj[i][0] = g.getGid();
            obj[i][1] = g.getGname();
            obj[i][3] = g.getGprice();
            obj[i][2] = g.getGcount();

            obj[i][4] = g.getGcount() * g.getGprice();
            sum += g.getGcount() * g.getGprice();

        }

        labelSum.setText(sum + "");
        // 设置setModel => new Object[][] =>要展示的数据 new String[]:列名
        tableCart.setModel(new DefaultTableModel(obj, new String[]{
                "\u5546\u54c1\u7f16\u53f7", "\u5546\u54c1\u540d\u79f0", "\u6570\u91cf", "\u5546\u54c1\u5355\u4ef7", "\u603b\u8ba1"
        }));
        // setViewportView 设置table和scrollpane关联
        scrollPaneCart.setViewportView(tableCart);

    }


    /**
     * @param e
     * @Author CodeSleep
     * @Date: 2021-06-17 22:02
     * @Description: //TODO 我的订单搜索按钮监听事件
     */
    private void buttonSS2ActionPerformed(ActionEvent e) {

    }


    /**
     * @param e
     * @Author CodeSleep
     * @Date: 2021-06-15 15:30
     * @Description: //TODO 菜单栏Exit退出系统按键监听事件
     */
    private void menuItemExit(ActionEvent e) {
        System.exit(0);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
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
        ordersSouSouButton = new JTabbedPane();
        Goods = new JPanel();
        scrollPaneGoods = new JScrollPane();
        tableGoods = new JTable();
        label1 = new JLabel();
        txtKeywordsGoods = new JTextArea();
        goodsSouSouButton = new JButton();
        Carts = new JPanel();
        scrollPaneCart = new JScrollPane();
        tableCart = new JTable();
        label3 = new JLabel();
        txtKeywordsCart = new JTextArea();
        cartSouSouButton = new JButton();
        button1 = new JButton();
        labelSum = new JLabel();
        Orders = new JPanel();
        scrollPaneOrders = new JScrollPane();
        tableOrders = new JTable();
        label2 = new JLabel();
        txtKeywordsOrders = new JTextArea();
        orderSouSouButton = new JButton();
        popupMenuCart = new JPopupMenu();
        AddCartMouseRight = new JMenuItem();

        //======== this ========
        setTitle("\u7528\u6237\u4e2a\u4eba\u4e2d\u5fc3");
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/com/singerw/ui/img/icoimage.png")).getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== systemMenuBar ========
        {

            //======== menuGooodsMenu ========
            {
                menuGooodsMenu.setText("\u4e2a\u4eba\u4e2d\u5fc3");
                menuGooodsMenu.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 16));

                //---- menuItem1 ----
                menuItem1.setText("\u6211\u7684\u8d2d\u7269\u8f66");
                menuItem1.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 16));
                menuGooodsMenu.add(menuItem1);

                //---- menuItem2 ----
                menuItem2.setText("\u6211\u7684\u8ba2\u5355");
                menuItem2.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 16));
                menuGooodsMenu.add(menuItem2);
            }
            systemMenuBar.add(menuGooodsMenu);

            //======== menuUserMenu ========
            {
                menuUserMenu.setText("\u8bbe\u7f6e");
                menuUserMenu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));

                //---- menuItem4 ----
                menuItem4.setText("\u4fee\u6539\u4fe1\u606f");
                menuItem4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                menuUserMenu.add(menuItem4);

                //---- menuItem3 ----
                menuItem3.setText("\u4fee\u6539\u5bc6\u7801");
                menuItem3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                menuUserMenu.add(menuItem3);
            }
            systemMenuBar.add(menuUserMenu);

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
                menuNavigation.add(menuItemGoBack);

                //---- menuItemExit ----
                menuItemExit.setText("\u9000\u51fa\u7cfb\u7edf");
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
                menuItemHelp.setText("\u7528\u6237\u624b\u518c");
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

        //======== ordersSouSouButton ========
        {
            ordersSouSouButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
            ordersSouSouButton.setTabPlacement(SwingConstants.LEFT);

            //======== Goods ========
            {
                Goods.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(
                        new javax.swing.border.EmptyBorder(0, 0, 0, 0), "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e"
                        , javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM
                        , new java.awt.Font("D\u0069al\u006fg", java.awt.Font.BOLD, 12)
                        , java.awt.Color.red), Goods.getBorder()));
                Goods.addPropertyChangeListener(
                        new java.beans.PropertyChangeListener() {
                            @Override
                            public void propertyChange(java.beans.PropertyChangeEvent e
                            ) {
                                if ("\u0062or\u0064er".equals(e.getPropertyName())) {
                                    throw new RuntimeException()
                                            ;
                                }
                            }
                        });
                Goods.setLayout(null);

                //======== scrollPaneGoods ========
                {

                    //---- tableGoods ----
                    tableGoods.setModel(new DefaultTableModel(
                            new Object[][]{
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
                                    {null, null, null, null},
                                    {null, null, null, null},
                                    {null, null, null, null},
                                    {null, null, null, null},
                                    {null, null, null, null},
                            },
                            new String[]{
                                    "\u5546\u54c1\u7f16\u53f7", "\u5546\u54c1\u540d\u79f0", "\u5546\u54c1\u4ef7\u683c", "\u5e93\u5b58"
                            }
                    ));
                    tableGoods.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                    tableGoods.setRowHeight(25);
                    tableGoods.setAutoCreateColumnsFromModel(false);
                    tableGoods.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            tableGoodsMouseRightClicked(e);
                        }
                    });
                    scrollPaneGoods.setViewportView(tableGoods);
                }
                Goods.add(scrollPaneGoods);
                scrollPaneGoods.setBounds(0, 60, 705, 305);

                //---- label1 ----
                label1.setText("\u8bf7\u8f93\u5165\u8981\u67e5\u8be2\u7684\u5173\u952e\u5b57");
                label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                Goods.add(label1);
                label1.setBounds(280, 25, 160, 20);

                //---- txtKeywordsGoods ----
                txtKeywordsGoods.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
                Goods.add(txtKeywordsGoods);
                txtKeywordsGoods.setBounds(450, 25, 158, 20);

                //---- goodsSouSouButton ----
                goodsSouSouButton.setText("\u641c \u7d22");
                goodsSouSouButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                goodsSouSouButton.addActionListener(e -> goodsSouSouButtonActionPerformed(e));
                Goods.add(goodsSouSouButton);
                goodsSouSouButton.setBounds(615, 20, 78, 30);

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
            ordersSouSouButton.addTab("\u5546\u54c1\u5217\u8868", Goods);

            //======== Carts ========
            {
                Carts.setLayout(null);

                //======== scrollPaneCart ========
                {

                    //---- tableCart ----
                    tableCart.setModel(new DefaultTableModel(
                            new Object[][]{
                                    {null, null, null, null, ""},
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
                                    {null, null, null, null, null},
                            },
                            new String[]{
                                    "\u5546\u54c1\u7f16\u53f7", "\u5546\u54c1\u540d\u79f0", "\u6570\u91cf", "\u5546\u54c1\u5355\u4ef7", "\u603b\u8ba1"
                            }
                    ));
                    tableCart.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                    tableCart.setRowHeight(25);
                    tableCart.setPreferredScrollableViewportSize(new Dimension(450, 350));
                    scrollPaneCart.setViewportView(tableCart);
                }
                Carts.add(scrollPaneCart);
                scrollPaneCart.setBounds(0, 60, 707, 260);

                //---- label3 ----
                label3.setText("\u8bf7\u8f93\u5165\u8981\u67e5\u8be2\u7684\u5173\u952e\u5b57");
                label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                Carts.add(label3);
                label3.setBounds(280, 25, 160, 20);

                //---- txtKeywordsCart ----
                txtKeywordsCart.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
                Carts.add(txtKeywordsCart);
                txtKeywordsCart.setBounds(450, 25, 158, 20);

                //---- cartSouSouButton ----
                cartSouSouButton.setText("\u641c \u7d22");
                cartSouSouButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                cartSouSouButton.addActionListener(e -> cartSouSouButtonActionPerformed(e));
                Carts.add(cartSouSouButton);
                cartSouSouButton.setBounds(615, 20, 78, 30);

                //---- button1 ----
                button1.setText("\u7ed3 \u7b97");
                button1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                Carts.add(button1);
                button1.setBounds(new Rectangle(new Point(600, 336), button1.getPreferredSize()));

                //---- labelSum ----
                labelSum.setText("\u5408 \u8ba1\uff1a");
                labelSum.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                Carts.add(labelSum);
                labelSum.setBounds(410, 341, 185, labelSum.getPreferredSize().height);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < Carts.getComponentCount(); i++) {
                        Rectangle bounds = Carts.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = Carts.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    Carts.setMinimumSize(preferredSize);
                    Carts.setPreferredSize(preferredSize);
                }
            }
            ordersSouSouButton.addTab("\u6211\u7684\u8d2d\u7269\u8f66", Carts);

            //======== Orders ========
            {
                Orders.setLayout(null);

                //======== scrollPaneOrders ========
                {

                    //---- tableOrders ----
                    tableOrders.setModel(new DefaultTableModel(
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
                                    {null, null, null, null, null, null},
                                    {null, null, null, null, null, null},
                            },
                            new String[]{
                                    "\u8ba2\u5355\u7f16\u53f7", "\u7528\u6237\u7f16\u53f7", "\u7528\u6237\u540d", "\u4e0b\u5355\u65f6\u95f4", "\u6536\u8d27\u5730\u5740", "\u603b\u91d1\u989d"
                            }
                    ));
                    tableOrders.setRowHeight(25);
                    tableOrders.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                    scrollPaneOrders.setViewportView(tableOrders);
                }
                Orders.add(scrollPaneOrders);
                scrollPaneOrders.setBounds(0, 60, 707, 305);

                //---- label2 ----
                label2.setText("\u8bf7\u8f93\u5165\u8981\u67e5\u8be2\u7684\u5173\u952e\u5b57");
                label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                Orders.add(label2);
                label2.setBounds(280, 25, 160, 20);

                //---- txtKeywordsOrders ----
                txtKeywordsOrders.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
                Orders.add(txtKeywordsOrders);
                txtKeywordsOrders.setBounds(450, 25, 158, 20);

                //---- orderSouSouButton ----
                orderSouSouButton.setText("\u641c \u7d22");
                orderSouSouButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                orderSouSouButton.addActionListener(e -> buttonSS2ActionPerformed(e));
                Orders.add(orderSouSouButton);
                orderSouSouButton.setBounds(615, 20, 78, 30);

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
            ordersSouSouButton.addTab("\u6211\u7684\u8ba2\u5355", Orders);
        }
        contentPane.add(ordersSouSouButton);
        ordersSouSouButton.setBounds(5, 10, 815, 395);

        contentPane.setPreferredSize(new Dimension(825, 440));
        pack();
        setLocationRelativeTo(null);

        //======== popupMenuCart ========
        {

            //---- AddCartMouseRight ----
            AddCartMouseRight.setText("\u52a0\u5165\u8d2d\u7269\u8f66");
            AddCartMouseRight.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    AddCartMouseRightClicked(e);
                }
            });
            popupMenuCart.add(AddCartMouseRight);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


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
    private JTabbedPane ordersSouSouButton;
    private JPanel Goods;
    private JScrollPane scrollPaneGoods;
    private JTable tableGoods;
    private JLabel label1;
    private JTextArea txtKeywordsGoods;
    private JButton goodsSouSouButton;
    private JPanel Carts;
    private JScrollPane scrollPaneCart;
    private JTable tableCart;
    private JLabel label3;
    private JTextArea txtKeywordsCart;
    private JButton cartSouSouButton;
    private JButton button1;
    private JLabel labelSum;
    private JPanel Orders;
    private JScrollPane scrollPaneOrders;
    private JTable tableOrders;
    private JLabel label2;
    private JTextArea txtKeywordsOrders;
    private JButton orderSouSouButton;
    private JPopupMenu popupMenuCart;
    private JMenuItem AddCartMouseRight;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
