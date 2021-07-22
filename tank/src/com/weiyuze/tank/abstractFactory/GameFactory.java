package com.weiyuze.tank.abstractFactory;

import com.weiyuze.tank.Dir;
import com.weiyuze.tank.Group;
import com.weiyuze.tank.TankFrame;

public abstract class GameFactory {
    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf);
    public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf);
    public abstract BaseExplode createExplode(int x, int y, TankFrame tf);
}
