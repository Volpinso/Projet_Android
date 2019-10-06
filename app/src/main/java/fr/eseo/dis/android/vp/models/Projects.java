package fr.eseo.dis.android.vp.models;

import java.util.Arrays;

public class Projects {

    private int projectId;
    private String title;
    private String descrip;
    private Supervisor supervisor;
    private String poster;
    private int confid;
    private Students[] students;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getConfid() {
        return confid;
    }

    public void setConfid(int confid) {
        this.confid = confid;
    }

    public Students[] getStudents() {
        return students;
    }

    public void setStudents(Students[] students) {
        this.students = students;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    @Override
    public String toString() {
        return "Projects{" +
                "projectId=" + projectId +
                ", title='" + title + '\'' +
                ", descrip='" + descrip + '\'' +
                ", supervisor=" + supervisor +
                ", poster='" + poster + '\'' +
                ", confid=" + confid +
                ", students=" + Arrays.toString(students) +
                '}';
    }
}
