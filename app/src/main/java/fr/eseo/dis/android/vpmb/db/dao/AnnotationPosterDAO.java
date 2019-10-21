package fr.eseo.dis.android.vpmb.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import fr.eseo.dis.android.vpmb.db.models.AnnotationPoster;

@Dao
public interface AnnotationPosterDAO {

    @Insert
    long insert(AnnotationPoster annotationPoster);

    @Query("SELECT * FROM AnnotationPoster")
    List<AnnotationPoster> loadAll();

    @Query("SELECT * FROM AnnotationPoster WHERE idProject= :idProjectGrade AND username= :usernameGrade")
    List<AnnotationPoster> loadAnnotationExist(long idProjectGrade, String usernameGrade);

    @Query("UPDATE AnnotationPoster SET comment = :newComment WHERE idProject= :idProjectGrade AND username= :usernameGrade")
    int updateAnnotationPoster(String newComment, long idProjectGrade, String usernameGrade);

    @Query("UPDATE AnnotationPoster SET grade = :newGrade WHERE idProject= :idProjectGrade AND username= :usernameGrade")
    int updateGradePoster(long newGrade, long idProjectGrade, String usernameGrade);

}
