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
    boolean pressed = false;
    
   public static void start(Stage stage) {
      //Drawing a Circle
      Circle circle = new Circle();

      //Setting the position of the circle
      circle.setCenterX(350.0f);
      circle.setCenterY(300.0f);

      //Setting the radius of the circle
      circle.setRadius(20.0f);

      //Setting the color of the circle
      circle.setFill(Color.BROWN);

      //Setting the stroke width of the circle
      circle.setStrokeWidth(20);



      //Creating a Group object
      Pane root = new Pane(circle);

      //Creating a scene object
      Scene scene = new Scene(root, 600, 300);

      //Setting title to the Stage
      stage.setTitle("Spork");

      //Adding scene to the stage
      stage.setScene(scene);
      
      scene.getStylesheets().add(ArenaOne.class.getResource("ArenaOne.css").toExternalForm());
      root.getStyleClass().add("arena");

      scene.setOnKeyPressed(e -> {
          //TODO look into replacing if statement with SWITCH statement
          if (e.getCode() == KeyCode.LEFT)
          {
            if(circle.getCenterX() - circle.getRadius() > 285)
            {
                circle.setCenterX(circle.getCenterX() - 4.0);
            }
          }
          else if (e.getCode() == KeyCode.RIGHT)
          {
            if(circle.getCenterX() + circle.getRadius() < Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 285)
            {
                circle.setCenterX(circle.getCenterX() + 4.0);
            }
          }
          else if (e.getCode() == KeyCode.UP)
          {
              if(circle.getCenterY() - circle.getRadius() > 170)
              {
                circle.setCenterY(circle.getCenterY() - 4.0);
              }
          }
          else if (e.getCode() == KeyCode.DOWN)
          {
              if(circle.getCenterY() + circle.getRadius() < Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 175)
              {
                circle.setCenterY(circle.getCenterY() + 4.0);
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
