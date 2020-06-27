import java.awt.*;

public class Car extends GameObject {
    private String path;

    public Car(int x, int y, int width, int height) {
        super(x, y, width, height);
    }


    public void draw(Graphics g) {
        super.draw(g, path);
    }
}
