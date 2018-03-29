/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * Arena - Abstract Arena Class, handles general functionality of the arenas
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 29Mar18    Kevin          Initial Arena Superclass Created - Superclass for
 *                              arena classes
*/

package arena;

import actors.Actor;
import actors.Player;
import input.Input;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import obstacle.Obstacle;

public abstract class Arena {
    protected static ArrayList<Obstacle> obsList = new ArrayList(5);
    protected static ArrayList<Actor> monsList = new ArrayList(5);
    protected static Player player = Player.getInstance();
    protected static Input input = new Input();
    static ProgressBar healthBar = new ProgressBar(1F);
    static ProgressBar xpBar = new ProgressBar(0F);
    
    protected static AnimationTimer gameLoop = new AnimationTimer(){
        @Override
        public void handle(long now){
            // Player Input
            player.processInput();

            player.move();

            monsList.forEach((monster) -> {
                monster.move();
            });

            checkPlayerCollisions();
            checkMonsterCollisions();

            player.updateUI();

            monsList.forEach((monster) -> {
                monster.updateUI();
            });

            //TODO: Check removability

            //TODO: Check Attack Collisions - this may take some work

            //TODO: Remove Dead Enemies

            //Updates / Etc...
        }
    };
    
    private static void checkPlayerCollisions(){
        obsList.forEach((obstacle) -> {
            player.checkObsCollision(obstacle);
        });
       
        monsList.forEach((monster) -> {
            player.checkActorCollision(monster);
        });
    }
   
    private static void checkMonsterCollisions(){
        // Change this to monster type instead of actor type when monster superclass is created
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
    
    public static void setObjects(Pane pane){
        pane.getChildren().clear();
        pane.getChildren().add(healthBar);
        pane.getChildren().add(xpBar);
        
        obsList.forEach((obstacle) -> {
            obstacle.setLayer(pane);
            obstacle.updateUI();
        });
        
        monsList.forEach((monster) -> {
            monster.setLayer(pane);
            monster.updateUI();
        });
        
        player.setLayer(pane);
    }
   
    public static void setHP(double amount){
        healthBar.setProgress(healthBar.getProgress() + amount);
    }
    
    // Will need to be changed based on how we want levels to go
    static double maxXp = 100;
    public static void setXP(double amount){
        xpBar.setProgress(xpBar.getProgress() + amount/maxXp);
        if(xpBar.getProgress() >= 1){
            xpBar.setProgress(0);
            maxXp = maxXp + (maxXp * .5);//player's xp gets higher
        }
    }
    
    // TODO: Figure out a good way to create "abstract" methods & ensure programmer has what is needed when creating new arenas.
}
