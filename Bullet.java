import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Player
{
    private int XMove = 0, YMove = 0;
    private float speed = 3;
    private float lifeTime = 0;
    
    public Bullet(String direction) {
        super();
        
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
    }
    
    public void act()
    {
        setLocation(getX() + (int)(XMove * speed), getY() + (int)(YMove * speed));
        
        // We have a set lifetime so bullets do not travel forever
        if (checkBoundaries() || lifeTime >= 100) {
            getWorld().removeObject(this);
        }
        lifeTime++;
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
}
