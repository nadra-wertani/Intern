package TestFx;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFx extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

       FXMLLoader loader = new FXMLLoader(getClass().getResource("/acceuil.fxml"));
      // FXMLLoader loader = new FXMLLoader(getClass().getResource("/sujfront1.fxml"));
     //FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontcate.fxml"));
        try {

            Parent root = loader.load();
            loader.setRoot(root);
            Scene scene = new Scene(root);

            primaryStage.setTitle("gestion-sujetstage");
            primaryStage.setScene(scene);
            primaryStage.show();





            /*
             * IMPORTANT mettre la taille de la fenêtre pour éviter l'erreur
             * java.lang.OutOfMemoryError
             */



        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }}




