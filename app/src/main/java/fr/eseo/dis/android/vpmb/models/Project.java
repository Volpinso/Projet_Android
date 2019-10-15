package fr.eseo.dis.android.vpmb.models;

import java.util.Arrays;

public class Project {

    private int idProject;
    private String title;
    private String description;
    private String poster;

    public int getProjectId() {
        return idProject;
    }

    public void setProjectId(int projectId) {
        this.idProject = projectId;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrip() {
        return description;
    }

    public void setDescrip(String descrip) {
        this.description = descrip;
    }

    @Override
    public String toString() {
        return "Project{" +
                "idProject=" + idProject +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }
}
