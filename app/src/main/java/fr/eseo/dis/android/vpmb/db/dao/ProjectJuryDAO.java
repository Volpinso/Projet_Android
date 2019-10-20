package fr.eseo.dis.android.vpmb.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import fr.eseo.dis.android.vpmb.db.models.ProjectJury;

@Dao
public interface ProjectJuryDAO {

    @Insert
    void insert(ProjectJury projectJury);

    @Query("SELECT * FROM ProjectJury")
    List<ProjectJury> loadAll();

    @Query("SELECT * FROM ProjectJury WHERE pseudoJury= :idPseudoJury")
    List<ProjectJury> loadSubjectForAJury(long idPseudoJury);
}
