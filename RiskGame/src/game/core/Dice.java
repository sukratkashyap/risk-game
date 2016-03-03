/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description enum for the type of player
 */
public class Dice {

    public static int roll() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    public static List<Integer> roll(int n) {
        List<Integer> list = new ArrayList<>(n);
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            list.add(random.nextInt(6) + 1);
        }
        return Collections.unmodifiableList(list);
    }
}
