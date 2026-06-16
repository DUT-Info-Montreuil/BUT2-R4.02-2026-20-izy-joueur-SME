package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.JoueurDTO;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.PartieDTO;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.impls.StatsServiceImpl;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.interfaces.IStatsService;

import static org.junit.jupiter.api.Assertions.*;

public class AJoueTest {

    private IStatsService stub;
    private JoueurDTO joueur;

    @BeforeEach
    void setUp() {
        stub   = new StatsServiceImpl();
        joueur = new JoueurDTO("Alice", "ali77", 2000, 1, "gaming");
    }

    @Test
    void aJoue_sansPartie_retourneFalse() {
        assertFalse(stub.aJoue(joueur));
    }

    @Test
    void aJoue_avecUnePartie_retourneTrue() {
        joueur.addPartie(new PartieDTO(5, 10, 300));

        assertTrue(stub.aJoue(joueur));
    }

    @Test
    void aJoue_avecPlusieursParties_retourneTrue() {
        joueur.addPartie(new PartieDTO(5, 10, 300));
        joueur.addPartie(new PartieDTO(7, 10, 420));

        assertTrue(stub.aJoue(joueur));
    }
}
