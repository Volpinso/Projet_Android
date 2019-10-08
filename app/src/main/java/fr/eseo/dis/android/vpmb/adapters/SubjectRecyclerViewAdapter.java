package fr.eseo.dis.android.vpmb.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.android.vpmb.projet_eseo.ActivityVisitorListSubjects;
import fr.eseo.dis.android.vp.projet_eseo.R;

public class SubjectRecyclerViewAdapter extends RecyclerView.Adapter<SubjectRecyclerViewAdapter.SubjectRecyclerViewHolder>{

    private final ActivityVisitorListSubjects subjectActivity;

    private List<Integer> subjectInformation;
    private List<Integer> expandedPositions;

    //TODO: This field will be deleted
    private float radius;

    public SubjectRecyclerViewAdapter(ActivityVisitorListSubjects subjectActivity) {
        this.subjectActivity = subjectActivity;
        //TODO: The following lines will be repalaced
        subjectInformation = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            subjectInformation.add(i);
        }
        expandedPositions = new ArrayList<>();
        //TODO: End of the code to be replaced
    }

    @NonNull
    @Override
    public SubjectRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View filmView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_filmography_item,parent,false);
        return new SubjectRecyclerViewHolder(filmView);

    }

    @Override
    public void onBindViewHolder(@NonNull final SubjectRecyclerViewHolder holder, final int position) {

        if(expandedPositions.contains(position)){
            holder.filmSynopsis.setVisibility(View.VISIBLE);
            holder.filmSynopsisLabel.setVisibility(View.VISIBLE);
        }
        else{
            holder.filmSynopsis.setVisibility(View.GONE);
            holder.filmSynopsisLabel.setVisibility(View.GONE);
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(holder.filmSynopsis.getVisibility()==View.VISIBLE){
                    expandedPositions.remove(new Integer(position));
                    holder.filmSynopsis.setVisibility(View.GONE);
                    holder.filmSynopsisLabel.setVisibility(View.GONE);
                }
                else{
                    expandedPositions.add(position);
                    holder.filmSynopsis.setVisibility(View.VISIBLE);
                    holder.filmSynopsisLabel.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return subjectInformation.size();
    }

    class SubjectRecyclerViewHolder extends RecyclerView.ViewHolder {

        private final TextView filmTitre;
        private final TextView filmGenre;
        private final TextView filmAnnee;
        private final TextView filmSynopsis;
        private final TextView filmSynopsisLabel;



        public SubjectRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            filmTitre = itemView.findViewById(R.id.filmography_titre);
            filmGenre = itemView.findViewById(R.id.filmography_genre);
            filmAnnee = itemView.findViewById(R.id.filmography_annee);
            filmSynopsis = itemView.findViewById(R.id.filmography_resume);
            filmSynopsisLabel = itemView.findViewById(R.id.filmography_synopsis);

        }
    }

}
