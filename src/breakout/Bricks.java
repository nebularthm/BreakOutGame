package breakout;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class will serve to represent bricks in our game of b reakout
 * This class extemds imagevoew amd os cpmstictired wotj a picture, an x coordinate, and a y coordinate
 * The game will use an array of brick objectts to poplate the top portion of the screen
 */
public class Bricks extends ImageView {
    public Bricks(Image brickpic,int x, int y){
        this.setImage(brickpic);
        this.setX(x);
        this.setY(y);
    }
}
