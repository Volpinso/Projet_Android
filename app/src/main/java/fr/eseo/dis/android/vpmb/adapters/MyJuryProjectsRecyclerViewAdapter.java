package fr.eseo.dis.android.vpmb.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.android.vp.projet_eseo.R;
import fr.eseo.dis.android.vpmb.models.Projects;
import fr.eseo.dis.android.vpmb.models.RequestModel;
import fr.eseo.dis.android.vpmb.projet_eseo.AllPfeActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.MyJuryProjectActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.PfeDetailsActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.login.LoginActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.main.PlaceholderFragmentPfe;


public class MyJuryProjectsRecyclerViewAdapter extends RecyclerView.Adapter<MyJuryProjectsRecyclerViewAdapter.PFERecyclerViewHolder>{

    private final MyJuryProjectActivity myJuryProjectActivity;
    private List<Integer> subjectInformation;
    private List<Integer> expandedPositions;
    private List<Projects> projectList;
    private static String thumbnail;

    //TODO: This field will be deleted
    private float radius;

    public MyJuryProjectsRecyclerViewAdapter(MyJuryProjectActivity myJuryProjectActivity) {
        this.myJuryProjectActivity = myJuryProjectActivity;
        this.projectList = JuryRecyclerViewAdapter.getMyJuryProjectList();
        System.out.println(projectList.size());
        //TODO: The following lines will be repalaced
        subjectInformation = new ArrayList<>();
        for(int i = 0; i < this.projectList.size(); i++) {
            subjectInformation.add(i);
        }
        expandedPositions = new ArrayList<>();
        //TODO: End of the code to be replaced
    }

    @NonNull
    @Override
    public PFERecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View filmView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_jury_project_item,parent,false);
        return new PFERecyclerViewHolder(filmView);

    }

    @Override
    public void onBindViewHolder(@NonNull final PFERecyclerViewHolder holder, final int position) {
        holder.pfeTitre.setText(projectList.get(position).getTitle());
        if (projectList.get(position).getPoster() != null) {
            holder.pfeEmplacement.setText(holder.itemView.getContext().getResources().getString(R.string.emplacement) + " " + projectList.get(position).getPoster());
        } else {
            holder.pfeEmplacement.setText(holder.itemView.getContext().getResources().getString(R.string.emplacement) + " No place defined");
        }
        if (projectList.get(position).getConfid() == 0 ||
                createPseudo(projectList.get(position).getSupervisor().getSurname(), projectList.get(position).getSupervisor().getForename()) != LoginActivity.getUsername()) {
            holder.pfeDescriptionLabel.setText(projectList.get(position).getDescrip());
        }
        else {
            holder.pfeDescriptionLabel.setText(holder.itemView.getContext().getResources().getString(R.string.confidential));
        }

        holder.pfeSupervisor.setText("Supervisor : " + projectList.get(position).getSupervisor().getForename() + " " + projectList.get(position).getSupervisor().getSurname());

        String members = "Members : ";
        for(int i =0; i < projectList.get(position).getStudents().length - 1; i++){
            members = members +  projectList.get(position).getStudents()[i].getForename() + " " + projectList.get(position).getStudents()[i].getSurname() + ", ";
        }
        members = members + projectList.get(position).getStudents()[projectList.get(position).getStudents().length - 1].getForename()
                + " " + projectList.get(position).getStudents()[projectList.get(position).getStudents().length - 1].getSurname();
        holder.pfeMembers.setText(members);

        if(expandedPositions.contains(position)){
            holder.pfeDescriptionLabel.setVisibility(View.VISIBLE);
            holder.pfeDescription.setVisibility(View.VISIBLE);
            holder.pfeSupervisor.setVisibility(View.VISIBLE);
            holder.pfeMembers.setVisibility(View.VISIBLE);
        }
        else{
            holder.pfeDescription.setVisibility(View.GONE);
            holder.pfeDescriptionLabel.setVisibility(View.GONE);
            holder.pfeSupervisor.setVisibility(View.GONE);
            holder.pfeMembers.setVisibility(View.GONE);
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(holder.pfeDescription.getVisibility()==View.VISIBLE){
                    expandedPositions.remove(new Integer(position));
                    holder.pfeDescription.setVisibility(View.GONE);
                    holder.pfeDescriptionLabel.setVisibility(View.GONE);
                    holder.pfeSupervisor.setVisibility(View.GONE);
                    holder.pfeMembers.setVisibility(View.GONE);
                }
                else{
                    expandedPositions.add(position);
                    holder.pfeDescription.setVisibility(View.VISIBLE);
                    holder.pfeDescriptionLabel.setVisibility(View.VISIBLE);
                    holder.pfeSupervisor.setVisibility(View.VISIBLE);
                    holder.pfeMembers.setVisibility(View.VISIBLE);
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
        private final TextView pfeSupervisor;
        private final TextView pfeMembers;



        public PFERecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            pfeTitre = itemView.findViewById(R.id.pfe_titre);
            pfeEmplacement = itemView.findViewById(R.id.pfe_emplacement);
            pfeDescription = itemView.findViewById(R.id.pfe_description);
            pfeDescriptionLabel = itemView.findViewById(R.id.pfe_description_label);
            pfeSupervisor = itemView.findViewById(R.id.pfe_supervisor);
            pfeMembers = itemView.findViewById(R.id.pfe_members);

        }
    }

    public static String getThumbnail(){
        return thumbnail;
    }

    public static void setThumbnail(String thumbnail){
        MyJuryProjectsRecyclerViewAdapter.thumbnail = thumbnail;
    }

    public String createPseudo(String surname, String forename){
        String pseudo;
        if (surname.length() < 5){
            pseudo = surname + forename.substring(0, 2);
        }
        else{
            pseudo = surname.substring(0, 4) + forename.substring(0,2);
        }
        return pseudo;
    }
}
