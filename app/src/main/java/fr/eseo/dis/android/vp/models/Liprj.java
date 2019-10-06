package fr.eseo.dis.android.vp.models;

import java.util.Arrays;

public class Liprj {

    private String result;
    private String api;
    private Projects[] projects;
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

    public Projects[] getProjects() {
        return projects;
    }

    public void setProjects(Projects[] projects) {
        this.projects = projects;
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
                ", projects=" + Arrays.toString(projects) +
                ", error='" + error + '\'' +
                '}';
    }
}
