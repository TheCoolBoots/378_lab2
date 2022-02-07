import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class endScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class endScreen extends World
{

    /**
     * Constructor for objects of class endScreen.
     * 
     */
    public endScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 900, 1); 
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */

    private void prepare()
    {
        endLetters endLetters = new endLetters();
        addObject(endLetters,617,385);
        endLetters.setLocation(619,450);
        label label = new label();
        addObject(label,724,373);
    }
    
    public void act(){
        if(Greenfoot.isKeyDown("r")){
            Greenfoot.setWorld(new MyWorld());
        }
    }
}

