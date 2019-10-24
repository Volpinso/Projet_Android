package fr.eseo.dis.android.vpmb.projet_eseo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jsibbold.zoomage.ZoomageView;

import fr.eseo.dis.android.vpmb.projet_eseo.R;

public class PosterLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster_location);
        ZoomageView imageZoom = (ZoomageView)findViewById(R.id.myZoomageView);
    }
}
