package fr.eseo.dis.android.vpmb.models;

import java.util.Arrays;

public class Notes {

    private String result;
    private String api;
    private Note[] notes;
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

    public Note[] getNotes() {
        return notes;
    }

    public void setNotes(Note[] notes) {
        this.notes = notes;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "result='" + result + '\'' +
                ", api='" + api + '\'' +
                ", notes=" + Arrays.toString(notes) +
                ", error='" + error + '\'' +
                '}';
    }
}
