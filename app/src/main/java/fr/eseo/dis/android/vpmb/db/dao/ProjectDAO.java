package fr.eseo.dis.android.vpmb.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import fr.eseo.dis.android.vpmb.db.models.Project;

@Dao
public interface ProjectDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public long insert(Project project);
}
