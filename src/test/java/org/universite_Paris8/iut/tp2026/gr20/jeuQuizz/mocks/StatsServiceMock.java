package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.mocks;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.JoueurDTO;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.PartieDTO;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.interfaces.IStatsService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Mock manuel de IStatsService.
 * Utilisé à la place de StatsServiceImpl tant que celui-ci n'est pas disponible.
 * Chaque méthode implémente le comportement attendu selon les critères d'acceptance.
 */
public class StatsServiceMock implements IStatsService {

    /**
     * Retourne true si le joueur a au moins une partie jouée.
     */
    @Override
    public boolean aJoue(JoueurDTO joueur) {
        return joueur.getParties() != null && !joueur.getParties().isEmpty();
    }

    /**
     * Retourne le nombre total de parties jouées par le joueur.
     */
    @Override
    public int calculerNbParties(JoueurDTO joueur) {
        return joueur.getParties().size();
    }

    /**
     * Retourne la somme des bonnes réponses sur toutes les parties.
     */
    @Override
    public int calculerTotalBonnesReponses(JoueurDTO joueur) {
        return joueur.getParties().stream()
                .mapToInt(PartieDTO::getBonnesReponses)
                .sum();
    }

    /**
     * Retourne la somme du nombre de questions sur toutes les parties.
     */
    @Override
    public int calculerTotalQuestions(JoueurDTO joueur) {
        return joueur.getParties().stream()
                .mapToInt(PartieDTO::getTotalQuestions)
                .sum();
    }

    /**
     * Retourne la moyenne générale sur 10, arrondie à 2 chiffres après la virgule.
     * Calcul : (totalBonnesReponses / totalQuestions) * 10
     */
    @Override
    public double calculerMoyenne(JoueurDTO joueur) {
        int totalBonnes    = calculerTotalBonnesReponses(joueur);
        int totalQuestions = calculerTotalQuestions(joueur);
        if (totalQuestions == 0) return 0.0;
        double moyenne = ((double) totalBonnes / totalQuestions) * 10;
        return BigDecimal.valueOf(moyenne)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    /**
     * Retourne la durée moyenne en secondes, arrondie à la seconde (Math.round).
     */
    @Override
    public long calculerDureeMoyenne(JoueurDTO joueur) {
        List<PartieDTO> parties = joueur.getParties();
        if (parties.isEmpty()) return 0L;
        double moyenne = parties.stream()
                .mapToLong(PartieDTO::getDureeSecondes)
                .average()
                .orElse(0);
        return Math.round(moyenne);
    }

    /**
     * Retourne les 5 dernières parties dans l'ordre récent → ancien.
     * Retourne une liste vide si le joueur a joué moins de 5 parties.
     */
    @Override
    public List<PartieDTO> getDernieresParties(JoueurDTO joueur) {
        List<PartieDTO> parties = joueur.getParties();
        if (parties.size() < 5) return List.of();
        List<PartieDTO> dernieres = new ArrayList<>(
                parties.subList(parties.size() - 5, parties.size())
        );
        Collections.reverse(dernieres);
        return dernieres;
    }

    /**
     * Affichage console des stats de tous les joueurs,
     * dans l'ordre alphabétique de pseudo (insensible à la casse).
     */
    @Override
    public void afficherStats(List<JoueurDTO> joueurs) {
        joueurs.stream()
                .sorted((a, b) -> a.getPseudo().compareToIgnoreCase(b.getPseudo()))
                .forEach(joueur -> {
                    System.out.println("Statistiques de " + joueur.getPseudo() + " :");
                    if (!aJoue(joueur)) {
                        System.out.println(joueur.getPseudo() + " n'a pas encore joué");
                    } else {
                        System.out.println(calculerNbParties(joueur) + " parties jouées");
                        System.out.println(calculerTotalBonnesReponses(joueur)
                                + " bonnes réponses sur " + calculerTotalQuestions(joueur) + ".");
                        System.out.println("Moyenne générale : " + calculerMoyenne(joueur) + "/10");
                        System.out.println("Durée moyenne : " + calculerDureeMoyenne(joueur) + "s");
                        List<PartieDTO> dernieres = getDernieresParties(joueur);
                        if (!dernieres.isEmpty()) {
                            System.out.println("5 dernières parties jouées :");
                            for (int i = 0; i < dernieres.size(); i++) {
                                PartieDTO p = dernieres.get(i);
                                System.out.println((i + 1) + ". "
                                        + p.getBonnesReponses() + "/" + p.getTotalQuestions()
                                        + " en " + p.getDureeSecondes() + "s");
                            }
                        }
                    }
                });
    }
}
