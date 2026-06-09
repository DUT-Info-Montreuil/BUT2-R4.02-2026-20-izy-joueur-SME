package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.interfaces;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.JoueurDTO;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.PartieDTO;

import java.util.List;

public interface IStatsService {

    boolean aJoue(JoueurDTO joueur);

    int calculerNbParties(JoueurDTO joueur);

    int calculerTotalBonnesReponses(JoueurDTO joueur);

    int calculerTotalQuestions(JoueurDTO joueur);

    double calculerMoyenne(JoueurDTO joueur);

    long calculerDureeMoyenne(JoueurDTO joueur);

    List<PartieDTO> getDernieresParties(JoueurDTO joueur);

    void afficherStats(List<JoueurDTO> joueurs);
}