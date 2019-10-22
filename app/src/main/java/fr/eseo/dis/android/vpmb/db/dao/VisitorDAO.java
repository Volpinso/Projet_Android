package fr.eseo.dis.android.vpmb.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import fr.eseo.dis.android.vpmb.db.models.Visitor;

@Dao
public interface VisitorDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public long insert(Visitor visitor);

    @Query("SELECT * FROM Visitor")
    List<Visitor> loadAll();

    @Query("DELETE FROM Visitor")
    void deleteAll();
}
