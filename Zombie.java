import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Zombie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Zombie extends Animated
{
    private static Player playerRef;
    private static Target targetRef;
    
    private final String spawnSoundFP = "ZombieSpawn.wav";
    private final String deathSoundFP = "ZombieDeath.wav";
    
    private final String rootImgPath = "Zombie";
    private final String numFrames = 5;
    private final String zombieSpeed = 1;
    
    public Zombie(int xpos, int ypos, Player player, Target target){
        super();
        this.playerRef = player;
        this.targetRef = target;
    }
    
    public 
    
    public void act()
    {
        // Add your action code here.
    }
}
