import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Zombie extends Animated
{
    private static Player playerRef;
    private static Target targetRef;
    private ScoreTracker scoreTrackerRef;
    
    private final int moveThreshold = 10;
    
    private int framesPerDamage = 60;
    private int collisionFrameCounter = framesPerDamage-10;
    
    public Zombie(Player player, Target target, ScoreTracker scoreTracker){
        super();
        //this.spawnSound = new GreenfootSound("ZombieSpawn.wav");
        //this.deathSound = new GreenfootSound("ZombieDeath.wav");
        this.scoreTrackerRef = scoreTracker;
        this.rootImgFP = "test";
        this.numFrames = 1;
        this.moveSpeed = 2;
        this.playerRef = player;
        this.targetRef = target;
        this.frameSkip = 5;
        
        super.loadAllImages();
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
        
        // System.out.println(yDist);
        // need to have moveThreshold or else zombie will only move left/right if on exact same y-coord as target
        if(Math.abs(xDist) > Math.abs(yDist) && Math.abs(yDist) > moveThreshold){
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
                return 3;
            }
            else{
                return 1;
            }
        }
    }
    
    private void handleTargetCollision(){
        if(collisionFrameCounter % framesPerDamage == 0){
            scoreTrackerRef.targetHealth -= 1;
            collisionFrameCounter = 1;
            System.out.println("Damage");
        }
        else{
            collisionFrameCounter += 1;
        }
    }
    
    private void handlePlayerCollision(){
         if(collisionFrameCounter % framesPerDamage == 0){
            scoreTrackerRef.playerHealth -= 1;
            collisionFrameCounter = 1;
        }
        else{
            collisionFrameCounter += 1;
        }
    }
    
    public void act()
    {
        this.setDirection(this.getTargetDirection());
        //System.out.println("direction = " + String.valueOf(this.getDirection()));
        super.act();
                
        if(isTouching(Target.class)){
            handleTargetCollision();
        }
        else if(isTouching(Player.class)){
            handlePlayerCollision();
        }
        else{
            collisionFrameCounter = framesPerDamage-50;
        }
    }
}
