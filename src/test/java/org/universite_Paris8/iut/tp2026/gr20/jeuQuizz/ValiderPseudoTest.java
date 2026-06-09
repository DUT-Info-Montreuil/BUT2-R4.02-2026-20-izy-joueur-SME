package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.JoueurServiceStub;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.utils.exceptions.InvalidPseudoException;

import static org.junit.jupiter.api.Assertions.*;

public class ValiderPseudoTest {

    private JoueurServiceStub stub;

    @BeforeEach
    void setUp() {
        stub = new JoueurServiceStub();
    }

    @Test
    void validerPseudo_valide() throws Exception {
        assertTrue(stub.validerPseudo("ali77"));
    }

    @Test
    void validerPseudo_tropCourt() {
        stub.lancerInvalidPseudo = true;
        assertThrows(InvalidPseudoException.class, () -> stub.validerPseudo("ab"));
    }

    @Test
    void validerPseudo_null() {
        stub.lancerInvalidPseudo = true;
        assertThrows(InvalidPseudoException.class, () -> stub.validerPseudo(null));
    }

    @Test
    void validerPseudo_avecEspaces() {
        stub.lancerInvalidPseudo = true;
        assertThrows(InvalidPseudoException.class, () -> stub.validerPseudo("ali 77"));
    }
}
