package application;

import java.io.IOException;


import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class InterfaceProf1controller {
	
	
	
	  @FXML
	    private Button addmodifybutton;

	  
	  @FXML
	    private Button infosetudiants;
	  
	  @FXML
	    private Button abscences;
	  
	  
	  public void  Interfaceprof2switchscene() throws IOException {
			
			Main prof2 = new Main();    //changer de scence 
			prof2.changeScene("InterfaceProfesseur2.fxml");
		}
	  
	  
	  public void  Interfaceprof4switchscene() throws IOException {
			
			Main prof4 = new Main();    //changer de scence 
			prof4.changeScene("InterfaceProfesseur4.fxml");}
	  
	  
	  public void  Logout() throws IOException {
			
			Main logout = new Main();
			logout.changeScene("Login.fxml");

			
		}
}
