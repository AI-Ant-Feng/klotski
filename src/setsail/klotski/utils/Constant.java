package setsail.klotski.utils;

public class Constant {

    /**
     * 游戏区距离主界面左上角的距离(留白)
     */
    public static final int LEFT_BLANK_X = 50;
    public static final int TOP_BLANK_Y = 50;
    /**
     * 每个单元格的宽度和高度。
     */
    public static final int WIDTH_PER_GRID = 100;
    public static final int HEIGHT_PER_GRID = 100;

    /**
     * 游戏区水平方向和垂直方向单元格个数
     */
    public static final int HORIZONTAL_NUMBER_OF_GAME_ZONE_GRID = 4;
    public static final int VERTICAL_NUMBER_OF_GAME_ZONE_GRID = 5;

    /**
     * 游戏隔离区厚度（用于碰撞检测）
     */
    public static final int ISOLATION_ZONE_WIDTH = 5;
    public static final int ISOLATION_ZONE_HEIGHT = 5;
}
