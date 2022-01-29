import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TargetLifeDisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LifeDisplayer extends Actor
{
    private int value = 0;
    private GreenfootImage[] images;
    
    public LifeDisplayer(GreenfootImage[] images){
        this.images = images;
    }
    
    public void setValue(int value){
        this.value = value;
    }
    
    public void act(){
        setImage(images[value-1]);
    }
}
