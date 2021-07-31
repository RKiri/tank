package com.weiyuze.tank.observer;

import com.weiyuze.tank.Tank;

public class TankFireHandler implements TankFireObserver {
    @Override
    public void actionOnFire(TankFireEvent event) {
        Tank tank = event.getSource();
        tank.fire();
    }
}
