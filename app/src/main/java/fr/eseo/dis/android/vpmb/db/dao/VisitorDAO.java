package fr.eseo.dis.android.vpmb.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import fr.eseo.dis.android.vpmb.db.models.Visitor;

@Dao
public interface VisitorDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public long insert(Visitor visitor);
}