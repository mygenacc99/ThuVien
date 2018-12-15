package application;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class MainApp extends Application{

    private Stage primaryStage;

    public static void main(String args[]){ launch(args);}

    @Override
    public void start( Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.getIcons().add(new Image("file:resources/images/icon.png"));
        showMenu();
    }

    public void showMenu(){
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("view/Menu/Menu.fxml"));
          //  root = loader.load(getClass().getResource("view/Category/CategoryManager.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

}