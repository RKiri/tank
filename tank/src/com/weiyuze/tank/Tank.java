package com.weiyuze.tank;

import com.weiyuze.tank.decorator.RectDecorator;
import com.weiyuze.tank.decorator.TailDecorator;
import com.weiyuze.tank.observer.TankFireEvent;
import com.weiyuze.tank.observer.TankFireHandler;
import com.weiyuze.tank.observer.TankFireObserver;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Tank extends GameObject {
    int oldX, oldY;
    public Dir dir = Dir.DOWN;
    private static final int speed = 2;
    private boolean moving = true;
    private boolean living = true;
    private Random random = new Random();
    public Group group = Group.BAD;
    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();
    public Rectangle rect = new Rectangle();

    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        GameModel.getInstance().add(this);
    }

    public void paint(Graphics g) {
        if (!living) GameModel.getInstance().remove(this);
        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
        }
        move();
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    private void move() {
        //记录移动之前的位置
        oldX = x;
        oldY = y;
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
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 8) x = TankFrame.GAME_WIDTH - Tank.WIDTH - 8;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 8) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 8;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        int bX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
//        GameModel.getInstance().add(
//              new TailDecorator(
//                new RectDecorator(
//                        new Bullet(bX, bY, this.dir, this.group))));
        new Bullet(bX, bY, this.dir, this.group);

        if (this.group == Group.GOOD) new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }

    List<TankFireObserver> observers = Arrays.asList(new TankFireHandler());
    public void handleFireKey() {
        TankFireEvent event = new TankFireEvent(this);
        for(TankFireObserver o:observers){
            o.actionOnFire(event);
        }
    }

    public void stop() {
        moving = false;
    }

    public void back() {
        x = oldX;
        y = oldY;
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

    public Rectangle getRect() {
        return rect;
    }

}
