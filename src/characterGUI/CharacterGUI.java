/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * Character - Creates a singleton character and handles the stats for the
 * player's character created
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 17Sept17   Glenn          First draft completed and ready to be verified
 * 21Sept17   Kevin          Premilinary check for username meeting spec
 *                           Don't allow users to enter a space as the first
 *                           letter of username.
 * 16Jan18    Kevin          Updated Labels
 * 28Jan18    Kevin          Fixed Username Checking Flaw
 * 30Jan18    Kevin          Made Stage FullScreen
 * 06Feb18    Glenn          Made Dynamic Fields for resolution
 * 18Feb18    Kevin          Made username checking more user friendly
*/

package characterGUI;

import actors.Player;
import arena.ArenaOne;
import java.awt.Toolkit;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CharacterGUI {

    //All this stuff is the UI elements
    static GridPane createChar = new GridPane();
    static Label lUsername = new Label("USERNAME");
    static Label lClass = new Label("CLASS");
    //static Scene scene = new Scene(createChar, 1, 1);
    static TextField tfUsername = new TextField();
    static TextArea taStats = new TextArea();
    static RadioButton rbWarrior = new RadioButton("Chef");
    static Button btCreate = new Button("Start New Game");
    static Button btBack = new Button("Back");

    //sets the UI up for Creating character and game
    public static void setSceneCharacter(Stage stage, Scene scene, GridPane pane){
        stage.setFullScreenExitHint(null);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        setStyles();
        scene.setRoot(createChar);
        stage.setScene(scene);
        stage.setFullScreen(true);
        scene.getStylesheets().add(CharacterGUI.class.getResource("CharacterGUI.css").toExternalForm());
        createChar.getStyleClass().add("createChar");
        createChar.add(lUsername, 0, 0);
        createChar.add(tfUsername, 1, 0, 4, 1);
        createChar.add(lClass, 0, 2);
        createChar.add(rbWarrior, 1, 2);
        createChar.add(taStats, 1, 3, 4, 1);
        createChar.add(btCreate, 0, 4, 2, 1);
        createChar.add(btBack, 2, 4);
        
        btCreate.setPrefWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.20);
        btBack.setPrefWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.20);
        tfUsername.setPrefWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.6);
        
        // Prevents username from having a space at the beginning or being greater than 10 characters long
        // Still allows players to have a space at the end of their name, that would have to be taken care of when pressing submit if we want to allow spaces at all.
        tfUsername.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.charAt(0) == ' '){
                    tfUsername.setText(oldValue);
                }
                
                if(newValue.length() > 10){
                    tfUsername.setText(oldValue);
                }
            }
        });
        
        //This makes radio buttons interactable when clicked, though arrow keys still need to be programmed.
        //  It's a start for the place holders
        rbWarrior.setOnAction(e -> {
            taStats.setText("Warrior \nStrong and hardy, but lacking in specialty skills \n\nHealth: 10 \nStamina: 10 \n"
                + "Attack Power: 5 \nMagic Power: 1");
        });
        
        //sets all the character values when button is clicked, but only if a name is entered
        btCreate.setOnAction(e -> {
            if(!usernameIsGood()){
                errorMessage(scene, createChar);
            }
            else{
                Player initChar = Player.getInstance();
                
                initChar.setUsername(tfUsername.getText());
                initChar.setDefense(1);

                if(rbWarrior.isSelected()){
                    initChar.setHp(10);
                    initChar.setAttack(5);
                }
                else{
                    initChar.setHp(8);
                    initChar.setAttack(4);
                }
                ArenaOne.start(stage, scene);
            }
        });
        
        btBack.setOnAction(e -> {
            scene.setRoot(pane);
        });
    }

    //I am just about positive there is the ability to apply CSS stylesheets to elements as you would html,
    //  though I have never been succesful in getting it figured out. As such, this could be done so much better
    //  once it is figured out.
    public static void setStyles(){
        taStats.setEditable(false);                             //This will display info, so it shouldn't be editable
        taStats.setWrapText(true);
        taStats.setText("Chef \nStrong and hardy, but lacking in specialty skills \n\nHealth: 10 \nStamina: 10 \n"
                + "Attack Power: 5 \nMagic Power: 1");          //These are place holders until we talk about fighting
                                                                //Since warrior is default, it can start with this text

        ToggleGroup classGroup = new ToggleGroup();             //So only one class/gender can be selected
        rbWarrior.setToggleGroup(classGroup);
        rbWarrior.setSelected(true);                            //Default selected
    }

    //sets up a new stage that displays a message to enter username
    public static void errorMessage(Scene scene, GridPane pane){
        GridPane errorPane = new GridPane();
        scene.setRoot(errorPane);
        Label tError = new Label("Please Enter a Username");
        Button ok = new Button("Okay");
        ok.setPrefWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.20);
        errorPane.setVgap(10);
        errorPane.setAlignment(Pos.CENTER);
        
        errorPane.add(tError, 0 , 0, 3, 1);
        errorPane.add(ok, 1, 1);

        ok.setOnAction(e -> {
            tfUsername.setText("");
            scene.setRoot(pane);
        });
    }

    // This might be good to put in the username error once we get that
    // encapsulated
    public static boolean usernameIsGood(){
        if(tfUsername.getText().length() < 1 || tfUsername.getText().length() > 10){
            return false;
        } else {
            return true;
        }
    }

    
}
