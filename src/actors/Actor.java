/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * Actor - Abstract superclass that handles common functionality of actors
 * Change Log
 * /////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 18Feb18    Kevin          Initial Abstract Actor Created
 * 24Feb18    Kevin          Fixed Collision Checking
 * 25Feb18    Kevin          Fixed collision checking for vertical & diagonal movement
 * 27Feb18    Kevin          Final Collision Checking Update - works diagonally now
 * 12Mar18    Kevin          Attempted to fix actor collisions
 * 13Mar18    Kevin          minimal fix actor collisions
*/

package actors;

import java.awt.Toolkit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import obstacle.*;

public abstract class Actor {
    protected Image actorImg;
    protected ImageView imageView;
    
    protected Pane layer;
    
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
    
    protected boolean canMove = true;
    protected boolean collision = true;
    protected boolean hitBound = false;
    protected boolean amPlayer = false; // May want to look into marker interface
    protected boolean amMonster = false; // May want to look into marker interface
    
    protected enum Direction{
        N, NE, E, SE, S, SW, W, NW, NONE;
    }
    
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
    
    public double getSpeed(){
        return speed;
    }
    
    public boolean isPlayer(){
        return amPlayer;
    }
    
    public boolean isMonster(){
        return amMonster;
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
    
    protected void togglePlayer(){ // only called in constructor of object to say if it is a player or not
        amPlayer = !amPlayer;
    }
    
    protected void toggleMonster(){ // only called in constructor of objec to say if it is a monster or not
        amMonster = !amMonster;
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
        
        left = this.getX();
        right = this.getX() + this.getImgWidth();
        top = this.getY();
        bottom = this.getY() + this.getImgHeight();
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
        boolean hitVert = false;
        boolean hitHor = false;
        
        // Vertical
        if(Double.compare((getY() + (getImage().getHeight() / 2.0)), getMoveBoundMinY()) < 0){
            setY(getMoveBoundMinY() - (getImage().getHeight() / 2.0)); // Top
            hitVert = true;
        } else if(Double.compare((getY() + getImage().getHeight()), getMoveBoundMaxY()) > 0){
            setY(getMoveBoundMaxY() - getImage().getHeight()); // Bottom
            hitVert = true;
        }
        
        // Horizontal
        if(Double.compare(getX(), getMoveBoundMinX()) < 0){
            setX(getMoveBoundMinX()); // Left
            hitHor = true;
        } else if(Double.compare((getX() + getImage().getWidth()), getMoveBoundMaxX()) > 0){
            setX(getMoveBoundMaxX() - getImage().getWidth()); // Right
            hitHor = true;
        }
        
        setHitBound(hitVert || hitHor);
    }
    
    // TODO: Make this per-pixel collision & allow some overlapping
    public void checkObsCollision(Obstacle nObs){
        if(this.getImageView().getBoundsInParent().intersects(nObs.getImageView().getBoundsInParent())){
            Direction moving = checkDir();

            switch(moving){
                case N:
                    if((Double.compare(this.getBottom(), nObs.getTop()) == 0) || // Touching object's top
                       (Double.compare(this.getLeft(), nObs.getRight()) == 0) || // Touching object's right
                       (Double.compare(this.getRight(), nObs.getLeft()) == 0)    // Touching object's left
                      ){
                        // Do Nothing
                    } else {
                        this.setY(nObs.getBottom());
                    }
                    break;
                case NE:
                    if((Double.compare(this.getLeft(), nObs.getRight()) == 0) || // Touching object's right
                       (Double.compare(this.getBottom(), nObs.getTop()) == 0)    // Touching object's top
                      ){
                        // Do Nothing
                    } else if(Double.compare(nObs.getBottom() - this.getTop(), 
                              this.getRight() - nObs.getLeft()) < 0){ // Hit the object's bottom first
                        this.setY(nObs.getBottom());
                    } else if(Double.compare(this.getRight() - nObs.getLeft(), 
                              nObs.getBottom() - this.getTop()) < 0){ // Hit the object's left first
                        this.setX(nObs.getLeft() - this.getImgWidth());
                    } else {
                        this.setY(nObs.getBottom());
                        this.setX(nObs.getLeft() - this.getImgWidth() + 1.0);
                    }
                    break;
                case E:
                    if((Double.compare(this.getLeft(), nObs.getRight()) == 0) || // Touching object's right
                       (Double.compare(this.getTop(), nObs.getBottom()) == 0) || // Touching object's bottom
                       (Double.compare(this.getBottom(), nObs.getTop()) == 0)    // Touching object's top
                      ){
                        // Do Nothing
                    } else {
                       this.setX(nObs.getLeft() - this.getImgWidth());
                    }
                    break;
                case SE:
                    if((Double.compare(this.getLeft(), nObs.getRight()) == 0) || // Touching object's right
                       (Double.compare(this.getTop(), nObs.getBottom()) == 0)    // Touching object's bottom
                      ){
                        // Do Nothing
                    } else if(Double.compare(this.getBottom() - nObs.getTop(), 
                              this.getRight() - nObs.getLeft()) < 0){ // Hit the object's top first
                        this.setY(nObs.getTop() - this.getImgHeight());
                    } else if(Double.compare(this.getRight() - nObs.getLeft(), 
                              this.getBottom() - nObs.getTop()) < 0){ // Hit the object's left first
                        this.setX(nObs.getLeft() - this.getImgWidth());
                    } else {
                        this.setY(nObs.getTop() - this.getImgHeight());
                        this.setX(nObs.getLeft() - this.getImgWidth() + 1.0);
                    }
                    break;
                case S:
                    if((Double.compare(this.getTop(), nObs.getBottom()) == 0) || // Touching object's bottom
                       (Double.compare(this.getLeft(), nObs.getRight()) == 0) || // Touching object's right
                       (Double.compare(this.getRight(), nObs.getLeft()) == 0)    // Touching object's left
                      ){
                        // Do Nothing
                    } else {
                        this.setY(nObs.getTop() - this.getImgHeight());
                    }
                    break;
                case SW:
                    if((Double.compare(this.getRight(), nObs.getLeft()) == 0) || // Touching object's left
                       (Double.compare(this.getTop(), nObs.getBottom()) == 0)    // Touching object's bottom
                      ){
                        // Do Nothing
                    } else if(Double.compare(this.getBottom() - nObs.getTop(), 
                              nObs.getRight() - this.getLeft()) < 0){ // Hit the object's top first
                        this.setY(nObs.getTop() - this.getImgHeight());
                    } else if(Double.compare(nObs.getRight() - this.getLeft(), 
                              this.getBottom() - nObs.getTop()) < 0){ // Hit the object's right first
                        this.setX(nObs.getRight());
                    } else {
                        this.setY(nObs.getTop() - this.getImgHeight());
                        this.setX(nObs.getRight() - 1.0);
                    }
                    break;
                case W:
                    if((Double.compare(this.getRight(), nObs.getLeft()) == 0) || // Touching object's left
                       (Double.compare(this.getTop(), nObs.getBottom()) == 0) || // Touching object's bottom
                       (Double.compare(this.getBottom(), nObs.getTop()) == 0)    // Touching object's top
                      ){
                        // Do Nothing
                    } else {
                        this.setX(nObs.getRight());
                    }
                    break;
                case NW:
                    if((Double.compare(this.getRight(), nObs.getLeft()) == 0) || // Touching object's left
                       (Double.compare(this.getBottom(), nObs.getTop()) == 0)    // Touching object's top
                      ){
                        // Do Nothing
                    } else if(Double.compare(nObs.getBottom() - this.getTop(), 
                              nObs.getRight() - this.getLeft()) < 0){ // Hit the object's bottom first
                        this.setY(nObs.getBottom());
                    } else if(Double.compare(nObs.getRight() - this.getLeft(), 
                              nObs.getBottom() - this.getTop()) < 0){ // Hit the object's right first
                        this.setX(nObs.getRight());
                    } else {
                        this.setY(nObs.getBottom());
                        this.setX(nObs.getRight() - 1.0);
                    }
                    break;
                default: // If it is not moving
                    break; // do nothing
            }
            
            setCollision(true);
        } else {
            setCollision(false);
        }
    }
    
    public void setCollision(boolean nBool){
        collision = nBool;
    }
    
    public void setHitBound(boolean nBool){
        hitBound = nBool;
    }
    
    // TODO: Make this per-pixel collision & allow some overlapping
    public void checkActorCollision(Actor nAct){
        if(this.getImageView().getBoundsInParent().intersects(nAct.getImageView().getBoundsInParent())){
            Direction thisMoving = this.checkDir();  
            Direction otherMoving = nAct.checkDir();  
            
            double thisBounceAm = 0.0;
            double thatBounceAm = 0.0;
            
            double thisBounceDir = 0.0;
            double thatBounceDir = 0.0;
           
            if(this.isMonster() && nAct.isPlayer()){
                // This Bounce Amount Stays 0
                thatBounceAm = nAct.getSpeed() * 8;
            } else {
                thisBounceAm = this.getSpeed() * 8;
            }
            
            // TODO: Add logic to figure out direction of bounce.
            
            this.bounce(otherMoving, thisBounceAm);
            nAct.bounce(otherMoving, thatBounceAm);
        }
    }
    
    public Direction checkDir(){
        if (Double.compare(this.getY(), this.getLy()) < 0 && Double.compare(this.getX(), this.getLx()) == 0){
            return Direction.N;
        } else if(Double.compare(this.getY(), this.getLy()) < 0 && Double.compare(this.getX(), this.getLx()) > 0){
            return Direction.NE;
        } else if(Double.compare(this.getX(), this.getLx()) > 0 && Double.compare(this.getY(), this.getLy()) == 0){
            return Direction.E;
        } else if(Double.compare(this.getY(), this.getLy()) > 0 && Double.compare(this.getX(), this.getLx()) > 0){
            return Direction.SE;
        } else if(Double.compare(this.getY(), this.getLy()) > 0 && Double.compare(this.getX(), this.getLx()) == 0){
            return Direction.S;
        } else if(Double.compare(this.getY(), this.getLy()) > 0 && Double.compare(this.getX(), this.getLx()) < 0){
            return Direction.SW;
        } else if(Double.compare(this.getX(), this.getLx()) < 0 && Double.compare(this.getY(), this.getLy()) == 0){
            return Direction.W;
        } else if(Double.compare(this.getY(), this.getLy()) < 0 && Double.compare(this.getX(), this.getLx()) < 0){
            return Direction.NW;
        }
        
        return Direction.NONE;
    }
    
    public void bounce(Direction nDir, double nAmount){
        switch(nDir){
            case N:
                setY(getY() - nAmount);
                break;
            case NE:
                setX(getX() + nAmount);
                setY(getY() - nAmount);
                break;
            case E:
                setX(getX() + nAmount);
                break;
            case SE:
                setX(getX() + nAmount);
                setY(getY() + nAmount);
                break;
            case S:
                setY(getY() + nAmount);
                break;
            case SW:
                setX(getX() - nAmount);
                setY(getY() + nAmount);
                break;
            case W:
                setX(getX() - nAmount);
                break;
            case NW:
                setX(getX() - nAmount);
                setY(getY() - nAmount);
                break;
        }
        
        canMove = false;
        updateUI();
        // TODO: Prevent colliding actors from moving for a moment when thrown backward
    }
}
