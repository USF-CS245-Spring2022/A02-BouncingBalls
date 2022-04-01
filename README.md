# Assignment 2 | RollBounce  
Mia Kobayashi  
31 March 2022  

**How the code works:**  
Program reads in a properties file to get specifications about a bouncing ball world (i.e. gravity, friction, minimum speed, maximum speed, timer delay, number of balls, canvas window height, canvas window width, and ball radius).  Individual balls are then made and given a random starting point on the canvas along with randomly generated x velocity (y velocity is always initally 0) and its own random generated color (although, if you're color blind like me, good luck because some of the colors that are generated do not sit well with my eyes).  
A paintComponent() method allows users to see the changes in ball movement.  
actionPerformed() actually moves the ball where friction is applied whenever a ball hits the ground or right or left side of the canvas.  

Each ball falls, accelerating at the rate (in pixels) indicated by the gravity property.  
Once a ball reaches the bottom of the canvas, it must “bounce” off the bottom and head in the opposite vertical direction, i.e. “up” with the same velocity.  
If a ball reaches the left or right side of the canvas, it must “bounce” off the wall and head in the opposite direction, i.e. toward the opposite wall.  
Every time a ball “bounces” it slows its velocity by an amount equal to the friction property.  

**Difficulties:**  
So, so much trouble.  The amount of thought dedicated to physics that went into this assignment?  (!)Happy.  There was first trouble with a ball not moving, then moving too fast, then moving quickly in an bound area that shouldn't have constricted movement, then balls never stop bouncing, and then balls having seizures.  I still think the balls have a funny looking bounce, but I guess that's just how gravity and velocity work in the world of Java that I've curated.  Also the properties file is weird.  

## RollBounce  
The goal of this assignment is to demonstrate your understanding of lists and polymorphism, along with coding to an API in order to demonstrate a simulation of bouncing balls.  

## Background
This is a working starting point for a bouncing ball simulation in Java Swing / AWT. (When you compile and run this, you'll see two animated balls moving horizontally at different speeds. You may use this as the starting point to meet the assignment objective.)  

There are many online tutorials for implementing 2-dimensional animations in Java. Here are two:  
* [Create basic shapes](https://www.youtube.com/watch?v=4YhrmAGpVtI)  
* [Create simple animations](https://www.youtube.com/watch?v=I3usNR8JrEE)  

These two tutorials are likely a sufficient primer for performing the tasks required by this assignment.  
You will use Java animations to simulate a number of bouncing balls on a two-dimensional canvas.  
