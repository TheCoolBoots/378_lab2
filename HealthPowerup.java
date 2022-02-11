import greenfoot.*;
import java.util.List;

public class HealthPowerup extends Actor
{
    private static GreenfootImage healthImage = null;
    
    public HealthPowerup(){
        if(healthImage == null){
            healthImage = new GreenfootImage("life1.png");
            healthImage.scale(30, 30);
        }
        setImage(healthImage);
    }
    
    public void act(){
        if(isTouching(Player.class)){
            handlePlayerCollision();
        }
    }
    
    public void handlePlayerCollision(){
        ScoreTracker.playerHealth = 3;
        ScoreTracker.targetHealth = 3;
        getWorld().removeObject(this);
    }
}
