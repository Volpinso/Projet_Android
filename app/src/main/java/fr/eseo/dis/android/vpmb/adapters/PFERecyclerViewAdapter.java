package fr.eseo.dis.android.vpmb.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.android.vpmb.models.Projects;
import fr.eseo.dis.android.vpmb.projet_eseo.AllPfeActivity;
import fr.eseo.dis.android.vp.projet_eseo.R;

public class PFERecyclerViewAdapter extends RecyclerView.Adapter<PFERecyclerViewAdapter.PFERecyclerViewHolder>{

    private final AllPfeActivity allPfeActivity;
    private List<Integer> subjectInformation;
    private List<Integer> expandedPositions;
    private List<Projects> projectList;

    //TODO: This field will be deleted
    private float radius;

    public PFERecyclerViewAdapter(AllPfeActivity allPfeActivity) {
        this.allPfeActivity = allPfeActivity;
        this.projectList = AllPfeActivity.listRequest();
        System.out.println(this.projectList.size());
        //TODO: The following lines will be repalaced
        subjectInformation = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            subjectInformation.add(i);
        }
        expandedPositions = new ArrayList<>();
        //TODO: End of the code to be replaced
    }

    @NonNull
    @Override
    public PFERecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View filmView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_all_pfe_item,parent,false);
        return new PFERecyclerViewHolder(filmView);

    }

    @Override
    public void onBindViewHolder(@NonNull final PFERecyclerViewHolder holder, final int position) {

        if(expandedPositions.contains(position)){
            holder.pfeDescriptionLabel.setVisibility(View.VISIBLE);
            holder.pfeDescription.setVisibility(View.VISIBLE);
        }
        else{
            holder.pfeDescription.setVisibility(View.GONE);
            holder.pfeDescriptionLabel.setVisibility(View.GONE);
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(holder.pfeDescription.getVisibility()==View.VISIBLE){
                    expandedPositions.remove(new Integer(position));
                    holder.pfeDescription.setVisibility(View.GONE);
                    holder.pfeDescriptionLabel.setVisibility(View.GONE);
                }
                else{
                    expandedPositions.add(position);
                    holder.pfeDescription.setVisibility(View.VISIBLE);
                    holder.pfeDescriptionLabel.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return subjectInformation.size();
    }

    class PFERecyclerViewHolder extends RecyclerView.ViewHolder {

        private final TextView pfeTitre;
        private final TextView pfeEmplacement;
        private final TextView pfeDescription;
        private final TextView pfeDescriptionLabel;



        public PFERecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            pfeTitre = itemView.findViewById(R.id.pfe_titre);
            pfeEmplacement = itemView.findViewById(R.id.pfe_emplacement);
            pfeDescription = itemView.findViewById(R.id.pfe_description);
            pfeDescriptionLabel = itemView.findViewById(R.id.pfe_description_label);

        }
    }
}
