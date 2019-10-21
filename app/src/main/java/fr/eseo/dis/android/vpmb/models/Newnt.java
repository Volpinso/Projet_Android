package fr.eseo.dis.android.vpmb.models;

public class Newnt {
    private String result;
    private String api;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Newnt{" +
                "result='" + result + '\'' +
                ", api='" + api + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
