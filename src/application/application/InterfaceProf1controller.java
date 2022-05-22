package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class InterfaceProf1controller {
	
	
	
	  @FXML
	    private Button addmodifybutton;

	  
	  
	  public void  Interfaceprof2switchscene() throws IOException {
			
			Main signupscene = new Main();    //changer de scence 
			signupscene.changeScene("InterfaceProfesseur2.fxml");
			
		}

}
