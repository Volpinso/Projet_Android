package fr.eseo.dis.android.vpmb.adapters;

import android.content.Intent;
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
import fr.eseo.dis.android.vpmb.projet_eseo.VisitorsGradesActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.main.PlaceholderFragmentComGrade;

public class GradeRecyclerViewFragmentComAdapter extends RecyclerView.Adapter<GradeRecyclerViewFragmentComAdapter.PFERecyclerGradeComViewHolder> {

    private final PlaceholderFragmentComGrade placeholderFragmentComGrade;

    private final List<Project> projectsList;

    private List<Integer> subjectInformation;
    private List<Integer> expandedPositions;

    public GradeRecyclerViewFragmentComAdapter(PlaceholderFragmentComGrade placeholderFragmentComGrade) {
        this.placeholderFragmentComGrade = placeholderFragmentComGrade;
        this.projectsList = AppDataBase.getINSTANCE(placeholderFragmentComGrade.getContext()).projectDAO().loadAll();
        subjectInformation = new ArrayList<>();
        for (int i = 0; i < this.projectsList.size(); i++) {
            subjectInformation.add(i);
        }
        expandedPositions = new ArrayList<>();
    }

    @NonNull
    @Override
    public PFERecyclerGradeComViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View pfeView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_all_pfe_visitor, parent, false);

        return new PFERecyclerGradeComViewHolder(pfeView);
    }

    @Override
    public void onBindViewHolder(@NonNull final GradeRecyclerViewFragmentComAdapter.PFERecyclerGradeComViewHolder holder, final int position) {

        holder.pfeTitre.setText(projectsList.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), VisitorsGradesActivity.class);
                intent.putExtra("projectID", projectsList.get(position).getIdProject());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjectInformation.size();
    }

    class PFERecyclerGradeComViewHolder extends RecyclerView.ViewHolder {

        private final TextView pfeTitre;


        public  PFERecyclerGradeComViewHolder(@NonNull View itemView){
            super(itemView);
            pfeTitre = itemView.findViewById(R.id.pfe_titre);

        }
    }

}