package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.JoueurDTO;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.PartieDTO;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.mocks.StatsServiceMock;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.interfaces.IStatsService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GetDernieresPartiesTest {

    private IStatsService stub;
    private JoueurDTO joueur;

    @BeforeEach
    void setUp() {
        stub   = new StatsServiceMock();
        joueur = new JoueurDTO("Alice", "ali77", 2000, 1, "gaming");
    }

    @Test
    void getDernieresParties_sansPartie_retourneListeVide() {
        List<PartieDTO> result = stub.getDernieresParties(joueur);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void getDernieresParties_moinsDe5_retourneListeVide() {
        joueur.addPartie(new PartieDTO(6, 10, 202));
        joueur.addPartie(new PartieDTO(7, 10, 753));
        joueur.addPartie(new PartieDTO(7, 10, 247));
        joueur.addPartie(new PartieDTO(2, 10, 420));

        List<PartieDTO> result = stub.getDernieresParties(joueur);

        assertTrue(result.isEmpty());
    }

    @Test
    void getDernieresParties_exactement5_retourne5Elements() {
        joueur.addPartie(new PartieDTO(6, 10, 202));
        joueur.addPartie(new PartieDTO(7, 10, 753));
        joueur.addPartie(new PartieDTO(7, 10, 247));
        joueur.addPartie(new PartieDTO(2, 10, 420));
        joueur.addPartie(new PartieDTO(9, 10, 310));

        List<PartieDTO> result = stub.getDernieresParties(joueur);

        assertEquals(5, result.size());
    }

    @Test
    void getDernieresParties_7parties_retourne5Elements() {
        joueur.addPartie(new PartieDTO(1, 10, 100));
        joueur.addPartie(new PartieDTO(2, 10, 200));
        joueur.addPartie(new PartieDTO(3, 10, 300));
        joueur.addPartie(new PartieDTO(4, 10, 400));
        joueur.addPartie(new PartieDTO(5, 10, 500));
        joueur.addPartie(new PartieDTO(6, 10, 600));
        joueur.addPartie(new PartieDTO(7, 10, 700));

        List<PartieDTO> result = stub.getDernieresParties(joueur);

        assertEquals(5, result.size());
    }

    @Test
    void getDernieresParties_7parties_laPlusRecenteEnPremier() {
        joueur.addPartie(new PartieDTO(1, 10, 100));
        joueur.addPartie(new PartieDTO(2, 10, 200));
        joueur.addPartie(new PartieDTO(3, 10, 300));
        joueur.addPartie(new PartieDTO(4, 10, 400));
        joueur.addPartie(new PartieDTO(5, 10, 500));
        joueur.addPartie(new PartieDTO(6, 10, 600));
        joueur.addPartie(new PartieDTO(7, 10, 700)); // la plus récente → index 0

        List<PartieDTO> result = stub.getDernieresParties(joueur);

        assertEquals(7, result.get(0).getBonnesReponses());
    }

    @Test
    void getDernieresParties_7parties_la5emeEnDernier() {
        joueur.addPartie(new PartieDTO(1, 10, 100)); // ignorée
        joueur.addPartie(new PartieDTO(2, 10, 200)); // ignorée
        joueur.addPartie(new PartieDTO(3, 10, 300)); // → index 4
        joueur.addPartie(new PartieDTO(4, 10, 400));
        joueur.addPartie(new PartieDTO(5, 10, 500));
        joueur.addPartie(new PartieDTO(6, 10, 600));
        joueur.addPartie(new PartieDTO(7, 10, 700));

        List<PartieDTO> result = stub.getDernieresParties(joueur);

        assertEquals(3, result.get(4).getBonnesReponses());
    }
}
