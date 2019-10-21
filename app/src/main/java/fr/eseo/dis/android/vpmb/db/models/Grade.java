package fr.eseo.dis.android.vpmb.db.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName="Grade",
        foreignKeys= {@ForeignKey(entity=PseudoJury.class, parentColumns = "idPseudoJury", childColumns = "pseudoJury"),
                @ForeignKey(entity=Project.class, parentColumns = "idProject", childColumns = "project")})

public class Grade {

    @PrimaryKey
    long idNotation;

    @ColumnInfo(name="note")
    double note;

    @ColumnInfo(name="commentaire")
    String commentaire;

    @ColumnInfo(name="pseudoJury")
    long pseudoJury;

    @ColumnInfo(name="project")
    long idSubject;


    public Grade(long idNotation, double note, String commentaire, long pseudoJury, long idSubject) {
        this.idNotation = idNotation;
        this.note = note;
        this.commentaire = commentaire;
        this.pseudoJury = pseudoJury;
        this.idSubject = idSubject;
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

    public long getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(long idSubject) {
        this.idSubject = idSubject;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "idNotation=" + idNotation +
                ", note=" + note +
                ", commentaire='" + commentaire + '\'' +
                ", pseudoJury=" + pseudoJury +
                ", idSubject=" + idSubject +
                '}';
    }
}
