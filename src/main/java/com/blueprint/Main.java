package com.blueprint;

import com.blueprint.managers.Managers;
import com.blueprint.managers.UIManager;
import com.blueprint.user.User;
import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static java.awt.SystemColor.text;

public class Main extends Application {

    Managers managers = new Managers();
    UIManager uiManager = managers.getUIManager();


    @Override
    public void start(Stage stage){
        Group root = new Group();
        Scene scene  = new Scene(root);
        Image icon = new Image("logo.png");


        stage.setTitle("WorkoutApp");
        stage.setScene(scene);
        stage.getIcons().add(icon);
        stage.setHeight(700);
        stage.setWidth(700);
//        stage.setResizable(false);
//        stage.setX(); setY();
//        stage.setFullScreen(true);
        Text titleApp = new Text("Workout App");
        titleApp.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        // gives the width of the text
        double titleWidth = titleApp.prefWidth(-1);
        titleApp.setX((700 - titleWidth) / 2);
        titleApp.setY(80);

        root.getChildren().add(titleApp);


        stage.show();
    }


    public static void main(String[] args) {
        launch(args);

        Main main = new Main();
        // run from here
        while(true){
            main.run();
            break;
        }
    }

    public void run(){
        User user = null;
        uiManager.runMenu(user);
    }
}