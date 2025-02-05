package Controllers;

import Models.stage_category;
import Models.sujet_stage;
import Services.Categorystageservices;
import Services.Sujetstageservices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Ajoutct implements Initializable {
    Categorystageservices sd = new Categorystageservices();

    private Affichecat affichcatController;


    public void setaffichcatController(Affichecat affichcatController) {
        this.affichcatController = affichcatController;
    }
    @FXML
    private Button retour;

    @FXML
    private TextField desciptiontf;
    @FXML
    private TextField idtf;
    @FXML
    private TextField categorynametf;


    @FXML
    private Button savetf;

    @FXML
    private Button updatetf;

    private  final ObservableList<stage_category> stage_categoryList = FXCollections.observableArrayList();



    private Scene scene;
    private Stage primaryStage;
    private Parent root;

        @FXML
    void retour(ActionEvent event) throws SQLException, IOException {


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Affichecat.fxml"));
                root =loader.load();
                scene = new Scene(root);
                primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setTitle("TANIT ONLINE");
                primaryStage.setScene(scene);
                primaryStage.show();
        }
    @FXML
    void ajou(ActionEvent event) throws SQLException {
        stage_category d = new stage_category();

        d.setstage_category_name(categorynametf.getText());
        d.setdescription(desciptiontf.getText());





        sd.add(d);


    }






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
