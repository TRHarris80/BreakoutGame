/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tHarrisFinalProject;

import java.io.File;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author TRHar
 */
public class Maze extends Region{
    int mMaze[][] = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,2,2,2,2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,0},
        {0,2,0,0,0,2,0,0,2,0,0,2,0,0,2,0,0,0,2,0},
        {0,2,0,0,0,2,0,0,2,0,0,2,0,0,2,0,0,0,2,0},
        {0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0},
        {0,2,0,0,0,2,0,2,0,0,0,0,2,0,2,0,0,0,2,0},
        {0,2,0,0,0,2,0,2,2,0,0,2,2,0,2,0,0,0,2,0},
        {0,2,2,2,2,2,0,0,2,0,0,2,0,0,2,2,2,2,2,0},
        {0,0,0,0,0,2,0,2,2,2,2,2,2,0,2,0,0,0,0,0},
        {0,2,2,2,2,2,0,2,0,0,0,0,2,0,2,2,2,2,2,0},
        {0,2,0,0,0,2,2,2,0,0,0,0,2,2,2,0,0,0,2,0},
        {0,2,2,2,2,2,0,2,0,0,0,0,2,0,2,2,2,2,2,0},
        {0,0,0,0,0,2,0,2,2,2,2,2,2,0,2,0,0,0,0,0},
        {0,2,2,2,2,2,0,0,2,0,0,2,0,0,2,2,2,2,2,0},
        {0,2,0,0,0,2,0,2,2,0,0,2,2,0,2,0,0,0,2,0},
        {0,2,0,0,0,2,0,2,0,0,0,0,2,0,2,0,0,0,2,0},
        {0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,0,2,0},
        {0,2,0,0,0,2,0,0,0,2,2,0,0,0,2,0,0,0,2,0},
        {0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
    
    
    int mMazeCheat[][] = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,0},
        {0,1,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,1,0},
        {0,1,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,0,1,0},
        {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
        {0,1,0,0,0,1,0,1,0,0,0,0,1,0,1,0,0,0,1,0},
        {0,1,0,0,0,1,0,1,1,0,0,1,1,0,1,0,0,0,1,0},
        {0,1,1,1,1,1,0,0,1,0,0,1,0,0,1,1,1,1,1,0},
        {0,0,0,0,0,1,0,1,1,1,1,1,1,0,1,0,0,0,0,0},
        {0,1,1,1,1,1,0,1,0,0,0,0,1,0,1,1,1,1,1,0},
        {0,1,0,0,0,1,1,1,0,0,0,0,1,1,1,0,0,0,1,0},
        {0,1,1,1,1,1,0,1,0,0,0,0,1,0,1,1,1,1,1,0},
        {0,0,0,0,0,1,0,1,1,2,1,1,1,0,1,0,0,0,0,0},
        {0,1,1,1,1,1,0,0,1,0,0,1,0,0,1,1,1,1,1,0},
        {0,1,0,0,0,1,0,1,1,0,0,1,1,0,1,0,0,0,1,0},
        {0,1,0,0,0,1,0,1,0,0,0,0,1,0,1,0,0,0,1,0},
        {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,0},
        {0,1,0,0,0,1,0,0,0,1,1,0,0,0,1,0,0,0,1,0},
        {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
    
    int mMaze2[][] = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0},
        {0,2,2,0,0,0,2,2,0,0,0,0,2,0,0,0,0,0,2,0},
        {0,2,0,0,0,0,0,2,2,2,2,2,2,0,0,0,0,0,2,0},
        {0,2,0,0,0,0,0,2,0,0,0,0,2,0,0,0,0,0,2,0},
        {0,2,0,0,0,0,0,2,0,0,0,0,2,0,0,0,0,0,2,0},
        {0,2,2,2,2,2,2,2,0,0,0,0,2,0,0,0,0,0,2,0},
        {0,2,0,2,2,2,0,2,2,2,2,2,2,2,2,2,2,2,2,0},
        {0,2,0,2,2,2,0,2,0,0,0,0,2,0,2,2,2,2,2,0},
        {0,2,0,0,0,0,0,2,2,2,2,2,2,0,0,2,2,2,2,0},
        {0,2,2,2,2,2,2,2,2,2,2,2,2,0,2,0,2,2,2,0},
        {0,2,0,2,0,0,0,2,0,0,0,0,2,0,2,2,0,2,2,0},
        {0,2,0,2,0,2,0,2,2,2,2,2,2,0,2,2,2,0,2,0},
        {0,2,0,0,0,2,0,2,0,0,0,0,2,2,2,2,2,2,2,0},
        {0,2,2,2,2,2,2,2,0,0,0,0,2,0,0,0,0,0,2,0},
        {0,2,0,2,2,2,0,2,0,0,0,0,2,0,2,0,2,0,2,0},
        {0,2,0,2,2,2,0,2,2,2,2,2,2,0,2,0,2,0,2,0},
        {0,2,0,0,0,0,0,2,0,0,0,0,2,0,2,0,2,0,2,0},
        {0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    };
    
    int mMaze2Cheat[][] = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
        {0,1,1,0,0,0,1,1,0,0,0,0,1,0,0,0,0,0,1,0},
        {0,1,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,1,0},
        {0,1,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,1,0},
        {0,1,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,1,0},
        {0,1,1,1,1,1,1,1,0,0,0,0,1,0,0,0,0,0,1,0},
        {0,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,0},
        {0,1,0,1,1,1,0,1,0,0,0,0,1,0,1,1,1,1,1,0},
        {0,1,0,0,0,0,0,1,1,1,1,1,2,0,0,1,1,1,1,0},
        {0,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,1,1,0},
        {0,1,0,1,0,0,0,1,0,0,0,0,1,0,1,1,0,1,1,0},
        {0,1,0,1,0,1,0,1,1,1,1,1,1,0,1,1,1,0,1,0},
        {0,1,0,0,0,1,0,1,0,0,0,0,1,1,1,1,1,1,1,0},
        {0,1,1,1,1,1,1,1,0,0,0,0,1,0,0,0,0,0,1,0},
        {0,1,0,1,1,1,0,1,0,0,0,0,1,0,1,0,1,0,1,0},
        {0,1,0,1,1,1,0,1,1,1,1,1,1,0,1,0,1,0,1,0},
        {0,1,0,0,0,0,0,1,0,0,0,0,1,0,1,0,1,0,1,0},
        {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    };
            
        final static int WALL = 0;
        final static int BLANK = 1;
        final static int CAKE = 2;
        static Canvas mCanvas = new Canvas(600,600);
        GraphicsContext gc = mCanvas.getGraphicsContext2D();
        int[][] mainMaze;
        int mLevel = 1;
        
    public Maze(Stage ps){
        
        super.getChildren().add(mCanvas);
        
        //super.getChildren().add(rect);
        
    }
    
    public void Draw(){
        gc.clearRect(0, 0, 600, 600);
        for(int row = 0; row < mainMaze.length; row++){
            for(int col = 0; col <mainMaze[row].length; col++){
                switch(mainMaze[row][col]){
                    case WALL:
                        gc.setFill(Color.BLACK);
                        gc.fillRect(row*30, col*30, 30, 30);
                        break;
                    case BLANK:
                        gc.setFill(Color.LIGHTBLUE);
                        gc.fillRect(row*30, col*30, 30, 30);
                        gc.setFill(Color.WHITE);
                        break;
                    case CAKE:
                        gc.setFill(Color.LIGHTBLUE);
                        gc.fillRect(row*30, col*30, 30, 30);
                        gc.setFill(Color.WHITE);
                        gc.fillOval(row*30+10, col *30+10, 10, 10);
                        break;
                            
                            
                }
        
            }
        }
    }
    
    public int[][] getMap(){
        return mainMaze;
    }
    
    public void cakeEaten(Point2D point){
        if(mainMaze[(int)Math.round(point.getX())][(int)Math.round(point.getY())] ==2){
            mainMaze[(int)Math.round(point.getX())][(int)Math.round(point.getY())] = 1;
            playChompSound();
        }
    }
    
    public int getCakeCount(){
        int num = 0;
        for(int row = 0; row < mainMaze.length; row++){
            for(int col = 0; col <mainMaze[row].length; col++){
                if(mainMaze[row][col] == 2){
                   num++;
                }
                else{}
            }
    
        }
        
        return num;
    
    }
    
    public int getScore(){
        int num = 0;
        for(int row = 0; row < mainMaze.length; row++){
            for(int col = 0; col <mainMaze[row].length; col++){
                if(mainMaze[row][col] == 1){
                   num++;
                }
                else{}
            }
    
        }
        
        return num;
    }
    
    public void setLevel(int i){
       
       mLevel = i;
    }
    public int getLevel(){
        return mLevel;
    }
    public void setMaze(int i){
        if(i == 1){
            mainMaze = mMaze;
            resetLevel();
            mLevel = 1;
        }
        else if(i == 2){
            mainMaze = mMaze2;
            resetLevel();
            mLevel = 2;
        }
        else if(i == 3){
            mainMaze = mMazeCheat;
            mMazeCheat[12][9] = 2;
        }
        else if(i == 4){
            mainMaze = mMaze2Cheat;
            mMaze2Cheat[9][12] = 2;
        }
    }
    
    public void resetLevel(){
        for(int row = 0; row < mainMaze.length; row++){
            for(int col = 0; col <mainMaze[row].length; col++){
                if(mainMaze[row][col] == 1){
                   mainMaze[row][col] = 2;
                }
                else{}
            }
    
        }
    }
    
    public boolean isFinished(){
        if(getCakeCount() == 0){
            return true;
        }
        return false;
    }
    private void playChompSound(){
         AudioClip chomp = new AudioClip(this.getClass().getResource("pacman_chomp.wav").toString());
         chomp.play();
     }
    
}