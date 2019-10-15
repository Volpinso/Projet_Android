package fr.eseo.dis.android.vpmb.db;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.android.vpmb.db.models.Project;
import fr.eseo.dis.android.vpmb.models.Projects;

public class DataConverter {

    public static List<Project> convertProjectsToProject(List<fr.eseo.dis.android.vpmb.models.Project> projects){
        List<Project> projectForDb = new ArrayList<>();
        if(!projects.isEmpty()){
            for(int i=0; i<projects.size(); i++){
                Project project = new Project(projects.get(i).getProjectId(), projects.get(i).getTitle(), projects.get(i).getDescrip(), projects.get(i).getPoster());
                projectForDb.add(project);
            }
        }
        return projectForDb;
    }
}
