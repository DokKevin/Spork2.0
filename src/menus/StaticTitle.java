/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * StaticTitle - Handles GUI for Title Screen in a Static Way
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 25Apr18   Kevin          First StaticTitle Created
 */

package menus;

import java.awt.Toolkit;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class StaticTitle{
    private static GridPane root = new GridPane();
    private static Scene scene = new Scene(root, 1440, 700);
    private static Button newGame = new Button("New Game");
    private static Button loadGame = new Button("Load Game");
    private static Button options = new Button("Options");
    private static Button Exit = new Button("Exit Game");
    private static Image SporkTitleImage = new Image("file:src/menus/SporkLogo-01.png");
    private static ImageView TitleImageView = new ImageView(SporkTitleImage);
    private static Media mp3MusicFile;
    private static MediaPlayer musicPlayer;
   
    public static void startTitle(Stage primaryStage) {
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
        
        mp3MusicFile = new Media(SporkTitleScreen.class.getResource("MainMenu.mp3").toExternalForm());
        musicPlayer = new MediaPlayer(mp3MusicFile);
        musicPlayer.setAutoPlay(true);
        
        primaryStage.show();

        newGame.setOnAction(e -> {
            CharacterGUI.setSceneCharacter(primaryStage, scene, root, musicPlayer); //This will run the GUI-layer method
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
    
    public static void setStyles(){
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
