/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * MiniBossRoom - Is the room the mini boss is in for the map
 * Change Log
 * /////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 03Apr18    Kevin          Initial MiniBossRoom Created - initially is almost clone
 *                              of LevelOneRoomOne
 *                           Added mini boss to room
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
public class MiniBossRoom extends Arena{
    private static MiniBossRoom room = new MiniBossRoom();
    
    private enum Dir{
        TOP, BOTTOM, LEFT, RIGHT
    }
    
    public static MiniBossRoom getInstance(){
        return room;
    }
    
    private MiniBossRoom(){}
    
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
        doorList[0] = RoomFive.getInstance();
        doorList[1] = TreasureRoomTwo.getInstance();
        doorList[2] = RoomSix.getInstance();
        doorList[3] = TreasureRoomOne.getInstance();
        
        monsList.clear();
        obsList.clear();
        itemList.clear();
        
        // Add Monsters - this will be changed to random in later iterations
        Actor sourGummiWyrm = new SourGummiWyrm((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.5), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.5));
        monsList.add(sourGummiWyrm);
        
        // Add Obstacles - this will be changed to random in later iterations
        Obstacle cinRoll = new CinnamonRoll((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.35), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.50));
        obsList.add(cinRoll);
        Obstacle gumDrops = new GumDrops((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.70), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.30));
        obsList.add(gumDrops);
        musicPlayer.stop();
        mp3MusicFile = new Media(LevelOneRoomOne.class.getResource("../MiniBoss.mp3").toExternalForm()); 
        musicPlayer = new MediaPlayer(mp3MusicFile);
    }
}