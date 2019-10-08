package fr.eseo.dis.android.vpmb.db.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="Subject")
public class Project {

    @PrimaryKey
    long idProject;

    @ColumnInfo(name = "title")
    @NonNull
    String title;

    @ColumnInfo(name = "description")
    @NonNull
    String description;

    @ColumnInfo(name = "poster")
    String poster64;


    public Project(long idProject, String title, String description, String poster64) {
        this.idProject = idProject;
        this.title = title;
        this.description = description;
        this.poster64 = poster64;
    }

    public long getIdProject() {
        return idProject;
    }

    public void setIdProject(long idSujet) {
        this.idProject = idSujet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster64() {
        return poster64;
    }

    public void setPoster64(String poster64) {
        this.poster64 = poster64;
    }

    @Override
    public String toString() {
        return "Project{" +
                "idSujet=" + idProject +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", poster64='" + poster64 + '\'' +
                '}';
    }
}
