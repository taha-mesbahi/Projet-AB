package application;

import java.io.FileOutputStream;
import java.io.IOException;



import java.net.URL;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
    private ComboBox<String> etudiantbox;

    @FXML
    private ComboBox<String> filierebox;

    @FXML
    private ComboBox<String> matierebox;

    @FXML
    private Button exportpdfbutton;

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
    private ComboBox<String> sessionbox;

    @FXML
    private Button validerbutton;
    public static int idetudianthighlighted;
	
    
	@Override
    public void initialize(URL arg0, ResourceBundle arg1) {  //responsible for filling combobox
    	final ObservableList<String> filiere1 = FXCollections.observableArrayList();
    	final ObservableList<String> semestre1 = FXCollections.observableArrayList();
    	final ObservableList<String> module = FXCollections.observableArrayList();
    	final ObservableList<String> matiere = FXCollections.observableArrayList();
    	final ObservableList<String> session = FXCollections.observableArrayList();
    	final ObservableList<String> etudiants = FXCollections.observableArrayList();
    	
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
    			
        			
                 String query6 = "select IdMatiereFK from Professeurs_Matiere where IdProfFK=?";
        			
        			PreparedStatement St6= cnx.prepareStatement(query6) ; 
        			St6.setInt(1, LoginController.idcompteconnecte);
        			ResultSet rsl6 = St6.executeQuery();
        			ArrayList<Integer> idMatieres = new ArrayList<>();

        			while(rsl6.next()) {
        				idMatieres.add( rsl6.getInt("idMatiereFK"));
        				System.out.println("IdMatiereFK"+idMatieres+"");

        			} for(int i=0; i<idMatieres.size(); i++) { 
            			String query7 = "select NomMatiere from Matiere where idMatiere = ?";
            			
            			PreparedStatement St7= cnx.prepareStatement(query7) ; 
            			St7.setInt(1,idMatieres.get(i));
            			ResultSet rsl7 = St7.executeQuery();
            			
            			while(rsl7.next()) {
            				matiere.add(rsl7.getString("NomMatiere")); 

            				System.out.println("rah kayjib matiere mn bd");
            			}   } 	
    			

         			String query8 = "select Session from Session";
        			
        			PreparedStatement St8= cnx.prepareStatement(query8) ; 
        		
        			ResultSet rsl8 = St8.executeQuery();

        			while(rsl8.next()) {
        				session.add(rsl8.getString("Session"));
        				System.out.println("rah kayjib session mn bbdd");
     			
        			}
        			
        			
                    String query9 = "Select NomComplet from comptes where idRoleFK=4";
           			
           			PreparedStatement St9= cnx.prepareStatement(query9) ; 
           			ResultSet rsl9 = St9.executeQuery();
           			ArrayList<Integer> idEtudiants = new ArrayList<>();

           			while(rsl9.next()) {
           				etudiants.add( rsl9.getString("NomComplet"));
           				System.out.println("IdEtudiant"+idEtudiants+"");
   
           			}
        			
           		   
           			
           			
        			
    			///
    			
    			
    			
    				
    	
    	
    	 } catch(Exception e){    
 			e.toString();
 		}
    		
    		filierebox.setItems(filiere1);
    		semestrebox.setItems(semestre1);
    		modulebox.setItems(module);
    		matierebox.setItems(matiere);
    		sessionbox.setItems(session);
    		etudiantbox.setItems(etudiants);}

	 
	  public void  Interfaceprof3switchscene() throws IOException {
			
			Main signupscene = new Main();
			signupscene.changeScene("InterfaceProfesseur3.fxml");
			
		}
	  
	  public void  Logout() throws IOException {
			
			Main logout = new Main();
			logout.changeScene("Login.fxml");

			
		}
	  
	  
	  public void  AddNote() throws SQLException {
		  
			
          String query10 = "Select idAuthen from comptes where idRoleFK=4 and NomComplet = ?";
          Connection cnx = mysqlconnect.getConnection();
          
 			PreparedStatement St10= cnx.prepareStatement(query10) ; 
 			St10.setString(1, etudiantbox.getValue());
 			ResultSet rsl10 = St10.executeQuery();
 			int idEtudiant = -1;

 			while(rsl10.next()) {
 				idEtudiant= rsl10.getInt("idAuthen");
 				System.out.println("IdEtudi");

 			}
 		
 			  String query11 = "Select idSemestre from semestre where Nom=?";
 	        
 	          
 	 			PreparedStatement St11= cnx.prepareStatement(query11) ; 
 	 			St11.setString(1, semestrebox.getValue());
 	 			ResultSet rsl11 = St11.executeQuery();
 	 			int idsemestre = -1;

 	 			while(rsl11.next()) {
 	 				idsemestre= rsl11.getInt("idSemestre");
 	 				System.out.println("IdSEMSEXTRACTED");

 	 			}
 	 		
 	 			String query12 = "Select idSession from Session where Session=?";
 	 	        
 	 	          
 	 			PreparedStatement St12= cnx.prepareStatement(query12) ; 
 	 			St12.setString(1, sessionbox.getValue());
 	 			ResultSet rsl12 = St12.executeQuery();
 	 			int idsession = -1;

 	 			while(rsl12.next()) {
 	 				idsession= rsl12.getInt("idSession");
 	 				System.out.println("IdSEssEXTRACTED");

 	 			}
 	 		
 	 			String query13 = "Select idMatiere from Matiere where NomMatiere=?";
 	 	        
	 	          
 	 			PreparedStatement St13= cnx.prepareStatement(query13) ; 
 	 			St13.setString(1, matierebox.getValue());
 	 			ResultSet rsl13 = St13.executeQuery();
 	 			int idmatiere = -1;

 	 			while(rsl13.next()) {
 	 				idmatiere= rsl13.getInt("idMatiere");
 	 				System.out.println("IdMatiereEXTRACTED");

 	 			}
 	 		
 	 			
       			

		  PreparedStatement ps= cnx.prepareStatement("insert into NotesEtudiants (idcompteEtudiant, SemestreId, SessionId, idMatiere, NoteCC, NoteTP, NoteExamen) values (?,?,?,?,?,?,?)");
		    ps.setInt(1, idEtudiant);
		    
		    
			ps.setInt(2,idsemestre);
		    ps.setInt(3,idsession);
			ps.setInt(4,idmatiere);

		    
		    
			ps.setString(5,notecontrolefield.getText());
			ps.setString(6,notetpfield.getText());
			ps.setString(7,noteexamenfield.getText());
	
			ps.execute();
	  	
		

			
		}
	 
	  

	  private static String FILE = "/Users/macbook/Downloads/test.pdf";
	    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
	            Font.BOLD);
	    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	            Font.NORMAL, BaseColor.RED);
	    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
	            Font.BOLD);
	    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
	            Font.BOLD);
	  public void exportData() throws SQLException {
			Connection cnx = mysqlconnect.getConnection();

	    	
	    	String query2 = "Select idAuthen from comptes where idRoleFK=4 and NomComplet=?";
	   		PreparedStatement St2= cnx.prepareStatement(query2) ; 
	   		St2.setString(1, etudiantbox.getValue());
			ResultSet rsl2 = St2.executeQuery();
			int idEtudiant = -1;

			while(rsl2.next()) {
					idEtudiant = rsl2.getInt("idAuthen");
					System.out.println("IdEtudiant");
			}
			
	    	
	    	String query5 = "select * from NotesEtudiants where idcompteEtudiant = ?";
			PreparedStatement St5= cnx.prepareStatement(query5) ; 

			St5.setInt(1,idEtudiant);
			ResultSet rsl5 = St5.executeQuery();
			
			ArrayList<String> data = new ArrayList<>();
			
			while(rsl5.next()) {
				data.add(rsl5.getString("idNotesEtudiants")); 
				String query3= "Select NomMatiere from matiere where idmatiere=?";
		   		PreparedStatement St3= cnx.prepareStatement(query3) ; 
		   		St3.setInt(1, rsl5.getInt("idMatiere"));
				ResultSet rsl3 = St3.executeQuery();
				String NomMatiere = "";

				while(rsl3.next()) {
					    NomMatiere = rsl3.getString("NomMatiere");
						System.out.println("IdEtudiant");
				}
				
				data.add(NomMatiere);
				data.add(String.valueOf( rsl5.getFloat("NoteCC")));
				data.add(String.valueOf( rsl5.getFloat("NoteTP")));
				data.add(String.valueOf( rsl5.getFloat("NoteExamen")));


				System.out.println("rah kayjib module mn bdjjj");
			}   
			
			cnx.close();
			
			for(int i=0;i< data.size();i++ ) {
				System.out.println("data"+data.get(i));
			}

	    	
	        try {
	            Document document = new Document();
	            PdfWriter.getInstance(document, new FileOutputStream(FILE));
	            document.open();
	            addMetaData(document);
	    		
	    	/*	dataa.add(1);
	    		dataa.add("java");
	    		dataa.add(18);
	    		dataa.add(2);
	    		dataa.add("javccda");
	    		dataa.add(12);

	    		dataa.add(2);
	    		dataa.add("javaccc");
	    		dataa.add(13);  */


	            addContent(document,data, etudiantbox.getValue());
	            document.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	  }
	  
	  // iText allows to add metadata to the PDF which can be viewed in your Adobe
	    // Reader
	    // under File -> Properties
	    private static void addMetaData(Document document) {
	        document.addTitle("Our first PDF");
	        document.addSubject("Using iText");
	        document.addKeywords("Java, PDF, iText");
	        document.addAuthor("Taha Mesbahi & Saad Layachi");
	        document.addCreator("Taha Mesbahi & Saad Layachi");
	    }


	    private static void addContent(Document document, ArrayList students, String name) throws DocumentException {
	        Anchor anchor = new Anchor(name, catFont);
	        anchor.setName("Name student");

	        // Second parameter is the number of the chapter
	        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

	        Paragraph subPara = new Paragraph("", subFont);
	        Section subCatPart = catPart.addSection(subPara);
	 

	        addEmptyLine(subPara, 8);

	        // add a table
	        
	        createTable(subCatPart, students);

	        // now add all this to the document
	        document.add(catPart);


	    }

	    private static void createTable(Section subCatPart, ArrayList mydata)
	            throws BadElementException {
	        PdfPTable table = new PdfPTable(5);

	        // t.setBorderColor(BaseColor.GRAY);
	        // t.setPadding(4);
	        // t.setSpacing(4);
	        // t.setBorderWidth(1);

	        PdfPCell c1 = new PdfPCell(new Phrase("id"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);

	        c1 = new PdfPCell(new Phrase("matiere"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);

	        c1 = new PdfPCell(new Phrase("note CC"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);
	        c1 = new PdfPCell(new Phrase("note Tp"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);
	        c1 = new PdfPCell(new Phrase("note Exam"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(c1);
	       
	       
	        table.setHeaderRows(1);

	        for(int i = 0 ; i < mydata.size();i++) {
	        table.addCell(mydata.get(i).toString());
	        }

	        subCatPart.add(table);

	    }

	    private static void createList(Section subCatPart) {
	        List list = new List(true, false, 10);
	        list.add(new ListItem("First point"));
	        list.add(new ListItem("Second point"));
	        list.add(new ListItem("Third point"));
	        subCatPart.add(list);
	    }

	    private static void addEmptyLine(Paragraph paragraph, int number) {
	        for (int i = 0; i < number; i++) {
	            paragraph.add(new Paragraph(" "));
	        }
	    }
	
}
