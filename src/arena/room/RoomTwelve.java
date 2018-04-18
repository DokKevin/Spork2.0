/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * RoomTwelve - Is the twelfth room for the map
 * Change Log
 * /////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 03Apr18    Kevin          Initial RoomTwelve Created - initially is almost clone
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
public class RoomTwelve extends Arena{
    private static RoomTwelve room = new RoomTwelve();
    
    private enum Dir{
        TOP, BOTTOM, LEFT, RIGHT
    }
    
    public static RoomTwelve getInstance(){
        return room;
    }
    
    private RoomTwelve(){}
    
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
        doorList[0] = BossRoom.getInstance();
        doorList[1] = RoomTen.getInstance();
        doorList[2] = NoRoom.getInstance();
        doorList[3] = NoRoom.getInstance();
        
        monsList.clear();
        obsList.clear();
        itemList.clear();
        
        // Add Monsters - this will be changed to random in later iterations
        Actor lollipop = new Lollipop((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.12), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.20));
        monsList.add(lollipop);
        Actor lollipop2 = new Lollipop((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.42), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.32));
        monsList.add(lollipop2);
        Actor lollipop3 = new Lollipop((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.66), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.30));
        monsList.add(lollipop3);
        Actor lollipop4 = new Lollipop((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.81), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.25));
        monsList.add(lollipop4);
        
        // Add Obstacles - this will be changed to random in later iterations
        Obstacle cinRoll = new CinnamonRoll((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.35), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.50));
        obsList.add(cinRoll);
        Obstacle gumDrops = new GumDrops((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.70), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.30));
        obsList.add(gumDrops);
    }
}