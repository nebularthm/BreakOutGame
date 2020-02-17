package breakout;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class LevelBuilder {

    private static final String GREEN_BRICK = "https://images-na.ssl-images-amazon.com/images/I/410eZ0DDF2L._SL500_AC_SS350_.jpg";
    private static final String BLUE_BRICK = "https://images-na.ssl-images-amazon.com/images/I/51DwBLBY5VL._AC_SL1001_.jpg";
    private static final String Orange_BRICK ="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTTEPsxPSAFGSIKspJzIpnfTwHnxmELuOb2DuXSaYobm_uCvbiX";
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
    private boolean hardMode = false;
    private int healthMod;

    private Bricks [][] level;

    /**
     * constructor for the Levelbuilder,
     * @param source
     * @param width
     * @param height
     */
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
        setHealthMod();
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
            return new Bricks(defaultBrick, (.5 *i * width / BRICK_AMOUNT),  ( .5 * j * height / BRICK_AMOUNT),1 *healthMod);
        }
        if(brickType.toLowerCase().contains(BLUE)){
            Image blueBrick = new Image(BLUE_BRICK, 30, 30, false, false);
            return new Bricks(blueBrick, (.5 * i * width / BRICK_AMOUNT),  ( .5 * j * height / BRICK_AMOUNT),2 * healthMod);
        }
        if(brickType.toLowerCase().contains(ORANGE)){
            Image oranBrick = new Image(Orange_BRICK, 30, 30, false, false);
            return new Bricks(oranBrick, (.5 * i * width / BRICK_AMOUNT), ( .5 * j * height / BRICK_AMOUNT),3 * healthMod);
        }
        if(brickType.toLowerCase().contains(RED)){
            Image redBrick = new Image(RED_BRICK, 30, 30, false, false);
            return new Bricks(redBrick,  (.5 * i * width / BRICK_AMOUNT),  ( .5 * j * height / BRICK_AMOUNT),4 * healthMod);
        }
        if(brickType.toLowerCase().contains(WHITE)){
            Image whiteBrick = new Image(White_BRICK, 30, 30, false, false);
            return new Bricks(whiteBrick,  (.5 * i * width / BRICK_AMOUNT),  ( .5 * j * height / BRICK_AMOUNT),5 * healthMod);
        }
        if(brickType.toLowerCase().contains(PURP)){
            Image purpBrick = new Image(PURPBRICK, 30, 30, false, false);
            return new Bricks(purpBrick,  (.5 * i * width / BRICK_AMOUNT),  ( .5 * j * height / BRICK_AMOUNT),6 * healthMod);
        }
        Image blackBrick = new Image(BLACK_BRICK, 30, 30, false, false);
        return new Bricks(blackBrick,  (.5 * i * width / BRICK_AMOUNT), ( .5 * j * height / BRICK_AMOUNT),6 * healthMod);

    }

    /**
     * converts the brick representation of the level into a 2d array list for easier modifciation
     * @param twoDArray
     * @return
     */
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

    /**
     * gets the array;list representation of the level grid, easy to modify
     * @return
     */
    public ArrayList<ArrayList<Bricks>> getLevelAsList() {
       return levelAsList;
    }

    /**
     * adjusts level configuration based on whether its hard mode
     * @param difficulty
     */
    public void setHardMode(boolean difficulty){
        hardMode = difficulty;
    }

    /**
     * depending on the mode, adjust how much health each brick will have
     */
    private void setHealthMod(){
        if (hardMode == false){
            healthMod = 1;
        }
        else{
            healthMod = 2;
        }

    }
}
