/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * BossRoom - Is the room with the boss in it for the map
 * Change Log
 * /////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 03Apr18    Kevin          Initial BossRoom Created - initially is almost clone
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

// May change this to simply "Room" when creating maps randomly and having a level subclass (i.e. there is one room class and it is filled randomly with stuff based on the level instead of having multiple room classes.
public class BossRoom extends Arena{
    private static BossRoom room = new BossRoom();
    
    private enum Dir{
        TOP, BOTTOM, LEFT, RIGHT
    }
    
    public static BossRoom getInstance(){
        return room;
    }
    
    private BossRoom(){}
    
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
        doorList[1] = RoomEleven.getInstance();
        doorList[2] = RoomTwelve.getInstance();
        doorList[3] = NoRoom.getInstance();
        
        monsList.clear();
        obsList.clear();
        itemList.clear();
        
        // Add Monsters - this will be changed to random in later iterations
        Actor Boss = new MalinerKren((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.35), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.10));
        monsList.add(Boss);
        // Add Obstacles - this will be changed to random in later iterations
        Obstacle cinRoll = new CinnamonRoll((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.15), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.15));
        Obstacle cinRoll2 = new CinnamonRoll((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.85), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.15));
        obsList.add(cinRoll);
        obsList.add(cinRoll2);
        
//        mp3MusicFile = new Media(LevelOneRoomOne.class.getResource("../Level1.mp3").toExternalForm()); 
//        musicPlayer = new MediaPlayer(mp3MusicFile);
    }
}