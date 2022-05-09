package com.company;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {

    public static void nasl() {
        FishService fishService = new FishService();
        int oldSize = fishList.size();
        fishList = fishService.naslQoldirish(fishList, counter);
        if (fishList.size() > oldSize) {
            counter += fishList.size() - oldSize;
            for (int i = oldSize + 1; i < fishList.size(); i++) {
                new Thread(fishList.get(i), String.valueOf(i)).start();

            }
        }
    }

    public static void clearAquarium() {
        FishService fishService = new FishService();
        fishList = fishService.deleteDeathFishes(fishList);
    }

    public static boolean getLive() {
        for (Fish fish : fishList) {
            if (fish.isLive)
                return true;
        }
        return false;
    }

    public int[] getFishInfo(){
        FishService fishService=new FishService();
        return fishService.getFishesInfo(fishList);
    }

    static List<Fish> fishList = new CopyOnWriteArrayList<>();
    static int counter;

    public static void main(String[] args) {
        Random random = new Random();

        int n = random.nextInt(15) + 1;
        int m = random.nextInt(15) + 1;
        System.out.println(n + " ta erkak\n" + m + " ta urg'ochi baliq bor");
        counter = n + m;
        for (int i = 1; i <= n + m; i++) {
            Fish fish;
            if (i <= n)
                fish = new Fish(true, i);
            else
                fish = new Fish(false, i);
            fishList.add(fish);
            new Thread(fish, String.valueOf(i)).start();
        }

        Runnable nasl = new Runnable() {
            @Override
            public void run() {
                while (getLive()) {
                    nasl();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Tirik baliqlar qolmadi");
                System.exit(0);
            }
        };

        Runnable clear = new Runnable() {
            @Override
            public void run() {
                while (fishList.size() != 0) {
                    clearAquarium();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        new Thread(clear).start();
        new Thread(nasl).start();

    }
}
