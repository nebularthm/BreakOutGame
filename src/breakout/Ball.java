package breakout;
/** This is a class that controls the balls for this game, will have methods for modifying the ball based on certain events
 * @author Michael Williams
 */

import javafx.scene.image.*;

public class Ball extends ImageView {
    private double height;
    private double width;
private double ballSpeed;

/**
     * This is the basic constructor for ball objects
     * @param picture picture of this ball
     * @param x x posiiton
     * @param y y position
     */
    public Ball(Image picture, double x, double y, double width, double height){
    this.setImage(picture);
    this.setX(x);
    this.setY(y);
    this.setId("ball_is_life");
        this.height = height;
        this.width = width;
}
public void setSpeed(double speed){
    ballSpeed = speed;
}

    public double getBallSpeed() {
        return ballSpeed;
    }

    public void onBounce(){
        ballSpeed *= -1;
}
    public double getWidth() {
        return this.width;
    }
    public double getHeight() {
        return this.height;
    }


}
