import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class startScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class startScreen extends World
{

    /**
     * Constructor for objects of class startScreen.
     * 
     */
    public startScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 900, 1); 
        //prepare();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    /*private void prepare()
    {
        titleletters titleletters = new titleletters();
        addObject(titleletters,612,436);
    }*/
    
    public void act(){
        if(Greenfoot.isKeyDown("enter")){
            Greenfoot.setWorld(new MyWorld());
        }
    }
}

