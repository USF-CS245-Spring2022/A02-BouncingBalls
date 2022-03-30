// @author: Mia Kobayashi
// @date and version: 28 Mar 2022, v.2
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

    // TODO: Consider removing the next two lines (coordinates for two balls)
    protected int x1, y1;
    protected int x2, y2;
    boolean move = true;

    Ball ball1;


    public class Ball {
        private Color color;
//        private Point location;
        private int xCoord, yCoord;
        private int xVel, yVel;
        private int maxHeight, minHeight = 0;
//        private final Dimension size;
        private boolean move_down = true;

        public Ball() {
//            size = new Dimension(ball_radius, ball_radius);
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }

//        protected void paint(Graphics2D g2d) {
//
//            Point p = getLocation();
//            if (p != null) {
//                g2d.setColor(getColor());
//                Dimension size = getSize();
//                g2d.fillOval(p.x, p.y, size.width, size.height);
//            }
//        }
    } //end Ball class


    public RollBounce (String propertyFileName) {
        // TODO: insert your code to read from configuration file here.
        gravity = 10;
        friction = 2;
//        list = {}; //arraylist
        minspeed = 5;
        maxspeed = 35;
        timerDelay = 75;
        balls = 7;
        window_height = 480;
        window_width = 640;
        ball_radius = 20;
        ball1 = new Ball();

        tm = new Timer(timerDelay, this); // TODO: Replace the first argument with delay with value from config file.

        // TODO: Consider removing the next two lines (coordinates) for random starting locations.
//        x1 = 100; y1 = 50;
//        x2 = 200; y2 = 400;

//
//        allBalls = new ArrayList<Ball>();

//        for (int i = 0; i < 1; i++) {
//            allBalls.add(new Ball());
//        }

        //get rand starting position
//        for (Ball ball : allBalls) {
//            int x = random(0, window_width); //x starting pos
//            int y = random(0, window_height); //y starting pos

//            Dimension size = ball.getSize();
//
//            if (x + size.width > window_width) {
//                x = window_width - size.width;
//            }
//            if (y + size.height > window_height) {
//                y = window_height - size.height;
//            }
            ball1.xCoord = 20;
            ball1.yCoord = 20;
            ball1.xVel = 10;
            ball1.yVel = 10;

//            ball.setLocation(new Point(x, y));
//        }
    } //end RollBounce()


//    public List<Ball> getBalls() {
//        return allBalls;
//    }


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
        tm.start();
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


        if (ball1.xCoord + (ball_radius*2) > window_width || ball1.xCoord + (ball_radius*2) < 0) { //if x coord hits R / L side
            System.out.println("in first if");
            ball1.xCoord = ball1.xCoord < 0 ? 0 : ball1.xCoord; //if x < 0, x = 0, else x = x
            ball1.xCoord = ball1.xCoord > window_width ? window_width : ball1.xCoord; //if x > top, x = top, else x = x
            // yvel -= FRICTION;
            ball1.xVel *= -1; //change direction

            System.out.println("xCoord: " + ball1.xCoord + " xVel: " + ball1.xVel);
             // Apply friction... should probably be a function onto itself.
            if (ball1.xVel > 0) { //if vel > 0
                System.out.println("x if");
                ball1.xVel -= friction;
                System.out.println("xVel: " + ball1.xVel);
                System.out.println("yVel: " + ball1.yVel);

                if (ball1.xVel < 0) {
                    System.out.println("x if if");
                    ball1.xVel = 0;
                    System.out.println("xVel: " + ball1.xVel);
                    System.out.println("yVel: " + ball1.yVel);

                }
            } else {
                System.out.println("x else");
                ball1.xVel += friction;
                System.out.println("xVel: " + ball1.xVel);
                System.out.println("yVel: " + ball1.yVel);

                if (ball1.xVel > 0) {
                    System.out.println("x else if");
                    ball1.xVel = 0;
                    System.out.println("xVel: " + ball1.xVel);
                    System.out.println("yVel: " + ball1.yVel);

                }
            }
        }

        if (ball1.yCoord + (ball_radius*2) > window_height || ball1.yCoord + (ball_radius*2) < 0) { //if y coord hits top or bottom // xlim <= x <= 0
            System.out.println("second if");
            ball1.yCoord = ball1.yCoord < 0 ? 0 : ball1.yCoord; //if x < 0, x = 0, else x = x
            ball1.yCoord = ball1.yCoord > window_height ? window_height : ball1.yCoord; //if x > top, x = top, else x = x
            // yvel -= FRICTION;
            ball1.yVel *= -1; //change direction

            System.out.println("xCoord: " + ball1.yCoord + " xVel: " + ball1.yVel);

            // Apply friction... should probably be a function onto itself.
            if (ball1.yVel > 0) {
                System.out.println("y if");
                ball1.yVel -= friction;
                System.out.println("yVel: " + ball1.yVel);
                System.out.println("xVel: " + ball1.xVel);

                if (ball1.yVel < 0) {
                    System.out.println("y if if");
                    ball1.yVel = 0;
                    System.out.println("yVel: " + ball1.yVel);
                    System.out.println("xVel: " + ball1.xVel);


                }
            } else {
                System.out.println("y else");
                ball1.yVel += friction;
                System.out.println("yVel: " + ball1.yVel);
                System.out.println("xVel: " + ball1.xVel);

                if (ball1.yVel > 0) {
                    System.out.println("y else if");
                    ball1.yVel = 0;
                    System.out.println("yVel: " + ball1.yVel);
                    System.out.println("xVel: " + ball1.xVel);

                }
            }
        } //brizan's code??

            ball1.xCoord += ball1.xVel;
            ball1.yCoord += ball1.yVel;

        repaint();



//        if (ball1.move_down) { //moving down
//            if (ball1.xCoord > 600) { //hit right side
//                System.out.println("down & right");
//                ball1.xCoord -= gravity / friction;
//                ball1.yCoord -= gravity;
//            } else if (ball1.xCoord < 10) { //hit left side
//                System.out.println("down & left");
//                ball1.xCoord += gravity / friction;
//                ball1.yCoord -= gravity;
//            } else if (ball1.yCoord > 400) { //hitting bottom
//                System.out.println("down & bottom, change to up");
//                ball1.xCoord += gravity;
//                ball1.yCoord -= gravity / friction;
//                ball1.move_down = false;
//            } else {
//                System.out.println("else down");
//                ball1.xCoord += gravity;
//                ball1.yCoord += gravity;
//            }
//        }
//        else { //moving up
//            if (ball1.xCoord > 600) { //hitting right side
//                System.out.println("up & right");
//                ball1.xCoord -= gravity / friction;
//                ball1.yCoord -= gravity;
//            } else if (ball1.xCoord < 10) { //hit left side
//                System.out.println("up & left");
//                ball1.xCoord += gravity / friction;
//                ball1.yCoord -= gravity;
////            } else if (ball1.y < 10) { //hit top
////                System.out.println("up & top");
////                ball1.x += gravity;
////                ball1.y -= gravity / friction;
////                ball1.move_down = true;
//            } else {
//                System.out.println("else up");
//                ball1.xCoord -= gravity;
//                ball1.yCoord -= gravity;
//            }
//        } //worked on w clay
//        repaint();

//        else if (ball1.x > 600 && ball1.y > 400) { //bottom right corner
//            ball1.x -= gravity;
//            ball1.y -= gravity;
//        }
//
//        else if (ball1.x < 0 && ball1.y > 400) { //bottom left corner
//            ball1.x += gravity;
//            ball1.y -= gravity;
//        }
//
//        else if (ball1.x > 600 && ball1.y < 0) { //top right corner
//            ball1.x -= gravity;
//            ball1.y += gravity;
//        }
//        else if (ball1.x < 0 && ball1.y < 0) { //top left corner
//            ball1.x += gravity;
//            ball1.y += gravity;
//        }



//        repaint();

//        x1 += 10;
//        x2 -= 15;

        // These two "if" statements keep the balls on the screen in case they go off one side.
//        if (x1 > window_height)
//            x1 = 0;
//        if (x2 < 0)
//            x2 = window_height;

//        int size = 5;
//        while (size != 0) {
//            ball1.getLocation().x = ball1.getLocation().x + ball1.getLocation().x * gravity;
//            ball1.getLocation().y = ball1.getLocation().y + ball1.getLocation().y * gravity;
//
//            size--;
//            repaint();
//        }
//        x1 += minspeed;
//        while (move) {
//            if (y1 < window_height) {
//                minspeed *= gravity; //curr location * gravity
//            }
//            else { //hit floor
//                minspeed *= -0.85;
//                move = false;
//            }
//
//            if (minspeed > 0) {
//                minspeed *= gravity; //* where you are in coord
//            }
//            else { //at max height
//                move = true;
//            }
         //while not hitting any 4 conditions, move in same direction as it currently is

//        if (move) { //moving down
//            if (y1 < window_height) {
//                minspeed += gravity; //curr location * gravity
//            }
//            else { //hit floor
//                minspeed *= -0.85;
//                move = false;
//            }
//        }
//        else { //moving up
//            if (minspeed > 0) {
//                minspeed -= gravity; //* where you are in coord
//            }
//            else { //at max height
//                move = true;
//            }
//        }
//        y1 -= minspeed;



//            //calculate new ball pos
//            xCoord += ;
//            yCood += ;
//
//            //if ball moves out of bounds horz, adjust pos & speed of ball
//            if (xCoord - ball_radius < 0) {
//                xSpeed -= xSpeed;
//                xCoord = ball_radius;
//            }
//            else if (xCoord + ball_radius > window_width) {
//                xSpeed -= xSpeed;
//                xCoord = window_width - ball_radius;
//            }
//
//            //if ball moves out of bounds vert, adjust pos * speed of ball
//            if (yCoord - ball_radius < 0) {
//                ySpeed -= ySpeed;
//                yCoord = ball_radius;
//            }
//            else if (yCoord + ball_radius > window_height) {
//                ySpeed -= ySpeed;
//                yCoord = window_height - ball_radius;
//            }



//        // Keep this at the end of the function (no matter what you do above):
//        repaint();
    } //end actionPerformed()


    private static Color randColor () {
        int r = random(0, 256);
        int g = random(0, 256);
        int b = random(0, 256);

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
        jf.setSize(window_width, window_height); // TODO: Replace with the size from configuration!
        jf.add(rb);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } //end main()
}
