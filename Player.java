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
    
    public Player() {
        super();
        
        this.footstepSounds = new GreenfootSound[2];
        this.footstepSounds[0] = new GreenfootSound("leftFootstep.mp3");
        this.footstepSounds[0].setVolume(0);
        this.footstepSounds[1] = new GreenfootSound("rightFootstep.mp3");
        this.footstepSounds[1].setVolume(0);
        this.rootImgFP = "Walk";
        this.numFrames = 4;
        this.numDirections = 4;
        this.frameSkip = 30;
        this.spriteScale = 50;
        
        super.loadAllImages();
        
    }
    
    public void act()
    {
        super.act(); 
        
        // Delay is put in place so players cannot spam bullets
        if (delay >= 33 && Greenfoot.isKeyDown("space")) {
            getWorld().addObject(new Bullet(direction), getX(), getY());
            delay = 0;
        }
        
        delay++;
        
        Movement(); 
        }
        
    private void Movement() {
        boolean moving = false;
        
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
            this.setDirection(1);
            moving =true;
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
            this.setDirection(0);
            moving =true;
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
            
            idleToWalk();
            direction = "N";
            moving =true;
        }
        if (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s")){
            if (getY() <= 1195){
                setLocation(getX(), getY()+5);
            }
            // Actor b = getOneIntersectingObject(debree.class);
            // Actor c = getOneIntersectingObject(house.class);
            //if (b != null | c != null){
            //    setLocation(getX(), getY() - 5);
            //}
            
            idleToWalk();
            direction = "S";
            moving =true;
        }
        
        standingStillCheck(moving);
        
        diagonalShots();
    }
    
    // This method will check the bullet directions to make sure that the
    //  bullets can be shot at diagonal angles
    private void diagonalShots() {
        if ((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) && 
        (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w"))) {
            direction = "W";
        }
        else if ((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) && 
        (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s"))) {
            direction = "W";
        }
        else if ((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) && 
        (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("w"))) {
            direction = "E";
        }
        else if ((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) && 
        (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("s"))) {
            direction = "E";
        }
    }
    
    private void standingStillCheck(boolean moving) {
        int currentDirection = this.getDirection();
        
        // If we're not moving, switch from walking to idle animation
        // 2 - Idle right 3 - Idle left
        if (!moving) {
            if (currentDirection == 0) {
                this.setDirection(2);
            }
            else if (currentDirection == 1) {
                this.setDirection(3);
            }
        }
    }
    
    private void idleToWalk() {
        if (this.getDirection() == 2) this.setDirection(0);
        else if (this.getDirection() == 3) this.setDirection(1);
    }
}
