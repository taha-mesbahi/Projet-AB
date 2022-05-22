package application;

import java.io.IOException;

public class InterfaceProf4controller {

	
	
	
	  public void  Interfaceprof3switchscene() throws IOException {
			
			Main signupscene = new Main();    //changer de scence 
			signupscene.changeScene("InterfaceProfesseur3.fxml");
		}
	  
	
	 
	  public void  Logout() throws IOException {
			
			Main logout = new Main();
			logout.changeScene("Login.fxml");

			
		}
}
