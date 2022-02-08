import greenfoot.*;

public class ScoreTracker 
{
    private MyWorld worldRef; 
    private GreenfootImage[] healthImages;
    
    private LifeDisplayer playerHealthDisp;
    private LifeDisplayer targetHealthDisp;
    
    public static int playerHealth = 3;
    public static int targetHealth = 3;
    public static int score = 0;
    
    public ScoreTracker(MyWorld worldRef)
    {
        playerHealth = 3;
        targetHealth = 3;
        score = 0;
        
        this.worldRef = worldRef;

        healthImages = new GreenfootImage[4];
        healthImages[0] = new GreenfootImage("life0.png");
        healthImages[1] = new GreenfootImage("life1.png");
        healthImages[2] = new GreenfootImage("life2.png");
        healthImages[3] = new GreenfootImage("life3.png");
        
        playerHealthDisp = new LifeDisplayer(healthImages);
        targetHealthDisp = new LifeDisplayer(healthImages);
        
        worldRef.addObject(playerHealthDisp, worldRef.getWidth() - 45, 30);
        worldRef.addObject(targetHealthDisp, worldRef.getWidth() - 45, 60);
        
        worldRef.showText("Player HP", worldRef.getWidth() - 130, 30);
        worldRef.showText("Target HP", worldRef.getWidth() - 130, 60);
        
    }

    public void act(){
        worldRef.showText(String.format("Score = %d", score), 50, 20);
        
        // System.out.println(targetHealth);
        playerHealthDisp.setValue(playerHealth);
        targetHealthDisp.setValue(targetHealth);

    }
}
