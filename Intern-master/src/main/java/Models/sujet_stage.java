package Models;

import com.gluonhq.maps.MapPoint;

import java.math.BigDecimal;

public class sujet_stage{
    private int id;
    private int stage_category_id;
    private int id_user_id;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String sujet;
    private String technologies;
    private String description;
    private String periode;

    private String adress;




    // Constructeur, getters et setters


    public sujet_stage(){}

    public sujet_stage(int id,int stage_category_id,int id_user_id,String sujet, String technologies, String description, String periode,String adress,BigDecimal latitude,BigDecimal longitude) {
        this.id = id;
        this.stage_category_id=stage_category_id;
        this.id_user_id=id_user_id;
this.longitude=longitude;
this.latitude=latitude;
        this.sujet = sujet;
        this.technologies=technologies;
        this.description=description;
        this.periode=periode;
        this.adress=adress;
    }

    public sujet_stage(String sujet, String description, String technologies, String periode,String adress, int id_user_id, int stage_category_id,BigDecimal longitude,BigDecimal latitude) {
        this.sujet = sujet;
        this.description = description;
        this.technologies = technologies;
        this.periode = periode;
        this.adress=adress;
        this.latitude=latitude;
        this.longitude=longitude;
        this.id_user_id = id_user_id;
        this.stage_category_id = stage_category_id;




     }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet =sujet;
    }
    public String getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String technologies) {
        this.technologies =technologies;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description=description;
    }
    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode =periode;
    }
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress =adress;
    }
    public int getStage_category_id() {
        return stage_category_id;
    }

    public void setStage_category_id(int stage_category_id) {
        this.stage_category_id= stage_category_id;
    }
    public int getId_user_id() {
        return id_user_id;
    }

    public void setId_user_id(int id_user_id) {
        this.id_user_id= id_user_id;
    }
    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "sujet_stage{" +
                "id=" + id +
                ", stage_category_id='" + stage_category_id+ '\'' +

                ", id_user_id='" + id_user_id + '\'' +
                ", sujet='" + sujet+ '\'' +
                ", technologies='" + technologies + '\'' +
                ", description='" + description + '\'' +
                ", periode='" + periode + '\'' +
                ", adress='" + adress + '\'' +

                ", latitude='" +latitude + '\'' +
                ", longitude='" + longitude + '\'' +

                '}';
    }
}


