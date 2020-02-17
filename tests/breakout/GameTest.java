
package breakout;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;


class GameTest extends DukeApplicationTest {
    // create an instance of our game to be able to call in tests (like step())
    private final Game myGame = new Game();
    // keep created scene to allow mouse and keyboard events
    private Scene myScene;
    // keep any useful elements whose values you want to test directly in multiple tests
    private Paddle myPaddle;
    // TODO: add many blocks
    private Ball myBall;
    private ImageView boundary;
    private int myBlockSpeedX, myBlockSpeedY;
    private Bricks [][] level;//this is a 2D array of our bricks
    private ProgressBar healthBar;
    private Label hLabel;
    private Label scoreTrack;
    private int myScore;
    private Text scoreText;
    private ArrayList<ArrayList<Bricks>> levelAsList;
    private boolean winCon = false;
    private PowerUp bigpaddie;
    private String firstLevl = "data/levels/level1.txt";
    private LevelBuilder myLevel;

    /**
     * for starting the test game
     * @param stage where the game is played
     */
    @Override
    public void start (Stage stage) {
        myScene = myGame.setupScene(Game.SIZE, Game.SIZE, Game.BACKGROUND, firstLevl);
        stage.setScene(myScene);
        stage.show();
        stage.setTitle(myGame.TITLE);
        myPaddle = lookup("#paddle").query();
        myBall = lookup("#ball_is_life").query();
        hLabel = lookup("#hLabel").query();
        scoreTrack = lookup("#scoreTrack").query();
        myLevel = myGame.getLevel();


    }

    /**
     * this test checks for initial paddle position
     */
    @Test
    public void testPaddleInitialPosition () {
        assertEquals(Game.SIZE/2, myPaddle.getX());
        assertEquals( Game.SIZE - 100, myPaddle.getY());
        assertEquals(Game.BLOCK_SIZE + 100, myPaddle.getFitWidth());
        assertEquals(Game.BLOCK_SIZE , myPaddle.getFitWidth());
    }


    /**
     * this test checks the ball initial posiiton
     */
    @Test
    public void testBallInitialPosition () {
        assertEquals(Game.SIZE/2 - 15, myBall.getX());
        assertEquals(Game.SIZE/2 + 60, myBall.getY());
        assertEquals(30, myBall.getFitHeight());
        assertEquals(30, myBall.getFitWidth());
    }


    /**
     * this method tests the paddle motion
     */
    @Test
    public void testPaddleMove () {
        // given mover is in middle of the scene
        myPaddle.setX(myScene.getWidth() / 2);
        myPaddle.setY(myScene.getHeight() / 2);
        // move it up one step by "pressing" the up arrow
        // PAUSE: not typically recommended in tests
        press(myScene, KeyCode.LEFT);
        // PAUSE: but useful when debugging to verify what is happening
        // then check its position has changed properly
        assertEquals(190, myPaddle.getX());
        assertEquals(200, myPaddle.getY());
    }

    /**
     * this method tests the intial health and score positiining
     */
    @Test
    public void testHealthAndScore(){
        assertEquals(7 * Game.SIZE/8, hLabel.getLayoutY());
        assertEquals(0,hLabel.getLayoutX());
        assertEquals(Game.SIZE * 4/5,scoreTrack.getLayoutX());
        assertEquals(Game.SIZE* 7/8, scoreTrack.getLayoutY());
    }

    /**
     * For this test, we test the removal logic of this game by testing what happens when the delete keyCode is pressed. If both values are the same, that means the brick is removed, because
     * A powerUp is added right after, so if the reaming bricks equas the starting bricks, then you know that a brick was removed, because otherwise the remaining bricks would have more nodes in the actual root.
     * Because this test uses the same logic, this test also functionally tests if bricks are deleted after a collision,
     * so if the collision test works, and this test works, then this feature works
     */
    @Test
    public void testBrickDestroyed(){
        int startingBricks = myGame.brickCount();
        press(myScene,KeyCode.D);
        int remainBricks = myGame.brickCount();
    assertEquals(startingBricks -1 ,remainBricks);
        press(myScene,KeyCode.D);//test if it registers multiple keypresses or just one
        int remainAfterdouble = myGame.brickCount();
        assertEquals(startingBricks - 2, remainAfterdouble );
        press(myScene,KeyCode.D);
        int remainAftertrip = myGame.brickCount();
        assertEquals(startingBricks - 3, remainAftertrip );

    }

    /**
     * this test checks that the R key properly resets the posirtion of the ball and the paddle to their origins
     */
    @Test
    public void testReset(){
        press(myScene,KeyCode.R);
        assertEquals(myBall.getX(),0);
        assertEquals(myBall.getY(),myGame.SIZE/2);
        assertEquals(myPaddle.getX(),myGame.SIZE/2);
        assertEquals(myPaddle.getX(),myGame.SIZE/2);
    }

    /**
     * this test tests the level skipping cheat keys 1-5, also tests the L key level skipper
     */
    @Test
    public void testLevelSkippers(){
        press(myScene,KeyCode.L);
        assertEquals(2,myGame.getCurLevel());
    press(myScene,KeyCode.DIGIT1);
        assertEquals(1,myGame.getCurLevel());
        press(myScene,KeyCode.DIGIT2);
        assertEquals(2,myGame.getCurLevel());
        press(myScene,KeyCode.DIGIT3);
        assertEquals(3,myGame.getCurLevel());
        press(myScene,KeyCode.DIGIT4);
        assertEquals(4,myGame.getCurLevel());
        press(myScene,KeyCode.DIGIT5);
        assertEquals(5,myGame.getCurLevel());
        press(myScene, KeyCode.L);
        assertEquals(5,myGame.getCurLevel());
    }

    /**
     * This test check powerUps,cheat codes that are responsible for changing paddle/ball speed/size
     */
    @Test
    public void testBiggerCheats(){
        double beforeExpBall = myBall.getFitWidth();
        press(myScene,KeyCode.B);
        assertEquals(2 * beforeExpBall, myBall.getFitWidth());
        double startPad = 260;//we klnow this
        press(myScene, KeyCode.O);
        assertEquals( startPad, myPaddle.getFitWidth());
        double padspeed = myPaddle.getPadSpeedX();  //because both x and y speed are updated, you only have to check for 1
        double balspeed = myBall.getBallSpeedX();
        press(myScene,KeyCode.M);
        assertEquals(2 * balspeed, myBall.getBallSpeedX());
        assertEquals( 2 * padspeed, myPaddle.getPadSpeedX());
}



}
