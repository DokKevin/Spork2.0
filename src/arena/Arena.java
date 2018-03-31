/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * Arena - Abstract Arena Class, handles general functionality of the arenas
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 29Mar18    Kevin          Initial Arena Superclass Created - Superclass for
 *                              arena classes
 * 31Mar18    Kevin          Fixed Healthbar Issue
*/

package arena;

import actors.Actor;
import actors.Player;
import input.Input;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import menus.GameOver;
import obstacle.Obstacle;

public abstract class Arena {
    protected static ArrayList<Obstacle> obsList = new ArrayList(5);
    protected static ArrayList<Actor> monsList = new ArrayList(5);
    protected static Player player = Player.getInstance();
    protected static Input input = new Input();
    protected static ProgressBar healthBar;
    protected static ProgressBar xpBar;
    protected static Stage currStage;
    protected static Scene currScene;
    protected static Pane root;
    
    protected static AnimationTimer gameLoop = new AnimationTimer(){
        @Override
        public void handle(long now){
            checkMoved();
            
            player.processInput();

            player.move();

            monsList.forEach((monster) -> {
                monster.move();
            });

            checkMonsterCollisions();
            checkPlayerCollisions();

            player.updateUI();

            monsList.forEach((monster) -> {
                monster.updateUI();
            });

            //TODO: Check Attack Collisions - this may take some work

            checkDeaths();
              
            if(player.isDead()){
                // Activate Game Over, may change in future development
                stop();
                GameOver.setStage(root, currStage);
            }
              
            // TODO: Move to Player class after death logic is in
//              updateXpBar(player.getExp()/player.getMaxExp());
        }
    };
    
    private static void checkPlayerCollisions(){
        if(!obsList.isEmpty()){
            obsList.forEach((obstacle) -> {
                player.checkObsCollision(obstacle);
            });
        }
        
        if(!monsList.isEmpty()){
            monsList.forEach((monster) -> {
                player.checkActorCollision(monster);
            });
        }
    }
   
    private static void checkMonsterCollisions(){
        if(!monsList.isEmpty()){
            monsList.forEach((monster) -> {
                monster.checkActorCollision(player);
            });

            monsList.forEach((monster) -> {
                obsList.forEach((obstacle) -> {
                     monster.checkObsCollision(obstacle);
                });
            });

            monsList.forEach((monster) -> {
                monsList.forEach((monster2) -> {
                     monster.checkActorCollision(monster2);
                });
            });
        }
    }
    
    public static void setObjects(Pane pane){
        pane.getChildren().clear();
        pane.getChildren().add(healthBar);
        pane.getChildren().add(xpBar);
        
        if(!obsList.isEmpty()){
            obsList.forEach((obstacle) -> {
                obstacle.setLayer(pane);
                obstacle.updateUI();
            });
        }
        
        if(!monsList.isEmpty()){
            monsList.forEach((monster) -> {
                monster.setLayer(pane);
                monster.updateUI();
            });
        }
        
        player.setLayer(pane);
    }
   
    private static void checkDeaths(){
        if(!monsList.isEmpty()){
            monsList.forEach((monster) -> {
                if(monster.isDead()){
                    monster.removeFromLayer();
                    monsList.remove(monster);
                }
            });
        }
    }
    
    private static void checkMoved(){
        if (player.hasMoved()){
            if(!monsList.isEmpty()){
                monsList.forEach((monster) -> {
                    monster.startMovement();
                });
            }
        }
    }
    
    // TODO: Figure out a good way to create "abstract" methods & ensure programmer has what is needed when creating new arenas.
}
