package Controllers;

import Models.sujet_stage;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;

public class Affichesu  implements Initializable {
    Sujetstageservices sd = new Sujetstageservices();
    private Scene scene;
    private Stage primaryStage;
    private Parent root;
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private CheckBox checkbox;
    @FXML
    private VBox adress;
    @FXML
    private Button ajout;

    @FXML
    private Button del;

    @FXML
    private GridPane grid;

    @FXML
    private Button mod;

    @FXML
    private Button retour;
    private ObservableList<sujet_stage> sujet_stageList;

    @FXML
    void ajout(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/newaj.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void del(ActionEvent event)throws SQLException {
        List<sujet_stage> sujetsASupprimer = new ArrayList<>();
        for (Node node : grid.getChildren()) {
            if (node instanceof AnchorPane) {
                AnchorPane anchorpane = (AnchorPane) node;
                for (Node childNode :anchorpane.getChildren()) {
                    if (childNode instanceof CheckBox) {
                        CheckBox checkbox = (CheckBox) childNode;
                        if (checkbox.isSelected()) {
                            sujet_stage sujet = sujet_stageList.get(grid.getRowIndex(anchorpane) * 3 + grid.getColumnIndex(anchorpane));
                            sujetsASupprimer.add(sujet);
                        }
                    }
                }
            }
    } for (sujet_stage sujet : sujetsASupprimer) {
            try {
                sd.delete(sujet.getId());
                sujet_stageList.remove(sujet);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        setSujetview();
    }



    @FXML
    void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/acceuil.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();}
    public  void setSujetview()   throws SQLException{
        sujet_stageList = FXCollections.observableArrayList();

        sujet_stageList = FXCollections.observableList(sd.getAll());
        int row = 0;
        int column = 0;
        grid.getRowConstraints().clear();
        grid.getColumnConstraints().clear();
        grid.getChildren().clear();
        for (int i = 0; i < sujet_stageList.size(); i++) {
            try {

                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/Item.fxml"));
                AnchorPane pane = load.load();
                Items items = load.getController();
                items.setdata(sujet_stageList.get(i));
                CheckBox checkBox = new CheckBox();
                checkBox.setOnAction(event -> {
                    if (checkBox.isSelected()) {

                    } else {

                    }
                });
                pane.getChildren().add(checkBox);
                grid.add(pane, column++, row);
                grid.setHgap(15);

                grid.setVgap(15);
                if (column == 3) {
                    column = 0;
                    row++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }}

            @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
                try {
                    setSujetview();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                }

            }
