package Interfaces;

import Models.sujet_stage;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IservicesSujetstage<S> {
    void add (sujet_stage sujetStage);




//testwebhooks
    ArrayList<S> getAll();


    void update(sujet_stage sujet_stage) throws SQLException;

    void delete(int id) throws SQLException;

    sujet_stage getById(int id) throws SQLException;
//findby..

    //getby ...

}
