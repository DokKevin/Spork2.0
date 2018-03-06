/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * GummiWorm - A monster that extends actor. Interacts autonomously with the 
 *             player & fights
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 06Mar18    Kevin          Initial GummiWorm Created
*/

package actors.monsters;

import actors.Actor;
import java.awt.Toolkit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GummiWorm extends Actor {
    public GummiWorm(){
        actorImg = new Image("/images/gummiWormSprite.png", Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.1, Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.1, true, false);
        imageView = new ImageView(actorImg);
        
        setMoveBounds();
    }
}
