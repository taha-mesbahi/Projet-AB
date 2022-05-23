package application;




import java.io.IOException;
import java.net.URL;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.mysql.cj.xdevapi.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class SignUp implements Initializable {

	@FXML
	private ComboBox<String> FiliereSelection;

	@FXML
	private TextField FirstNameField; 

	@FXML
	private TextField LastNameField;

	@FXML
	private TextField PasswordField;

	@FXML
	private TextField TelephoneField;
	
	@FXML
	private ComboBox<String>  RoleSelection;

	@FXML
	private Button SignUpButton;

	//liste contenant les donnees a afficher sur combobox
	final ObservableList<String>  listData1 = FXCollections.observableArrayList();

	final ObservableList<String>  listData2 = FXCollections.observableArrayList();


	@Override
	public void initialize(URL url, ResourceBundle rb) {     
		// Lister les elments de Combobox
		FillComboBox();
	}


	public void FillComboBox() {
		try {
			String query1 = "select NomFiliere from filiere";
			Connection cnx = mysqlconnect.getConnection();
			PreparedStatement St1= cnx.prepareStatement(query1) ; 
			ResultSet rsl1 = St1.executeQuery();

			while(rsl1.next()) {
				listData1.add(rsl1.getString("NomFiliere")); 


			}

			String query2 = "select RoleComplet from Role WHERE idRole != 1"; // pour ne pas afficher admin en inscription

			PreparedStatement St2= cnx.prepareStatement(query2) ; 
			ResultSet rsl2 = St2.executeQuery();

			while(rsl2.next()) {
				listData2.add(rsl2.getString("RoleComplet")); 


			}

		} catch(Exception e){    
			e.toString();
		}
		FiliereSelection.setItems(listData1);
		RoleSelection.setItems(listData2);

	} 
	//  METHODE POUR LES ALERTES INFOBOX
    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }


    public void SignUp() {

    	Connection conn = mysqlconnect.getConnection();
    	try { 

    		//
    		if(FiliereSelection.getValue().equals("Sic")) { System.out.println("filiere sic Home to pass on greatly detected");

    		if (RoleSelection.getValue().equals("Etudiant")) { 
    			System.out.println("Student Home to pass on greatly detected");
    			PreparedStatement ps= conn.prepareStatement("insert into comptes (PRENOM, NOM, Pssd, Telephone, IDRoleFK, IDFiliereFK) values (?,?,?,?,4,1)");
    			ps.setString(1,FirstNameField.getText());
    			ps.setString(2,LastNameField.getText());
    			ps.setString(3,PasswordField.getText());
    			ps.setString(4,TelephoneField.getText());
    			ps.execute();

    		}


    		else if(RoleSelection.getValue().equals("Coordinateur")) { 
    			PreparedStatement ps0= conn.prepareStatement("Select IdAuthen from comptes order by IdAuthen DESC limit 1");
    			ResultSet queryResult2 = ps0.executeQuery();
    			int lastId = -1;
    			while(queryResult2.next()) {
    				lastId = queryResult2.getInt(1)+1;

    			}
    			PreparedStatement ps= conn.prepareStatement("insert into comptes (PRENOM, NOM, Pssd, Telephone, IDRoleFK,IDFiliereFK, IdAuthen) values (?,?,?,?,2,1,?)");
    			System.out.println("Coordinateur Home to pass on greatly detected");
    			ps.setString(1,FirstNameField.getText());
    			ps.setString(2,LastNameField.getText());
    			ps.setString(3,PasswordField.getText());
    			ps.setString(4,TelephoneField.getText());
    			ps.setInt(5,lastId);
    			ps.execute();}


    		else {


    			PreparedStatement ps0= conn.prepareStatement("Select IdAuthen from comptes order by IdAuthen DESC limit 1");
    			ResultSet queryResult2 = ps0.executeQuery();
    			int lastId = -1;
    			while(queryResult2.next()) {
    				lastId = queryResult2.getInt(1)+1;

    			}


    			PreparedStatement ps= conn.prepareStatement("insert into comptes (PRENOM, NOM, Pssd, Telephone, IDRoleFK, IdAuthen, IDFiliereFK) values (?,?,?,?,3,?,1)");

    			System.out.println("Prof Home to pass on greatly detected");
    			ps.setString(1,FirstNameField.getText());
    			ps.setString(2,LastNameField.getText());
    			ps.setString(3,PasswordField.getText());
    			ps.setString(4,TelephoneField.getText());
    			ps.setInt(5,lastId);
    			ps.execute();

    			PreparedStatement ps1= conn.prepareStatement("insert into Professeurs_Filiere (IdProfFK,IDFiliereFK) values (?,1)");  
    			ps1.setInt(1,lastId);
    			ps1.execute();//
    		}
    		} 

    		else if (FiliereSelection.getValue().equals("Ge")) {System.out.println("filiere ge Home to pass on greatly detected");
    		PreparedStatement ps1= conn.prepareStatement("insert into comptes (IDFiliereFK) values (2)");
    		if (RoleSelection.getValue().equals("Etudiant")) { 
    			System.out.println("Student Home to pass on greatly detected");
    			PreparedStatement ps= conn.prepareStatement("insert into comptes (PRENOM, NOM, Pssd, Telephone, IDRoleFK, IDFiliereFK) values (?,?,?,?,4,2)");
    			ps.setString(1,FirstNameField.getText());
    			ps.setString(2,LastNameField.getText());
    			ps.setString(3,PasswordField.getText());
    			ps.setString(4,TelephoneField.getText());
    			ps.execute();




    		}



    		else if(RoleSelection.getValue().equals("Coordinateur")) {  
    			PreparedStatement ps0= conn.prepareStatement("Select IdAuthen from comptes order by IdAuthen DESC limit 1");
    			ResultSet queryResult2 = ps0.executeQuery();
    			int lastId = -1;
    			while(queryResult2.next()) {
    				lastId = queryResult2.getInt(1)+1;

    			}
    			PreparedStatement ps= conn.prepareStatement("insert into comptes (PRENOM, NOM, Pssd, Telephone, IDRoleFK,IDFiliereFK, IdAuthen) values (?,?,?,?,2,2,?)");
    			System.out.println("Coordinateur Home to pass on greatly detected");
    			ps.setString(1,FirstNameField.getText());
    			ps.setString(2,LastNameField.getText());
    			ps.setString(3,PasswordField.getText());
    			ps.setString(4,TelephoneField.getText());
    			ps.setInt(5,lastId);
    			ps.execute();}


    		else {
    			PreparedStatement ps0= conn.prepareStatement("Select IdAuthen from comptes order by IdAuthen DESC limit 1");
    			ResultSet queryResult2 = ps0.executeQuery();

    			int lastId = -1;

    			while(queryResult2.next()) {
    				lastId = queryResult2.getInt(1)+1;

    			}



    			PreparedStatement ps= conn.prepareStatement("insert into comptes (PRENOM, NOM, Pssd, Telephone, IDRoleFK, IdAuthen, IDFiliereFK) values (?,?,?,?,3,?,2)");

    			System.out.println("Prof Home to pass on greatly detected");
    			ps.setString(1,FirstNameField.getText());
    			ps.setString(2,LastNameField.getText());
    			ps.setString(3,PasswordField.getText());
    			ps.setString(4,TelephoneField.getText());
    			ps.setInt(5,lastId);
    			ps.execute();

    			PreparedStatement ps11= conn.prepareStatement("insert into Professeurs_Filiere (IdProfFK,IDFiliereFK) values (?,2)");  
    			ps11.setInt(1,lastId);
    			ps11.execute();//
    		}

    		}

    		else { System.out.println("filiere Gme Home to pass on greatly detected");

    		if (RoleSelection.getValue().equals("Etudiant")) { 
    			System.out.println("Student Home to pass on greatly detected");
    			PreparedStatement ps= conn.prepareStatement("insert into comptes (PRENOM, NOM, Pssd, Telephone, IDRoleFK,IDFiliereFK) values (?,?,?,?,4,3)");
    			ps.setString(1,FirstNameField.getText());
    			ps.setString(2,LastNameField.getText());
    			ps.setString(3,PasswordField.getText());
    			ps.setString(4,TelephoneField.getText());
    			ps.execute();




    		}



    		else if(RoleSelection.getValue().equals("Coordinateur")) {  
    			PreparedStatement ps0= conn.prepareStatement("Select IdAuthen from comptes order by IdAuthen DESC limit 1");
    			ResultSet queryResult2 = ps0.executeQuery();
    			int lastId = -1;
    			while(queryResult2.next()) {
    				lastId = queryResult2.getInt(1)+1;

    			}
    			PreparedStatement ps= conn.prepareStatement("insert into comptes (PRENOM, NOM, Pssd, Telephone, IDRoleFK,IDFiliereFK, IdAuthen) values (?,?,?,?,2,3,?)");
    			System.out.println("Coordinateur Home to pass on greatly detected");
    			ps.setString(1,FirstNameField.getText());
    			ps.setString(2,LastNameField.getText());
    			ps.setString(3,PasswordField.getText());
    			ps.setString(4,TelephoneField.getText());
    			ps.setInt(5,lastId);
    			ps.execute();}

    		else {
    			PreparedStatement ps0= conn.prepareStatement("Select IdAuthen from comptes order by IdAuthen DESC limit 1");
    			ResultSet queryResult2 = ps0.executeQuery();

    			int lastId = -1;

    			while(queryResult2.next()) {
    				lastId = queryResult2.getInt(1)+1;

    			}




    			PreparedStatement ps= conn.prepareStatement("insert into comptes (PRENOM, NOM, Pssd, Telephone, IDRoleFK, IdAuthen, IDFiliereFK) values (?,?,?,?,3,?,3)");

    			System.out.println("Prof Home to pass on greatly detected");
    			ps.setString(1,FirstNameField.getText());
    			ps.setString(2,LastNameField.getText());
    			ps.setString(3,PasswordField.getText());
    			ps.setString(4,TelephoneField.getText());
    			ps.setInt(5,lastId);
    			ps.execute();

    			PreparedStatement ps12= conn.prepareStatement("insert into Professeurs_Filiere (IdProfFK,IDFiliereFK) values (?,3)");  
    			ps12.setInt(1,lastId);
    			ps12.execute();//
    		}
    		}



    		String query3 = "SELECT Username FROM comptes where NOM= '" + LastNameField.getText() + "' AND PRENOM = '" + FirstNameField.getText()+ "'";
    		Connection cnx = mysqlconnect.getConnection();
    		PreparedStatement St3= cnx.prepareStatement(query3) ; 
    		ResultSet rsl3 = St3.executeQuery();


    		while(rsl3.next()){

    			infoBox("You are signed up as '" + rsl3.getString("Username") + "' Successfully", "Success", "Info");
    			System.out.println("succes inscription");


    			Main m = new Main();

    			m.changeScene("Login.fxml");

    			conn.close(); }}

    	catch (Exception e) {
    		e.printStackTrace();

    		infoBox("Error! Retry (with other parametes preferably)", "Error", "Error Message");
    	}}


		  
	public void  LogoutSwitchScene() throws IOException {
		Main signupscene = new Main();
		signupscene.changeScene("Login.fxml");
	}
	
	
}
