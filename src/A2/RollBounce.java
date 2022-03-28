// @author: Mia Kobayashi
// @date and version: 28 Mar 2022, v.1
// CS245 Assignment 2: Roll Bounce

package A2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.Properties;
import java.io.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class RollBounce extends JPanel implements ActionListener {

    protected Timer tm;
    InputStream input;

    // TODO: Consider removing the next two lines (coordinates for two balls)
    protected int x1, y1; //ball 1 x & y coords
    protected int x2, y2; //ball 2 x & y coords

    static int gravity, friction, minspeed, maxspeed, balls, window_height, window_width, ball_radius;


    public class Balls extends JPanel {
        private List<Ball> allBalls;

        public Balls(int numBalls, int minspeed, int maxspeed, int radius) {
            allBalls = new ArrayList<Ball>(25); //25 is current num of max balls??

            for (int i = 0; i < numBalls; i++) {
                Color color = new Color(random(0, 256), random(0, 256), random(0, 256));
                allBalls.add(new Ball(color, radius));
            }
        } //end Balls()

        public List<Ball> getBalls() {
            return allBalls;
        }
    } //end Balls class


    public class Ball {
        Color color;
        Point speed;
        Dimension size;
        Point location;

        public Ball (Color color, int radius) {
            this.color = color;
            this.size = new Dimension(radius, radius);
        }

        public void setLocation(Point loc) {
            this.location = loc;
        }

        public Point getLocation() {
            return location;
        }

        public Dimension getSize() {
            return size;
        }

        public void setSpeed(Point speed) {
            this.speed = speed;
        }

//        public Color getColor() {
//            return color;
//        }
//
        public Point getSpeed() {
            return speed;
        }
    } //end Ball class

    public class RollBounceEngine implements Runnable {
        private Balls parent;

        public RollBounceEngine (Balls parent) {
            this.parent = parent;
        }

        public Balls getParent() {
            return parent;
        }

//        public void move (Ball ball) {
//            Point loc = ball.getLocation();
//            Point speed = ball.getSpeed();
//            Dimension ballSize = ball.getSize();
//
//            int vX = speed.x;
//            int vY = speed.y;
//
//            //coords
//            int x = loc.x;
//            int y = loc.y;
//
//            if (x + vX < 0 || x + ballSize.width + vX > getParent().getWidth()) {
//                vX *= -1;
//            }
//            if (y + vY < 0 || y + ballSize.height + vY > getParent().getHeight()) {
//                vY *= -1;
//            }
//
//            x += vX;
//            y += vY;
//
//            ball.setSpeed(new Point(vX, vY));
//            ball.setLocation(new Point(x, y));
//        }

        @Override
        public void run() {
            int width = getParent().getWidth();
            int height = getParent().getHeight();

            //rand starting position
            for (Ball ball : getParent().getBalls()) {
                int x = random(0, window_width);
                int y = random(0, window_height);

                Dimension ballSize = ball.getSize();

                if (x + ballSize.width > width) {
                    x = width - ballSize.width;
                }
                if (y + ballSize.height > height) {
                    y = height - ballSize.height;
                }

                ball.setLocation(new Point(x, y));
            }
        }
    } //end RollBounceEngine class


    public List RollBounce (String propertyFileName) throws IOException {
        // TODO: insert your code to read from configuration file here.
        Properties prop = new Properties();

        try {
//            input = new FileInputStream(res.getFile());
//            prop.load(input);
//            System.out.println(prop.getProperty("gravity"));

            input = getClass().getClassLoader().getResourceAsStream(propertyFileName);

            System.out.println("input: " + input);
            if (input != null) { //fixme, should be ==
                System.out.println("throwing");
                throw new IOException();
            } else {
                System.out.println("here");
                prop.load(input);
//                 int gravity = prop.getProperty("gravity");
//                 int friction = prop.getProperty("friction");
//                 int[] arrList = prop.getProperty("arraylist");
//                 int minspeed = prop.getProperty("minspeed");
//                 int maxspeed = prop.getProperty("maxspeed");
//                 int balls = prop.getProperty("balls");
//                 int window_height = prop.getProperty("window_height");
//                 int window_width = prop.getProperty("window_width");
//                 int ball_radius = prop.getProperty("ball_radius");
                // prop.list(System.out);
                //                throw new IOException();
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            System.out.println("All keys of prop file: ");
            gravity = 10;
            friction = 2;
//            list = {}; //arraylist
            minspeed = 5;
            maxspeed = 35;
            balls = 7;
            window_height = 480;
            window_width = 640;
            ball_radius = 20;

//            for (Object key : prop.keySet()) {
//                System.out.println(key + " : " + prop.getProperty(key.toString()));
//                // field.set(null, getValue(prop, key.toString(), ket.getT)
//            }


            tm = new Timer("gravity", true); // TODO: Replace the first argument with delay with value from config file.

            // TODO: Consider removing the next two lines (coordinates) for random starting locations.
//            x1 = 100;
//            y1 = 50;
//            x2 = 200;
//            y2 = 400;

            Balls allBalls = new Balls(balls, minspeed, maxspeed, ball_radius);
            return allBalls.getBalls();
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g); //probably best you leave this as is, paints background

        // TODO: Paint each ball. Here's how to paint two balls, one after the other:

        g.setColor(Color.BLUE);
        g.fillOval(x1, y1, 20, 20);

        //draw ball
        g.setColor(Color.RED);
        g.fillOval(x2, y2, 20, 20);


        // Recommend you leave the next line as is
//        tm.start();
    }

    /**
     * Random number generator with a max range
     * @param max the upper bound range
     * @return maxNum, int rand number
     */
    public static int random(int minBound, int maxBound) {
        Random rand = new Random();
        int randNum = rand.nextInt((maxBound - minBound) + 1) + minBound;

        return randNum;
    }


    @Override
    public void actionPerformed (ActionEvent actEvent) {
        // TODO: Change the location of each ball. Here's an example of them moving across the screen:
        //       ... but to be clear, you should change this.

        x1 += 10;
        x2 -= 15;

        // These two "if" statements keep the balls on the screen in case they go off one side.
        if (x1 > 640) {
            x1 = 0;
        }
        if (x2 < 0) {
            x2 = 640;
        }

//        Point loc = ball.getLocation();
//        Point speed = ball.getSpeed();
//        Dimension ballSize = ball.getSize();
//
//        int vX = speed.x;
//        int vY = speed.y;
//
//        //coords
//        int x = loc.x;
//        int y = loc.y;
//
//        if (x + vX < 0 || x + ballSize.width + vX > getParent().getWidth()) {
//            vX *= -1;
//        }
//        if (y + vY < 0 || y + ballSize.height + vY > getParent().getHeight()) {
//            vY *= -1;
//        }
//
//        x += vX;
//        y += vY;
//
//        ball.setSpeed(new Point(vX, vY));
//        ball.setLocation(new Point(x, y));

            // Keep this at the end of the function (no matter what you do above):
        repaint();
    }

    public static void main(String[] args) throws IOException {
        RollBounce rb = new RollBounce(); //("RollBounce.prop");

        System.out.println("to print canvas?");
        JFrame jf = new JFrame();
        jf.setTitle("Roll Bounce");
        jf.setSize(window_width, window_height); // TODO: Replace with the size from configuration!
        jf.add(rb);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}