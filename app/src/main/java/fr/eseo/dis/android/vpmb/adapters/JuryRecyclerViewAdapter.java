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
import fr.eseo.dis.android.vpmb.models.Juries;
import fr.eseo.dis.android.vpmb.projet_eseo.JuryMemberActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.login.LoginActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.main.PlaceholderFragmentJury;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.main.PlaceholderFragmentPfe;

public class JuryRecyclerViewAdapter extends RecyclerView.Adapter<fr.eseo.dis.android.vpmb.adapters.JuryRecyclerViewAdapter.JuryRecyclerViewHolder> {


    private final PlaceholderFragmentJury placeholderFragmentJury;
    private final List<Juries> juriesList;

    private List<Integer> subjectInformation;
    private List<Integer> expandedPositions;



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
