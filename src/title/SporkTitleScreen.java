/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * Character - Handles GUI for Title Screen and Handles Main Class
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 17Sept17   Glenn          First draft completed and ready to be verified
 * 18Jan18    Kevin          Updated Header
 * 23Jan18    Glenn          Created a design theme for the whole game
 * 30Jan18    Kevin          Made Stage FullScreen
*/

package title;

import character.CharacterGUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SporkTitleScreen extends Application {
   public static void main(String[] args) {
      launch(args);
   }

    GridPane root = new GridPane();
    Scene scene = new Scene(root, 1440, 700);
    Label sporkTitle = new Label("SPORK");
    Button newGame = new Button("New Game");
    Button loadGame = new Button("Load Game");
    Button options = new Button("Options");

    @Override
    public void start(Stage primaryStage) {
        root.add(sporkTitle, 0, 0, 3, 1);
        root.add(newGame, 1, 1);  
        root.add(loadGame, 1, 2);
        root.add(options, 1, 3);
        
        primaryStage.setTitle("Spork");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        scene.getStylesheets().add(SporkTitleScreen.class.getResource("Title.css").toExternalForm());
        primaryStage.show();

        newGame.setOnAction(e -> {
            CharacterGUI.setSceneCharacter(primaryStage); //This will run the GUI-layer method
        });

        loadGame.setOnAction(e -> {
            //This will initiate Loading a save
        });

        options.setOnAction(e -> {
            //Probably just pop up a menu to choose some basic stuff like difficulty, which then could
            //  be deserialized (yay! :) ) on load-up.
        });
    }
}
