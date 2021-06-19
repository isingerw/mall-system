package com.singerw.ui;

import com.singerw.dao.CartDao;
import com.singerw.dao.GoodsDao;
import com.singerw.dao.OrderDao;
import com.singerw.dao.OrderDetailDao;
import com.singerw.entity.*;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import javax.swing.table.*;

import com.singerw.tools.CommonInfo;

/**
 * @Author: CodeSleep
 * @Date: 2021-06-20 0:49
 * @Description: //TODO 用户UI
 */
public class CustomerMainFrame extends JFrame {
    private static int gid;

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
            gid = Integer.valueOf(tableGoods.getValueAt(tableGoods.rowAtPoint(e.getPoint()), 0) + "");
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
            int state = 1;
            // 构造一个CartEntity对象
            CartEntity cartEntity = new CartEntity(0, gid, gcount, cid, state);
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
     * @Date: 2021-06-20 0:47
     * @Description: //TODO 我的购物车结算按钮监听事件
     */
    private void JieSuanButtonActionPerformed(ActionEvent e) {
        // 调用CarDao的方法
        OrderDao orderDao = new OrderDao();
        UserEntity userEntity = new UserEntity();

        String oid = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + new Random().nextInt(1000);
        int cid = CommonInfo.cid;
        String odate = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        String address = "测试地址";
        double total = Double.parseDouble(labelSum.getText());

        OrderEntity orderEntity = new OrderEntity(oid, cid, odate, address, total);
        // 订单项的集合
        List<OrderDetailEntity> orderDetailEntityList = new ArrayList<OrderDetailEntity>();
        int rowcount = tableCart.getRowCount();
        for (int i = 0; i < rowcount; i++) {
            int gid = Integer.valueOf(tableCart.getValueAt(i, 0) + "");
            int gcount = Integer.valueOf(tableCart.getValueAt(i, 2) + "");
            double gprice = Double.valueOf(tableCart.getValueAt(i, 3) + "");
            double gtotal = Double.valueOf(tableCart.getValueAt(i, 4) + "");
            // 构造一个OrderDetailEntity
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity(0, oid, gid, gcount, gprice, gtotal);
            orderDetailEntityList.add(orderDetailEntity);
        }
        boolean flag = orderDao.addOrder(orderEntity, orderDetailEntityList);
        if (flag) {
            JOptionPane.showMessageDialog(null, "下单成功");
        } else {
            JOptionPane.showMessageDialog(null, "下单失败");
        }
    }


    /**
     * @param e
     * @Author CodeSleep
     * @Date: 2021-06-17 22:02
     * @Description: //TODO 我的订单搜索按钮监听事件
     */
    private void orderSouSuoActionPerformed(ActionEvent e) {
        // 获取用户输入的关键字
        String keywords = txtKeywordsOrders.getText();
        OrderDao orderDao = new OrderDao();
        // 先查
        List<OrderAndUserEntity> list = orderDao.getOrderByLike("%" + keywords + "%");
        // 记录条数
        int size = list.size();
        // 创建保存数据的二维数组
        Object obj[][] = new Object[size][6];
        // 循环
        for (int i = 0; i < size; i++) {
            OrderAndUserEntity g = list.get(i);
            obj[i][0] = g.getOid();
            obj[i][1] = g.getCid();
            obj[i][2] = g.getCname();
            obj[i][3] = g.getOdate();
            obj[i][4] = g.getAddress();
            obj[i][5] = g.getTotal();

        }
        // 设置setModel => new Object[][] =>要展示的数据 new String[]:列名
        tableOrders.setModel(new DefaultTableModel(
                obj,
                new String[]{
                        "\u8ba2\u5355\u7f16\u53f7", "\u7528\u6237\u7f16\u53f7", "\u7528\u6237\u540d", "\u4e0b\u5355\u65f6\u95f4", "\u6536\u8d27\u5730\u5740", "\u603b\u91d1\u989d"
                }));
        // setViewportView 设置table和scrollpane关联
        scrollPaneOrders.setViewportView(tableOrders);
    }

    /**
     * @param e
     * @Author CodeSleep
     * @Date: 2021-06-20 4:35
     * @Description: //TODO 订单详情搜索按钮监听事件
     */
    private void orderSouSouButtonXiangQingActionPerformed(ActionEvent e) {
        // 获取用户输入的关键字
        String keywords = txtKeywordsOrdersXiangQing.getText();
        OrderDetailDao orderDetailDao = new OrderDetailDao();
        // 先查
        List<OrderDetailEntity> list = orderDetailDao.getOrderDetailByLike("%" + keywords + "%");
        // 记录条数
        int size = list.size();
        // 创建保存数据的二维数组
        Object obj[][] = new Object[size][6];
        // 循环
        for (int i = 0; i < size; i++) {
            OrderDetailEntity g = list.get(i);
            obj[i][0] = g.getId();
            obj[i][1] = g.getOid();
            obj[i][2] = g.getGid();
            obj[i][3] = g.getGcount();
            obj[i][4] = g.getGprice();
            obj[i][5] = g.getTotal();

        }
        // 设置setModel => new Object[][] =>要展示的数据 new String[]:列名
        tableOrdersXiangQing.setModel(new DefaultTableModel(
                obj,
                new String[]{
                        "\u7f16\u53f7", "\u8ba2\u5355\u7f16\u53f7", "\u5546\u54c1\u7f16\u53f7", "\u6570\u91cf", "\u5546\u54c1\u4ef7\u683c", "\u603b\u8ba1"
                }));
        // setViewportView 设置table和scrollpane关联
        scrollPaneOrdersXiangQing.setViewportView(tableOrdersXiangQing);
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
        JieSuanButton = new JButton();
        labelSum = new JLabel();
        Orders = new JPanel();
        scrollPaneOrders = new JScrollPane();
        tableOrders = new JTable();
        label2 = new JLabel();
        txtKeywordsOrders = new JTextArea();
        orderSouSouButton = new JButton();
        OrdersXiangQing = new JPanel();
        scrollPaneOrdersXiangQing = new JScrollPane();
        tableOrdersXiangQing = new JTable();
        label4 = new JLabel();
        txtKeywordsOrdersXiangQing = new JTextArea();
        orderSouSouButtonXiangQing = new JButton();
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
                Goods.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0
                        , 0, 0, 0), "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM
                        , new java.awt.Font("Dialo\u0067", java.awt.Font.BOLD, 12), java.awt.Color.red),
                        Goods.getBorder()));
                Goods.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                    @Override
                    public void propertyChange(java.beans.PropertyChangeEvent e
                    ) {
                        if ("borde\u0072".equals(e.getPropertyName())) throw new RuntimeException();
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

                //---- JieSuanButton ----
                JieSuanButton.setText("\u7ed3 \u7b97");
                JieSuanButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                JieSuanButton.addActionListener(e -> JieSuanButtonActionPerformed(e));
                Carts.add(JieSuanButton);
                JieSuanButton.setBounds(new Rectangle(new Point(600, 330), JieSuanButton.getPreferredSize()));

                //---- labelSum ----
                labelSum.setText("\u5408 \u8ba1\uff1a");
                labelSum.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                Carts.add(labelSum);
                labelSum.setBounds(490, 335, 100, labelSum.getPreferredSize().height);

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
                orderSouSouButton.addActionListener(e -> orderSouSuoActionPerformed(e));
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

            //======== OrdersXiangQing ========
            {
                OrdersXiangQing.setLayout(null);

                //======== scrollPaneOrdersXiangQing ========
                {

                    //---- tableOrdersXiangQing ----
                    tableOrdersXiangQing.setModel(new DefaultTableModel(
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
                                    "\u7f16\u53f7", "\u8ba2\u5355\u7f16\u53f7", "\u5546\u54c1\u7f16\u53f7", "\u6570\u91cf", "\u5546\u54c1\u4ef7\u683c", "\u603b\u8ba1"
                            }
                    ));
                    tableOrdersXiangQing.setRowHeight(25);
                    tableOrdersXiangQing.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                    scrollPaneOrdersXiangQing.setViewportView(tableOrdersXiangQing);
                }
                OrdersXiangQing.add(scrollPaneOrdersXiangQing);
                scrollPaneOrdersXiangQing.setBounds(0, 60, 707, 305);

                //---- label4 ----
                label4.setText("\u8bf7\u8f93\u5165\u8981\u67e5\u8be2\u7684\u5173\u952e\u5b57");
                label4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                OrdersXiangQing.add(label4);
                label4.setBounds(280, 25, 160, 20);

                //---- txtKeywordsOrdersXiangQing ----
                txtKeywordsOrdersXiangQing.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
                OrdersXiangQing.add(txtKeywordsOrdersXiangQing);
                txtKeywordsOrdersXiangQing.setBounds(450, 25, 158, 20);

                //---- orderSouSouButtonXiangQing ----
                orderSouSouButtonXiangQing.setText("\u641c \u7d22");
                orderSouSouButtonXiangQing.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
                orderSouSouButtonXiangQing.addActionListener(e -> orderSouSouButtonXiangQingActionPerformed(e));
                OrdersXiangQing.add(orderSouSouButtonXiangQing);
                orderSouSouButtonXiangQing.setBounds(615, 20, 78, 30);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < OrdersXiangQing.getComponentCount(); i++) {
                        Rectangle bounds = OrdersXiangQing.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = OrdersXiangQing.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    OrdersXiangQing.setMinimumSize(preferredSize);
                    OrdersXiangQing.setPreferredSize(preferredSize);
                }
            }
            ordersSouSouButton.addTab("\u8ba2\u5355\u8be6\u60c5", OrdersXiangQing);
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


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
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
    private JButton JieSuanButton;
    private JLabel labelSum;
    private JPanel Orders;
    private JScrollPane scrollPaneOrders;
    private JTable tableOrders;
    private JLabel label2;
    private JTextArea txtKeywordsOrders;
    private JButton orderSouSouButton;
    private JPanel OrdersXiangQing;
    private JScrollPane scrollPaneOrdersXiangQing;
    private JTable tableOrdersXiangQing;
    private JLabel label4;
    private JTextArea txtKeywordsOrdersXiangQing;
    private JButton orderSouSouButtonXiangQing;
    private JPopupMenu popupMenuCart;
    private JMenuItem AddCartMouseRight;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
