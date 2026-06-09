package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.JoueurDTO;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.JoueurServiceStub;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.utils.exceptions.*;

import static org.junit.jupiter.api.Assertions.*;

public class CreatePlayerTest {

    private JoueurServiceStub stub;
    private JoueurDTO joueurValide;

    @BeforeEach
    void setUp() {
        stub = new JoueurServiceStub();
        joueurValide = new JoueurDTO("Alice", "ali77", 2000, 1, "gaming,lecture");
    }

    @Test
    void createPlayer_nominal() throws Exception {
        stub.joueurRetourne = joueurValide;

        JoueurDTO result = stub.createPlayer(joueurValide);

        assertNotNull(result);
        assertEquals("Alice", result.getPrenom());
        assertEquals("ali77", result.getPseudo());
        assertEquals(2000, result.getAnneeNaissance());
    }

    @Test
    void createPlayer_prenomInvalide() {
        stub.lancerInvalidPrenom = true;
        assertThrows(InvalidPrenomException.class, () -> stub.createPlayer(joueurValide));
    }

    @Test
    void createPlayer_pseudoInvalide() {
        stub.lancerInvalidPseudo = true;
        assertThrows(InvalidPseudoException.class, () -> stub.createPlayer(joueurValide));
    }

    @Test
    void createPlayer_anneeInvalide() {
        stub.lancerInvalidAnnee = true;
        assertThrows(InvalidAnneeNaissanceException.class, () -> stub.createPlayer(joueurValide));
    }

    @Test
    void createPlayer_langueInvalide() {
        stub.lancerInvalidLangue = true;
        assertThrows(InvalidLangueException.class, () -> stub.createPlayer(joueurValide));
    }

    @Test
    void createPlayer_hobbiesInvalides() {
        stub.lancerInvalidHobbies = true;
        assertThrows(InvalidHobbiesException.class, () -> stub.createPlayer(joueurValide));
    }
}
