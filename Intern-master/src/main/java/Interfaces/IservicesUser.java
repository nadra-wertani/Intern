package Interfaces;

import Models.sujet_stage;
import Models.user;

import java.sql.SQLException;
import java.util.ArrayList;


    public interface IservicesUser <S>{
        void add (S s);
        ArrayList<S> getAll();

        void update(S s );
        boolean delete (S s);

       user getById(int id) throws SQLException;
    }

