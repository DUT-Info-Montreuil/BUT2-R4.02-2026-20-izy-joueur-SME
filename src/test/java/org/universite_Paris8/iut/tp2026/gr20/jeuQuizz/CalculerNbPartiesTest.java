package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.JoueurDTO;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.PartieDTO;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.mocks.StatsServiceMock;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.interfaces.IStatsService;

import static org.junit.jupiter.api.Assertions.*;

public class CalculerNbPartiesTest {

    private IStatsService stub;
    private JoueurDTO joueur;

    @BeforeEach
    void setUp() {
        stub   = new StatsServiceMock();
        joueur = new JoueurDTO("Alice", "ali77", 2000, 1, "gaming");
    }

    @Test
    void calculerNbParties_sansPartie_retourneZero() {
        assertEquals(0, stub.calculerNbParties(joueur));
    }

    @Test
    void calculerNbParties_unePartie_retourneUn() {
        joueur.addPartie(new PartieDTO(5, 10, 300));

        assertEquals(1, stub.calculerNbParties(joueur));
    }

    @Test
    void calculerNbParties_quatreParties_retourneQuatre() {
        joueur.addPartie(new PartieDTO(6, 10, 202));
        joueur.addPartie(new PartieDTO(7, 10, 753));
        joueur.addPartie(new PartieDTO(7, 10, 247));
        joueur.addPartie(new PartieDTO(2, 10, 420));

        assertEquals(4, stub.calculerNbParties(joueur));
    }
}
