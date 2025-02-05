package Controllers;

import Models.stage_category;
import Models.sujet_stage;

import Models.user;
import Services.Categorystageservices;
import Services.Sujetstageservices;
import Services.Userservices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.MyDatabase;
public class Ajouts implements  Initializable {
    Sujetstageservices sd = new Sujetstageservices();



    @FXML
    private Button cler;

    @FXML
    private TextField descriptiontf;



    @FXML
    private TextField adressf;
    @FXML
    private TextField pertf;
    @FXML
    private Button retour;

    @FXML
    private Button saves;


    @FXML
    private TextField latituf;

    @FXML
    private TextField longf;
    @FXML
    private Label periodelabe;


    @FXML
    private Label sujetla;

    @FXML
    private TextField sujettf;

    @FXML
    private Label techlab;

    @FXML
    private TextField technologiestf;

    @FXML
    private Button update;


    private Scene scene;
    private Stage primaryStage;
    private Parent root;



    @FXML
    private ComboBox<String> catalog;
    @FXML
    private ComboBox<String> user;

    private Affichesu affichesuController;

    public void setAffichesuController(Affichesu affichesuController) {
        this.affichesuController = affichesuController;
    }


    @FXML
    void ajou(ActionEvent event) throws SQLException {
        String sujet=(sujettf.getText());
        String description=descriptiontf.getText();
        String technologies=technologiestf.getText();
        String periode=pertf.getText();
String adress=adressf.getText();
        BigDecimal latitude = new BigDecimal(latituf.getText());

// Récupérer la longitude à partir du champ de texte longf
        BigDecimal longitude = new BigDecimal(longf.getText());

        String selectedCategory = catalog.getValue(); // Récupérer la valeur sélectionnée dans la combobox catalog
        System.out.println("Selected category: " + selectedCategory); // Afficher la valeur sélectionnée

        if (sujet.isEmpty()) {
            showAlert("Erreur : Veuillez donner un sujet.");
        } else if (description.isEmpty()) {
            showAlert("Erreur : Veuillez donner une desc.");
        }
        else if (technologies.isEmpty()) {
            showAlert("Erreur : Veuillez donner une technologie.");
        }
        else if (periode.isEmpty()) {
            showAlert("Erreur : Veuillez donner une periode.");
        }
        else if (adress.isEmpty()) {
            showAlert("Erreur : Veuillez donner une adress.");
        }

        else  {

            Userservices se = new Userservices();
            int id_user_id = se.getIdByPrenom(user.getValue());
            if (id_user_id == -1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Erreur : cat non trouvé");
                alert.show();
                return;
            } else  {
                Categorystageservices sc = new Categorystageservices();
                int stage_category_id = sc.getIdBynom(selectedCategory); // Utiliser la valeur sélectionnée
                if (stage_category_id == -1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Erreur : Catégorie non trouvée");
                    alert.show();
                    return;
                }
                sujet_stage sujet_stage = new sujet_stage(sujet, description, technologies, periode,adress ,id_user_id, stage_category_id,latitude,longitude); // Passer les valeurs
                sd.add(sujet_stage);
                showAlert("sujet ajoutée avec succès !");

            }
        }
    }


    // Méthode pour afficher une alerte
    private void showAlert(String sujet) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(sujet);
        alert.show();
    }



    @FXML
    void anuu(ActionEvent event) {
        sujettf.setText("");
        descriptiontf.setText("");
        technologiestf.setText("");
        pertf.setText("");
        adressf.setText("");
latituf.setText("");
longf.setText("");
    }
//je faire les changement naissecaire pour categorystage et la jointure et les metires //


    @FXML
    void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Listesu.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList();
        ObservableList<String> List = FXCollections.observableArrayList();

        Categorystageservices sc = new Categorystageservices();
        Userservices se = new Userservices();
        ObservableList<stage_category> obList = FXCollections.observableArrayList();
        ObservableList<user> userList = FXCollections.observableArrayList();

        obList = sc.afficherTest2();
        userList = se.afficherTest2();
        catalog.getItems().clear();
        for (stage_category nameCat : obList) {
            list.add(nameCat.getstage_category_name());
        }
        catalog.setItems(list);

        user.getItems().clear();
        for (user nameCat : userList) {
            List.add(nameCat.getLastname());
        }
        user.setItems(List);
    }
}




