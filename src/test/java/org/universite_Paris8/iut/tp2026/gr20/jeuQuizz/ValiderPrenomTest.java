package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.impls.JoueurServiceImpl;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.interfaces.IJoueurService;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.utils.exceptions.InvalidPrenomException;

import static org.junit.jupiter.api.Assertions.*;

public class ValiderPrenomTest {

    private IJoueurService stub;

    @BeforeEach
    void setUp() {
        stub = new JoueurServiceImpl();
    }

    @Test
    void validerPrenom_valide() throws Exception {
        assertTrue(stub.validerPrenom("Alice"));
    }

    @Test
    void validerPrenom_invalide() {
        assertThrows(InvalidPrenomException.class, () -> stub.validerPrenom(""));
    }

    @Test
    void validerPrenom_null() {
        assertThrows(InvalidPrenomException.class, () -> stub.validerPrenom(null));
    }

    @Test
    void validerPrenom_avecChiffres() {
        assertThrows(InvalidPrenomException.class, () -> stub.validerPrenom("Al1ce"));
    }
}