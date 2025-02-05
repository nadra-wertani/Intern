package Controllers;

import Models.stage_category;
import Services.Categorystageservices;
import Services.Sujetstageservices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class modicat implements Initializable {
    @FXML
    private AnchorPane anchor;

    @FXML
    private TextField catname;

    @FXML
    private TextField desc;

    private int Id; // Nouvelle variable pour stocker l'ID
    @FXML
    private Button up;
    @FXML
    private Button retour;
    private Scene scene;
    private Stage primaryStage;
    private Parent root;
    @FXML
    void retour(ActionEvent event) {
        try {
            FXMLLoader loader = createFXMLLoader("/Affichecat.fxml");
            root = loader.load();
            anchor.getChildren().setAll(root);
        } catch (IOException ex) {
            System.out.println("Erreur lors du chargement de la vue : " + ex.getMessage());
        }
    }


    // Nouvelle méthode pour initialiser l'ID
    public void initData(int id, String stage_category_name, String description) {
        this.Id = id;
        catname.setText(stage_category_name);
        desc.setText(description);

    }

    @FXML
    void up(ActionEvent event) throws SQLException {
        Categorystageservices inter = new  Categorystageservices();
        String stage_category_name = catname.getText();
        String description = desc.getText();

        if (stage_category_name.isEmpty()) {
            showAlert("Erreur : Veuillez donner un titre de test.");
        } else if (description.isEmpty()) {
            showAlert("Erreur : Veuillez donner une description de test.");
        }
        int CatId = this.Id;
            stage_category stage_category= new stage_category(CatId,stage_category_name, description);
            inter.update(stage_category);
            showAlert("category modifié avec succès !");

            // Redirection vers l'affichage des tests
            // redirectToAfficherTest();

    }



    private FXMLLoader createFXMLLoader(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlPath));
        return loader;
    }



    // Méthode pour afficher une alerte
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}
    // Méthode pour rediriger vers l'affichage des tests




}
