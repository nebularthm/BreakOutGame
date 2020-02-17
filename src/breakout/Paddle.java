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

    /**
     * adjust x speed of paddle
     * @param padSpeedX
     */
    public void setPadSpeedX(double padSpeedX) {
        this.padSpeedX = padSpeedX;
    }

    /**
     * get the x speed
     * @return
     */
    public double getPadSpeedX() {
        return padSpeedX;
    }

    /**
     * adjust the y sp-eed of paddle
     * @param padSpeedY
     */
    public void setPadSpeedY(double padSpeedY) {
        this.padSpeedY = padSpeedY;
    }

    /**
     * get the y speed of our paddle
     * @return
     */
    public double getPadSpeedY() {
        return padSpeedY;
    }
}
