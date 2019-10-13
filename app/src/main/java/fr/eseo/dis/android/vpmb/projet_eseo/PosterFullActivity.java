package fr.eseo.dis.android.vpmb.projet_eseo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;

import fr.eseo.dis.android.vp.projet_eseo.R;
import fr.eseo.dis.android.vpmb.adapters.PFERecyclerViewAdapter;

public class PosterFullActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster_full);
        setTitle("Full Screen");
        ImageView imageView = (ImageView) this.findViewById(R.id.full_screen);
        if (PfeDetailsActivity.getFullB64() != null) {
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(convertB64toImage(PfeDetailsActivity.getFullB64()), 0,
                    convertB64toImage(PfeDetailsActivity.getFullB64()).length));
        }
        else {
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(convertB64toImage(MyPfeDetailsActivity.getFullB64()), 0,
                    convertB64toImage(MyPfeDetailsActivity.getFullB64()).length));
        }
    }

    public byte[] convertB64toImage(String base64){

        byte[] imageAsBytes = Base64.decode(base64.getBytes(), Base64.DEFAULT);

        return  imageAsBytes;

    }
}
