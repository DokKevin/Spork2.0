/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * GumDrops - An Obstacle players cannot move through
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 28Feb18    Kevin          Initial Gum Drops created
*/

package obstacle;

import java.awt.Toolkit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GumDrops extends Obstacle{
    public GumDrops(double nx, double ny){
        // TODO: Replace gumdrops image with Sprite image - currently using full resolution image
        obsImg = new Image("/images/gumdropsSprite.png", Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.09, Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.09, true, false);
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
