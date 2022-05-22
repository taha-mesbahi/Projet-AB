package application;

public class Etudiant {
	   private String idEtudiant;
	   private String nomm;
	   private String prenomm;
	   private String filier;
	public String getIdEtudiant() {
		return idEtudiant;
	}
	public void setIdEtudiant(String idEtudiant) {
		this.idEtudiant = idEtudiant;
	}
	public String getNomm() {
		return nomm;
	}
	public void setNomm(String nomm) {
		this.nomm = nomm;
	}
	public String getPrenomm() {
		return prenomm;
	}
	public void setPrenomm(String prenomm) {
		this.prenomm = prenomm;
	}
	public String getFilier() {
		return filier;
	}
	public void setFilier(String filier) {
		this.filier = filier;
	}
	public Etudiant() {
		super();
	}
	public Etudiant(String idEtudiant, String nomm, String prenomm, String filier) {
		super();
		this.idEtudiant = idEtudiant;
		this.nomm = nomm;
		this.prenomm = prenomm;
		this.filier = filier;
	}
	   
	   
	   
	   
	   
	}