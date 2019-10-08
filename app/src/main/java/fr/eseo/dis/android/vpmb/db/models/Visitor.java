package fr.eseo.dis.android.vpmb.db.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName="Visitor", primaryKeys = "pseudoJury",
        foreignKeys =
                @ForeignKey(entity=PseudoJury.class, parentColumns = "idPseudoJury",childColumns = "pseudoJury"))

public class Visitor {

    @PrimaryKey
    @NonNull
    long idVisiteur ;

    @ColumnInfo(name = "pseudoJury")
    @NonNull
    long pseudoJury;

    public Visitor(long idVisiteur, long idPseudoJury) {
        this.idVisiteur = idVisiteur;
        this.pseudoJury = idPseudoJury;
    }

    public long getIdVisiteur() {
        return idVisiteur;
    }

    public void setIdVisiteur(long idVisiteur) {
        this.idVisiteur = idVisiteur;
    }

    public long getPseudoJury() {
        return pseudoJury;
    }

    public void setPseudoJury(long idPseudoJury) {
        this.pseudoJury = idPseudoJury;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "idVisiteur=" + idVisiteur +
                ", idPseudoJury=" + pseudoJury +
                '}';
    }
}
