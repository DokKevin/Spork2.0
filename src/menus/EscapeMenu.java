/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * Character - Handles GUI for Title Screen and Handles Main Class
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 06Mar18    Glenn          First draft created for the Escape Menu
 * 30Mar18    Kevin          Updated Package
*/
package menus;

import arena.LevelOneRoomOne;
import java.awt.Toolkit;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EscapeMenu {
    static Button Resume = new Button("Resume Game");
    static Button Options = new Button("Options");
    static Button MainMenu = new Button("Main Menu");
    static Button Exit = new Button("Exit Game");
    
    
    public static void setStage(Pane pane, Stage stage){
        pane.getChildren().clear();
        pane.getChildren().add(Resume);
        pane.getChildren().add(Options);
        pane.getChildren().add(MainMenu);
        pane.getChildren().add(Exit);
        setPositions();
        
        Resume.setOnAction(e -> {
            LevelOneRoomOne.setObjects(pane);
        });
        
        // Needs logic for Main Menu and Options
        
        Exit.setOnAction(e -> {
            stage.close();
        });
    }
    
    public static void setPositions(){
        Resume.setPrefSize(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.3, 
                   Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.1);
        Resume.setTranslateX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.35);
        Resume.setTranslateY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.15);
        Options.setPrefSize(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.3, 
                   Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.1);
        Options.setTranslateX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.35);
        Options.setTranslateY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.35);
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
