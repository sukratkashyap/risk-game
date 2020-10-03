package com.sukratkashyap.riskgame.game.core;

import java.awt.Color;

/**
 * @author MiFans (Sukrat Kashyap - 14200092, Zhesi Ning - 12252511)
 * @Description List of all the constants that will be used in the program
 * throughout.
 */
public class Constants {

    //Game constant
    public static final int NUM_PLAYERS = 2;
    public static final int NUM_NEUTRALS = 4;
    public static final int NUM_PLAYERS_PLUS_NEUTRALS = NUM_PLAYERS + NUM_NEUTRALS;
    public static final int NUM_COUNTRIES = 42;
    public static final int NUM_TERRITORY_CARDS = 42;
    public static final int NUM_WILD_CARDS = 2;
    public static final int NUM_CARDS = NUM_TERRITORY_CARDS + NUM_WILD_CARDS;
    public static final int INIT_COUNTRIES_PLAYER = 9;
    public static final int INIT_COUNTRIES_NEUTRAL = 6;
    public static final int INIT_UNITS_PLAYER = 36;
    public static final int INIT_UNITS_NEUTRAL = 24;
    public static final int NUM_CONTINENTS = 6;

    /**
     * colors that a player can take
     */
    public static final Color[] COLOR_PLAYER = new Color[]{
        Color.CYAN, Color.MAGENTA, Color.WHITE, Color.BLACK, Color.LIGHT_GRAY, Color.ORANGE
    };

    //Graphic constant
    public static final int MAP_RATIO_HEIGHT = 2;
    public static final int MAP_RATIO_WIDTH = 3;
    public static final int MAP_GROWTH_ORIGINAL = 300;
    public static final int MAP_GROWTH_NEW = 250;
    public static final int MAP_RATIO_GROWTH_HEIGHT_ORIGINAL = MAP_RATIO_HEIGHT * MAP_GROWTH_ORIGINAL;
    public static final int MAP_RATIO_GROWTH_WIDTH_ORIGINAL = MAP_RATIO_WIDTH * MAP_GROWTH_ORIGINAL;
    public static final int MAP_RATIO_GROWTH_HEIGHT_NEW = MAP_RATIO_HEIGHT * MAP_GROWTH_NEW;
    public static final int MAP_RATIO_GROWTH_WIDTH_NEW = MAP_RATIO_WIDTH * MAP_GROWTH_NEW;
}
