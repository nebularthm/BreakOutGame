package breakout;
/** This is a class that controls the balls for this game, will have methods for modifying the ball based on certain events
 * @author Michael Williams
 */

import javafx.scene.image.*;

public class Ball extends ImageView {
private double ballSpeedX;
private double ballSpeedY;
/**
     * This is the basic constructor for ball objects
     * @param picture picture of this ball
     * @param x x posiiton
     * @param y y position
     */
    public Ball(Image picture, double x, double y){
    this.setImage(picture);
    this.setX(x);
    this.setY(y);
    this.setId("ball_is_life");
}

    /**
     * set the x speed of the ball
     * @param speed
     */
    public void setSpeedX(double speed){
    ballSpeedX = speed;
}

    /**
     * set the y speed of the ball
     * @param speed
     */
    public void setSpeedY(double speed){ballSpeedY = speed;}

    /**
     * get the x speed of the ball
     * @return
     */
    public double getBallSpeedX() {
        return ballSpeedX;
    }

    /**
     * get the y speed of the ball
     * @return
     */
    public double getBallSpeedY(){ return ballSpeedY;}





}
