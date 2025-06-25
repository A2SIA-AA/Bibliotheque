import java.util.Set;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

import javafx.fxml.FXML;
import javafx.scene.control.*;


public class Bibliothecaire extends Personne{
    
    private static final int prolongationmax=2;
    private Bibliotheque bibliotheque;
	
    public Bibliothecaire() {
	super();
        this.bibliotheque=null;
    }
    
    public Bibliothecaire (String nom, String prenom, Bibliotheque bibliotheque){
        super(nom,prenom);
        this.bibliotheque=bibliotheque;
    }

    public void NouvelAdherent(String nom, String prenom, String mail, Calendar age){
        Adherent adherent=bibliotheque.TrouveAdherentmail(mail);
        if (adherent==null) {
            adherent = new Adherent(nom,prenom,mail,age,this.bibliotheque.getensemble_adherent().size()+1);
            this.bibliotheque.getensemble_adherent().add(adherent);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("L'inscription a bien été effectuée, son numéro d'identifiant est : " + adherent.getidentifiant());
	alert.showAndWait();
        } else {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("L'adherent: "+ adherent.getprenom() + " est deja inscrit \n");
	alert.showAndWait();
	}
    }

    public void NouvelOuvrage(String titre,String type){
        Ouvrage ouvrage = bibliotheque.TrouveOuvrage(titre);
        if (ouvrage==null) {
            ouvrage=new Ouvrage(titre,type);
            
            this.bibliotheque.getensemble_ouvrage().add(ouvrage);
            Exemplaire exemplaire = new Exemplaire(this.bibliotheque.getensemble_exemplaire().size()+1,ouvrage);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("L'ouvrage "+ ouvrage.gettitre() + " a bien été enregistré a la référence, ajouter maintenant un exemplaire ! \n");
	alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("L'ouvrage "+ouvrage.gettitre()+ " existe deja dans la bibliotheque \n");
	alert.showAndWait();
        }
   }
    public void NouvelExemplaire(String titre){
	Ouvrage ouvrage = this.bibliotheque.TrouveOuvrage(titre);
        if (ouvrage == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("Le titre n'est pas bon, echec de l'enregistrement \n");
	alert.showAndWait();
	} else {
            Exemplaire exemplaire = new Exemplaire(this.bibliotheque.getensemble_exemplaire().size()+1,ouvrage);
            this.bibliotheque.getensemble_exemplaire().add(exemplaire);
            ouvrage.getensemble_exemplaire().add(exemplaire);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("L'exemplaire "+ouvrage.gettitre()+" a bien ete enregistrer a la reference : "+exemplaire.getreference()+"\n");
	alert.showAndWait();
        }
    }

    public void NouvelEmprunt(int identifiant, int reference) throws Empruntimpossible{
        
        Adherent adherent = this.bibliotheque.TrouveAdherent(identifiant);
        Exemplaire exemplaire = this.bibliotheque.TrouveExemplaire(reference);
        
        if (adherent == null && exemplaire == null) {
        } else if (adherent == null) {
          throw new Empruntimpossible  ("L'identifiant et la réference ne sont pas bons, echec de l'enregistrement");
        } else if (exemplaire == null) {
          throw new Empruntimpossible ("La reference n'est pas bonne, echec de l'enregistrement");
        } else if (adherent.ExemplaireRetard().size() != 0) {
          throw new Empruntimpossible ("L'adherent a des retards, echec de l'enregistrement");
        } else if (adherent.TestLimiteEmprunt() == true) {
          throw new Empruntimpossible ("Limite des emprunts deja atteint, echec de l'enregistrement");
        } else if (adherent.TotalAPayer() > 5) {
          throw new Empruntimpossible  ("Paiement non a jour, echec de l'enregistrement");
        } else if (exemplaire.getemprunt()!=null) {
          throw new Empruntimpossible  ("Livre deja emprunte, echec de l'enregistrement");
        } else {
            Emprunt emprunt = new Emprunt(adherent);
            adherent.getensemble_exemplaire().add(exemplaire);
            exemplaire.setemprunt(emprunt);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("L'enregistrement a été réalisé avec succés \n");
	alert.showAndWait();
        }
    }

    public void Verifier(String titre){ 
        
        Ouvrage ouvrage = this.bibliotheque.TrouveOuvrage(titre);
        if (ouvrage == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("Le titre n'est pas bon, l'ouvrage n'existe pas au sein de la bibliotheque");
	alert.showAndWait();
        } else if (ouvrage.VerifierDisponibite() >0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("L'ouvrage "+ titre+ " est disponible en "+ ouvrage.VerifierDisponibite()+" exemplaire(s)");
	alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("L'ouvrage "+ titre +" n'est pas disponible");
	alert.showAndWait();
        }
    }

    public void Restitution(int reference) {
        Exemplaire exemplaire = this.bibliotheque.TrouveExemplaire(reference);
        if (exemplaire == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("La reference n'est pas bonne, echec de la restitution");
	alert.showAndWait();
        }else if(exemplaire.getemprunt()==null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("L exemplaire se trouve dans la bibliotheque, il ne peut pas etre rendu");
	alert.showAndWait();
        } else {
            if (exemplaire.getemprunt().EnRetard() == true) {
                exemplaire.getemprunt().getadherent().setnbrretard(exemplaire.getemprunt().getadherent().getnbrretard()+1);
            }
            exemplaire.getemprunt().getadherent().getensemble_exemplaire().remove(exemplaire);
            exemplaire.setemprunt(null);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("La restitution a ete realisee avec succes");
	alert.showAndWait();
        }
    }
	
	public void Prolonger(int reference) throws Prolongationimpossible{
        Exemplaire exemplaire = this.bibliotheque.TrouveExemplaire(reference);
        if (exemplaire == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("La reference n'est pas bonne, echec de l'enregistrement");
	alert.showAndWait();
    	} else if (exemplaire.getemprunt()==null){
    	  throw new Prolongationimpossible("Ce livre n'est pas emprunté donc aucune prolongation n'est possible !");
        } else if (exemplaire.getemprunt().getnbrprolongation()>=prolongationmax) {
          throw new Prolongationimpossible("Le nombre de prolongations est déjà atteint");
    } else {
            exemplaire.getemprunt().setnbrprolongation(exemplaire.getemprunt().getnbrprolongation()+1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("La prolongation a ete realisee avec succes");
	alert.showAndWait();
        }
    }
    
	public void RecupererMailRetard() {
        Set<Adherent> ensembleadherentretard = bibliotheque.EnsembleAdherentEnRetard();
        if (ensembleadherentretard.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("Aucun adherent est en retard");
	alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("Le mail des adherents en retard sont :");
	alert.showAndWait();
	StringBuilder mailListBuilder = new StringBuilder();
            for (Adherent adherent : bibliotheque.EnsembleAdherentEnRetard()) {
                mailListBuilder.append(adherent.getadressemail()).append("\n");    
            }
            alert.setContentText(mailListBuilder.toString());
	    alert.showAndWait();
        }
    }

    public void SommeAPayer(int identifiant) {
        Adherent adherent = this.bibliotheque.TrouveAdherent(identifiant);
        if (adherent == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("L identifiant n'est pas bon");
	alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("Le montant total a payer est "+adherent.TotalAPayer());
	alert.showAndWait();
        }
    }

    public void Paiement(int identifiant) {
        Adherent adherent = this.bibliotheque.TrouveAdherent(identifiant);
        if (adherent == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("L identifiant n'est pas bon");
	alert.showAndWait();
        } else {
            adherent.setnbrretard(0);
            adherent.setdernierpaiement(new GregorianCalendar());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("Paiment effectue");
	alert.showAndWait();
     }
}
   public void AdherentExemplaireretard(int identifiant) {
        Adherent adherent = this.bibliotheque.TrouveAdherent(identifiant);
        if (adherent == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("L identifiant n'est pas bon");
	alert.showAndWait();
        } else {
            Set<Exemplaire> exemplaireenretard = adherent.ExemplaireRetard();
            if (exemplaireenretard.size() == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("L'adherent n'a pas d'exemplaire en retard");
	alert.showAndWait();
                
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("Les ouvrages en retard sont");
	alert.showAndWait();
	StringBuilder ExemplaireListBuilder = new StringBuilder();
                for (Exemplaire exemplaire : exemplaireenretard ) {
                    ExemplaireListBuilder.append(exemplaire.getouvrage().gettitre()).append("\n");
                }
                alert.setContentText(ExemplaireListBuilder.toString());
	        alert.showAndWait();
            }
        }
    }
    public void DateLimiteExemplaire(int reference) {
        Exemplaire exemplaire = this.bibliotheque.TrouveExemplaire(reference);
        if (exemplaire == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("La reference n'est pas bonne, echec de l'enregistrement");
	alert.showAndWait();
    	} else if (exemplaire.getemprunt()==null){
    	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("Ce livre n'est pas emprunte donc il est disponible");
	alert.showAndWait();
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy");
            Calendar date = (Calendar)exemplaire.getemprunt().getdateemprunt().clone();
            date.add(Calendar.DAY_OF_MONTH, 15*(1+exemplaire.getemprunt().getnbrprolongation()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText(null);
	alert.setContentText("La date limite est "+dateFormat.format(date.getTime()));
	alert.showAndWait();
        }         
    }
    
    public void ChangeEmail(int identifiant, String adresseemail) {
        Adherent adherent = this.bibliotheque.TrouveAdherent(identifiant);
        if (adherent == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setTitle("Information");
	    alert.setHeaderText(null);
	    alert.setContentText("L'identifiant n'est pas bon");
        } else {
            adherent.setadressemail(adresseemail);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    alert.setTitle("Information");
	    alert.setHeaderText(null);
	    alert.setContentText("Changement bien effectué");
        }
    }
}
