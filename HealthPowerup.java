import greenfoot.*;
import java.util.List;

public class HealthPowerup extends Actor
{
    private static GreenfootImage healthImage = null;
    private static GreenfootSound soundEffect = null;
    
    public HealthPowerup(){
        if(healthImage == null){
            healthImage = new GreenfootImage("life1.png");
            healthImage.scale(30, 30);
        }
        setImage(healthImage);
        
        if(soundEffect == null){
            soundEffect = new GreenfootSound("healSoundEffect.mp3");
            soundEffect.setVolume(40);
        }
        soundEffect.play();
    }
    
    public void act(){
        if(isTouching(Player.class)){
            handlePlayerCollision();
        }
    }
    
    public void handlePlayerCollision(){
        ScoreTracker.playerHealth = 3;
        ScoreTracker.targetHealth = 3;
        soundEffect.play();
        getWorld().removeObject(this);
    }
}
