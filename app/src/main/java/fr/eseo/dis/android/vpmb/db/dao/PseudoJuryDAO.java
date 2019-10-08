package fr.eseo.dis.android.vpmb.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import fr.eseo.dis.android.vpmb.db.models.PseudoJury;

@Dao
public interface PseudoJuryDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public long insert(PseudoJury pseudoJuryÒ);
}
