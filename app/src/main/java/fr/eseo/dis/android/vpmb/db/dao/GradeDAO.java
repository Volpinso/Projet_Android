package fr.eseo.dis.android.vpmb.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import fr.eseo.dis.android.vpmb.db.models.Grade;
import fr.eseo.dis.android.vpmb.db.models.PseudoJury;

@Dao
public interface GradeDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Grade grade);

    @Query("SELECT * FROM Grade")
    List<Grade> loadAll();

    @Query("SELECT * FROM Grade WHERE idNotation= :idSubject")
    List<Grade> loadSubjectGrade(long idSubject);
}
