/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tHarrisFinalProject;

import com.sun.javaws.Main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author tharris22
 */
public class tHarrisFinalProject extends Application {
    
    static Label mStatus;
    static PacMan pacman;
    Point2D startingPosition = new Point2D(10,12);
    static Maze mMaze;
    AnimationTimer mTimer, pacmanTimer;
    long mPreviousTime = 0, TIMER_MSEC = 35, mPacTime;
    static int randomX, randomY;
    static Random rand = new Random();
    boolean mouthClosed = false;
    static boolean pause = true;
    static MenuItem pauseMenuItem, goMenuItem; 
    static PauseScreen pauseScreen;
    static ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
    static Ghost ghostOne;
    static ChoiceDialog optionPane;
    static StackPane sp;
    static Stage mStage;
    static File curFile = null;
    static MenuItem openMenuItem, saveASMenuItem;
    static int mGhostNum = 1;
    static ArrayList<Ghost> mGhostList = new ArrayList();
    
    public tHarrisFinalProject() {
        
    }
    @Override
    public void start(Stage primaryStage) {
        readPreferences();
        sp = new StackPane();
        mMaze = new Maze(primaryStage);
        mMaze.setMaze(1);
        mMaze.Draw();
        mMaze.prefWidthProperty().bind(primaryStage.widthProperty());
        mMaze.prefHeightProperty().bind(primaryStage.heightProperty());
        sp.getChildren().add(mMaze);
        pacman = new PacMan();
        pacman.drawPacMan(mMaze.getMap());
        
        
        sp.getChildren().add(pacman);
        ghostOne = new Ghost(mMaze.getMap());
        ghostOne.setLevel(1);
        do{
            randomX = rand.nextInt(20);
            randomY = rand.nextInt(20);
        }while(ghostOne.checkPosition(new Point2D(randomX, randomY))== false);
        
        ghostOne.drawGhost(mMaze.getMap(), new Point2D(randomX, randomY));
        ghosts.add(ghostOne);
        sp.getChildren().add(ghostOne);
        mGhostList.add(ghostOne);
        pauseScreen = new PauseScreen();
        pauseScreen.drawPaused("Press Space to Start");
        
        sp.getChildren().add(pauseScreen);
        //sp.getChildren().add(pauseScreen);
        mTimer = new AnimationTimer(){
            @Override
            public void handle(long now){
                onTimer(now);
            }

            private void onTimer(long now) {
                now = System.currentTimeMillis();
                long elapsed =(now- mPreviousTime);
                if(elapsed > TIMER_MSEC){
                    //ghostOne.drawGhost(mMaze.getMapOne(), new Point2D(ghostOne.getCurPos().getX()+.05, ghostOne.getCurPos().getY()));
                    if(pause == false && !mMaze.isFinished() && !pacGhostCollision()){
                    ghostOne.moveDirection(new Point2D(ghostOne.getCurPos().getX(), ghostOne.getCurPos().getY()));
                        if(pacman.checkPacDirection(pacman.getDirection()) == true){
                            pacman.drawPacMan(mMaze.getMap());
                            mMaze.cakeEaten(pacman.getCurPos());
                        
                            setStatus();
                        
                        }
                        else{
                            pacman.setDirection(1);
                            pacman.drawPacMan(mMaze.getMap());
                        }
                    pacman.getDirection();
                    mPreviousTime = now;
                    }
                    else if(pacGhostCollision()){
                        gameOver();
                    }
                    else if(mMaze.isFinished() && mMaze.getLevel() == 2){
                        endGame();
                    }
                    else if(mMaze.isFinished()){
                        ghostOne.setLevel(2);
                        mMaze.setMaze(2);
                        ghostOne.setMaze(mMaze.getMap());
                        pauseNewLevel();
                        
                        
                        
                    }
                        
                    
                    
                
                }
                
                
                mMaze.Draw();
                
                
            }
            
            
        };
        mTimer.start();
        //sp.setOnKeyPressed(event -> pacmanDirection(event));
        
        
        
        
        sp.prefWidthProperty().bind(primaryStage.widthProperty());
        BorderPane root = new BorderPane();
        root.setCenter(sp);
        //btn.prefWidthProperty().bind(primaryStage.widthProperty().divide(2));
        //add the menus
        root.setTop(buildMenuBar());
        
        mStatus = new Label("Score: 0 Level = 1 Cakes = 184");
        ToolBar toolBar = new ToolBar(mStatus);
        root.setBottom(toolBar);
        
        Scene scene = new Scene(root, 600, 650);
        scene.setOnKeyPressed(event -> pacmanDirection(event));
        //btn.prefWidthProperty().bind(scene.widthProperty().divide(2.0));
        mStage = primaryStage;
        primaryStage.getIcons().add(new Image("tHarrisFinalProject/pacman03.jpg"));
        primaryStage.setTitle("PAC-MAN");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private static void onAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Trenton R Harris, CSCD 370 Final Project, Wtr 2020");
        alert.showAndWait();
    }
    
    public static MenuBar buildMenuBar() {
        // build a menu bar
        MenuBar menuBar = new MenuBar();
        SeparatorMenuItem smi = new SeparatorMenuItem();
// File menu with just a quit item for now
        Menu fileMenu = new Menu("_File");
        
        MenuItem quitMenuItem = new MenuItem("_Quit");
        quitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));
        quitMenuItem.setOnAction(actionEvent -> Platform.exit());
        fileMenu.getItems().add(quitMenuItem);
        
        openMenuItem = new MenuItem("Open");
        openMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));
        openMenuItem.setOnAction(actionEvent -> openFile());
        fileMenu.getItems().add(openMenuItem);
        
        saveASMenuItem = new MenuItem("_Save AS");
        saveASMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));
        saveASMenuItem.setOnAction(actionEvent -> saveFile());
        fileMenu.getItems().add(saveASMenuItem);

 //Game Menu
        Menu gameMenu = new Menu("_Game");
        MenuItem newMenuItem = new MenuItem("_New");
        newMenuItem.setOnAction(actionEvent -> newGame());
        
        goMenuItem = new MenuItem("_Go");
        goMenuItem.setOnAction(actionEvent -> setPause());
        
        
        
        pauseMenuItem = new MenuItem("_Pause");
        pauseMenuItem.setOnAction(actionEvent -> setPause());
        pauseMenuItem.disableProperty().set(true);
        MenuItem settingsMenuItem = new MenuItem("_Settings");
        settingsMenuItem.setOnAction(actionEvent -> settingsHandler());
        
        gameMenu.getItems().addAll(newMenuItem, new SeparatorMenuItem(), goMenuItem, pauseMenuItem, smi, settingsMenuItem);
// Help menu with just an about item for now
        Menu helpMenu = new Menu("_Help");
        MenuItem aboutMenuItem = new MenuItem("_About");
        aboutMenuItem.setOnAction(actionEvent -> onAbout());
        helpMenu.getItems().add(aboutMenuItem);
        menuBar.getMenus().addAll(fileMenu, gameMenu, helpMenu);
        
        return menuBar;
    }
    
    public void setStatus(){
        mStatus.setText("Score: " + mMaze.getScore() + " Level = " + mMaze.getLevel() + " Cakes = " + mMaze.getCakeCount());
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void pacmanDirection(KeyEvent event) {
                    
                        if(event.getCode() == KeyCode.RIGHT){
                            pacman.setDirection(2);
                            
                        }
                        else if(event.getCode() == KeyCode.UP){
                            pacman.setDirection(3);
                            
                        }
                        else if(event.getCode() == KeyCode.LEFT){
                            pacman.setDirection(4);
                            
                            
                        }   
                        else if(event.getCode() == KeyCode.DOWN){
                            pacman.setDirection(5);
                            
                        }
                        else if(event.getCode() == KeyCode.HOME){
                            if(mMaze.getLevel() ==1)
                                mMaze.setMaze(3);
                            else
                                mMaze.setMaze(4 );
                        }
                        else if(event.getCode() == KeyCode.SPACE){
                            setPause();
                            
                        }
                    
    }
    
    private static void setPause() {
        if(pause == true){
            pauseScreen.clearPauseScreen();
            pause = false;
            pauseMenuItem.disableProperty().set(false);
            goMenuItem.disableProperty().set(true);
            saveASMenuItem.disableProperty().set(true);
            openMenuItem.disableProperty().set(true);
            
        }
        else{
            pause = true;
            pauseScreen.drawPaused("Paused");
            pauseMenuItem.disableProperty().set(true);
            goMenuItem.disableProperty().set(false);
            saveASMenuItem.disableProperty().set(false);
            openMenuItem.disableProperty().set(false);
        }
    }
    
    private static void pauseNewLevel(){
        pause = true;
        ghostOne.setCurPos();
        ghostOne.drawGhost(mMaze.getMap(), ghostOne.getCurPos());
        pauseScreen.drawPaused("Level 2");
        pauseMenuItem.disableProperty().set(true);
        goMenuItem.disableProperty().set(false);
    }
    
        
    private static void newGame() {
        pacman.setPacPos(new Point2D(10,12));
        
        ghostOne.setCurPos();
        ghostOne.drawGhost(mMaze.getMap(), ghostOne.getCurPos());
        mMaze.setMaze(1);
        ghostOne.setMaze(mMaze.getMap());
        mMaze.setMaze(1);
        ghostOne.setLevel(1);
        mMaze.resetLevel();
        pauseNewGame();
    }
    
    private static void pauseNewGame(){
        pause = true;
        pauseScreen.drawPaused("Press Space to Start");
        pauseMenuItem.disableProperty().set(true);
        goMenuItem.disableProperty().set(false);
        
        
    }
  
    private static void settingsHandler() {
        String[] choices = {"1", "2", "3", "4"};
        optionPane = new ChoiceDialog(mGhostNum, choices);
        optionPane.setContentText("Initial Ghosts on Level 1");
        optionPane.setTitle("Preferences");
        optionPane.setHeaderText("Ghost Preferences");
        optionPane.setGraphic(null);
        optionPane.showAndWait();
        changeGhostAmount();
        mGhostNum = Integer.parseInt((String)optionPane.getSelectedItem());
        storePreferences();
        
     
                
        
        
    }
    
    private static void changeGhostAmount(){
        int num = Integer.parseInt((String)optionPane.getSelectedItem());
        System.out.println(num);
        for(int i = 0; i < num; i++){ 
            ghosts.add(new Ghost(mMaze.getMap()));
    }
        
        for(int x = 0; x < ghosts.size(); x++){
            do{
                randomX = rand.nextInt(20);
                randomY = rand.nextInt(20);
            }while(ghostOne.checkPosition(new Point2D(randomX, randomY))== false);
            System.out.println(x);
            ghosts.get(x).drawGhost(mMaze.getMap(), new Point2D(randomX, randomY));
            //sp.getChildren().add(ghosts.get(x));
        }
        
    }
    
    private void endGame(){
        pause = true;
        pauseScreen.drawPaused("WINNER!");
        pauseMenuItem.disableProperty().set(true);
        goMenuItem.disableProperty().set(false);
    }
    
    private boolean pacGhostCollision(){
        int pacX = (int)Math.round(pacman.getCurPos().getX());
        int pacY = (int)Math.round(pacman.getCurPos().getY());
        
        int ghostOneX = (int)Math.round(ghostOne.getCurPos().getX());
        int ghostOneY = (int)Math.round(ghostOne.getCurPos().getY());
        
        if(pacX == ghostOneX && pacY ==ghostOneY){
            
            return true;
            
        }
        return false;
    }
    
     private void gameOver(){
        pause = true;
        pauseScreen.drawPaused("GAME OVER");
        pauseMenuItem.disableProperty().set(true);
        goMenuItem.disableProperty().set(false);
     }
     
     private static void openFile() {
        
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Image File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Portable Network Graphics", "*.png")
        );
        curFile = chooser.showOpenDialog(mStage);
        if (curFile != null) {
            try {
                
                
                FileInputStream fos = new FileInputStream(curFile);
                ObjectInputStream oos = new ObjectInputStream(fos);
                mMaze = (Maze) oos.readObject();
                
                oos.close();
            } catch (Exception e) {
                Logger.getLogger(Main.class.getName())
                        .log(Level.SEVERE, "Open Exception", e.getMessage());

            }
        }
     }
        
        
        
     
     
    private static void saveFile() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save Image File");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Portable Network Graphics", "*.png")
        );
        
        
        curFile = chooser.showSaveDialog(mStage);
        if(curFile != null){
            try {
                //WritableImage image = mPermCanvas.snapshot(null, null);
                //BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
                //ImageIO.write(bImage, "png", file);
                
                    FileOutputStream fos = new FileOutputStream(curFile);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(mMaze);
                    
                    oos.close();
                
            } 
            catch (Exception e) {
                Logger.getLogger(Main.class.getName())
                        .log(Level.SEVERE, "Open Exception", e.getMessage());

            }
        }
    }
    
    public static void storePreferences(){
        Preferences prefs = Preferences.userNodeForPackage(tHarrisFinalProject.class);
        prefs.putInt("mGhostNum", mGhostNum);
        
    }
    
    public static void readPreferences(){
        Preferences prefs = Preferences.userNodeForPackage(tHarrisFinalProject.class);
        mGhostNum = prefs.getInt("mGhostNum", mGhostNum);
        
        
        
    }
}
    
    
    

