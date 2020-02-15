package breakout;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class LevelBuilder {

    private static final String GREEN_BRICK = "https://images-na.ssl-images-amazon.com/images/I/410eZ0DDF2L._SL500_AC_SS350_.jpg";
    private static final String BLUE_BRICK = "https://images-na.ssl-images-amazon.com/images/I/51DwBLBY5VL._AC_SL1001_.jpg";
    private static final String Orange_BRICK = "data:image/jpeg;base64,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";
    private static final String RED_BRICK = "https://img.brickowl.com/files/image_cache/larger/lego-red-brick-2-x-4-3001-2-771344-81.jpg";
    private static final String White_BRICK = "https://brickit.com/images/_site/shapes/shape-image-9.png";
    private static final String PURPBRICK = "https://www.lightstax.eu/wp-content/uploads/2016/10/regular-purple-2x4.png";
    private static final String BLACK_BRICK = "https://img.brickowl.com/files/image_cache/larger/lego-black-brick-2-x-4-3001-2-771344-38.jpg";
    public static final int BRICK_AMOUNT = 5;
    private static final String GREEN = "green";
    private static final String BLUE = "blue";
    private static final String ORANGE = "orange";
    private static final String RED = "red";
    private static final String WHITE = "white";
    private static final String PURP = "purple";
    private static final String BLACK = "black";
    private ArrayList<ArrayList<Bricks>> levelAsList;

    private Bricks [][] level;
    LevelBuilder(String source, int width, int height){
        this.level = levelReader(source, width, height);

    }
    /**
     * this method constructs the grid of bricks by reading the config file for a level
     * @param levelSource string representing filename
     * @return
     */
    public Bricks [][]  levelReader(String levelSource, int width, int height) {
        Bricks [][] brickconfig = new Bricks[BRICK_AMOUNT][BRICK_AMOUNT];
        File lev  = new File(levelSource);
        Scanner levelScanner;
        try{
            levelScanner = new Scanner(lev);
        }
        catch(FileNotFoundException e){
            return new Bricks [0][0];
        }
        int i = 0;
        while(levelScanner.hasNextLine()){
            int j = 0;
            String levelLine = levelScanner.nextLine();
            String [] brickTypes = levelLine.split(" ");
            for(String type:brickTypes) {


                brickconfig[i][j] = makeBrick(type,i,j,width,height); //this is so bricks are added ight next to the next brick
                j++;
            }
            i++;
        }
        return brickconfig;
    }
    /**
     * based on the type of brick, we set the HP of this brick
     * @param brickType type of brick in the file
     * @return
     */
    public Bricks makeBrick(String brickType,int i,int j,int width,int height) {
        if (brickType.toLowerCase().contains(GREEN)) {
            Image defaultBrick = new Image(GREEN_BRICK, 30, 30, false, false);
            return new Bricks(defaultBrick, (.5 *i * width / BRICK_AMOUNT),  ( .5 * j * height / BRICK_AMOUNT),1);
        }
        if(brickType.toLowerCase().contains(BLUE)){
            Image blueBrick = new Image(BLUE_BRICK, 30, 30, false, false);
            return new Bricks(blueBrick, (.5 * i * width / BRICK_AMOUNT),  ( .5 * j * height / BRICK_AMOUNT),2);
        }
        if(brickType.toLowerCase().contains(ORANGE)){
            Image oranBrick = new Image(Orange_BRICK, 30, 30, false, false);
            return new Bricks(oranBrick, (.5 * i * width / BRICK_AMOUNT), ( .5 * j * height / BRICK_AMOUNT),3);
        }
        if(brickType.toLowerCase().contains(RED)){
            Image redBrick = new Image(RED_BRICK, 30, 30, false, false);
            return new Bricks(redBrick,  (.5 * i * width / BRICK_AMOUNT),  ( .5 * j * height / BRICK_AMOUNT),4);
        }
        if(brickType.toLowerCase().contains(WHITE)){
            Image whiteBrick = new Image(White_BRICK, 30, 30, false, false);
            return new Bricks(whiteBrick,  (.5 * i * width / BRICK_AMOUNT),  ( .5 * j * height / BRICK_AMOUNT),5);
        }
        if(brickType.toLowerCase().contains(PURP)){
            Image purpBrick = new Image(PURPBRICK, 30, 30, false, false);
            return new Bricks(purpBrick,  (.5 * i * width / BRICK_AMOUNT),  ( .5 * j * height / BRICK_AMOUNT),6);
        }
        Image blackBrick = new Image(BLACK_BRICK, 30, 30, false, false);
        return new Bricks(blackBrick,  (.5 * i * width / BRICK_AMOUNT), ( .5 * j * height / BRICK_AMOUNT),6);

    }
    public  ArrayList<ArrayList<Bricks>> twoDArrayToList(Bricks [][] twoDArray) {
        ArrayList<ArrayList<Bricks>> list = new ArrayList<>();
        for (Bricks [] array : twoDArray) {
            ArrayList<Bricks> tempList = new ArrayList<>();
            for(Bricks brick: array){
                tempList.add(brick);
            }
            list.add(tempList);
        }
        return list;
    }

    /**
     * this code is from stack, essentially converts 2d array into 2d arraylist
     * @param
     * @return
     */
    public void setLevelAsList(){
        this.levelAsList = twoDArrayToList(level);
    }


    public ArrayList<ArrayList<Bricks>> getLevelAsList() {
       return levelAsList;
    }
}
