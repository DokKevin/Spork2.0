/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * ArenaOne - Handles the level one basic arena. Will be expanded later when map
 * is created and levels are created.
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 24jan18    Kevin          Changed from Animation_Demo to ArenaOne and created
 *                           background and adjusted boundaries.
 * 30Jan18    Kevin          Made Stage FullScreen & Fixed Boundaries
 * 06Feb18    Kevin          Made ball size and position dynamic
 *                           Added fixed, generic obstacle - to be dynamic later
*/

package arena;

import java.awt.Toolkit;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ArenaOne {
   public static void start(Stage stage) {
      //Drawing a Circle (representing the playable character)
      Circle chara = new Circle();
      chara.setCenterX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.5);
      chara.setCenterY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.5);
      chara.setRadius(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.025);
      chara.setFill(Color.BROWN);
      chara.setStrokeWidth(0.0);
      
      //Drawing a Circle (representing an obstacle)
      Circle obs = new Circle();
      obs.setCenterX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.40);
      obs.setCenterY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.50);
      obs.setRadius(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.05);
      obs.setFill(Color.BLUE);
      obs.setStrokeWidth(0.0);

      //Creating a Pane object
      Pane root = new Pane(chara);

      //Creating a scene object
      Scene scene = new Scene(root, 600, 300);

      //Setting title to the Stage
      stage.setTitle("Spork");
      
      //Adding scene to the stage
      stage.setScene(scene);
      scene.getStylesheets().add(ArenaOne.class.getResource("ArenaOne.css").toExternalForm());
      root.getStyleClass().add("arena");
      stage.setFullScreen(true);
      
      // Add obstacle to pane
      root.getChildren().addAll(obs);
      
      scene.setOnKeyPressed(e -> {
          //TODO look into replacing if statement with SWITCH statement
          //TODO Make Obstacle Checking dynamic (and add function(s) for checking pos / obstacles)
          //TODO add functions to get char & obs extremes so we can clean this up.
          if (e.getCode() == KeyCode.LEFT)
          {
            if((chara.getCenterX() - chara.getRadius()) > (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.145) && ((chara.getCenterX() - chara.getRadius()) > (obs.getCenterX() + obs.getRadius() + 1.0) || ((chara.getCenterY() + chara.getRadius()) < (obs.getCenterY() - obs.getRadius())) || ((chara.getCenterY() - chara.getRadius()) > (obs.getCenterY() + obs.getRadius())) || ((chara.getCenterX() + chara.getRadius()) < (obs.getCenterX() - obs.getRadius()))))
            {
                chara.setCenterX(chara.getCenterX() - 1.0); // Changed movement to 1px at a time because it caused an issue with the obstacle
            }
          }
          else if (e.getCode() == KeyCode.RIGHT)
          {
            if((chara.getCenterX() + chara.getRadius()) < (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.145)) && ((chara.getCenterX() - chara.getRadius()) > (obs.getCenterX() + obs.getRadius()) || ((chara.getCenterY() + chara.getRadius()) < (obs.getCenterY() - obs.getRadius())) || ((chara.getCenterY() - chara.getRadius()) > (obs.getCenterY() + obs.getRadius())) || ((chara.getCenterX() + chara.getRadius()) < (obs.getCenterX() - obs.getRadius() - 1.0))))
            {
                chara.setCenterX(chara.getCenterX() + 1.0); // Changed movement to 1px at a time because it caused an issue with the obstacle
            }
          }
          else if (e.getCode() == KeyCode.UP)
          {
              if((chara.getCenterY() - chara.getRadius()) > (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.16) && ((chara.getCenterX() - chara.getRadius()) > (obs.getCenterX() + obs.getRadius()) || ((chara.getCenterY() + chara.getRadius()) < (obs.getCenterY() - obs.getRadius())) || ((chara.getCenterY() - chara.getRadius()) > (obs.getCenterY() + obs.getRadius() + 1.0)) || ((chara.getCenterX() + chara.getRadius()) < (obs.getCenterX() - obs.getRadius()))))
              {
                chara.setCenterY(chara.getCenterY() - 1.0); // Changed movement to 1px at a time because it caused an issue with the obstacle
              }
          }
          else if (e.getCode() == KeyCode.DOWN)
          {
              if((chara.getCenterY() + chara.getRadius()) < (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.16)) && ((chara.getCenterX() - chara.getRadius()) > (obs.getCenterX() + obs.getRadius()) || ((chara.getCenterY() + chara.getRadius()) < (obs.getCenterY() - obs.getRadius() - 1.0)) || ((chara.getCenterY() - chara.getRadius()) > (obs.getCenterY() + obs.getRadius())) || ((chara.getCenterX() + chara.getRadius()) < (obs.getCenterX() - obs.getRadius()))))
              {
                chara.setCenterY(chara.getCenterY() + 1.0); // Changed movement to 1px at a time because it caused an issue with the obstacle
              }
          }
          else if(e.getCode() == KeyCode.SPACE)
          {

          }
      });
      //Displaying the contents of the stage
      stage.show();
   }
}