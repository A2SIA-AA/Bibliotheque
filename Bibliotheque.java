import java.util.Set;
import java.util.HashSet;

public class Bibliotheque {

    
    private Set<Adherent> ensemble_adherent;
    private Set<Ouvrage> ensemble_ouvrage;
    private Set<Exemplaire> ensemble_exemplaire;

    // Constructeur de la classe Bibliotheque
    public Bibliotheque() {
        this.ensemble_adherent= new HashSet<>();
        this.ensemble_ouvrage= new HashSet<>();
        this.ensemble_exemplaire= new HashSet<>();
    }
    
    public void setensemble_adherent(Set<Adherent> ensemble_adherent) {
    	this.ensemble_adherent=ensemble_adherent;
    }
    
    public Set<Adherent> getensemble_adherent() {
    	return this.ensemble_adherent;
    }
    
    public void setensemble_ouvrage(Set<Ouvrage> ensemble_ouvrage) {
    	this.ensemble_ouvrage=ensemble_ouvrage;
    }
    
    public Set<Ouvrage> getensemble_ouvrage(){
    	return this.ensemble_ouvrage;
    }
    
    public void setensemble_exemplaire(Set<Exemplaire> ensemble_exemplaire) {
    	this.ensemble_exemplaire=ensemble_exemplaire;
    }
    
    public Set<Exemplaire> getensemble_exemplaire() {
    	return this.ensemble_exemplaire;
    }
    
    public Adherent TrouveAdherent(int identifiant) {
        for (Adherent adherent : this.getensemble_adherent()) {
            if (adherent.getidentifiant() == identifiant) {
                return adherent;
            }
        }
        return null;
    }
    
    public Ouvrage TrouveOuvrage(String titre) {
        for (Ouvrage ouvrage : this.getensemble_ouvrage()) {
            if (ouvrage.gettitre().equals(titre) == true) {
                return ouvrage;
            }
        }
        return null;
    }
    
    public Exemplaire TrouveExemplaire(int reference) {
        for (Exemplaire exemplaire : this.getensemble_exemplaire()) {
            if (exemplaire.getreference() == reference) {
                return exemplaire;
            }
        }
        return null;
    }
    
    public Set<Adherent> EnsembleAdherentEnRetard() {
        Set<Adherent> ensembleadherentenretard = new HashSet<>();
        for (Adherent adherent : this.ensemble_adherent) {
            if (adherent.ExemplaireRetard().size() != 0) {
                ensembleadherentenretard.add(adherent);
            }
        } 
        return ensembleadherentenretard;
    }

    public Adherent TrouveAdherentmail(String mail) {
    	for (Adherent adherent : this.getensemble_adherent()) {
            if (adherent.getadressemail().equals(mail)) {
                return adherent;
            }
        }
        return null;
    }
   
}
