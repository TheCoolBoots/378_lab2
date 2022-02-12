import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    public Player player;
    public Target target;
    private GreenfootSound backgroundMusic;

    private float spawnChance = .01f;

    private Random rand = new Random();

    ScoreTracker scoreTracker;
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 900, 1); 
        scoreTracker = new ScoreTracker(this);

        backgroundMusic = new GreenfootSound("BackgroundMusic.mp3");
        backgroundMusic.setVolume(40);
        backgroundMusic.playLoop();

        player = new Player();
        target = new Target();
        addObject(player, getWidth()/2 + 100, getHeight()/2 + 200);
        addObject(target, getWidth()/2, getHeight()/2);

        //Zombie zombie = new Zombie(player, target, scoreTracker);
        //addObject(zombie, 500, 300);

        /*InvincibilityPowerup invincible = new InvincibilityPowerup();
        UpgradePowerup upgrade = new UpgradePowerup();
        HealthPowerup heal = new HealthPowerup();

        addObject(invincible, 50, 50);
        addObject(upgrade, 100, 50);
        addObject(heal, 150, 50);*/

        SpeechBubble introBubble = new SpeechBubble("StartBubble.png", 400, player, 1.0f, 100, -100);
        addObject(introBubble, 0, 0);
        SpeechBubble dogBark = new SpeechBubble("BarkBubble.png", 400, target, 1.0f, 60, -60);
        addObject(dogBark, 0, 0);
        prepare();
    }

    private int lastScore = 0;
    public void act(){
        scoreTracker.act();

        if(rand.nextFloat() < spawnChance){
            switch(rand.nextInt(4)){
                case 0:
                    // spawn somewhere on top of screen
                    addObject(new Zombie(player, target, scoreTracker), (int) (rand.nextFloat() * getWidth()), -30);
                    break;
                case 1:
                    // spawn somewhere on bottom of screen
                    addObject(new Zombie(player, target, scoreTracker), (int) (rand.nextFloat() * getWidth()), getHeight() + 30);
                    break;
                case 2:
                    // spawn somewhere on left of screen
                    addObject(new Zombie(player, target, scoreTracker), -30, (int) (rand.nextFloat() * getHeight()));
                    break;
                case 3:
                    // spawn somewhere on right of screen
                    addObject(new Zombie(player, target, scoreTracker), getWidth() + 30, (int) (rand.nextFloat() * getHeight()));
                    break;
            }
        }

        if(lastScore < scoreTracker.score && (scoreTracker.score) % 10 == 0){
            spawnChance += .005f;
            // System.out.println(spawnChance);
        }
        lastScore = scoreTracker.score;

        if(scoreTracker.playerHealth <= 0)
        {
            backgroundMusic.stop();
            Greenfoot.setWorld(new endScreen());
        }   
        else if(scoreTracker.targetHealth <= 0)
        {
            backgroundMusic.stop();
            Greenfoot.setWorld(new endScreen());
        }

    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        rocks rocks = new rocks();
        addObject(rocks,280,183);
        rocks rocks2 = new rocks();
        addObject(rocks2,924,177);
        rocks rocks3 = new rocks();
        addObject(rocks3,263,644);
        rocks rocks4 = new rocks();
        addObject(rocks4,922,635);
    }
}
