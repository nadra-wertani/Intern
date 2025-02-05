package Controllers;

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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import  Models.stage_category;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.layout.GridPane.*;

public class Affichecat  implements Initializable {
    Categorystageservices sd = new Categorystageservices();
    private stage_category stage_category;

    @FXML
    private Button ajout;

    @FXML
    private AnchorPane anchor;

    @FXML
    private CheckBox chek;

    @FXML
    private Button det;

    @FXML
    private GridPane grid;

    @FXML
    private VBox itemvbox;

    @FXML
    private Button retour;
    private Scene scene;
    private Stage primaryStage;
    private Parent root;

    @FXML
    private Pagination pagination;
    private ObservableList<stage_category> stage_categoryList;
    private final int itemsPerPage = 9; // Nombre d'éléments par page


    public void setSujetview()  throws SQLException {


        stage_categoryList = FXCollections.observableList(sd.getAll());

        int row = 0;
        int column = 0;
        grid.getRowConstraints().clear();
        grid.getColumnConstraints().clear();
        grid.getChildren().clear();
        for (int i = 0; i <stage_categoryList.size(); i++) {
            try {

                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/Itemcat.fxml"));
                AnchorPane anchor = load.load();
                IItemcat items = load.getController();
                items.setdata(stage_categoryList.get(i));
                CheckBox chek= new CheckBox();
                chek.setOnAction(event -> {
                    if (chek.isSelected()) {

                    } else {

                    }
                });
                anchor.getChildren().add(chek);
                grid.add(anchor, column++, row);
                grid.setHgap(15);

                grid.setVgap(15);
                if (column == 2) {
                    column = 0;
                    row++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }}
    @FXML
    void Ajoutcat(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/categorystage.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    @FXML
    void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/acceuil.fxml"));
        root = loader.load();
        scene = new Scene(root);
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStage.setTitle("TANIT ONLINE");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    @FXML
    void det(ActionEvent event)throws SQLException {
        int elementsToRemove = 0; // Compteur pour suivre le nombre d'éléments à supprimer

        for (Node node : grid.getChildren()) {
            if (node instanceof AnchorPane) {
                AnchorPane anchor = (AnchorPane) node;
                Integer rowIndex = grid.getRowIndex(anchor);
                Integer columnIndex = grid.getColumnIndex(anchor);
                if (rowIndex != null && columnIndex != null) {
                    int index = rowIndex * grid.getColumnCount() + columnIndex; // Calcul de l'index
                    if (index < stage_categoryList.size()) { // Vérification si l'index est valide
                        stage_category sujetr = stage_categoryList.get(index); // Obtenir l'élément correspondant
                        try {
                            sd.delete(sujetr.getId());
                            stage_categoryList.remove(1);
                            elementsToRemove++; // Incrémenter le compteur
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
            if (elementsToRemove >= 1) { // Sortir de la boucle après avoir supprimé deux éléments
                break;
            }
        }

        setSujetview(); // Rafraîchir la vue après la suppression

    }

    @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
            try {
                setSujetview();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }


    }
