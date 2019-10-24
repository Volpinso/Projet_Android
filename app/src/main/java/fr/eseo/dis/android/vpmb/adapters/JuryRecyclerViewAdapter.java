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

import fr.eseo.dis.android.vpmb.projet_eseo.R;
import fr.eseo.dis.android.vpmb.models.Juries;
import fr.eseo.dis.android.vpmb.models.Jyinf;
import fr.eseo.dis.android.vpmb.models.Projects;
import fr.eseo.dis.android.vpmb.models.RequestModel;
import fr.eseo.dis.android.vpmb.projet_eseo.MyJuryProjectActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.login.LoginActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.main.PlaceholderFragmentJury;

public class JuryRecyclerViewAdapter extends RecyclerView.Adapter<fr.eseo.dis.android.vpmb.adapters.JuryRecyclerViewAdapter.JuryRecyclerViewHolder> {


    private final PlaceholderFragmentJury placeholderFragmentJury;
    private final List<Juries> juriesList;

    private List<Integer> subjectInformation;
    private List<Integer> expandedPositions;

    private static List<Projects> myJuryProjectList = new ArrayList<>();

    public static List<Projects> getMyJuryProjectList() {
        return myJuryProjectList;
    }

    public static void setMyJuryProjectList(List<Projects> myJuryProjectList){
        JuryRecyclerViewAdapter.myJuryProjectList = myJuryProjectList;
    }


    public JuryRecyclerViewAdapter(PlaceholderFragmentJury placeholderFragmentJury) {
        this.placeholderFragmentJury = placeholderFragmentJury;
        this.juriesList = LoginActivity.getJuryList();
        //TODO: The following lines will be repalaced
        subjectInformation = new ArrayList<>();
        for(int i = 0; i < this.juriesList.size(); i++) {
            subjectInformation.add(i);
        }
        expandedPositions = new ArrayList<>();
        //TODO: End of the code to be replaced

    }

    @NonNull
    @Override
    public JuryRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View filmView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_jury_item,parent,false);

        return new JuryRecyclerViewHolder(filmView);
    }

    @Override
    public void onBindViewHolder(@NonNull final JuryRecyclerViewAdapter.JuryRecyclerViewHolder holder, final int position) {
        holder.juryNumber.setText("Jury " + juriesList.get(position).getIdJury());
        holder.juryDate.setText("Date : " + juriesList.get(position).getDate());

        String members = "";
        for(int i = 0; i < juriesList.get(position).getInfo().getMembers().length -1; i++){
            members = members + juriesList.get(position).getInfo().getMembers()[i].getForename() + " " + juriesList.get(position).getInfo().getMembers()[i].getSurname()
                    + ", ";
        }
        members = members + juriesList.get(position).getInfo().getMembers()[juriesList.get(position).getInfo().getMembers().length -1].getForename()
                + " " + juriesList.get(position).getInfo().getMembers()[juriesList.get(position).getInfo().getMembers().length -1].getSurname();

        holder.juryMembersName.setText(members);

        if (expandedPositions.contains(position)) {
            holder.juryMembers.setVisibility(View.VISIBLE);
            holder.juryMembersName.setVisibility(View.VISIBLE);
        } else {
            holder.juryMembers.setVisibility(View.GONE);
            holder.juryMembersName.setVisibility(View.GONE);
        }
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (holder.juryMembers.getVisibility() == View.VISIBLE) {
                    expandedPositions.remove(new Integer(position));
                    holder.juryMembers.setVisibility(View.GONE);
                    holder.juryMembersName.setVisibility(View.GONE);
                } else {
                    expandedPositions.add(position);
                    holder.juryMembers.setVisibility(View.VISIBLE);
                    holder.juryMembersName.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    // Instantiate the RequestQueue.
                    setMyJuryProjectList(new ArrayList<Projects>());
                    RequestQueue queue = Volley.newRequestQueue(view.getContext());
                    String url = RequestModel.getMyJuriesProjetcsRequest(LoginActivity.getUsername(), juriesList.get(position).getIdJury() ,LoginActivity.getToken());
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Gson gson = new Gson();
                                    Jyinf responseModel = gson.fromJson(String.valueOf(response),
                                            Jyinf.class);
                                    for(int i = 0 ; i < responseModel.getProjects().length; i++){
                                        myJuryProjectList.add(responseModel.getProjects()[i]);
                                    }
                                    setMyJuryProjectList(myJuryProjectList);

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // Handle error
                                }
                            });

                    // Add the request to the RequestQueue.
                    queue.add(jsonObjectRequest);
                    try{
                        Thread.sleep(3000);
                    }
                    catch(Exception e){

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent( view.getContext(), MyJuryProjectActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjectInformation.size();
    }

    class JuryRecyclerViewHolder extends RecyclerView.ViewHolder {

        private final TextView juryNumber;
        private final TextView juryDate;
        private final TextView juryMembers;
        private final TextView juryMembersName;

        public JuryRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            juryNumber = itemView.findViewById(R.id.jury_number);
            juryDate = itemView.findViewById(R.id.jury_date);
            juryMembers = itemView.findViewById(R.id.jury_membres);
            juryMembersName = itemView.findViewById(R.id.jury_membres_name);
        }
    }
}
