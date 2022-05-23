package application;

import java.io.IOException;

public class InterfaceCoord1controller {

	
	
	
	
	 public void  Interfacecoord2switchscene() throws IOException {
			
			Main signupscene = new Main();
			signupscene.changeScene("InterfaceCoordinateur2.fxml");
			
		}
	 
	 public void  Interfacecoord3switchscene() throws IOException {
			
			Main signupscene = new Main();
			signupscene.changeScene("InterfaceCoordinateur3.fxml");
			
		}
	 
	 public void  Interfacecoord4switchscene() throws IOException {
			
			Main signupscene = new Main();
			signupscene.changeScene("InterfaceCoordinateur4.fxml");
			
		}
	  
	  public void  Logout() throws IOException {
			
			Main logout = new Main();
			logout.changeScene("Login.fxml");

			
		}
}
