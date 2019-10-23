package fr.eseo.dis.android.vpmb.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.android.vp.projet_eseo.R;
import fr.eseo.dis.android.vpmb.models.Note;
import fr.eseo.dis.android.vpmb.projet_eseo.GradesDetailsActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.IndividualGradeActivity;

public class GradesDetailsJuryAdapter extends RecyclerView.Adapter<GradesDetailsJuryAdapter.GradeDetailsViewHolder> {


    private final GradesDetailsActivity gradesDetailsActivity;
    private List<Integer> subjectInformation;
    private List<Integer> expandedPositions;
    private List<Note> notesList;





    public GradesDetailsJuryAdapter(GradesDetailsActivity gradesDetailsActivity) {
        this.gradesDetailsActivity = gradesDetailsActivity;
        //TODO: The following lines will be repalaced
        this.notesList = GradeRecyclerViewJuryAdapter.getNotes();
        //TODO: The following lines will be repalaced
        subjectInformation = new ArrayList<>();
        for(int i = 0; i < this.notesList.size(); i++) {
            subjectInformation.add(i);
        }
        expandedPositions = new ArrayList<>();
        //TODO: End of the code to be replaced

    }


    @NonNull
    @Override
    public GradeDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View pfeView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_grades_details_item,parent,false);

        return new GradeDetailsViewHolder(pfeView);
    }

    @Override
    public void onBindViewHolder(@NonNull final GradesDetailsJuryAdapter.GradeDetailsViewHolder holder, final int position) {


        holder.studentName.setText(notesList.get(position).getForename() + " " + notesList.get(position).getSurname());
        if (notesList.get(position).getMyNote() != null ){
            holder.juryGrade.setText(holder.itemView.getContext().getString(R.string.MyGrade) + " " + notesList.get(position).getMyNote());
        }
        else {
            holder.juryGrade.setText(holder.itemView.getContext().getString(R.string.MyGrade)
                    + " " + holder.itemView.getContext().getString(R.string.NotGraded));
        }

        if (notesList.get(position).getAvgNote() != null ){
            holder.averageGrade.setText(holder.itemView.getContext().getString(R.string.AverageGrade) + " "
                    + notesList.get(position).getAvgNote());
        }
        else {
            holder.averageGrade.setText(holder.itemView.getContext().getString(R.string.AverageGrade) + " "
                    + holder.itemView.getContext().getString(R.string.NotGraded));
        }

        if(expandedPositions.contains(position)){
            holder.juryGrade.setVisibility(View.VISIBLE);
            holder.averageGrade.setVisibility(View.VISIBLE);
            holder.gradeButton.setVisibility(View.VISIBLE);
        }
        else{
            holder.juryGrade.setVisibility(View.GONE);
            holder.averageGrade.setVisibility(View.GONE);
            holder.gradeButton.setVisibility(View.GONE);
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(holder.juryGrade.getVisibility()==View.VISIBLE){
                    expandedPositions.remove(new Integer(position));
                    holder.juryGrade.setVisibility(View.GONE);
                    holder.averageGrade.setVisibility(View.GONE);
                    holder.gradeButton.setVisibility(View.GONE);
                }
                else{
                    expandedPositions.add(position);
                    holder.juryGrade.setVisibility(View.VISIBLE);
                    holder.averageGrade.setVisibility(View.VISIBLE);
                    holder.gradeButton.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });

        Button buttonAddGrade = (Button) holder.itemView.findViewById(R.id.button_add_grade);
        buttonAddGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), IndividualGradeActivity.class);
                intent.putExtra("studentName", notesList.get(position).getForename() + " " + notesList.get(position).getSurname());
                intent.putExtra("studentId", notesList.get(position).getUserId());
                if (notesList.get(position).getMyNote() != null ){
                    intent.putExtra("studentGrade", "" + notesList.get(position).getMyNote());
                }
                else {
                    intent.putExtra("studentGrade" ,holder.itemView.getContext().getString(R.string.NotGraded));
                }
                v.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return subjectInformation.size();
    }

    class GradeDetailsViewHolder extends RecyclerView.ViewHolder {

        private final TextView studentName;
        private final TextView juryGrade;
        private final TextView studentGrade;
        private final TextView averageGrade;
        private final Button gradeButton;



        public GradeDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.student_name);
            juryGrade = itemView.findViewById(R.id.jury_grade);
            studentGrade = itemView.findViewById(R.id.student_grade);
            averageGrade = itemView.findViewById(R.id.average_grade);
            gradeButton = itemView.findViewById(R.id.button_add_grade);

        }
    }
}
