package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.JoueurDTO;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.interfaces.IJoueurService;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.utils.enums.Langue;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.utils.exceptions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JoueurServiceTest {

    // =========================================================
    // Stub manuel de IJoueurService
    // =========================================================
    private static class JoueurServiceStub implements IJoueurService {

        boolean lancerInvalidPrenom      = false;
        boolean lancerInvalidPseudo      = false;
        boolean lancerInvalidAnnee       = false;
        boolean lancerInvalidLangue      = false;
        boolean lancerInvalidHobbies     = false;
        boolean lancerNoPlayer           = false;

        JoueurDTO joueurRetourne         = null;
        List<JoueurDTO> listeRetournee   = null;
        Langue langueRetournee           = null;
        List<String> hobbiesRetournes    = null;

        @Override
        public JoueurDTO createPlayer(JoueurDTO joueur)
                throws InvalidPrenomException, InvalidPseudoException,
                InvalidAnneeNaissanceException, InvalidLangueException,
                InvalidHobbiesException {

            if (lancerInvalidPrenom)  throw new InvalidPrenomException("Prenom invalide");
            if (lancerInvalidPseudo)  throw new InvalidPseudoException("Pseudo invalide");
            if (lancerInvalidAnnee)   throw new InvalidAnneeNaissanceException("Annee invalide");
            if (lancerInvalidLangue)  throw new InvalidLangueException("Langue invalide");
            if (lancerInvalidHobbies) throw new InvalidHobbiesException("Hobbies invalides");
            return joueurRetourne;
        }

        @Override
        public List<JoueurDTO> listerJoueurs() throws NoPlayerAvailableException {
            if (lancerNoPlayer) throw new NoPlayerAvailableException("Aucun joueur");
            return listeRetournee;
        }

        @Override
        public boolean validerPrenom(String prenom) throws InvalidPrenomException {
            if (lancerInvalidPrenom) throw new InvalidPrenomException("Prenom invalide");
            return true;
        }

        @Override
        public boolean validerPseudo(String pseudo) throws InvalidPseudoException {
            if (lancerInvalidPseudo) throw new InvalidPseudoException("Pseudo invalide");
            return true;
        }

        @Override
        public boolean validerAnneeNaissance(int annee) throws InvalidAnneeNaissanceException {
            if (lancerInvalidAnnee) throw new InvalidAnneeNaissanceException("Annee invalide");
            return true;
        }

        @Override
        public Langue validerLangue(int choix) throws InvalidLangueException {
            if (lancerInvalidLangue) throw new InvalidLangueException("Langue invalide");
            return langueRetournee;
        }

        @Override
        public List<String> validerHobbies(String hobbies) throws InvalidHobbiesException {
            if (lancerInvalidHobbies) throw new InvalidHobbiesException("Hobbies invalides");
            return hobbiesRetournes;
        }
    }

    private JoueurServiceStub stub;
    private JoueurDTO joueurValide;

    @BeforeEach
    void setUp() {
        stub = new JoueurServiceStub();
        joueurValide = new JoueurDTO("Alice", "ali77", 2000, 1, "gaming,lecture");
    }

    // =========================================================
    // createPlayer
    // =========================================================

    @Test
    void createPlayer_nominal() throws Exception {
        stub.joueurRetourne = joueurValide;

        JoueurDTO result = stub.createPlayer(joueurValide);

        assertNotNull(result);
        assertEquals("Alice", result.getPrenom());
        assertEquals("ali77", result.getPseudo());
        assertEquals(2000, result.getAnneeNaissance());
    }

    @Test
    void createPlayer_prenomInvalide() {
        stub.lancerInvalidPrenom = true;
        assertThrows(InvalidPrenomException.class, () -> stub.createPlayer(joueurValide));
    }

    @Test
    void createPlayer_pseudoInvalide() {
        stub.lancerInvalidPseudo = true;
        assertThrows(InvalidPseudoException.class, () -> stub.createPlayer(joueurValide));
    }

    @Test
    void createPlayer_anneeInvalide() {
        stub.lancerInvalidAnnee = true;
        assertThrows(InvalidAnneeNaissanceException.class, () -> stub.createPlayer(joueurValide));
    }

    @Test
    void createPlayer_langueInvalide() {
        stub.lancerInvalidLangue = true;
        assertThrows(InvalidLangueException.class, () -> stub.createPlayer(joueurValide));
    }

    @Test
    void createPlayer_hobbiesInvalides() {
        stub.lancerInvalidHobbies = true;
        assertThrows(InvalidHobbiesException.class, () -> stub.createPlayer(joueurValide));
    }

    // =========================================================
    // listerJoueurs
    // =========================================================

    @Test
    void listerJoueurs_listeNonVide() throws Exception {
        JoueurDTO j2 = new JoueurDTO("Bob", "bob99", 1999, 2, "sport");
        stub.listeRetournee = Arrays.asList(joueurValide, j2);

        List<JoueurDTO> result = stub.listerJoueurs();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getPrenom());
        assertEquals("Bob", result.get(1).getPrenom());
    }

    @Test
    void listerJoueurs_unSeulJoueur() throws Exception {
        stub.listeRetournee = Collections.singletonList(joueurValide);

        List<JoueurDTO> result = stub.listerJoueurs();

        assertEquals(1, result.size());
    }

    @Test
    void listerJoueurs_aucunJoueur() {
        stub.lancerNoPlayer = true;
        assertThrows(NoPlayerAvailableException.class, () -> stub.listerJoueurs());
    }

    // =========================================================
    // validerPrenom
    // =========================================================

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

    // =========================================================
    // validerPseudo
    // =========================================================

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

    // =========================================================
    // validerAnneeNaissance
    // =========================================================

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

    // =========================================================
    // validerLangue
    // =========================================================

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

    // =========================================================
    // validerHobbies
    // =========================================================

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
}