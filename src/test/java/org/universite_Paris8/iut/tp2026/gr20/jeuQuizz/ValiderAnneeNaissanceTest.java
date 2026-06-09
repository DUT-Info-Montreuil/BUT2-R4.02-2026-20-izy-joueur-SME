package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.JoueurServiceStub;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.utils.exceptions.InvalidAnneeNaissanceException;

import static org.junit.jupiter.api.Assertions.*;

public class ValiderAnneeNaissanceTest {

    private JoueurServiceStub stub;

    @BeforeEach
    void setUp() {
        stub = new JoueurServiceStub();
    }

    @Test
    void validerAnnee_valide() throws Exception {
        assertTrue(stub.validerAnneeNaissance(2000));
    }

    @Test
    void validerAnnee_tropAncienne() {
        stub.lancerInvalidAnnee = true;
        assertThrows(InvalidAnneeNaissanceException.class, () -> stub.validerAnneeNaissance(1800));
    }

    @Test
    void validerAnnee_dansLeFutur() {
        stub.lancerInvalidAnnee = true;
        assertThrows(InvalidAnneeNaissanceException.class, () -> stub.validerAnneeNaissance(2100));
    }

    @Test
    void validerAnnee_negative() {
        stub.lancerInvalidAnnee = true;
        assertThrows(InvalidAnneeNaissanceException.class, () -> stub.validerAnneeNaissance(-1));
    }
}
