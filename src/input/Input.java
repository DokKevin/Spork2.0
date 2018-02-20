/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * Input - Handles input from the user to move the character.
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 18Feb18    Kevin          Initial Input Handler created
*/

package input;

import java.util.BitSet;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Input {
    // Bitset that registers whether a KeyCode keeps being pressed or is released.
    private BitSet keyboardInp = new BitSet();
    
    // Default key codes - may vary if we allow remapping of keys later
    // Add more here & when evaluating bitset when/if they are used in the game
    private KeyCode upPress = KeyCode.UP;
    private KeyCode downPress = KeyCode.DOWN;
    private KeyCode leftPress = KeyCode.LEFT;
    private KeyCode rightPress = KeyCode.RIGHT;
    
    Scene scene;
    
    public Input(Scene nScene){
        scene = nScene;
    }
    
    public void addListeners(){
        scene.addEventFilter(KeyEvent.KEY_PRESSED, pressedEventHandler);
        scene.addEventFilter(KeyEvent.KEY_RELEASED, releasedEventHandler);
    }
    
    public void removeListeners(){
        scene.removeEventFilter(KeyEvent.KEY_PRESSED, pressedEventHandler);
        scene.removeEventFilter(KeyEvent.KEY_RELEASED, releasedEventHandler);
    }
    
    // Key Pressed event handler - registers pressed keys in the bitset
    private EventHandler<KeyEvent> pressedEventHandler = new EventHandler<KeyEvent>(){
        @Override
        public void handle(KeyEvent event){
            keyboardInp.set(event.getCode().ordinal(), true);
        }
    };
    
    // Key Pressed event handler - registers pressed keys in the bitset
    private EventHandler<KeyEvent> releasedEventHandler = new EventHandler<KeyEvent>(){
        @Override
        public void handle(KeyEvent event){
            keyboardInp.set(event.getCode().ordinal(), false);
        }
    };
    
    // Evaluates bitset of pressed keys & return the player input
    // Does not allow opposite directions to be pressed at once.
    public boolean isMoveUp() {
        return keyboardInp.get(upPress.ordinal()) && !keyboardInp.get(downPress.ordinal());
    }

    public boolean isMoveDown() {
        return keyboardInp.get(downPress.ordinal()) && !keyboardInp.get(upPress.ordinal());
    }

    public boolean isMoveLeft() {
        return keyboardInp.get(leftPress.ordinal()) && !keyboardInp.get(rightPress.ordinal());  
    }

    public boolean isMoveRight() {
        return keyboardInp.get(rightPress.ordinal()) && !keyboardInp.get(leftPress.ordinal());
    }

}
