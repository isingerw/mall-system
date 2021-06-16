/*
 * Created by JFormDesigner on Tue Jun 15 11:09:32 CST 2021
 */

package com.singerw.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.singerw.dao.GoodsDao;
import com.singerw.entity.GoodsEntity;

/**
 * @author unknown
 */
public class AddGoodsFrame extends JFrame {
    public AddGoodsFrame() {
        initComponents();
    }



    /**
     * @Author CodeSleep
     * @Date: 2021-06-15 19:52
     * @Description: //TODO 退出按钮监听事件
     * @param e
     */
    private void ExitButtonActionPerformed(ActionEvent e) {
        // 提示用户是否退出
        int n = JOptionPane.showConfirmDialog(null, "是否退出", "提示信息", JOptionPane.YES_OPTION);
        if (n == 0) {
            // 如果用户选择退出并确定
            dispose();
        }
    }

    /**
     * @Author CodeSleep
     * @Date: 2021-06-15 20:17
     * @Description: //TODO 新增商品按钮监听事件
     * @param e
     */
    private void AddGoodsButtonActionPerformed(ActionEvent e) {
        // 获取商品名称、价格、库存等信息
        String gname = txtGname.getText();
        double gprice = Double.parseDouble(txtGprice.getText());
        int gstock = Integer.parseInt(txtGstock.getText());
        String ginfo = textAreaGinfo.getText();
        // 前端校验

        // 上架 1
        int gstate = 1;
        if (checkBoxXiaJia.isSelected()){
            // 下架 0
            gstate = 0;
        }
        GoodsDao goodsDao = new GoodsDao();
        GoodsEntity goodsEntity = new GoodsEntity(gname,gprice,gstock,ginfo,gstate);
        boolean flag = goodsDao.addGoods(goodsEntity);
        if (flag){
            JOptionPane.showMessageDialog(null,"添加成功");
        }else {
            JOptionPane.showMessageDialog(null,"添加失败");
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        title1 = compFactory.createTitle("text");
        txtGname = new JFormattedTextField();
        titleID = compFactory.createTitle("\u5546\u54c1\u540d\u79f0\uff1a");
        titlepAssword = compFactory.createTitle("\u5546\u54c1\u4ef7\u683c\uff1a");
        AddGoodsButton = new JButton();
        ExitButton = new JButton();
        titlelogin = compFactory.createTitle("\u589e \u52a0 \u5546 \u54c1");
        titlePhone = compFactory.createTitle("\u5546\u54c1\u5e93\u5b58\uff1a");
        titleAdress = compFactory.createTitle("\u5546\u54c1\u72b6\u6001\uff1a");
        txtGstock = new JFormattedTextField();
        titleAdress2 = compFactory.createTitle("\u5546\u54c1\u7b80\u4ecb\uff1a");
        scrollPane1 = new JScrollPane();
        textAreaGinfo = new JTextArea();
        checkBoxShangJia = new JCheckBox();
        checkBoxXiaJia = new JCheckBox();
        txtGprice = new JFormattedTextField();

        //======== this ========
        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/com/singerw/ui/img/icoimage.png")).getImage());
        setTitle("\u6dfb\u52a0\u5546\u54c1");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(title1);
        title1.setBounds(new Rectangle(new Point(30, -20), title1.getPreferredSize()));
        contentPane.add(txtGname);
        txtGname.setBounds(140, 80, 310, 35);

        //---- titleID ----
        titleID.setFont(titleID.getFont().deriveFont(titleID.getFont().getSize() + 3f));
        contentPane.add(titleID);
        titleID.setBounds(56, 89, 94, 20);

        //---- titlepAssword ----
        titlepAssword.setFont(titlepAssword.getFont().deriveFont(titlepAssword.getFont().getSize() + 3f));
        contentPane.add(titlepAssword);
        titlepAssword.setBounds(56, 134, 89, 20);

        //---- AddGoodsButton ----
        AddGoodsButton.setText("\u65b0 \u589e");
        AddGoodsButton.setFont(new Font("\u9ed1\u4f53", AddGoodsButton.getFont().getStyle(), 16));
        AddGoodsButton.addActionListener(e -> AddGoodsButtonActionPerformed(e));
        contentPane.add(AddGoodsButton);
        AddGoodsButton.setBounds(90, 385, 175, 35);

        //---- ExitButton ----
        ExitButton.setText("\u9000 \u51fa");
        ExitButton.setFont(new Font("\u9ed1\u4f53", Font.PLAIN, 16));
        ExitButton.addActionListener(e -> ExitButtonActionPerformed(e));
        contentPane.add(ExitButton);
        ExitButton.setBounds(290, 385, 175, 35);

        //---- titlelogin ----
        titlelogin.setFont(titlelogin.getFont().deriveFont(titlelogin.getFont().getSize() + 8f));
        contentPane.add(titlelogin);
        titlelogin.setBounds(245, 25, 115, 30);

        //---- titlePhone ----
        titlePhone.setFont(titlePhone.getFont().deriveFont(titlePhone.getFont().getSize() + 3f));
        contentPane.add(titlePhone);
        titlePhone.setBounds(56, 185, 84, 20);

        //---- titleAdress ----
        titleAdress.setFont(titleAdress.getFont().deriveFont(titleAdress.getFont().getSize() + 3f));
        contentPane.add(titleAdress);
        titleAdress.setBounds(56, 235, 84, 20);
        contentPane.add(txtGstock);
        txtGstock.setBounds(140, 180, 310, 35);

        //---- titleAdress2 ----
        titleAdress2.setFont(titleAdress2.getFont().deriveFont(titleAdress2.getFont().getSize() + 3f));
        contentPane.add(titleAdress2);
        titleAdress2.setBounds(55, 280, 84, 20);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(textAreaGinfo);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(145, 280, 300, 85);

        //---- checkBoxShangJia ----
        checkBoxShangJia.setText("\u4e0a\u67b6");
        contentPane.add(checkBoxShangJia);
        checkBoxShangJia.setBounds(215, 235, 60, 30);

        //---- checkBoxXiaJia ----
        checkBoxXiaJia.setText("\u4e0b\u67b6");
        contentPane.add(checkBoxXiaJia);
        checkBoxXiaJia.setBounds(305, 235, 60, 30);
        contentPane.add(txtGprice);
        txtGprice.setBounds(140, 130, 310, 35);

        contentPane.setPreferredSize(new Dimension(550, 485));
        pack();
        setLocationRelativeTo(null);

        //---- buttonGroupGstate ----
        ButtonGroup buttonGroupGstate = new ButtonGroup();
        buttonGroupGstate.add(checkBoxShangJia);
        buttonGroupGstate.add(checkBoxXiaJia);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel title1;
    private JFormattedTextField txtGname;
    private JLabel titleID;
    private JLabel titlepAssword;
    private JButton AddGoodsButton;
    private JButton ExitButton;
    private JLabel titlelogin;
    private JLabel titlePhone;
    private JLabel titleAdress;
    private JFormattedTextField txtGstock;
    private JLabel titleAdress2;
    private JScrollPane scrollPane1;
    private JTextArea textAreaGinfo;
    private JCheckBox checkBoxShangJia;
    private JCheckBox checkBoxXiaJia;
    private JFormattedTextField txtGprice;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
