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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class InventoryMenu {
    static Rectangle menu = new Rectangle();
    static Pane inventory = new Pane();
    static ImageView itemIcon;
    static double translateX;
    static double translateY;
    static int iterator;
    
    public static void setMenu(Pane pane){
        translateX = 0;
        translateY = Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.05;
        iterator = 1;
        pane.getChildren().add(inventory);
        inventory.getChildren().add(menu);
        setModes();
        if(!Player.getInstance().getInventory().isEmpty()){
            Player.getInstance().getInventory().forEach((item) -> {
                itemIcon = item.getImageView();
                itemIcon.setTranslateX(translateX);
                itemIcon.setTranslateY(translateY);
                inventory.getChildren().add(itemIcon);
                translateX += Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.10;
                iterator++;
                if(iterator == 7){
                    translateX = 0;
                    translateY = Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.2;
                }
            });
            
        }
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
