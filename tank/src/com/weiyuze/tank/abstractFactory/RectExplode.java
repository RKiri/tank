package com.weiyuze.tank.abstractFactory;

import com.weiyuze.tank.Audio;
import com.weiyuze.tank.ResourceMgr;
import com.weiyuze.tank.TankFrame;

import java.awt.*;

public class RectExplode extends BaseExplode {

    private int x, y;
    //private boolean living = true;
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
    TankFrame tf = null;
    private int step = 0;

    public RectExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    @Override
    public void paint(Graphics g) {

        //g.drawImage(ResourceMgr.explodes[step++], x, y, null);

        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillRect(x,y,step*10,step*10);
        step++;
        if (step >= 10)
            tf.explodes.remove(this);
    }
}
