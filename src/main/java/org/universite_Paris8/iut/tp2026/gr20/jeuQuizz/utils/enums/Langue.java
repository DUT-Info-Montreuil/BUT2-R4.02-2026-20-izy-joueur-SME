package org.universite_Paris8.iut.tp2026.gr20.jeuQuizz.utils.enums;

public enum Langue {
    FRANCAIS(1),
    ANGLAIS(2),
    ALLEMAND(3),
    ESPAGNOL(4),
    ITALIEN(5);

    private final int code;

    Langue(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Langue fromCode(int code) {
        for (Langue l : values()) {
            if (l.code == code) return l;
        }
        return null;
    }
}