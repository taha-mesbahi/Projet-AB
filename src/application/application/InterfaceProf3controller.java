package application;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;




public class InterfaceProf3controller implements Initializable{
	final ObservableList<String> etudiants = FXCollections.observableArrayList();
	   public static int idetudianthighlighted;
	   public static String numtelp;
	   public static String numtelpw;
	@FXML
    private ComboBox<String> etudiantbox;
	 @FXML
	    private TextField nometudiantfield;

	    @FXML
	    private TextField numtelparent;
		@Override
	    public void initialize(URL arg0, ResourceBundle arg1) { 
	try {
	    String query9 = "Select NomComplet from comptes where idRoleFK=4";
	    Connection cnx = mysqlconnect.getConnection();
			PreparedStatement St9= cnx.prepareStatement(query9) ; 
			ResultSet rsl9 = St9.executeQuery();
		

			while(rsl9.next()) {
				etudiants.add( rsl9.getString("NomComplet"));
				

			}
		
	//
	
		//


	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} etudiantbox.setItems(etudiants);
	
		}
	  public void  Logout() throws IOException {
			
			Main logout = new Main();
			logout.changeScene("Login.fxml");

			
		}
	  
	  public void  ok() throws IOException {
			try {  String query101 = "Select idAuthen from comptes where NomComplet =?";
		  Connection cnx = mysqlconnect.getConnection();
	        
			PreparedStatement St101= cnx.prepareStatement(query101) ; 
			St101.setString(1, etudiantbox.getValue());
			ResultSet rsl101 = St101.executeQuery();


			while(rsl101.next()) {
				idetudianthighlighted= rsl101.getInt("idAuthen");
				System.out.println(idetudianthighlighted);

			}
	String query1 = "select NumParent from comptes where idAuthen=?";
	
	PreparedStatement St1;

		St1 = cnx.prepareStatement(query1);

	St1.setInt(1, idetudianthighlighted);
	ResultSet rsl1 = St1.executeQuery();
	
	String Numerotelephoneparent;
	while(rsl1.next()) {
		Numerotelephoneparent= rsl1.getString("NumParent");
		System.out.println("num tel extracted successfuly");
		System.out.println(Numerotelephoneparent);
		
numtelp = Numerotelephoneparent;
		
	}}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();}
		  numtelparent.setText(numtelp);
			
			
		}
	  public void  contact() throws URISyntaxException, IOException {

		 try {  String query111 = "select WhatsappLinkNumParent from comptes where idAuthen=?";
			
			PreparedStatement St111;
			  Connection cnx = mysqlconnect.getConnection();
		        
				St111 = cnx.prepareStatement(query111);

			St111.setInt(1, idetudianthighlighted);
			ResultSet rsl111 = St111.executeQuery();
			
			String link;
			while(rsl111.next()) {
				link= rsl111.getString("WhatsappLinkNumParent");
				System.out.println("whatsapp link tel extracted successfuly");
				System.out.println(link);
				
		numtelpw = link;
				
			}}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();}
				 
			Desktop.getDesktop().browse(new URI(numtelpw));
			
				  System.out.println("whatsapp methode detected");
				}
		  
		  
			
	
	  }
	




