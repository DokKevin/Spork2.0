/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * RoomTen - Is the tenth room for the map
 * Change Log
 * /////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 03Apr18    Kevin          Initial RoomTen Created - initially is almost clone
 *                              of LevelOneRoomOne
 */

package arena.room;

import obstacle.*;
import java.awt.Toolkit;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import actors.*;
import actors.monsters.*;
import arena.Arena;
import gameHandler.GameHandler;
import javafx.scene.input.KeyCombination;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

// May change this to simply "Room" when creating maps randomly and having a level subclass (i.e. there is one room class and it is filled randomly with stuff based on the level instead of having multiple room classes.
public class RoomTen extends Arena{
    private static RoomTen room = new RoomTen();
    
    private enum Dir{
        TOP, BOTTOM, LEFT, RIGHT
    }
    
    public static RoomTen getInstance(){
        return room;
    }
    
    private RoomTen(){}
    
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
        doorList[0] = RoomEleven.getInstance();
        doorList[1] = RoomNine.getInstance();
        doorList[2] = NoRoom.getInstance();
        doorList[3] = RoomTwelve.getInstance();
        
        monsList.clear();
        obsList.clear();
        itemList.clear();
        
        // Add Monsters - this will be changed to random in later iterations
        Actor gummiWorm = new GummiWorm((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.30), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.30));
        monsList.add(gummiWorm);
        Actor donut = new Donut((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.50), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.70));
        monsList.add(donut);
        
        // Add Obstacles - this will be changed to random in later iterations
        Obstacle cinRoll = new CinnamonRoll((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.35), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.50));
        obsList.add(cinRoll);
        Obstacle gumDrops = new GumDrops((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.70), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.30));
        obsList.add(gumDrops);
    }
}