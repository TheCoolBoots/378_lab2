import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class UpgradePowerup here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UpgradePowerup extends Actor
{
    private static GreenfootImage upgradeImage = null;
    private static GreenfootSound soundEffect = null;
    
    public UpgradePowerup(){
        if(upgradeImage == null){
            upgradeImage = new GreenfootImage("gunupgrade.png");
            upgradeImage.scale(30, 30);
        }
        setImage(upgradeImage);
        
        if(soundEffect == null){
            soundEffect = new GreenfootSound("gunUpgrade.mp3");
            soundEffect.setVolume(40);
        }
        soundEffect.play();
    }
    public void act()
    {
        if(isTouching(Player.class)){
            handlePlayerCollision();
        }
    }
    
    private void handlePlayerCollision(){
        Player.gunLevel += 1;
        soundEffect.play();
        getWorld().removeObject(this);
    }
}
