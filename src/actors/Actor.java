/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * Actor - Abstract superclass that handles common functionality of actors
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 18Feb18    Kevin          Initial Abstract Actor Created
 * 24Feb18    Kevin          Fixed Collision Checking
*/

package actors;

import java.awt.Toolkit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import obstacle.*;

public abstract class Actor {
    Image actorImg;
    ImageView imageView;
    
    Pane layer;
    
    protected double x;
    protected double y;
    
    protected double left;
    protected double right;
    protected double top;
    protected double bottom;
    
    protected double dx;
    protected double dy;
    
    protected double lx;
    protected double ly;
    
    protected int hp;
    protected int defense;
    protected int attack;
    protected int speed;
    
    protected boolean removable = false;
    
    protected double w;
    protected double h;
    
    protected double minX;
    protected double maxX;
    protected double minY;
    protected double maxY;
    
    boolean canMove = true;
    
    public Actor() {}
    
    public void addToLayer() {
        layer.getChildren().add(imageView);
    }
    
    public void removeFromLayer() {
        layer.getChildren().remove(imageView);
    }
    
    // min and max stats. May change depending on future development.
    // Made public to accomodate testing without using/creating getters.
    public int minHp = 0;
    public int maxHp = 0;
    public int minDef = 0;
    public int maxDef = 0;
    public int minAtt = 0;
    public int maxAtt = 0;
    
    
    // Getters
    public Pane getLayer() {
        return layer;
    }
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    public double getLx() {
        return lx;
    }

    public double getLy() {
        return ly;
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

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public int getHp(){
        return hp;
    }

    public int getDefense(){
        return defense;
    }

    public int getAttack(){
        return attack;
    }
    
    public ImageView getView() {
        return imageView;
    }
    
    public double getWidth() {
        return w;
    }

    public double getHeight() {
        return h;
    }
    
    public double getCenterX() {
        return x + w * 0.5;
    }

    public double getCenterY() {
        return y + h * 0.5;
    }
    
    public Image getImage(){
        return actorImg;
    }
    
    public double getImgHeight(){
        return getImage().getHeight();
    }
    
    public double getImgWidth(){
        return getImage().getWidth();
    }
    
    public ImageView getImageView(){
        return imageView;
    }
    
    public double getMoveBoundMinX(){
        return minX;
    }
    
    public double getMoveBoundMaxX(){
        return maxX;
    }
    
    public double getMoveBoundMinY(){
        return minY;
    }
    
    public double getMoveBoundMaxY(){
        return maxY;
    }
    
    // Setters
    public void setLayer(Pane nLayer) {
        layer = nLayer;
        addToLayer();
    }
    
    public void setX(double nx) {
        x = nx;
    }
    
    public void setY(double ny) {
        y = ny;
    }
    
    public void setDx(double ndx) {
        dx = ndx;
    }
    
    public void setDy(double ndy) {
        dy = ndy;
    }
    
    //To Change Min and Max values, see variables at top of class.
    public void setHp(int number){
        if(number < minHp){
            kill();
        } else if (number > maxHp){
            hp = maxHp;
        } else {
            hp = number;
        }
    }

    //To Change Min and Max values, see variables at top of class.
    public void setDefense(int number){
        if(number < minDef){
            defense = minDef;
        } else if (number > maxDef){
            defense = maxDef;
        } else {
            defense = number;
        }
    }

    //To Change Min and Max values, see variables at top of class.
    public void setAttack(int number){
        if(number < minAtt){
            attack = minAtt;
        } else if (number > maxAtt){
            attack = maxAtt;
        } else {
            attack = number;
        }
    }
    
    public void setRemovable(boolean nRem) {
        removable = nRem;
    }
    
    protected void setMoveBounds(){
        minX = Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.145;
        maxX = Toolkit.getDefaultToolkit().getScreenSize().getWidth() - (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.145);
        minY = Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.16;
        maxY = Toolkit.getDefaultToolkit().getScreenSize().getHeight() - (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.17);
    }
    
    public boolean isRemovable() {
        return removable;
    }
    
    public void move() {
        if(!canMove){
            return;
        }
        
        // Record last x and y position for collision checking
        lx = x;
        ly = y;
        
        x += dx;
        y += dy;
    }
    
    public boolean isAlive() {
        return (hp > 0);
    }
    
    public void updateUI() {
        imageView.setX(x);
        imageView.setY(y);
    }
    
    // Take damage based on monster that's attacking
    public void getDamagedBy( Actor monster) {
        setHp(getHp() - monster.getAttack());
    }

    // Set Health to 0
    public void kill() {
        setHp(minHp);
    }

    // Set flag that this actor is able to be removed from UI
    // Character should never be made removable - override on character
    public void remove() {
        setRemovable(true);
    }

    // Set flag that this actor can't move anymore.
    public void stopMovement() {
        canMove = false;
    }

    public void checkRemovability(){
        if(!isAlive()){
            remove();
        }
    }
    
    protected void checkBounds(){
        // Vertical
        if(Double.compare((getY() + (getImage().getHeight() / 2.0)), getMoveBoundMinY()) < 0){
            setY(getMoveBoundMinY() - (getImage().getHeight() / 2.0)); // Top
        } else if(Double.compare((getY() + getImage().getHeight()), getMoveBoundMaxY()) > 0){
            setY(getMoveBoundMaxY() - getImage().getHeight()); // Bottom
        }
        
        // Horizontal
        if(Double.compare(getX(), getMoveBoundMinX()) < 0){
            setX(getMoveBoundMinX()); // Left
        } else if(Double.compare((getX() + getImage().getWidth()), getMoveBoundMaxX()) > 0){
            setX(getMoveBoundMaxX() - getImage().getWidth()); // Right
        }
    }
    
    // TODO: Make this per-pixel collision & allow some overlapping
    // TODO: Bounces when colliding, this is an issue
    // TODO: Sometimes when colliding after a while he will not reset to outside the object, this allows teleportation through the obstacle.
    public void checkObsCollision(Obstacle nObs){
        if(this.getImageView().getBoundsInParent().intersects(nObs.getImageView().getBoundsInParent())){
            // Vertical
            if(Double.compare(this.getY(), this.getLy()) > 0){
                this.setY((nObs.getTop() - this.getImgHeight())); 
            } else if(Double.compare(this.getY(), this.getLy()) < 0){
                this.setY(nObs.getBottom()); 
            }

            // Horizontal
            if(Double.compare(this.getX(), this.getLx()) < 0){
                this.setX(nObs.getRight());
            } else if(Double.compare(this.getX(), this.getLx()) > 0){
                this.setX(nObs.getLeft() - this.getImgWidth());
            }
        }
    }
}
