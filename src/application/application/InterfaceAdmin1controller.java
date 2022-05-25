package application;

import java.io.IOException;

public class InterfaceAdmin1controller {

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	  public void  InterfaceTimetableswitchscene() throws IOException {
			
			Main signupscene = new Main();    //changer de scence 
			signupscene.changeScene("Timetable.fxml");
		}
	  
	

	  public void  InterfaceAdmin() throws IOException {
			
			Main signupscene = new Main();    //changer de scence 
			signupscene.changeScene("AfterLoginAdmin.fxml");
		}
	  
	
	
	
	  public void  Logout() throws IOException {
			
			Main logout = new Main();
			logout.changeScene("Login.fxml");

			
		}
}
