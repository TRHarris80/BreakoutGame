/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tHarrisFinalProject;

import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;


/**
 *
 * @author TRHar
 * 
 */

public class Ghost extends Region{
    Point2D curPos;
    Canvas mCanvas = new Canvas(600,600);
    int[][] mMaze;
    final int UP = 1, RIGHT = 2, DOWN = 3, LEFT = 4; //down = 1
    Random rand = new Random();
    int dir = rand.nextInt(5);
    GraphicsContext gc = mCanvas.getGraphicsContext2D();
    boolean moved = false, up = true, down = true, left = true, right = true;
    boolean upOpen, rightOpen, leftOpen, downOpen;
    int level;
    
    public Ghost(int[][] maze){
        this.mMaze =maze;
        super.getChildren().add(mCanvas);
    }
    
    public void drawGhost(int[][] maze, Point2D pos){
        
       
            gc.clearRect(0, 0, 600, 600);
            curPos = pos;
            gc.setFill(Color.RED);
            gc.fillOval(4+(pos.getX()*30), 4+(pos.getY()*30), 22, 22);
        
        
    }
    
    public void setLevel(int num){
        level = num;
    }
    
    public Point2D getCurPos(){
        return curPos;
    }
    
    public void setCurPos(){
        int randomX, randomY;
        
                
        do{
            randomX = rand.nextInt(20);
            randomY = rand.nextInt(20);
        }while(checkPosition(new Point2D(randomX, randomY))== false);
        curPos = new Point2D(randomX, randomY);
    }
    
    public void setAllTrue(){
        this.up =true;
        this.down = true;
        this.left = true;
        this.right = true;
               
    }
    // might need to pass the map into this as well
    public void moveDirection(Point2D pos){
        
        
        if(level == 1){
        if(dir == UP && checkDirection(dir) == true){
            this.drawGhost(mMaze, new Point2D(curPos.getX(), curPos.getY()-.025));
            //curPos = new Point2D(curPos.getX(), curPos.getY()+.05);
            
        }
        else if(dir == RIGHT && checkDirection(dir) == true){
            this.drawGhost(mMaze, new Point2D(curPos.getX()+.025, curPos.getY()));
            //curPos = new Point2D(curPos.getX()+.05, curPos.getY());
            
        }
        else if(dir == DOWN && checkDirection(dir) == true){
            this.drawGhost(mMaze, new Point2D(curPos.getX(), curPos.getY()+.025));
            //curPos = new Point2D(curPos.getX(), curPos.getY()-.05);
            
        }
        else if(dir == LEFT && checkDirection(dir) == true){
            this.drawGhost(mMaze, new Point2D(curPos.getX()-.025, curPos.getY()));
            //curPos = new Point2D(curPos.getX()-.05, curPos.getY());
            
        }
        else{
            dir = rand.nextInt(5);
            //System.out.println("help");
        }
        }
        if(level == 2){
            if(dir == UP && checkDirection(dir) == true){
            this.drawGhost(mMaze, new Point2D(curPos.getX(), curPos.getY()-.05));
            //curPos = new Point2D(curPos.getX(), curPos.getY()+.05);
            
        }
        else if(dir == RIGHT && checkDirection(dir) == true){
            this.drawGhost(mMaze, new Point2D(curPos.getX()+.05, curPos.getY()));
            //curPos = new Point2D(curPos.getX()+.05, curPos.getY());
            
        }
        else if(dir == DOWN && checkDirection(dir) == true){
            this.drawGhost(mMaze, new Point2D(curPos.getX(), curPos.getY()+.05));
            //curPos = new Point2D(curPos.getX(), curPos.getY()-.05);
            
        }
        else if(dir == LEFT && checkDirection(dir) == true){
            this.drawGhost(mMaze, new Point2D(curPos.getX()-.05, curPos.getY()));
            //curPos = new Point2D(curPos.getX()-.05, curPos.getY());
            
        }
        else{
            dir = rand.nextInt(5);
            //System.out.println("help");
        }
        }
       // }
    }
    
    public boolean checkPosition(Point2D point){
        if(this.mMaze[(int)Math.round(point.getX())][(int)Math.round(point.getY())] != 0)
            return true;
        else
            return false;
    }
    
    public boolean checkDirection(int i){
        int value = 0;
        
        //(int)curPos.getY()-1 > 1
        switch (i) {
            case UP:
                value = this.mMaze[(int)curPos.getX()][(int)curPos.getY()];
                if(value != 0){
                    
                    return true;
                    
                }   break;
            case RIGHT:
                value = this.mMaze[(int)curPos.getX()+1][(int)curPos.getY()];
                if(value != 0){
                    return true;
                }   break;
            case DOWN:
                value = this.mMaze[(int)curPos.getX()][(int)curPos.getY()+1];
                if(value != 0){
                    return true;
                }   break;
            case LEFT:
                value = this.mMaze[(int)curPos.getX()][(int)curPos.getY()];
                if(value != 0){
                    return true;
                }   break;
            default:
                return false;
        }
        return false;
    }
    
    public void setMaze(int[][] maze){
        this.mMaze = maze;
    }
    

    
}
