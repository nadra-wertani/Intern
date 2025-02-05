package Models;

import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class user {
    private int id;
    private String firstname;
    private String lastname;
    private int num_tel;
    private int is_active;
    private int isconnected;
    private int isemailverified;
    private String password;
    private String email;
    private String role;
    private String bio;
    private String profile;
    private String verification_token;
    private String resetpasswordcode;
    private String imageprofile;
    private String matricule;
    private LocalDate date_naissance;

    public user(){}

    public user(int id, String firstname, String lastname, int num_tel, String password, String email, String role, String verification_token, String matricule, String image_profile, LocalDate date_naissance, String bio, String profile, int is_active, int is_email_verified, int is_connected, String reset_password_code) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.num_tel = num_tel;
        this.password = password;
        this.email = email;
        this.role = role;
        this.verification_token = verification_token;
        this.resetpasswordcode = reset_password_code;
        this.imageprofile = image_profile;
        this.date_naissance = date_naissance;
        this.bio = bio;
        this.profile = profile;
        this.is_active = is_active;
        this.isconnected = is_connected;
        this.isemailverified = is_email_verified;
        this.matricule = matricule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public int getIs_connected() {
        return isconnected;
    }

    public void setIs_connected(int is_connected) {
        this.isconnected = is_connected;
    }

    public int getIs_email_verified() {
        return isemailverified;
    }

    public void setIs_email_verified(int is_email_verified) {
        this.isemailverified = is_email_verified;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getVerification_token() {
        return verification_token;
    }

    public void setVerification_token(String verification_token) {
        this.verification_token = verification_token;
    }

    public String getResetpasswordcode() {
        return resetpasswordcode;
    }

    public void setReset_password_code(String reset_password_code) {
        this.resetpasswordcode = reset_password_code;
    }

    public String getImageprofile() {
        return imageprofile;
    }

    public void setImageprofile(String imageprofile) {
        this.imageprofile = imageprofile;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public LocalDate getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", num_tel=" + num_tel +
                ", is_active=" + is_active +
                ", is_connected=" + isconnected +
                ", is_email_verified=" + isemailverified +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", bio='" + bio + '\'' +
                ", profile='" + profile + '\'' +
                ", verification_token='" + verification_token + '\'' +
                ", reset_password_code='" + resetpasswordcode + '\'' +
                ", image_profile='" + imageprofile + '\'' +
                ", matricule='" + matricule + '\'' +
                ", date_naissance=" + date_naissance +
                '}';
    }
}
