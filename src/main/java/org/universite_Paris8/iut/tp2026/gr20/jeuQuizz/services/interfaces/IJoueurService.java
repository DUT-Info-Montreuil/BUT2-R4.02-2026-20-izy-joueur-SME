package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.interfaces;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.JoueurDTO;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.utils.enums.Langue;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.utils.exceptions.*;

import java.util.List;

public interface IJoueurService {

    JoueurDTO createPlayer(JoueurDTO joueur)
            throws InvalidPrenomException, InvalidPseudoException,
            InvalidAnneeNaissanceException, InvalidLangueException,
            InvalidHobbiesException;

    List<JoueurDTO> listerJoueurs() throws NoPlayerAvailableException;

    boolean validerPrenom(String prenom) throws InvalidPrenomException;
    boolean validerPseudo(String pseudo) throws InvalidPseudoException;
    boolean validerAnneeNaissance(int annee) throws InvalidAnneeNaissanceException;
    Langue validerLangue(int choix) throws InvalidLangueException;
    List<String> validerHobbies(String hobbies) throws InvalidHobbiesException;
}