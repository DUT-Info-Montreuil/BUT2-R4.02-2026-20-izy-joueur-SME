package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.JoueurDTO;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.JoueurServiceStub;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.utils.exceptions.NoPlayerAvailableException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListerJoueursTest {

    private JoueurServiceStub stub;
    private JoueurDTO joueurValide;

    @BeforeEach
    void setUp() {
        stub = new JoueurServiceStub();
        joueurValide = new JoueurDTO("Alice", "ali77", 2000, 1, "gaming,lecture");
    }

    @Test
    void listerJoueurs_listeNonVide() throws Exception {
        JoueurDTO j2 = new JoueurDTO("Bob", "bob99", 1999, 2, "sport");
        stub.listeRetournee = Arrays.asList(joueurValide, j2);

        List<JoueurDTO> result = stub.listerJoueurs();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getPrenom());
        assertEquals("Bob", result.get(1).getPrenom());
    }

    @Test
    void listerJoueurs_unSeulJoueur() throws Exception {
        stub.listeRetournee = Collections.singletonList(joueurValide);

        List<JoueurDTO> result = stub.listerJoueurs();

        assertEquals(1, result.size());
    }

    @Test
    void listerJoueurs_aucunJoueur() {
        stub.lancerNoPlayer = true;
        assertThrows(NoPlayerAvailableException.class, () -> stub.listerJoueurs());
    }
}
