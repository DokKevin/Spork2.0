/*
SE Project: SPORK
Authors: Kevin Kauffman, Glenn Sweithelm
Errors - Handles all of the Error messages that could be displayed throughout Spork
Change Log
////////////////////////////////////////////////////////////////////////////////
Date       Contributer    Change
24Sept17    Glenn         First draft completed and ready to be verified
*/
package sporkcreatecharacter; //this is auto-generated, so it'll have to be replaced with correct thing

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import static javafx.scene.text.Font.font; //so I got rid of the static and it ran an error, it must be needed
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Errors {
    
    public static void UsernameErrorMessage(){
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
}
