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
    public PowerUp(Image powerImage){
        this.setImage(powerImage);

    }

    /**
     * for this method we want to define what type of powerup this is
     * @param typeOfPower
     */
    public void setType(String typeOfPower){
        type = typeOfPower;
    }

    /**
     * get the type of powerUp this is
     * @return
     */
    public String getType() {
        return type;
    }
}
