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
    private boolean hardMode = false;

    /**
     * based on x,y, hp, and a picture of the brick, construcuts a brick object
     * @param brickpic
     * @param x
     * @param y
     * @param hp
     */
    public Bricks(Image brickpic, double x, double  y, int hp){
        this.setImage(brickpic);
        this.setX(x);
        this.setY(y);
        health = hp;
    }

    /**
     * becaue daage mod may not be 1, if our health is below zero then this brick is done for
     */
    public void updateDestroyed(){
        if(health <= 0)
        destroyed = true;
    }

    /**
     * gives the status of the brick, lets us know if it is destoryed yet
     * @return
     */
    public boolean isDestroyed(){
        return destroyed;
    }

    /**
     * based on damage mod, damage brick
     * @param dmgMod
     */
    public void updateHealth(int dmgMod){
        health -= dmgMod;
    }

    /**
     * adjust health of the brick depending on the mode
     * @param hp
     */
    public void setHealth(int hp){
        health = hp;
    }


}
