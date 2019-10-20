package fr.eseo.dis.android.vpmb.projet_eseo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;

import fr.eseo.dis.android.vp.projet_eseo.R;

public class FullScreenPosterVisitor extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String posterBase64 = intent.getStringExtra("poster");
        setContentView(R.layout.activity_poster_full);
        setTitle(R.string.FullScreen);
        ImageView imageView = (ImageView) this.findViewById(R.id.full_screen);

        if(posterBase64 != null) {
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(convertB64toImage(posterBase64), 0,
                    convertB64toImage(posterBase64).length));
        }else{
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(convertB64toImage("No poster"), 0,
                    convertB64toImage("No poster").length));
        }
    }

    public byte[] convertB64toImage(String base64){

        byte[] imageAsBytes = Base64.decode(base64.getBytes(), Base64.DEFAULT);

        return  imageAsBytes;

    }
}
