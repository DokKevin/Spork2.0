/*
 * SE Project: SPORK
 * Authors: Kevin Kauffman, Glenn Sweithelm
 * Actor - Abstract superclass that handles common functionality of actors
 * Change Log
 * ////////////////////////////////////////////////////////////////////////////
 * Date       Contributer    Change
 * 18Feb18    Kevin          Initial Abstract Actor Created
*/

package actors;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class Actor {
    Image actorImg;
    ImageView imageView;
    
    Pane layer;
    
    protected double x;
    protected double y;
    protected double r;
    
    protected double dx;
    protected double dy;
    protected double dr;
    
    protected int hp;
    protected int defense;
    protected int attack;
    
    protected boolean removable = false;
    
    protected double w;
    protected double h;
    
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

    public double getR() {
        return r;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public double getDr() {
        return dr;
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
    
    public void setR(double nr) {
        r = nr;
    }
    
    public void setDx(double ndx) {
        dx = ndx;
    }
    
    public void setDy(double ndy) {
        dy = ndy;
    }
    
    public void setDr(double ndr) {
        dr = ndr;
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
    
    public boolean isRemovable() {
        return removable;
    }
    
    public void move() {
        if(!canMove){
            return;
        }

        x += dx;
        y += dy;
        r += dr;
    }
    
    public boolean isAlive() {
        return (hp > 0);
    }
    
    public void updateUI() {
        imageView.setX(x);
        imageView.setY(y);
        imageView.setRotate(r);
    }
    
//    // TODO: make this per-pixel-collision & Make Obstacles
//    public boolean collidesWith(Obstacle someObs) {
//        return ( someObs.getX() + someObs.getW() >= this.getX() && someObs.getY() + someObs.getH() >= this.getY() && someObs.getX() <= this.getX() + this.getWidth() && someObs.getY() <= this.getY() + this.getHeight());
//    }
    
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
}
