package breakout;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * this class controls the paddle, consturcutor is used to assign  x and y coordinates and a paddle image when creating a paddle object.
 */
public class Paddle extends ImageView {
    public Paddle(Image paddle, double x, double y){
        this.setImage(paddle);
        this.setX(x);
        this.setY(y);
    }
}
