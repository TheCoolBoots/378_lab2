import greenfoot.*;
import java.util.List;

public class HealthPowerup extends Actor
{
    private int despawnFrames = 5000;
        
    public void act(){
        if(despawnFrames <= 0){
            getWorld().removeObject(this);
        }
        else if(isTouching(Player.class)){
            handlePlayerCollision();
        }
        else{
            despawnFrames -= 1;
        }

    }
    
    public void handlePlayerCollision(){
        ScoreTracker.playerHealth = 3;
        ScoreTracker.targetHealth = 3;
    }
}
