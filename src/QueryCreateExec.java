import Request.User;

import java.sql.*;
import java.util.ArrayList;

public class QueryCreateExec extends Request.User {

    Connection conn;
    Statement stmt;
    public QueryCreateExec() {
        super();
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost/students?user=root&password=Dipsonu@11");

        } catch (SQLException ex) {
            // handle any errors
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
    }

    public void executeSelectMySQL(String cmd) throws SQLException {
        ResultSet rs = this.stmt.executeQuery(cmd);
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
    public void executeUpdateMySQL(String cmd) throws SQLException {
        System.out.println(this.stmt.executeUpdate(cmd));
    }

    public static void main(String[] args) {

        System.out.println("Welcome to student database\n+++++++++++++++++++++++++++");

        mainDBLoop:
        while (true) {
            try {
                QueryCreateExec obj = new QueryCreateExec();
                obj.stmt = obj.conn.createStatement();
                obj.stmt.setFetchSize(100);
                String choice = obj.requestHandler();
                switch (choice) {
                    case ".ex" -> {
                        break mainDBLoop;
                    }
                    case ".h", ".inv" -> {
                    }
                    case ".get" -> obj.executeSelectMySQL("select * from student;");

                    default -> {
                        System.out.println(choice);
                        obj.executeUpdateMySQL(choice);
                    }
                }
                if (obj.stmt != null) {
                    try {
                        obj.stmt.close();
                    } catch (SQLException sqlEx) {
                    } // ignore

                    obj.stmt = null;
                }
                obj.conn.close();
            }catch (SQLException ex) {
                // handle any errors
                System.err.println("SQLException: " + ex.getMessage());
                System.err.println("SQLState: " + ex.getSQLState());
                System.err.println("VendorError: " + ex.getErrorCode());
            }
        }
    }
}
