package Controllers;

import Models.stage_category;
import Models.user;
import Services.Categorystageservices;
import Services.Sujetstageservices;
import Services.Userservices;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import Models.sujet_stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class Items {
    @FXML
    private Label desclab;

    @FXML
    private Label periodelab;

    @FXML
    private Label sujetlab;
    @FXML
    private VBox itemvbox;
    @FXML
    private Label latituf;

    @FXML
    private Label longf;

    @FXML
    private AnchorPane pane;


    @FXML
    private Label techlab;



    @FXML
    private VBox vboxmap;

    private sujet_stage sujet_stage;

    @FXML
    private Label adresslab;


    // Autres champs et méthodes existantes
    @FXML
    private Button update;
    private ObservableList<sujet_stage> sujet_stageList;

    @FXML
    void updat(ActionEvent event) throws SQLException, IOException {
        try {
            // Charger le fichier FXML de la fenêtre de modification
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/modisujet.fxml"));
            root = loader.load();

            // Récupérer le contrôleur de la fenêtre de modification
            Modisuj modisuj = loader.getController();

            // Initialiser les données du contrôleur avec l'ID de la question
            modisuj.initData(sujet_stage.getId(), sujet_stage.getStage_category_id(), sujet_stage.getId_user_id(), sujet_stage.getSujet(), sujet_stage.getDescription(), sujet_stage.getTechnologies(), sujet_stage.getPeriode(),sujet_stage.getAdress());;
            // Remplacer le contenu de itemvbox par la fenêtre de modification
            Scene scene = new Scene(root);

            // Obtenir la fenêtre actuelle à partir de l'événement
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Définir la nouvelle scène dans la fenêtre
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }}

    private Scene scene;
    private Stage primaryStage;
    private Parent root;


    @FXML
    private Label catlab;

    Categorystageservices categorystageservices= new Categorystageservices();
    Userservices userservices= new Userservices();
    Sujetstageservices sujetstageservices=new Sujetstageservices();
    public void setdata(sujet_stage sujet_stage){
        this.sujet_stage=sujet_stage;
        sujetlab.setText(sujet_stage.getSujet());
        desclab.setText(sujet_stage.getDescription());

        techlab.setText(sujet_stage.getTechnologies());
        periodelab.setText(sujet_stage.getPeriode());
        adresslab.setText(sujet_stage.getAdress());
        latituf.setText(String.valueOf(sujet_stage.getLatitude()));
        longf.setText(String.valueOf(sujet_stage.getLongitude()));
        boolean categoryFound = false;
        for (stage_category category : categorystageservices.getAll()) {
            if (category.getId() == sujet_stage.getStage_category_id()) {
                catlab.setText(category.getstage_category_name());
                categoryFound = true;
                break; // Sortir de la boucle une fois la catégorie trouvée
            }
        }

        if (!categoryFound) {
            catlab.setText("N/A"); // Ou un message alternatif si la catégorie n'est pas trouvée
        }

    }



}
