import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DB {

    private Connection conn = null;

    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://db.bucomputing.uk:6612"
            + "?verifyServerCertificate=false" +
            "&useSSL=true" +
            "&requireSSL=true";

    private final String USER = "s5108260";
    private final String PASS = "SimpRoot42069";


    public DB() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("connecting to database ...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("connection made");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
    }





    public int createTable() {
        try {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE s5108260.REGISTRATION " +
                    "(id INTEGER not NULL, " +
                    " first VARCHAR(255), " +
                    " last VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";


            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");

        } catch (SQLException e) {
            e.printStackTrace();


        }
        return 0;
    }



    public int insert(){

        try{
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO  s5108260.tbl_account(account_fname, account_sname, account_email, account_password,account_salt,account_ispremium)"+
                    "VALUES ('Jim','peter','peter232@live.co.uk','abcd123','34353631',1)";

            stmt.executeUpdate(sql);
            System.out.println("Record Added ...");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static void main(String[] args){

        DB db = new DB();
        db.insert();

    }

}


