/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * Obstacle - Main Abstract Obstacle Class - handles functionality for Obstacles
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 19Feb18    Kevin          Initial Abstract Obstacle Created
 * 24Feb18    Kevin          Added GetImageView function
*/

package obstacle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Obstacle {
    Image obsImg;
    ImageView imageView;
    
    Pane layer;
    
    protected double x;
    protected double y;
    protected double left;
    protected double right;
    protected double top;
    protected double bottom;
    
    public Obstacle(){}
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public double getLeft(){
        return left;
    }
    
    public double getRight(){
        return right;
    }
    
    public double getTop(){
        return top;
    }
    
    public double getBottom(){
        return bottom;
    }
    
    public double getImgWidth(){
        return obsImg.getWidth();
    }
    
    public double getImgHeight(){
        return obsImg.getHeight();
    }
    
    public ImageView getImageView(){
        return imageView;
    }
    
    public void setLayer(Pane nLayer){
        layer = nLayer;
        addToLayer();
    }
    
    public void updateUI() {
        imageView.setX(x);
        imageView.setY(y);
    }
    
    public void addToLayer() {
        layer.getChildren().add(imageView);
    }
    
    public void removeFromLayer() {
        layer.getChildren().remove(imageView);
    }
}
