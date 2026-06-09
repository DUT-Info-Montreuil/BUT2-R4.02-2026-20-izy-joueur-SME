package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.impls.JoueurServiceImpl;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.interfaces.IJoueurService;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.utils.enums.Langue;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.utils.exceptions.InvalidLangueException;

import static org.junit.jupiter.api.Assertions.*;

public class ValiderLangueTest {

    private IJoueurService stub;

    @BeforeEach
    void setUp() {
        stub = new JoueurServiceImpl();
    }

    @Test
    void validerLangue_francais() throws Exception {
        assertEquals(Langue.FRANCAIS, stub.validerLangue(1));
    }

    @Test
    void validerLangue_anglais() throws Exception {
        assertEquals(Langue.ANGLAIS, stub.validerLangue(2));
    }

    @Test
    void validerLangue_allemand() throws Exception {
        assertEquals(Langue.ALLEMAND, stub.validerLangue(3));
    }

    @Test
    void validerLangue_espagnol() throws Exception {
        assertEquals(Langue.ESPAGNOL, stub.validerLangue(4));
    }

    @Test
    void validerLangue_italien() throws Exception {
        assertEquals(Langue.ITALIEN, stub.validerLangue(5));
    }

    @Test
    void validerLangue_codeInconnu() {
        assertThrows(InvalidLangueException.class, () -> stub.validerLangue(99));
    }

    @Test
    void validerLangue_codeZero() {
        assertThrows(InvalidLangueException.class, () -> stub.validerLangue(0));
    }
}