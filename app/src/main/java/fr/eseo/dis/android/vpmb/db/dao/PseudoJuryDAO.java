package fr.eseo.dis.android.vpmb.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import fr.eseo.dis.android.vpmb.db.models.Project;
import fr.eseo.dis.android.vpmb.db.models.PseudoJury;

@Dao
public interface PseudoJuryDAO {

    @Insert
    void insert(PseudoJury pseudoJury);

    @Query("SELECT * FROM PseudoJury")
    List<PseudoJury> loadAll();
}
