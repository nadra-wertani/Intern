package Services;

import Interfaces.IservicesUser;
import Models.stage_category;
import Models.user;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDatabase;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Userservices implements IservicesUser<user> {
    private Connection connection;

    public Userservices() {
        connection = MyDatabase.getInstance().getConnection();
    }
    ObservableList<user> List = FXCollections.observableArrayList();
    @Override
    public void add(user user) {
        String qry = "INSERT INTO `user`(`firstname`, `lastname`, `email`, `num_tel`, `password`, `is_active`, `matricule`, `verification_token`, `date_naissance`, `isemailverified`, `resetpasswordcode`, `role`, `isconnected`, `bio`, `profile`, `imageprofile`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstm = connection.prepareStatement(qry);
            pstm.setString(1, user.getFirstname());
            pstm.setString(2, user.getLastname());
            pstm.setString(3, user.getEmail());
            pstm.setInt(4, user.getNum_tel());
            pstm.setString(5, user.getPassword());
            pstm.setInt(6, user.getIs_active());
            pstm.setString(7, user.getMatricule());
            pstm.setString(8, user.getVerification_token());
            pstm.setObject(9, user.getDate_naissance());
            pstm.setInt(10, user.getIs_email_verified());
            pstm.setString(11, user.getResetpasswordcode());
            pstm.setString(12, user.getRole());
            pstm.setInt(13, user.getIs_connected());
            pstm.setString(14, user.getBio());
            pstm.setString(15, user.getProfile());
            pstm.setString(16, user.getImageprofile());

            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<user> getAll() {
        ArrayList<user> userList = new ArrayList<>();
        String qry = "SELECT * FROM user";
        try {
            if (connection != null) {
                Statement stm = connection.createStatement();
                ResultSet rs = stm.executeQuery(qry);
                while (rs.next()) {
                    user p = new user();
                    p.setId(rs.getInt("id"));
                    p.setFirstname(rs.getString("firstname"));
                    p.setLastname(rs.getString("lastname"));
                    p.setEmail(rs.getString("email"));
                    p.setNum_tel(rs.getInt("num_tel"));
                    p.setPassword(rs.getString("password"));
                    p.setIs_active(rs.getInt("is_active"));
                    p.setMatricule(rs.getString("matricule"));
                    p.setVerification_token(rs.getString("verification_token"));
                    p.setDate_naissance(rs.getObject("date_naissance", LocalDate.class));
                    p.setIs_email_verified(rs.getInt("isemailverified"));
                    p.setReset_password_code(rs.getString("resetpasswordcode"));
                    p.setRole(rs.getString("role"));
                    p.setIs_connected(rs.getInt("isconnected"));
                    p.setBio(rs.getString("bio"));
                    p.setProfile(rs.getString("profile"));
                    p.setImageprofile(rs.getString("imageprofile"));

                    userList.add(p);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return userList;
    }

    @Override
    public void update(user user) {
        // Implémenter la logique de mise à jour
    }

    @Override
    public boolean delete(user user) {
        // Implémenter la logique de suppression
        return false;
    }

    public int getIdByPrenom(String lastname) {
        int id = -1;
        String sql = "SELECT id FROM user WHERE lastname= ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,lastname);
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

    public ObservableList<user> afficherTest2() {
        ObservableList<user> userList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM user";
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                int id = result.getInt("id");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String num_tel = result.getString("num_tel");
                String password = result.getString("password");
                String email = result.getString("email");
                String role = result.getString("role");
                String verification_token = result.getString("verification_token");
                String matricule = result.getString("matricule");
                String image_profile = result.getString("imageprofile");
                LocalDate date_naissance = result.getObject("date_naissance", LocalDate.class);
                String bio = result.getString("bio");
                String profile = result.getString("profile");
                int is_active = result.getInt("is_active");
                int is_email_verified = result.getInt("isemailverified");
                int is_connected = result.getInt("isconnected");
                String resetpasswordcode = result.getString("resetpasswordcode");

                user c = new user(id, firstname, lastname, Integer.parseInt(num_tel), password, email, role, verification_token, matricule, image_profile, date_naissance, bio, profile, is_active, is_email_verified, is_connected, resetpasswordcode);
                userList.add(c);
            }
            result.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return userList;
    }



    @Override
    public user getById(int id) throws SQLException {
        String sql = "SELECT * FROM `user` WHERE `id` = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            user u = new user();
            u.setId(resultSet.getInt("id"));
            u.setFirstname(resultSet.getString("firstname"));
            u.setLastname(resultSet.getString("lastname"));
            u.setEmail(resultSet.getString("email"));
            u.setNum_tel(resultSet.getInt("num_tel"));
            u.setPassword(resultSet.getString("password"));
            u.setIs_active(resultSet.getInt("is_active"));
            u.setMatricule(resultSet.getString("matricule"));
            u.setVerification_token(resultSet.getString("verification_token"));
            u.setDate_naissance(resultSet.getObject("date_naissance", LocalDate.class));
            u.setIs_email_verified(resultSet.getInt("isemailverified"));
            u.setReset_password_code(resultSet.getString("resetpasswordcode"));
            u.setRole(resultSet.getString("role"));
            u.setIs_connected(resultSet.getInt("isconnected"));
            u.setBio(resultSet.getString("bio"));
            u.setProfile(resultSet.getString("profile"));
            u.setImageprofile(resultSet.getString("imageprofile"));
            return u;
        } else {
            return null;
        }
    }
}
