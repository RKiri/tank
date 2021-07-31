package com.weiyuze.tank.observer;

import com.weiyuze.tank.Tank;

public class TankFireEvent {

    Tank tank;

    public TankFireEvent(Tank tank) {
        this.tank = tank;
    }

    public Tank getSource() {
        return tank;
    }

}
