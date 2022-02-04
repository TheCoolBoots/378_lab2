import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

public class Zombie extends Animated
{
    private static Player playerRef;
    private static Target targetRef;
    private ScoreTracker scoreTrackerRef;
    
    private final int moveThreshold = 10;
    
    private int framesPerDamage = 120;
    private int collisionFrameCounter = framesPerDamage-10;
    
    private GreenfootSound targetHurtSound;
    private GreenfootSound neutralSound;
    private Random rand;
    
    public Zombie(Player player, Target target, ScoreTracker scoreTracker){
        super();
        
        // configure the animated superclass
        this.footstepSounds = new GreenfootSound[2];
        this.footstepSounds[0] = new GreenfootSound("leftFootstep.mp3");
        this.footstepSounds[0].setVolume(30);
        this.footstepSounds[1] = new GreenfootSound("rightFootstep.mp3");
        this.footstepSounds[1].setVolume(30);
        this.scoreTrackerRef = scoreTracker;
        this.rootImgFP = "Zombie";
        this.numFrames = 2;
        this.moveSpeed = 2;
        this.playerRef = player;
        this.targetRef = target;
        this.frameSkip = 30;
        this.spriteScale = 40;
        
        // load the animated superclass images
        super.loadAllImages();
        
        targetHurtSound = new GreenfootSound("dogHurt.mp3");
        neutralSound = new GreenfootSound("zombie.mp3");
        rand = new Random();
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
            if(scoreTrackerRef.targetHealth > 0){
                scoreTrackerRef.targetHealth -= 1;
                collisionFrameCounter = 1;
                System.out.println("Damage");
                targetHurtSound.play();
            }
        }
        else{
            collisionFrameCounter += 1;
        }
        this.setMoving(false);
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
        
        if(this.rand.nextInt(200) == 0)
            neutralSound.play();
        
    }
}
