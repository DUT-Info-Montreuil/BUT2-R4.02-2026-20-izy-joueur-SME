package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.impls;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.JoueurDTO;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.PartieDTO;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.interfaces.IStatsService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StatsServiceImpl implements IStatsService {

    @Override
    public boolean aJoue(JoueurDTO joueur) {
        return !joueur.getParties().isEmpty();
    }

    @Override
    public int calculerNbParties(JoueurDTO joueur) {
        return joueur.getParties().size();
    }

    @Override
    public int calculerTotalBonnesReponses(JoueurDTO joueur) {
        return joueur.getParties().stream().mapToInt(PartieDTO::getBonnesReponses).sum();
    }

    @Override
    public int calculerTotalQuestions(JoueurDTO joueur) {
        return joueur.getParties().stream().mapToInt(PartieDTO::getTotalQuestions).sum();
    }

    @Override
    public double calculerMoyenne(JoueurDTO joueur) {
        int nbParties = calculerNbParties(joueur);
        if (nbParties == 0) return 0.0;
        return (double) calculerTotalBonnesReponses(joueur) / nbParties;
    }

    @Override
    public long calculerDureeMoyenne(JoueurDTO joueur) {
        return Math.round(
                joueur.getParties().stream().mapToLong(PartieDTO::getDureeSecondes).average().orElse(0));
    }

    @Override
    public List<PartieDTO> getDernieresParties(JoueurDTO joueur) {
        List<PartieDTO> parties = joueur.getParties();
        int nb = parties.size();
        if (nb < 5) return Collections.emptyList();
        return parties.subList(nb - 5, nb);
    }

    @Override
    public void afficherStats(List<JoueurDTO> joueurs) {
        System.out.println("\n*** Les Stats ***");

        List<JoueurDTO> tries = new ArrayList<>(joueurs);
        tries.sort(Comparator.comparing(j -> j.getPseudo().toLowerCase()));

        for (JoueurDTO joueur : tries) {
            afficherStatsJoueur(joueur);
        }
    }

    private void afficherStatsJoueur(JoueurDTO joueur) {
        System.out.println("\nStatistiques de " + joueur.getPseudo() + " :");

        if (!aJoue(joueur)) {
            System.out.println(joueur.getPseudo() + " n'a pas encore joue");
            return;
        }

        int    nbParties          = calculerNbParties(joueur);
        int    totalBonnes        = calculerTotalBonnesReponses(joueur);
        int    totalQuestions     = calculerTotalQuestions(joueur);
        int    questionsParPartie = totalQuestions / nbParties;
        double moyenne            = calculerMoyenne(joueur);
        long   dureeMoyenne       = calculerDureeMoyenne(joueur);

        System.out.println(nbParties + " parties jouees");
        System.out.println(totalBonnes + " bonnes reponses sur " + totalQuestions + ".");
        System.out.println("Moyenne generale : " + formaterMoyenne(moyenne) + "/" + questionsParPartie);
        System.out.println("Duree moyenne : " + formaterDuree(dureeMoyenne));

        List<PartieDTO> dernieres = getDernieresParties(joueur);
        if (!dernieres.isEmpty()) {
            System.out.println("5 dernieres parties jouees :");
            for (int i = 0; i < dernieres.size(); i++) {
                PartieDTO p = dernieres.get(i);
                System.out.println((i + 1) + ". "
                        + p.getBonnesReponses() + "/" + p.getTotalQuestions()
                        + " en " + formaterDuree(p.getDureeSecondes()));
            }
        }
    }

    private String formaterMoyenne(double moyenne) {
        double arrondie = Math.round(moyenne * 100.0) / 100.0;
        if (arrondie == (int) arrondie) return String.valueOf((int) arrondie);
        return String.format("%.2f", arrondie).replaceAll("0+$", "");
    }

    private String formaterDuree(long secondes) {
        long minutes = secondes / 60;
        long secs    = secondes % 60;
        if (minutes == 0) return secs + "s";
        if (secs == 0) return minutes + " min";
        return minutes + " min " + secs + "s";
    }
}