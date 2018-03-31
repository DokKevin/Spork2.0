/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * GummiWorm - A monster that extends actor. Interacts autonomously with the 
 *             player & fights
 * Change Log
 * /////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 06Mar18    Kevin          Initial GummiWorm Created
 * 12Mar18    Kevin          Added functionality to determine if actor is a monster
 * 22Mar18    Kevin          Moved change direction to its own function
 * 30Mar18    Kevin          Extends correct Superclass now
 *                           Overrode new abstract methods
 * 31Mar18    Kevin          Monsters can't move until player does first
*/

package actors.monsters;

import java.awt.Toolkit;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GummiWorm extends MeleeMonster {
    public GummiWorm(double nx, double ny){
        actorImg = new Image("/images/gummiWormSprite.png",
                             Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.1,
                             Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.1,
                             true, false);
        imageView = new ImageView(actorImg);
        
        // This checking will have to be enhanced, it should not be able to be outside playable area.
        if(nx > 0){
            x = nx;
        } else {
            x = 0.0;
        }
        
        if(ny > 0){
            y = ny;
        } else {
            y = 0.0;
        }
        
        top = getY();
        bottom = getY() + actorImg.getHeight();
        left = getX();
        right = getX() + actorImg.getWidth();
        
        setStats();
        
        setMoveBounds();
    }
    
    @Override
    public void move(){
        // Moves randomly
        if(collision || hitBound){
            changeDirection();
            setCollision(false);
        } else {
            // Move the same direction
        }
        
        super.move();
        super.checkBounds();
    }
    
    @Override
    public void changeDirection(){
        int randomNum = ThreadLocalRandom.current().nextInt(0, 8); // Corresponds to directions in Direction enum in Actor.java
            
        switch(randomNum){
            case 0:
                setDy(-speed);
                break;
            case 1:
                setDy(-speed);
                setDx(speed);
                break;
            case 2:
                setDx(speed);
                break;
            case 3:
                setDy(speed);
                setDx(speed);
                break;
            case 4:
                setDy(speed);
                break;
            case 5:
                setDy(speed);
                setDx(-speed);
                break;
            case 6:
                setDx(-speed);
                break;
            case 7:
                setDy(-speed);
                setDx(-speed);
                break;
        }
    }
    
    // This will have to be enhanced
    @Override
    protected void setStats(){
        speed = 3.0;
        attack = 1.0;
        hp = 10.0;
        
        super.setStats();
    }
}
