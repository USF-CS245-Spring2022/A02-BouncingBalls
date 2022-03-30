// @author: Mia Kobayashi
// @date and version: 30 Mar 2022, v.2
// CS245 Assignment 2: Roll Bounce

package A2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.*;
import java.util.List;
import A2.ListNode;

public class RollBounce extends JPanel implements ActionListener {

    protected Timer tm;

    private List<Ball> allBalls;
    private static int minspeed, maxspeed;
    private static int gravity, friction, timerDelay, balls, window_height, window_width, ball_radius;

    Ball ball1;


    public class Ball {
        private Color color;
        private int xCoord, yCoord;
        private int xVel, yVel;
        private double ballHeight = 10;
        private boolean moveDown = true, moveRight = true;

        public Ball() {
//            size = new Dimension(ball_radius, ball_radius);
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }

    } //end Ball class


    public RollBounce (String propertyFileName) {
        // TODO: insert your code to read from configuration file here.
        gravity = 10;
        friction = 2;
        minspeed = 5;
        maxspeed = 35;
        timerDelay = 75;
        balls = 7;
        window_height = 480;
        window_width = 640;
        ball_radius = 20;
        ball1 = new Ball();

        tm = new Timer(timerDelay, this); // TODO: Replace the first argument with delay with value from config file.


        ball1.xCoord = 20;
        ball1.yCoord = 20;
        ball1.xVel = 10;
        ball1.yVel = 10;
    } //end RollBounce()



    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Probably best you leave this as is.

        g.fillOval(ball1.xCoord, ball1.yCoord, ball_radius, ball_radius);

        // Recommend you leave the next line as is
        tm.start(); //then all the actionPerformed stuff happens
    } //end paintComponent()



    @Override
    public void actionPerformed(ActionEvent actionEvent) {
//        System.out.println("ball coords: " + ball1.xCoord + " " + ball1.yCoord);
//        System.out.println("ball vels: " + ball1.xVel + " " + ball1.yVel);
//        System.out.println("ballHeight: " + ball1.ballHeight);



//        ball1.yVel += gravity;

//        ball1.xCoord += ball1.xVel;
//        ball1.yCoord += ball1.yVel;
//
//        if (ball1.xCoord < 0) {
//            ball1.xVel = 1;
//        }
//        if (ball1.xCoord - ball_radius > window_width) {
//            ball1.xVel = -1;
//        }
//
//        if (ball1.yCoord < 0) {
//            ball1.yVel = 1;
//        }
//        if (ball1.yCoord - ball_radius > window_height && ball1.yVel > 0) {
//            ball1.yVel *= -0.98;
//        }
//        if (ball1.yCoord - ball_radius > window_height && ball1.yVel < 0) {
//            ball1.yVel *= 0.98;
//        }
//        repaint();


//        ball1.xCoord += gravity;
//        ball1.yCoord += gravity;

        if (ball1.moveDown) {
//            System.out.println("move down");
            if (ball1.yCoord > window_height) { //touch bottom
                ball1.moveDown = false;
                System.out.println("touch bottom");

                ball1.ballHeight *= 5; //+= (ball1.ballHeight / 2); //new height is

                ball1.xVel = ball1.xVel / friction;
                ball1.yVel = -ball1.yVel / friction;

                ball1.xCoord += ball1.xVel;
                ball1.yCoord += ball1.yVel;

                System.out.println("ball coords: " + ball1.xCoord + " " + ball1.yCoord);
                System.out.println("ball vels: " + ball1.xVel + " " + ball1.yVel);
            }
            else if (ball1.xCoord < 0) { //touch left
                System.out.println("touch left down");

                ball1.moveRight = true;

//                ball1.ballHeight += -ball1.xCoord;

                ball1.xVel *= -1;
                ball1.yVel *= -1;

                ball1.xCoord = -ball1.xCoord;
                ball1.yCoord += ball1.yVel;

                System.out.println("ball coords: " + ball1.xCoord + " " + ball1.yCoord);
                System.out.println("ball vels: " + ball1.xVel + " " + ball1.yVel);
            }
            else if (ball1.xCoord > window_width) { //touch right
                System.out.println("touch right down");

                ball1.moveRight = false;

//                ball1.ballHeight += window_width - ball1.xCoord;

                ball1.xVel *= -1;
                ball1.yVel *= -1;

                ball1.xCoord = window_width - ball1.xCoord;
                ball1.yCoord += ball1.yVel;

                System.out.println("ball coords: " + ball1.xCoord + " " + ball1.yCoord);
                System.out.println("ball vels: " + ball1.xVel + " " + ball1.yVel);
            }
//            else {
                System.out.print("just move down");
                if (ball1.moveRight) {
                    System.out.println(" right");
                    ball1.xCoord += ball_radius;
                }
                else {
                    System.out.println(" left");
                    ball1.xCoord -= ball_radius;
                }
                ball1.yCoord += ball_radius;
//            }
        }
        else {
//            System.out.println("move up");
            if (ball1.yCoord < ball1.ballHeight) { //touch top
                ball1.moveDown = true;

                ball1.ballHeight *= 5; //+= (ball1.ballHeight / 2);
                System.out.println("touch top up");

                ball1.xVel = ball1.xVel / friction;
                ball1.yVel = -ball1.yVel / friction;

                ball1.xCoord += ball1.xVel;
                ball1.yCoord -= ball1.yVel;

                System.out.println("ball coords: " + ball1.xCoord + " " + ball1.yCoord);
                System.out.println("ball vels: " + ball1.xVel + " " + ball1.yVel);
            }
            else if (ball1.xCoord < 0) { //touch left
                ball1.moveDown = true;
                ball1.moveRight = true;

                System.out.println("touch left up");

//                ball1.ballHeight = ((window_height + ball1.yCoord) / 2) + 1; //*= 1.5;

                ball1.xVel *= -1;
                ball1.yVel *= -1;
                ball1.xCoord -= ball1.xVel;
                ball1.yCoord += ball1.yVel;

                System.out.println("ball coords: " + ball1.xCoord + " " + ball1.yCoord);
                System.out.println("ball vels: " + ball1.xVel + " " + ball1.yVel);
            }
            else if (ball1.xCoord - ball_radius > window_width) { //touch right
                ball1.moveDown = true;
                ball1.moveRight = false;

                System.out.println("touch right up");

//                ball1.ballHeight = ((window_height + ball1.yCoord) / 2) + 1; //*= 1.5;

                ball1.xVel *= -1;
                ball1.yVel *= -1;
                ball1.xCoord = window_width;
                ball1.yCoord = ((window_height + ball1.yCoord) / 2) + 1; //ball1.yVel; //(window_height + ball1.yCoord) / 2

                System.out.println("ball coords: " + ball1.xCoord + " " + ball1.yCoord);
                System.out.println("ball vels: " + ball1.xVel + " " + ball1.yVel);
            }
//            else {
                System.out.print("just move up");
                if (ball1.moveRight) {
                    System.out.println(" right");
                    ball1.xCoord += ball_radius;
                }
                else {
                    System.out.println(" left");
                    ball1.xCoord -= ball_radius;
                }
                ball1.yCoord -= ball_radius;
//            }
        }
//        ball1.xCoord += ball1.xVel;
//        ball1.yCoord += ball1.yVel;
        repaint();


//        ball1.xCoord += ball1.xVel;
//        ball1.yCoord += ball1.yVel;
//        if (ball1.xCoord > window_width || ball1.xCoord < 0) { //if x coord hits R / L side
//            System.out.println("hit r/l wall");
//            ball1.xCoord = ball1.xCoord < 0 ? 0 : ball1.xCoord; //if x < 0, x = 0, else x = x
//            ball1.xCoord = ball1.xCoord > window_width ? window_width : ball1.xCoord; //if x > top, x = top, else x = x
//            // yvel -= FRICTION;
//            ball1.xVel *= -1; //change direction
//
//            System.out.println("xCoord: " + ball1.xCoord + " xVel: " + ball1.xVel);
//            // Apply friction... should probably be a function onto itself.
//            if (ball1.xVel > 0) {
//                System.out.println("r/l wall if");
//                ball1.xVel -= friction;
//                System.out.println("xVel: " + ball1.xVel);
//                System.out.println("yVel: " + ball1.yVel);
//
//                if (ball1.xVel < 0) {
//                    System.out.println("r/l wall if if");
//                    ball1.xVel = 0;
//                    System.out.println("xVel: " + ball1.xVel);
//                    System.out.println("yVel: " + ball1.yVel);
//
//                }
//            } else {
//                System.out.println("r/l wall else");
//                ball1.xVel += friction;
//                System.out.println("xVel: " + ball1.xVel);
//                System.out.println("yVel: " + ball1.yVel);
//
//                if (ball1.xVel > 0) {
//                    System.out.println("r/l wall else if");
//                    ball1.xVel = 0;
//                    System.out.println("xVel: " + ball1.xVel);
//                    System.out.println("yVel: " + ball1.yVel);
//
//                }
//            }
//
//        }
//
//        if (ball1.yCoord > window_height || ball1.yCoord < 0) { //if y coord hits top or bottom // xlim <= x <= 0
//            System.out.println("hit t/b wall");
//            ball1.yCoord = ball1.yCoord < 0 ? 0 : ball1.yCoord; //if x < 0, x = 0, else x = x
//            ball1.yCoord = ball1.yCoord > window_height ? window_height : ball1.yCoord; //if x > top, x = top, else x = x
//            // yvel -= FRICTION;
//            ball1.yVel *= -1; //change direction
//
//            System.out.println("xCoord: " + ball1.yCoord + " xVel: " + ball1.yVel);
//
//            // Apply friction... should probably be a function onto itself.
//            if (ball1.yVel > 0) { //hit a wall
//                System.out.println("t/b wall if");
//                ball1.yVel -= friction;
//                System.out.println("yVel: " + ball1.yVel);
//                System.out.println("xVel: " + ball1.xVel);
//
//                if (ball1.yVel < 0) {
//                    System.out.println("t/b wall if if");
//                    ball1.yVel = 0;
//                    System.out.println("yVel: " + ball1.yVel);
//                    System.out.println("xVel: " + ball1.xVel);
//
//
//                }
//            } else {
//                System.out.println("t/b wall else");
//                ball1.yVel += friction;
//                System.out.println("yVel: " + ball1.yVel);
//                System.out.println("xVel: " + ball1.xVel);
//
//                if (ball1.yVel > 0) {
//                    System.out.println("t/b wall else if");
//                    ball1.yVel = 0;
//                    System.out.println("yVel: " + ball1.yVel);
//                    System.out.println("xVel: " + ball1.xVel);
//
//                }
//            }
//
//        } //brizan's code??
//        repaint();
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
        jf.setSize(window_width + 100, window_height + 100); // TODO: Replace with the size from configuration!
        jf.add(rb);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } //end main()
}
