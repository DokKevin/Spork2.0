/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * TreasureRoomOne - Is the first treasure room for the map
 * Change Log
 * /////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 03Apr18    Kevin          Initial TreasureRoomOne Created - initially is almost 
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
public class TreasureRoomOne extends Arena{
    private static TreasureRoomOne room = new TreasureRoomOne();
    
    private enum Dir{
        TOP, BOTTOM, LEFT, RIGHT
    }
    
    public static TreasureRoomOne getInstance(){
        return room;
    }
    
    private TreasureRoomOne(){}
    
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
        doorList[1] = MiniBossRoom.getInstance();
        doorList[2] = NoRoom.getInstance();
        doorList[3] = NoRoom.getInstance();
        
        monsList.clear();
        obsList.clear();
        itemList.clear();
        
        // Add Obstacles - this will be changed to random in later iterations
        Obstacle cinRoll = new CinnamonRoll((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.35), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.50));
        obsList.add(cinRoll);
        Obstacle gumDrops = new GumDrops((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.70), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.30));
        obsList.add(gumDrops);
        
        mp3MusicFile = new Media(LevelOneRoomOne.class.getResource("../Level1.mp3").toExternalForm()); 
        musicPlayer = new MediaPlayer(mp3MusicFile);
    }
}