package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.JoueurDTO;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.PartieDTO;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.mocks.StatsServiceMock;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.interfaces.IStatsService;

import static org.junit.jupiter.api.Assertions.*;

public class CalculerMoyenneTest {

    private IStatsService stub;
    private JoueurDTO joueur;

    @BeforeEach
    void setUp() {
        stub   = new StatsServiceMock();
        joueur = new JoueurDTO("Alice", "ali77", 2000, 1, "gaming");
    }

    @Test
    void calculerMoyenne_unePartie_moyenneEgaleScore() {
        joueur.addPartie(new PartieDTO(8, 10, 300));

        assertEquals(8.0, stub.calculerMoyenne(joueur), 0.005);
    }

    @Test
    void calculerMoyenne_4parties_calculCorrect() {
        // 22 bonnes réponses sur 40 → 5.50
        joueur.addPartie(new PartieDTO(6, 10, 202));
        joueur.addPartie(new PartieDTO(7, 10, 753));
        joueur.addPartie(new PartieDTO(7, 10, 247));
        joueur.addPartie(new PartieDTO(2, 10, 420));

        assertEquals(5.50, stub.calculerMoyenne(joueur), 0.005);
    }

    @Test
    void calculerMoyenne_arrondie2DecimalesApresLaVirgule() {
        // 10 bonnes réponses sur 30 → 3.333... → 3.33
        joueur.addPartie(new PartieDTO(3, 10, 200));
        joueur.addPartie(new PartieDTO(4, 10, 200));
        joueur.addPartie(new PartieDTO(3, 10, 200));

        assertEquals(3.33, stub.calculerMoyenne(joueur), 0.005);
    }

    @Test
    void calculerMoyenne_porteSurToutesLesParties() {
        // Sans la 1ère (score 10), moyenne des 5 suivantes = 0.0
        // Avec toutes : 10/60 * 10 = 1.67
        joueur.addPartie(new PartieDTO(10, 10, 100));
        joueur.addPartie(new PartieDTO(0,  10, 100));
        joueur.addPartie(new PartieDTO(0,  10, 100));
        joueur.addPartie(new PartieDTO(0,  10, 100));
        joueur.addPartie(new PartieDTO(0,  10, 100));
        joueur.addPartie(new PartieDTO(0,  10, 100));

        assertEquals(1.67, stub.calculerMoyenne(joueur), 0.005);
    }

    @Test
    void calculerMoyenne_scoreParfait_retourne10() {
        joueur.addPartie(new PartieDTO(10, 10, 200));
        joueur.addPartie(new PartieDTO(10, 10, 300));

        assertEquals(10.0, stub.calculerMoyenne(joueur), 0.005);
    }

    @Test
    void calculerMoyenne_scoreZero_retourneZero() {
        joueur.addPartie(new PartieDTO(0, 10, 200));
        joueur.addPartie(new PartieDTO(0, 10, 300));

        assertEquals(0.0, stub.calculerMoyenne(joueur), 0.005);
    }
}
