package breakout;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PowerUp extends ImageView {
    public PowerUp(Image powerImage, double x, double y){
        this.setImage(powerImage);
        this.setX(x);
        this.setY(y);

    }
}
