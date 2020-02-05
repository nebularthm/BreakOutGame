package breakout;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Paddle extends ImageView {
    private double height;
    private double width;
    public Paddle(Image paddle, double x, double y,double width, double height){
        this.setImage(paddle);
        this.setX(x);
        this.setY(y);
        this.setId("paddle");
        this.height = height;
        this.width = width;

    }

    public double getWidth() {
        return this.width;
    }
    public double getHeight() {
        return this.height;
    }
}
