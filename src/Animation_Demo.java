/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class Animation_Demo extends Application {
    boolean pressed = false;
    @Override
   public void start(Stage stage) {
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
            if(circle.getCenterX() - circle.getRadius() != 0)
            {
                circle.setCenterX(circle.getCenterX() - 1.0);
            }
          }
          else if (e.getCode() == KeyCode.RIGHT)
          {
            if(circle.getCenterX() + circle.getRadius() != 600)
            {
                circle.setCenterX(circle.getCenterX() + 1.0);
            }
          }
          else if (e.getCode() == KeyCode.UP)
          {
              if(circle.getCenterY() - circle.getRadius() != 0)
              {
                circle.setCenterY(circle.getCenterY() - 1);
              }
          }
          else if (e.getCode() == KeyCode.DOWN)
          {
              if(circle.getCenterY() + circle.getRadius() != 300)
              {
                circle.setCenterY(circle.getCenterY() + 1);
              }
          }
          else if(e.getCode() == KeyCode.SPACE)
          {

          }
      });
      //Displaying the contents of the stage
      stage.show();
   }
   public static void main(String args[]){
      launch(args);
   }
}
