package fr.eseo.dis.android.vpmb.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import fr.eseo.dis.android.vpmb.db.models.Project;

@Dao
public interface ProjectDAO {

    @Insert
    void insert(Project project);

    @Query("SELECT * FROM Subject")
    List<Project>  loadAll();

    @Query("SELECT * FROM Subject WHERE idProject= :idProjectSearched")
    Project  selectProject(long idProjectSearched);
}
