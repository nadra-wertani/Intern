package Controllers;

import Models.sujet_stage;
import Services.Sujetstageservices;
import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.stream.Collectors;

import java.util.ArrayList;

import java.util.Collection;
import java.util.Collections;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class SujetFront implements Initializable {


        @FXML
        private AnchorPane aanchropane;
        @FXML
        private AnchorPane ancrop;

        @FXML
        private GridPane grid;
    @FXML
    private VBox adress;


        @FXML
        private Pagination pagination; // Ajoutez un composant Pagination à votre interface FXML
    @FXML
    private Button retour;
        @FXML
        private TextField searchField; // Ajoutez un champ de recherche TextField à votre interface FXML
    private Scene scene;
    private Stage primaryStage;
    private Parent root;
        private Sujetstageservices sd = new Sujetstageservices();
        private ObservableList<sujet_stage> sujet_stageList;

        private int itemsPerPage = 4; // Nombre d'éléments par page
        private FilteredList<sujet_stage> filteredData; // Liste filtrée pour la recherche
    private  ImageView mapPinImageView;
    private static final int PIN_WIDTH = 30, PIN_HEIGHT = 30;

        public void setSujetview() throws SQLException {
            sujet_stageList = FXCollections.observableArrayList();
            sujet_stageList = FXCollections.observableList(sd.getAll());
            grid.getRowConstraints().clear();
            grid.getColumnConstraints().clear();
            grid.getChildren().clear();
            // Créer une liste filtrée pour la recherche
            filteredData = new FilteredList<>(sujet_stageList, p -> true);


            // Ajouter un écouteur de changement pour mettre à jour l'affichage en fonction du filtrage et de la pagination
            filteredData.addListener((ListChangeListener.Change<? extends sujet_stage> c) -> {
                updatePagination();
                updateItems();
            });

            updatePagination();
            updateItems();
        }

        private void updatePagination() {
            pagination.setPageCount((int) Math.ceil((double) filteredData.size() / itemsPerPage));
            pagination.setCurrentPageIndex(0);
            pagination.setMaxPageIndicatorCount(3);

            pagination.currentPageIndexProperty().addListener((observable, oldValue, newValue) -> updateItems());
        }

        private void updateItems() {
            grid.getRowConstraints().clear();
            grid.getColumnConstraints().clear();
            grid.getChildren().clear();

            int row = 0;
            int column = 0;
            int page = pagination.getCurrentPageIndex();
            int startIndex = page * itemsPerPage;
            int endIndex = Math.min(startIndex + itemsPerPage, filteredData.size());

            for (int i = startIndex; i < endIndex; i++) {
                try {
                    FXMLLoader load = new FXMLLoader();
                    load.setLocation(getClass().getResource("/itmFronts.fxml"));
                    AnchorPane aancrope = load.load();
                    ItemFronts items = load.getController();
                    items.setdata(filteredData.get(i));

                    grid.add(aancrope, i % 2, i / 2);

                    grid.setHgap(15);
                    grid.setVgap(15);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    //private  MapPoint mapPoint = new MapPoint( 36.8065,  10.1815);
   // private MapView map(){
        //MapView mapView =new MapView();
       // mapView.setPrefSize(500 ,410);
      //  mapView.setZoom(15);
      //  mapView.flyTo(0,  mapPoint, 0.1);

//mapView.addLayer(new CustomCircleMarkerLayer());
        /* Ajoute l'épingle au MapLayer */
        //sujet_stage premierSujet = new sujet_stage();
        //premierSujet.setAdress("");
      //  mapView.flyTo(0,mapPoint,0.1);

        /* Déplace l'épingle selon les coordonnées du point */

      //  return mapView;
//
 //   }
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

    @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            try {
                setSujetview();
               // MapView mapview=map();
              ///  adress.getChildren().add(mapview);
              //  VBox.setVgrow(mapview , Priority.ALWAYS);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(sujet_stage -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (sujet_stage.getSujet().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }

                    // Ajoutez d'autres conditions de recherche ici

                    return false;
                });
            });
        }
   // public class CustomCircleMarkerLayer extends MapLayer {


     //   private final Circle circle;


      //  public CustomCircleMarkerLayer() {


            /* Cercle rouge de taille 5 */
        //  circle = new Circle(5, Color.RED);

            /* Ajoute le cercle au MapLayer */
           // this.getChildren().add(circle);

      //  }

        /* La fonction est appelée à chaque rafraichissement de la carte */
     //   @Override
       // protected void layoutLayer() {
            /* Conversion du MapPoint vers Point2D */
          //  Point2D point2d = this.getMapPoint(mapPoint.getLatitude(), mapPoint.getLongitude());
           // circle.setTranslateX(point2d.getX());
          //  circle.setTranslateY(point2d.getY());

       // }}


    }
