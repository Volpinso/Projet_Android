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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.android.vp.projet_eseo.R;
import fr.eseo.dis.android.vpmb.models.Juries;
import fr.eseo.dis.android.vpmb.models.Note;
import fr.eseo.dis.android.vpmb.models.Notes;
import fr.eseo.dis.android.vpmb.models.Projects;
import fr.eseo.dis.android.vpmb.models.RequestModel;
import fr.eseo.dis.android.vpmb.projet_eseo.GradesDetailsActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.login.LoginActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.main.PlaceholderFragmentGrade;



public class GradeRecyclerViewJuryAdapter extends RecyclerView.Adapter<GradeRecyclerViewJuryAdapter.PFERecyclerComViewHolder> {


    private final PlaceholderFragmentGrade placeholderFragmentGrades;

    private final List<Projects> projectsList;
    private final List<Juries> juryList;
    private List<Projects> myProjectsList = new ArrayList<>();

    private List<Integer> subjectInformation;
    private List<Integer> expandedPositions;
    private static List<Note> notesList = new ArrayList<>();

    private static int projectId;

    public static int getProjectId() {
        return projectId;
    }

    public static void setProjectId(int projectId) {
        GradeRecyclerViewJuryAdapter.projectId = projectId;
    }

    public GradeRecyclerViewJuryAdapter(PlaceholderFragmentGrade placeholderFragmentGrades) {
        this.placeholderFragmentGrades = placeholderFragmentGrades;
        //TODO: The following lines will be repalaced

        this.projectsList = LoginActivity.getProjectList();
        this.juryList = LoginActivity.getJuryList();

        for(int i =0; i < juryList.size(); i++){
            for(int j =0; j < juryList.get(i).getInfo().getProjects().length; j++){
                for (int k = 0; k < projectsList.size(); k++){
                    if(projectsList.get(k).getProjectId() == juryList.get(i).getInfo().getProjects()[j].getProjectId()){
                        myProjectsList.add(projectsList.get(k));
                    }
                }
            }
        }

        System.out.println(projectsList.toString());
        System.out.println(juryList.toString());
        System.out.println(myProjectsList.toString());
        //TODO: The following lines will be repalaced
        subjectInformation = new ArrayList<>();
        for(int i = 0; i < this.myProjectsList.size(); i++) {
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
    public void onBindViewHolder(@NonNull final GradeRecyclerViewJuryAdapter.PFERecyclerComViewHolder holder, final int position) {


        holder.pfeTitre.setText(myProjectsList.get(position).getTitle());
        if (myProjectsList.get(position).getPoster() != null) {
            holder.pfeEmplacement.setText(holder.itemView.getContext().getResources().getString(R.string.emplacement) + " " + myProjectsList.get(position).getPoster());
        } else {
            holder.pfeEmplacement.setText(holder.itemView.getContext().getResources().getString(R.string.emplacement) + " " +
                    holder.itemView.getContext().getResources().getString(R.string.NoPlaceDefined));
        }
        if (myProjectsList.get(position).getConfid() == 0 ||
                createPseudo(myProjectsList.get(position).getSupervisor().getSurname(), myProjectsList.get(position).getSupervisor().getForename()) != LoginActivity.getUsername()) {
            holder.pfeDescriptionLabel.setText(myProjectsList.get(position).getDescrip());
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
                    setNotes(new ArrayList<Note>());
                    RequestQueue queue = Volley.newRequestQueue(view.getContext());
                    String url = RequestModel.getProjectGrades(LoginActivity.getUsername(), myProjectsList.get(position).getProjectId(), LoginActivity.getToken());
                    System.out.println(url);
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    Gson gson = new Gson();
                                    Notes responseModel = gson.fromJson(String.valueOf(response),
                                            Notes.class);

                                    for(int i = 0 ; i < responseModel.getNotes().length; i++){
                                        notesList.add(responseModel.getNotes()[i]);
                                        System.out.println(notesList.toString());
                                    }
                                    setNotes(notesList);
                                    System.out.println(getNotes());

                                }
                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // TODO: Handle error
                                    notesList = null;
                                    System.out.println("error");

                                }
                            });
                    queue.add(jsonObjectRequest);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                try{
                    Thread.sleep(2000);
                }
                catch (Exception e){

                }
                Intent intent = new Intent( view.getContext(), GradesDetailsActivity.class);
                intent.putExtra("projectName", myProjectsList.get(position).getTitle());
                setProjectId(myProjectsList.get(position).getProjectId());
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

    public static List<Note> getNotes(){
        return notesList;
    }

    public static void setNotes(List<Note> notesList){
        GradeRecyclerViewJuryAdapter.notesList = notesList;
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
