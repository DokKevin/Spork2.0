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
*/

package character;

import arena.ArenaOne;
import static javafx.scene.text.Font.font;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class CharacterGUI {

    //All this stuff is the UI elements
    static GridPane createChar = new GridPane();
    static Label lUsername = new Label("USERNAME");
    static Label lClass = new Label("CLASS");
    static Scene scene = new Scene(createChar, 1, 1);
    static TextField tfUsername = new TextField();
    static TextArea taStats = new TextArea();
    static RadioButton rbWarrior = new RadioButton("Chef");
    static Button btCreate = new Button("Start New Game");

    //sets the UI up for Creating character and game
    public static void setSceneCharacter(Stage stage){
        setStyles();
        stage.setScene(scene);
        scene.getStylesheets().add(CharacterGUI.class.getResource("CharacterGUI.css").toExternalForm());
        createChar.getStyleClass().add("createChar");
        createChar.add(lUsername, 0, 0);
        createChar.add(tfUsername, 1, 0, 4, 1);
        createChar.add(lClass, 0, 2);
        createChar.add(rbWarrior, 1, 2);
        createChar.add(taStats, 1, 3, 4, 1);
        createChar.add(btCreate, 0, 4, 2, 1);

        // Check to make sure the username's first char is not a space
        tfUsername.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(tfUsername.getText().length() < 1){ // Not sure if this is the correct length I should be checking.
                    if (event.getCode().equals(KeyCode.SPACE)) {
                        tfUsername.setText(""); // This should set the field to blank.
                        errorMessage(); // TO Be Changed to its own error
                    }
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
        //" " works as a name, so may need to find how to solve that
        btCreate.setOnAction(e -> {
            if(!usernameIsGood()){
                errorMessage();
            }
            else{
                Character initChar = Character.getInstance();
                
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
                
                ArenaOne.start(stage);
            }
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
    public static void errorMessage(){
        Stage error = new Stage();
        GridPane errorPane = new GridPane();
        Scene ErrorScene = new Scene(errorPane, 300, 100);
        Text tError = new Text("Please Enter a Username");
        Button ok = new Button("Okay");
        
        error.setResizable(false);
        ok.setPrefSize(100, 30);
        errorPane.setVgap(10);
        errorPane.setAlignment(Pos.CENTER);
        tError.setFont(font("Imprint MT Shadow", 16));
        ok.setFont(font("Imprint MT Shadow", 16));

        errorPane.add(tError, 0 , 0, 3, 1);
        errorPane.add(new Label("\t"), 0, 1);
        errorPane.add(ok, 1, 1);
        error.setTitle("Username");
        error.setScene(ErrorScene);
        error.show();

        ok.setOnAction(e -> {
            error.close();
        });
    }

    // This might be good to put in the username error once we get that
    // encapsulated
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
