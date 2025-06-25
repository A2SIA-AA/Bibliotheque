//Importe la classe Application de JavaFX, nécessaire pour créer une application JavaFX.
import javafx.application.Application;

//Importe la classe FXMLLoader, utilisée pour charger des fichiers FXML et créer des interfaces utilisateur à partir de ceux-ci.
import javafx.fxml.FXMLLoader;

//Importe les classes Parent et Scene, nécessaires pour créer la hiérarchie des nœuds de l'interface utilisateur et définir une scène pour l'application.
import javafx.scene.Parent;
import javafx.scene.Scene;

//Importe la classe Stage, qui représente la fenêtre principale de l'application JavaFX.
import javafx.stage.Stage;
import java.util.List;
import java.util.Arrays;

public class main extends Application { //Déclare une classe principale nommée main qui étend Application, indiquant que c'est une application JavaFX.
    @Override
    public void start(Stage primaryStage) throws Exception { //Override la méthode start de la classe Application. Cette méthode est appelée lors du démarrage de l'application et est utilisée pour initialiser l'interface utilisateur.
    
        Parent root = FXMLLoader.load(getClass().getResource("interface.fxml")); //Charge le fichier FXML nommé "interface.fxml" et crée la hiérarchie des nœuds de l'interface utilisateur à partir de celui-ci. Le nœud racine est stocké dans la variable root.
        
    primaryStage.setTitle("Bibliothèque"); //Définit le titre de la fenêtre principale de l'application sur "Bibliothèque".
    
    primaryStage.setScene(new Scene(root, 800, 600)); //Crée une nouvelle scène avec le nœud racine root et une taille de 800x600 pixels, puis la définie comme la scène de la fenêtre principale.
    
    primaryStage.show(); //Affiche la fenêtre principale de l'application avec la scène précédemment définie.
    }

    public static void main(String[] args) { //Définit la méthode main de l'application, qui est le point d'entrée de l'application Java. C'est là que le programme commence son exécution.
    
        launch(args); //Appelle la méthode statique launch de la classe Application, démarrant l'application JavaFX en appelant la méthode start et en passant les arguments de la ligne de commande.
    }
}

