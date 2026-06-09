package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.JoueurDTO;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.impls.JoueurServiceImpl;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.interfaces.IJoueurService;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.utils.exceptions.*;

import static org.junit.jupiter.api.Assertions.*;

public class CreatePlayerTest {

    private IJoueurService stub;
    private JoueurDTO joueurValide;

    @BeforeEach
    void setUp() {
        stub = new JoueurServiceImpl();
        joueurValide = new JoueurDTO("Alice", "ali77", 2000, 1, "gaming,lecture");
    }

    @Test
    void createPlayer_nominal() throws Exception {
        JoueurDTO result = stub.createPlayer(joueurValide);

        assertNotNull(result);
        assertEquals("Alice", result.getPrenom());
        assertEquals("ali77", result.getPseudo());
        assertEquals(2000, result.getAnneeNaissance());
    }

    @Test
    void createPlayer_prenomInvalide() {
        JoueurDTO dto = new JoueurDTO("Al1ce", "ali77", 2000, 1, "gaming");
        assertThrows(InvalidPrenomException.class, () -> stub.createPlayer(dto));
    }

    @Test
    void createPlayer_pseudoInvalide() {
        JoueurDTO dto = new JoueurDTO("Alice", "1ali", 2000, 1, "gaming");
        assertThrows(InvalidPseudoException.class, () -> stub.createPlayer(dto));
    }

    @Test
    void createPlayer_anneeInvalide() {
        JoueurDTO dto = new JoueurDTO("Alice", "ali77", 1800, 1, "gaming");
        assertThrows(InvalidAnneeNaissanceException.class, () -> stub.createPlayer(dto));
    }

    @Test
    void createPlayer_langueInvalide() {
        JoueurDTO dto = new JoueurDTO("Alice", "ali77", 2000, 99, "gaming");
        assertThrows(InvalidLangueException.class, () -> stub.createPlayer(dto));
    }

    @Test
    void createPlayer_hobbiesInvalides() {
        JoueurDTO dto = new JoueurDTO("Alice", "ali77", 2000, 1, ",gaming");
        assertThrows(InvalidHobbiesException.class, () -> stub.createPlayer(dto));
    }
}