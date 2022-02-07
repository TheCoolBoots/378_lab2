import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private Player player;
    private Target target;
    private GreenfootSound backgroundMusic;
    
    ScoreTracker scoreTracker;
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 900, 1); 
        scoreTracker = new ScoreTracker(this);

        backgroundMusic = new GreenfootSound("BackgroundMusic.mp3");
        backgroundMusic.setVolume(20);
        backgroundMusic.playLoop();
        
        player = new Player();
        target = new Target();
        Zombie zombie = new Zombie(player, target, scoreTracker);

        addObject(player, 1000, 100);
        addObject(target, 1000, 200);
        addObject(zombie, 500, 300);
        prepare();
    }

    public void act(){
        scoreTracker.act();

        /*if(Greenfoot.getRandomNumber(100) == 1) {
            int[][] locations = new int[][] { {5, 5}, {1195, 5}, {1195, 895}, {5, 895} };
            int ran = Greenfoot.getRandomNumber(4);
            addObject(new Zombie(player, target, scoreTracker), locations[ran][0], locations[ran][1]);
        }*/
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {

    }
}
