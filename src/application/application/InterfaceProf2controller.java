package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class InterfaceProf2controller {
	
	
	@FXML
    private Button afficherlisteetudiantsbutton;

	
	 
	  public void  Interfaceprof3switchscene() throws IOException {
			
			Main signupscene = new Main();
			signupscene.changeScene("InterfaceProfesseur3.fxml");
			
		}
}
