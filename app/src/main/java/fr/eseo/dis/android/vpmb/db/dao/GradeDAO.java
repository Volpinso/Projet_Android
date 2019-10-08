package fr.eseo.dis.android.vpmb.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import fr.eseo.dis.android.vpmb.db.models.Grade;

@Dao
public interface GradeDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public long insert(Grade grade);

}
