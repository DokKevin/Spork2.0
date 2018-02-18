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
*/

package actors;

import input.Input;
import java.awt.Toolkit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends Actor {
    //Instantiates Singleton
    private static Player player = new Player();
    
    private int exp;
    private String username;
    private String job; //These are classes
    
    public int minExp;
    public int maxExp;
    
    private Input input;
    
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;
    
    private final double speed = 12;
    
    //constructor for the Singleton. The stats will vary, so begins with nothing
    // Parameters will change as development continues
    private Player(){
        actorImg = new Image("/images/chefSprite.png", Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.1, Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.1, true, false);
        imageView = new ImageView(actorImg);
        
        // Override min stats
        minHp = 0;
        maxHp = 10;
        minDef = 1;
        maxDef = 10;
        minAtt = 1;
        maxAtt = 10;
        minExp = 0; // These two will be dependant on level
        maxExp = 10; // Will change when development gets to level
        
        hp = minHp;
        defense = minDef;
        attack = minAtt;
        exp = minExp;
        username = "";
        job = "Chef"; // TODO: To be changed when we add more class
    }
    
    //singleton function to get instance
    public static Player getInstance(){
        return player;
    }
    
    //all getters and setters
    public String getUsername(){
        return username;
    }
    
    public int getExp(){
        return exp;
    }
    
    public Input getInput(){
        return input;
    }

    public double getMoveBoundMinX(){
        return minX;
    }
    
    public double getMoveBoundMaxX(){
        return maxX;
    }
    
    public double getMoveBoundMinY(){
        return minY;
    }
    
    public double getMoveBoundMaxY(){
        return maxY;
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
        // Only to be called when usernameIsGood method is used.
        username = name;
    }
    
    public void setInput(Input nInp){
        input = nInp;
    }
    
    public void setMoveBounds(double nMinX, double nMaxX, double nMinY, double nMaxY){
        if(nMinX > 0 && nMaxX > 0 && nMinY > 0 && nMaxY > 0){
            minX = nMinX;
            maxX = nMaxX;
            minY = nMinY;
            maxY = nMaxY;
        }
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
        
        checkBounds();
    }
    
    private void checkBounds(){
        // Vertical
        if(Double.compare((getY() + (getImage().getHeight() / 2.0)), getMoveBoundMinY()) < 0){
            setY(getMoveBoundMinY() - (getImage().getHeight() / 2.0)); // Top
        } else if(Double.compare((getY() + getImage().getHeight()), getMoveBoundMaxY()) > 0){
            setY(getMoveBoundMaxY() - getImage().getHeight()); // Bottom
        }
        
        // Horizontal
        if(Double.compare(getX(), getMoveBoundMinX()) < 0){
            setX(getMoveBoundMinX()); // Left
        } else if(Double.compare((getX() + getImage().getWidth()), getMoveBoundMaxX()) > 0){
            setX(getMoveBoundMaxX() - getImage().getWidth()); // Right
        }
    }
    
    @Override
    public void checkRemovability(){
        //Do nothing for now - Character will not die
        //TODO: make game over / restart at check point
    }
}
