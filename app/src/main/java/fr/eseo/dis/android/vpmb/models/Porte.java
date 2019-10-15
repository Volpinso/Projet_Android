package fr.eseo.dis.android.vpmb.models;

import java.util.Arrays;

public class Porte {

    private String result;
    private String api;
    private String seed;
    private Project[] projects;
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

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public Project[] getProjects() {
        return projects;
    }

    public void setProjects(Project[] projects) {
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
        return "Porte{" +
                "result='" + result + '\'' +
                ", api='" + api + '\'' +
                ", seed='" + seed + '\'' +
                ", projects=" + Arrays.toString(projects) +
                ", error='" + error + '\'' +
                '}';
    }
}
