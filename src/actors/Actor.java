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
 * 19Mar18    Kevin          Fixed actor collisions
 * 20Mar18    Kevin          Prevent Actors from moving for a few seconds after
 *                              colliding
 * 22Mar18    Kevin          Updated Boundaries
 * 22Mar18    Kevin          Updated Monster Collisions
 * 29Mar18    Kevin          Mostly fixed collisions causes multiple bounces
 * 30Mar18    Kevin          Changed Stats to Doubles
 *                           Changed isPlayer, isMonster, and setStats to abstract
 *                           Allow Monsters to damage players by running into them
 *                           Added death functionality
 * 31Mar18    Kevin          Monsters can't move until player does first
 * 03Apr18    Kevin          Made Direction Public
 *                           Used functions where appropriate
*/

package actors;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;
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
    
    protected double hp;
    protected double defense;
    protected double attack;
    protected double speed;
    
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
    
    private final Timer timer = new Timer();
    
    public enum Direction{
        N, NE, E, SE, S, SW, W, NW, NONE;
    }
    
    public void addToLayer() {
        layer.getChildren().add(imageView);
    }
    
    public void removeFromLayer() {
        layer.getChildren().remove(imageView);
    }
    
    // min and max stats. May change depending on future development.
    // Made public to accomodate testing without using/creating getters.
    public double minHp = 0.0;
    public double maxHp = 0.0;
    public double minDef = 0.0;
    public double maxDef = 0.0;
    public double minAtt = 0.0;
    public double maxAtt = 0.0;
    
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

    public double getHp(){
        return hp;
    }
    
    public double getMaxHp(){
        return maxHp;
    }
    public double getMinHp(){
        return minHp;
    }

    public double getDefense(){
        return defense;
    }

    public double getAttack(){
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
    
    public abstract boolean isPlayer();
    public abstract boolean isMonster();
    
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
    public void setHp(double number){
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
        minX = Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.0775;
        maxX = Toolkit.getDefaultToolkit().getScreenSize().getWidth() - (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.0775);
        minY = Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.09;
        maxY = Toolkit.getDefaultToolkit().getScreenSize().getHeight() - (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.09);
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
    public void getDamagedBy(Actor monster) {
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
    
    public void startMovement() {
        canMove = true;
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
            } // Else do nothing
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
        if(this == nAct){
            return;
        } // else do nothing
        
        if(this.getImageView().getBoundsInParent().intersects(nAct.getImageView().getBoundsInParent())){
            if(this.isMonster() && nAct.isPlayer()){
                nAct.getDamagedBy(this);
            } else if (this.isPlayer() && nAct.isMonster()){
                this.getDamagedBy(nAct);
            }

            Direction thisMoving = this.checkDir();  
            Direction otherMoving = nAct.checkDir();
            Direction relation = checkRelation(nAct);
            
            this.setCollision(true);
            nAct.setCollision(true);
            
            if(thisMoving == otherMoving || thisMoving == Direction.NONE || otherMoving == Direction.NONE){ // If actors are moving in the same direction
                switch(thisMoving){
                    case NONE:
                        this.bounce(otherMoving, nAct.getSpeed() * 15.0);
                        nAct.bounce(nAct.getOppDir(otherMoving), this.getSpeed());
                        break;
                    case N:
                        switch(relation){
                            case N:
                            case NE:
                            case NW:
                                nAct.bounce(nAct.getOppDir(otherMoving), this.getSpeed());
                                this.bounce(thisMoving, nAct.getSpeed() * 15.0);
                                break;
                            case S:
                            case SE:
                            case SW:
                                this.bounce(this.getOppDir(thisMoving), nAct.getSpeed());
                                nAct.bounce(thisMoving, this.getSpeed() * 15.0);
                                break;
                        }
                        break;
                    case NE:
                        switch(relation){
                            case N:
                            case NE:
                            case E:
                            case SE:
                                nAct.bounce(nAct.getOppDir(otherMoving), this.getSpeed());
                                this.bounce(thisMoving, nAct.getSpeed() * 15.0);
                                break;
                            case S:
                            case SW:
                            case W:
                            case NW:
                                this.bounce(this.getOppDir(thisMoving), nAct.getSpeed());
                                nAct.bounce(thisMoving, this.getSpeed() * 15.0);
                                break;
                        }
                        break;
                    case E:
                        switch(relation){
                            case NE:
                            case E:
                            case SE:
                                nAct.bounce(nAct.getOppDir(otherMoving), this.getSpeed());
                                this.bounce(thisMoving, nAct.getSpeed() * 15.0);
                                break;
                            case NW:
                            case W:
                            case SW:
                                this.bounce(this.getOppDir(thisMoving), nAct.getSpeed());
                                nAct.bounce(thisMoving, this.getSpeed() * 15.0);
                                break;
                        }
                        break;
                    case SE:
                        switch(relation){
                            case S:
                            case SW:
                            case SE:
                            case E:
                                nAct.bounce(nAct.getOppDir(otherMoving), this.getSpeed());
                                this.bounce(thisMoving, nAct.getSpeed() * 15.0);
                                break;
                            case N:
                            case NE:
                            case NW:
                            case W:
                                this.bounce(this.getOppDir(thisMoving), nAct.getSpeed());
                                nAct.bounce(thisMoving, this.getSpeed() * 15.0);
                                break;
                        }
                        break;
                    case S:
                        switch(relation){
                            case S:
                            case SE:
                            case SW:
                                nAct.bounce(nAct.getOppDir(otherMoving), this.getSpeed());
                                this.bounce(thisMoving, nAct.getSpeed() * 15.0);
                                break;
                            case N:
                            case NE:
                            case NW:
                                this.bounce(this.getOppDir(thisMoving), nAct.getSpeed());
                                nAct.bounce(thisMoving, this.getSpeed() * 15.0);
                                break;
                        }
                        break;
                    case SW:
                        switch(relation){
                            case S:
                            case SW:
                            case W:
                            case NW:
                                nAct.bounce(nAct.getOppDir(otherMoving), this.getSpeed());
                                this.bounce(thisMoving, nAct.getSpeed() * 15.0);
                                break;
                            case SE:
                            case E:
                            case NE:
                            case N:
                                this.bounce(this.getOppDir(thisMoving), nAct.getSpeed());
                                nAct.bounce(thisMoving, this.getSpeed() * 15.0);
                                break;
                        }
                        break;
                    case W:
                        switch(relation){
                            case NW:
                            case W:
                            case SW:
                                nAct.bounce(nAct.getOppDir(otherMoving), this.getSpeed());
                                this.bounce(thisMoving, nAct.getSpeed() * 15.0);
                                break;
                            case NE:
                            case E:
                            case SE:
                                this.bounce(this.getOppDir(thisMoving), nAct.getSpeed());
                                nAct.bounce(thisMoving, this.getSpeed() * 15.0);
                                break;
                        }
                        break;
                    case NW:
                        switch(relation){
                            case SW:
                            case W:
                            case NW:
                            case N:
                                nAct.bounce(nAct.getOppDir(otherMoving), this.getSpeed());
                                this.bounce(thisMoving, nAct.getSpeed() * 15.0);
                                break;
                            case S:
                            case SE:
                            case E:
                            case NE:
                                this.bounce(this.getOppDir(thisMoving), nAct.getSpeed());
                                nAct.bounce(thisMoving, this.getSpeed() * 15.0);
                                break;
                        }
                        break;
                }
            } else { // else both bounce back - may expand in the future
                this.bounce(otherMoving, nAct.getSpeed() * 15.0);
                nAct.bounce(thisMoving, this.getSpeed() * 15.0);
            }
            
            if(this.isMonster()){
                this.changeDirection();
            } // Else do nothing
            
            if(nAct.isMonster()){
                nAct.changeDirection();
            } // Else do nothing
        } else {
            setCollision(false);
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
        
        updateUI();
        this.stopMovement();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                startMovement();
            }
        }, 1*1000);
    }
    
    public Direction checkRelation(Actor nAct){
        if(Double.compare(this.getY(), nAct.getY()) < 0 && Double.compare(this.getX(), nAct.getX()) == 0){
            return Direction.N;
        } else if(Double.compare(this.getY(), nAct.getY()) < 0 && Double.compare(this.getX(), nAct.getX()) > 0){
            return Direction.NE;
        } else if(Double.compare(this.getY(), nAct.getY()) == 0 && Double.compare(this.getX(), nAct.getX()) > 0){
            return Direction.E;
        } else if(Double.compare(this.getY(), nAct.getY()) > 0 && Double.compare(this.getX(), nAct.getX()) > 0){
            return Direction.SE;
        } else if(Double.compare(this.getY(), nAct.getY()) > 0 && Double.compare(this.getX(), nAct.getX()) == 0){
            return Direction.S;
        } else if(Double.compare(this.getY(), nAct.getY()) > 0 && Double.compare(this.getX(), nAct.getX()) < 0){
            return Direction.SW;
        } else if(Double.compare(this.getY(), nAct.getY()) == 0 && Double.compare(this.getX(), nAct.getX()) < 0){
            return Direction.W;
        } else{
            return Direction.NW;
        }
    }
    
    public abstract void changeDirection();
    
    public Direction getOppDir(Direction nDir){
        switch(nDir){
            case N:
                return Direction.S;
            case NE:
                return Direction.SW;
            case E:
                return Direction.W;
            case SE:
                return Direction.NW;
            case S:
                return Direction.N;
            case SW:
                return Direction.NE;
            case W:
                return Direction.E;
            case NW:
                return Direction.SE;
            default: // Should not get here
                return Direction.N;
        }
    }
    
    public boolean isDead(){
        return (Double.compare(getHp(), 0.0) <= 0);
    }
    
    protected abstract void setStats();
}
