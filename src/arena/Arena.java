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
 * 31Mar18    Glenn          Added Items to Map
 * 03Apr18    Kevin          Added doors & ability to move between arenas & moved
 *                              game handler to its own class
*/

package arena;

import Items.Item;
import actors.Actor;
import actors.Player;
import arena.room.NoRoom;
import gameHandler.GameHandler;
import input.Input;
import java.awt.Toolkit;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import obstacle.Obstacle;

public abstract class Arena {
    protected static ArrayList<Obstacle> obsList = new ArrayList(5);
    protected static ArrayList<Actor> monsList = new ArrayList(5);
    protected static ArrayList<Item> itemList = new ArrayList(5);
    protected static Arena doorList[] = {NoRoom.getInstance(), NoRoom.getInstance(),
                                         NoRoom.getInstance(), NoRoom.getInstance()};
    protected static Image doorZeroImg = new Image("/images/doorSpriteNorth.png",
                             Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.11,
                             Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.11,
                             true, false);
    protected static ImageView doorZero = new ImageView(doorZeroImg);
    protected static Image doorOneImg = new Image("/images/doorSpriteEast.png",
                             Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.183,
                             Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.183,
                             true, false);
    protected static ImageView doorOne = new ImageView(doorOneImg);
    protected static Image doorTwoImg = new Image("/images/doorSpriteSouth.png",
                             Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.11,
                             Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.11,
                             true, false);
    protected static ImageView doorTwo = new ImageView(doorTwoImg);
    protected static Image doorThreeImg = new Image("/images/doorSpriteWest.png",
                             Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.183,
                             Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.183,
                             true, false);
    protected static ImageView doorThree = new ImageView(doorThreeImg);
    protected static Player player = Player.getInstance();
    protected static Input input = new Input();
    protected static ProgressBar healthBar;
    protected static ProgressBar xpBar;
    protected static Stage currStage;
    protected static Scene currScene;
    protected static Pane root;
    protected static Media mp3MusicFile;
    protected static MediaPlayer musicPlayer;
    
    public void checkPlayerCollisions(){
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
   
    public void checkMonsterCollisions(){
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
        
        if(!itemList.isEmpty()){
            itemList.forEach((item) -> {
                item.setLayer(pane);
                item.updateUI();
            });
        }
        
        player.setLayer(pane);
    }
   
    public void checkDeaths(){
        if(!monsList.isEmpty()){
            monsList.forEach((monster) -> {
                if(monster.isDead()){
                    monster.removeFromLayer();
                    monsList.remove(monster);
                }
            });
        }
    }
    
    public void checkMoved(){
        if (player.hasMoved()){
            if(!monsList.isEmpty()){
                monsList.forEach((monster) -> {
                    monster.startMovement();
                });
            }
        }
    }
    
    public void checkItemCollisions(){
        if(!itemList.isEmpty()){
            itemList.forEach((item) -> {
                if(!player.getInventory().contains(item)){
                    if(player.getImageView().getBoundsInParent().intersects(item.getImageView().getBoundsInParent())){
                        player.addItem(item);
                        itemList.remove(item);
                    } // else do nothing
                }
            });
        }
    }
    
    public boolean isNull(){
        return false;
    }
    
    protected void addDoors(){
        if(!doorList[0].isNull()){
            doorZero.setX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.45);
            doorZero.setY(0.0);
            root.getChildren().add(doorZero);
        } // else do nothing
        if(!doorList[1].isNull()){
            doorOne.setX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() - doorOneImg.getWidth());
            doorOne.setY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.4);
            root.getChildren().add(doorOne);
        } // else do nothing
        if(!doorList[2].isNull()){
            doorTwo.setX(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.45);
            doorTwo.setY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - doorTwoImg.getHeight());
            root.getChildren().add(doorTwo);
        } // else do nothing
        if(!doorList[3].isNull()){
            doorThree.setX(0.0);
            doorThree.setY(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.4);
            root.getChildren().add(doorThree);
        } // else do nothing
    }
    
    public void checkDoorCollisions(){
        if(!doorList[0].isNull()){
            if(doorZero.getBoundsInParent().intersects(player.getImageView().getBoundsInParent())
               && (player.checkDir() == Actor.Direction.N || player.checkDir() == Actor.Direction.NW
                   || player.checkDir() == Actor.Direction.NE)){
                GameHandler.stopGame();
                GameHandler.setArena(doorList[0]);
                doorList[0].start(currStage, currScene);
                player.changeArena(Actor.Direction.N);
            }
        } // else do nothing
        if(!doorList[1].isNull()){
            if(doorOne.getBoundsInParent().intersects(player.getImageView().getBoundsInParent())
               && (player.checkDir() == Actor.Direction.E || player.checkDir() == Actor.Direction.NE
                   || player.checkDir() == Actor.Direction.SE)){
                GameHandler.stopGame();
                GameHandler.setArena(doorList[1]);
                doorList[1].start(currStage, currScene);
                player.changeArena(Actor.Direction.E);
            }
        } // else do nothing
        if(!doorList[2].isNull()){
            if(doorTwo.getBoundsInParent().intersects(player.getImageView().getBoundsInParent())
               && (player.checkDir() == Actor.Direction.S || player.checkDir() == Actor.Direction.SW
                   || player.checkDir() == Actor.Direction.SE)){
                GameHandler.stopGame();
                GameHandler.setArena(doorList[2]);
                doorList[2].start(currStage, currScene);
                player.changeArena(Actor.Direction.S);
            }
        } // else do nothing
        if(!doorList[3].isNull()){
            if(doorThree.getBoundsInParent().intersects(player.getImageView().getBoundsInParent())
               && (player.checkDir() == Actor.Direction.W || player.checkDir() == Actor.Direction.NW
                   || player.checkDir() == Actor.Direction.SW)){
                GameHandler.stopGame();
                GameHandler.setArena(doorList[3]);
                player.changeArena(Actor.Direction.W);
                doorList[3].start(currStage, currScene);
            }
        } // else do nothing
    }
    
    public ArrayList<Actor> getMonsList(){
        return monsList;
    }
    
    public Pane getPane(){
        return root;
    }
    
    public Stage getStage(){
        return currStage;
    }
    
    protected abstract void init();
    public abstract void start(Stage stage, Scene scene);
    
    // TODO: Figure out a good way to create "abstract" methods & ensure programmer has what is needed when creating new arenas.
}
