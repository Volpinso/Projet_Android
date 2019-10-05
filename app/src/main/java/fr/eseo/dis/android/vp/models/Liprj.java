package fr.eseo.dis.android.vp.models;

public class Liprj {

    private String result;
    private String api;
    private String projects;
    private String projectId;
    private String title;
    private String descrip;
    private String poster;
    private String supervisor;
    private String forenameSupervisor;
    private String surnameSupervisor;
    private String confid;
    private String students;
    private String userId;
    private String forenameStudent;
    private String surnameStudent;
    private String error;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getForenameSupervisor() {
        return forenameSupervisor;
    }

    public void setForenameSupervisor(String forenameSupervisor) {
        this.forenameSupervisor = forenameSupervisor;
    }

    public String getSurnameSupervisor() {
        return surnameSupervisor;
    }

    public void setSurnameSupervisor(String surnameSupervisor) {
        this.surnameSupervisor = surnameSupervisor;
    }

    public String getConfid() {
        return confid;
    }

    public void setConfid(String confid) {
        this.confid = confid;
    }

    public String getStudents() {
        return students;
    }

    public void setStudents(String students) {
        this.students = students;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getForenameStudent() {
        return forenameStudent;
    }

    public void setForenameStudent(String forenameStudent) {
        this.forenameStudent = forenameStudent;
    }

    public String getSurnameStudent() {
        return surnameStudent;
    }

    public void setSurnameStudent(String surnameStudent) {
        this.surnameStudent = surnameStudent;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Liprj{" +
                "result='" + result + '\'' +
                ", api='" + api + '\'' +
                ", projects='" + projects + '\'' +
                ", projectId='" + projectId + '\'' +
                ", title='" + title + '\'' +
                ", descrip='" + descrip + '\'' +
                ", poster='" + poster + '\'' +
                ", supervisor='" + supervisor + '\'' +
                ", forenameSupervisor='" + forenameSupervisor + '\'' +
                ", surnameSupervisor='" + surnameSupervisor + '\'' +
                ", confid='" + confid + '\'' +
                ", students='" + students + '\'' +
                ", userId='" + userId + '\'' +
                ", forenameStudent='" + forenameStudent + '\'' +
                ", surnameStudent='" + surnameStudent + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
