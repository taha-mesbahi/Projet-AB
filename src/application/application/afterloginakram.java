package application;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


import javafx.scene.input.MouseEvent;


public class afterloginakram implements Initializable {
	

	 @FXML
	    private TextField txt_username;

	    @FXML
	    private TextField txt_password;

	    @FXML
	    private TextField txt_email;
	    
	    @FXML
	    private TextField txt_id;
	    
	    
	    @FXML
	    private TextField filterField; // pour la recherche 

   @FXML
   private TableColumn<users, Integer> id;

   @FXML
   private TableColumn<users, String> password;
   
   @FXML
   private TableColumn<users, String> email;

   @FXML
   private TableColumn<users, String> username;



   @FXML
   private TableView<users> table_users;


   @FXML
   void userlogout() throws IOException, SQLException {
		Main m = new Main();
     m.changeScene("Login.fxml");
     
     // fermeture connection pour optimisation consommation ressources Memoires
     String databaseName = "project";
		String databaseUser = "root";
		String databasePassword = "taharoot";
		String url = "jdbc:mysql://localhost:3306/"+ databaseName; 
		  DriverManager.getConnection(url,databaseUser,databasePassword).close();  // close() cad fermeture connection BD elle sera initieee2e si jamais l'utilis veut se reconnecter
     //
   }

	public ObservableList<users> data = FXCollections.observableArrayList();
	int index = -1;

	
	   @FXML
	    void insert(ActionEvent event) throws Exception {
		   data.clear();
		   Connection conn = mysqlconnect.getConnection();
		
		   try { 
			   PreparedStatement ps= conn.prepareStatement("insert into comptes (idAuthen, username, pssd, email) values (?,?,?,?)");
			   ps.setString(1,txt_id.getText());
			   ps.setString(2,txt_username.getText());
			   ps.setString(3,txt_password.getText());
			   ps.setString(4,txt_email.getText());
			
			   ps.execute();

			   System.out.println("executee2");

			   update_table();
	            search_user();
	    }catch(Exception e) {
			   JOptionPane.showMessageDialog(null, e);
				  System.out.println("errreur");
	    }
	   }
	   @FXML
	   void getSelected (MouseEvent event) {

		   index = table_users.getSelectionModel().getSelectedIndex();
		   if(index <= -1) {
			   return;
		   }
		   txt_id.setText(id.getCellData(index).toString());
		   txt_username.setText(username.getCellData(index).toString());
		   txt_password.setText(password.getCellData(index).toString());
		   txt_email.setText(email.getCellData(index).toString());
		

	   }
	   
	   public void Edit() {
		  
		   try {
			   Connection conn = mysqlconnect.getConnection();
			   String value1 = txt_id.getText();
			   String value2 = txt_username.getText();
			   String value3 = txt_password.getText();
			   String value4 = txt_email.getText();
			  

			   String sql = "update comptes set idAuthen= '"+value1+"',Username= '"+value2+"',pssd= '"+value3+"',email= '"+value4+"'  where idAuthen = '"+value1+"'";
			   PreparedStatement ps= conn.prepareStatement(sql);
			   ps.execute();
			   System.out.println("executee");
			   JOptionPane.showMessageDialog(null, "Update");
	            update_table();
	            search_user();
		   } catch(Exception e) {
			   JOptionPane.showMessageDialog(null, e);
			  System.out.println("errreur");
			   
		   }
		   
	   }
	   public void Delete() {

		   try {
		   Connection conn =mysqlconnect.getConnection();
		   String sql = "DELETE FROM comptes WHERE idAuthen=?";
		   
			   PreparedStatement ps = conn.prepareStatement(sql);
			   ps.setString(1, txt_id.getText());
			   ps.execute();
			   System.out.println("executee2");
			   update_table();
	            search_user();
		   } catch(Exception e) {
			   JOptionPane.showMessageDialog(null, e);
			   System.out.println("errreur");
		   }
		   
	   }
	   
	   	 
	   
	  	@FXML
		public void update_table() {   //actualiser to change name
			data.clear();

			try {
		        Connection conn = mysqlconnect.getConnection();
				String sql = "SELECT * FROM comptes";
				PreparedStatement stat = conn.prepareStatement(sql);
				ResultSet rs = stat.executeQuery();
				while (rs.next()) {    
					data.add(new users(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(6)));
					
			}
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
			id.setCellValueFactory(new PropertyValueFactory<users, Integer>("id"));
			username.setCellValueFactory(new PropertyValueFactory<users, String>("username"));
			password.setCellValueFactory(new PropertyValueFactory<users, String>("password"));
			email.setCellValueFactory(new PropertyValueFactory<users, String>("email"));
		


			table_users.setItems(data);
			
		}
	  	
	  	
	  	@FXML
	    public void search_user() {           // filtration de la recherche tableau selon username, password, mail
	  		id.setCellValueFactory(new PropertyValueFactory<users, Integer>("id"));
			username.setCellValueFactory(new PropertyValueFactory<users, String>("username"));
			password.setCellValueFactory(new PropertyValueFactory<users, String>("password"));
			email.setCellValueFactory(new PropertyValueFactory<users, String>("email"));


	    	data = mysqlconnect.getDatausers();
	    	table_users.setItems(data);
	    	
	    	FilteredList<users> filteredData = new FilteredList<>(data, b -> true);  
	    	
	    	filterField.textProperty().addListener((observable, oldValue, newValue) -> {
	    		filteredData.setPredicate(person -> {
	    			if (newValue == null || newValue.isEmpty()) {
	    				return true;
	    			}    
	    			String lowerCaseFilter = newValue.toLowerCase();

	    			if (person.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
	    				return true; // Filter matches username
	    			} else if (person.getPassword().toLowerCase().indexOf(lowerCaseFilter) != -1) {
	    				return true; // Filter matches password

	    			}
	    			else if (String.valueOf(person.getEmail()).indexOf(lowerCaseFilter)!=-1)
	    				return true;// Filter matches email

	    			else  
	    				return false; // Does not match.
	    		});
	    	});  
	    	SortedList<users> sortedData = new SortedList<>(filteredData);  
	    	sortedData.comparatorProperty().bind(table_users.comparatorProperty());  
	    	table_users.setItems(sortedData);      
	    }
	  	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		this.update_table();
		this.search_user();
	     

	
	}
}