// @author: Mia Kobayashi
// @date and version: 30 Mar 2022, v.2
// CS245 Assignment 2: Roll Bounce

package A2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;
import A2.ListNode;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;


public class RollBounce extends JPanel implements ActionListener {

    final Timer tm;

    Ball b;

    private static List<Ball> allBalls;
    private static int minSpeed, maxSpeed;
    private static int gravity, friction, timerDelay, balls, windowHeight, windowWidth, ballRadius;
    private static String list;

    public class Ball {
        private Color color;
        private int xCoord, yCoord;
        private int xVel, yVel;

        public Ball() {
            this.yVel = 0;
        }

        public void setCoords(int x, int y) {
            this.xCoord = x;
            this.yCoord = y;
        }

        public void setVel(int xV) {
            this.xVel = xV;
        }

        public void setColor(Color color) {
            this.color = color;
        }

    } //end Ball class


    public RollBounce (String propertyFileName) {
        gravity = 10;
        friction = 2;
        minSpeed = 5;
        maxSpeed = 35;
        timerDelay = 75;
        balls = 7;
        windowHeight = 480;
        windowWidth = 640;
        ballRadius = 20;

        allBalls = new ArrayList<Ball>();

        for (int i = 0; i < balls; i++) { //create all balls
                allBalls.add(new Ball());
        }

        for (Ball ball : allBalls) { //set starting coords & vels & color
            int x = random(0, windowWidth);
            int y = random(0, windowHeight);

            int xV = random(minSpeed, maxSpeed);
//            int yV = 0;

            ball.setCoords(x, y);
            ball.setVel(xV);
            ball.setColor(randColor());
        }

        tm = new Timer(timerDelay, this);

//        Properties p = new Properties();
//        try {
//            ClassLoader classLoader = RollBounce.class.getClassLoader();
//            URL res = Objects.requireNonNull(classLoader.getResource(propertyFileName), "Can't find file.");
//            InputStream inputStream = new FileInputStream(res.getFile());
//
//            p.load(inputStream);
//            gravity = (int) p.get("gravity");
//            friction = (int) p.get("friction");
////            allBalls = (List<Ball>) Class.forName((String) p.get("list")).getDeclaredConstructor().newInstance();
//            list = (String) p.get("list");
//            minSpeed = (int) p.get("minspeed");
//            maxSpeed = (int) p.get("maxSpeed");
//            timerDelay = (int) p.get("timerDelay");
//            balls = (int) p.get("balls");
//            windowHeight = (int) p.get("window_height");
//            windowWidth = (int) p.get("window_width");
//            ballRadius = (int) p.get("ball_radius");
//
////            if (list.equals("arrayList")) {
////                allBalls = new ArrayList<Ball>();
////            }
////            else {
////                allBalls = new ListNode<Ball>();
////            }
//
//            allBalls = new ArrayList<Ball>();
//
//            for (int i = 0; i < balls; i++) { //create all balls
//                allBalls.add(new Ball());
//            }
//
//            for (Ball ball : allBalls) { //set starting coords & vels & color
//                int x = random(0, windowWidth);
//                int y = random(0, windowHeight);
//
//                int xV = random(minSpeed, maxSpeed);
//                int yV = random(minSpeed, maxSpeed);
//
//                ball.setCoords(x, y);
//                ball.setVels(xV, yV);
//                ball.setColor(randColor());
//            }
//
////            tm = new Timer(timerDelay, this); // TODO: Replace the first argument with delay with value from config file.
//
//        } catch (Exception e) {
//            System.err.println("Property was not found, using default values.");
//            gravity = 10;
//            friction = 2;
//            minSpeed = 5;
//            maxSpeed = 35;
//            timerDelay = 75;
//            balls = 7;
//            windowHeight = 480;
//            windowWidth = 640;
//            ballRadius = 20;
//        }
    } //end RollBounce()



    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Probably best you leave this as is.

        for (Ball b : allBalls) {
            g.setColor(b.color);
            g.fillOval(b.xCoord, b.yCoord, ballRadius, ballRadius);
        }

        // Recommend you leave the next line as is
        tm.start(); //then all the actionPerformed stuff happens
    } //end paintComponent()



    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        for (Ball b : allBalls) {
            if (b.xCoord <= ballRadius || b.xCoord >= windowWidth - ballRadius) { //hits left or right
                b.xVel = -(b.xVel - friction); //change vel
            }
            if (b.yCoord <= ballRadius || b.yCoord >= windowHeight - ballRadius) { //hits top or bottom
                b.yVel = -(b.yVel - friction); //change vel
            }

            if (b.xCoord + b.xVel >= windowWidth) {//hits right
                b.xCoord = windowWidth - ballRadius;
            } else {
                b.xCoord += b.xVel; //move horz
                if (b.xCoord > windowWidth - ballRadius) {
                    b.xCoord = windowWidth - ballRadius;
                }
            }

            if (b.yCoord <= windowHeight - ballRadius) { //apply gravity only if ball is not touching bottom
                b.yVel += gravity; //continue to drop ball (in its direction)
                System.out.println("coord: " + b.xCoord + " " + b.yCoord);
                System.out.println("vels: " + b.xVel + " " + b.yVel);
                System.out.println("adding garivyt");
                if (b.yVel >= -friction && b.yVel <= friction) {
                    b.yVel = 0;
                }
            } //fixme?

            if ((b.xVel >= -friction && b.xVel <= friction) && b.yCoord == windowHeight - ballRadius) {
                b.xVel = 0;
            }

            if (b.yCoord + b.yVel >= windowHeight - ballRadius) { //if moving the ball hits bottom
                b.yCoord = windowHeight - ballRadius;
                if (b.xVel != 0) {//if ball still rolling, keep it moving
                    b.xVel += b.xVel > 0 ? -friction : friction;
                }
            } else { //continue to move ball in direction of travel
                b.yCoord += b.yVel; //move vert
                if (b.yCoord >= windowHeight - ballRadius) {
                    b.yCoord = windowHeight - ballRadius;
                }
            }
            repaint(); //show the changed ball position
        } //end for
    } //end actionPerformed()


    private static Color randColor () {
        int r = random(0, 255);
        int g = random(0, 255);
        int b = random(0, 255);

        Color color = new Color(r, g, b);

        return color;
    } //end randColor()


    private static int random(int minBound, int maxBound) {
        Random rand = new Random();
        int randNum = rand.nextInt((maxBound - minBound) + 1) + minBound;
        return randNum;
    } //end random()




    public static void main(String[] args) {
        RollBounce rb = new RollBounce("RollBounce.prop"); //RollBounce(args[0]);

        JFrame jf = new JFrame();
        jf.setTitle("Roll Bounce");
        jf.setSize(windowWidth, windowHeight + (ballRadius * 2)); //640x480
        jf.add(rb);
//        jf.getRootPane().setBorder(BorderFactory.createMatteBorder(windowWidth, windowHeight, windowWidth, windowHeight, Color.BLACK));
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } //end main()
}
