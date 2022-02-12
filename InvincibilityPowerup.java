import greenfoot.*;
import java.util.List;

public class InvincibilityPowerup extends Actor
{
    private int durationFrames = 500;
    
    private boolean active = false;
    private static GreenfootImage powerupImg = null;
    private static GreenfootSound soundEffect = null;
    
    public InvincibilityPowerup()
    {
         if(powerupImg == null){
            powerupImg = new GreenfootImage("forcefield.png");
            powerupImg.scale(40, 20);
        }
        setImage(powerupImg);
        
                
        if(soundEffect == null){
            soundEffect = new GreenfootSound("shieldSound.mp3");
            soundEffect.setVolume(40);
        }
        soundEffect.play();
    }

    public void act(){
        if(active){
            if(durationFrames <= 0){
                Animated.invincible = false;
                getWorld().removeObject(this);
            }
            else{
                durationFrames -= 1;
            }
        }
        else{
            if(isTouching(Player.class)){
                handlePlayerCollision();
            }
        }

    }
    
    public void handlePlayerCollision(){
        active = true;
        Animated.invincible = true;
        // create SpeechBubble class with transparent shield over player and target
        SpeechBubble playerShield = new SpeechBubble("invince.png", durationFrames, getOneIntersectingObject(Player.class), .2f, 0, 0);
        getWorld().addObject(playerShield, 0, 0);
        List<Target> targetRef = getObjectsInRange(800, Target.class);
        SpeechBubble dogShield = new SpeechBubble("invince.png", durationFrames, targetRef.get(0), .18f, 0, 0);  // hard coded for the moment
        getWorld().addObject(dogShield, 0, 0);
        // need to pass in reference to target somehow
        setImage("alpha.png");
        soundEffect.play();
    }
}
