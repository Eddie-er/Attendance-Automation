package sample.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {

    public void statementsVSPreparedStatements(String uname, String pw) throws Exception{
        try(Connection connection = new DBConnector().getConnection()){

        String sql = "SELECT * FROM Student WHERE Email = ? AND Password = ?";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1,uname);
            stmt.setString(2,pw);
            ResultSet rs = stmt.executeQuery();


        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    /**
     *
     * @throws Exception
     */

    private void batchSample() throws Exception{

        try(Connection connection = new DBConnector().getConnection()){
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Student VALUES (?,?)");

            for(int i = 0; i < 10000; i++){
                stmt.setString(1,"Email" + i);
                stmt.setString(2,"Password" + i);
                stmt.addBatch();
            }

            stmt.executeBatch();
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }
}
