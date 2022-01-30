import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TargetLifeDisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LifeDisplayer extends Actor
{
    
    private int value = 3;
    private GreenfootImage[] images;
    private boolean firstTime = true;
    
    public LifeDisplayer(GreenfootImage[] images){
        this.images = images;
    }
    
    public void setValue(int value){
        this.value = value;
    }
    
    public void act(){
        if(value < 1){
            if(firstTime){
                firstTime = false;
                System.out.println("GAME OVER");
                setImage(images[0]);
            }
        } 
        else{
            setImage(images[value]);
        }
            
    }
}
