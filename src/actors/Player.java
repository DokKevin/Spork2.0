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
        speed = 12;
        
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
    
    public int getExp(){
        return exp;
    }
    
    public Input getInput(){
        return input;
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
        
        super.checkBounds();
    }
    
    @Override
    public void checkRemovability(){
        //Do nothing for now - Character will not die
        //TODO: make game over / restart at check point
    }
}
