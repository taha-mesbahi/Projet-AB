package application;

import java.io.IOException;
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
import javafx.scene.image.ImageView;

public class InterfaceCoord2controller implements Initializable{
	
	@FXML
    private ComboBox<String> matierebox;

    @FXML
    private ComboBox<String> professeurbox;
    
    
	 
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {  //responsible for filling combobox
	 final ObservableList<String> professeurs = FXCollections.observableArrayList();
	 final ObservableList<String> matiere = FXCollections.observableArrayList();
	 try {
 
		    String query1 = "Select NomComplet from comptes where IDRoleFK=3";
		    Connection cnx = mysqlconnect.getConnection();
  			PreparedStatement St1= cnx.prepareStatement(query1) ; 
  			ResultSet rsl1 = St1.executeQuery();
  			ArrayList<Integer> idProfesseurs = new ArrayList<>();

  			while(rsl1.next()) {
  				professeurs.add( rsl1.getString("NomComplet"));
  				System.out.println("IDPROFF"+idProfesseurs+"");

  			}
  			
  			 String query2 = "Select NomMatiere from Matiere";

   			PreparedStatement St2= cnx.prepareStatement(query2) ; 
   			ResultSet rsl2 = St2.executeQuery();
   			ArrayList<Integer> idMatiere = new ArrayList<>();

   			while(rsl2.next()) {
   				matiere.add( rsl2.getString("NomMatiere"));
   				System.out.println("IdEtudiant"+idMatiere+"");

   			}
		/////
		
  			
	 } catch(Exception e){    
			e.toString();
		}
	 professeurbox.setItems(professeurs);
	 matierebox.setItems(matiere);
	 }
    

    public void  Valider() throws SQLException {

        String query10 = "Select idAuthen from comptes where idRoleFK=3 and NomComplet = ?";
        Connection cnx = mysqlconnect.getConnection();
        
			PreparedStatement St10= cnx.prepareStatement(query10) ; 
			St10.setString(1, professeurbox.getValue());
			ResultSet rsl10 = St10.executeQuery();
			int idProf = -1;

			while(rsl10.next()) {
				idProf= rsl10.getInt("idAuthen");
				System.out.println("idprof");

			}
		
			String query12 = "Select idFiliereFK from matiere where NomMatiere=?";
 	        
	          
 			PreparedStatement St12= cnx.prepareStatement(query12) ; 
 			St12.setString(1, matierebox.getValue());
 			ResultSet rsl12 = St12.executeQuery();
 			int idfiliere = -1;

 			while(rsl12.next()) {
 				idfiliere= rsl12.getInt("idFiliereFK");
 				System.out.println("IDFILIERE");

 			}
 			
 			
 			String query15 = "Select Module_idModule from matiere where NomMatiere=?";
 	        
	          
 			PreparedStatement St15= cnx.prepareStatement(query15) ; 
 			St15.setString(1, matierebox.getValue());
 			ResultSet rsl15 = St15.executeQuery();
 			int idModule = -1;

 			while(rsl15.next()) {
 				idModule= rsl15.getInt("Module_idModule");
 				System.out.println("moduleidmodyleextrait");

 			}
	 			String query13 = "Select idMatiere from Matiere where NomMatiere=?";
	 	        
	 	          
	 			PreparedStatement St13= cnx.prepareStatement(query13) ; 
	 			St13.setString(1, matierebox.getValue());
	 			ResultSet rsl13 = St13.executeQuery();
	 			int idmatiere = -1;

	 			while(rsl13.next()) {
	 				idmatiere= rsl13.getInt("idMatiere");
	 				System.out.println("IdMatiereEXTRACTED");

	 			}
	 		
		  

		  PreparedStatement ps= cnx.prepareStatement("insert into Professeurs_Matiere (idProfFK, idMatiereFK) values (?,?)");
		    ps.setInt(1, idProf);
			ps.setInt(2,idmatiere);
			ps.execute();
			
			
			 PreparedStatement ps1= cnx.prepareStatement("insert into Professeurs_Filiere (idProfFK, idFiliereFK) values (?,?)");
			    ps1.setInt(1, idProf);
				ps1.setInt(2,idfiliere);

			
		
				ps1.execute();
				
				PreparedStatement ps2= cnx.prepareStatement("insert into comptes (idFiliereFK) values (?) where idAuthen=?");
			    ps2.setInt(1, idfiliere);
			    ps2.setInt(2, idProf);
		
				ps2.execute();
	
				

				PreparedStatement ps3= cnx.prepareStatement("insert into Professeurs_Module (idProfFK, idModuleFK) values (?,?)");
			    ps3.setInt(1, idProf);
			    ps3.setInt(2, idModule);
				ps3.execute();

    }
    
    
    
    
	  public void  Logout() throws IOException {
			
			Main logout = new Main();
			logout.changeScene("Login.fxml");

			
		}
}
		 

