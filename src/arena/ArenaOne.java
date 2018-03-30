/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * ArenaOne - Handles the level one basic arena. Will be expanded later when map
 * is created and levels are created.
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
 * 30Mar18    Kevin          Updated Based on new Superclasses
 *                           Added new Damage Functionality
 *                           Added new Death Functionality
 */

package arena;

import obstacle.*;
import java.awt.Toolkit;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ProgressBar;
import actors.*;
import actors.monsters.*;
import menus.EscapeMenu;
import input.Input;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCombination;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import menus.GameOver;

public class ArenaOne {
    static Actor gummiWorm = new GummiWorm((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.30), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.30));
    static Actor donut = new Donut((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.50), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.70));
    static Obstacle cinRoll = new CinnamonRoll((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.35), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.50));
    static Obstacle gumDrops = new GumDrops((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.70), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.30));
    static ProgressBar healthBar;
    static ProgressBar xpBar;
    static Stage currStage;
    static Scene currScene;
    static Pane root;
    
    private enum Dir{
        TOP, BOTTOM, LEFT, RIGHT
    }
    
    private static Player player;
    
    private static ArrayList<Obstacle> obsList = new ArrayList(5);
    // TODO: Change so monsters have a monster superclass & possibly a ranged vs. melee superclass or marker class pattern
    private static ArrayList<Actor> monsList = new ArrayList(5);
    
   public static void start(Stage stage, Scene scene) {
        Media mp3MusicFile = new Media(ArenaOne.class.getResource("Level1.mp3").toExternalForm()); 
        MediaPlayer musicplayer = new MediaPlayer(mp3MusicFile);
        musicplayer.setAutoPlay(true);
        
        currStage = stage;
        currScene = scene;
        
        currStage.setFullScreenExitHint(null);
        currStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        //Creating a Pane object
        root = new Pane();
        //Creating a scene object
        //Scene scene = new Scene(root, 600, 300);
        currScene.setRoot(root);
        //Setting title to the Stage
        currStage.setTitle("Spork");
      
        //Adding scene to the stage
        currStage.setScene(currScene);
        currScene.getStylesheets().add(ArenaOne.class.getResource("ArenaOne.css").toExternalForm());
        root.getStyleClass().add("arena");
        currStage.setFullScreen(true);
      
        currScene.setOnKeyPressed(e -> {
            switch(e.getCode()){
                case ESCAPE:
                    EscapeMenu.setStage(root, currStage);
                    break;
            }
        });
      
    // This can be added to its own function when arena gets a superclass
    //Add player to layer
    player = Player.getInstance();
    healthBar = player.getHpBar();
    xpBar = player.getExpBar();
    //player.setLayer(root); // This adds player to the layer
    
    // Create input so player can move
    Input input = new Input(currScene);
    input.addListeners(); //TODO: Remove listeners on game over.
    player.setInput(input);
    
    //Position player at center of screen
    // TODO: Create a setToCenter function for player (to be used when player is changing arenas / levels
    player.setX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.5);
    player.setY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.5);
    // End arena superclass function
    
    // This can be its own function too
    // TODO: Allow player to interact more naturally with the obstacle (i.e. some overlap when correct)
    //       Possibly allow player and obstacle to have different panes / layers?
    //Obstacle cinRoll = new CinnamonRoll((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.35), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.50));
    //cinRoll.setLayer(root);
    //cinRoll.updateUI();
    
    //Obstacle gumDrops = new GumDrops((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.70), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.30));
    //gumDrops.setLayer(root);
    //gumDrops.updateUI();
    
    obsList.add(cinRoll);
    obsList.add(gumDrops);
    // End obstacle population function
    
    // Maybe also a function
    // Same as above, but for monsters not obstacles
    //Actor gummiWorm = new GummiWorm((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.30), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.30));
    //gummiWorm.setLayer(root);
    //gummiWorm.updateUI();
    
    monsList.add(gummiWorm);
    monsList.add(donut);
    // End monster function
    
    healthBar.setPrefSize(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.2, 
                   Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.05);
    healthBar.setTranslateX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.07);
    xpBar.setPrefSize(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.1, 
                   Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.03);
    xpBar.setTranslateX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.07);
    xpBar.setTranslateY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.058);
    healthBar.getStyleClass().add("healthBar");
    xpBar.getStyleClass().add("xpBar");
    setObjects(root);
    //root.getChildren().add(healthBar);
    //root.getChildren().add(xpBar); //can do these two in one-line, but kept hitting a reflection error
                                   //TODO: get these added together
    
      //Displaying the contents of the stage
      currStage.show();
      
      AnimationTimer gameLoop = new AnimationTimer(){
          @Override
          public void handle(long now){
              //See this for info and TODOs: https://stackoverflow.com/questions/29057870/in-javafx-how-do-i-move-a-sprite-across-the-screen
              
              player.processInput();
              
              player.move();
              
              monsList.forEach((monster) -> {
                  monster.move();
              });
              
              checkMonsterCollisions();
              checkPlayerCollisions();
              
              player.updateUI();
              
              monsList.forEach((monster) -> {
                  monster.updateUI();
              });
              
              //TODO: Check Attack Collisions - this may take some work
              
              checkDeaths();
              
              if(player.isDead()){
                // Activate Game Over, may change in future development
                stop();
                GameOver.setStage(root, currStage);
              }
              
              // TODO: Move to Player class after death logic is in
//              updateXpBar(player.getExp()/player.getMaxExp());
          }
      };
      gameLoop.start();
   }
   
   public static void setObjects(Pane pane){
       pane.getChildren().clear();
       pane.getChildren().add(healthBar);
       pane.getChildren().add(xpBar);
       cinRoll.setLayer(pane); // Will want to do this based off of the obsList instead of individual obstacles
       cinRoll.updateUI();
       gumDrops.setLayer(pane);
       gumDrops.updateUI();
       gummiWorm.setLayer(pane); // Will want to do this based off of the monsList instead of individual monsters
       gummiWorm.updateUI();
       donut.setLayer(pane);
       donut.updateUI();
       player.setLayer(pane);
   }
   
   private static void checkPlayerCollisions(){
        if(!obsList.isEmpty()){
            obsList.forEach((obstacle) -> {
                player.checkObsCollision(obstacle);
            });
        }
        
        if(!monsList.isEmpty()){
            monsList.forEach((monster) -> {
                player.checkActorCollision(monster);
            });
        }
   }
   
   private static void checkMonsterCollisions(){
        if(!monsList.isEmpty()){
            monsList.forEach((monster) -> {
                monster.checkActorCollision(player);
            });

            monsList.forEach((monster) -> {
                obsList.forEach((obstacle) -> {
                     monster.checkObsCollision(obstacle);
                });
            });

            monsList.forEach((monster) -> {
                monsList.forEach((monster2) -> {
                     monster.checkActorCollision(monster2);
                });
            });
        }
   }
   
    public static void checkDeaths(){
        if(!monsList.isEmpty()){
            monsList.forEach((monster) -> {
                if(monster.isDead()){
                    monster.removeFromLayer();
                    monsList.remove(monster);
                }
            });
        }
    }
}