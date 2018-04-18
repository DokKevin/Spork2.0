/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * Maliner Kren - Level 1 Boss that is so obese it cannot move
 * Change Log
 * /////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 11Apr18    Glenn          Initial Boss Created
*/
package actors.monsters;

import java.awt.Toolkit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MalinerKren extends RangedMonster {
    
    public MalinerKren(double nx, double ny){
        actorImg = new Image("/images/malinerKrenSprite.png",
                             Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.4,
                             Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.4,
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
        //Maliner Kren Cannot Move
        canMove = false;
    }
    
    @Override
    public void changeDirection(){
        //empty
    }
    
    @Override
    protected void setStats(){
        speed = 0;
        attack = 8.0;
        hp = 16.0;
        
        super.setStats();
    }
}
