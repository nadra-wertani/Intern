package Services;

import Interfaces.IservicesCategorystage;
import Models.stage_category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;

public class Categorystageservices  implements IservicesCategorystage <stage_category> {
    private Connection connection ;

    public Categorystageservices(){
        connection = MyDatabase.getInstance().getConnection();
    }
    ObservableList<stage_category> obList = FXCollections.observableArrayList();
    @Override
    public void add(stage_category stage_category) {
        //1-req sql INSERT
        //2-executer req
        String qry ="INSERT INTO `stage_category`(`stage_category_name`,`description`) VALUES (?,?)";
        try {
            PreparedStatement pstm =connection.prepareStatement(qry);

            pstm.setString(1,stage_category.getstage_category_name());

            pstm.setString(2,stage_category.getdescription());

            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public ObservableList<stage_category> afficherTest2() {
        String sql = "SELECT * FROM stage_category";
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int id = result.getInt(1);
                String stage_category_name = result.getString(2);
                String description = result.getString(3);
                stage_category c = new stage_category(id,stage_category_name,description);
                obList.add(c);
            }
            result.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return obList;
    }
    @Override
    public ArrayList<stage_category> getAll() {
        //1-req SELECT
        //2-recuperation de la base de donné remplissage dans Array
        //3-retour du tableau done
        ArrayList<stage_category> stage_category = new ArrayList<>();
        String qry = "SELECT * FROM stage_category";
        ;
        try {

            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()){
                stage_category s = new stage_category();

                s.setstage_category_name(rs.getString("stage_category_name"));

                s.setdescription(rs.getString("description"));
                s.setId(rs.getInt("id"));

                stage_category .add(s);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return stage_category;
    }

    public void update(stage_category stage_category) throws SQLException {
        if (stage_category.getId() <= 0) {
            System.out.println("Invalid stage category ID.");
            return;
        }

        try {
            String req = "update stage_category set stage_category_name = ?, description = ? where id = ?";
            try (PreparedStatement st = connection.prepareStatement(req)) {
                st.setString(1, stage_category.getstage_category_name());
                st.setString(2, stage_category.getdescription());
                st.setInt(3, stage_category.getId());

                int rowsAffected = st.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Stage category updated successfully.");
                } else {
                    System.out.println("No stage category found with the given ID.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error updating stage category: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for detailed error information
        } catch (NullPointerException e) {
            System.out.println("Connection is not initialized: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for detailed error information
        }
    }
    @Override
    public void delete(int id) throws SQLException {
        String sql = "delete from stage_category where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();

    }
    public int getIdBynom(String stage_category_name) {
        int id = -1;
        String sql = "SELECT id FROM stage_category WHERE stage_category_name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, stage_category_name);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    id = result.getInt("id");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return id;
    }

    @Override
    public stage_category getById(int id) throws SQLException {

       stage_category stage_category = null;
        String sql = "SELECT `stage_category_name`, `description` FROM `stage_category` WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    int idc = result.getInt("id");
                    String stage_category_name=result.getString("stage_category_name");
                    String description = result.getString("description");

                    stage_category= new stage_category(id, stage_category_name, description);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return stage_category;
    }
    }

