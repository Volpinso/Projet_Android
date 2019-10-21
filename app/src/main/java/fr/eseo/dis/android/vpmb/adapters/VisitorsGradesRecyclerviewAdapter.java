package fr.eseo.dis.android.vpmb.adapters;

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
import fr.eseo.dis.android.vpmb.db.models.Grade;
import fr.eseo.dis.android.vpmb.projet_eseo.VisitorsGradesActivity;

public class VisitorsGradesRecyclerviewAdapter extends RecyclerView.Adapter<VisitorsGradesRecyclerviewAdapter.VisitorsGradesRecyclerViewHolder> {

    VisitorsGradesActivity visitorsGradesActivity;
    private List<Integer> subjectInformation;
    private List<Integer> expandedPositions;
    private List<Grade> gradeList;

    public VisitorsGradesRecyclerviewAdapter(VisitorsGradesActivity visitorsGradesActivity){
        this.visitorsGradesActivity = visitorsGradesActivity;
        this.gradeList = AppDataBase.getINSTANCE(visitorsGradesActivity.getApplicationContext()).gradeDAO().loadSubjectGrade(visitorsGradesActivity.getProjectId());
        subjectInformation = new ArrayList<>();
        for(int i = 0; i < this.gradeList.size(); i++) {
            subjectInformation.add(i);
        }
        expandedPositions = new ArrayList<>();
    }

    @NonNull
    @Override
    public VisitorsGradesRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View gradesView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_all_grades_subject,parent,false);
        return new VisitorsGradesRecyclerViewHolder(gradesView);
    }

    @Override
    public void onBindViewHolder(@NonNull VisitorsGradesRecyclerViewHolder holder, int position) {


        holder.comment.setText(gradeList.get(position).getCommentaire());
        holder.grade.setText(String.valueOf(gradeList.get(position).getNote()));
    }

    public int getItemCount() {
        return subjectInformation.size();
    }

    public class VisitorsGradesRecyclerViewHolder extends RecyclerView.ViewHolder {

        private final TextView grade;
        private final TextView comment;


        public VisitorsGradesRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            grade = itemView.findViewById(R.id.grade);
            comment = itemView.findViewById(R.id.comment);


        }
    }
}
