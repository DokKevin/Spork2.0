package character;

import java.awt.Toolkit;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Glenn
 */
public class Animation_Demo {
    boolean pressed = false;
    
   public static void start(Stage stage) {
      //Drawing a Circle
      Circle circle = new Circle();
      Rectangle rect = new Rectangle();

      //Setting the position of the circle
      circle.setCenterX(150.0f);
      circle.setCenterY(135.0f);

      //Setting the radius of the circle
      circle.setRadius(20.0f);

      //Setting the color of the circle
      circle.setFill(Color.BROWN);

      //Setting the stroke width of the circle
      circle.setStrokeWidth(20);



      //Creating a Group object
      Group root = new Group(circle);

      //Creating a scene object
      Scene scene = new Scene(root, 600, 300);

      //Setting title to the Stage
      stage.setTitle("Translate transition example");

      //Adding scene to the stage
      stage.setScene(scene);

      scene.setOnKeyPressed(e -> {
          if (e.getCode() == KeyCode.LEFT)
          {
            if(circle.getCenterX() - circle.getRadius() > 40)
            {
                circle.setCenterX(circle.getCenterX() - 4.0);
            }
          }
          else if (e.getCode() == KeyCode.RIGHT)
          {
            if(circle.getCenterX() + circle.getRadius() < Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 40)
            {
                circle.setCenterX(circle.getCenterX() + 4.0);
            }
          }
          else if (e.getCode() == KeyCode.UP)
          {
              if(circle.getCenterY() - circle.getRadius() > 40)
              {
                circle.setCenterY(circle.getCenterY() - 4.0);
              }
          }
          else if (e.getCode() == KeyCode.DOWN)
          {
              if(circle.getCenterY() + circle.getRadius() < Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 100)
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
