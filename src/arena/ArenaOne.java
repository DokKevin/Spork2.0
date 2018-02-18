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
*/

package arena;

import java.awt.Toolkit;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import actors.*;
import input.Input;
import javafx.animation.AnimationTimer;

public class ArenaOne {
    
    private enum Dir{
        TOP, BOTTOM, LEFT, RIGHT
    }
    
    private boolean collision = false;
    private static Player player;
    
   public static void start(Stage stage) {
       
      //Drawing a Circle (representing an obstacle)
      Circle obs = new Circle();
      obs.setCenterX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.40);
      obs.setCenterY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.50);
      obs.setRadius(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.05);
      obs.setFill(Color.BLUE);
      obs.setStrokeWidth(0.0);

      //Creating a Pane object
      Pane root = new Pane();

      //Creating a scene object
      Scene scene = new Scene(root, 600, 300);

      //Setting title to the Stage
      stage.setTitle("Spork");
      
      //Adding scene to the stage
      stage.setScene(scene);
      scene.getStylesheets().add(ArenaOne.class.getResource("ArenaOne.css").toExternalForm());
      root.getStyleClass().add("arena");
      stage.setFullScreen(true);
      
    // This can be added to its own function when arena gets a superclass
    //Add player to layer
    player = Player.getInstance();
    player.setLayer(root); // This adds player to the layer
    player.setMoveBounds(getBound(Dir.LEFT), getBound(Dir.RIGHT), getBound(Dir.TOP), getBound(Dir.BOTTOM));
    
    // Create input so player can move
    Input input = new Input(scene);
    input.addListeners(); //TODO: Remove listeners on game over.
    player.setInput(input);
    
    //Position player at center of screen
    player.setX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.5);
    player.setY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.5);
    // End arena superclass function
    
      // Add obstacle to pane
      root.getChildren().addAll(obs);
      
      //Displaying the contents of the stage
      stage.show();
      
      AnimationTimer gameLoop = new AnimationTimer(){
          @Override
          public void handle(long now){
              //See this for info and TODOs: https://stackoverflow.com/questions/29057870/in-javafx-how-do-i-move-a-sprite-across-the-screen
              
              // Player Input
              player.processInput();
              
              player.move();
              //TODO: Enemies move as well
              
              //TODO: Check Collisions
              
              player.updateUI();
              //TODO: Enemies update UI
              
              //TODO: Check removability
              
              //TODO: Attack / Be Attacked
              
              //TODO: Remove Dead Enemies
              
              //Updates / Etc...
          }
      };
      gameLoop.start();
   }
   
   // Get Boundaries of Arena
   private static double getBound(Dir nDir){ 
       switch (nDir){
           case TOP:
                return (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.16);
           case BOTTOM:
                return (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.17));
           case LEFT:
                return (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.145);
           case RIGHT:
                return (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.145));
           default: // returns top, should return error message. Shouldn't reach this stage
               return (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.16);
       }
   }
}