import java.sql.*;

public class backend {

    private static String password = ""; // Put your MySQL Password here, pretty sure we were required to make one when we installed workbench


    private static String username = "root"; 
    // this is default username for all but if unsure go to homepage in workbench press database on top left
    // press manage connections, then look at username


    private static String connectionString = "jdbc:mysql://localhost:3306/dbvehicleservice";
    //create a schema/database and name it dbvehicleservice then copy past my code in github to the file and execute it

    private static Connection connection;
    private static Statement command;
    // private static ResultSet data;
    //ignore this one for now

    public static void main(String[] args) {
        try {   
            connection = DriverManager.getConnection(connectionString, username, password);
            command = connection.createStatement();
            command.execute("INSERT INTO Customer_table(customer_id, vehicle_id, service_id, first_name, last_name, contact_details)"
            + " VALUES (6, 1, 2, 'Naruto', 'Uzumaki', '09123456789');");
            //this is just a sample command see if your database works
            
        } catch(SQLException e){
            e.printStackTrace(); 
        } finally {
            try {
                if (command != null) command.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
