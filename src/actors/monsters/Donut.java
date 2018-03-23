/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * Donut - A monster that fights the player. Interacts autonomously with player. 
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 06Mar18    Kevin          Initial Donut Created
*/

package actors.monsters;

import actors.Actor;
import java.awt.Toolkit;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// TODO: create Monster Superclass - Ranged Monster & Melee Monster Subclasses - Monsters extend correct Ranged / Melee Class
public class Donut extends Actor {
    public Donut(double nx, double ny){
        actorImg = new Image("/images/donutSprite.png",
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
        
        speed = 3;
        
        setMoveBounds();
        
        toggleMonster();
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
}
