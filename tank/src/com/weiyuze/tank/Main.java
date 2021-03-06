package com.weiyuze.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));

        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            tf.tanks.add(tf.gf.createTank(50 + i * 80, 200, Dir.DOWN, Group.BAD, tf));
        }

        //new Thread(()->new Audio("audio/war1.wav").loop()).start();

        while (true) {
            Thread.sleep(25);
            tf.repaint();
        }
    }
}
