package application;
import java.io.IOException;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 

//il faut importer classe etudiant

public class acceuil  {
public static int list = 0;
	@FXML Label titre;
	@FXML Label notee;
	@FXML Text nom;
	@FXML Text prenom;
	@FXML ComboBox cne;
	@FXML ComboBox filiere;
	@FXML ComboBox module;
	@FXML ComboBox matiere;
	@FXML TextField note;
	@FXML Pane accueil;
	@FXML Pane tp;
	@FXML Pane parent;
	// Show students list

	@FXML
	private TableColumn<Etudiant, String> nomm;

	@FXML
	private TableColumn<Etudiant, String> prenomm;

	@FXML
	private TableColumn<Etudiant, String> idEtudiant;

	@FXML
	private TableColumn<Etudiant, String> filier;

    @FXML
	private TableView <Etudiant> table;
    ObservableList<Etudiant> listEtudiantsObsrv= FXCollections.observableArrayList(
    	
    		
    		);
	String root="root";
	String dbpass="taharoot";
	
	@SuppressWarnings("unchecked")
    @FXML
	public void initialize() {
		if(list==0) {
		cne.setOnAction((event) -> {
		    int selectedIndex = cne.getSelectionModel().getSelectedIndex();
		    Object selectedItem = cne.getSelectionModel().getSelectedItem();
		   String s= (String) cne.getValue();
		    try{  
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project",root,"taharoot");  
			    PreparedStatement stmt4=con.prepareStatement("select Nom,Prenom from etudiants where idEtudiants_cne = ?"); //to check if working
			    stmt4.setString(1,s);
			    System.out.print(s);
				ResultSet rs4=stmt4.executeQuery(); 
				rs4.next();
				nom.setText(rs4.getString(1));
				prenom.setText(rs4.getString(2));
				con.close();  
			}catch(Exception e){ System.out.println(e);}  
		});
		}else {
			ObservableList<Etudiant> listEtudiantsObsrv= FXCollections.observableArrayList();
			ArrayList<Etudiant> listEtudiants= new ArrayList<>();
		
			try{  
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project",root,dbpass);  
				PreparedStatement stmt1=con.prepareStatement("select * from etudiants"); 
				ResultSet rs=stmt1.executeQuery();
				while(rs.next()) {
					Etudiant student = new Etudiant();
					student.setIdEtudiant(rs.getNString("idEtudiants_cne"));
					student.setPrenomm(rs.getNString("Prenom"));
					student.setNomm(rs.getNString("Nom"));
					student.setFilier(rs.getString("FId"));
					listEtudiants.add(student);
				}
				con.close();  
			}catch(Exception e){ System.out.println(e);} 
			for(Etudiant etudiant : listEtudiants) {
				System.out.println("nom"+ etudiant.getNomm());
			}
			listEtudiantsObsrv.addAll(listEtudiants);
			System.out.println(listEtudiantsObsrv.get(1).getIdEtudiant());
			nomm.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("nomm"));
		    prenomm.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("prenomm"));
			idEtudiant.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("idEtudiant"));
			filier.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("filier"));
			table.setItems(listEtudiantsObsrv);

		}
	}
@FXML
   private void valider(ActionEvent event)   //TO EXPLAAAAIN
    {	
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project",root,"taharoot");  
			PreparedStatement stmt1=con.prepareStatement("select idMatiere from matiere where NomMatiere =?"); 
			stmt1.setString(1,(String) matiere.getValue());
			ResultSet rs=stmt1.executeQuery();
			rs.next();
			PreparedStatement stmt=con.prepareStatement("INSERT INTO note( note, ,idmatiere) VALUES (?,?,?,?)"); // to correct 
			if(titre.getText().equals("Saisir note de TP :")) {
				stmt.setInt(1,1);
			}else {
				stmt.setInt(1,2);
			}
			stmt.setDouble(2, Double.parseDouble(note.getText()));
			stmt.setString(3,(String)cne.getValue());
			stmt.setInt(4,rs.getInt(1));
			stmt.executeUpdate();
			
			con.close();  	
	        Alert a = new Alert(AlertType.NONE,"Votre note est bien ajouté!", ButtonType.APPLY);
            a.setContentText("Votre note est bien ajouté!");
	        a.show();

			}catch(Exception e){ System.out.println(e);}  
		 
    }
	@FXML
    private void saisirnotetp(ActionEvent event)
    {
		list=0;
		for( Node node: parent.getChildren()) {

		    if( node instanceof Pane) {
		       node.setVisible(false);
		    }

		}
		tp.setVisible(true);
		tp.toFront();
		titre.setText("Saisir note de TP :");
		notee.setText("Note de TP :");
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project",root,"taharoot");  
			PreparedStatement stmt1=con.prepareStatement("select nom from module"); 
			ResultSet rs=stmt1.executeQuery();  
			PreparedStatement stmt2=con.prepareStatement("select NomFiliere from Filiere"); 
			ResultSet rs2=stmt2.executeQuery();  
			PreparedStatement stmt3=con.prepareStatement("select NomMatiere from Matiere"); 
			ResultSet rs3=stmt3.executeQuery(); 
			PreparedStatement stmt4=con.prepareStatement("select idAuthen from comptes WHERE IDRoleFK=4"); 
			ResultSet rs4=stmt4.executeQuery(); 
			ObservableList<String> datamodule = FXCollections.observableArrayList();
			ObservableList<String> datafiliere = FXCollections.observableArrayList();
			ObservableList<String> datamatiere = FXCollections.observableArrayList();
			ObservableList<String> datacne = FXCollections.observableArrayList();
			while(rs.next()) {
				datamodule.add(rs.getString(1));
			}
			module.setItems(datamodule);
			while(rs2.next()) {
				datafiliere.add(rs2.getString(1));
			}
			filiere.setItems(datafiliere);
			while(rs3.next()) {
				datamatiere.add(rs3.getString(1));
			}
			matiere.setItems(datamatiere);
			while(rs4.next()) {
				datacne.add(rs4.getString(1));
			}
			cne.setItems(datacne);
			con.close();  
			}catch(Exception e){ System.out.println(e);}  
		 
    }
	@FXML
    private void saisirnoteecrit(ActionEvent event)
    {
		list=0;

		for( Node node: parent.getChildren()) {

		    if( node instanceof Pane) {
		       node.setVisible(false);
		    }

		}
		tp.setVisible(true);
		tp.toFront();
		titre.setText("Saisir note ecrit :");
		notee.setText("Note de l'ecrit :");
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			
			String databaseName = "project";
			String databaseUser = "root";
			String databasePassword = "taharoot";
			String url = "jdbc:mysql://localhost:3306/"+ databaseName; 
			
			Connection con=DriverManager.getConnection(url,databaseUser,databasePassword);
			
		 
			PreparedStatement stmt1=con.prepareStatement("select nom from module"); 
			ResultSet rs=stmt1.executeQuery();  
			PreparedStatement stmt2=con.prepareStatement("select NomFiliere from Filiere"); 
			ResultSet rs2=stmt2.executeQuery();  
			PreparedStatement stmt3=con.prepareStatement("select NomMatiere from Matiere"); 
			ResultSet rs3=stmt3.executeQuery(); 
			PreparedStatement stmt4=con.prepareStatement("select idAuthen from comptes WHERE IDRoleFK=4"); // will engender an error bbcs table has been altered !
			ResultSet rs4=stmt4.executeQuery(); 
			ObservableList<String> datamodule = FXCollections.observableArrayList();
			ObservableList<String> datafiliere = FXCollections.observableArrayList();
			ObservableList<String> datamatiere = FXCollections.observableArrayList();
			ObservableList<String> datacne = FXCollections.observableArrayList();
			while(rs.next()) {
				datamodule.add(rs.getString(1));
			}
			module.setItems(datamodule);
			while(rs2.next()) {
				datafiliere.add(rs2.getString(1));
			}
			filiere.setItems(datafiliere);
			while(rs3.next()) {
				datamatiere.add(rs3.getString(1));
			}
			matiere.setItems(datamatiere);
			while(rs4.next()) {
				datacne.add(rs4.getString(1));
			}
			cne.setItems(datacne);
			con.close();  
			}catch(Exception e){ System.out.println(e);} 

    }
	
	@FXML
    private void afficherListe(ActionEvent event) throws IOException
    {
		list=1;
		Parent root = FXMLLoader.load(getClass().getResource("liste-etudiants.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
		stage.show();

		
    }

    
	@FXML
    private void quitter(ActionEvent event)
    {
		for( Node node: parent.getChildren()) {

		    if( node instanceof Pane) {
		       node.setVisible(false);
		    }

		}
		accueil.setVisible(true);
		accueil.toFront();
		cne.valueProperty().set(null);
		matiere.valueProperty().set(null);
		filiere.valueProperty().set(null);
		module.valueProperty().set(null);
		note.setText("");
		nom.setText("");
		prenom.setText("");
    }
	
	@FXML
	private void Retourner(ActionEvent event) throws IOException
	{
		System.out.println("retourn");
		list=0;
		Parent root = FXMLLoader.load(getClass().getResource("accueil.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
		stage.show();

	}


	
	
	 @FXML
	   void userlogout() throws IOException, SQLException {
			Main m = new Main();
	     m.changeScene("Login.fxml");
	     
	     // fermeture connection pour optimisation consommation ressources Memoires
	     String databaseName = "project";
			String databaseUser = "root";
			String databasePassword = "taharoot";
			String url = "jdbc:mysql://localhost:3306/"+ databaseName; 
			  DriverManager.getConnection(url,databaseUser,databasePassword).close();  // close() cad fermeture connection BD elle sera initieee2e si jamais l'utilis veut se reconnecter
	     //
	   }

}