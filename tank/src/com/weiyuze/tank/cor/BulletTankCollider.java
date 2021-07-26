package com.weiyuze.tank.cor;

import com.weiyuze.tank.Bullet;
import com.weiyuze.tank.GameObject;
import com.weiyuze.tank.Tank;

public class BulletTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Tank){
            Bullet b = (Bullet) o1;
            Tank t = (Tank) o2;
            if (b.collideWith(t)){
                return false;
            }
        }else if (o1 instanceof Tank && o2 instanceof Bullet){
            return collide(o2,o1);
        }
        return true;
    }
}
