import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    ScoreTracker scoreTracker;
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1);
        
        scoreTracker = new ScoreTracker(this);
        Player player = new Player();
        Target target = new Target();
        Zombie zombie = new Zombie(player, target, scoreTracker);
        
        
        
        addObject(player, 0, 0);
        addObject(target, 1000, 200);
        addObject(zombie, 500, 300);
    }
    
    public void act(){
        scoreTracker.act();
    }
}
