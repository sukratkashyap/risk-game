/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sukratkashyap.riskgame.game.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description dice class for rolling
 */
public class Dice {

    public static int roll() {
        return rollDice();
    }

    public static List<Integer> roll(int n) {
        List<Integer> list = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            list.add(rollDice());
        }
        return Collections.unmodifiableList(list);
    }

    private static int rollDice() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}
