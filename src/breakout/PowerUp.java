package breakout;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PowerUp extends ImageView {
    private String type;
    /**
     * constructor for powerUPs
     * @param powerImage
     * @param x
     * @param y
     */
    public PowerUp(Image powerImage, double x, double y){
        this.setImage(powerImage);
        this.setX(x);
        this.setY(y);
    }

    /**
     * for this method we want to define what type of powerup this is
     * @param typeOfPower
     */
    public void setType(String typeOfPower){
        type = typeOfPower;
    }

    public String getType() {
        return type;
    }
}
