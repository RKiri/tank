package com.weiyuze.tank.abstractFactory;

import com.weiyuze.tank.Tank;

import java.awt.*;

public abstract class BaseBullet {
    public abstract void collideWith(Tank tank);

    public abstract void paint(Graphics g);
}
