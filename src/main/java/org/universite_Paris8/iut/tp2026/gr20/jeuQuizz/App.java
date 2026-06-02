package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.JoueurDTO;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.impls.JoueurServiceImpl;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.interfaces.IJoueurService;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.utils.exceptions.*;

import java.util.List;
import java.util.Scanner;

public class App {

    private static final IJoueurService service = new JoueurServiceImpl();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            afficherMenu();
            int choix = lireEntierBorne(1, 6);
            switch (choix) {
                case 1 -> gestionUtilisateurs();
                case 2 -> System.out.println("(Lancer une partie)");
                case 3 -> System.out.println("(Classement)");
                case 4 -> System.out.println("(Statistiques)");
                case 5 -> System.out.println("(Règles)");
                case 6 -> running = false;
            }
        }
        System.out.println("Au revoir !");
        scanner.close();
    }

    // ----------------------------------------------------------------
    // Menu principal
    // ----------------------------------------------------------------

    private static void afficherMenu() {
        System.out.println("\n---------- Bienvenue dans le jeu du Quizz ----------");
        System.out.println("1. Ajouter/suppression d'un joueur.");
        System.out.println("2. Lancer une partie.");
        System.out.println("3. Voir le classement.");
        System.out.println("4. Voir les statistiques.");
        System.out.println("5. Afficher la règle.");
        System.out.println("6. Quitter le jeu");
        System.out.print("Votre choix : ");
    }

    // ----------------------------------------------------------------
    // Gestion utilisateurs
    // ----------------------------------------------------------------

    private static void gestionUtilisateurs() {
        afficherListeJoueurs();
        System.out.println("\nVous souhaitez :");
        System.out.println("1. Ajouter un joueur");
        System.out.println("2. Supprimer un joueur");
        System.out.print("Votre choix : ");

        int choix = lireEntierBorne(1, 2);
        switch (choix) {
            case 1 -> ajouterJoueur();
            case 2 -> System.out.println("(Suppression)");
        }
    }

    private static void afficherListeJoueurs() {
        System.out.println("\n*** Gestion Utilisateurs ***");
        try {
            List<JoueurDTO> joueurs = service.listerJoueurs();
            System.out.println("Les joueurs connus sont :");
            joueurs.forEach(j -> System.out.println("  - " + j));
        } catch (NoPlayerAvailableException e) {
            System.out.println("Aucun joueur enregistré pour le moment.");
        }
    }

    // ----------------------------------------------------------------
    // Ajout d'un joueur
    // ----------------------------------------------------------------

    private static void ajouterJoueur() {
        System.out.println("\n*** Création Utilisateur ***");

        System.out.print("Prénom du joueur : ");
        String prenom = scanner.nextLine().trim();

        System.out.print("Pseudo : ");
        String pseudo = scanner.nextLine().trim();

        System.out.print("Année de naissance : ");
        int annee = lireEntier();

        System.out.print("Centres d'intérêts (séparés par des ,) : ");
        String hobbies = scanner.nextLine().trim();

        System.out.println("Langue préférée :");
        System.out.println("  1. Français");
        System.out.println("  2. Anglais");
        System.out.println("  3. Allemand");
        System.out.println("  4. Espagnol");
        System.out.println("  5. Italien");
        System.out.print("Votre choix de langue préférée : ");
        int langue = lireEntierBorne(1, 5);

        JoueurDTO dto = new JoueurDTO(prenom, pseudo, annee, langue, hobbies);

        try {
            JoueurDTO joueur = service.createPlayer(dto);
            String hobbiesAffichage = joueur.getHobbies();
            if (hobbiesAffichage == null || hobbiesAffichage.isBlank()) {
                System.out.println(joueur.getPseudo() + " a été ajouté.");
            } else {
                System.out.println(joueur.getPseudo() + " fan de " + hobbiesAffichage + " a été ajouté.");
            }
        } catch (InvalidPrenomException | InvalidPseudoException |
                 InvalidAnneeNaissanceException | InvalidLangueException |
                 InvalidHobbiesException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        System.out.println("Appuyez sur Entrée pour revenir au menu...");
        scanner.nextLine();
    }

    // ----------------------------------------------------------------
    // Utilitaires
    // ----------------------------------------------------------------

    private static int lireEntier() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Saisie invalide, entrez un nombre : ");
            }
        }
    }

    private static int lireEntierBorne(int min, int max) {
        while (true) {
            try {
                int valeur = Integer.parseInt(scanner.nextLine().trim());
                if (valeur >= min && valeur <= max) {
                    return valeur;
                }
                System.out.print("Saisie invalide, entrez un chiffre entre " + min + " et " + max + " : ");
            } catch (NumberFormatException e) {
                System.out.print("Saisie invalide, entrez un chiffre entre " + min + " et " + max + " : ");
            }
        }
    }
}