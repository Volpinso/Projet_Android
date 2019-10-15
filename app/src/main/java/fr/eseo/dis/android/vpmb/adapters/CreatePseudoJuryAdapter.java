package fr.eseo.dis.android.vpmb.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.android.vp.projet_eseo.R;
import fr.eseo.dis.android.vpmb.db.AppDataBase;
import fr.eseo.dis.android.vpmb.db.models.Project;
import fr.eseo.dis.android.vpmb.projet_eseo.ActivityVisitorListSubjects;
import fr.eseo.dis.android.vpmb.projet_eseo.CreatePseudoJuryManual;

public class CreatePseudoJuryAdapter extends RecyclerView.Adapter<CreatePseudoJuryAdapter.PseudoJuryRecyclerViewHolder> {

    private final CreatePseudoJuryManual pseudoJuryManual;

    private List<Integer> subjectInformation;
    private List<Integer> expandedPositions;
    private  List<Project> dbProjects;


    //TODO: This field will be deleted
    private float radius;

    public CreatePseudoJuryAdapter(CreatePseudoJuryManual pseudoJuryManual) {
        this.pseudoJuryManual = pseudoJuryManual;
        //TODO: The following lines will be repalaced
        subjectInformation = new ArrayList<>();
        this.dbProjects = AppDataBase.getINSTANCE(pseudoJuryManual.getApplicationContext()).projectDAO().loadAll();
        System.out.println(dbProjects.size());
        for(int i = 0; i < dbProjects.size() ; i++){
            subjectInformation.add(i);
        }
        expandedPositions = new ArrayList<>();
        //TODO: End of the code to be replaced
    }

    @NonNull
    @Override
    public CreatePseudoJuryAdapter.PseudoJuryRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View pseudoJury = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_all_subjects_pj,parent,false);
        return new CreatePseudoJuryAdapter.PseudoJuryRecyclerViewHolder(pseudoJury);

    }

    @Override
    public void onBindViewHolder(@NonNull final CreatePseudoJuryAdapter.PseudoJuryRecyclerViewHolder holder, final int position) {
        holder.checkbox.setText(dbProjects.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return subjectInformation.size();
    }

    class PseudoJuryRecyclerViewHolder extends RecyclerView.ViewHolder {

        private final TextView checkbox;



        public PseudoJuryRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            checkbox = itemView.findViewById(R.id.checkBox);

        }
    }

}
