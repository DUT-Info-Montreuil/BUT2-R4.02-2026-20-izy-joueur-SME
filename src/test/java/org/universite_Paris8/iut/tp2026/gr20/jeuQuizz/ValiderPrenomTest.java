package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.JoueurServiceStub;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.utils.exceptions.InvalidPrenomException;

import static org.junit.jupiter.api.Assertions.*;

public class ValiderPrenomTest {

    private JoueurServiceStub stub;

    @BeforeEach
    void setUp() {
        stub = new JoueurServiceStub();
    }

    @Test
    void validerPrenom_valide() throws Exception {
        assertTrue(stub.validerPrenom("Alice"));
    }

    @Test
    void validerPrenom_invalide() {
        stub.lancerInvalidPrenom = true;
        assertThrows(InvalidPrenomException.class, () -> stub.validerPrenom(""));
    }

    @Test
    void validerPrenom_null() {
        stub.lancerInvalidPrenom = true;
        assertThrows(InvalidPrenomException.class, () -> stub.validerPrenom(null));
    }

    @Test
    void validerPrenom_avecChiffres() {
        stub.lancerInvalidPrenom = true;
        assertThrows(InvalidPrenomException.class, () -> stub.validerPrenom("Al1ce"));
    }
}
