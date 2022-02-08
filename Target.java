import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Target here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Target extends Animated
{
    
    public Target() {
        super();
        
        this.rootImgFP = "Dog";
        this.numFrames = 2;
        this.numDirections = 2;
        this.frameSkip = 30;
        this.spriteScale = 50;

        // Idle - 0 Hurt - 1
        super.loadAllImages();
    }
    
    public void act()
    {
        super.act();
        
        if (isTouching(Zombie.class)) this.setDirection(1);
        else this.setDirection(0);
    }
}
