import java.sql.*;
import java.util.ArrayList;

public class QueryExec {

    static public void executeSelectMySQL(Statement stmt, String cmd) throws SQLException {
        ResultSet rs = stmt.executeQuery(cmd);
        ArrayList<String> columnsNam = new ArrayList<>();

        ResultSetMetaData columns = rs.getMetaData();
        int i = 0;
        System.out.println();
        while (i < columns.getColumnCount()) {
            i++;
            System.out.print(columns.getColumnName(i)+"\t");
            columnsNam.add(columns.getColumnName(i));
        }
        System.out.println();
        while(rs.next()) {
            for (String s : columnsNam) {
                System.out.print(rs.getString(s) + "\t");
            }
            System.out.println();
        }
        System.out.println();

    }
    static public void executeUpdateMySQL(Statement stmt, String cmd) throws SQLException {
        System.out.println(stmt.executeUpdate(cmd));
    }

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/students?user=root&password=Dipsonu@11");

            stmt = conn.createStatement();
            stmt.setFetchSize(100);

//            executeSelectMySQL(stmt, "SHOW DATABASES;");
//            executeUpdateMySQL(stmt, "USE students;");

            executeSelectMySQL(stmt, "select * from student;");

            conn.close();
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
    }
}
