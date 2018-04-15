/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * Toothpick - Basic melee weapon item.
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 31Mar18    Glenn          Initial Item Superclass created
 * 15Apr18    Kevin          Added Vertical Image
*/

package Items;

import java.awt.Toolkit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Toothpick extends MeleeItem {
    public Toothpick(double nx, double ny){
        itemHorizontalImg = new Image("/images/toothpickSpriteHorizontal.png",
                             Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.075,
                             Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.075,
                             true, false);
        itemVerticalImg = new Image("/images/toothpickSpriteVertical.png",
                             Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.05,
                             Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.05,
                             true, false);
        imageView = new ImageView(itemHorizontalImg);
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
        
        attack = 5.0;
    }
}
