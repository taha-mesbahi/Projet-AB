package application;


import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;





public class mysqlconnect {
	public static Connection databaseLink;

// Histore de creer des fonctions a utiliser ulterieurement lors du besoin 


	public static Connection getConnection() {
		String databaseName = "project";
		String databaseUser = "root";
		String databasePassword = "taharoot";
		String url = "jdbc:mysql://localhost:3306/"+ databaseName; 



		try  {
			Class.forName("com.mysql.cj.jdbc.Driver");
			databaseLink=DriverManager.getConnection(url,databaseUser,databasePassword);
		} catch (Exception e) {
			e.printStackTrace();} 
		return databaseLink;}


// what would the following lines be? 

public static ObservableList<users> getDatausers() {

	Connection conn = getConnection();
	ObservableList<users> list = FXCollections.observableArrayList();
	try {
		PreparedStatement ps = conn.prepareStatement ("SELECT * FROM comptes") ;  // to change
		ResultSet rs = ps.executeQuery();

	
	while ( rs.next() ) {
		
		
		list.add(new users(Integer.parseInt(rs.getString("user_id")), rs.getString("username"), rs.getString("password"), rs.getString("email")));
	}

	}
		catch (Exception e) {}
			return list;
		}}