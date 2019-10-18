package com.example.api1;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesActivity extends AppCompatActivity {
    private String[] types = new String[]{"Action", "Comedy ", "Thriller", "Horror", "Christian", "Romance"};
    private TextView mTel;
    private TextView mTel2;
    private TextView mTel5;
    private int [] images = {R.drawable. action,
                             R.drawable. comedy,
                             R.drawable. thriller,
                             R.drawable. horror,
                             R.drawable. christian,
                             R.drawable. romance

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        mTel = (TextView) findViewById(R.id.tel);
        mTel2 = (TextView) findViewById(R.id.tel2);
        mTel5 = (TextView) findViewById(R.id.tel5);


        mTel.setOnClickListener(new  View.OnClickListener(){


            @Override
            public void onClick(View view) {
                Intent intent1 = getIntent();
                Intent intent = new Intent(MoviesActivity.this, ActionActivity.class);


                startActivity(intent);


            }
        });
        mTel2.setOnClickListener(new  View.OnClickListener(){


            @Override
            public void onClick(View view) {
                Intent intent1 = getIntent();
                Intent intent = new Intent(MoviesActivity.this, ThrillerActivity.class);


                startActivity(intent);


            }
        });

        mTel5.setOnClickListener(new  View.OnClickListener(){


            @Override
            public void onClick(View view) {
                Intent intent1 = getIntent();
                Intent intent = new Intent(MoviesActivity.this, RomanceActivity.class);


                startActivity(intent);


            }
        });


    }
}
