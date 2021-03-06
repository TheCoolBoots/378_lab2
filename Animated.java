import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.GreenfootSound;
import java.util.HashMap;

/**
 * Write a description of class Animated here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Animated extends Actor
{
    
    
    protected GreenfootSound[] footstepSounds;
    protected String rootImgFP;
    protected int numFrames = 1;
    protected int moveSpeed = 1;
    protected int frameSkip = 0;
    protected int spriteScale = 1;
    protected int numDirections = 1;
    
    public static boolean invincible = false;
    
    private int moveDirection;
    private int frameSkipCounter = 0;
    private boolean moving = true;
    private int currentFrame = 0;
    private int currentFootstepSound = 0;
    private HashMap<String, GreenfootImage> images;
    
    public Animated(){
        // number of images will be equal to number of frames times number of directions (4)
        images = new HashMap<String, GreenfootImage>(numFrames * 4);
    }
    
    protected void loadAllImages(){
        for(int frame = 0; frame<numFrames; frame++){
            for(int dir = 0; dir<numDirections; dir++){
                String key = String.format("%x%x", dir, frame);
                String fileName = String.format("%s%o%o.png", rootImgFP, dir, frame);
                // System.out.println(key + " " + fileName);
                images.put(key, new GreenfootImage(fileName));
                images.get(key).scale(spriteScale, spriteScale);
            }
        }
    }
    
    private void move(){
        if(moving){
            if(moveDirection == 0){
                setLocation(getX(), getY() - moveSpeed);
            }
            else if(moveDirection == 1){
                setLocation(getX() + moveSpeed, getY());
            }
            else if(moveDirection == 2){
                setLocation(getX(), getY() + moveSpeed);
            }
            else if(moveDirection == 3){
                setLocation(getX() - moveSpeed, getY());
            }
            else{
                throw new IllegalArgumentException("moveDirection should be 0, 1, 2, or 3");
            }
        }
    }
    
    private void playWalkSound(){
        if(currentFootstepSound >= footstepSounds.length){
            currentFootstepSound = 0;
        }
        
        footstepSounds[currentFootstepSound].play();
        currentFootstepSound ++;
    }
    
    private GreenfootImage getNextAnimationFrame(){
        if(moving){
            if(frameSkipCounter % frameSkip == 0){
                currentFrame = (currentFrame + 1)%numFrames;
                if (this instanceof Zombie || this instanceof Player) playWalkSound();
                frameSkipCounter = 1;
            }
            else{
                frameSkipCounter += 1;
            }
        }

        return images.get(String.format("%x%x", moveDirection, currentFrame));
    }
    
    // 0 = up, 1 = right, 2 = down, 3 = left
    public void setDirection(int direction){
        this.moveDirection = direction;
    }
    
    // 0 = up, 1 = right, 2 = down, 3 = left
    public int getDirection(){
        return this.moveDirection;
    }
    
    public void setMoving(boolean moving){
        this.moving = moving;
    }
    
    public boolean isMoving(){
        return this.moving;
    }
    
    public void act()
    {
        if (this instanceof Zombie)
            move();
        setImage(getNextAnimationFrame());     
        //System.out.println("Super moving");
    }
}
