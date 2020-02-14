package breakout;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


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
        public static final int BLOCK_MIN_SPEED = 10;
        public static final int BLOCK_MAX_SPEED = 100;
        public static final int BLOCK_SPEEDUP_FACTOR = 2;
        public static final int BRICK_AMOUNT = 5;
        public static final int BALL_SPEED = 50;

    public static final String BALL_PICTURE = "https://vignette.wikia.nocookie.net/idle-breakout/images/4/4b/Screen_Shot_2019-04-06_at_4.04.05_PM.png/revision/latest/top-crop/width/360/height/450?cb=20190406210459";

    public static final String PADDLE_PICTURE = "https://docs.microsoft.com/en-us/windows/uwp/get-started/images/monogame-tutorial-1.png";
    // some things we need to remember during our game
    public static final String BIGGERPADDLE = "https://i.pinimg.com/originals/b4/92/d5/b492d594465b29bcbe7ff840fa18c896.png";
        private static final double BALL_PENALTY = 0.25 ;
    private static final String GREEN = "green";
    private static final String BLUE = "blue";
    private static final String ORANGE = "orange";
    private static final String RED = "red";
    private static final String WHITE = "white";
    private static final String PURP = "purple";
    private static final String BLACK = "black";
    //TODO: Find better Brick textures, all you have to do here is just add themm to the sources, and then read the file like how i showed you
    private static final String GREEN_BRICK = "https://images-na.ssl-images-amazon.com/images/I/410eZ0DDF2L._SL500_AC_SS350_.jpg";
    private static final String BLUE_BRICK = "https://images-na.ssl-images-amazon.com/images/I/51DwBLBY5VL._AC_SL1001_.jpg";
    private static final String Orange_BRICK = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxISEBAPEhAQEBAVFRUVEA8VEBUQFRUQFRUXFhUWFRUYHSggGBolGxUVITEhJSk3Li4uFx8zODMsNygtLisBCgoKDg0OGhAQGy0lICUtLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLf/AABEIAOEA4QMBEQACEQEDEQH/xAAcAAEAAgMBAQEAAAAAAAAAAAAAAQQDBQYCBwj/xABBEAACAQICBQgHBQcEAwAAAAAAAQIDEQQhBQYSMUETMlFhcYGRoSIjQlJyscEHYpKy8BQkY4LR4fFzorPCFjND/8QAGgEBAAMBAQEAAAAAAAAAAAAAAAEDBAUCBv/EAC4RAQACAgIBAwMCBgIDAAAAAAABAgMRBCExBRJBEzJRIjMVIzRCcYEUYSSRof/aAAwDAQACEQMRAD8A+4gAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIAAAAEgYMZio0oOpN2it7+SXWeb3ikbl6pWbTqHM43XOMU3GnkuMn/1j/UwX58R4h0sXpl7eZarE69Vs1GnTW7NpvwSebM9vUr/ENlPR8c+bLWjNeZSaVShf78G0sulPd4nvF6jv7qqc/pPt+2zr8DjI1YKpB3T8U+KfWdOl4vX3Q5GSk0t7Z8rB7eAAAAAAAAAAAAAAAAAAAAJAAQ2BUr6UowylVpp9G0m/BFdstK+ZWVw3t4hTrazYSHOrrujN+SRVPLxR/cujg8ifFJWqGl6E47Ua9KUeL21l29BZXNS3cSqtgyVnU1lymvunKUsLKnTm3Pbg1JJpZPOze/K5h5uelsc1ie3U9L4t4zRa0dOFp42Tzdr8ZL0X32395w7ZJ2+l+hHwyftCV/R275u/oq/wxyfeR9SI+Hn6Np+WPEaZqJWiox7Ff5lsZJkji0+XXag6xU6eHcKm25OpKUp22s2lvzudficita6lwPUuFe+X3U8Oxen8Ps7XKp/dSe1+G1zbPIx63tyo4uWZ1prMVrlSje0JPjnJJtLoSuZ7c+kNVPTMtmvevyUkv2d7Nr35SzfYmreLKP4pEf2tH8GtMdW7bvRetGHr2ScoO9rSVs+i5sxcvHk8MOfg5sXmNt2mamNIAAAAAAAAAAAAAAEAAOM1+0xKjKjSu1Cak5Wdm7NJX6szm8/PbHqIdf0vjRlm1p+HGVMXtWW13xur/FF2fgzkTk93y71cPt+GKME98oQu25Xku7JZ8dzZ4mIl7iZjxDLHE0KXpOo5Stwu7/rrZZWaV7iZ2rtTJk601+ksa6uHckrLlMl1L/JdM7jcppSaZNKGErcHkY8lfw3xPS5Oa6SnUkSq1c9xbXry9RP4brV2Fqcvi+iNWKYlzuX9z1X0jKNSpB5xytZ7LXorcyq2WazpNOPF6xLz+0KV887Wu5KnK27Np2l4Hj6kT5evpTVEatJZy2pPoUVCN+55kxenyTjyTHTFjdPZONOOzfLafC/FItrk76eJ4mo3bt9b0XpejOnTSrQctmN05WbdlffvPoceWs1jt8jlwXraemxqVoxW05KK6W0l4lnuj8qYrMzqIa+rp/Dx/wDpfsi352sVTyMcfK+vFy28QqLW7CbTjyrVt8nTlsp/FaxX/wA7DvW1s+nciI37W2wmOp1M6dSM+xp+RopkrfxLLfHek/qjSye3gAAAAAAAAi4GlxusuHpuUVPlJxbUoQzs1vTe5Ga/Kx0mY3214uFmvETrUNHjNbasrqnGNNdPPl55LwMeTm2n7Ybcfp1Y++duM1kxU6s4Oc5TaT3u9t27oOdyb2tP6pd3g4aY4n2w1tNtZq6Zi22zET5RUbbu7vrbJ2mIiPCnXiW0l602OBoOWFkknlO/dlcutP6GS0xGWNrmjsLR2dprlJcY9H8vE8U9vy8ZL392o6WKtfnbKiopZWitpP7ye7wJm6ulZ+ZV9iU3l6bsrtbvHJdx49trTC33VrE7X6EOThJy2IrfdZLvyS8i+ke2O2XJPvnpzWk8X66UuDUWuxxR4vTboYJ1XUs2Hq3Mlq6Xz2mZEChXjfJGiqZh2eBj6uF/dj8jXWeunEyRG52xrSivKm3Zxk1FO+zk7cN2RXPImOpev+H4tEMGIqbV23k1bZbc4dzg+lcVcrm++9ra011EPEMJKVnnCKXOnZPsSVvF2ZEU3rt6nJrxG5Z3pGjhk5Qm9retmW09rt3Ivx5K4/tZ74L5vuh9S0RiHUw9GpLnTpwk+1xTZ9BjtusS+WyV9t5hcPbwgDy6iXFeJ5mYhPtl6TPSEgAAAD4lpyFsViXe3rqtvxs+U5PWWz7bhzE4Kb/CvTxtSPtX7cyuuW0LrYaz8PGKrco0+a11k3ye7y9Y8cUeKbd+DKulm0VJO7yQjT1EsTh0nr3fg23mhV6v+Z/Q04u4c/kfe1M+fP4pfNma/lsrG4jbN+0O2aUuhu+XZmRFtPNscTPU9PM8dU9+XieotP5eow18zDWYurKXOk32u5bV79kRHUMtehdr4YfkiTa+peKV6bHR2iZSW1fZi93X3ERWb9qsnIinULMtHU03FzqSkldpL+xM0pDxHIyT4hVlhoezez3Xab77biqdLa5JbvBYiLioq94pX7Fle6NeO8TGmDLSYnbnMZiFy1T45fMzZKTM7dPFqaQyUa74PtKe6vVqRbzDzNEQmIj8NfiUaKJtHTt9B4upGlScak4+hG9pNXdlw4nQpmtWOpcDPgpa07iJbj/yatH1bnFya9GTir7+HBss/wCbevVpZf4dS3ceGox+lqkrpzlKd9021+FOyfcZr8m0z5bMXEx1+Goxjbu5u9s47Vo2v0Re/o3ma1rW+W2lKV8RC/ovE4mFnGrOnG/Ncm7rqT+vgX4cmWvcSzZ8WC/Xt/27nVnTrrynRnZ1IRUnJcYt2zXBnY42ecnUuBzOL9GYmPEuiNbEAQB8Y1kVsXiV/Fn5u58py/3rPtPT++PSf+mssZm0sNo2mwTsINmyv8ExJuWyweJhGKhdrra49xqplrrTHkxXtbbWTdpPLK7tbouUW7npsp4hkpqLTeeXUedSmZ70wTa/SJiHtXnSv1Isi2k7jTfR0dFwg4vZeyr8U8i22L3dsEZ5rMxKpSxUo3g7SjdrZeayfDiU+6a9LbYq37Z1iIyTu0uhTvL8LtdeJMX/ACrnHMT4eY4qkrbSnNpWzsl4ce8mLV13CZxXnxLzidYLK0IJdDbvbuRdGSZ8PMcT8y0Fbam9tvN72Pd+WmI9vUM+F2kU30siV+nhasllTk107l4siuOZVzmpXzLxW0XUWcopL4ovPsTPUxNURnpbw6PR81ycI3Taik10NIvpeJhzctZ90y1mn5+nT+F/Mqzx4a+HEe2dqtPGy3bTcfdbuvBlETMNFsVZ8JljJRe1FKPDmxbS6nbJHr3z8PH0I+VXFaSqtc+Xc7fIsi0yThpHiGy1D0vOhWrTioy2opPavuUuo3cbN9OXK5vFjNEbl9Gwut8Hz6Uo9cWpLzsdCvNr8uPf069fEtthdNUKllGrFPhGXoPzL658dvEst+Plp5hsLlu1L41rSv33FL+I/NI+X5v79n2fps/+NRqjI2pAECQCIHoBYkE964PeNkvGwuwnaYkVFvcrnqOybadFFWSXQrG6vhzJn9UuekrtvLe/mYLz26dY/TCeTZ52mdMUoHqJetKWJRfRKxh6D2U2rJ7mebzLzFo3psdG1KcJ3kuGTtez7BjtETuWfPS1q/pbSvXck1F5cJwe3l1reu4utkmfDHXHqdypSScnb0pNeyuUfa9rOJX3K+NRDNRwDtebcFws1dd9rLuPdMeu5l5vm+Kwo6yYik4xUWpVFuad8uKbLLTEnH99bNDSxDvmVzSG6LriqlPteoYqqueq9Ilf1dp7M5t+73bzRS+5YuRXS9jNOQj6MPWS6uau89e5njH+WroYypVr0FKWXK07RWSznHgesX3x/mHnNqMVtfiX36x3dPlHx/XBWx2J+NfkifNc79+z7D0z+mq0xkb0gEQJIEoCQJAAAIJOmWniJR3Sdujej1F5h4nHW3mGCUE3fcR7lkdRpkp3SktrsHSJ8q8oy43PXT37njkifcbdFo6FqUF1fN3+prpEe1zcsz750o6YglKFklk72VuJRniInUNPG3NfKlGTVn9L+RRHS6axKauLm+Nvh9HyRZuZRXFWI0pYirJ75N9uZ7rBNYjxCjTV5l0z0rpX9W16lhrtFE3XeFqrhrO363HiZki3SrVrRjlzn0L+pbWkz5VXzRHhgnUlJWbtH3Vku/pNFccQy3yzZ5tY9q9rugqd8XhV/Gpf8kT3ij9dVXInWK3+H6AsdzT5d8g10X7/AIjtj+SJ8zz/AN+z670r+mhpUYnRAJAECQJQEgAJIEAAAAlASky6PoTEolsaOPjZRacbZdKyNFM0eJZL4Lb3CrpKptSTjaSt9TxltFp6X8eJrGpVYSi96aKtSvnorKKdriNlZ3CpVz3Ftekp0bhb1YXzz3PdaxbF4mdKckaja7jcXQpu0XKpNezGS2U+uVvkPpRtRGW8x21eIxU6jbk7J+ysvHpLq44hVbJPiGNRRZpXvbIS8ob4EbTENnqxTvjcJ/rU/KSf0LMEROSqnlfs2/w+7nbfMvkmvK/f638n5InzXqP78vrfSf6aP8tCYXTCBKAIgegAEgAJIAAACEACdiLEgEhAjaJESSfUTsjpXxNWMLb23uSLcdJs8XyxTyo1a85Zc2PQuPa+JqrjiGS2abEYJFulPumXtImIQ9qJOkPdgIaIlMbbjU6nfH4X/UT8E2XceP5lVHMn+RZ9uOxp80+Ya/6Oq/tU63JzlSlGNqii5JNKzTtu3HB9RwZPqe+I6fTekcnFGH6czqXKJnLmNeXb/wAJI0BAkCUBJAkAgJIAAAABABAEEgBDCUEijjucuw2cbwycmfCvY1MmnqwS9xRKHtIkSiEwmxCYb3UaN9IYb4pP/ZIv437kMvOn+Tb/AE+znYfOFiJhLTaT1Ywte7nRUZP24erl4rf3mbLxcWSO4asXNz4vts5XSP2ezV3QrKS4QqKz/EsvI5+X0r5pLrYfW/jJX/05bSGiMRQ/9tGcF79tqP4lkc3JxctOph1sPMwZftspJmfTUkgSmBKIEgAJIAAAAAAhASEiGB5JFLF87uNvG8MfJ8sFjSzJiTAyID0DSUEwlkJh0eoEb6QodSm/9kjRxf3GL1Cf5Mvr513zz0EgEAQ49RExsjrw0ektU8JWu3SUJP26fq3fuyfejLl4WLJ5hsw8/Pi8WcrpL7P6sbuhVjUXuTWxLuksn5HOy+lT/ZLrYfW6z1lr/uHM4/Rlag7VaU6f3mvRfZJZHNycfJT7odbDy8WX7bQqplEtDFNty2V3v9byyuqxtOvllULcSubbQ9kAQAAAACAJQBBIhoCjjOd3G7jfax8nywmlmSQl7TJNPSY2Qm4NJTCXUfZ1H9/p/DP8v9zVxP3GD1Gf5L62dVwEhIAAAAAHipBNNNJrimroiYiepImYnpz+k9TcLWu1B0Z+9T9Hxju8jHl4GLJ8alvwepZ8Xidx+JaqP2d0lGXr6vKN329mOzbgtn+5nn0uk11vtq/jWX3eI00ekdTMVSu4xVaPTDnW64vPwuc/L6blp47h0MPq+G/Vupc/Ug4txknGS3xaaa7mYbUtXqYdOmSt43WdoK1gAAAAJAgCAIZIoY3n9yN3G+1j5HlgNCiC5CdDkNnt2wrEPat/T/PmJmNPUV3PTLKqePce1udXdXsTjHenDZp8a07qC7OMn1LyNOHBe8snJ5WPDGt9vqurWqtHBraTdSs1aVWWWW9qMd0VkuvLedTFgrj8eXCz8m+Xz4dBYvZwAAAAAAAAAAgCnpLRtOvTlCcIyumlJxUnF2yavxRTlw1yRq0LMWa+K0TWXz3SGo2Jp503CvHqexPweXmcTL6Xkr3Xt9Fi9ZxW1F4053FYadN7NSnOm+iUXHwOdkw3p90Orjz48kbpMSxlS0AAAAACANdjuf3I38f7WTP5V2y7akbIGKvO2XHLLre5frpJiNotbp02hPs+xmIjy0lHDxteCqXUpfyrOK63n1GqnDveJ+GK3qWPHbWtut1c+zeEJcri3GtK940Y35NfE3nPs3dppw8KK927YuT6la/WPqP/AK72lTUUoxSilkopWSXUkbojXUOXM7ncshIXAAAAAAAAAAAAABDAxV8PGa2ZxjOL3xklJeDPNqRbzCa2mvjpzmkdR8NUu6alQl9x3j+F/Sxhy+m4r+OnRw+q8jH1afdDl9I6k4qndwUa8fuvZlb4X9GczN6ZlpH6e3Ww+sYb9X6c9XoypvZnCVOXRKLi/M598V6/dDqUzUyRusxLwVrAABAGt0hz+5fI3YPtZM/lgjFsuiJlRNoh7lllve5Lfdlvt+IeItvt9h1W1Kw+Garyjy2JebqT9hvhTjujbdff1nVw4K1jenz3I5V8lpjfTqkjSypAAAAAAAAAAAAAAAAAAAABDQGHFYSFSOzUhCceiUVL5ni+Ot+rRt6pe1J3WdOa0jqHh53dKUqEuhenC/wv6MwZfTMV+46dPB6vnx9W7hy2kNTsVSu4xVePTTfpfgefgczL6blp47dbB6xgv1bpoKkXF7Mk4yW+Mk4vwZgtjtWdTDp0yUv9s7QedPc9Ndi4Xm+75HQ49d1YeRaIls9BavYjFu1KFqadpV5ZQXTZ+0+pG7Hitbw5ubkY8Xdp7/D6fq5qdh8LaduWr8a80m0/uR3RXn1nQxcetO/lx8/LyZf+o/Do0jQzJAAAAAAAAAAAAAAAAAAAAAAAAAEWArY3R9KqtmpThUX3op+HQV3xUvH6oWUy3pO6zpzOkdQqMrulUnRfuv1kfB5rxOfl9Lx27p06eH1jNTq/cK2iPs9pxm6mJmq261JJxhl73GXZu7S3jcKMcat2q5XqVss7r07alSUYqMUoxSsopWSXQkjdERHUObM77ekSJAAAAACAJAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB/9k=";
    private static final String RED_BRICK = "https://img.brickowl.com/files/image_cache/larger/lego-red-brick-2-x-4-3001-2-771344-81.jpg";
    private static final String White_BRICK = "https://brickit.com/images/_site/shapes/shape-image-9.png";
    private static final String PURPBRICK = "https://www.lightstax.eu/wp-content/uploads/2016/10/regular-purple-2x4.png";
    private static final String BLACK_BRICK = "https://img.brickowl.com/files/image_cache/larger/lego-black-brick-2-x-4-3001-2-771344-38.jpg";
    private static final int lvlcounter = 1;


    // some things we need to remember during our game
        private Scene myScene;
        private Timeline myAnimation;
        private Paddle myPaddle;
        // TODO: add many blocks
        private Ball myBall;
        private ImageView boundary;
        private int myBlockSpeedX, myBlockSpeedY;
        private Bricks [][] level;//this is a 2D array of our bricks
        private ProgressBar healthBar;
        private HBox healthBarLabel;
        private Label hLabel;
        private Label scoreTrack;
        private int myScore;
        private Text scoreText;
        private ArrayList<ArrayList<Bricks>> levelAsList;
        private boolean winCon = false;
        private PowerUp bigpaddie;
        private Group root;
        private boolean isPowerUP = false;
        private Menu myMenu;

        //StackPane menu
    /**
     * this code is from stack, essentially converts
     * @param twoDArray 2d array of bricks
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
     * this method constructs the grid of bricks by reading the config file for a level
     * @param levelSource string representing filename
     * @return
     */
    private Bricks [][]  levelReader(String levelSource, int width, int height){
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
    private Bricks makeBrick(String brickType,int i,int j,int width,int height) {
        if (brickType.toLowerCase().contains(GREEN)) {
            Image defaultBrick = new Image(GREEN_BRICK, 30, 30, false, false);
            return new Bricks(defaultBrick, (int) (i * width / BRICK_AMOUNT), (int) ( .5 * j * height / BRICK_AMOUNT),1);
        }
        if(brickType.toLowerCase().contains(BLUE)){
            Image blueBrick = new Image(BLUE_BRICK, 30, 30, false, false);
            return new Bricks(blueBrick, (int) (i * width / BRICK_AMOUNT), (int) ( .5 * j * height / BRICK_AMOUNT),2);
        }
        if(brickType.toLowerCase().contains(ORANGE)){
            Image oranBrick = new Image(Orange_BRICK, 30, 30, false, false);
            return new Bricks(oranBrick, (int) (i * width / BRICK_AMOUNT), (int) ( .5 * j * height / BRICK_AMOUNT),3);
        }
        if(brickType.toLowerCase().contains(RED)){
            Image redBrick = new Image(RED_BRICK, 30, 30, false, false);
            return new Bricks(redBrick, (int) (i * width / BRICK_AMOUNT), (int) ( .5 * j * height / BRICK_AMOUNT),4);
        }
        if(brickType.toLowerCase().contains(WHITE)){
            Image whiteBrick = new Image(White_BRICK, 30, 30, false, false);
            return new Bricks(whiteBrick, (int) (i * width / BRICK_AMOUNT), (int) ( .5 * j * height / BRICK_AMOUNT),5);
        }
        if(brickType.toLowerCase().contains(PURP)){
            Image purpBrick = new Image(PURPBRICK, 30, 30, false, false);
            return new Bricks(purpBrick, (int) (i * width / BRICK_AMOUNT), (int) ( .5 * j * height / BRICK_AMOUNT),6);
        }
        Image blackBrick = new Image(BLACK_BRICK, 30, 30, false, false);
        return new Bricks(blackBrick, (int) (i * width / BRICK_AMOUNT), (int) ( .5 * j * height / BRICK_AMOUNT),6);

    }
        /**
         * Initialize what will be displayed and how it will be updated.
         */
        @Override
        public void start (Stage stage) {
            // attach scene to the stage and display it
            myScene = setupScene(SIZE, SIZE, BACKGROUND);
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


        // Create the game's "scene": what shapes will be in the game and their starting properties
        Scene setupScene (int width, int height, Paint background) {
            // create one top level collection to organize the things in the scene
            root = new Group();
            // make some shapes, set their properties, and add them to the scene
            Image ballImage = new Image(BALL_PICTURE,30,30,false,false);
            myBall = new Ball(ballImage,width/2 - 15,height/2 +60);

            myBall.setSpeed(BALL_SPEED);
            root.getChildren().add(myBall);
            myPaddle = new Paddle(new Image(PADDLE_PICTURE, BLOCK_SIZE + 100,BLOCK_SIZE,false,false),width/2 , height - 100);
            myBlockSpeedX = BALL_SPEED;
            myBlockSpeedY = BALL_SPEED;
            root.getChildren().add(myPaddle);

            // create a place to see the shapes
            myScene = new Scene(root, width, height, background);
            level = levelReader("data/lev1.txt",width,height);
            levelAsList = twoDArrayToList(level);
            for(ArrayList<Bricks> brickies:levelAsList){
                for(Bricks brick: brickies){
                    root.getChildren().add(brick);

                }
            }
            //create boundary that ball cannot pass over
            boundary = new ImageView();
            boundary.setImage(new Image("https://i.redd.it/rkfe2i3pdqqx.jpg",myScene.getWidth(),BLOCK_SIZE,false,false));
            boundary.setY(4 * height/5);
            boundary.setId("boundary");
            root.getChildren().add(boundary);
            healthBar = new ProgressBar(1);
            healthBarLabel = new HBox();//https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/progress.htm I am gonna use a ProgressBar to represent the health we have

            hLabel = new Label("Health",healthBar);
            hLabel.setLayoutY(7 * height/8);
            hLabel.setId("hLabel");
            root.getChildren().add(hLabel);
            myScore = 0;
            scoreText = new Text(myScore + "0");
            scoreTrack = new Label("Score: ", scoreText);
            scoreTrack.setLayoutY(7*height/8);
            scoreTrack.setLayoutX(width * 4/5);
            scoreTrack.setId("scoreTrack");
            root.getChildren().add(scoreTrack);
//            BufferedImage img = null;
//            try {
//                img = ImageIO.read(new File(Main.class.getClassLoader().getResource("Images/gameover.png").getFile()));
//            } catch (IOException e) {
//                System.out.print(e);
//            }
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




            // respond to
            myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
            return myScene;
        }

        // Change properties of shapes to animate them
        void step (double elapsedTime) {


            // get internal values of other classes
//            Rectangle blockShape = myBall.getShape();
//            Rectangle moverShape = myPaddle.getShape();

            // update attributes
            dropPowerUp(elapsedTime);

            updateBall(elapsedTime);

            // check for collisions TODO: Make  a physics class that handles this, for now I will figure out how to do this later
//            if (Shape.intersect(myPaddle, myBall).getBoundsInLocal().getWidth() != -1) {
//                // reset player's position to the start
//                myPaddle.setX(myScene.getWidth() / 2 - myPaddle.getWidth() / 2);
//                myPaddle.setY(myScene.getHeight() - myPaddle.getHeight());
//            }
            //this is for when the ball hits the boundary
            if(myBall.getBoundsInParent().intersects(boundary.getBoundsInParent())){
                myBall.setX(0);
                myBall.setY(myScene.getHeight()/2);
                healthBar.setProgress(healthBar.getProgress() - BALL_PENALTY);
                hLabel = new Label("Health", healthBar);
            }
            if(healthBar.getProgress() == 0){//this is if you run out of health
                System.out.println("You lost the game and you suck");
                root.getChildren().clear();
                root.getChildren().add(myMenu);
                // TODO: Figure out how to get the button on the menu
               Button ret = new Button("Reset");
                     //  myMenu.getReset();
                root.getChildren().add(ret);
                myAnimation.stop();

                //hbox vbox grid pane
            }
            //if you hit the paddle, bounce as if you hit the wall
            if(myBall.getBoundsInParent().intersects(myPaddle.getBoundsInParent())){
                myBlockSpeedY *= -1;
                myBlockSpeedX *= 1;

            }
            //check for case when you hit a brick
            updateBricks();
            destroyBricks();
            // TODO: check for win and, if true, pause the animation
        }

    /**
     * this method handles updating the health of bricks as they are collided into
     */
    private void updateBricks() {
        for(ArrayList<Bricks> brickies: levelAsList){
            for(Bricks brick: brickies){
                if(myBall.getBoundsInParent().intersects(brick.getBoundsInParent())){
                    myBlockSpeedY *= -1;
                    myBlockSpeedX *= -1;
                    brick.updateHealth();
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
        for(ArrayList<Bricks> brickies:levelAsList){
            Iterator<Bricks> itr = brickies.iterator();
            while(itr.hasNext()){
                Bricks brick = itr.next();
                if(brick.isDestroyed()){
                    brick.setImage(null);
                    itr.remove();
                    root.getChildren().remove(brick);
                    if(isPowerUP == false) {
                        isPowerUP = true;

                        bigpaddie = new PowerUp(new Image(BIGGERPADDLE,30,30,false,false), brick.getX(), brick.getY());
                        root.getChildren().add(bigpaddie);
                    }
                }
            }
        }
    }

    /**
     * This method updates the positioning of the ball
     * @param elapsedTime
     */
    private void updateBall(double elapsedTime) {
        myBall.setX(myBall.getX() + myBlockSpeedX * elapsedTime);
        if (myBall.getX() + myBall.getWidth()>= myScene.getWidth() || myBall.getX() <= 0 ) {
           myBlockSpeedX *= -1;
        }
        myBall.setY(myBall.getY() + myBlockSpeedY * elapsedTime);
        if (myBall.getY()  >= myScene.getHeight() || myBall.getY() <= 0 ) {
            myBlockSpeedY *= -1;
        }
    }

    /**
     * This method drops a powerup from a brick that was destroyed on the PREVIOUS frame
     * @param elapsedTime how much time has elapsed
     */
    private void dropPowerUp(double elapsedTime) {
        if(isPowerUP) {
            bigpaddie.setY(bigpaddie.getY() + 50 * elapsedTime);
            if (bigpaddie.getBoundsInParent().intersects(myPaddle.getBoundsInParent())) {
                myPaddle.setFitWidth(myScene.getWidth()-50);
                isPowerUP = false;
                root.getChildren().remove(bigpaddie);
            }
        }
    }


    /**
     * counts the bricks remaining in the grid
     * @return the int with bricks
     */
    private int brickCount(){
        int brickAmount = 0;
        for(ArrayList<Bricks> brickies:levelAsList){
            Iterator<Bricks> itr = brickies.iterator();
            while(itr.hasNext()){
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
            ImageView moverShape = myPaddle;
            if (code == KeyCode.RIGHT) {
                moverShape.setX(moverShape.getX() + PADDLE_SPEED);
            }
            else if (code == KeyCode.LEFT) {
                moverShape.setX(moverShape.getX() - PADDLE_SPEED);
            }
            else if (code == KeyCode.UP) {
                moverShape.setY(moverShape.getY() - PADDLE_SPEED);
            }
            else if (code == KeyCode.DOWN) {
                moverShape.setY(moverShape.getY() + PADDLE_SPEED);
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
            if(code == KeyCode.P){
                isPowerUP = true;
                bigpaddie = new PowerUp(new Image(BIGGERPADDLE,30,30,false,false), 0, 0);
                root.getChildren().add(bigpaddie);
            }
            if(code == KeyCode.B){
                myBall.setFitWidth(myBall.getWidth() * 2);
            }
            if(code == KeyCode.D){// we are going to break the first undamaged brick
                for(ArrayList<Bricks> brickies:levelAsList){
                    Iterator<Bricks> itr = brickies.iterator();
                    while(itr.hasNext()){
                        Bricks brick = itr.next();
                        if(!brick.isDestroyed()){
                            brick.setImage(null);
                            itr.remove();
                            root.getChildren().remove(brick);
                            if(isPowerUP == false) {
                                isPowerUP = true;

                                bigpaddie = new PowerUp(new Image(BIGGERPADDLE,30,30,false,false), brick.getX(), brick.getY());
                                root.getChildren().add(bigpaddie);
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
        }
        public static void main (String[] args) {
            launch(args);
        }



    }
