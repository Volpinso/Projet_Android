package fr.eseo.dis.android.vpmb.projet_eseo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import fr.eseo.dis.android.vpmb.projet_eseo.R;

public class FullScreenPosterVisitor extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        final String posterBase64 = intent.getStringExtra("poster");
        final long idProject = intent.getLongExtra("idProject", 1000L);

        setContentView(R.layout.activity_full_screen_poster_visitor);
        setTitle(R.string.Poster);
        ImageView imageView = (ImageView) this.findViewById(R.id.full_screen);
        Button button = (Button) this.findViewById(R.id.grade_button);
        TextView text = (TextView) this.findViewById(R.id.text_no_preview);
        if(posterBase64 != null) {
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(convertB64toImage(posterBase64), 0,
                    convertB64toImage(posterBase64).length));
        }else{
            text.setVisibility(View.VISIBLE);
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( v.getContext(), GradeFormVisitorActivity.class);
                intent.putExtra("idProject", idProject);
                v.getContext().startActivity(intent);
            }
        });

    }

    public byte[] convertB64toImage(String base64){

        byte[] imageAsBytes = Base64.decode(base64.getBytes(), Base64.DEFAULT);

        return  imageAsBytes;

    }
}
