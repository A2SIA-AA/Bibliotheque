import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class Personne {

    //Définition des attributs
    private String nom;
    private String prenom;
    private Calendar age;
    private String adressemail;
    
    //Constructeurs
    public Personne(){
        this.nom="";
        this.prenom="";
        this.age = null;
        this.adressemail = "";
    }
    
    public Personne(String nom, String prenom){
        this.nom=nom;
        this.prenom=prenom;
    }
    
    public Personne(String nom, String prenom, String mail, Calendar age){
        this.nom=nom;
        this.prenom=prenom;
        this.adressemail=mail;
    	this.age=age;
    }
    
    //Accesseurs et mutateurs
    public void setnom(String nom){
        this.nom=nom;
    }
    
    public String getnom(){
        return this.nom;
    }
    
    
    public void setprenom(String prenom){
        this.prenom=prenom;
    }
    
    public String getprenom(){
        return this.prenom;
    }
    
    public void setage(Calendar age){
        this.age=age;
    }
    
    public Calendar getage(){
        return this.age;
    }
    
    public void setadressemail(String adressemail){
		this.adressemail=adressemail;
    }
    
    public String getadressemail() {
		return this.adressemail;
    }
    
    
}
