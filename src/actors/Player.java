/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * Character - Creates a singleton character and handles the stats for the
 *             player's character created
 * Change Log
 * /////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 18Jan18    Kevin          Seperated CharacterGUI and Character Class
 * 31Jan18    Kevin          Determined Parameters and Added Checking to Setters
 * 18Feb18    Kevin          Created Actor Superclass for Character & Monsters
 * 19Feb18    Kevin          Added collision checking for obstacles.
 * 12Mar18    Kevin          Added functionality to determine if actor is a player
 * 22Mar18    Kevin          Overrode new changeDirection method
 * 29Mar18    Kevin          Added setToCenter method
 * 30Mar18    Kevin          Updated Stats Variable Types & Overrode New Methods
 *                           Player Handles Progress & Exp Bar
 *                           Updated Damage Functionality
 * 31Mar18    Kevin          Monsters can't move until player does first
 * 31Mar18    Glenn          Added Inventory
 * 03Apr18    Kevin          Add functionality to change arenas
 *                           Fixed Monster Bounce Error
*/

package actors;

import Items.Item;
import input.Input;
import java.awt.Toolkit;
import java.util.ArrayList;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends Actor {
    //Instantiates Singleton
    private static Player player = new Player();
    
    private double exp;
    private String username;
    private String job; //These are classes (ex: Bar-pear-ian)
    
    public double minExp;
    public double maxExp;
    
    private Input input;
    
    private static ProgressBar healthBar = new ProgressBar(1F);
    private static ProgressBar xpBar = new ProgressBar(0F);
    
    private static ArrayList<Item> inventory = new ArrayList(10);
    
    private boolean hasMoved = false;
    private boolean isFirst = true;
    
    // constructor for the Singleton. The stats will vary, so begins with nothing
    // Parameters will change as development continues
    private Player(){
        actorImg = new Image("/images/chefSprite.png", Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.1, Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.1, true, false);
        imageView = new ImageView(actorImg);
        
        setStats();
        
        setMoveBounds();
    }
    
    //singleton function to get instance
    public static Player getInstance(){
        return player;
    }
    
    //all getters and setters
    public String getUsername(){
        return username;
    }
    
    public double getExp(){
        return exp;
    }
    
    public double getMaxExp(){
        return maxExp;
    }
    public double getMinExp(){
        return minExp;
    }
    
    public Input getInput(){
        return input;
    }
    
    public ProgressBar getHpBar(){
        return healthBar;
    }
    
    public ProgressBar getExpBar(){
        return xpBar;
    }

    //To Change Min and Max values, see variables at top of class.
    public void setExp(int number){
        if(number < minExp){
            exp = minExp;
        } else if (number > maxExp){
            exp = maxExp;
        } else {
            exp = number;
        }
    }

    public void setUsername(String name){
        username = name;
    }
    
    public void setInput(Input nInp){
        input = nInp;
    }
    
    public void processInput(){
        // Vertical
        if(input.isMoveUp()){
            setDy(-speed);
        } else if(input.isMoveDown()){
            setDy(speed);
        } else {
            setDy(0);
        }
        
        // Horizontal
        if(input.isMoveLeft()){
            setDx(-speed);
        } else if(input.isMoveRight()){
            setDx(speed);
        } else {
            setDx(0);
        }
    }
    
    @Override
    public void move(){
        super.move();
        
        if(!hasMoved){
            if ((Double.compare(getY(), getLy()) != 0) ||
                (Double.compare(getX(), getLx()) != 0)){
                hasMoved = true;
            }
        } // else do nothing
        
        super.checkBounds();
    }
    
    @Override
    public void checkRemovability(){
        //Do nothing for now - Character will not be removed from arena
        //TODO: make game over / restart at check point
    }
    
    @Override
    public void changeDirection(){
        // Do nothing, movement is handled by the player's input
    }
    
    public void setToCenter(){
        setX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.5);
        setY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.5);
    }
    
    public void changeArena(Direction dir){
        switch(dir){
            case N:
                setX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.45);
                setY(maxY);
                break;
            case E:
                setX(minX);
                setY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.45);
                break;
            case S:
                setX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.45);
                setY(minY);
                break;
            case W:
                setX(maxX);
                setY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.45);
                break;
        }
        
        resetMoved();
    }
    
    @Override
    public boolean isPlayer(){
        return true;
    }
    
    @Override
    public boolean isMonster(){
        return false;
    }
    
    public static void updateHpBar(double amount){
        healthBar.setProgress(amount);
    }
   
   public static void updateXpBar(double amount){
        xpBar.setProgress(amount);
        if(xpBar.getProgress() >= 1){
            xpBar.setProgress(0); // Will need to determine how xp bar will behave when leveling up
        }
    }
   
    @Override
    public void getDamagedBy(Actor monster) {
        setHp(getHp() - monster.getAttack());
        updateHpBar(getHp()/getMaxHp());
    }
    
    @Override
    protected void setStats(){
        // Override min stats - these will be dependent on class eventually
        maxHp = 10.0;
        minDef = 1.0;
        maxDef = 10.0;
        minAtt = 1.0;
        maxAtt = 10.0;
        minExp = 0.0;
        maxExp = 10.0;
        
        hp = maxHp;
        defense = maxDef;
        attack = maxAtt;
        exp = minExp;
        username = "";
        job = "Chef"; // TODO: To be changed when we add more class
        speed = 10.0;
    }
    
    public boolean hasMoved(){
        return hasMoved;
    }
    
    public boolean isFirst(){
        return isFirst;
    }
    
    public void notFirst(){
        isFirst = false;
    }
    
    public void resetMoved(){
        hasMoved = false;
        isFirst = true;
        dx = x;
        dy = y;
    }
    
    public void addItem(Item nItem){
        if(inventory.size() < 10){
            inventory.add(nItem);
            nItem.removeFromLayer();
        } // else do nothing
    }
    
    public ArrayList getInventory(){
        return inventory;
    }
}
