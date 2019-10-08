package fr.eseo.dis.android.vpmb.db.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName="Grade",primaryKeys = "pseudoJury",
        foreignKeys= @ForeignKey(entity=PseudoJury.class, parentColumns = "idPseudoJury", childColumns = "pseudoJury"))

public class Grade {

    @PrimaryKey
    long idNotation;

    @ColumnInfo(name="note")
    double note;

    @ColumnInfo(name="note")
    String commentaire;

    @ColumnInfo(name="pseudoJury")
    long pseudoJury;

    public Grade(long dNotation, double note, String commentaire, long idPseudoJury) {
        this.idNotation = dNotation;
        this.note = note;
        this.commentaire = commentaire;
        this.pseudoJury = idPseudoJury;
    }

    public long getIdNotation() {
        return idNotation;
    }

    public void setIdNotation(long dNotation) {
        this.idNotation = dNotation;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
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
                "dNotation=" + idNotation +
                ", note=" + note +
                ", commentaire='" + commentaire + '\'' +
                ", idPseudoJury=" + pseudoJury +
                '}';
    }


}
