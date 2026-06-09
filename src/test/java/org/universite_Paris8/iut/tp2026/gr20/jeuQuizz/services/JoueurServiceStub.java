package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.JoueurDTO;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.interfaces.IJoueurService;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.utils.enums.Langue;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.utils.exceptions.*;

import java.util.List;

public class JoueurServiceStub implements IJoueurService {

    public boolean lancerInvalidPrenom  = false;
    public boolean lancerInvalidPseudo  = false;
    public boolean lancerInvalidAnnee   = false;
    public boolean lancerInvalidLangue  = false;
    public boolean lancerInvalidHobbies = false;
    public boolean lancerNoPlayer       = false;

    public JoueurDTO joueurRetourne       = null;
    public List<JoueurDTO> listeRetournee = null;
    public Langue langueRetournee         = null;
    public List<String> hobbiesRetournes  = null;

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
