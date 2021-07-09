package com.weiyuze.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame Tf = new TankFrame();

        while (true){
            Thread.sleep(50);
            Tf.repaint();
        }
    }
}
