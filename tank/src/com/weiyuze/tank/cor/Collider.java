package com.weiyuze.tank.cor;

import com.weiyuze.tank.GameObject;

public interface Collider {
    boolean collide(GameObject o1,GameObject o2);
}
