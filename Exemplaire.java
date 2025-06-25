public class Exemplaire{
	
	private int reference;
    	private Ouvrage ouvrage;
	private Emprunt emprunt;

	public Exemplaire() {
        this.reference = 0;
        this.ouvrage = null;
        this.emprunt = null;
    }
	public Exemplaire(int reference, Ouvrage ouvrage) {
        this.reference = reference;
        this.ouvrage = ouvrage;
        this.emprunt = null;
    }


    public void setreference(int reference){
		this.reference = reference;
    }
    public int getreference() {
		return this.reference;
    }


    public void setouvrage (Ouvrage ouvrage) {
		this.ouvrage = ouvrage;
    }
    public Ouvrage getouvrage() {
		return this.ouvrage;
    }

    
    public void setemprunt(Emprunt emprunt) {
		this.emprunt = emprunt;
    }
    public Emprunt getemprunt() {
		return this.emprunt;
    }
}
