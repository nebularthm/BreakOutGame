package breakout;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class will serve to represent bricks in our game of b reakout
 * This class extemds imagevoew amd os cpmstictired wotj a picture, an x coordinate, and a y coordinate
 * The game will use an array of brick objectts to poplate the top portion of the screen
 */
public class Menu  {
    Button reset;
    ImageView bg;
    public Menu(Image img,int x, int y){

        this.bg = new ImageView(img);
        this.bg.toBack();
        this.bg.setX(x-200);
        this.bg.setY(y-200);
        this.bg.setFitWidth(400);
        this.bg.setFitHeight(400);
        this.reset = new Button("reset");
        this.reset.toFront();

    }
    public Button getReset(){
        return reset;
    }

    public ImageView getBg() {
        return bg;
    }
}
