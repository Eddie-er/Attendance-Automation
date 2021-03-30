package sample.BLL;

import sample.BE.Classes;
import sample.DAL.ClassesDAO;
import sample.DAL.IClassesDAO;

import java.sql.SQLException;
import java.util.List;

public class ClassesManager implements IClassesDAO {

    private IClassesDAO iClassesDAO;

    public ClassesManager() {
        iClassesDAO = (IClassesDAO) new ClassesDAO();
    }

    @Override
    public List<Classes> getAllClasses() throws SQLException {
        return iClassesDAO.getAllClasses();
    }
}
