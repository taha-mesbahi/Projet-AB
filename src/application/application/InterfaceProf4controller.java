package application;

import java.io.IOException;



import java.net.URL;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.mysql.cj.xdevapi.Statement;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


public class InterfaceProf4controller implements Initializable {

    @FXML
    private Button afficherinfoetudiantbutton;

    @FXML
    private Button ajouter1abscencebutton;

    @FXML
    private ComboBox<String> etudiantbox;

    @FXML
    private ComboBox<String> filierebox;

    @FXML
    private ComboBox<String> matierebox;

    @FXML
    private Button modifierbutton;

    @FXML
    private ComboBox<String> modulebox;

    @FXML
    private TextField nombresabscencesfield;

    @FXML
    private TextField nombresseancesfield;

    @FXML
    private ComboBox<String> semestrebox;

    @FXML
    private ComboBox<String> typeseancebox;

    @FXML
    private Button validerbutton;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {  //responsible for filling combobox
    	final ObservableList<String> filiere = FXCollections.observableArrayList();
    	final ObservableList<String> semestre = FXCollections.observableArrayList();
    	final ObservableList<String> module = FXCollections.observableArrayList();
    	final ObservableList<String> matiere = FXCollections.observableArrayList();
    	final ObservableList<String> typeseance = FXCollections.observableArrayList();
    	final ObservableList<String> etudiants = FXCollections.observableArrayList();
    	
    	
    	System.out.print(LoginController.idcompteconnecte);

    		try {
    			
    			String query1 = "select idFiliereFK from Professeurs_filiere where IdProfFK=?";
    			Connection cnx = mysqlconnect.getConnection();
    			PreparedStatement St1= cnx.prepareStatement(query1) ; 
    			St1.setInt(1, LoginController.idcompteconnecte);
    			ResultSet rsl1 = St1.executeQuery();
    	
    			ArrayList<Integer> idFilieres = new ArrayList<>();

    			while(rsl1.next()) {
    				idFilieres.add( rsl1.getInt("idFiliereFK"));
    				System.out.println("IdFiliereFK"+idFilieres+"");

    			}
    			
    	       
    			for(int i=0; i<idFilieres.size(); i++) { 
    			String query2 = "select NomFiliere from filiere where IdFiliere = ?";
    			
    			PreparedStatement St2= cnx.prepareStatement(query2) ; 
    			St2.setInt(1,idFilieres.get(i));
    			ResultSet rsl2 = St2.executeQuery();

    			while(rsl2.next()) {
    				filiere.add(rsl2.getString("NomFiliere")); 

    				System.out.println("rah kayjib filiere mn bd");
    			}    	
    			
    			}
    			
    			String query3 = "select Nom from semestre";
    			
    			PreparedStatement St3= cnx.prepareStatement(query3) ; 
    		
    			ResultSet rsl3 = St3.executeQuery();

    			while(rsl3.next()) {
    				semestre.add(rsl3.getString("Nom"));
    				System.out.println("rah kayjib semestre mn bbdd");
    	
    	
    	 } 
                String query4 = "select IdModuleFK from Professeurs_Module where IdProfFK=?";
    			
    			PreparedStatement St4= cnx.prepareStatement(query4) ; 
    			St4.setInt(1, LoginController.idcompteconnecte);
    			ResultSet rsl4 = St4.executeQuery();
    			ArrayList<Integer> idModules = new ArrayList<>();

    			while(rsl4.next()) {
    				idModules.add( rsl4.getInt("idModuleFK"));
    				System.out.println("IdModuleFK"+idModules+"");

    			} for(int i=0; i<idModules.size(); i++) { 
        			String query5 = "select Nom from Module where idModule = ?";
        			
        			PreparedStatement St5= cnx.prepareStatement(query5) ; 
        			St5.setInt(1,idModules.get(i));
        			ResultSet rsl5 = St5.executeQuery();
        			
        			while(rsl5.next()) {
        				module.add(rsl5.getString("Nom")); 

        				System.out.println("rah kayjib module mn bd");
        			}    	}

    			
        			 String query6 = "select IdMatiereFK from Professeurs_Matiere where IdProfFK=?";
         			
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
             			}   }
         			
         			
         			String query8 = "select TypeSeance from TypeSeance";
        			
        			PreparedStatement St8= cnx.prepareStatement(query8) ; 
        		
        			ResultSet rsl8 = St8.executeQuery();

        			while(rsl8.next()) {
        				typeseance.add(rsl8.getString("TypeSeance"));
        				System.out.println("rah kayjib typeseance mn bbdd");
    		}
        			
                    String query9 = "Select NomComplet from comptes where idRoleFK=4";
           			
           			PreparedStatement St9= cnx.prepareStatement(query9) ; 
           			ResultSet rsl9 = St9.executeQuery();
           			ArrayList<Integer> idEtudiants = new ArrayList<>();

           			while(rsl9.next()) {
           				etudiants.add( rsl9.getString("NomComplet"));
           				System.out.println("IdEtudiant"+idEtudiants+"");

           			}
        			
   /*
   
                    String query9 = "select IdcompteFK from Etudiants where NomComplet =?";
                    PreparedStatement  st9 = cnx.prepareStatement(query9);
        			
                    st9.setString(1,etudiantbox.getPromptText());	
					ResultSet rsl9 = st9.executeQuery();

     			
    		
    		*/
    		
    		
        			
    		
    		} catch(Exception e){    
 			e.toString();
 		}
    		
    		filierebox.setItems(filiere);
    		semestrebox.setItems(semestre);
    		modulebox.setItems(module);
    		matierebox.setItems(matiere);
    		typeseancebox.setItems(typeseance);
    		etudiantbox.setItems(etudiants);}
    
    
    public void  Add1Abscence() throws SQLException {
		  
		
        String query10 = "Select idAuthen from comptes where idRoleFK=4 and NomComplet = ?";
        Connection cnx = mysqlconnect.getConnection();
        
			PreparedStatement St10= cnx.prepareStatement(query10) ; 
			St10.setString(1, etudiantbox.getValue());
			ResultSet rsl10 = St10.executeQuery();
			int idEtudiant = -1;

			while(rsl10.next()) {
				idEtudiant= rsl10.getInt("idAuthen");
				System.out.println("IdEtudi");

			}
		
			  String query11 = "Select idSemestre from semestre where Nom=?";
	        
	          
	 			PreparedStatement St11= cnx.prepareStatement(query11) ; 
	 			St11.setString(1, semestrebox.getValue());
	 			ResultSet rsl11 = St11.executeQuery();
	 			int idsemestre = -1;

	 			while(rsl11.next()) {
	 				idsemestre= rsl11.getInt("idSemestre");
	 				System.out.println("IdSEMSEXTRACTED");

	 			}
	 		
	 			String query12 = "Select idTypeSeance from TypeSeance where TypeSeance=?";
	 	        
	 	          
	 			PreparedStatement St12= cnx.prepareStatement(query12) ; 
	 			St12.setString(1, typeseancebox.getValue());
	 			ResultSet rsl12 = St12.executeQuery();
	 			int idtypeseance= -1;

	 			while(rsl12.next()) {
	 				idtypeseance= rsl12.getInt("idTypeSeance");
	 				System.out.println("IdSEancetypeEXTRACTED");

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
	 		
		  

		  PreparedStatement ps= cnx.prepareStatement("insert into Abscences (idEtudiantFK, idMatiereFK, idSemestreFK, idTypeSeanceFK, NombreAbscences) values (?,?,?,?,?)");
		    ps.setInt(1, idEtudiant);
		    
		    
			ps.setInt(3,idsemestre);
			ps.setInt(2,idmatiere);

			ps.setInt(4,idtypeseance);
			ps.setString(5,nombresabscencesfield.getText());
		
	
			ps.execute();
	  	
		

			
		}


	
	  public void  Interfaceprof3switchscene() throws IOException {
			
			Main signupscene = new Main();    //changer de scence 
			signupscene.changeScene("InterfaceProfesseur3.fxml");
		}
	  
	
	 
	  public void  Logout() throws IOException {
			
			Main logout = new Main();
			logout.changeScene("Login.fxml");

			
		}
}
