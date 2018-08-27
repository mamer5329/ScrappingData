package com.example.adamstelmaszyk.myscraping;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class MoreInformationActivity extends AppCompatActivity {


    ProgressDialog progressDialog;
    String img;

    ImageView image_MoreInf;
    TextView Name_of_work, Description_of_work;
    Button Btn_Go_to_web;
    Intent intent;
    Bundle extras;
    String Photolink, MI_name, MI_description,MI_web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_information);

        image_MoreInf = (ImageView) findViewById(R.id.IN_PhotoOfWork);
        Btn_Go_to_web = (Button) findViewById(R.id.IN_Apply);
        Description_of_work = (TextView) findViewById(R.id.IN_Description);
        Name_of_work = (TextView) findViewById(R.id.IN_Name);
        intent = getIntent();
        extras = intent.getExtras();
        Photolink = extras.getString("photo_job");
        MI_name = extras.getString("name_job");
        MI_web = extras.getString("url_job");
        MI_description = extras.getString("description_job");

        Name_of_work.setText(MI_name);
        Description_of_work.setText(MI_description);

        new Content().execute();

        Btn_Go_to_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(MI_web));
                startActivity(browserIntent);
            }
        });
    }


    private class Content extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MoreInformationActivity.this);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Picasso.with(getApplicationContext()).load(img).resize(200,200).centerCrop().into(image_MoreInf);
            //button.setText(img.get(0));
            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                Document doc;
                doc = Jsoup.connect(Photolink).get();

                Element imgs = doc.select("div[class=thumb-frame]").select("a[class=js-search-result-thumbnail non-js-link]").select("img[src]").first();

                img =imgs.attr("src").toString();

            } catch (IOException e) {
                Toast.makeText(MoreInformationActivity.this, "Reed Error", Toast.LENGTH_LONG).show();



            }
            return null;
        }
    }
}


