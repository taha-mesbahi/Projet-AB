package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class InterfaceCoord3controller implements Initializable{

	 @FXML
	    private ComboBox<String> matierebox;
	
	
	
	 public void  Logout() throws IOException {
			
			Main logout = new Main();
			logout.changeScene("Login.fxml");}
	 
	 
	 
	 @Override
	    public void initialize(URL arg0, ResourceBundle arg1) {  //responsible for filling combobox
		 final ObservableList<String> matiere = FXCollections.observableArrayList();
		 try {
	 
			String query6 = "select IdMatiereFK from Coordinateurs_Matiere where IdCoordinateurFK=?";
			Connection cnx = mysqlconnect.getConnection();
 			PreparedStatement St6= cnx.prepareStatement(query6) ; 
 			St6.setInt(1, LoginController.idcompteconnecte);
 			ResultSet rsl6 = St6.executeQuery();
 			ArrayList<Integer> idMatieres = new ArrayList<>();

 			while(rsl6.next()) {
 				idMatieres.add( rsl6.getInt("idMatiereFK"));
 				System.out.println("IdMatiereFK"+idMatieres+"");

 			} for(int i=0; i<idMatieres.size(); i++) { 
     			String query7 = "select NomMatiere from Matiere where idMatiere = ?";
     			
     			PreparedStatement St7= cnx.prepareStatement(query7) ; 
     			St7.setInt(1,idMatieres.get(i));
     			ResultSet rsl7 = St7.executeQuery();
     			
     			while(rsl7.next()) {
     				matiere.add(rsl7.getString("NomMatiere")); 

     				System.out.println("rah kayjib matiere mn bd");
     			} } }
		
 			catch(Exception e){    
 	 			e.toString();
 	 		}
 	    		
 	    		
 	    		matierebox.setItems(matiere);
 	    		}
}
