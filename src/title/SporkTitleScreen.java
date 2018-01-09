/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * Character - Creates a singleton character and handles the stats for the
 * player's character created
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 17Sept17   Glenn          First draft completed and ready to be verified
*/

package title;

import character.Character; // This will need changed once character class is removed from character ui

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import static javafx.scene.text.Font.font;
import javafx.stage.Stage;

public class SporkTitleScreen extends Application {
   public static void main(String[] args) {
      launch(args);
   }

    GridPane root = new GridPane();
    Scene scene = new Scene(root, 1440, 810);
    Label sporkTitle = new Label("SPORK");
    Button newGame = new Button("New Game");
    Button loadGame = new Button("Load Game");
    Button options = new Button("Options");

    @Override
    public void start(Stage primaryStage) {
        setStyles();
        root.add(sporkTitle, 0, 0, 2, 1);
        root.add(new Label("\t\t\t\t\t"), 0, 1);           //Adds space for the buttons to be centered.
        root.add(new Label("\t\t\t\t\t"), 0, 2);           //Problem is, this way to do it sucks. Gotta get that CSS figured out.
        root.add(new Label("\t\t\t\t\t"), 0, 3);
        root.add(newGame, 1, 1);
        root.add(loadGame, 1, 2);
        root.add(options, 1, 3);

        primaryStage.setTitle("Spork");
        primaryStage.setScene(scene);
        primaryStage.show();

        newGame.setOnAction(e -> {
            Character.setSceneCharacter(primaryStage); //This will run the GUI-layer method
        });

        loadGame.setOnAction(e -> {
            //This will initiate Loading a save
        });

        options.setOnAction(e -> {
            //Probably just pop up a menu to choose some basic stuff like difficulty, which then could
            //  be deserialized (yay! :) ) on load-up.
        });
    }


    //I am just about positive there is ways to get stylesheets with .CSS properties instead
    //    of styling the stage this really long, code-heavy way, so this could very well probably
    //    be able to be done better later. I just never got CSS to work in javaFX.

    //Also, there is limited styles you can do this way, so there has to be a CSS way to make things look better.

    public void setStyles(){
        root.setAlignment(Pos.TOP_CENTER);
        root.setVgap(20);
        sporkTitle.setFont(font("Imprint MT Shadow", 130));
        newGame.setPrefSize(200, 65);
        newGame.setFont(font("Imprint MT Shadow", 25));
        loadGame.setPrefSize(200, 65);
        loadGame.setFont(font("Imprint MT Shadow", 25));
        options.setPrefSize(200, 65);
        options.setFont(font("Imprint MT Shadow", 25));
    }
}
