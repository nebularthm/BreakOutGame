package breakout;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class will serve to represent bricks in our game of b reakout
 * This class extemds imagevoew amd os cpmstictired wotj a picture, an x coordinate, and a y coordinate
 * The game will use an array of brick objectts to poplate the top portion of the screen
 */
public class Menu extends ImageView {
    Button reset;
    ImageView bg;

    /**
     * consturcts amenu based on an image or text
     * @param img
     * @param x
     * @param y
     * @param text
     */
    public Menu(Image img,int x, int y, String text){
        this.setImage(img);
        this.setX(x);
        this.setY(y);
        reset = new Button(text);
        this.reset.setMaxSize(100, 200);

    }

    /**
     * gets the resset button field for a given menu
     * @return
     */
    public Button getReset(){
        return reset;
    }

    /**
     * gets the actua background image for a given menu
     * @return
     */
    public ImageView getBg() {
        return bg;
    }
}
