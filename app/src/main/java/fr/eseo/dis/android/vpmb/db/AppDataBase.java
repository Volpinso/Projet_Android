package fr.eseo.dis.android.vpmb.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

import fr.eseo.dis.android.vpmb.db.dao.AnnotationPosterDAO;
import fr.eseo.dis.android.vpmb.db.dao.GradeDAO;
import fr.eseo.dis.android.vpmb.db.dao.ProjectDAO;
import fr.eseo.dis.android.vpmb.db.dao.ProjectJuryDAO;
import fr.eseo.dis.android.vpmb.db.dao.PseudoJuryDAO;
import fr.eseo.dis.android.vpmb.db.dao.StudentAnnotationDAO;
import fr.eseo.dis.android.vpmb.db.dao.VisitorDAO;
import fr.eseo.dis.android.vpmb.db.models.AnnotationPoster;
import fr.eseo.dis.android.vpmb.db.models.Grade;
import fr.eseo.dis.android.vpmb.db.models.Project;
import fr.eseo.dis.android.vpmb.db.models.ProjectJury;
import fr.eseo.dis.android.vpmb.db.models.PseudoJury;
import fr.eseo.dis.android.vpmb.db.models.StudentAnnotation;
import fr.eseo.dis.android.vpmb.db.models.Visitor;
import fr.eseo.dis.android.vpmb.models.Projects;

@Database(entities = {Project.class, Visitor.class, ProjectJury.class, Grade.class, PseudoJury.class, AnnotationPoster.class, StudentAnnotation.class},
        version = 5)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase INSTANCE;


    public abstract ProjectDAO projectDAO();

    public abstract VisitorDAO visitorDAO();

    public abstract PseudoJuryDAO pseudoJuryDAO();

    public abstract GradeDAO gradeDAO();

    public abstract ProjectJuryDAO projectJuryDAO();

    public abstract AnnotationPosterDAO annotationPosterDAO();

    public abstract StudentAnnotationDAO studentAnnotationDAO();



    public static AppDataBase getINSTANCE(Context context){
        if(INSTANCE==null){
            synchronized (AppDataBase.class){
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "jpoManager.db")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }

       }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }


    public void insertData(List<Projects> listProjects){

        Project project;

        for(int i=0; i<listProjects.size(); i++){

            Projects subject = listProjects.get(i);
            project = new Project(subject.getProjectId(), subject.getTitle(), subject.getDescrip(), subject.getPoster());
            projectDAO().insert(project);
        }

    }


    public static void insertProjectJury(List<Project> projectsJury, Context context){
        //Find last jury id
        List<PseudoJury> pseudoJuries = AppDataBase.getINSTANCE(context).pseudoJuryDAO().loadAll();
        long idLastPseudoJury = -1;
        long idPseudoJury;

        if (!pseudoJuries.isEmpty()) {
            idPseudoJury=pseudoJuries.size();
        }else {
            idPseudoJury = 0;
        }

        AppDataBase.getINSTANCE(context).pseudoJuryDAO().insert(new PseudoJury(idPseudoJury));


        for (int i = 0; i < projectsJury.size(); i++) {
            //Insert new jury and juryProject
            AppDataBase.getINSTANCE(context).projectJuryDAO().insert(new ProjectJury(projectsJury.get(i).getIdProject(), idPseudoJury));
        }



    }



}
