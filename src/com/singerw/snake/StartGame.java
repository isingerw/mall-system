package com.singerw.snake;

import javax.swing.*;

/**
 * @Author: CodeSleep
 * @Date: 2021-06-21 21:53
 * @Description: //TODO 贪吃蛇小游戏
 */
public class StartGame {

    public static void start() {
        //1.新建一个窗口
        JFrame frame = new JFrame("贪吃蛇小游戏");
        // 设置窗口的位置和大小
        frame.setBounds(10, 10, 900, 720);
        //窗口大小不可调整,即固定窗口大小
        frame.setResizable(false);
        // 设置关闭事件，游戏可以关闭
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //2.添加我们自己编写的画布背景
        frame.add(new com.singerw.snake.GamePanel());
        //将窗口展示出来
        frame.setVisible(true);
    }

}
