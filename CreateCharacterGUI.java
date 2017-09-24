/*
SE Project: SPORK
Authors: Kevin Kauffman, Glenn Sweithelm
CreateCharacterGUI - Handles all of the front end Interface for the CreateCharacterClass
Change Log
////////////////////////////////////////////////////////////////////////////////
Date       Contributer    Change
24Sept17    Glenn         First draft completed and ready to be verified
*/
package sporkcreatecharacter; //This is always auto-generated and will need switched with what we are using

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import static javafx.scene.text.Font.font;
import javafx.stage.Stage;

public class CreateCharacterGUI {
    static GridPane createChar = new GridPane();
    static Label lUsername = new Label("NAME");
    static Label lClass = new Label("CLASS");
    static Label lGender = new Label ("GENDER");
    static Scene scene = new Scene(createChar, 1440, 810);
    static TextField tfUsername = new TextField();
    static TextArea taStats = new TextArea();
    static RadioButton rbWarrior = new RadioButton("Warrior");
    static Button btCreate = new Button("Start New Game");
    
    public static void setSceneCharacter(Stage stage){
        setStyles();
        stage.setScene(scene);
        createChar.add(lUsername, 0, 0);
        createChar.add(tfUsername, 1, 0, 4, 1);
        createChar.add(lGender, 0, 1);
        createChar.add(lClass, 0, 2);
        createChar.add(rbWarrior, 1, 2);
        createChar.add(taStats, 1, 3, 4, 1);
        createChar.add(btCreate, 0, 4, 2, 1);
        
         rbWarrior.setOnAction(e -> {
             taStats.setText("Warrior \nStrong and hardy, but lacking in specialty skills \n\nHealth: 10 \nStamina: 10 \n"
                 + "Attack Power: 5 \nMagic Power: 1");
         });
        
        btCreate.setOnAction(e -> {
            if(!usernameIsGood()){
                Errors.UsernameErrorMessage();
            }
            else{
                Character.setHp(10);
                Character.setAttack(5);
                Character.setUsername(tfUsername.getText());
            }
        });
        
        // Check to make sure the username's first char is not a space
        tfUsername.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(tfUsername.getText().length() < 1){ // Not sure if this is the correct length I should be checking.
                    if (event.getCode().equals(KeyCode.SPACE)) {
                        tfUsername.setText(""); // This should set the field to blank.
                        Errors.UsernameErrorMessage();
                    }
                }
            }
        });
    }
    
    public static void setStyles(){
        createChar.setPadding(new Insets(40, 40, 100, 100));      //Page Margins (top, bottom, right, left)
        createChar.setVgap(50);
        createChar.setHgap(10);
        lUsername.setFont(font("Imprint MT Shadow", 20));
        tfUsername.setFont(font("Imprint MT Shadow", 16));
        lClass.setFont(font("Imprint MT Shadow", 20));
        lGender.setFont(font("Imprint MT Shadow", 20));
        rbWarrior.setFont(font("Imprint MT Shadow", 16));
        btCreate.setFont(font("Imprint MT Shadow", 16));
        taStats.setEditable(false);                             //This will display info, so it shouldn't be editable
        taStats.setPrefHeight(300);
        taStats.setFont(font("Imprint MT Shadow", 20));
        taStats.setWrapText(true);
        taStats.setText("Warrior \nStrong and hardy, but lacking in specialty skills \n\nHealth: 10 \nStamina: 10 \n"
                + "Attack Power: 5 \nMagic Power: 1");          //These are place holders until we talk about fighting
                                                                //Since warrior is default, it can start with this text
        
        ToggleGroup classGroup = new ToggleGroup();             //So only one class
        rbWarrior.setToggleGroup(classGroup);
        rbWarrior.setSelected(true);                            //Default selected
    }
    
    public static boolean usernameIsGood(){
        if(tfUsername.getText().length() < 1){
            return false;
        } else if(tfUsername.getText().length() > 10){
            return false;
        } else {
            return true;
        }
    }
}
