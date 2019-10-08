package fr.eseo.dis.android.vpmb.db.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="PseudoJury")
public class PseudoJury {

    @PrimaryKey
    @NonNull
    long idPseudoJury;

    public PseudoJury(long idPseudoJury) {
        this.idPseudoJury = idPseudoJury;
    }

    public long getIdPseudoJury() {
        return idPseudoJury;
    }

    public void setIdPseudoJury(long idPseudoJury) {
        this.idPseudoJury = idPseudoJury;
    }

    @Override
    public String toString() {
        return "PseudoJury{" +
                "idPseudoJury=" + idPseudoJury +
                '}';
    }
}
