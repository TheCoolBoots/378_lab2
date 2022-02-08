import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class label here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class label extends Actor
{
    /**
     * Act - do whatever the label wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public label(){
        String text = String.valueOf(ScoreTracker.getScore());
        GreenfootImage img = new GreenfootImage(100,30);
        img.drawString(text,1,20);
        setImage(img);
    }
    public void act()
    {
        // Add your action code here.
    }
}
