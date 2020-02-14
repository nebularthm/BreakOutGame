package breakout;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * this class controls the paddle, consturcutor is used to assign  x and y coordinates and a paddle image when creating a paddle object.
 */
public class Paddle extends ImageView {
    private double padSpeedX;
    private double padSpeedY;
    /**
     * the constructor for paddle objects
     * @param paddle image for our paddle
     * @param  x and y coordinate
     * @param y
     */
    public Paddle(Image paddle, double x, double y){
        this.setImage(paddle);
        this.setX(x);
        this.setY(y);
        this.setId("paddle");

    }

    public void setPadSpeedX(double padSpeedX) {
        this.padSpeedX = padSpeedX;
    }

    public double getPadSpeedX() {
        return padSpeedX;
    }

    public void setPadSpeedY(double padSpeedY) {
        this.padSpeedY = padSpeedY;
    }

    public double getPadSpeedY() {
        return padSpeedY;
    }
}
