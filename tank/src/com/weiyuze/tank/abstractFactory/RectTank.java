package com.weiyuze.tank.abstractFactory;

import com.weiyuze.tank.*;

import java.awt.*;
import java.util.Random;

public class RectTank extends BaseTank {
    int x, y;
    Dir dir = Dir.DOWN;
    private static final int speed = 2;
    TankFrame tf;
    private boolean moving = true;
    private boolean living = true;
    private Random random = new Random();
    Group group = Group.BAD;
    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();
    public Rectangle rect = new Rectangle();
    FireStrategy fs;

    public RectTank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        if(group==Group.GOOD){
            String goodFSName = (String) PropertyMgr.get("goodFS");
            try {
                fs = (FireStrategy) Class.forName(goodFSName).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            fs = new DefaultFireStrategy();
        }
    }

    public void paint(Graphics g) {
        if (!living) tf.tanks.remove(this);
        Color c = g.getColor();
        g.setColor(group==Group.GOOD?Color.yellow:Color.cyan);
        g.fillRect(x,y,40,40);
        g.setColor(c);
        move();
    }

    private void move() {
        if (!moving) return;
        switch (dir) {
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
        if (this.group == Group.BAD && random.nextInt(100) > 95)
            this.fire();

        if (this.group == Group.BAD && random.nextInt(100) > 95)
            randomDir();

        boundsCheck();

        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if (this.x < 6) x = 6;
        if (this.y < 30) y = 30;
        if (this.x > TankFrame.GAME_WIDTH - RectTank.WIDTH - 8) x = TankFrame.GAME_WIDTH - RectTank.WIDTH - 8;
        if (this.y > TankFrame.GAME_HEIGHT - RectTank.HEIGHT - 8) y = TankFrame.GAME_HEIGHT - RectTank.HEIGHT - 8;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        //fs.fire(this);
        int bX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        Dir[] dirs =Dir.values();
        for(Dir dir:dirs){
            tf.gf.createBullet(bX, bY, dir, group, tf);
        }


        if (group == Group.GOOD) new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }

    public void die() {
        this.living = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isMoving() {
        return moving;
    }

    public Dir getDir() {
        return dir;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }
}
