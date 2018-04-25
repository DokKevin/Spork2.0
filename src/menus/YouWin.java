/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * YouWin - Handles GUI for winning the game
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 25Apr18    Kevin          Initial Win Menu Created
*/
package menus;

import java.awt.Toolkit;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class YouWin {
    static Label goLabel = new Label("You Win!");
    static Button MainMenu = new Button("Main Menu");
    static Button Exit = new Button("Exit Game");
    
    
    public static void setStage(Pane pane, Stage stage){
        pane.getChildren().clear();
        pane.getChildren().add(goLabel);
        pane.getChildren().add(MainMenu);
        pane.getChildren().add(Exit);
        setPositions();
        
        // Needs Logic for Main Menu
        
        Exit.setOnAction(e -> {
            stage.close();
        });
        
        MainMenu.setOnAction(e -> {
            StaticTitle.startTitle(stage);
        });
    }
    
    public static void setPositions(){
        goLabel.setPrefSize(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.5, 
                   Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.1);
        goLabel.setTranslateX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.33);
        goLabel.setTranslateY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.25);
        goLabel.setStyle("-fx-font-size: 200;");
        
        MainMenu.setPrefSize(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.3, 
                   Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.1);
        MainMenu.setTranslateX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.35);
        MainMenu.setTranslateY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.55);
        
        Exit.setPrefSize(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.3, 
                   Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.1);
        Exit.setTranslateX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.35);
        Exit.setTranslateY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.75);
    }
}