package Interfaces;

import Models.stage_category;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IservicesCategorystage <S>{
    void add (stage_category stage_category);
    ArrayList<S> getAll();

    //void update(S s );
    // boolean delete (S s);

    void delete(int id) throws SQLException;

    stage_category getById(int id) throws SQLException;
//findby..

    //getby ...

}
