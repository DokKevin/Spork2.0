/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * CinnamonRoll - An Obstacle players cannot move through
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 19Feb18    Kevin          Initial Cinnamon Roll created
*/

package obstacle;

import java.awt.Toolkit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CinnamonRoll extends Obstacle{
    public CinnamonRoll(double nx, double ny){
        obsImg = new Image("/images/cinnamonRollSprite.png", Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.075, Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.075, true, false);
        imageView = new ImageView(obsImg);
        
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
        bottom = getY() + obsImg.getHeight();
        left = getX();
        right = getX() + obsImg.getWidth();
    }
}
