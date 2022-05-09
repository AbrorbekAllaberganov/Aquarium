package com.company;

import java.util.Random;

public class Fish implements Runnable{
    boolean jins;
    long life;
    boolean isLive=true;
    int id;

    public Fish(boolean jins,int id){
        this.jins=jins;
        this.id=id;
    }

    public void setLife(){
        Random random=new Random();
        life=random.nextInt(30);
    }

    @Override
    public void run() {
        setLife();
        long currentTime=System.currentTimeMillis();
        while ((System.currentTimeMillis()-currentTime)/1000<life){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.isLive=false;
        Main main=new Main();
        int[] res =main.getFishInfo();
        System.out.println(id+"-baliq o'ldi "+res[0]+" ta erkak "+res[1]+" ta urg'ochi");


    }
}
