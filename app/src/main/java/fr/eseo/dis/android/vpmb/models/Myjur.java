package fr.eseo.dis.android.vpmb.models;

import java.util.Arrays;

public class Myjur {

    private String result;
    private String api;
    private Juries[] juries;
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

    public Juries[] getJuries() {
        return juries;
    }

    public void setJuries(Juries[] juries) {
        this.juries = juries;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Myjur{" +
                "result='" + result + '\'' +
                ", api='" + api + '\'' +
                ", juries=" + Arrays.toString(juries) +
                ", error='" + error + '\'' +
                '}';
    }
}
