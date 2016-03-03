package game.core;

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
    public static final int INIT_COUNTRIES_PLAYER = 9;
    public static final int INIT_COUNTRIES_NEUTRAL = 6;
    public static final int INIT_UNITS_PLAYER = 36;
    public static final int INIT_UNITS_NEUTRAL = 24;
    public static final int NUM_CONTINENTS = 6;

    public static final Color[] COLOR_PLAYER = new Color[]{
        Color.CYAN, Color.MAGENTA, Color.WHITE, Color.BLACK, Color.LIGHT_GRAY, Color.ORANGE
    };

    //Graphic constant
    //    public static final int PANEL_MAP_WIDTH = 1000;
    //    public static final int PANEL_MAP_HEIGHT = 575;
    //    public static final int PANEL_PLAYER_STATS_WIDTH = 200;
    //    public static final int PANEL_PLAYER_STATS_HEIGHT = PANEL_MAP_HEIGHT;
    //    public static final int PANEL_MESSAGE_WIDTH = PANEL_MAP_WIDTH + PANEL_PLAYER_STATS_WIDTH;
    //    public static final int PANEL_MESSAGE_HEIGHT = 50;
    //    public static final int PANEL_COMMAND_WIDTH = PANEL_MESSAGE_WIDTH;
    //    public static final int PANEL_COMMAND_HEIGHT = 20;
    //    public static final int FRAME_WIDTH = PANEL_MESSAGE_WIDTH;
    //    public static final int FRAME_HEIGHT = PANEL_PLAYER_STATS_HEIGHT + PANEL_MESSAGE_HEIGHT + PANEL_COMMAND_HEIGHT;
}
