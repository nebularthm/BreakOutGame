package breakout;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class will serve to represent bricks in our game of b reakout
 * This class extemds imagevoew amd os cpmstictired wotj a picture, an x coordinate, and a y coordinate
 * The game will use an array of brick objectts to poplate the top portion of the screen
 */
public class Bricks extends ImageView {
    private int health;
    private Boolean destroyed = false;
    public Bricks(Image brickpic, double x, double  y, int hp){
        this.setImage(brickpic);
        this.setX(x);
        this.setY(y);
        health = hp;
    }
    public void updateDestroyed(){
        if(health <= 0)
        destroyed = true;
    }
    public boolean isDestroyed(){
        return destroyed;
    }
    public void updateHealth(int dmgMod){
        health -= dmgMod;
    }
    public void setHealth(int hp){
        health = hp;
    }

}
