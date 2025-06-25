# La Bibliothèque d’Alexandrie

> **Auteurs :** Doubli Hoda & Ait Taleb Assia - INSA Rouen, promo GM3 
> **Date :** juin 2024  

Une application Java (console **et** JavaFX) qui simule la gestion d’une médiathèque :  
*inscriptions, emprunts, prolongations, pénalités, interface graphique, etc.*

---


## 1. Contenu du dossier

| Fichier                          | Rôle                                                     |
|----------------------------------|----------------------------------------------------------|
| `Adherent.java`                  | classe adhérent (hérite de `Personne`)                  |
| `Bibliotheque.java`              | classe principale « modèle »                            |
| `Bibliothecaire.java`            | classe bibliothécaire (hérite de `Personne`)            |
| `Emprunt.java` / `EmpruntImpossible.java` / `Prolongationimpossible.java` | gestion des prêts & exceptions        |
| `Exemplaire.java` / `Ouvrage.java` | stock & catalogue                                     |
| `Personne.java`                  | super-classe commun aux acteurs                         |
| `main.java`                      | lanceur console (tests)                                 |
| `interface.fxml`                 | vue JavaFX (menu, formulaires)                          |
| `MediathequeController.java`     | logique JavaFX                                          |
| `adherents.txt` / `ouvrages.txt` | petites “BD” texte pour les démos                       |
| `Diagramme de Classe UML.png`    | diagramme de classes                                    |
| `Diagramme de séquence*.png`     | séquences emprunt / restitution                         |
| `Diagramme de Cas d’utilisation.png` | acteurs & cas d'utilisation                         |
| `projet_MIPP_S2.pdf`             | rapport détaillé                                        |


---

## 3. Prérequis

| Outil | Version mini | Rôle |
|-------|--------------|------|
| **Java JDK** | 17 | compilation / exécution |
| **JavaFX SDK** | 11 | interface graphique |
| **IDE** (optionnel) | IntelliJ IDEA, VS Code… | confort |

> Sous Debian/Ubuntu : `sudo apt install openjdk-17-jdk` puis télécharger JavaFX SDK sur <https://gluonhq.com/products/javafx/>.

---

## 4. Compilation & exécution

```bash
# Compilation
javac \
  --module-path $PATH_TO_FX/lib --add-modules javafx.controls,javafx.fxml \
  -d out $(find java -name "*.java")

# Exécution
java  \
  --module-path $PATH_TO_FX/lib --add-modules javafx.controls,javafx.fxml \
  -cp out ui.Launcher      # classe qui étend javafx.application.Application
```

*Remplace* `$PATH_TO_FX` *par le chemin vers le dossier* `javafx-sdk-XX.X.X`.

---

## 5. Tests rapides

1. **Se connecter** (mot de passe par défaut :`Ceprojetmerite20`).
2. **Ajouter un adhérent** (menu ► Gestion Adhérents ► Ajouter).
3. **Créer un ouvrage**, puis un **exemplaire**.
4. **Enregistrer un emprunt** & tester **prolongation/restitution**.
5. Observer la mise à jour automatique des retards & pénalités.

---

## 6. Points techniques

* **UML :** cas d’utilisation, classe (agrégation, composition, héritage), séquences.
* **Collections Java :** `Set` pour garantir l’unicité des adhérents/ouvrages.
* **Dates :** `Calendar`, `GregorianCalendar`, format `SimpleDateFormat`.
* **Exceptions custom :** `EmpruntImpossible`, `ProlongationImpossible`.
* **Hachage SHA-256** pour le mot de passe bibliothécaire.

---

## 7. Sources

* Cours Java & UML – INSA Rouen
* [Gluon JavaFX](https://gluonhq.com/products/javafx/) — SDK + tutoriels*

---
