package com.weiyuze.tank;

import com.weiyuze.tank.cor.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    Tank myTank = new Tank(200, 900, Dir.UP, Group.GOOD, this);
//    List<Bullet> bullets = new ArrayList<>();
//    List<Tank> tanks = new ArrayList<>();
//    List<Explode> explodes = new ArrayList<>();

    private List<GameObject> objects = new ArrayList<>();
    ColliderChain chain = new ColliderChain();

    public GameModel(){
        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));

        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            add(new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD, this));
        }
    }

    public void add(GameObject go){
        this.objects.add(go);
    }

    public void remove(GameObject go){
        this.objects.remove(go);
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.white);
//        g.drawString("子弹的数量" + bullets.size(), 10, 60);
//        g.drawString("敌人的数量" + tanks.size(), 10, 80);
//        g.drawString("爆炸的数量" + explodes.size(), 10, 100);
        g.setColor(c);
        myTank.paint(g);
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        //collision detect
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i+1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                chain.collide(o1,o2);
            }
        }


//        for(Iterator<Bullet> it = bullets.iterator();it.hasNext();){
//            Bullet b = it.next();
//            if(!b.live)it.remove();
//        }

//        for(Bullet b : bullets){
//            b.paint(g);
//        }
    }

    public Tank getMainTank() {
        return myTank;
    }
}
