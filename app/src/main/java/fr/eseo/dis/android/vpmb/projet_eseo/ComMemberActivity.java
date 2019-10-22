package fr.eseo.dis.android.vpmb.projet_eseo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import fr.eseo.dis.android.vp.projet_eseo.R;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.login.LoginActivity;
import fr.eseo.dis.android.vpmb.projet_eseo.ui.main.SectionsPagerAdapterCom;

public class ComMemberActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_member);
        SectionsPagerAdapterCom sectionsPagerAdapterCom = new SectionsPagerAdapterCom(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapterCom);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        Button buttonLogout = (Button) findViewById(R.id.filterButtonMainWorkorderSelection);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComMemberActivity.this, MainActivity.class);
                LoginActivity.setUsername("");
                LoginActivity.setToken("");
                startActivity(intent);
            }
        });

        Button buttonCleanDatabase = (Button) findViewById(R.id.clean_database);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }


}