package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.impls;

import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos.JoueurDTO;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.services.interfaces.IJoueurService;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.utils.enums.Langue;
import org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.utils.exceptions.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class JoueurServiceImpl implements IJoueurService {

    private final List<JoueurDTO> listeJoueurs = new ArrayList<>();

    @Override
    public JoueurDTO createPlayer(JoueurDTO joueur)
            throws InvalidPrenomException, InvalidPseudoException,
            InvalidAnneeNaissanceException, InvalidLangueException,
            InvalidHobbiesException {

        validerPrenom(joueur.getPrenom());
        validerPseudo(joueur.getPseudo());
        validerAnneeNaissance(joueur.getAnneeNaissance());
        validerLangue(joueur.getLangue());
        validerHobbies(joueur.getHobbies());

        listeJoueurs.add(joueur);
        return joueur;
    }

    @Override
    public List<JoueurDTO> listerJoueurs() throws NoPlayerAvailableException {
        if (listeJoueurs == null || listeJoueurs.isEmpty()) {
            throw new NoPlayerAvailableException("Aucun joueur enregistré.");
        }
        List<JoueurDTO> sorted = new ArrayList<>(listeJoueurs);
        sorted.sort(Comparator.comparing(j -> j.getPseudo().toLowerCase()));
        return sorted;
    }

    @Override
    public boolean validerPrenom(String prenom) throws InvalidPrenomException {
        if (prenom == null || prenom.isBlank()) {
            throw new InvalidPrenomException("Le prénom ne peut pas être vide.");
        }
        if (!prenom.matches("[\\p{L}\\s'-]+")) {
            throw new InvalidPrenomException("Le prénom contient des caractères invalides : " + prenom);
        }
        return true;
    }

    @Override
    public boolean validerPseudo(String pseudo) throws InvalidPseudoException {
        if (pseudo == null || pseudo.isBlank()) {
            throw new InvalidPseudoException("Le pseudo ne peut pas être vide.");
        }
        if (Character.isDigit(pseudo.charAt(0))) {
            throw new InvalidPseudoException("Le pseudo ne doit pas commencer par un chiffre : " + pseudo);
        }
        boolean exists = listeJoueurs.stream()
                .anyMatch(j -> j.getPseudo().equals(pseudo));
        if (exists) {
            throw new InvalidPseudoException("Le pseudo est déjà utilisé : " + pseudo);
        }
        return true;
    }

    @Override
    public boolean validerAnneeNaissance(int annee) throws InvalidAnneeNaissanceException {
        int anneeMin = 1900;
        int anneeMax = Year.now().getValue();
        if (annee < anneeMin || annee > anneeMax) {
            throw new InvalidAnneeNaissanceException(
                    "L'année de naissance doit être comprise entre " + anneeMin + " et " + anneeMax + ".");
        }
        return true;
    }

    @Override
    public Langue validerLangue(int choix) throws InvalidLangueException {
        Langue langue = Langue.fromCode(choix);
        if (langue == null) {
            throw new InvalidLangueException("La langue doit être comprise entre 1 et 5, reçu : " + choix);
        }
        return langue;
    }

    @Override
    public List<String> validerHobbies(String hobbies) throws InvalidHobbiesException {
        if (hobbies == null || hobbies.isBlank()) {
            return new ArrayList<>();
        }
        if (hobbies.startsWith(",") || hobbies.endsWith(",")) {
            throw new InvalidHobbiesException("Les centres d'intérêts ne doivent pas commencer ou finir par une virgule.");
        }
        if (hobbies.contains(",,")) {
            throw new InvalidHobbiesException("Les centres d'intérêts ne doivent pas contenir de virgules consécutives.");
        }
        return Arrays.stream(hobbies.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();
    }
}