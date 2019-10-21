package fr.eseo.dis.android.vpmb.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import fr.eseo.dis.android.vpmb.db.models.StudentAnnotation;

@Dao
public interface StudentAnnotationDAO {

    @Insert
    long insert(StudentAnnotation studentAnnotation);

    @Query("SELECT * FROM StudentAnnotation")
    List<StudentAnnotation> loadAll();

    @Query("SELECT * FROM StudentAnnotation WHERE idProject= :idProjectStudent AND idStudent= :idStudentStudent AND username= :usernameStudent")
    List<StudentAnnotation> loadStudentAnnotatinExist(long idProjectStudent, long idStudentStudent, String usernameStudent);

    @Query("UPDATE StudentAnnotation SET comment = :newComment WHERE idProject= :idProjectStudent AND idStudent= :idStudentStudent AND username= :usernameStudent")
    int updateStudentAnnotationPoster(String newComment, long idProjectStudent, long idStudentStudent, String usernameStudent);

}
