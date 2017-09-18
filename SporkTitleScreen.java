/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sporktitlescreen;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import static javafx.scene.text.Font.font;
import javafx.stage.Stage;

/**
 *
 * @author Glenn
 */
public class SporkTitleScreen extends Application {
    GridPane root = new GridPane();
    Scene scene = new Scene(root, 1500, 680);
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
            //This will initiate Creating the Character
        });
        
        loadGame.setOnAction(e -> {
            //This will initiate Loading a save
        });
        
        options.setOnAction(e -> {
            //Probably just pop up a menu to choose some basic stuff like difficulty, which then could
            //  be deserialized (yay! :) ) on load-up.
        });
    }

    public static void main(String[] args) {
        launch(args);
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
