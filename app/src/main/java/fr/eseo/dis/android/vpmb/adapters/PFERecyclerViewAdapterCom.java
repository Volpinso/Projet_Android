package fr.eseo.dis.android.vpmb.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.eseo.dis.android.vp.projet_eseo.R;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.main.PlaceholderFragmentComPfe;


public class PFERecyclerViewAdapterCom extends RecyclerView.Adapter<PFERecyclerViewAdapterCom.FilmographyRecyclerViewHolder> {


    private final PlaceholderFragmentComPfe placeholderFragmentComPfe;

    private List<Integer> filmInformation;



    public PFERecyclerViewAdapterCom(PlaceholderFragmentComPfe placeholderFragmentComPfe) {
        this.placeholderFragmentComPfe = placeholderFragmentComPfe;
        //TODO: The following lines will be repalaced
        filmInformation = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            filmInformation.add(i);
        }
        //TODO: End of the code to be replaced

    }

    @NonNull
    @Override
    public FilmographyRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View filmView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_filmography_item,parent,false);

        return new FilmographyRecyclerViewHolder(filmView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FilmographyRecyclerViewHolder holder, final int position) {


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(holder.filmSynopsis.getVisibility()==View.VISIBLE){

                    holder.filmSynopsis.setVisibility(View.GONE);
                    holder.filmSynopsisLabel.setVisibility(View.GONE);
                }
                else{

                    holder.filmSynopsis.setVisibility(View.VISIBLE);
                    holder.filmSynopsisLabel.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return filmInformation.size();
    }

    class FilmographyRecyclerViewHolder extends RecyclerView.ViewHolder {

        private final TextView filmTitre;
        private final TextView filmGenre;
        private final TextView filmAnnee;
        private final TextView filmSynopsis;
        private final TextView filmSynopsisLabel;

        public FilmographyRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            filmTitre = itemView.findViewById(R.id.filmography_titre);
            filmGenre = itemView.findViewById(R.id.filmography_genre);
            filmAnnee = itemView.findViewById(R.id.filmography_annee);
            filmSynopsis = itemView.findViewById(R.id.filmography_resume);
            filmSynopsisLabel = itemView.findViewById(R.id.filmography_synopsis);
        }
    }
}
