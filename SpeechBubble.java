import greenfoot.*;

public class SpeechBubble extends Actor
{
    private int durationFrames;
    private Actor parent;
    private GreenfootImage img;
    
    private int frameCounter = 0;
    
    public SpeechBubble(String srcFilepath, int durationFrames, Actor parent)
    {
        this.img = new GreenfootImage(srcFilepath);
        this.durationFrames = durationFrames;
        this.parent = parent;
        this.setImage(this.img);
    }
    
    public void act(){
        setLocation(parent.getX(), parent.getY());
        if(frameCounter > durationFrames){
            getWorld().removeObject(this);
        }
        frameCounter++;
    }
}
