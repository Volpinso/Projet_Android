package fr.eseo.dis.android.vpmb.db.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "AnnotationPoster", primaryKeys = {"idProject","username"})

public class AnnotationPoster {

    @ColumnInfo(name = "idProject")
    @NonNull
    long idProject;

    @ColumnInfo(name="username")
    @NonNull
    String username;

    @ColumnInfo(name = "comment")
    String comment;

    @ColumnInfo(name = "grade")
    long grade;

    public AnnotationPoster(long idProject, String username, String comment, long grade) {
        this.idProject = idProject;
        this.username = username;
        this.comment = comment;
        this.grade = grade;
    }

    public long getIdProject() {
        return idProject;
    }

    public void setIdProject(long idProject) {
        this.idProject = idProject;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getGrade() {
        return grade;
    }

    public void setGrade(long grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "AnnotationPoster{" +
                "idProject=" + idProject +
                ", username='" + username + '\'' +
                ", comment='" + comment + '\'' +
                ", grade=" + grade +
                '}';
    }
}
