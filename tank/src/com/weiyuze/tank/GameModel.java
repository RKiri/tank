package com.weiyuze.tank;

import com.weiyuze.tank.cor.ColliderChain;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    private static final GameModel INSTANCE = new GameModel();

    //    List<Bullet> bullets = new ArrayList<>();
//    List<Tank> tanks = new ArrayList<>();
//    List<Explode> explodes = new ArrayList<>();
    static {
        INSTANCE.init();
    }

    Tank myTank;
    private List<GameObject> objects = new ArrayList<>();
    ColliderChain chain = new ColliderChain();

    public static GameModel getInstance() {
        return INSTANCE;
    }

    public GameModel() {
    }

    public void init() {
        //初始化主战坦克
        myTank = new Tank(200, 900, Dir.UP, Group.GOOD);

        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            new Tank(50 + i * 80, 200, Dir.DOWN, Group.BAD);
        }

        add(new Wall(150, 150, 200, 50));
        add(new Wall(700, 150, 200, 50));
        add(new Wall(300, 500, 50, 200));
        add(new Wall(700, 500, 50, 200));
    }

    public void add(GameObject go) {
        this.objects.add(go);
    }

    public void remove(GameObject go) {
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
            for (int j = i + 1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                chain.collide(o1, o2);
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

    public void save() {
        File f = new File("f:/Work/tank.data");
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(myTank);
            oos.writeObject(objects);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load() {
        File f = new File("f:/Work/tank.data");
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            myTank = (Tank) ois.readObject();
            objects = (List<GameObject>) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
