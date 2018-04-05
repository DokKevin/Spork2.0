/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * InventoryMenu - Handles GUI for the inventory when user hits the I key
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 06Mar18    Glenn          First draft created for the Escape Menu
*/
package menus;

import Items.Item;
import Items.Toothpick;
import actors.Player;
import java.awt.Toolkit;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class InventoryMenu {
    static Rectangle menu = new Rectangle();
    static Pane inventory = new Pane();
    static Item Toothpick = new Toothpick(20, 20);
    public static void setMenu(Pane pane){
        pane.getChildren().add(inventory);
        inventory.getChildren().add(menu);
        setModes();
    }
    
    public static void clearMenu(Pane pane){
        inventory.getChildren().clear();
        pane.getChildren().remove(inventory);
    }
    
    public static void setModes(){
        menu.setFill(Color.LIGHTGRAY);
        menu.setWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.70);
        menu.setHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.50);
        menu.setTranslateX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.15);
        menu.setTranslateY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.25);
        menu.setArcHeight(30);
        menu.setArcWidth(30);
    }
}
