package com.company;

import java.util.List;
import java.util.Random;

public class FishService {
    public List<Fish> naslQoldirish(List<Fish> fishList, int counter) {
        Random random = new Random();
        int first = random.nextInt(fishList.size() - 1);
        int second = first;
        while (second == first) {
            second = random.nextInt(fishList.size() - 1);
        }

        if (fishList.get(first).isLive && fishList.get(second).isLive
                && (!fishList.get(first).jins == fishList.get(second).jins)) {
            int x = random.nextInt(10);
            for (int i = 0; i < x; i++) {
                int jins = random.nextInt(2);
                System.out.println(first + 1 + " va " + (second + 1) + " nasl qoldirdi => id si " + (++counter)+" jinsi "+((jins==1)?"Erkak":"Urg'ochi"));
                fishList.add(new Fish(jins == 1, counter));
            }
        }
        return fishList;
    }

    public List<Fish> deleteDeathFishes(List<Fish> fishList) {
        for (int i = 0; i < fishList.size(); i++) {
            if (!fishList.get(i).isLive)
                fishList.remove(i);
        }
        return fishList;
    }

    public int[] getFishesInfo(List<Fish>fishList){
        int[] res=new int[2];
        fishList.forEach(fish -> {
            if (fish.jins&&fish.isLive)
                res[0]++;
            else if (fish.isLive)
                res[1]++;
        });
        return res;
    }
}
