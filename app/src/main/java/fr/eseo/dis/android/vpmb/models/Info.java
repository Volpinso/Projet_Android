package fr.eseo.dis.android.vpmb.models;

import java.util.Arrays;

import fr.eseo.dis.android.vpmb.db.models.Project;

public class Info {

    private Members[] members;
    private Projects[] projects;

    public Members[] getMembers() {
        return members;
    }

    public void setMembers(Members[] members) {
        this.members = members;
    }

    public Projects[] getProjects() {
        return projects;
    }

    public void setProjects(Projects[] projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Info{" +
                "members=" + Arrays.toString(members) +
                ", projects=" + Arrays.toString(projects) +
                '}';
    }
}
