package breakout;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.*;


/**
     * A simple frogger-like game.
     *
     * @author Robert C. Duvall
     */
    public class Game extends Application {
        public static final String TITLE = "Super Breakout";
        public static final String WINNING_MESSAGE = "WINNER!";
        public static final int SIZE = 400;
        public static final int FRAMES_PER_SECOND = 60;
        public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
        public static final Paint BACKGROUND = Color.AZURE;
        public static final int PADDLE_SPEED = 10;
        public static final int BLOCK_SIZE = 30;
        public static final String LEVEL_SOURCE = "data/levels";

        public static final int BALL_SPEED = 50;

    public static final String BALL_PICTURE = "https://vignette.wikia.nocookie.net/idle-breakout/images/4/4b/Screen_Shot_2019-04-06_at_4.04.05_PM.png/revision/latest/top-crop/width/360/height/450?cb=20190406210459";

    public static final String PADDLE_PICTURE = "https://docs.microsoft.com/en-us/windows/uwp/get-started/images/monogame-tutorial-1.png";
    // some things we need to remember during our game
    public static final String BIGGERPADDLEIMAGE = "https://i.pinimg.com/originals/b4/92/d5/b492d594465b29bcbe7ff840fa18c896.png";
        private static final double BALL_PENALTY = 0.25 ;

    //TODO: Find better Brick textures, all you have to do here is just add themm to the sources, and then read the file like how i showed you
    //These URLS correspond to the brick images

    //These URL's correspond to paddle Images
    private static final String  LASERIMAGE = "https://labs.phaser.io/assets/animations/lazer/lazer_01.png";
    private static final String LASERSOURCE = "https://st2.depositphotos.com/5205783/9032/v/950/depositphotos_90328212-stock-illustration-logo-abstract-circle-l-design.jpg";
    private static final String MULTIBALLIMAGE = "https://www.logolynx.com/images/logolynx/c3/c3fc5f02c40e5e729e646a2ca97871ca.jpeg";
    private static final String LEVELSKIPIMAGE = "https://lh3.googleusercontent.com/proxy/YHqYDsLvTKimMM9CdmN7NP4foZtIrQDY-S_9aOSM2c4HFkJu4icxQz7XX8AM8r32ExbbkVKLTIGfD40C_vX5DoTpfJaWNXb1Ky5kXfvCcy_1LcsjEgF1";
    private static final String FASTBALLIMAGE = "https://i.pinimg.com/originals/a2/f4/14/a2f414b603535c3726a8403de2ed99db.png";
    private static final String FASTPADDLEIMAGE = "https://steamuserimages-a.akamaihd.net/ugc/874122921369426925/E1E9341ECFFFDD61C985EB5B3AE35672F878B523/";
    private static final String TURBOIMAGE = "https://image.shutterstock.com/image-vector/initial-company-circle-t-logo-260nw-752158675.jpg";
    private static final String SLOWIMAGE = "https://media.merchantcircle.com/32160242/circle%20s1_full.jpeg";
    private static final String DMGIMAGE = "https://i.ya-webdesign.com/images/d-transparent-circle-logo-1.png";
    private static final String HEALTHIMAGE ="https://st2.depositphotos.com/5943796/8708/v/950/depositphotos_87080608-stock-illustration-h-initial-circle-company-or.jpg";
    //this next part is just for the powerUP types
    private static final String BIG_PADDLE = "bigpad";
    private static final String LEVELSKIP = "levskip";
    private static final String FASTER_BALL = "fastball";
    private static final String FASTPADDLE = "fastpad";
    private static final String MULTIBALL = "moreball";
    private static final String TURBOMODE = "turb";
    private static final String SLOWMO = "slow";
    private static final String DOUBLEDMG = "doub";
    private static final String HEAL = "heal";
    private static final String LASER = "laser";


    // some things we need to remember during our game
        private Scene myScene;
        private Timeline myAnimation;
        private Paddle myPaddle;
        private Ball myBall;
        private ArrayList<Ball> multiBalls;
        private ImageView boundary;
        private int myBlockSpeedX, myBlockSpeedY;
       //this is a 2D array of our bricks
        private ProgressBar healthBar;
        private HBox healthBarLabel;
        private Label hLabel;
        private Label scoreTrack;
        private int myScore;
        private Text scoreText;

        private boolean winCon = false;
        private PowerUp powerUp;
        private Group root;
        private boolean isPowerUP = false;
        private Menu myMenu;
        private int dmgPenalty;
        private ArrayList<PowerUp> possiblePowerUps;
        private LevelBuilder level;
        private boolean areManyBalls = false;
        private ArrayList<String> allLevelPaths;
        private int maxLevel;
        private int curLevel;
        private String playerTag;
        private ImageView myLaser;
        private boolean isLaser = false;

    /**
     * this method returns data structure containing all possible powerUps, and a powerUP will be randomly selected, see the README for more details on each powerup
     * @return an arrayList of all of the powerUIps
     */
    private ArrayList<PowerUp> allPowerUps(){
        ArrayList<PowerUp> retList = new ArrayList<>();
        PowerUp bigpad = new PowerUp(new Image(BIGGERPADDLEIMAGE,30,30,false,false));
        bigpad.setType(BIG_PADDLE);
        retList.add(bigpad);
        PowerUp manyball = new PowerUp(new Image(MULTIBALLIMAGE,30,30,false,false));
        manyball.setType(MULTIBALL);
        retList.add(manyball);
        PowerUp turbo = new PowerUp(new Image(TURBOIMAGE,30,30,false,false));
        turbo.setType(TURBOMODE);
        retList.add(turbo);
        PowerUp slowMo = new PowerUp(new Image(SLOWIMAGE,30,30,false,false));
        slowMo.setType(SLOWMO);
        retList.add(slowMo);
        PowerUp doub = new PowerUp(new Image(DMGIMAGE,30,30,false,false));
        doub.setType(DOUBLEDMG);
        retList.add(doub);
        PowerUp health = new PowerUp(new Image(HEALTHIMAGE,30,30,false,false));
        health.setType(HEAL);
        retList.add(health);
        PowerUp balspeed = new PowerUp(new Image(FASTBALLIMAGE,30,30,false,false));
        balspeed.setType(FASTER_BALL);
        retList.add(balspeed);
        PowerUp padspeed = new PowerUp(new Image(FASTPADDLEIMAGE,30,30,false,false));
        padspeed.setType(FASTPADDLE);
        retList.add(padspeed);
        PowerUp lasuh = new PowerUp(new Image(LASERSOURCE,30,30,false,false));
        lasuh.setType(LASER);
        retList.add(lasuh);
        PowerUp skipper = new PowerUp(new Image(LEVELSKIPIMAGE,30,30,false,false));
        skipper.setType(LEVELSKIP);
        retList.add(skipper);
        return retList;
        }

    /**
     * this method essentially resets the level, tkes on a new root
     * @param rootie root to be modified
     * @param width width of scene
     * @param height height of scene
     * @param source source of the level
     */
    private void populateRoot(Group rootie,int width,int height, String source){
        // make some shapes, set their properties, and add them to the scene
        Image ballImage = new Image(BALL_PICTURE,30,30,false,false);
        myBall = new Ball(ballImage,width/2 - 15,height/2 +60);
        dmgPenalty = 1;
        myBlockSpeedX = BALL_SPEED;
        myBlockSpeedY = BALL_SPEED;
        myBall.setSpeedX(myBlockSpeedX);
        myBall.setSpeedY(myBlockSpeedY);
        rootie.getChildren().add(myBall);
        myPaddle = new Paddle(new Image(PADDLE_PICTURE, BLOCK_SIZE + 100,BLOCK_SIZE,false,false),width/2 , height - 100);
        myPaddle.setPadSpeedX(PADDLE_SPEED);
        myPaddle.setPadSpeedY(PADDLE_SPEED);
        rootie.getChildren().add(myPaddle);
        level = new LevelBuilder(source,width,height);
        level.setLevelAsList();
        for(ArrayList<Bricks> brickies:level.getLevelAsList()){
            for(Bricks brick: brickies){
                rootie.getChildren().add(brick);
            }
        }
        //create boundary that ball cannot pass over
        boundary = new ImageView();
        boundary.setImage(new Image("https://i.redd.it/rkfe2i3pdqqx.jpg",myScene.getWidth(),BLOCK_SIZE,false,false));
        boundary.setY(4 * height/5);
        boundary.setId("boundary");
        root.getChildren().add(boundary);
        healthBar = new ProgressBar(1);
        hLabel = new Label("Health",healthBar);
        hLabel.setLayoutY(7 * height/8);
        hLabel.setId("hLabel");
        rootie.getChildren().add(hLabel);
        scoreText = new Text(myScore + "0");
        scoreTrack = new Label("Score: ", scoreText);
        scoreTrack.setLayoutY(7*height/8);
        scoreTrack.setLayoutX(width * 4/5);
        scoreTrack.setId("scoreTrack");
        rootie.getChildren().add(scoreTrack);
        isLaser = false;
    }

    /**
     *
     *gives a particular powerup, this method provides the effect of that powerup when touched by the paddle
     * @param type string representing type of powerUp
     */

    private void powerUPEffect( PowerUp type){
            if(type.getType().equals(BIG_PADDLE)){
                bigify();
            }
            if(type.getType().equals(MULTIBALL)){
                multiply();
            }
            if(type.getType().equals(TURBOMODE)){
                myAnimation.setRate(5);
            }
            if(type.getType().equals(SLOWMO)){
                myAnimation.setRate(0.1);
            }
            if(type.getType().equals(DOUBLEDMG)){
                dmgPenalty *= 2;
            }
            if(type.getType().equals(HEAL)){
                if(healthBar.getProgress() < 1) {
                    healthBar.setProgress(healthBar.getProgress() + dmgPenalty);
                    hLabel = new Label("Health", healthBar);
                }
            }
            if(type.getType().equals(FASTER_BALL)){
                myBall.setSpeedX(myBall.getBallSpeedX() * 1.5);
                myBall.setSpeedY(myBall.getBallSpeedY() * 1.5);
            }
            if(type.getType().equals(FASTPADDLE)){
               myPaddle.setPadSpeedX(myPaddle.getPadSpeedX() * 1.5);
               myPaddle.setPadSpeedY(myPaddle.getPadSpeedY() * 1.5);
            }
            if(type.getType().equals(LEVELSKIP) && curLevel != maxLevel){
                loadNextLevel();
            }
            if(type.getType().equals(LASER)){
                loadLaser(powerUp);
            }
        }

    /**
     * this method loads a lazer  based  on the x position of a given powerup, this laser will then be preesent for the entire level, and will periodically do damage to all the brickw in front of it,
     * @param pow powerup object
     */
    private void loadLaser(PowerUp pow) {
        isLaser = true;
        myLaser = new ImageView((new Image(LASERIMAGE,10, 10 - myScene.getHeight(),false,false)));
        myLaser.setX(pow.getX());
        myLaser.setY(boundary.getY()); // we want this powerup to start from the boundary and then shoot upwards into the level
        root.getChildren().add(myLaser);
    }

    /**
     * this method adds 5 balls to the screen, biracial
     */
    private void multiply() {
    if(areManyBalls == true){
        return;
    }
        multiBalls = makeMoreBalls();
    for(Ball ball:multiBalls){
        ball.setSpeedX(myBlockSpeedX);
        ball.setSpeedY(myBlockSpeedY);
        root.getChildren().add(ball);
    }
    }

    /**
     * This method returns a data structure data
     * @return an arrayList containing 5 balls
     */
    private ArrayList<Ball> makeMoreBalls() {
        ArrayList<Ball> retList = new ArrayList<>();
        Image ballImage = new Image(BALL_PICTURE,30,30,false,false);
        retList.add(new Ball(ballImage,0,myScene.getHeight()/2 +60));
        retList.add(new Ball(ballImage,myScene.getWidth()/4,myScene.getHeight()/2 +60));
        retList.add(new Ball(ballImage,myScene.getWidth()/2 - 15,myScene.getHeight()/2 +60));
        retList.add(new Ball(ballImage,3 * myScene.getWidth()/4,myScene.getHeight()/2 +60));
        retList.add(new Ball(ballImage,7 * myScene.getWidth()/8,myScene.getHeight()/2 +60));
        return retList;
    }

    /**
     * this method doubles the size of the paddle
     */
    private void bigify() {
        myPaddle.setFitWidth(myPaddle.getImage().getWidth() * 2);
    }


        /**
         * Initialize what will be displayed and how it will be updated.
         */
        @Override
        public void start (Stage stage) {
            // attach scene to the stage and display it
            allLevelPaths = makeLevelPaths();
            curLevel = 1;
            maxLevel = allLevelPaths.size();
            myScene = setupScene(SIZE, SIZE, BACKGROUND,allLevelPaths.get(0) );
            stage.setScene(myScene);
            stage.setTitle(TITLE);
            stage.show();
            // attach "game loop" to timeline to play it
            KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
            myAnimation = new Timeline();
            myAnimation.setCycleCount(Timeline.INDEFINITE);
            myAnimation.getKeyFrames().add(frame);
            myAnimation.play();
        }

    private ArrayList<String> makeLevelPaths() {
            ArrayList<String> levelsPath = new ArrayList<>();
            File  levelDir = new File(LEVEL_SOURCE);
            File [] levelFiles = levelDir.listFiles();
            for(File file:levelFiles){
                levelsPath.add(file.getPath());
            }
            return levelsPath;
    }


    // Create the game's "scene": what shapes will be in the game and their starting properties

    /**
     * Sets up the scene/initial level
     * @param width of scene
     * @param height of scene
     * @param background of scene
     * @param source souce for level
     * @return
     */
    Scene setupScene (int width, int height, Paint background, String source) {
            // create one top level collection to organize the things in the scene
            root = new Group();
            // make some shapes, set their properties, and add them to the scene
            // create a place to see the shapes
            myScene = new Scene(root, width, height, background);
            //create boundary that ball cannot pass over
            populateRoot(root,width,height,source);
            FileInputStream imgFile = null;
            try {
                imgFile = new FileInputStream("data/Images/gameover.png");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Image img = new Image(imgFile);
            myMenu = new Menu(img,SIZE/2,SIZE/2);
            myMenu.setFitWidth(100);
            myMenu.setFitHeight(100);
            possiblePowerUps = allPowerUps();
            // respond to
            myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
            return myScene;
        }

        // Change properties of shapes to animate them

    /**
     * this method handles the animation of the program
     * @param elapsedTime time that has passed
     */
    void step (double elapsedTime) {
            // update attributes
            dropPowerUp(elapsedTime);
            updateBall(elapsedTime);
            if(multiBalls != null){
                updateManyBalls(elapsedTime);
                areManyBalls = true;
            }
            if(multiBalls != null && multiBalls.size() == 0){
                areManyBalls = false;
        }

            // check for collisions TODO: Make  a physics class that handles this, for now I will figure out how to do this later
             if(myBall.getBoundsInParent().intersects(boundary.getBoundsInParent())){
                myBall.setX(0);
                myBall.setY(myScene.getHeight()/2);
                healthBar.setProgress(healthBar.getProgress() - BALL_PENALTY);
                hLabel = new Label("Health", healthBar);
            }
            if(healthBar.getProgress() == 0){//this is if you run out of health, ie if you lost
                root.getChildren().clear();
                root.getChildren().add(myMenu);
                // TODO: Figure out how to get the button on the menu
//                Button ret = myMenu.getReset();
//                root.getChildren().add(ret);
                //myAnimation.stop();
            }
            //if you hit the paddle, bounce as if you hit the wall
            if(myBall.getBoundsInParent().intersects(myPaddle.getBoundsInParent())){
            myBall.setSpeedY(myBall.getBallSpeedY() * -1);
            myBall.setSpeedX(myBall.getBallSpeedX() * -1);

        }
            //check for case when you hit a brick
            updateBricks(myBall);
            if(isLaser){
                updateBricksImageView(myLaser);
            }
            destroyBricks();
        if(brickCount() == 0){
            loadNextLevel();
        }
            // TODO: check for win and, if true, pause the animation
        }

    /**
     * this method loads the next Level
     */
    private void loadNextLevel() {
        if(curLevel == maxLevel){
            youWon();
            return;
        }
        curLevel++;
        root.getChildren().clear();
        populateRoot(root,SIZE,SIZE,allLevelPaths.get(curLevel-1));
    }

    /**
     * this handles the win condition, in terms of returning the screen
     */
    private void youWon() {
        //TODO: AP write/call the WIn Screen method here
        writeUserScore();
    }

    /**
     * This method prompts the user to write their initials/gamerTag to the score.txt file
     */
    private void writeUserScore() {
        root.getChildren().clear();
        TextField name = new TextField();
        name.setPromptText("Enter your epic gamer tag");
        name.setPrefColumnCount(10);
        name.setLayoutX(SIZE/2);
        name.setLayoutY(SIZE/2);
        playerTag = name.getText();
        root.getChildren().add(name);
        Button submit = new Button("Submit");
        root.getChildren().add(submit);
        submit.setOnAction(e -> {
            if (!name.getText().isEmpty()) {
                myAnimation.stop();
            }
        });

        updateScoreFile();
    }
    /**
     * This method simply writes whatever the highscore + initial combination is to the file, also sorts the score file as more scores are added
     */
    private void updateScoreFile() {
        try {
            FileWriter myWriter = new FileWriter("data/score.txt", true);
            myWriter.write(playerTag + " " + scoreText.getText());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred in writing your score lmfao you are ass");
            e.printStackTrace();
        }
        sortScores();
    }

    /**
     * this method sorts the scores file so we can easily pull high schore
     */
    private void sortScores(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/score.txt"));
            ArrayList<String> lines = new ArrayList<String>();
            String currentLine = br.readLine();
            while (currentLine != null)
            {
                lines.add(currentLine);
                currentLine = br.readLine();
            }
            br.close();
            Collections.sort(lines);
            Collections.reverse(lines);
            FileWriter myWriter = new FileWriter("data/score.txt", false);
            for(String str:lines){
                myWriter.write(str);
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred in writing your score lmfao you are ass");
            e.printStackTrace();
        }
    }

    /**
     * this method is responsible for updating many balls in the case of the multiball powerup
     * @param elapsedTime time that has passed
     */
    private void updateManyBalls(double elapsedTime) {
        Iterator<Ball> itr = multiBalls.iterator();
        while(itr.hasNext()){
            Ball ball = itr.next();
            ball.setX(ball.getX() + ball.getBallSpeedX() * elapsedTime);
            if (ball.getX()  >= myScene.getWidth() || ball.getX() <= 0 ) {
                ball.setSpeedX(ball.getBallSpeedX() * -1);
            }
            ball.setY(ball.getY() + ball.getBallSpeedY() * elapsedTime);
            if (ball.getY()  >= myScene.getHeight() || ball.getY() <= 0 ) {
                ball.setSpeedY(ball.getBallSpeedY() * -1);
            }
            if(ball.getBoundsInParent().intersects(boundary.getBoundsInParent())){
               ball.setImage(null);
               itr.remove();
            }
            if(ball.getBoundsInParent().intersects(myPaddle.getBoundsInParent())){
                ball.setSpeedY(ball.getBallSpeedY() * -1);
                ball.setSpeedX(ball.getBallSpeedX() * -1);

            }
            updateBricks(ball);
        }
    }

    /**
     * this method handles updating the health of bricks as they are collided into
     */
    private void updateBricks(Ball ball) {
        for(ArrayList<Bricks> brickies: level.getLevelAsList()){
            for(Bricks brick: brickies){
                if(ball.getBoundsInParent().intersects(brick.getBoundsInParent())){
                    ball.setSpeedY(ball.getBallSpeedY() * -1);
                    ball.setSpeedX(ball.getBallSpeedX() * -1);
                    brick.updateHealth(dmgPenalty);
                    brick.updateDestroyed();
                    myScore += 10;
                    scoreText.setText(myScore + "0");
                    scoreTrack = new Label("Score: ",scoreText);

                }
            }
        }
    }

    /**
     * this method is similar to update bricks, except it checks for collision based on a general imageView object, such as lasers or missles. USeful for future feature adding to this game
     * @param immy Any imageview
     */
    private void updateBricksImageView(ImageView immy){
        for(ArrayList<Bricks> brickies: level.getLevelAsList()){
            for(Bricks brick: brickies){
                if(immy.getBoundsInParent().intersects(brick.getBoundsInParent())){
                    brick.updateHealth(dmgPenalty );
                    brick.updateDestroyed();
                    myScore += 10;
                    scoreText.setText(myScore + "0");
                    scoreTrack = new Label("Score: ",scoreText);
                }
            }
        }
    }

    /**
     * This method handles the destruction of bricks
     */
    private void destroyBricks() {
        for(ArrayList<Bricks> brickies:level.getLevelAsList()){
            Iterator<Bricks> itr = brickies.iterator();
            while(itr.hasNext()){
                Bricks brick = itr.next();
                if(brick.isDestroyed()){
                    brick.setImage(null);
                    itr.remove();
                    root.getChildren().remove(brick);
                    myScore += 100;
                    scoreText.setText(myScore + "0");
                    scoreTrack = new Label("Score: ",scoreText);
                    if(isPowerUP == false) {
                        isPowerUP = true;

                        Random rand = new Random();
                        powerUp = possiblePowerUps.get(rand.nextInt(possiblePowerUps.size()));
                        powerUp.setX(brick.getX());
                        powerUp.setY(brick.getY());
                        root.getChildren().add(powerUp);
                    }
                }
            }
        }
    }

    /**
     * This method updates the positioning of the ball
     * @param elapsedTime time that has passed
     */
    private void updateBall(double elapsedTime) {
        myBall.setX(myBall.getX() + myBall.getBallSpeedX() * elapsedTime);
        if (myBall.getX() >= myScene.getWidth() || myBall.getX() <= 0 ) {
           myBall.setSpeedX(myBall.getBallSpeedX() * -1);
        }
        myBall.setY(myBall.getY() + myBall.getBallSpeedY() * elapsedTime);
        if (myBall.getY()  >= myScene.getHeight() || myBall.getY() <= 0 ) {
            myBall.setSpeedY(myBall.getBallSpeedY() * -1);
        }
    }

    /**
     * This method drops a powerup from a brick that was destroyed on the PREVIOUS frame
     * @param elapsedTime how much time has elapsed
     */
    private void dropPowerUp(double elapsedTime) {
        if(isPowerUP) {
            powerUp.setY(powerUp.getY() + 50 * elapsedTime);
            if (powerUp.getBoundsInParent().intersects(myPaddle.getBoundsInParent())) {
                powerUPEffect(powerUp);
                isPowerUP = false;
                root.getChildren().remove(powerUp);
            }
            if(powerUp.getBoundsInParent().intersects(boundary.getBoundsInParent())){
                isPowerUP = false;
                powerUp.setImage(null);
                root.getChildren().remove(powerUp);
            }
        }
    }


    /**
     * counts the bricks remaining in the grid
     * @return the int with bricks
     */
    private int brickCount(){
        int brickAmount = 0;
        for(ArrayList<Bricks> brickies:level.getLevelAsList()){
            Iterator<Bricks> itr = brickies.iterator();
            while(itr.hasNext()){
                Bricks brick = itr.next();
                brickAmount++;
            }
        }
        return brickAmount;
        }

        // What to do each time a key is pressed

    /**
     * handles what ahppens when the player presses a certain key
     * @param code the key the player pressed
     */
    private void handleKeyInput (KeyCode code) {
            // move player
            if (code == KeyCode.RIGHT) {
                myPaddle.setX(myPaddle.getX() + myPaddle.getPadSpeedX());
            }
            else if (code == KeyCode.LEFT) {
                myPaddle.setX(myPaddle.getX() - myPaddle.getPadSpeedX());
            }
            else if (code == KeyCode.UP) {
                myPaddle.setY(myPaddle.getY() - myPaddle.getPadSpeedY());
            }
            else if (code == KeyCode.DOWN) {
                myPaddle.setY(myPaddle.getY() + myPaddle.getPadSpeedY());
            }


            // pause/restart animation
            if (code == KeyCode.SPACE) {
                if (myAnimation.getStatus() == Animation.Status.RUNNING) {
                    myAnimation.pause();
                }
                else {
                    myAnimation.play();
                }
            }
            //when you press r this completely resets ball and paddle
            if(code == KeyCode.R){
                myBall.setX(0);
                myBall.setY(myScene.getHeight()/2);
                myPaddle.setX(myScene.getWidth()/2);
                myPaddle.setY(myScene.getHeight()/2);
            }
            if(code == KeyCode.L){//This block of code gives the player full health on the presing of the L key
                healthBar.setProgress(1);
                hLabel = new Label("Health", healthBar);
            }
            if(code == KeyCode.P && isPowerUP == false){
                isPowerUP = true;
                Random rand = new Random();
                powerUp = possiblePowerUps.get(rand.nextInt(possiblePowerUps.size()));
                root.getChildren().add(powerUp);
            }
            if(code == KeyCode.B){
                myBall.setFitWidth(myBall.getFitWidth() * 2);
            }
            if(code == KeyCode.D){// we are going to break the first undamaged brick
                for(ArrayList<Bricks> brickies:level.getLevelAsList()){
                    Iterator<Bricks> itr = brickies.iterator();
                    while(itr.hasNext()){
                        Bricks brick = itr.next();
                        if(!brick.isDestroyed()){
                            brick.setImage(null);
                            itr.remove();
                            root.getChildren().remove(brick);
                            if(isPowerUP == false) {
                                isPowerUP = true;
                                Random rand = new Random();
                                powerUp = possiblePowerUps.get(rand.nextInt(possiblePowerUps.size()));
                                root.getChildren().add(powerUp);
                            }
                            return;
                        }
                    }
                }
            }
            //setRate - increases the rate
            if(code == KeyCode.F){//this engages fast mode
                myAnimation.setRate(5);
            }
            if(code == KeyCode.S){//this engages fast mode
                myAnimation.setRate(.1);
            }
            if(code == KeyCode.N){//makes stuff normal
                myAnimation.setRate(1);
            }
            if(code == KeyCode.L){
                loadNextLevel();
            }
            if(code == KeyCode.DIGIT1){
                curLevel = 0;
                loadNextLevel();
        }
        if(code == KeyCode.DIGIT2){
            curLevel = 1;
            loadNextLevel();
        }
        if(code == KeyCode.DIGIT3){
            curLevel = 2;
            loadNextLevel();
        }
        if(code == KeyCode.DIGIT4){
            curLevel = 3;
            loadNextLevel();
        }
        if(code == KeyCode.DIGIT5){
            curLevel = 4;
            loadNextLevel();
        }
        }



    public static void main (String[] args) {
            launch(args);
        }



    }
