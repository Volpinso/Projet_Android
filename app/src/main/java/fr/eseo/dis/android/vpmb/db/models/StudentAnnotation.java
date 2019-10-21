package fr.eseo.dis.android.vpmb.db.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "StudentAnnotation")
public class StudentAnnotation {

    @PrimaryKey
    long idStudentAnnotation;

    @ColumnInfo(name="idStudent")
    long idStudent;

    @ColumnInfo(name="username")
    String username;

    @ColumnInfo(name="idProject")
    long idProject;

    @ColumnInfo(name="comment")
    String comment;

    public StudentAnnotation(long idStudentAnnotation, long idStudent, String username, long idProject, String comment) {
        this.idStudentAnnotation = idStudentAnnotation;
        this.idStudent = idStudent;
        this.username = username;
        this.idProject = idProject;
        this.comment = comment;
    }

    public long getIdStudentAnnotation() {
        return idStudentAnnotation;
    }

    public void setIdStudentAnnotation(long idStudentAnnotation) {
        this.idStudentAnnotation = idStudentAnnotation;
    }

    public long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(long idStudent) {
        this.idStudent = idStudent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getIdProject() {
        return idProject;
    }

    public void setIdProject(long idProject) {
        this.idProject = idProject;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "StudentAnnotation{" +
                "idStudentAnnotation=" + idStudentAnnotation +
                ", idStudent=" + idStudent +
                ", username='" + username + '\'' +
                ", idProject=" + idProject +
                ", comment='" + comment + '\'' +
                '}';
    }
}
