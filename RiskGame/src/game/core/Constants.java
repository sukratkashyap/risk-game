package game.core;

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

    //Graphic constant
    public static final int NODE_RADIUS = 12;
    public static final int NODE_DIAMETER = NODE_RADIUS * 2;
    public static final int PANEL_MAP_WIDTH = 1000;
    public static final int PANEL_MAP_HEIGHT = 600;
    public static final int PANEL_PLAYER_STATS_WIDTH = 200;
    public static final int PANEL_PLAYER_STATS_HEIGHT = PANEL_MAP_HEIGHT;
    public static final int PANEL_MESSAGE_WIDTH = PANEL_MAP_WIDTH + PANEL_PLAYER_STATS_WIDTH;
    public static final int PANEL_MESSAGE_HEIGHT =110;
    public static final int FRAME_WIDTH = PANEL_MESSAGE_WIDTH;
    public static final int FRAME_HEIGHT = (2*PANEL_MESSAGE_HEIGHT) + PANEL_PLAYER_STATS_HEIGHT;
}