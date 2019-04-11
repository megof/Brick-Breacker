package brickBreaker;

public class Juego implements Constantes {

    protected int x, y, width, height;

    public Juego(int x, int y, int width, int height) {
        setHeight(height);
        setWidth(width);
        setX(x);
        setY(y);
    }

    protected void setX(int x) {
        this.x = x;
    }

    protected void setY(int y) {
        this.y = y;
    }

    protected void setWidth(int width) {
        this.width = width;
    }

    protected void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
