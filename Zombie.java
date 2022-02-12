import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

public class Zombie extends Animated
{
    private static Player playerRef;
    private static Target targetRef;
    private ScoreTracker scoreTrackerRef;
    
    private final int moveThreshold = 10;
    
    private int framesPerDamageTarget = 120;
    private int framesPerDamagePlayer = 90;
    private int collisionFrameCounter = 0;
    
    private GreenfootSound targetHurtSound;
    private GreenfootSound neutralSound;
    private GreenfootSound playerHurtSound;
    private Random rand;
    
    public Zombie(Player player, Target target, ScoreTracker scoreTracker){
        super();
        
        // configure the animated superclass
        this.footstepSounds = new GreenfootSound[2];
        this.footstepSounds[0] = new GreenfootSound("leftFootstep.mp3");
        this.footstepSounds[0].setVolume(50);
        this.footstepSounds[1] = new GreenfootSound("rightFootstep.mp3");
        this.footstepSounds[1].setVolume(50);
        this.playerHurtSound = new GreenfootSound("playerHurt.mp3");
        this.playerHurtSound.setVolume(60);
        this.scoreTrackerRef = scoreTracker;
        this.rootImgFP = "Zombie";
        this.numFrames = 2;
        this.numDirections = 4;
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
    
    private int framesPerDirection = 100;
    private int currentFrames = 100;
    private double dirRandomChance = .1;
    
    private int getTargetDirection(){
        if(framesPerDirection == currentFrames){
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
            currentFrames = 0;
            // System.out.println(yDist);
            // need to have moveThreshold or else zombie will only move left/right if on exact same y-coord as target
            if(Math.abs(xDist) < moveThreshold || Math.abs(xDist) > Math.abs(yDist) && Math.abs(yDist) > moveThreshold){
                // there is a chance it will move left or right
                if(rand.nextDouble() < dirRandomChance){
                    if(yDist > 0){
                        return 0;
                    }
                    else{
                        return 2;
                    }
                }
                // zombie will be moving up or down
                else{
                    if(xDist > 0){
                        return 3;
                    }
                    else{
                        return 1;
                    }  
                }

            }
            else{
                // there is a chance it will move up or down
                if(rand.nextDouble() < dirRandomChance){
                    if(xDist > 0){
                        return 3;
                    }
                    else{
                        return 1;
                    }
                }
                // zombie will be moving left or right
                else{
                    if(yDist > 0){
                        return 0;
                    }
                    else{
                        return 2;
                    }
                }
            }
        }
        else{
            currentFrames += 1;
            return getDirection();
        }
    }
    
    private void handleTargetCollision(){
        if(collisionFrameCounter % framesPerDamageTarget == 0){
            if(scoreTrackerRef.targetHealth > 0 && !targetRef.invincible){
                scoreTrackerRef.targetHealth -= 1;
                collisionFrameCounter = 1;
                //System.out.println("Target Damage");
                targetHurtSound.play();
                
                if(scoreTrackerRef.targetHealth == 2){
                    SpeechBubble b = new SpeechBubble("Reaction2Bubble.png", 300, playerRef, 1.0f, 100, -100);
                    getWorld().addObject(b, 0, 0);
                }
                else if(scoreTrackerRef.targetHealth == 1){
                    SpeechBubble b = new SpeechBubble("Reaction1Bubble.png", 300, playerRef, 1.0f, 100, -100);
                    getWorld().addObject(b, 0, 0);
                }
                else if(scoreTrackerRef.targetHealth == 0){
                    SpeechBubble b = new SpeechBubble("Reaction3Bubble.png", 300, playerRef, 1.0f, 100, -100);
                    getWorld().addObject(b, 0, 0);
                }
            }
        }
        else{
            collisionFrameCounter += 1;
        }
        this.setMoving(false);
    }
    
    private void handlePlayerCollision(){
         if(collisionFrameCounter % framesPerDamagePlayer == 0){
            if(scoreTrackerRef.playerHealth > 0 && !playerRef.invincible){
                scoreTrackerRef.playerHealth -= 1;
                collisionFrameCounter = 1; 
                playerHurtSound.play();
            }
        }
        else{
            collisionFrameCounter += 1;
        }
        this.setMoving(false);
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
            collisionFrameCounter = 0;
            this.setMoving(true);
        }
        
        if(this.rand.nextInt(200) == 0)
            neutralSound.play();
        
    }
}
