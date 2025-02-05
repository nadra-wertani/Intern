package Controllers;

import Models.stage_category;
import Models.sujet_stage;
import Services.Categorystageservices;
import Services.Sujetstageservices;
import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemFronts implements Initializable {

    @FXML
    private Label desclab;

    @FXML
    private Button share;

    @FXML
    private VBox itemvbox;

    @FXML
    private Label periodelab;

    @FXML
    private Label sujetlab;
    @FXML
    private AnchorPane anchro;
    @FXML
    private Label adressf;

    @FXML
    private Label techlab;
    // Autres champs et méthodes existantes
    @FXML
    private VBox vboxmap;
    @FXML
    private WebView webview;
Categorystageservices categorystageservices= new Categorystageservices();
Sujetstageservices sujetstageservices=new Sujetstageservices();
    @FXML

    void sharefb(ActionEvent event) {
        String urlToShare = "https://www.sujetstage.com/sujet?id="+sujet_stage.getId();

        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI("https://www.facebook.com/sharer/sharer.php?u=" + urlToShare));
        } catch (java.io.IOException | java.net.URISyntaxException e) {
            // Gérer l'exception, par exemple en affichant un message d'erreur
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WebEngine webEngine = webview.getEngine();
        webview.setContextMenuEnabled(false);
    }
Sujetstageservices sd=new Sujetstageservices();
    private sujet_stage sujet_stage;
    @FXML
    private Label catlab;

    private Scene scene;
    private Stage primaryStage;
    private Parent root;
    public void setdata(sujet_stage sujet_stage) throws SQLException {
        this.sujet_stage=sujet_stage;
        sujetlab.setText(sujet_stage.getSujet());
        desclab.setText(sujet_stage.getDescription());
        adressf.setText(sujet_stage.getAdress());
        techlab.setText(sujet_stage.getTechnologies());
        periodelab.setText(sujet_stage.getPeriode());
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


        MapView mapView = new MapView();
        mapView.setPrefSize(400, 400);
        mapView.setZoom(15);

        MapPoint mapPoint = new MapPoint(sujet_stage.getLatitude().doubleValue(), sujet_stage.getLongitude().doubleValue());
        mapView.flyTo(0, mapPoint, 0.1);

        // Convertir les coordonnées de latitude et longitude en x et y sur la carte
        double latitude = mapPoint.getLatitude();
        double longitude = mapPoint.getLongitude();

        double mapWidth = mapView.getWidth();
        double mapHeight = mapView.getHeight();

        double x = (longitude + 180) * (mapWidth / 360);
        double y = (90 - latitude) * (mapHeight / 180);

        // Ajouter le marqueur (cercle rouge) à la carte
        Circle marker = new Circle(5, Color.RED);
        marker.setLayoutX(x);
        marker.setLayoutY(y);

        // Ajouter le marqueur à la carte

        // Ajouter la carte à votre VBox
        vboxmap.getChildren().add(mapView);
    }



    public class CustomCircleMarkerLayer extends MapLayer {

        private final Circle circle;

        public CustomCircleMarkerLayer(Circle circle) {
            this.circle = circle;
            this.getChildren().add(circle);
        }

        @Override
        protected void layoutLayer() {
            // Pas besoin de repositionner le cercle, il est déjà positionné lors de sa création
        }
    }
}
