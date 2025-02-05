package Controllers;

import Models.stage_category;
import Models.sujet_stage;
import Services.Categorystageservices;
import Services.Sujetstageservices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CategoryFront implements Initializable{
    @FXML
    private AnchorPane anchro;

    @FXML
    private Button retour;
    @FXML
    private GridPane grid;

    private Scene scene;
    private Stage primaryStage;
    private Parent root;
    private Categorystageservices sd = new Categorystageservices();
    private ObservableList<stage_category> stage_categoryList;

    public void setCategoryView() throws SQLException {
        stage_categoryList = FXCollections.observableList(sd.getAll());

        int row = 0;
        int column = 0;
        grid.getRowConstraints().clear();
        grid.getColumnConstraints().clear();
        grid.getChildren().clear();
        for (int i = 0; i < stage_categoryList.size(); i++) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/itemcatfont.fxml"));
                AnchorPane anchorPane = loader.load();
                Itemcatfront items = loader.getController();
                items.setdata(stage_categoryList.get(i));

                grid.add(anchorPane, column++, row);
                grid.setHgap(30);
                grid.setVgap(15);
                if (column == 2) {
                    column = 0;
                    row++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void retour(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/acceuil.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @FXML
    void cherchecat(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setCategoryView();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
