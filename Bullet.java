import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Actor
{
    private GreenfootSound zombieDeathSound;
    private GreenfootSound shootSound;
    
    private int XMove = 0, YMove = 0;
    private float speed = 15;
    private float lifeTime = 0;
    
    private static Random rand = null;
    
    public Bullet(String direction) {
        // super();
        
        if(rand == null){
            rand = new Random();
        }
        
        zombieDeathSound = new GreenfootSound("ZombieDeath.mp3");
        zombieDeathSound.setVolume(30);
        shootSound = new GreenfootSound("TechySheriff2.mp3");
        shootSound.setVolume(40);
        
        shootSound.play();
        
        if (direction == "N") {
            XMove = 0;
            YMove = -1;
        }
        else if (direction == "S") {
            XMove = 0;
            YMove = 1;
        }
        else if (direction == "W") {
            XMove = -1;
            YMove = 0;
        }
        else if (direction == "E") {
            XMove = 1;
            YMove = 0;
        }
        else if (direction == "NW") {
            XMove = -1;
            YMove = -1;
        }
        else if (direction == "SW") {
            XMove = -1;
            YMove = 1;
        }
        else if (direction == "NE") {
            XMove = 1;
            YMove = -1;
        }
        else if (direction == "SE") {
            XMove = 1;
            YMove = 1;
        }
    }
    
    public void act()
    {
        setLocation(getX() + (int)(XMove * speed), getY() + (int)(YMove * speed));
        
        // We have a set lifetime so bullets do not travel forever
        if (checkBoundaries() || lifeTime >= 100) {
            getWorld().removeObject(this);
            return;
        }
        lifeTime++;
        
        checkCollision();
    }
    
    private Boolean checkBoundaries() {
        if (getY() > getWorld().getHeight() - 10 || getY() <= 0) {
            return true;
        }
        else if (getX() > getWorld().getWidth() - 20 || getX() <= 0) {
            return true;
        }
        
        return false;
    }
    
    private void checkCollision() {
        Actor Zombie;
        Zombie = getOneObjectAtOffset(0, 0, Zombie.class);
        
        // If we are hitting a zombie, remove it from the world
        if (Zombie != null) {
            World detect;
            ScoreTracker.score += 1;
            detect = getWorld();
            zombieDeathSound.play();
            
            spawnPowerup();
            
            detect.removeObject(Zombie);
            detect.removeObject(this);
            
        }
    }
    
    private void spawnPowerup(){
        if(rand.nextDouble() < .15){
            switch(rand.nextInt(3)){
                case 0:
                    getWorld().addObject(new HealthPowerup(), getX(), getY());
                    break;
                case 1:
                    if(Player.gunLevel < 3)
                        getWorld().addObject(new UpgradePowerup(), getX(), getY());
                    break;
                case 2:
                    getWorld().addObject(new InvincibilityPowerup(), getX(), getY());
                    break;
            }

        }
    }
}
