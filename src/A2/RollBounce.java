package A2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.*;
import java.util.List;
// import A2.ListNode;


public class RollBounce {

    protected Timer tm;

    private List<Ball> allBalls;
    private static int minspeed, maxspeed;
    private static int gravity, friction, timerDelay, balls, window_height, window_width, ball_radius;

    // TODO: Consider removing the next two lines (coordinates for two balls)
    protected int x1, y1;
    protected int x2, y2;
    boolean move = true;

    Ball ball1;


    public class Ball {
        private Color color;
        private int xCoord, yCoord; //coords
        private int xVel, yVel; //velocity

        public Ball() {
            // xCoord = x;
            // yCoord = y;
            // xVel = vy;
            // xVel = vy;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }

    } //end Ball class



    public RollBounce() {
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

        // Ball ball = new Ball(20, 20, gravity, gravity);
        ball1.xCoord = 20;
        ball1.yCoord = 20;
        ball1.xVel = gravity;
        ball1.yVel = gravity;
    } //end RollBounce() constructor?


    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Probably best you leave this as is.

//        // TODO: Paint each ball. Here's how to paint two balls, one after the other:
//        g.setColor(Color.BLUE);
//        g.fillOval(x1, y1, 20, 20);
//
//        g.setColor(Color.RED);
//        g.fillOval(x2, y2, 20, 20);

//        for (Ball ball : allBalls) {
//            g.setColor(randColor());
        g.fillOval(ball1.xCoord, ball1.yCoord, ball_radius, ball_radius);
//        }

        // Recommend you leave the next line as is
        tm.start(); //then all the actionPerformed stuff happens
    } //end paintComponent()

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // TODO: Change the location of each ball. Here's an example of them moving across the screen:
        //       ... but to be clear, you should change this.

//        Ball ball1 = allBalls. get(0); //allBalls.get(0); //first ball obj
//        ball1.setLocation(new Point (10, 10));
//        int x1 = ball1.x;
//        int y1 = ball1.y;
        System.out.println("ball coords: " + ball1.xCoord + " " + ball1.yCoord);

        ball1.xCoord += ball1.xVel;
        ball1.xVel += gravity;
        ball1.yCoord += ball1.yVel;
        ball1.yVel += gravity;

        //touch top
        if (ball1.yCoord - ball_radius < 0) {
            ball1.yCoord = ball_radius;
            ball1.yVel = (int)(-ball1.yVel * 0.99);
        }

        //touch bottom
        if (ball1.yCoord > window_height - ball_radius) {
            if (ball1.yVel <= gravity * 2 + ball1.yVel * 0.8) {
                ball1.yVel = 0;
            }
            ball1.yCoord = window_height - ball_radius;
            ball1.yVel = -ball1.yVel * 75;
        }

        //touch right
        if (ball1.xCoord - ball_radius < 0) {
            ball1.xCoord = ball_radius;
            ball1.xVel = (int)(-ball1.xVel * 0.5);
        }

        //touch left
        if (ball1.xCoord + ball_radius > window_width) {
            ball1.xCoord = window_width - ball_radius;
            ball1.xVel = (int)(-ball1.xVel * 0.5);
        }
        repaint();
    }



    public static void main(String[] args) {
        RollBounce rb = new RollBounce();


        // RollBounce rb = new RollBounce("RollBounce.prop");

        System.out.println("to print canvas?");
        JFrame jf = new JFrame();
        jf.setTitle("Roll Bounce");
        jf.setSize(window_width, window_height); // TODO: Replace with the size from configuration!
        jf.add(rb);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
