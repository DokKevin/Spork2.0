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
 * 06Feb18    Glenn          Made Fields dynamic to resolution
 * 06Mar18    Glenn          Eliminated Jitters of Scene transition
 * 06Mar18    Glenn          Added Exit Button
 * 30Mar18    Kevin          Updated Package
 */

package menus;

import java.awt.Toolkit;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SporkTitleScreen extends Application {
   public static void main(String[] args) {
      launch(args);
   }
    GridPane root = new GridPane();
    Scene scene = new Scene(root, 1440, 700);
    Button newGame = new Button("New Game");
    Button loadGame = new Button("Load Game");
    Button options = new Button("Options");
    Button Exit = new Button("Exit Game");
    Image SporkTitleImage = new Image("file:src/menus/SporkLogo-01.png");
    ImageView TitleImageView = new ImageView(SporkTitleImage);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setFullScreenExitHint(null);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        setStyles();
        root.add(TitleImageView, 0, 0, 3, 1);
        root.add(newGame, 1, 1);
        root.add(loadGame, 1, 2);
        root.add(options, 1, 3);
        root.add(Exit, 1, 4);
        primaryStage.setTitle("Spork");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        scene.getStylesheets().add(SporkTitleScreen.class.getResource("Title.css").toExternalForm());
        primaryStage.show();

        newGame.setOnAction(e -> {
            CharacterGUI.setSceneCharacter(primaryStage, scene, root); //This will run the GUI-layer method
        });

        loadGame.setOnAction(e -> {
            //This will initiate Loading a save
        });

        options.setOnAction(e -> {
            //Probably just pop up a menu to choose some basic stuff like difficulty, which then could
            //  be deserialized (yay! :) ) on load-up.
        });
        
        Exit.setOnAction(e -> {
            primaryStage.close();
        });
    }
    
    public void setStyles(){
        TitleImageView.setFitWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.4);
        TitleImageView.setFitHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.25);
        newGame.setPrefWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.25);
        newGame.setTranslateX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.07);
        loadGame.setPrefWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.25);
        loadGame.setTranslateX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.07);
        options.setPrefWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.25);
        options.setTranslateX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.07);
        Exit.setPrefWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.25);
        Exit.setTranslateX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.07);
    }
}
