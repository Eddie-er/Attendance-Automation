package sample.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import sample.BE.Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClassesDAO implements IClassesDAO {
    private DBConnector dbConnector;

    public ClassesDAO() {
        dbConnector = new DBConnector();
    }

    @Override
    public List<Classes> getAllClasses() throws SQLException {
        Connection connection = dbConnector.getConnection();
        List<Classes> classes = new ArrayList<>();

        String query = "SELECT ClassID, ClassName, Education FROM Classes";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Classes class1 = new Classes(
                    resultSet.getInt("ClassID"),
                    resultSet.getString("ClassName"),
                    resultSet.getString("Education")
            );
            classes.add(class1);
        }
        connection.close();
        statement.close();
        resultSet.close();

        return classes;
    }
}
