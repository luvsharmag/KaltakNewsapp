package com.example.kaltak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsdetailActivity extends AppCompatActivity {
    String title,desc,content,imageUrl,url;
    private TextView titletv,sudescTv,contenttv;
    private ImageView newsIV;
    private Button readnewsbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetail);
        title = getIntent().getStringExtra("title");
        desc = getIntent().getStringExtra("desc");
        content = getIntent().getStringExtra("content");
        imageUrl = getIntent().getStringExtra("image");
        url = getIntent().getStringExtra("url");
        titletv = findViewById(R.id.idTVtitle);
        sudescTv = findViewById(R.id.idTVsubdesc);
        contenttv = findViewById(R.id.idTVcontent);
        newsIV = findViewById(R.id.idIVnews);
        readnewsbtn = findViewById(R.id.idbtnreadnews);
        titletv.setText(title);
        sudescTv.setText(desc);
        contenttv.setText(content);
        Picasso.get().load(imageUrl).into(newsIV);
        readnewsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}