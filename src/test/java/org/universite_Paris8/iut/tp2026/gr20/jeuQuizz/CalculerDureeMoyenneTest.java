package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.JoueurDTO;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.PartieDTO;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.mocks.StatsServiceMock;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.interfaces.IStatsService;

import static org.junit.jupiter.api.Assertions.*;

public class CalculerDureeMoyenneTest {

    private IStatsService stub;
    private JoueurDTO joueur;

    @BeforeEach
    void setUp() {
        stub   = new StatsServiceMock();
        joueur = new JoueurDTO("Alice", "ali77", 2000, 1, "gaming");
    }

    @Test
    void calculerDureeMoyenne_unePartie_dureeMoyenneEgaleDureePartie() {
        joueur.addPartie(new PartieDTO(6, 10, 300));

        assertEquals(300L, stub.calculerDureeMoyenne(joueur));
    }

    @Test
    void calculerDureeMoyenne_4parties_calculCorrect() {
        // (202 + 753 + 247 + 420) / 4 = 405.5 → arrondi à 406
        joueur.addPartie(new PartieDTO(6, 10, 202));
        joueur.addPartie(new PartieDTO(7, 10, 753));
        joueur.addPartie(new PartieDTO(7, 10, 247));
        joueur.addPartie(new PartieDTO(2, 10, 420));

        assertEquals(406L, stub.calculerDureeMoyenne(joueur));
    }

    @Test
    void calculerDureeMoyenne_dureesEgales_moyennePrecise() {
        // 3 × 120s → exactement 120s
        joueur.addPartie(new PartieDTO(5, 10, 120));
        joueur.addPartie(new PartieDTO(5, 10, 120));
        joueur.addPartie(new PartieDTO(5, 10, 120));

        assertEquals(120L, stub.calculerDureeMoyenne(joueur));
    }

    @Test
    void calculerDureeMoyenne_arrondiAuDessus() {
        // (100 + 101) / 2 = 100.5 → 101
        joueur.addPartie(new PartieDTO(5, 10, 100));
        joueur.addPartie(new PartieDTO(5, 10, 101));

        assertEquals(101L, stub.calculerDureeMoyenne(joueur));
    }

    @Test
    void calculerDureeMoyenne_resultatEntier_pasArrondi() {
        // (100 + 102) / 2 = 101.0 → 101
        joueur.addPartie(new PartieDTO(5, 10, 100));
        joueur.addPartie(new PartieDTO(5, 10, 102));

        assertEquals(101L, stub.calculerDureeMoyenne(joueur));
    }
}
