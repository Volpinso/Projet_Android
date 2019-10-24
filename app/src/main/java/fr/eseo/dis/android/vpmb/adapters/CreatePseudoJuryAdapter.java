package fr.eseo.dis.android.vpmb.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.android.vpmb.projet_eseo.R;
import fr.eseo.dis.android.vpmb.db.AppDataBase;
import fr.eseo.dis.android.vpmb.db.models.Project;
import fr.eseo.dis.android.vpmb.projet_eseo.CreatePseudoJuryManualActivity;

public class CreatePseudoJuryAdapter extends RecyclerView.Adapter<CreatePseudoJuryAdapter.PseudoJuryRecyclerViewHolder> {

    private final CreatePseudoJuryManualActivity pseudoJuryManual;

    private List<Integer> subjectInformation;
    private List<Integer> expandedPositions;
    private  List<Project> dbProjects;

    public List<Project> getProjectSelected() {
        return projectSelected;
    }

    public void setProjectSelected(List<Project> projectSelected) {
        this.projectSelected = projectSelected;
    }

    List<Project> projectSelected = new ArrayList<>();;



    public CreatePseudoJuryAdapter(CreatePseudoJuryManualActivity pseudoJuryManual) {
        this.pseudoJuryManual = pseudoJuryManual;
        //TODO: The following lines will be repalaced
        subjectInformation = new ArrayList<>();
        this.dbProjects = AppDataBase.getINSTANCE(pseudoJuryManual.getApplicationContext()).projectDAO().loadAll();

        for(int i = 0; i < dbProjects.size() ; i++){
            subjectInformation.add(i);
        }
        expandedPositions = new ArrayList<>();
 }

    @NonNull
    @Override
    public CreatePseudoJuryAdapter.PseudoJuryRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View pseudoJury = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_all_subjects_pj,parent,false);

        return new CreatePseudoJuryAdapter.PseudoJuryRecyclerViewHolder(pseudoJury);

    }

    @Override
    public void onBindViewHolder(@NonNull final CreatePseudoJuryAdapter.PseudoJuryRecyclerViewHolder holder, final int position) {
            holder.checkbox.setTag(dbProjects.get(position));
            holder.checkbox.setText(dbProjects.get(position).getTitle());
            holder.checkbox.setChecked(false);

            holder.checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Project projet = (Project) holder.checkbox.getTag();

                    if(holder.checkbox.isChecked()) {
                        projectSelected.add(projet);
                    }else{
                        projectSelected.remove(projet);

                    }

                }
            });



    }


    @Override
    public int getItemCount() {
        return subjectInformation.size();
    }

    class PseudoJuryRecyclerViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox checkbox;


        public PseudoJuryRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            checkbox = itemView.findViewById(R.id.checkBox);
            checkbox.setClickable(true);

        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            itemView.setOnClickListener(onClickListener);
        }
    }


}
