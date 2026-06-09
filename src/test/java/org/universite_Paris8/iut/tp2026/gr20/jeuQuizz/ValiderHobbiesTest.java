package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.JoueurServiceStub;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.utils.exceptions.InvalidHobbiesException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ValiderHobbiesTest {

    private JoueurServiceStub stub;

    @BeforeEach
    void setUp() {
        stub = new JoueurServiceStub();
    }

    @Test
    void validerHobbies_valides() throws Exception {
        stub.hobbiesRetournes = Arrays.asList("gaming", "lecture");

        List<String> result = stub.validerHobbies("gaming,lecture");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains("gaming"));
        assertTrue(result.contains("lecture"));
    }

    @Test
    void validerHobbies_unSeul() throws Exception {
        stub.hobbiesRetournes = Collections.singletonList("sport");

        List<String> result = stub.validerHobbies("sport");

        assertEquals(1, result.size());
        assertEquals("sport", result.get(0));
    }

    @Test
    void validerHobbies_vides() {
        stub.lancerInvalidHobbies = true;
        assertThrows(InvalidHobbiesException.class, () -> stub.validerHobbies(""));
    }

    @Test
    void validerHobbies_null() {
        stub.lancerInvalidHobbies = true;
        assertThrows(InvalidHobbiesException.class, () -> stub.validerHobbies(null));
    }

    @Test
    void validerHobbies_formatInvalide() {
        stub.lancerInvalidHobbies = true;
        assertThrows(InvalidHobbiesException.class, () -> stub.validerHobbies("gaming;lecture"));
    }
}
