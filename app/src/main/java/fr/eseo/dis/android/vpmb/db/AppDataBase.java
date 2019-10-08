package fr.eseo.dis.android.vpmb.db;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

import fr.eseo.dis.android.vpmb.db.dao.GradeDAO;
import fr.eseo.dis.android.vpmb.db.dao.ProjectDAO;
import fr.eseo.dis.android.vpmb.db.dao.PseudoJuryDAO;
import fr.eseo.dis.android.vpmb.db.dao.VisitorDAO;
import fr.eseo.dis.android.vpmb.db.models.Grade;
import fr.eseo.dis.android.vpmb.db.models.Project;
import fr.eseo.dis.android.vpmb.db.models.ProjectJury;
import fr.eseo.dis.android.vpmb.db.models.PseudoJury;
import fr.eseo.dis.android.vpmb.db.models.Visitor;
import fr.eseo.dis.android.vpmb.models.Projects;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.login.LoginActivity;

@Database(entities = {Project.class, Visitor.class, ProjectJury.class, Grade.class, PseudoJury.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase INSTANCE;


    public abstract ProjectDAO projectDAO();

    public abstract VisitorDAO visitorDAO();

    public abstract PseudoJuryDAO pseudoJuryDAO();

    public abstract GradeDAO gradeDAO();



    public static AppDataBase getAppDatabase(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context, AppDataBase.class, "jpoManager.db")
                    .fallbackToDestructiveMigration()
                    .build();
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

   /* private void setupFilms(){
        AppListTask flt = new AppListTask();
        flt.execute();
    }

    private class AppListTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            Log.d("DB","doInBackground started");
            AppDataBase.getDatabase(LoginActivity).moviesDao().findAllMoviesWithTheirMainGenre();
            return null;
        }

        @Override
        protected void onPostExecute(Void resultCode){
            Log.d("DB","onPostExecute started");
            super.onPostExecute(resultCode);
        }
    }*/




}
