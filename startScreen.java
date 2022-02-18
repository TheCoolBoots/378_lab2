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
    private GreenfootSound menuMusic;
    public startScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 900, 1); 
        //prepare();
        menuMusic = new GreenfootSound("MenuSoundtrack.mp3");
        menuMusic.setVolume(20);
        menuMusic.playLoop();
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        startletters startletters = new startletters();
        addObject(startletters,617,385);
    }
    
    public void act(){
        if(Greenfoot.isKeyDown("enter")){
            slide1 slide1 = new slide1();
            addObject(slide1,600,450);
            Greenfoot.delay(300);
            slide2 slide2 = new slide2();
            addObject(slide2,600,450);
            Greenfoot.delay(300);
            menuMusic.stop();
            Greenfoot.setWorld(new MyWorld());
        }
    }
}

