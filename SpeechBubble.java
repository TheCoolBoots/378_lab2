import greenfoot.*;

public class SpeechBubble extends Actor
{
    private int durationFrames;
    private Actor parent;
    private GreenfootImage img;
    private int xShift;
    private int yShift;
    private int frameCounter = 0;
    
    public SpeechBubble(String srcFilepath, int durationFrames, Actor parent, float scale, int xShift, int yShift)
    {
        this.img = new GreenfootImage(srcFilepath);
        img.scale((int)(img.getWidth()*scale), (int)(img.getHeight()*scale)); 
        this.xShift = xShift;
        this.yShift = yShift;
        this.durationFrames = durationFrames;
        this.parent = parent;
        this.setImage(this.img);
    }
    
    public void act(){
        setLocation(parent.getX()+xShift, parent.getY()+yShift);
        if(frameCounter > durationFrames){
            getWorld().removeObject(this);
        }
        frameCounter++;
    }
}
