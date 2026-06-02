package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos;

public class JoueurDTO {
    private String prenom;
    private String pseudo;
    private int anneeNaissance;
    private int langue;
    private String hobbies;

    public JoueurDTO(String prenom, String pseudo, int anneeNaissance, int langue, String hobbies) {
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.anneeNaissance = anneeNaissance;
        this.langue = langue;
        this.hobbies = hobbies;
    }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getPseudo() { return pseudo; }
    public void setPseudo(String pseudo) { this.pseudo = pseudo; }

    public int getAnneeNaissance() { return anneeNaissance; }
    public void setAnneeNaissance(int anneeNaissance) { this.anneeNaissance = anneeNaissance; }

    public int getLangue() { return langue; }
    public void setLangue(int langue) { this.langue = langue; }

    public String getHobbies() { return hobbies; }
    public void setHobbies(String hobbies) { this.hobbies = hobbies; }

    public String toString(){
        return "\n Prenom : "+getPrenom()+", Pseudo : "+getPseudo();
    }
}