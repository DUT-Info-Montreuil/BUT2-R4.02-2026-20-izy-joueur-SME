package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz;

import java.util.Scanner;

public class App {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        afficherMenu();

        int choix = scanner.nextInt();

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



}