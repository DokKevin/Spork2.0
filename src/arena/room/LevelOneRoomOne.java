/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * LevelOneRoomOne - Handles the functionality of the game
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 24jan18    Kevin          Changed from Animation_Demo to ArenaOne and created
 *                              background and adjusted boundaries.
 * 30Jan18    Kevin          Made Stage FullScreen & Fixed Boundaries
 * 06Feb18    Kevin          Made ball size and position dynamic
 *                           Added fixed, generic obstacle - to be dynamic later
 * 07Feb18    Kevin          Added get extremes function for circles - may need
 *                              updated later - and get bounds function for arena
 * 08Feb18    Kevin          Changed nested if to switch
 * 18Feb18    Kevin          Added sprite to game & updated movement
 * 19Feb18    Kevin          Added collision checking
 * 20Feb18    Glenn          Added a HUD for HP and XP
 * 06Mar18    Kevin          Added first monster
 * 06Mar18    Glenn          Added Escape Menu
 * 22Mar18    Kevin          Added Some Comments
 * 22Mar18    Kevin          Added new Monster (Donut)
 * 29Mar18    Kevin          Updated to LevelOneRoomOne & Moved Game Handling to
 *                              its own file
 *                           Also updated tabs to fit NetBeans standards
 * 30Mar18    Kevin          Updated Based on new Superclasses
 *                           Added new Damage Functionality
 *                           Added new Death Functionality
 * 31Mar18    Kevin          Fixed Healthbar Issue
 * 31Mar18    Glenn          Added Toothpick
 * 03Apr18    Kevin          Fixed shared lists error & added functionality to
 *                              change arenas.
 *                           Updated doorList with new rooms
 */

package arena.room;

import Items.Item;
import Items.Toothpick;
import obstacle.*;
import java.awt.Toolkit;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import actors.*;
import actors.monsters.*;
import arena.Arena;
import gameHandler.GameHandler;
import menus.EscapeMenu;
import javafx.scene.input.KeyCombination;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import menus.InventoryMenu;

// May change this to simply "Room" when creating maps randomly and having a level subclass (i.e. there is one room class and it is filled randomly with stuff based on the level instead of having multiple room classes.
public class LevelOneRoomOne extends Arena{
    private static boolean escMenuOpen = false;
    private static boolean inventoryOpen = false;
    
    private static LevelOneRoomOne roomOne = new LevelOneRoomOne();
    
    private enum Dir{
        TOP, BOTTOM, LEFT, RIGHT
    }
    
    public static LevelOneRoomOne getInstance(){
        return roomOne;
    }
    
    private LevelOneRoomOne(){}
    
    @Override
    public void start(Stage stage, Scene scene) {
        init();
        
        musicPlayer.setAutoPlay(true);
        
        currStage = stage;
        currScene = scene;
        
        currStage.setFullScreenExitHint(null);
        currStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        //Creating a Pane object
        root = new Pane();
        //Creating a scene object
        currScene.setRoot(root);
        //Setting title to the Stage
        currStage.setTitle("Spork");
      
        //Adding scene to the stage
        currStage.setScene(scene);
        // May change ArenaOne.css to LevelOne.css
        currScene.getStylesheets().add(LevelOneRoomOne.class.getResource("../ArenaOne.css").toExternalForm());
        root.getStyleClass().add("arena");
        currStage.setFullScreen(true);
      
        currScene.setOnKeyPressed(e -> {
            switch(e.getCode()){
                case ESCAPE:
                    if(!escMenuOpen){
                        player.setMovable(false);
                        EscapeMenu.setStage(root, currStage, this);
                        escMenuOpen = true;
                    }
                    else{
                        player.setMovable(true);
                        setObjects(root);
                        escMenuOpen = false;
                    }
                    break;
                case I:
                    if(!inventoryOpen){
                        InventoryMenu.setMenu(root);
                        player.setMovable(false);
                        inventoryOpen = true;
                    }
                    else{
                        InventoryMenu.clearMenu(root);
                        player.setMovable(true);
                        setObjects(root);
                        inventoryOpen = false;
                    }       
                    break;
            }
        });
        
        healthBar = player.getHpBar();
        xpBar = player.getExpBar();
        HPLabel = player.getHPLabel();
        XPLabel = player.getXPLabel();
        
        // Create input so player can move
        input.setScene(currScene);
        input.addListeners(); //TODO: Remove listeners on game over.
        // Seems like a smell to require setting the player's input every time.
        player.setInput(input);
    
        //Position player at center of screen
        player.setToCenter();
    
        healthBar.setPrefSize(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.2, 
                       Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.05);
        healthBar.setTranslateX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.07);
        healthBar.setTranslateY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.05);
        xpBar.setPrefSize(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.1, 
                       Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.03);
        xpBar.setTranslateX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.29);
        xpBar.setTranslateY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.05);
        healthBar.getStyleClass().add("healthBar");
        xpBar.getStyleClass().add("xpBar");
        HPLabel.setTranslateX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.07);
        XPLabel.setTranslateX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.29);
        HPLabel.getStyleClass().add("HPLabel");
        XPLabel.getStyleClass().add("XPLabel");
        setObjects(root);

        //Displaying the contents of the stage
        currStage.show();
        
        addDoors();

        GameHandler.startGame();
    }
    
    @Override
    protected void init(){
        doorList[0] = RoomThree.getInstance();
        doorList[1] = RoomFive.getInstance();
        doorList[2] = NoRoom.getInstance();
        doorList[3] = RoomTwo.getInstance();
        
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
        
        // Add Items - this will be changed to random in later iterations
        Item toothPick = new Toothpick((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.25), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.25));
        itemList.add(toothPick);
        
        mp3MusicFile = new Media(LevelOneRoomOne.class.getResource("../Level1.mp3").toExternalForm()); 
        musicPlayer = new MediaPlayer(mp3MusicFile);
    }
}