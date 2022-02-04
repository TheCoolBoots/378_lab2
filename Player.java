import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Animated
{
    private float delay = 100;
    
    // Direction is set when the player moves and indicates which way is forward. Used to control which direction the bullets shoot
    private String direction = "N";
    
    public void act()
    {
        // Delay is put in place so players cannot spam bullets
        if (delay >= 33 && Greenfoot.isKeyDown("space")) {
            getWorld().addObject(new Bullet(direction), getX(), getY());
            delay = 0;
        }
        
        delay++;
        
        Movement(); 
        }
        
    private void Movement() {
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")){
            if(getX() >= 5){
                setLocation(getX()-5, getY());
            }
            // Actor b = getOneIntersectingObject(debree.class);
            // Actor c = getOneIntersectingObject(house.class);
            //if (b != null | c != null){
            //    setLocation(getX()+5, getY());
            //}
            
            direction = "W";
        }
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")){
            if(getX() <= 1195){
                setLocation(getX()+5, getY());
            }
            // Actor b = getOneIntersectingObject(debree.class);
            // Actor c = getOneIntersectingObject(house.class);
            //if (b != null | c != null){
            //    setLocation(getX()-5, getY());
            //}
            
            direction = "E";
        }
        if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w")){
            if (getY() >= 5){
                setLocation(getX(), getY()-5);
            }
            // Actor b = getOneIntersectingObject(debree.class);
            // Actor c = getOneIntersectingObject(house.class);
            //if (b != null | c != null){
            //    setLocation(getX(), getY() + 5);
            //}
            
            direction = "N";
        }
        if (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")){
            if (getY() <= 1195){
                setLocation(getX(), getY()+5);
            }
            setLocation(getX(), getY()+5);
            // Actor b = getOneIntersectingObject(debree.class);
            // Actor c = getOneIntersectingObject(house.class);
            //if (b != null | c != null){
            //    setLocation(getX(), getY() - 5);
            //}
            
            direction = "S";
        }
    }
}
