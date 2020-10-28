/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tHarrisFinalProject;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

/**
 *
 * @author TRHar
 */
public class PacMan extends Region{
    
    AnimationTimer pacmanTimer;
    Canvas mCanvas = new Canvas(600,600);
    GraphicsContext gc = mCanvas.getGraphicsContext2D();
    long mPreviousTime, TIMER_MSEC;
    Point2D curPos= new Point2D(10,12);
    int[][] mMaze;
    int UP = 2, RIGHT = 3, LEFT = 4, DOWN = 5;
    int dir = 1;
    boolean openClose = false;
    boolean upOpen = false, rightOpen = false, leftOpen = false, downOpen = false;
    int mod = 1;
    
    public PacMan(){
       super.getChildren().add(mCanvas);
    }
    
    public void drawPacMan(int[][] maze){
    gc.clearRect(0, 0, 600, 600);
    //Staring point of PacMan
        
        mMaze = maze;
        
        
        switch(dir){
            case 1:{
                curPos = new Point2D(curPos.getX(), curPos.getY());
                gc.setFill(Color.YELLOW);
                gc.fillOval(4+(curPos.getX()*30), 4+(curPos.getY()*30), 22, 22);
                break;
            }
        
            case 2:{
                curPos = new Point2D(curPos.getX()+.05, curPos.getY());
                gc.setFill(Color.YELLOW);
                gc.fillOval(4+(curPos.getX()*30), 4+(curPos.getY()*30), 22, 22);
                if(mod %2 == 0){
                    gc.setFill(Color.LIGHTBLUE);
                    double[] x = {15+(curPos.getX()*30),30+(curPos.getX()*30),30+(curPos.getX()*30)};
                    double[] y = {15+(curPos.getY()*30),0+(curPos.getY()*30),30+(curPos.getY()*30)};
                    gc.fillPolygon(x, y, 3);
                }
                mod++;
                
                    //System.out.println(((double)Math.round(curPos.getX()*100) / 100)*30);
                
                break;
            }
            case 3:
                {  
                    curPos = new Point2D(curPos.getX(), curPos.getY()-.05);
                    gc.setFill(Color.YELLOW);
                    gc.fillOval(4+(curPos.getX()*30), 4+(curPos.getY()*30), 22, 22);
                    if(mod %2 == 0){
                        gc.setFill(Color.LIGHTBLUE);
                        double[] x = {0+(curPos.getX()*30),15+(curPos.getX()*30),30+(curPos.getX()*30)};
                        double[] y = {0+(curPos.getY()*30),15+(curPos.getY()*30),0+(curPos.getY()*30)};
                        gc.fillPolygon(x, y, 3);
                    }
                    mod++;
                    break;
                }
        
            case 4:
            {
                    curPos = new Point2D(curPos.getX()-.05, curPos.getY());
                    gc.setFill(Color.YELLOW);
                    gc.fillOval(4+(curPos.getX()*30), 4+(curPos.getY()*30), 22, 22);
                    if(mod%2 == 0){
                        gc.setFill(Color.LIGHTBLUE);
                        double[] x = {0+(curPos.getX()*30),15+(curPos.getX()*30),0+(curPos.getX()*30)};
                        double[] y = {0+(curPos.getY()*30),15+(curPos.getY()*30),30+(curPos.getY()*30)};
                        gc.fillPolygon(x, y, 3);
                    }
                    mod++;
                    break;
            }
            case 5:
            {
                    curPos = new Point2D(curPos.getX(), curPos.getY()+.05);
                    gc.setFill(Color.YELLOW);
                    gc.fillOval(4+(curPos.getX()*30), 4+(curPos.getY()*30), 22, 22);
                    if(mod %2 == 0){
                        gc.setFill(Color.LIGHTBLUE);
                        double[] x = {0+(curPos.getX()*30),15+(curPos.getX()*30),30+(curPos.getX()*30)};
                        double[] y = {30+(curPos.getY()*30),15+(curPos.getY()*30),30+(curPos.getY()*30)};
                        gc.fillPolygon(x, y, 3);
                    }
                    mod++;
                    break;
            }
        
           
        }
    }
    
        
   
    
    public Point2D getCurPos(){
        return this.curPos;
    }
    
    public boolean checkPacDirection(int direction){
        int value = 0;
        
        //(int)curPos.getY()-1 > 1
        if(direction == 2){
                value = this.mMaze[(int)Math.round(curPos.getX())+1][(int)Math.round(curPos.getY())];
                
                if(value != 0){
                    return true;
                }   
        }  
        else if(direction == 3){
                value = this.mMaze[(int)Math.round(curPos.getX())][(int)Math.round(curPos.getY())-1];
                
                if(value != 0){
                    
                    return true;
                }   
        }
        
        else if(direction == 4){
                value = this.mMaze[(int)Math.round(curPos.getX())-1][(int)Math.round(curPos.getY())];
                
                if(value != 0){
                    return true;
                }   
        }
        else if(direction == 5){
                value = this.mMaze[(int)Math.round(curPos.getX())][(int)Math.round(curPos.getY())+1];
                
                if(value != 0){
                    return true;
                }  
        }
        else{
                return false;
        }
        return false;
    }
    
    public void setDirection(int num){
        if((((double)Math.round(curPos.getX()*100) / 100)*30 )%30 == 0 && (((double)Math.round(curPos.getY()*100) / 100)*30 )%30 == 0)
            dir = num;
        
        else{}
    }
    
    public int getDirection(){
        return dir;
    }
    
    public void setMouth(boolean bool){
        openClose = bool;
        
    }
    
    public boolean getMouth(){
        return openClose;
    }
    
    public void setPacPos(Point2D point){
        curPos = new Point2D((int)point.getX(), (int)point.getY());
        
    }
}
