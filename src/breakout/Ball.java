package breakout;
/** This is a class that controls the balls for this game, will have methods for modifying the ball based on certain events
 * @author Michael Williams
 */

import javafx.scene.image.*;

public class Ball extends ImageView {
private double ballSpeed;
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
}
public void setSpeed(double speed){
    ballSpeed = speed;
}
public void onBounce(){
        ballSpeed *= -1;
}



}
