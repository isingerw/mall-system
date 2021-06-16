/*
 * Created by JFormDesigner on Fri Jun 11 15:31:26 CST 2021
 */

package com.singerw.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.singerw.dao.GoodsDao;
import com.singerw.dao.OrderDao;
import com.singerw.dao.UserDao;
import com.singerw.entity.GoodsEntity;
import com.singerw.entity.OrderAndUserEntity;
import com.singerw.entity.UserEntity;
import com.singerw.tools.CommonInfo;

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
        tabbedPane.setSelectedIndex(1);
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
        tabbedPane.setSelectedIndex(0);
        fillUsersTable(scrollPaneUser);
    }

    /**
     * 自动查询并展示用户信息方法
     *
     * @param scrollPaneUser
     */
    private void fillUsersTable(JScrollPane scrollPaneUser) {
        // 获取用户输入的关键字
        String keywords = txtKeywords.getText();
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
        tableUser.setModel(new DefaultTableModel(
                obj,
                new String[]{"\u7528\u6237\u7f16\u53f7", "\u7528\u6237\u540d", "\u5bc6\u7801", "\u624b\u673a\u53f7", "\u5730\u5740", "\u6743\u9650\u72b6\u6001", "\u767b\u5f55\u65f6\u95f4"
                }));
        // setViewportView 设置table和scrollpane关联
        scrollPaneUser.setViewportView(tableUser);
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
        String keywords = txtKeywords.getText();
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
     * @Date: 2021-06-14 22:29
     * @Description: //TODO 搜索按钮监听事件
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
                new String[]{"\u7F16\u53F7", "\u5546\u54C1\u540D\u79F0", "\u5546\u54C1\u4EF7\u683C",
                        "\u5E93\u5B58", "\u72B6\u6001"}));
        // setViewportView 设置table和scrollpane关联
        scrollPaneGoods.setViewportView(tableGoods);
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
        menuGooodsMenu = new JMenu();
        menuItemNewGoods = new JMenuItem();
        menuItemChaXunGoods = new JMenuItem();
        menuUserMenu = new JMenu();
        menuItemNewUser = new JMenuItem();
        menuItemChaXunUser = new JMenuItem();
        menu1 = new JMenu();
        menuItem4 = new JMenuItem();
        menuItemOrderChaXun = new JMenuItem();
        menu2 = new JMenu();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();
        menuNavigation = new JMenu();
        menuItemIndex = new JMenuItem();
        menuItemGoBack = new JMenuItem();
        menuItemExit = new JMenuItem();
        menuHelp = new JMenu();
        menuItemHelp = new JMenuItem();
        menuItemXinShou = new JMenuItem();
        menuItemAbout = new JMenuItem();
        panelChaXun = new JPanel();
        tabbedPane = new JTabbedPane();
        scrollPaneUser = new JScrollPane();
        tableUser = new JTable();
        scrollPaneGoods = new JScrollPane();
        tableGoods = new JTable();
        scrollPaneOrder = new JScrollPane();
        tableOrder = new JTable();
        scrollPaneMessages = new JScrollPane();
        tableMessages = new JTable();
        labelHint = new JLabel();
        txtKeywords = new JTextField();
        fssSouSuo = new JButton();

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
                menuGooodsMenu.setText("\u5546\u54c1\u7ba1\u7406");
                menuGooodsMenu.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 14));

                //---- menuItemNewGoods ----
                menuItemNewGoods.setText("\u65b0\u589e\u5546\u54c1");
                menuItemNewGoods.addActionListener(e -> menuItemNewGoodsActionPerformed(e));
                menuGooodsMenu.add(menuItemNewGoods);

                //---- menuItemChaXunGoods ----
                menuItemChaXunGoods.setText("\u67e5\u8be2\u5546\u54c1");
                menuItemChaXunGoods.addActionListener(e -> menuItemChaXunGoodsActionPerformed(e));
                menuGooodsMenu.add(menuItemChaXunGoods);
            }
            systemMenuBar.add(menuGooodsMenu);

            //======== menuUserMenu ========
            {
                menuUserMenu.setText("\u7528\u6237\u7ba1\u7406");

                //---- menuItemNewUser ----
                menuItemNewUser.setText("\u65b0\u589e\u7528\u6237");
                menuItemNewUser.addActionListener(e -> menuItemNewUserActionPerformed(e));
                menuUserMenu.add(menuItemNewUser);

                //---- menuItemChaXunUser ----
                menuItemChaXunUser.setText("\u67e5\u8be2\u7528\u6237");
                menuItemChaXunUser.addActionListener(e -> menuItemChaXunUserActionPerformed(e));
                menuUserMenu.add(menuItemChaXunUser);
            }
            systemMenuBar.add(menuUserMenu);

            //======== menu1 ========
            {
                menu1.setText("\u8ba2\u5355\u7ba1\u7406");

                //---- menuItem4 ----
                menuItem4.setText("\u8ba2\u5355\u4fee\u6539");
                menu1.add(menuItem4);

                //---- menuItemOrderChaXun ----
                menuItemOrderChaXun.setText("\u8ba2\u5355\u67e5\u8be2");
                menuItemOrderChaXun.addActionListener(e -> menuItemOrderChaXunActionPerformed(e));
                menu1.add(menuItemOrderChaXun);
            }
            systemMenuBar.add(menu1);

            //======== menu2 ========
            {
                menu2.setText("\u7559\u8a00\u7ba1\u7406");

                //---- menuItem2 ----
                menuItem2.setText("\u67e5\u8be2\u7559\u8a00");
                menu2.add(menuItem2);

                //---- menuItem3 ----
                menuItem3.setText("\u5220\u9664\u7559\u8a00");
                menu2.add(menuItem3);
            }
            systemMenuBar.add(menu2);

            //======== menuNavigation ========
            {
                menuNavigation.setText("\u5bfc\u822a");

                //---- menuItemIndex ----
                menuItemIndex.setText("\u8fd4\u56de\u4e3b\u9875");
                menuNavigation.add(menuItemIndex);

                //---- menuItemGoBack ----
                menuItemGoBack.setText("\u8fd4\u56de\u4e0a\u4e00\u7ea7");
                menuItemGoBack.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 14));
                menuItemGoBack.addActionListener(e -> menuItemGoBack(e));
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

        //======== panelChaXun ========
        {
            panelChaXun.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing.
            border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e", javax. swing. border. TitledBorder. CENTER
            , javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069al\u006fg" ,java .awt .Font
            .BOLD ,12 ), java. awt. Color. red) ,panelChaXun. getBorder( )) ); panelChaXun. addPropertyChangeListener (
            new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062or\u0064er"
            .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
            panelChaXun.setLayout(null);

            //======== tabbedPane ========
            {
                tabbedPane.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                tabbedPane.setTabPlacement(SwingConstants.LEFT);

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
                tabbedPane.addTab("\u7528\u6237\u5217\u8868", scrollPaneUser);

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
                            "\u5546\u54c1\u7f16\u53f7", "\u5546\u54c1\u540d\u79f0", "\u4ef7\u683c", "\u5e93\u5b58", "\u8be6\u60c5", "\u5546\u54c1\u72b6\u6001"
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
                            "\u8ba2\u5355\u7f16\u53f7", "\u7528\u6237\u7f16\u53f7", "\u7528\u6237\u540d", "\u4e0b\u5355\u65f6\u95f4", "\u6536\u8d27\u5730\u5740", "\u603b\u91d1\u989d"
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
                            "\u7559\u8a00\u7f16\u53f7", "\u5546\u54c1\u7f16\u53f7", "\u7559\u8a00\u4fe1\u606f", "\u7528\u6237\u7f16\u53f7", "\u53d1\u5e03\u65f6\u95f4"
                        }
                    ));
                    tableMessages.setFont(new Font("Microsoft YaHei UI", tableMessages.getFont().getStyle(), 14));
                    tableMessages.setRowHeight(25);
                    scrollPaneMessages.setViewportView(tableMessages);
                }
                tabbedPane.addTab("\u7559\u8a00\u4fe1\u606f", scrollPaneMessages);
            }
            panelChaXun.add(tabbedPane);
            tabbedPane.setBounds(0, 36, 795, 329);

            //---- labelHint ----
            labelHint.setText("\u8bf7\u8f93\u5165\u5173\u952e\u5b57");
            labelHint.setFont(new Font("\u9ed1\u4f53", labelHint.getFont().getStyle(), 16));
            panelChaXun.add(labelHint);
            labelHint.setBounds(385, 5, labelHint.getPreferredSize().width, 25);

            //---- txtKeywords ----
            txtKeywords.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panelChaXun.add(txtKeywords);
            txtKeywords.setBounds(490, 0, 160, 34);

            //---- fssSouSuo ----
            fssSouSuo.setText("\u641c \u7d22");
            fssSouSuo.setFont(new Font("\u9ed1\u4f53", fssSouSuo.getFont().getStyle(), 16));
            fssSouSuo.addActionListener(e -> fssSouSuoActionPerformed(e));
            panelChaXun.add(fssSouSuo);
            fssSouSuo.setBounds(655, 0, 85, 30);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panelChaXun.getComponentCount(); i++) {
                    Rectangle bounds = panelChaXun.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panelChaXun.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panelChaXun.setMinimumSize(preferredSize);
                panelChaXun.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panelChaXun);
        panelChaXun.setBounds(0, 0, 785, 385);

        contentPane.setPreferredSize(new Dimension(795, 440));
        pack();
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JMenuBar systemMenuBar;
    private JMenu menuGooodsMenu;
    private JMenuItem menuItemNewGoods;
    private JMenuItem menuItemChaXunGoods;
    private JMenu menuUserMenu;
    private JMenuItem menuItemNewUser;
    private JMenuItem menuItemChaXunUser;
    private JMenu menu1;
    private JMenuItem menuItem4;
    private JMenuItem menuItemOrderChaXun;
    private JMenu menu2;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JMenu menuNavigation;
    private JMenuItem menuItemIndex;
    private JMenuItem menuItemGoBack;
    private JMenuItem menuItemExit;
    private JMenu menuHelp;
    private JMenuItem menuItemHelp;
    private JMenuItem menuItemXinShou;
    private JMenuItem menuItemAbout;
    private JPanel panelChaXun;
    private JTabbedPane tabbedPane;
    private JScrollPane scrollPaneUser;
    private JTable tableUser;
    private JScrollPane scrollPaneGoods;
    private JTable tableGoods;
    private JScrollPane scrollPaneOrder;
    private JTable tableOrder;
    private JScrollPane scrollPaneMessages;
    private JTable tableMessages;
    private JLabel labelHint;
    private JTextField txtKeywords;
    private JButton fssSouSuo;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
