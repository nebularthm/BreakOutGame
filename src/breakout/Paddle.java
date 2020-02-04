package breakout;

import javafx.scene.shape.Rectangle;

public class Paddle extends Rectangle {
    public Paddle(int width, int height,double x, double y){
        this.setHeight(height);
        this.setWidth(width);
        this.setX(x);
        this.setY(y);
    }
}
