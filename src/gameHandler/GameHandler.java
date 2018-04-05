/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * GameHandler - Handles the animation timer / repetitive game functions
 * player's character created
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 03Apr18    Kevin          Initial GameHandler Created
*/

package gameHandler;

import actors.Player;
import arena.Arena;
import javafx.animation.AnimationTimer;
import menus.GameOver;

public class GameHandler {
    private static Arena currArena;
    private static Player player = Player.getInstance();
    
    protected static AnimationTimer gameLoop = new AnimationTimer(){
        @Override
        public void handle(long now){
            currArena.checkMoved();
            
            player.processInput();

            player.move();

            currArena.getMonsList().forEach((monster) -> {
                monster.move();
            });

            currArena.checkMonsterCollisions();
            currArena.checkPlayerCollisions();
            currArena.checkItemCollisions();

            player.updateUI();

            currArena.getMonsList().forEach((monster) -> {
                monster.updateUI();
            });

            //TODO: Check Attack Collisions - this may take some work

            currArena.checkDeaths();
              
            if(player.isDead()){
                // Activate Game Over, may change in future development
                stop();
                GameOver.setStage(currArena.getPane(), currArena.getStage());
            }
              
            // TODO: Move to Player class after death logic is in
//              updateXpBar(player.getExp()/player.getMaxExp());

            currArena.checkDoorCollisions();
        }
    };
    
    public static void startGame(){
        gameLoop.start();
    }
    
    public static void stopGame(){
        gameLoop.stop();
    }
    
    public static void setArena(Arena nArena){
        currArena = nArena;
    }
}
