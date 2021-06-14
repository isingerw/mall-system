/*
 * Created by JFormDesigner on Fri Jun 11 15:31:26 CST 2021
 */

package com.singerw.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.*;
import com.jgoodies.forms.factories.*;
import com.singerw.dao.GoodsDao;
import com.singerw.entity.GoodsEntity;
import java.awt.event.ActionListener;
import java.util.List;

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



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        scrollPane = new JScrollPane();
        tableGoods = new JTable();
        fssButton = new JButton();
        scrollPane1 = new JScrollPane();
        txtKeywords = new JTextArea();
        titleKeywords = compFactory.createTitle("\u8bf7\u8f93\u5165\u5173\u952e\u5b57");
        // 设置绝对布局
        setLayout(null);
        // 居中显示
        setLocationRelativeTo(null);
        // 窗口关闭 应用程序本身行为
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //======== this ========
        setTitle("\u8d85\u5e02\u7ba1\u7406\u7cfb\u7edf");
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/com/singerw/ui/img/icoimage.png")).getImage());
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane ========
        {

            //---- tableGoods ----
            tableGoods.setModel(new DefaultTableModel(
                new Object[][] {
                    {"", "", "", null, null},
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
                new String[] {
                    "\u7f16\u53f7", "\u5546\u54c1\u540d\u79f0", "\u4ef7\u683c", "\u4ef7\u683c", "\u5e93\u5b58"
                }
            ));
            tableGoods.setFont(tableGoods.getFont().deriveFont(tableGoods.getFont().getSize() + 2f));
            scrollPane.setViewportView(tableGoods);
        }
        contentPane.add(scrollPane);
        scrollPane.setBounds(10, 65, 760, 315);

        //---- fssButton ----
        fssButton.setText("\u641c\u7d22");
        fssButton.setFont(fssButton.getFont().deriveFont(fssButton.getFont().getSize() + 2f));
        contentPane.add(fssButton);
        fssButton.setBounds(695, 15, fssButton.getPreferredSize().width, 35);

        fssButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 搜索按钮的单击事件

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
                scrollPane.setViewportView(tableGoods);
            }
        });


        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(txtKeywords);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(530, 20, 160, 25);

        //---- titleKeywords ----
        titleKeywords.setFont(titleKeywords.getFont().deriveFont(titleKeywords.getFont().getSize() + 2f));
        contentPane.add(titleKeywords);
        titleKeywords.setBounds(430, 20, titleKeywords.getPreferredSize().width, 25);

        contentPane.setPreferredSize(new Dimension(785, 445));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JScrollPane scrollPane;
    private JTable tableGoods;
    private JButton fssButton;
    private JScrollPane scrollPane1;
    private JTextArea txtKeywords;
    private JLabel titleKeywords;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}




//fssButton.addActionListener(new ActionListener() {
//@Override
//public void actionPerformed(ActionEvent e) {
//        // 搜索按钮的单击事件
//
//        // 获取用户输入的关键字
//        String keywords = txtKeywords.getText();
//
//        GoodsDao gdao = new GoodsDao();
//
//        // 先查
//        List<GoodsEntity> list = gdao.getGoodsByLike("%" + keywords + "%");
//
//        // 记录条数
//        int size = list.size();
//        // 创建保存数据的二维数组
//        Object obj[][] = new Object[size][5];
//
//        // 循环
//        for (int i = 0; i < size; i++) {
//        GoodsEntity g = list.get(i);
//        obj[i][0] = g.getGid();
//        obj[i][1] = g.getGname();
//        obj[i][2] = g.getGprice();
//        obj[i][3] = g.getGstock();
//        obj[i][4] = g.getGstate() == 1 ? "上架" : "下架";
//        }
//
//        // 设置setModel => new Object[][] =>要展示的数据 new String[]:列名
//        tableGoods.setModel(new DefaultTableModel(
//        obj,
//        new String[] { "\u7F16\u53F7", "\u5546\u54C1\u540D\u79F0", "\u5546\u54C1\u4EF7\u683C",
//        "\u5E93\u5B58", "\u72B6\u6001" }));
//        // setViewportView 设置table和scrollpane关联
//        scrollPane.setViewportView(tableGoods);
//        }
//        });