import java.util.Set;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.HashSet;

public class Adherent extends Personne {

	private static final int ageenfant=12;
	private static final int nbrempruntmaxadulte=3;
    	private static final int nbrempruntmaxenfant=2;

	
	private int identifiant;
	private Set<Exemplaire> ensemble_exemplaire; 
    	private int nbrretard; 
    	private Calendar dernierpaiement;
	
	public Adherent() {
        super();
        this.identifiant = 0;
        this.ensemble_exemplaire = new HashSet<>();
        this.nbrretard = 0;
        this.dernierpaiement = new GregorianCalendar();
        
    	}
    	
    	public Adherent(String nom, String prenom, String mail, Calendar age, int identifiant) {
		super(nom,prenom,mail,age);
		this.identifiant = identifiant;
		this.ensemble_exemplaire = new HashSet<>();
        	this.nbrretard = 0;
       		this.dernierpaiement = new GregorianCalendar();
    	}
    	
    	public void setidentifiant(int identifiant) {
		this.identifiant = identifiant;
    	}
    	
	public int getidentifiant(){
		return this.identifiant;
    	}
    	
    	public void setensemble_exemplaire(Set<Exemplaire> ensemble_exemplaire) {
        	this.ensemble_exemplaire = ensemble_exemplaire;
    	}
    	
	public Set<Exemplaire> getensemble_exemplaire() {
        	return this.ensemble_exemplaire;
    	}
    	
    	public void setnbrretard(int nbrretard) {
        	this.nbrretard = nbrretard;
    	}
    	
    	public int getnbrretard() {
        	return this.nbrretard;
    	}
    	
    	public Calendar getdernierpaiement() {
      		return this.dernierpaiement;
    	}
    	
  	public void setdernierpaiement(Calendar dernierpaiement) {
      		this.dernierpaiement = dernierpaiement;
    	}
	
	public boolean TestLimiteEmprunt() {
        	Calendar calendar = new GregorianCalendar();
        	calendar.add(Calendar.YEAR,-ageenfant);
        	if (calendar.compareTo(this.getage()) >= 0){
            		if (nbrempruntmaxadulte>this.getensemble_exemplaire().size()){
                		return false;
            		}
            		return true;
        	}else{
            		if (nbrempruntmaxenfant>this.getensemble_exemplaire().size()){
                		return false;
            		}
            		return true;
        	}
    	}
    	
    	public Set<Exemplaire> ExemplaireRetard() {
        	Set<Exemplaire> exemplaireretard =  new HashSet<>();
        	for (Exemplaire exemplaire : this.getensemble_exemplaire()){
            		if (exemplaire.getemprunt().EnRetard() == true) {
                		exemplaireretard.add(exemplaire);
            		}
        	}
        	return exemplaireretard;
    	}
    	
    	public int TotalAPayer() {
        	Calendar calendar = new GregorianCalendar();
        	calendar.add(Calendar.YEAR,-ageenfant);
        	if (calendar.compareTo(this.getage()) >= 0) {
            		Calendar dateactuelle = new GregorianCalendar();
            		int differencemois = dateactuelle.get(Calendar.MONTH)-this.dernierpaiement.get(Calendar.MONTH);
            		int differenceannee = dateactuelle.get(Calendar.YEAR)-this.dernierpaiement.get(Calendar.YEAR);
            		return 5 * (this.getnbrretard() + 12*differenceannee + differencemois);
        	}
        	return 5*this.getnbrretard();
    	}
    	
    	public String toString() {
    	
    		Calendar calendar = this.getage();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dateFormatted = dateFormat.format(calendar.getTime());
                
		return "Nom: " + this.getnom() + "\t" + "Prénom: " + this.getprenom() + "\t" + "Type: " + "Email: " + this.getadressemail() + "\t" + "Âge: " + dateFormatted + "\n";
    	}    
    
}
