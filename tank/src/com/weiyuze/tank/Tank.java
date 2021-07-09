package com.weiyuze.tank;

import java.awt.*;

public class Tank {
    private int x = 200, y = 200;
    private Dir dir  = Dir.DOWN;
    private static final int speed = 5;

    private boolean moving = false;
    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }


    public Tank(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Dir getDir() {
        return dir;
    }

    public void paint(Graphics g) {
        g.fillRect(x,y,50,50);
        move();
    }

    private void move() {
        if (!moving)return;
        else {
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
        }
    }
}
