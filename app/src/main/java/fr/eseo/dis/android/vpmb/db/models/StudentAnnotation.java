package fr.eseo.dis.android.vpmb.db.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "StudentAnnotation", primaryKeys = {"idStudent","username", "idProject"})
public class StudentAnnotation {

    @ColumnInfo(name="idStudent")
    @NonNull
    long idStudent;

    @ColumnInfo(name="username")
    @NonNull
    String username;

    @ColumnInfo(name="idProject")
    @NonNull
    long idProject;

    @ColumnInfo(name="comment")
    String comment;

    public StudentAnnotation(long idStudent, String username, long idProject, String comment) {
        this.idStudent = idStudent;
        this.username = username;
        this.idProject = idProject;
        this.comment = comment;
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
                "idStudent=" + idStudent +
                ", username='" + username + '\'' +
                ", idProject=" + idProject +
                ", comment='" + comment + '\'' +
                '}';
    }
}
