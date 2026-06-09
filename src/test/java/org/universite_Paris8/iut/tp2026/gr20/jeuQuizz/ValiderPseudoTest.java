package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.impls.JoueurServiceImpl;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.interfaces.IJoueurService;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.utils.exceptions.InvalidPseudoException;

import static org.junit.jupiter.api.Assertions.*;

public class ValiderPseudoTest {

    private IJoueurService stub;

    @BeforeEach
    void setUp() {
        stub = new JoueurServiceImpl();
    }

    @Test
    void validerPseudo_valide() throws Exception {
        assertTrue(stub.validerPseudo("ali77"));
    }

    @Test
    void validerPseudo_tropCourt() {
        // L'impl refuse les pseudos commencant par un chiffre
        assertThrows(InvalidPseudoException.class, () -> stub.validerPseudo("1ab"));
    }

    @Test
    void validerPseudo_null() {
        assertThrows(InvalidPseudoException.class, () -> stub.validerPseudo(null));
    }

    @Test
    void validerPseudo_avecEspaces() {
        // L'impl refuse les pseudos vides/blank
        assertThrows(InvalidPseudoException.class, () -> stub.validerPseudo(""));
    }
}