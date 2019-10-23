package fr.eseo.dis.android.vpmb.adapters;

import android.content.Intent;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.android.vpmb.db.AppDataBase;
import fr.eseo.dis.android.vpmb.db.models.Project;
import fr.eseo.dis.android.vpmb.db.models.ProjectJury;
import fr.eseo.dis.android.vpmb.projet_eseo.VisitorListSubjectsActivity;
import fr.eseo.dis.android.vp.projet_eseo.R;
import fr.eseo.dis.android.vpmb.projet_eseo.FullScreenPosterVisitor;

public class VisitorSubjectRecyclerViewAdapter extends RecyclerView.Adapter<VisitorSubjectRecyclerViewAdapter.SubjectRecyclerViewHolder>{

    private final VisitorListSubjectsActivity subjectActivity;

    private List<Integer> subjectInformation;
    private List<Integer> expandedPositions;
    private static String thumbnail="";
    private List<ProjectJury> projectsJury;

    private List<Project> projectsToDisplay;

    //TODO: This field will be deleted
    private float radius;

    public VisitorSubjectRecyclerViewAdapter(VisitorListSubjectsActivity subjectActivity) {
        this.subjectActivity = subjectActivity;
        //TODO: The following lines will be repalaced
        projectsJury = new ArrayList<>();
        projectsJury = AppDataBase.getINSTANCE(subjectActivity.getApplicationContext()).projectJuryDAO().loadSubjectForAJury(VisitorListSubjectsActivity.getPseudoJuryVisitorId());

        projectsToDisplay = new ArrayList<>();
        subjectInformation = new ArrayList<>();

        for (int i=0; i<projectsJury.size(); i++){
            projectsToDisplay.add(AppDataBase.getINSTANCE(subjectActivity.getApplicationContext()).projectDAO().selectProject(projectsJury.get(i).getIdSubject()));
        }

        for(int i = 0; i < projectsToDisplay.size(); i++){
            subjectInformation.add(i);
        }
        expandedPositions = new ArrayList<>();
        //TODO: End of the code to be replaced
    }

    @NonNull
    @Override
    public SubjectRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View filmView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_all_pfe_visitor,parent,false);
        return new SubjectRecyclerViewHolder(filmView);

    }

    @Override
    public void onBindViewHolder(@NonNull final SubjectRecyclerViewHolder holder, final int position) {
        holder.titre.setText(projectsToDisplay.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( v.getContext(), FullScreenPosterVisitor.class);
                intent.putExtra("poster", projectsToDisplay.get(position).getPoster64());
                intent.putExtra("idProject", projectsToDisplay.get(position).getIdProject());
                System.out.println("IdPorject1 "+projectsToDisplay.get(position).getIdProject()+"");
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return subjectInformation.size();
    }

    class SubjectRecyclerViewHolder extends RecyclerView.ViewHolder {

        private final TextView titre;





        public SubjectRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            titre = itemView.findViewById(R.id.pfe_titre);


        }
    }

    public byte[] convertB64toImage(String base64){

        byte[] imageAsBytes = Base64.decode(base64.getBytes(), Base64.DEFAULT);

        return  imageAsBytes;

    }


}
