import java.util.Set;
import java.util.HashSet;

enum listeOuvrage {
	Livre,
	Films,
	DVD,
	Jeux_Vidéo
}


public class Ouvrage {
	
	//Attribut
	private listeOuvrage type;
	private String titre;
	private Set<Exemplaire> ensemble_exemplaire;
	
	//Constructeurs
    	public Ouvrage(){
    		this.titre = " ";
    		this.type = listeOuvrage.Livre; // Initialisation avec une valeur par défaut;
    		this.ensemble_exemplaire= new HashSet<>();
    	}
	
	public Ouvrage(String titre, String type){
        	this.titre = titre;
        	this.type = listeOuvrage.valueOf(type);        	
        	this.ensemble_exemplaire= new HashSet<>();
    	}
    	
    	//Accesseurs et mutateurs
    	public listeOuvrage getType(){
        	return this.type;
    	}
    
    	public String gettitre(){
        	return this.titre;
    	}
    	
    	public void setType(listeOuvrage type){
        	this.type = type;
    	}
    
    	public void setTitre(String titre){
        	this.titre = titre;
    	}
    	
    	public Set<Exemplaire> getensemble_exemplaire() {
        	return this.ensemble_exemplaire;
    	}
    	
    	public void setensemble_exemplaire(Set <Exemplaire> ensemble_exemplaire) {
        	this.ensemble_exemplaire=ensemble_exemplaire;
    	}
    	
    	public int VerifierDisponibite(){
		int nbrex=0;
        	for (Exemplaire exemplaire : this.getensemble_exemplaire()) { 
            		if (exemplaire.getemprunt() == null) {
                		nbrex++;
            		}
        	}
        	return nbrex;
    	}
    	
    	public String toString() {
		return "Titre: " + this.gettitre() + "\t" + "Nombre d'exemplaire: " + this.getensemble_exemplaire().size() + "\t" + "Type: " + this.getType() + "\n";
    	}
}
