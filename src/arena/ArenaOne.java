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
import characterGUI.EscapeMenu;
import input.Input;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCombination;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ArenaOne {
    static Actor gummiWorm = new GummiWorm((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.30), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.30));
    static Obstacle cinRoll = new CinnamonRoll((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.35), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.50));
    static Obstacle gumDrops = new GumDrops((Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.70), (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.30));
    static ProgressBar healthBar = new ProgressBar(1F);
    static ProgressBar xpBar = new ProgressBar(0F);
    
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
        
       stage.setFullScreenExitHint(null);
       stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
      //Creating a Pane object
      Pane root = new Pane();
      //Creating a scene object
      //Scene scene = new Scene(root, 600, 300);
      scene.setRoot(root);
      //Setting title to the Stage
      stage.setTitle("Spork");
      
      //Adding scene to the stage
      stage.setScene(scene);
      scene.getStylesheets().add(ArenaOne.class.getResource("ArenaOne.css").toExternalForm());
      root.getStyleClass().add("arena");
      stage.setFullScreen(true);
      
      scene.setOnKeyPressed(e -> {
          switch(e.getCode()){
              case ESCAPE:
                  EscapeMenu.setStage(root, stage);
                  break;
          }
      });
      
    // This can be added to its own function when arena gets a superclass
    //Add player to layer
    player = Player.getInstance();
    //player.setLayer(root); // This adds player to the layer
    
    // Create input so player can move
    Input input = new Input(scene);
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
      stage.show();
      
      //These two events are for stakeholder demonstration. Both the events and the functuions they call will be removed
      root.setOnMouseClicked(e -> { //"gets hit" and "kills something"
         setHP(-0.1); 
         setXP(10);
      });
      
      AnimationTimer gameLoop = new AnimationTimer(){
          @Override
          public void handle(long now){
              //See this for info and TODOs: https://stackoverflow.com/questions/29057870/in-javafx-how-do-i-move-a-sprite-across-the-screen
              
              // Player Input
              player.processInput();
              
              player.move();
              
              monsList.forEach((monster) -> {
                  monster.move();
              });
              
              checkPlayerCollisions();
              checkMonsterCollisions();
              
              player.updateUI();
              
              monsList.forEach((monster) -> {
                  monster.updateUI();
              });
              
              //TODO: Check removability
              
              //TODO: Check Attack Collisions - this may take some work
              
              //TODO: Remove Dead Enemies
              
              //Updates / Etc...
          }
      };
      gameLoop.start();
   }
   
   public static void setObjects(Pane pane){
       pane.getChildren().clear();
       pane.getChildren().add(healthBar);
       pane.getChildren().add(xpBar);
       cinRoll.setLayer(pane);
       cinRoll.updateUI();
       gumDrops.setLayer(pane);
       gumDrops.updateUI();
       gummiWorm.setLayer(pane);
       gummiWorm.updateUI();
       player.setLayer(pane);
   }
   
   private static void checkPlayerCollisions(){
       obsList.forEach((obstacle) -> {
           player.checkObsCollision(obstacle);
        });
       
       monsList.forEach((monster) -> {
           player.checkActorCollision(monster);
        });
   }
   
   private static void checkMonsterCollisions(){
       // Change this to monster type instead of actor type when monster superclass is created
       monsList.forEach((monster) -> {
           monster.checkActorCollision(player);
       });
       
       monsList.forEach((monster) -> {
           obsList.forEach((obstacle) -> {
                monster.checkObsCollision(obstacle);
           });
       });
   }
   
   public static void setHP(double amount){
       healthBar.setProgress(healthBar.getProgress() + amount);
   }
   static double maxXp = 100;
   public static void setXP(double amount){
       xpBar.setProgress(xpBar.getProgress() + amount/maxXp);
       if(xpBar.getProgress() >= 1)
       {
           xpBar.setProgress(0);
           maxXp = maxXp + (maxXp * .5);//player's xp gets higher
       }
   }
}