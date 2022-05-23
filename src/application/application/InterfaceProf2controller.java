package application;

import java.io.IOException;



import java.net.URL;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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



public class InterfaceProf2controller implements Initializable {
	
	
	
	
	@FXML
    private Button afficherlisteetudiantsbutton;


    @FXML
    private Button afficherinfoetudiantbutton;


    @FXML
    private Button ajouterbutton;

    @FXML
    private ComboBox<?> etudiantbox;

    @FXML
    private ComboBox<String> filierebox;

    @FXML
    private ComboBox<?> matierebox;

    @FXML
    private Button modifierbutton;

    @FXML
    private ComboBox<String> modulebox;

    @FXML
    private TextField notecontrolefield;

    @FXML
    private TextField noteexamenfield;

    @FXML
    private TextField notetpfield;

    @FXML
    private ComboBox<String> semestrebox;

    @FXML
    private ComboBox<?> sessionbox;

    @FXML
    private Button validerbutton;
    
    
	@Override
    public void initialize(URL arg0, ResourceBundle arg1) {  //responsible for filling combobox
    	final ObservableList<String> filiere1 = FXCollections.observableArrayList();
    	final ObservableList<String> semestre1 = FXCollections.observableArrayList();
    	final ObservableList<String> module = FXCollections.observableArrayList();
    	
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
    				filiere1.add(rsl2.getString("NomFiliere")); 

    				System.out.println("rah kayjib filiere mn bbdd");
    			}    	}
    			
    			///
    			
        			String query3 = "select Nom from semestre";
        			
        			PreparedStatement St3= cnx.prepareStatement(query3) ; 
        		
        			ResultSet rsl3 = St3.executeQuery();

        			while(rsl3.next()) {
        				semestre1.add(rsl3.getString("Nom")); 

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
            			}   } 	
    			
    			///
    			
    			
    			
    				
    	
    	
    	 } catch(Exception e){    
 			e.toString();
 		}
    		
    		filierebox.setItems(filiere1);
    		semestrebox.setItems(semestre1);
    		modulebox.setItems(module);}

	 
	  public void  Interfaceprof3switchscene() throws IOException {
			
			Main signupscene = new Main();
			signupscene.changeScene("InterfaceProfesseur3.fxml");
			
		}
	  
	  public void  Logout() throws IOException {
			
			Main logout = new Main();
			logout.changeScene("Login.fxml");

			
		}


	
}
