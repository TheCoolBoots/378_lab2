import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Zombie extends Animated
{
    private static Player playerRef;
    private static Target targetRef;
    
    public Zombie(int xpos, int ypos, Player player, Target target){
        super();
        this.spawnSound = new GreenfootSound("ZombieSpawn.wav");
        this.deathSound = new GreenfootSound("ZombieDeath.wav");
        this.rootImgFP = "Zombie";
        this.numFrames = 5;
        this.moveSpeed = 1;
        this.playerRef = player;
        this.targetRef = target;
    }
    
    private double getDistToActor(Actor actor){
        return Math.sqrt(Math.pow(actor.getX()-this.getX(), 2.0) + Math.pow(actor.getY()-this.getY(), 2));
    }
    
    private int getTargetDirection(){
        double playerDist = getDistToActor(playerRef);
        double targetDist = getDistToActor(targetRef);
        
        int xDist;
        int yDist;
        
        if(targetDist < playerDist){
            // move towards target
            xDist = this.getX() - targetRef.getX();
            yDist = this.getY() - targetRef.getY();
        }
        else{
            // move towards player
            xDist = this.getX() - playerRef.getX();
            yDist = this.getY() - playerRef.getY();
        }
        
        if(Math.abs(xDist) > Math.abs(yDist)){
            // zombie will be moving up or down
            if(yDist > 0){
                return 0;
            }
            else{
                return 2;
            }
        }
        else{
            // zombie will be moving left or right
            if(xDist > 0){
                return 4;
            }
            else{
                return 1;
            }
        }
    }
    
    public void act()
    {
        this.setDirection(this.getTargetDirection());
    }
}
