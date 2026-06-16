package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.JoueurDTO;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.PartieDTO;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.impls.StatsServiceImpl;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.interfaces.IStatsService;

import static org.junit.jupiter.api.Assertions.*;

public class CalculerTotauxTest {

    private IStatsService stub;
    private JoueurDTO joueur;

    @BeforeEach
    void setUp() {
        stub   = new StatsServiceImpl();
        joueur = new JoueurDTO("Alice", "ali77", 2000, 1, "gaming");
    }

    // --- calculerTotalBonnesReponses ---

    @Test
    void calculerTotalBonnesReponses_sansPartie_retourneZero() {
        assertEquals(0, stub.calculerTotalBonnesReponses(joueur));
    }

    @Test
    void calculerTotalBonnesReponses_unePartie_retourneScore() {
        joueur.addPartie(new PartieDTO(7, 10, 300));

        assertEquals(7, stub.calculerTotalBonnesReponses(joueur));
    }

    @Test
    void calculerTotalBonnesReponses_4parties_sommeTotale() {
        // 6 + 7 + 7 + 2 = 22
        joueur.addPartie(new PartieDTO(6, 10, 202));
        joueur.addPartie(new PartieDTO(7, 10, 753));
        joueur.addPartie(new PartieDTO(7, 10, 247));
        joueur.addPartie(new PartieDTO(2, 10, 420));

        assertEquals(22, stub.calculerTotalBonnesReponses(joueur));
    }

    // --- calculerTotalQuestions ---

    @Test
    void calculerTotalQuestions_sansPartie_retourneZero() {
        assertEquals(0, stub.calculerTotalQuestions(joueur));
    }

    @Test
    void calculerTotalQuestions_unePartie_retourneTotalQuestions() {
        joueur.addPartie(new PartieDTO(7, 10, 300));

        assertEquals(10, stub.calculerTotalQuestions(joueur));
    }

    @Test
    void calculerTotalQuestions_4parties_sommeTotale() {
        // 4 × 10 = 40
        joueur.addPartie(new PartieDTO(6, 10, 202));
        joueur.addPartie(new PartieDTO(7, 10, 753));
        joueur.addPartie(new PartieDTO(7, 10, 247));
        joueur.addPartie(new PartieDTO(2, 10, 420));

        assertEquals(40, stub.calculerTotalQuestions(joueur));
    }

    @Test
    void calculerTotalQuestions_partiesDeNombresDifferents() {
        // 5 + 8 + 12 = 25
        joueur.addPartie(new PartieDTO(3,  5, 100));
        joueur.addPartie(new PartieDTO(6,  8, 200));
        joueur.addPartie(new PartieDTO(9, 12, 300));

        assertEquals(25, stub.calculerTotalQuestions(joueur));
    }
}
