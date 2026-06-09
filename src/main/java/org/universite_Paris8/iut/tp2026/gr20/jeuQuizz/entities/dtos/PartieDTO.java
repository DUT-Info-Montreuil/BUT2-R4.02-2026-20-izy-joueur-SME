package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.entities.dtos;

public class PartieDTO {

    private final int  bonnesReponses;
    private final int  totalQuestions;
    private final long dureeSecondes;

    public PartieDTO(int bonnesReponses, int totalQuestions, long dureeSecondes) {
        this.bonnesReponses = bonnesReponses;
        this.totalQuestions = totalQuestions;
        this.dureeSecondes  = dureeSecondes;
    }

    public int  getBonnesReponses() { return bonnesReponses; }
    public int  getTotalQuestions() { return totalQuestions; }
    public long getDureeSecondes()  { return dureeSecondes; }
}
