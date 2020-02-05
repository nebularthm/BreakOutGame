package breakout;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Paddle extends ImageView {
    public Paddle(Image paddle, double x, double y){
        this.setImage(paddle);
        this.setX(x);
        this.setY(y);
    }
}
