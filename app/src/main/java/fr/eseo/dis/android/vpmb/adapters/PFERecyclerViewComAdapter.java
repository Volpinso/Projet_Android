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

import fr.eseo.dis.android.vpmb.projet_eseo.R;
import fr.eseo.dis.android.vpmb.models.Projects;
import fr.eseo.dis.android.vpmb.models.RequestModel;
import fr.eseo.dis.android.vpmb.projet_eseo.MyPFEDetailsComActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.login.LoginActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.main.PlaceholderFragmentComPfe;


public class PFERecyclerViewComAdapter extends RecyclerView.Adapter<PFERecyclerViewComAdapter.PFERecyclerComViewHolder> {


    private final PlaceholderFragmentComPfe placeholderFragmentComPfe;

    private List<Projects> projectsList;

    private List<Integer> subjectInformation;
    private List<Integer> expandedPositions;
    private static String thumbnail;





    public PFERecyclerViewComAdapter(PlaceholderFragmentComPfe placeholderFragmentComPfe) {
        this.placeholderFragmentComPfe = placeholderFragmentComPfe;
        //TODO: The following lines will be repalaced
        this.projectsList = LoginActivity.getProjectList();
        //TODO: The following lines will be repalaced
        subjectInformation = new ArrayList<>();
        for(int i = 0; i < this.projectsList.size(); i++) {
            subjectInformation.add(i);
        }
        expandedPositions = new ArrayList<>();
        //TODO: End of the code to be replaced

    }


    @NonNull
    @Override
    public PFERecyclerComViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View pfeView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_all_pfe_item,parent,false);

        return new PFERecyclerComViewHolder(pfeView);
    }

    @Override
    public void onBindViewHolder(@NonNull final PFERecyclerComViewHolder holder, final int position) {


        holder.pfeTitre.setText(projectsList.get(position).getTitle());
        if (projectsList.get(position).getPoster() != null) {
            holder.pfeEmplacement.setText(holder.itemView.getContext().getResources().getString(R.string.emplacement) + " " + projectsList.get(position).getPoster());
        } else {
            holder.pfeEmplacement.setText(holder.itemView.getContext().getResources().getString(R.string.emplacement) + " " +
                    holder.itemView.getContext().getResources().getString(R.string.NoPlaceDefined));
        }
        if (projectsList.get(position).getConfid() == 0 ||
                createPseudo(projectsList.get(position).getSupervisor().getSurname(), projectsList.get(position).getSupervisor().getForename()) != LoginActivity.getUsername()) {
            holder.pfeDescriptionLabel.setText(projectsList.get(position).getDescrip());
        }
        else {
            holder.pfeDescriptionLabel.setText(holder.itemView.getContext().getResources().getString(R.string.confidential));
        }

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    // Instantiate the RequestQueue.
                    setThumbnail("");
                    RequestQueue queue = Volley.newRequestQueue(view.getContext());
                    String url = RequestModel.getPoster(LoginActivity.getUsername(), projectsList.get(position).getProjectId(), "THB64", LoginActivity.getToken());
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if(response.contains("Invalid Credentials") || response.contains("No Poster")){
                                        setThumbnail("No Poster");
                                    }
                                    else{
                                        setThumbnail(response);
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // Handle error
                                }
                            });

                    // Add the request to the RequestQueue.
                    queue.add(stringRequest);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(10000);
                }
                catch (Exception e){

                }
                Intent intent = new Intent( view.getContext(), MyPFEDetailsComActivity.class);
                intent.putExtra("projectId", projectsList.get(position).getProjectId());
                view.getContext().startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return subjectInformation.size();
    }

    class PFERecyclerComViewHolder extends RecyclerView.ViewHolder {

        private final TextView pfeTitre;
        private final TextView pfeEmplacement;
        private final TextView pfeDescription;
        private final TextView pfeDescriptionLabel;



        public PFERecyclerComViewHolder(@NonNull View itemView) {
            super(itemView);
            pfeTitre = itemView.findViewById(R.id.pfe_titre);
            pfeEmplacement = itemView.findViewById(R.id.pfe_emplacement);
            pfeDescription = itemView.findViewById(R.id.pfe_description);
            pfeDescriptionLabel = itemView.findViewById(R.id.pfe_description_label);

        }
    }

    public static String getThumbnail(){
        return thumbnail;
    }

    public static void setThumbnail(String thumbnail){
        PFERecyclerViewComAdapter.thumbnail = thumbnail;
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
