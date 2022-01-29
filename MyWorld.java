import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 900, 1); 
        Player player = new Player();
        Target target = new Target();
        addObject(player, 0, 0);
        addObject(target, 1000, 200);
        Zombie zombie = new Zombie(player, target);
        addObject(zombie, 500, 300);
    }
}
