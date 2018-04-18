/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * TreasureRoomTwo - Is the second treasure room for the map
 * Change Log
 * /////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 03Apr18    Kevin          Initial TreasureRoomTwo Created - initially is almost 
 *                              clone of LevelOneRoomOne
 */

package arena.room;

import obstacle.*;
import java.awt.Toolkit;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import arena.Arena;
import gameHandler.GameHandler;
import menus.EscapeMenu;
import javafx.scene.input.KeyCombination;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

// May change this to simply "Room" when creating maps randomly and having a level subclass (i.e. there is one room class and it is filled randomly with stuff based on the level instead of having multiple room classes.
public class TreasureRoomTwo extends Arena{
    private static TreasureRoomTwo room = new TreasureRoomTwo();
    
    private enum Dir{
        TOP, BOTTOM, LEFT, RIGHT
    }
    
    public static TreasureRoomTwo getInstance(){
        return room;
    }
    
    private TreasureRoomTwo(){}
    
    @Override
    public void start(Stage stage, Scene scene) {
        init();
        currScene = scene;
        root = new Pane();
        setSettings(root, scene, stage);
        setObjects(root);

        //Displaying the contents of the stage
        currStage.show();
        GameHandler.startGame();
    }
    
    @Override
    protected void init(){
        doorList[0] = NoRoom.getInstance();
        doorList[1] = NoRoom.getInstance();
        doorList[2] = NoRoom.getInstance();
        doorList[3] = MiniBossRoom.getInstance();
        
        monsList.clear();
        obsList.clear();
        itemList.clear();
        
        // Add Obstacles - this will be changed to random in later iterations
        Obstacle cinRoll = new CinnamonRoll((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.35), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.50));
        obsList.add(cinRoll);
        Obstacle gumDrops = new GumDrops((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.70), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.30));
        obsList.add(gumDrops);
    }
}