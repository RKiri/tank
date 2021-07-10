package com.weiyuze.tank;

import java.awt.*;

public class Bullet {
    private int x , y ;
    private Dir dir  ;
    private static final int speed = 8;
    private static int WIDTH = 10,HEIGHT = 10;
    TankFrame tf = null;
    private boolean live = true;

    public Bullet(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        if(!live){
            tf.bullets.remove(this);
        }
        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(c);
        move();
    }

    private void move() {
        switch(dir){
            case LEFT:
                x -= speed;
                break;
            case UP:
                y -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case DOWN:
                y += speed;
                break;
        }
        if(x<0||y<0||x>tf.GAME_WIDTH||y>tf.GAME_HEIGHT) live = false;
    }
}
