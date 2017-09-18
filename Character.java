/*
SE Project: SPORK
Authors: Kevin Kauffman, Glenn Sweithelm
Character - Creates a singleton character and handles the stats for the player's character created
Change Log
////////////////////////////////////////////////////////////////////////////////
Date       Contributer    Change
17Sept17    Glenn         First draft completed and ready to be verified
*/
package sporkcreatecharacter;

import static javafx.scene.text.Font.font;
import javafx.geometry.Insets;
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

public class Character {
    private static Character player = new Character();              //Instantiates Singleton
    static GridPane createChar = new GridPane();                    //All this stuff is the UI elements
    static Label lUsername = new Label("NAME");
    static Label lClass = new Label("CLASS");
    static Label lGender = new Label ("GENDER");
    static Scene scene = new Scene(createChar, 1500, 680);
    static TextField tfUsername = new TextField();
    static TextArea taStats = new TextArea();
    static RadioButton rbWarrior = new RadioButton("Warrior");
    static RadioButton rbMage = new RadioButton("Mage");
    static RadioButton rbArcher = new RadioButton("Archer");
    static RadioButton rbRogue = new RadioButton("Rogue");
    static RadioButton rbMale = new RadioButton("Male");
    static RadioButton rbFemale = new RadioButton("Female");
    static Button btCreate = new Button("Start New Game");
    
    //Player's actual stats. Seperated from the interface items
    private static int hp;
    private static int defense;
    private static int stamina;
    private static int attack;
    private static int magicAttack;
    private static String username;
    private static String gender;
    
    //constructor for the Singleton. The stats will vary, so begins with nothing
    private Character(){
        hp = 0;
        defense = 0;
        username = "";
    }
    
    //singleton function to get instance
    public static Character getInstance(){
        return player;
    }
    
    //sets the UI up for Creating character and game
    public static void setSceneCharacter(Stage stage){
        setStyles();
        stage.setScene(scene);
        createChar.add(lUsername, 0, 0);
        createChar.add(tfUsername, 1, 0, 4, 1);
        createChar.add(lGender, 0, 1);
        createChar.add(rbMale, 1, 1);
        createChar.add(rbFemale, 2, 1, 2, 1);
        createChar.add(lClass, 0, 2);
        createChar.add(rbWarrior, 1, 2);
        createChar.add(rbMage, 2, 2);
        createChar.add(rbRogue, 3, 2);
        createChar.add(rbArcher, 4, 2);
        createChar.add(taStats, 1, 3, 4, 1);
        createChar.add(btCreate, 0, 4, 2, 1);
        
        
        //This makes radio buttons interactable when clicked, though arrow keys still need to be programmed.
        //  It's a start for the place holders
        
        rbWarrior.setOnAction(e -> {
            taStats.setText("Warrior \nStrong and hardy, but lacking in specialty skills \n\nHealth: 10 \nStamina: 10 \n"
                + "Attack Power: 5 \nMagic Power: 1");
        });
        
        rbMage.setOnAction(e ->{
            taStats.setText("Mage \nIntelligent and powerful, bending the elements within their hands \n\nHealth: 6 \n"
                    + "Stamina: 8 \nAttackPower: 2 \nMagic Power: 8");
        });
        
        rbRogue.setOnAction(e -> {
            taStats.setText("Rogue \nSwift and nimble, able to dodge attacks and sneak by enemies \n\nHealth: 8\n"
                    + "Stamina: 12 \nAttack Power: 3 \nMagic Power: 2");
        });
        
        rbArcher.setOnAction(e -> {
            taStats.setText("Archer \nAgile, using ranged to their advantage, though not in the magical sense like the mage \n\n"
                    + "Health: 8 \nStamina: 10 \nAttack Power: 4 \nMagic Power: 3");
        });
        
        //sets all the character values when button is clicked, but only if a name is entered
        //" " works as a name, so may need to find how to solve that
        
        btCreate.setOnAction(e -> {
            if(tfUsername.getText().length() < 1){
                errorMessage();
            }
            else{
                setUsername(tfUsername.getText());
                setDefense(1);
                
                if(rbMale.isSelected()){
                    setGender("Male");
                }
                else{
                    setGender("Female");
                }
                
                if(rbWarrior.isSelected()){
                    setHp(10);
                    setStamina(10);
                    setAttack(5);
                    setMagicAttack(1);
                }
                else if(rbMage.isSelected()){
                    setHp(6);
                    setStamina(8);
                    setAttack(2);
                    setMagicAttack(8);
                }
                else if(rbRogue.isSelected())
                {
                    setHp(8);
                    setStamina(12);
                    setAttack(3);
                    setMagicAttack(2);
                }
                else{
                    setHp(8);
                    setStamina(10);
                    setAttack(4);
                    setMagicAttack(3);
                }
            }
        });
    }
    
    //I am just about positive there is the ability to apply CSS stylesheets to elements as you would html,
    //  though I have never been succesful in getting it figured out. As such, this could be done so much better
    //  once it is figured out.
    
    public static void setStyles(){
        createChar.setPadding(new Insets(40, 40, 100, 100));      //Page Margins (top, bottom, right, left)
        createChar.setVgap(50);
        createChar.setHgap(10);
        lUsername.setFont(font("Imprint MT Shadow", 20));
        tfUsername.setFont(font("Imprint MT Shadow", 16));
        lClass.setFont(font("Imprint MT Shadow", 20));
        lGender.setFont(font("Imprint MT Shadow", 20));
        rbMale.setFont(font("Imprint MT Shadow", 16));
        rbFemale.setFont(font("Imprint MT Shadow", 16));
        rbWarrior.setFont(font("Imprint MT Shadow", 16));
        rbMage.setFont(font("Imprint MT Shadow", 16));
        rbRogue.setFont(font("Imprint MT Shadow", 16));
        rbArcher.setFont(font("Imprint MT Shadow", 16));
        btCreate.setFont(font("Imprint MT Shadow", 16));
        taStats.setEditable(false);                             //This will display info, so it shouldn't be editable
        taStats.setPrefHeight(300);
        taStats.setFont(font("Imprint MT Shadow", 20));
        taStats.setWrapText(true);
        taStats.setText("Warrior \nStrong and hardy, but lacking in specialty skills \n\nHealth: 10 \nStamina: 10 \n"
                + "Attack Power: 5 \nMagic Power: 1");          //These are place holders until we talk about fighting
                                                                //Since warrior is default, it can start with this text
        
        ToggleGroup classGroup = new ToggleGroup();             //So only one class/gender can be selected
        rbWarrior.setToggleGroup(classGroup);
        rbMage.setToggleGroup(classGroup);
        rbRogue.setToggleGroup(classGroup);
        rbArcher.setToggleGroup(classGroup);
        rbWarrior.setSelected(true);                            //Default selected
        
        ToggleGroup genderGroup = new ToggleGroup();
        rbMale.setToggleGroup(genderGroup);
        rbFemale.setToggleGroup(genderGroup);
        rbMale.setSelected(true);
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
    
    //all getters and setters
    static int getHp(){
        return hp;
    }
    
    static int getStamina(){
        return stamina;
    }
    
    static int getDefense(){
        return defense;
    }
    
    static int getAttack(){
        return attack;
    }
    
    static int getMagicAttack(){
        return magicAttack;
    }
    
    static String getName(){
        return username;
    }
    
    static String getGender(){
        return gender;
    }
    
    static void setHp(int number){
        hp = number;
    }
    
    static void setStamina(int number){
        stamina = number;
    }
    
    static void setDefense(int number){
        defense = number;
    }
    
    static void setAttack(int number){
        attack = number;
    }
    
    static void setMagicAttack(int number){
        magicAttack = number;
    }
    
    static void setUsername(String name){
        username = name;
    }
    
    static void setGender(String _gender){
        gender = _gender;
    }
}
