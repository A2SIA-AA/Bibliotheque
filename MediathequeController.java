//Permet de marquer les champs et les méthodes dans un contrôleur JavaFX pour lier automatiquement les composants définis dans un fichier FXML avec les éléments correspondants dans le contrôleur.
import javafx.fxml.FXML;

//Inclut les composants de contrôle de l'interface utilisateur JavaFX, comme Button, Label, TextField, TableView, etc., permettant de les utiliser dans la construction de l'interface utilisateur.
import javafx.scene.control.*;

import javafx.collections.FXCollections;

// Utilisée pour créer un conteneur de disposition vertical qui organise ses enfants en une seule colonne, facilitant la création de mises en page structurées verticalement dans l'interface utilisateur.
import javafx.scene.layout.VBox;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class MediathequeController {

//Chaque annotation @FXML permet à JavaFX d'injecter automatiquement l'élément correspondant du fichier FXML dans la variable appropriée du contrôleur.

    @FXML
    private MenuItem seConnecterMenuItem;
    
    @FXML
    private MenuItem gestionAdherentsMenuItem;
    
    @FXML
    private MenuItem gererOuvrageMenuItem;
    
    @FXML
    private MenuItem empruntMenuItem;
    
    @FXML
    private MenuItem dispoMenuItem;

    @FXML
    private TextArea outputTextArea;

    @FXML
    private VBox interfaceGererOuvrage;
    
    @FXML
    private VBox interfaceGererAdherent;
    
    @FXML
    private VBox interfaceDisponibilite;
    
    @FXML
    private VBox interfaceEmprunt;
    
    @FXML
    private VBox interfaceconnexion;

    @FXML
    private TextField passwordField;
    
    @FXML
    private Label message;
    
    @FXML
    private Label MDP;

    @FXML
    private Button bouton;
     
    @FXML
    private ChoiceBox<listeOuvrage> typeChoiceBox;

    @FXML
    public void initialize() {
        // Remplir le ChoiceBox avec les options disponibles
        typeChoiceBox.setItems(FXCollections.observableArrayList(listeOuvrage.values()));
    }


    @FXML
    private void handleLoginButtonAction() {
        String inputPassword = passwordField.getText();

        // Vérification du mot de passe
        if (checkPassword(inputPassword)) {
            // Si le mot de passe est correct, exécuter d'autres actions
            afficherMessage("Mot de passe correct !");
            seConnecterBibliothecaire();
        } else {
            afficherErreur("Mot de passe incorrect !");
        }
    }

    // Méthode pour vérifier le mot de passe
    private boolean checkPassword(String inputPassword) {
        return inputPassword.equals("Ceprojetmerite20");
    }
    
    @FXML
    public void seConnecterBibliothecaire() {
    	
        afficherMessage("Connecter en tant que Bibliothécaire");
    	
    	passwordField.setVisible(false); //Cache le champ de mot de passe après une connexion réussie où la saisie du mot de passe n'est plus nécessaire.
    	MDP.setVisible(false); //Cache le label du mot de passe, utilisé conjointement avec le champ de mot de passe pour masquer toute la section de saisie du mot de passe.
    	
    	message.setVisible(false);
    	bouton.setVisible(false);
        
        seConnecterMenuItem.setVisible(false); //Cache l'option de connexion du menu après que l'utilisateur s'est connecté avec succès.
        
        gestionAdherentsMenuItem.setVisible(true); //Affiche l'option de menu pour gérer les adhérents après que l'utilisateur s'est connecté et a les permissions nécessaires.
        
        gererOuvrageMenuItem.setVisible(true);
        empruntMenuItem.setVisible(true);
        dispoMenuItem.setVisible(true);
    }
    
    @FXML
    public void SeConnecterAffichage() {
    
    	interfaceGererOuvrage.setVisible(false);
        interfaceGererAdherent.setVisible(false); // Cache l'interface de gestion des adhérents, permettant de masquer cette section.
        interfaceDisponibilite.setVisible(false);
        interfaceEmprunt.setVisible(false);
        interfaceconnexion.setVisible(true);
    
    }
    
    
    @FXML
    private TextField nomT; //TextField est un composant de JavaFX permettant à l'utilisateur de saisir une ligne de texte. La variable nomT sera utilisée pour manipuler cet élément dans le contrôleur.

    @FXML
    private TextField prenomT;

    @FXML
    private TextField emailT;

    @FXML
    private TextField dateNaissanceT;
    
    @FXML
    private Bibliotheque bibliotheque = new Bibliotheque();
    
    @FXML
    private Bibliothecaire bibliothecaire = new Bibliothecaire("Bourgais","Mathieu",bibliotheque);

    @FXML
    private void InscrireAdherent() {
    
    	adherent();
    	
        String nom = nomT.getText(); // getText() est une méthode de la classe TextField qui retourne le texte actuellement saisi dans le champ de texte.
        String prenom = prenomT.getText();
        String email = emailT.getText();
        String dateNaissanceStr = dateNaissanceT.getText();
        

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dateanniversaire = Calendar.getInstance();

        try {
            dateanniversaire.setTime(sdf.parse(dateNaissanceStr));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            afficherErreur("Format de date invalide. Veuillez entrer une date au format JJ/MM/AAAA.");
            return;
        }
        
        bibliothecaire.NouvelAdherent(nom,prenom,email,dateanniversaire);
        
    }
    
    @FXML
    public void adherent() {
    	seConnecterMenuItem.setVisible(false);
    	interfaceconnexion.setVisible(false);
        interfaceGererOuvrage.setVisible(false);
        interfaceGererAdherent.setVisible(true);
        interfaceDisponibilite.setVisible(false);
        interfaceEmprunt.setVisible(false);
    }

    @FXML
    private TextField titreT;
      
    
    @FXML
    public void ajouterOuvrage() {
    	GererOuvrage();
    	
        String titre = titreT.getText();
        listeOuvrage Type = typeChoiceBox.getValue(); //C'est une méthode de la classe ChoiceBox qui retourne la valeur actuellement sélectionnée dans la boîte de sélection.
        
        System.out.println("Titre de l'ouvrage : " + titre);
        
        
        bibliothecaire.NouvelOuvrage(titre,Type.toString());
        
    }
    
    @FXML
    public void GererOuvrage() {
    	seConnecterMenuItem.setVisible(false);
    	interfaceconnexion.setVisible(false);
        interfaceGererOuvrage.setVisible(true);
        interfaceGererAdherent.setVisible(false);
        interfaceDisponibilite.setVisible(false);
        interfaceEmprunt.setVisible(false);
    }
    
    @FXML
    private TextField titreT1;
    
    @FXML
    public void ajouterExemplaire() {
        GererOuvrage();
    	    
        String titre = titreT1.getText();
        
        bibliothecaire.NouvelExemplaire(titre);
    }    
    
    @FXML
    private TextField IDT;
    
    @FXML
    private TextField referenceT;
    
    //Méthode permettant de convertir en int la valeur écrite dans le champ de Texte.
    public int getID(TextField IDT) {
        String ID = IDT.getText();
        try {
            return Integer.parseInt(ID);
        } catch (NumberFormatException e) {
            // Gérer l'erreur de conversion
            e.printStackTrace();
            return -1; // Valeur par défaut en cas d'erreur
        }
    }
    
    public int getReference(TextField referenceT) {
        String reference = referenceT.getText();
        try {
            return Integer.parseInt(reference);
        } catch (NumberFormatException e) {
            // Gérer l'erreur de conversion
            e.printStackTrace();
            return -1; // Valeur par défaut en cas d'erreur
        }
    }
    
    @FXML
    public void EnregistrerEmprunt() {
        int ID = getID(IDT);
        int reference = getReference(referenceT);
        try{
          bibliothecaire.NouvelEmprunt(ID,reference);
        }catch (Empruntimpossible e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur d'emprunt");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    
    @FXML
    public void emprunt() {
    	seConnecterMenuItem.setVisible(false);
    	interfaceconnexion.setVisible(false);
        interfaceGererOuvrage.setVisible(false);
        interfaceGererAdherent.setVisible(false);
        interfaceDisponibilite.setVisible(false);
        interfaceEmprunt.setVisible(true);
    }
    
    @FXML
    private TextField referenceT1;
    
    @FXML
    public void ProlongerEmprunt() {
        int reference = getReference(referenceT1);
        try{
        bibliothecaire.Prolonger(reference);
        }catch (Prolongationimpossible e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de prolongation");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    
    @FXML
    private TextField referenceT2;
    
    @FXML
    public void RestituerEmprunt() {
    	emprunt();
    	
        int reference = getReference(referenceT2);
        
        bibliothecaire.Restitution(reference);
    }
    
    @FXML
    private TextField titreT2;
    
    @FXML
    public void VerifierDisponibilite() {
    	dispo();
    	
        String titre = titreT2.getText();
        
        bibliothecaire.Verifier(titre);
    }
    
    @FXML
    public void dispo() {
    	interfaceconnexion.setVisible(false);
        interfaceGererOuvrage.setVisible(false);
        interfaceGererAdherent.setVisible(false);
        interfaceDisponibilite.setVisible(true);
        interfaceEmprunt.setVisible(false);
        seConnecterMenuItem.setVisible(false);
    }
    
    @FXML
    private TextField referenceT3;
    
    @FXML
    public void RecupererDateLimite() {
    	dispo();
    
        int reference = getReference(referenceT3);
        
        bibliothecaire.DateLimiteExemplaire(reference);
    }
    
    @FXML
    public void RecupererMail() {
    	adherent();
        
        bibliothecaire.RecupererMailRetard();
    }
    
    @FXML
    private TextField emailT1;
    
    @FXML
    private TextField IDT4;
    
    @FXML
    public void ChangerMail() {
    	int ID = getID(IDT4);
    	
    	String email = emailT1.getText();
        
        bibliothecaire.ChangeEmail(ID, email);
    }
    
    @FXML
    private TextField IDT1;

    @FXML
    public void EnsembleRetardAdherent() {
        int ID = getID(IDT1);
        
        bibliothecaire.AdherentExemplaireretard(ID);
    }
    
    @FXML
    private TextField IDT2;
    
    @FXML
    public void MontantDu() {
    	adherent();
    	
        int ID = getID(IDT2);
        
        bibliothecaire.SommeAPayer(ID);
    }
    
    @FXML
    private TextField IDT3;
    
    @FXML
    public void Paiement() {
    	adherent();
    	
        int ID = getID(IDT3);
        
        bibliothecaire.Paiement(ID);
    }
    
    @FXML
    private TextArea adherentsTexte;
    
    @FXML
    public void enregistrerAdherentsDansFichier() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("adherents.txt"))) {
            Set<Adherent> adherents = bibliotheque.getensemble_adherent();
            for (Adherent adherent : adherents) {
                writer.write(adherent.toString());
            }
            writer.close();
            afficherMessage("Les adhérents ont été enregistrés dans le fichier 'adherents.txt'.");
        } catch (IOException e) {
            afficherErreur("Erreur lors de l'enregistrement des adhérents dans le fichier.");
            e.printStackTrace();
        }
    }
    
    @FXML
    public void enregistrerOuvragesDansFichier() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ouvrages.txt"))) {
            Set<Ouvrage> ouvrages = bibliotheque.getensemble_ouvrage();
            for (Ouvrage ouvrage : ouvrages) {
                writer.write(ouvrage.toString());
            }
            writer.close();
            afficherMessage("Les ouvrages ont été enregistrés dans le fichier 'ouvrages.txt'.");
        } catch (IOException e) {
            afficherErreur("Erreur lors de l'enregistrement des ouvrages dans le fichier.");
            e.printStackTrace();
        }
    }


// Méthode pour afficher un message d'information
    private void afficherMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Méthode pour afficher un message d'erreur
    private void afficherErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    

}

