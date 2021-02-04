package setsail.klotski.entites;

public class Node {
    private int id;
    private int pixelY;//对应height
    private int pixelX;//对应width
    private boolean horizontal ;

    public Node(int id, int pixelY, int pixelX, boolean horizontal) {
        this.id = id;
        this.pixelY = pixelY;
        this.pixelX = pixelX;
        this.horizontal  = horizontal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPixelY() {
        return pixelY;
    }

    public void setPixelY(int pixelY) {
        this.pixelY = pixelY;
    }

    public int getPixelX() {
        return pixelX;
    }

    public void setPixelX(int pixelX) {
        this.pixelX = pixelX;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }
}