import greenfoot.*;
import java.util.List;

public class InvincibilityPowerup extends Actor
{
    private int durationFrames = 5000;
    private int despawnFrames = 4000;
    
    private boolean active = false;
    
    public InvincibilityPowerup()
    {
        
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
                active = true;
                Animated.invincible = true;
                // create SpeechBubble class with transparent shield over player and target
                SpeechBubble playerShield = new SpeechBubble("invincibility.png", durationFrames, getOneIntersectingObject(Player.class), 1.0f, 0, 0);
                List<Target> targetRef = getObjectsInRange(800, Target.class);
                SpeechBubble dogShield = new SpeechBubble("invincibility.png", durationFrames, targetRef.get(0), 1.0f, 0, 0);  // hard coded for the moment
                    // need to pass in reference to target somehow
            }
            else if (despawnFrames <= 0){
                getWorld().removeObject(this);
            }
            else{
                despawnFrames -= 1;
            }
        }

    }
    
    public void handlePlayerCollision(){
        
    }
}
