import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class Emprunt{

    private Adherent adherent;
    private Calendar dateemprunt;
    private int nbrprolongation;


	public Emprunt () {
        this.adherent = null;
        this.dateemprunt= null;
        this.nbrprolongation=0;
    }
    public Emprunt(Adherent adherent) {
        this.adherent=adherent;
        this.dateemprunt = new GregorianCalendar();
        this.nbrprolongation=0;
    }
		
    
    public Adherent getadherent() {
        return this.adherent;
    }
    public void setadherent(Adherent adherent) {
        this.adherent = adherent;
    }
    
    
    public Calendar getdateemprunt() {
      		return this.dateemprunt;
    }
  	public void setdateemprunt(Calendar dateemprunt) {
      		this.dateemprunt = dateemprunt;
    }
    
    
	public int getnbrprolongation() {
		return this.nbrprolongation;
    }
    public void setnbrprolongation(int prolong) {
		this.nbrprolongation=prolong;
    }

    public boolean EnRetard() {
        
        Calendar calDateLimite = this.getdateemprunt();
        
        Calendar calDateActuelle = new GregorianCalendar();
        
        calDateActuelle.add(Calendar.DAY_OF_MONTH, -15*(1+this.getnbrprolongation()));
        return calDateActuelle.getTime().compareTo(calDateLimite.getTime()) > 0;
    }
}