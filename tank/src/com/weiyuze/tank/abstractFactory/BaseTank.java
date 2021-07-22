package com.weiyuze.tank.abstractFactory;

import com.weiyuze.tank.Group;

import java.awt.*;

public abstract class BaseTank {
    public Group group = Group.BAD;
    public Rectangle rect = new Rectangle();
    public void paint(Graphics g) {
    }

    public Group getGroup(){
        return this.group;
    }
    public abstract void die();

    public abstract int getX();

    public abstract int getY();


}
