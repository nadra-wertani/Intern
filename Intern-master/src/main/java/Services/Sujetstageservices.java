package Services;


import Interfaces.IservicesSujetstage;
import Models.stage_category;
import Models.sujet_stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDatabase;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sujetstageservices implements IservicesSujetstage<sujet_stage>{

    private Connection connection;

    public Sujetstageservices() {
        connection = MyDatabase.getInstance().getConnection();
    }
    ObservableList<sujet_stage> obList = FXCollections.observableArrayList();
    public void add(sujet_stage sujet_stage) {
        String qry = "INSERT INTO `sujet_stage`(`stage_category_id`,`id_user_id`,`sujet`, `technologies`, `description`, `periode`, `adress`, `latitude`, `longitude`) VALUES (?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement pstm = connection.prepareStatement(qry)) {
            pstm.setInt(1, sujet_stage.getStage_category_id());
            pstm.setInt(2, sujet_stage.getId_user_id());
            pstm.setString(3, sujet_stage.getSujet());
            pstm.setString(4, sujet_stage.getTechnologies());
            pstm.setString(5, sujet_stage.getDescription());
            pstm.setString(6, sujet_stage.getPeriode());
            pstm.setString(7, sujet_stage.getAdress());
            pstm.setBigDecimal(8, sujet_stage.getLatitude());
            pstm.setBigDecimal(9, sujet_stage.getLongitude());

            pstm.executeUpdate();
            System.out.println("Question ajoutée avec succès!");
            //notf
        } catch (SQLException ex) {
            Logger.getLogger(Sujetstageservices.class.getName()).log(Level.SEVERE, null, ex);
        }}
//

    @Override
    public ArrayList<sujet_stage> getAll() {
        ArrayList<sujet_stage> sujet_stage = new ArrayList<>();

        String qry = "SELECT * FROM sujet_stage";
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                sujet_stage p = new sujet_stage();
                p.setId(rs.getInt(1));
                p.setStage_category_id(rs.getInt(2));
                p.setId_user_id(rs.getInt(3));
                p.setSujet(rs.getString("Sujet"));
                p.setTechnologies(rs.getString("Technologies"));
                p.setDescription(rs.getString("Description"));
                p.setPeriode(rs.getString("Periode"));
                p.setAdress(rs.getString("Adress"));
                p.setLatitude(rs.getBigDecimal("Latitude"));

                p.setLongitude(rs.getBigDecimal("Longitude"));

                sujet_stage.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return sujet_stage;
    }


    @Override
    public void update(sujet_stage sujet_stage) throws SQLException {
        try{
            String sql = "update sujet_stage set stage_category_id = ?, id_user_id = ?,sujet = ?, technologies = ?," +
                    "description = ?,periode = ?,adress = ? ,latitude =? ,longitude =? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, sujet_stage.getStage_category_id());
            preparedStatement.setInt(2, sujet_stage.getId_user_id());
            preparedStatement.setString(3, sujet_stage.getSujet());
            preparedStatement.setString(4, sujet_stage.getTechnologies());
            preparedStatement.setString(5, sujet_stage.getDescription());
            preparedStatement.setString(6, sujet_stage.getPeriode());
            preparedStatement.setString(7, sujet_stage.getAdress());
            preparedStatement.setBigDecimal(8, sujet_stage.getLatitude());
            preparedStatement.setBigDecimal(9, sujet_stage.getLongitude());


            preparedStatement.setInt(10, sujet_stage.getId());
            preparedStatement.executeUpdate();
            System.out.println(sujet_stage);
        }catch (SQLException ex){  Logger.getLogger(Sujetstageservices.class.getName()).log(Level.SEVERE, null, ex);
        }}


    @Override
    public void delete(int id) throws SQLException {
        String sql = "delete from sujet_stage where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }


    @Override
    public sujet_stage getById(int id) throws SQLException {
        String sql = "SELECT `stage_category_id`,`id_user_id`,`sujet`, `technologies`, `description`, `periode`, `adress`,`latitude`,`longitude` FROM `sujet_stage` WHERE `id` = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int stage_category_id = resultSet.getInt("stage_category_id");
            int id_user_id = resultSet.getInt("id_user_id");
            String sujet= resultSet.getString("sujet");
            String technologies = resultSet.getString("technologies");
            String description = resultSet.getString("description");
            String periode = resultSet.getString("periode");
            String adress = resultSet.getString("adress");
            BigDecimal latitude=resultSet.getBigDecimal("latitude");
            BigDecimal longitude=resultSet.getBigDecimal("longitude");


            //return new Apprenant(id, name, email, password, statutNiveau, formationEtudies, niveau);
            return new sujet_stage(id,stage_category_id ,id_user_id,sujet, technologies, description,periode,adress,latitude,longitude);
        } else {
            // Handle the case when no matching record is found
            return null;
        }
    }
}