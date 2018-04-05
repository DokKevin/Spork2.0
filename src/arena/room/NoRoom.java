/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * NoRoom - Null Object for Rooms - handles no room for moving through maps
 * player's character created
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 03Apr18    Kevin          Initial No Room Created
*/

package arena.room;

import arena.Arena;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NoRoom extends Arena {
    private static NoRoom room = new NoRoom();
    
    private NoRoom(){}
    
    public static NoRoom getInstance(){
        return room;
    }
    
    @Override
    public boolean isNull(){
        return true;
    }
    
    @Override
    protected void init(){
        // Do Nothing
    }
    
    @Override
    public void start(Stage stage, Scene scene){
        // Do Nothing
    }
}
