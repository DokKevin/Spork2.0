/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * RunStage - Allows developers to run a stage without having to go through the
 *            start menu
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 30Jan18    Kevin          Initial Release
 * 18Feb18    Kevin          Changed to work with CharacterGUI
 * 24Feb18    Kevin          Changed to work with ArenaOne
 * 29Mar18    Kevin          Minor Updates - Updated imports
*/

package runstage;

import arena.LevelOneRoomOne;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RunStage extends Application {
   public static void main(String[] args) {
      launch(args);
   }

    GridPane root = new GridPane();
    Scene scene = new Scene(root, 1440, 700);

    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("Spork");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();

        LevelOneRoomOne.start(primaryStage, scene); //This will run the GUI-layer method
    }
}
