package Controllers;

import Models.stage_category;
import Models.sujet_stage;
import Models.user;
import Services.Categorystageservices;
import Services.Sujetstageservices;
import Services.Userservices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Modisuj implements Initializable {
    private Affichesu affichesuController;

    public void setAffichesuController(Affichesu affichesuController) {
        this.affichesuController = affichesuController;
    }

    Sujetstageservices sd = new Sujetstageservices();
    private Models.sujet_stage sujet_stage;

    @FXML
    private ComboBox<String> catalog;

    @FXML
    private TextField desc;

    @FXML
    private TextField perio;
    @FXML
    private TextField adressf;

    @FXML
    private Button reto;

    @FXML
    private TextField sujettf;

    @FXML
    private TextField technol;

    @FXML
    private Button up;
    @FXML
    private TextField latituf;

    @FXML
    private TextField longf;

    @FXML
    private ComboBox<String> user;
    @FXML
    private AnchorPane np;
private int id;
    private Scene scene;
    private Stage primaryStage;
    private Parent root;
    Categorystageservices sc = new Categorystageservices();
    // Méthode pour initialiser primaryStage
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @FXML
    void reto(ActionEvent event)throws IOException {
        if (primaryStage != null) {
            primaryStage.setTitle("Votre titre");
        } else {
            System.err.println("primaryStage n'est pas initialisé.");
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Listesu.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void initData(int id,int stage_category_id,int id_user_id,String sujet, String technologies, String description, String periode,String adress) throws SQLException {
        this.id = id;

        sujettf.setText(sujet);
        technol.setText(technologies);
        desc.setText(description);
        perio.setText(periode);
        adressf.setText(adress);
        catalog.setValue(getcatById(stage_category_id));
        user.setValue(getusById(id_user_id));
    }
    private String getusById(int id_user_id) throws SQLException {
        Userservices se = new Userservices();
       user user1 = se.getById(id);
        return (user1 != null) ? user1.getLastname():null ;
    }
    private String getcatById(int stage_category_id) throws SQLException {
        Categorystageservices sc = new Categorystageservices();
       stage_category category = sc.getById(id);
        return (category != null) ? category.getstage_category_name():null ;
    }


    @FXML
    void upda(ActionEvent event) throws SQLException {


        String sujet = sujettf.getText();
        String description = desc.getText();
        String technologie = technol.getText();
        String periode = perio.getText();
String adress = adressf.getText();
        BigDecimal latitude = new BigDecimal(latituf.getText());

// Récupérer la longitude à partir du champ de texte longf
        BigDecimal longitude = new BigDecimal(longf.getText());


        if (sujet.isEmpty()) {
            showAlert("Erreur : Veuillez donner un sujet.");
        } else if (description.isEmpty()) {
            showAlert("Erreur : Veuillez donner une desc.");
        } else if (technologie.isEmpty()) {
            showAlert("Erreur : Veuillez donner une tech.");
        } else if (periode.isEmpty()) {
            showAlert("Erreur : Veuillez donner une per.");
        }
        else if (adress.isEmpty()) {
            showAlert("Erreur : Veuillez donner une add.");
        }

        else  {

            Userservices se = new Userservices();
            int id_user_id = se.getIdByPrenom(user.getValue());
            if (id_user_id == -1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Erreur : Test non trouvé");
                alert.show();

            } else  {
                Categorystageservices sc = new Categorystageservices();
                int stage_category_id = sc.getIdBynom(catalog.getValue());
                if (stage_category_id == -1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Erreur : cat non trouvé");
                    alert.show();
                    return;
                }


                // Récupérer l'identifiant de la question à partir de l'initialisation
                int sujetId = this.id; // Ajoutez cette ligne

                // Transmettre l'identifiant de la question à la méthode modifierQuestion
                sujet_stage sujet_stage = new sujet_stage(sujetId, stage_category_id, id_user_id, sujet, description, technologie, periode,adress,latitude,longitude); // Modifier cette ligne

                Sujetstageservices Sujetstageservices = new Sujetstageservices();
                Sujetstageservices.update(sujet_stage);
                showAlert(" sujet modifier ");

                // Redirection vers l'affichage des questions

            }
        }
    }
        // Méthode pour afficher une alerte

        private void showAlert (String sujet){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(sujet);
            alert.setHeaderText(null);

            alert.showAndWait();
        }
    private FXMLLoader createFXMLLoader(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlPath));
        return loader;
    }
    //ObservableList<String> list = FXCollections.observableArrayList();
    //        ServiceTest sc = new ServiceTest();
    //        ObservableList<Test> obList = FXCollections.observableArrayList();
    //        obList = sc.afficherTest2();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList();
        ObservableList<String> List = FXCollections.observableArrayList();

        Categorystageservices sc = new Categorystageservices();
        Userservices se = new Userservices();
        ObservableList<stage_category> obList = FXCollections.observableArrayList();
        obList = sc.afficherTest2();
        ObservableList<user> userList= FXCollections.observableArrayList();
       userList = se.afficherTest2();

        catalog.getItems().clear();
        user.getItems().clear();

// Ajouter les noms des catégories de stages à la liste déroulante
        for (stage_category nameCat : obList) {
            list.add(nameCat.getstage_category_name());
        }
        catalog.setItems(list);

// Ajouter les prénoms des utilisateurs à la liste déroulante
        for (user nameus : userList) {
            List.add(nameus.getLastname());
        }
        user.setItems(List);
    }}

