package fr.eseo.dis.android.vpmb.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import fr.eseo.dis.android.vpmb.db.dao.GradeDAO;
import fr.eseo.dis.android.vpmb.db.dao.ProjectDAO;
import fr.eseo.dis.android.vpmb.db.dao.PseudoJuryDAO;
import fr.eseo.dis.android.vpmb.db.dao.VisitorDAO;
import fr.eseo.dis.android.vpmb.db.models.Grade;
import fr.eseo.dis.android.vpmb.db.models.Project;
import fr.eseo.dis.android.vpmb.db.models.ProjectJury;
import fr.eseo.dis.android.vpmb.db.models.PseudoJury;
import fr.eseo.dis.android.vpmb.db.models.Visitor;

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
                    .addCallback(new AppDataBaseCallback())
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }




}
