package Controllers;

import Models.stage_category;
import Models.sujet_stage;
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
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class IItemcat implements Initializable {
    @FXML
    private Label catname;
    @FXML
    private VBox itemvbox;
    @FXML
    private Label desc;
    @FXML
    private Button up;
    private Scene scene;
    private Stage primaryStage;
    private Parent root;
    private stage_category stage_category;


    // Autres champs et méthodes existantes

    private ObservableList<stage_category> obList;
    @FXML
    void update(ActionEvent event) throws SQLException, IOException {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/catmodi.fxml"));
        root = loader.load();


        // Récupérer le contrôleur de la fenêtre de modification
       modicat modicat = loader.getController();

        // Initialiser les données du contrôleur avec l'ID de la question
        modicat.initData(stage_category.getId(),stage_category.getstage_category_name(),stage_category.getdescription());
        // Remplacer le contenu de itemvbox par la fenêtre de modification
        Scene scene = new Scene(root);

        // Obtenir la fenêtre actuelle à partir de l'événement
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Définir la nouvelle scène dans la fenêtre
        stage.setScene(scene);
        stage.show();}
     catch (IOException ex) {
        ex.printStackTrace();
    }}









    // Autres champs et méthodes existantes


    public void setdata(stage_category stage_category){
        this.stage_category=stage_category;
        catname.setText(stage_category.getstage_category_name());
        desc.setText(stage_category.getdescription());




    }
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {


        }
}
