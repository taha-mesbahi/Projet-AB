package application;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.sql.*;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent
;

	public class LoginController {

		   @FXML
		    private TextField Usernamefield;

		    @FXML
		    private Label labelmessage;

		    @FXML
		    private Button loginbutton;

		    @FXML
		    private PasswordField passwordfield;

		    public static int idcompteconnecte;
		
		 
		@FXML
			 private void checklogin() throws IOException, InterruptedException {
			///
			
			
			if (Usernamefield.getText().isEmpty() && passwordfield.getText().isEmpty()) {
				
				labelmessage.setText("Both Username and password are empty!");
				 } 
			
			
			else{
				

	
			Connection connectDB = mysqlconnect.getConnection();


			String verifyLogin ="SELECT count(*) FROM comptes WHERE Username=? AND Pssd=?"; 
			// une seule possibilite d'unicite de resultat entre username et password
		try {
			PreparedStatement statement = connectDB.prepareStatement(verifyLogin);
			statement.setString(1,Usernamefield.getText() );
			statement.setString(2,passwordfield.getText());
			ResultSet queryResult = statement.executeQuery();

			


			while(queryResult.next()) {
				if (queryResult.getInt(1) == 1 ) {
					labelmessage.setText("Login successful,loading interface..."); 
					
			        String extractroleid = "select IDRoleFK, IdAuthen from comptes where Username=?"; 
			      
			        PreparedStatement  statement2 = connectDB.prepareStatement(extractroleid);
			     	statement2.setString(1,Usernamefield.getText() );	
					ResultSet queryResult2 = statement2.executeQuery();
					
			
					
					while(queryResult2.next()) {
						
						idcompteconnecte = queryResult2.getInt(2);
			
					 if(queryResult2.getInt(1)==1) {   
						

						Main m = new Main();

						m.changeScene("AfterLoginAdmin.fxml");
						System.out.println("admin interface accessed");

					} else if (queryResult2.getInt(1)==2) {
						Main m = new Main();

						m.changeScene("InterfaceCoordinateur1.fxml"); 
						
						System.out.println("coord interface accessed");} 
	
					
					else if (queryResult2.getInt(1)==3) { 
						Main m = new Main();

						m.changeScene("InterfaceProfesseur1.fxml"); 
						System.out.println("prof interface accessed");}
					
					else {
						
						Main m = new Main();

					m.changeScene("InterfaceEtudiant1.fxml"); 
					System.out.println("etudiant interface accessed");} 
					}
					
///        

				

				} else { labelmessage.setText("Invalid Login, please try again.");
				infoBox("Please enter correct Username and Password", "ERROR", "Failed");}
			}
		}

			
		catch (Exception e) {

			e.printStackTrace();
		}}}
		
		
		public void  SignUpSwitchScene() throws IOException {
	
			Main signupscene = new Main();
			signupscene.changeScene("SignUp.fxml");
			
		}
		
		//  METHODE POUR LES ALERTES INFOBOX
	    public static void infoBox(String infoMessage, String headerText, String title) {
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setContentText(infoMessage);
	        alert.setTitle(title);
	        alert.setHeaderText(headerText);
	        alert.showAndWait();
	    }

	 // apres la saisie de username passer au field mot de passe en cliquant au clavier sur la touche entree
	 		@FXML
	 		public void onEnterToPasswordFocus(KeyEvent e){
	 			if(e.getCode().equals(KeyCode.ENTER)) {
	 				System.out.print(" to focus");
	 				Usernamefield.setFocusTraversable(false);
	 				passwordfield.setFocusTraversable(true);
	 				passwordfield.requestFocus();
	 				
	 			}
	 			   
	 		        
	 		}
	 		
	 		// Apres la saisie de mot de pass
	 		@FXML
	 		public void onEnter(KeyEvent e) throws IOException, InterruptedException{
	 			    if(e.getCode().equals(KeyCode.ENTER))
	 			        this.checklogin();
	 			}
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}
	    
	}


		///////