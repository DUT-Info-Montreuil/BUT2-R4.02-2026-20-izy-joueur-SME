package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.impls.JoueurServiceImpl;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.interfaces.IJoueurService;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.utils.exceptions.InvalidHobbiesException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ValiderHobbiesTest {

    private IJoueurService stub;

    @BeforeEach
    void setUp() {
        stub = new JoueurServiceImpl();
    }

    @Test
    void validerHobbies_valides() throws Exception {
        List<String> result = stub.validerHobbies("gaming,lecture");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains("gaming"));
        assertTrue(result.contains("lecture"));
    }

    @Test
    void validerHobbies_unSeul() throws Exception {
        List<String> result = stub.validerHobbies("sport");

        assertEquals(1, result.size());
        assertEquals("sport", result.get(0));
    }

    @Test
    void validerHobbies_vides() throws Exception {
        // L'impl retourne une liste vide pour une chaine vide (pas d'exception)
        List<String> result = stub.validerHobbies("");
        assertTrue(result.isEmpty());
    }

    @Test
    void validerHobbies_null() throws Exception {
        // L'impl retourne une liste vide pour null (pas d'exception)
        List<String> result = stub.validerHobbies(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void validerHobbies_formatInvalide() {
        // L'impl refuse les hobbies commencant par une virgule
        assertThrows(InvalidHobbiesException.class, () -> stub.validerHobbies(",gaming"));
    }
}