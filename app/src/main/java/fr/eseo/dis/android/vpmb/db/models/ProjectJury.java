package fr.eseo.dis.android.vpmb.db.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName="ProjectJury",primaryKeys = {"idSubject","pseudoJury"},
        foreignKeys= {@ForeignKey(entity=Project.class, parentColumns = "idProject", childColumns = "idSubject", onDelete = CASCADE ),
                @ForeignKey(entity=PseudoJury.class, parentColumns = "idPseudoJury",childColumns = "pseudoJury", onDelete = CASCADE ) })


public class ProjectJury {

    @ColumnInfo(name="idSubject")
    @NonNull
    long idSubject;

    @ColumnInfo(name="pseudoJury")
    @NonNull
    long pseudoJury;

    public ProjectJury(long idSubject, long pseudoJury) {
        this.idSubject = idSubject;
        this.pseudoJury = pseudoJury;
    }

    public long getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(long idSubject) {
        this.idSubject = idSubject;
    }

    public long getPseudoJury() {
        return pseudoJury;
    }

    public void setPseudoJury(long pseudoJury) {
        this.pseudoJury = pseudoJury;
    }
}
