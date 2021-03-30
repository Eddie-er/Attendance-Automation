package sample.DAL;

import sample.BE.Classes;

import java.sql.SQLException;
import java.util.List;

public interface IClassesDAO {
    List<Classes> getAllClasses() throws SQLException;
}
