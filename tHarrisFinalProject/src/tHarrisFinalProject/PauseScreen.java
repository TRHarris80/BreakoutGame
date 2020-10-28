/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tHarrisFinalProject;


import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Region;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author TRHar
 */
public class PauseScreen extends Region{
    Canvas mCanvas = new Canvas(600, 600);
    GraphicsContext gc = mCanvas.getGraphicsContext2D();
    String screenText;
    public PauseScreen(){
        super.getChildren().add(mCanvas);
    }
    
    
    
    public void clearPauseScreen(){
        gc.clearRect(0, 0, 600, 600);
        if(screenText.equals("Press Space to Start")){
         AudioClip intro = new AudioClip(this.getClass().getResource("pacman_ringtone.mp3").toString());
         intro.play();
         screenText = "";
        }
    }
    
    public void drawPaused(String text){
        screenText = text;
        gc.clearRect(0, 0, 600, 600);
        gc.setFill(Color.YELLOW);
        gc.setFont(new Font("", 200));
        gc.fillText(text, 0, 350, 600);
    }
    
    
       
    
}
