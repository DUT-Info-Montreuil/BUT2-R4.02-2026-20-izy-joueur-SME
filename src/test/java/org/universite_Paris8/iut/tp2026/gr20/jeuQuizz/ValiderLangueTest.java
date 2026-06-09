package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.JoueurServiceStub;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.utils.enums.Langue;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.utils.exceptions.InvalidLangueException;

import static org.junit.jupiter.api.Assertions.*;

public class ValiderLangueTest {

    private JoueurServiceStub stub;

    @BeforeEach
    void setUp() {
        stub = new JoueurServiceStub();
    }

    @Test
    void validerLangue_francais() throws Exception {
        stub.langueRetournee = Langue.FRANCAIS;
        assertEquals(Langue.FRANCAIS, stub.validerLangue(1));
    }

    @Test
    void validerLangue_anglais() throws Exception {
        stub.langueRetournee = Langue.ANGLAIS;
        assertEquals(Langue.ANGLAIS, stub.validerLangue(2));
    }

    @Test
    void validerLangue_allemand() throws Exception {
        stub.langueRetournee = Langue.ALLEMAND;
        assertEquals(Langue.ALLEMAND, stub.validerLangue(3));
    }

    @Test
    void validerLangue_espagnol() throws Exception {
        stub.langueRetournee = Langue.ESPAGNOL;
        assertEquals(Langue.ESPAGNOL, stub.validerLangue(4));
    }

    @Test
    void validerLangue_italien() throws Exception {
        stub.langueRetournee = Langue.ITALIEN;
        assertEquals(Langue.ITALIEN, stub.validerLangue(5));
    }

    @Test
    void validerLangue_codeInconnu() {
        stub.lancerInvalidLangue = true;
        assertThrows(InvalidLangueException.class, () -> stub.validerLangue(99));
    }

    @Test
    void validerLangue_codeZero() {
        stub.lancerInvalidLangue = true;
        assertThrows(InvalidLangueException.class, () -> stub.validerLangue(0));
    }
}
