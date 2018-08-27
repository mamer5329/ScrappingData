package com.example.adamstelmaszyk.myscraping;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemActivity extends AppCompatActivity {

    private RecyclerView mRecyclerview;
    private WorkAdapter mAdapter;
    private TextView textView;



    private List<Upload> mUploads;
    private ProgressDialog progressDialog;

    private List<String> head;
    private List<String> link;
    private List<String> findPhoto1;
    private List<String> findPhoto2;
    private List<String> findPhoto3;
    private List<String> description;
    private List<String> descriptionIND;
    private List<String> headIND;
    private List<String> linkIND;
    private List<String> descriptionGUM;
    private List<String> headGUM;
    private List<String> linkGUM;

    private String City_job;
    private String Title_job;
    private String Time_job;

    private String reedWeb;
    private String indeedWeb;
    private String gumtreeWeb;

    private Intent intentReciveData;
    private Bundle extras;

    //Content content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        mRecyclerview = findViewById(R.id.recycler_view);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        textView = (TextView) findViewById(R.id.NORMAL_text);

        head = new ArrayList<>();
        description = new ArrayList<>();
        descriptionIND = new ArrayList<>();
        descriptionGUM = new ArrayList<>();
        headGUM = new ArrayList<>();
        linkGUM = new ArrayList<>();
        findPhoto1 = new ArrayList<>();
        findPhoto2 = new ArrayList<>();
        findPhoto3 = new ArrayList<>();
        mUploads = new ArrayList<>();

        intentReciveData = getIntent();
        extras = intentReciveData.getExtras();
        City_job = extras.getString("City_job");
        Title_job = extras.getString("Name_job");
        Time_job = extras.getString("Time_job");

        link = new ArrayList<>();

        website();

                new Content().execute();


    }

    private void upload() {

        mUploads.clear();

        for(int i=0; i<5;i++)
        {
            Upload upload1 = new Upload(head.get(i),link.get(i),R.drawable.reedphoto,findPhoto1.get(i),description.get(i));
            mUploads.add(upload1);
        }


        if(!(headIND==null))
        {
            for (int i = 0; i < 5; i++) {
                Upload upload1 = new Upload(headIND.get(i), linkIND.get(i), R.drawable.indeedphoto, findPhoto2.get(i), descriptionIND.get(i));
                mUploads.add(upload1);
            }
        }

        for(int i=0; i<5;i++)
        {
            Upload upload1 = new Upload(headGUM.get(i),linkGUM.get(i),R.drawable.gumtree,findPhoto3.get(i),descriptionGUM.get(i));
            mUploads.add(upload1);
        }

        mAdapter = new WorkAdapter(ItemActivity.this,mUploads);

        mRecyclerview.setAdapter(mAdapter);
    }

    private class Content extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(ItemActivity.this);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            upload();
            progressDialog.dismiss();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
               Reed reed = new Reed(reedWeb);
               reed.download();

               head = reed.getHeadersList();
               description = reed.getDescriptions();

               link = reed.getLinks();

                for(int i=0; i<5;i++)
                {
                    String [] dividedString;
                    dividedString = head.get(i).split(" ");
                    findPhoto1.add("https://stock.adobe.com/search?load_type=search&native_visual_search=&similar_content_id=&is_recent_search=&k=" + dividedString[0]);
                }

            } catch (IOException e) {
                Toast.makeText(ItemActivity.this, "Reed Error", Toast.LENGTH_LONG).show();
            }

            if(!(indeedWeb==null))
            {
                try {

                    Indeed indeed = new Indeed(indeedWeb);
                    indeed.download();

                    headIND = indeed.getHeadersList();
                    descriptionIND = indeed.getDescriptions();
                    linkIND = indeed.getLinks();

                    for (int i = 0; i < 5; i++) {
                        String[] dividedString;
                        dividedString = head.get(i).split(" ");
                        findPhoto2.add("https://stock.adobe.com/search?load_type=search&native_visual_search=&similar_content_id=&is_recent_search=&k=" + dividedString[0]);
                    }


                } catch (IOException e) {
                    Toast.makeText(ItemActivity.this, "Indeed Error", Toast.LENGTH_LONG).show();
                }
            }

            try {
                Gumtree gumtree = new Gumtree(gumtreeWeb);
                gumtree.download();

                headGUM = gumtree.getHeadersList();
                descriptionGUM = gumtree.getDescriptions();
                linkGUM = gumtree.getLinks();

                for(int i=0; i<5;i++)
                {
                    String [] dividedString;
                    dividedString = headGUM.get(i).split(" ");
                    findPhoto3.add("https://stock.adobe.com/search?load_type=search&native_visual_search=&similar_content_id=&is_recent_search=&k=" + dividedString[0]);
                }

            } catch (IOException e) {
                Toast.makeText(ItemActivity.this, "Indeed Error", Toast.LENGTH_LONG).show();
            }

            return null;
        }
    }



    public void website()
    {
        int i,j,k;


        String [] cit = new String[]{"London","Leicester","Liverpool","Manchester","Birmingham"};
        String [] citGMU = new String[]{"325","543","1720","336","1596"};

        String [] jobb = new String[]{"IT","Logistics","Engineering","Admin and Secretarial","Education","Accountancy","Sales","Customer Service","Health","Social Care","Factory","Catering"};
        String [] jobb2 = new String[]{"20","51","33","2","94","1","86","69","40","90","66","51"};

        String [] tim = new String[]{"Part time","Full time"};
        String [] tim1 = new String[]{"147","146"};
        String [] tim2 = new String[]{"parttime","fulltime"};



        for(i=0; i<cit.length; i++)
        {

            if(City_job.equals(cit[i]))
                break;

        }





        if(!Title_job.equals("All Jobs") && !Time_job.equals("Part and Full time"))
        {
            for(j=0; j<jobb.length; j++)
            {
                if(Title_job.equals(jobb[j]))
                {
                    break;
                }
            }
            for(k=0; k<tim.length; k++)
            {
                if(Time_job.equals(tim[k]))
                {
                    //textView.setText(Integer.toString(k));
                    break;
                }
            }
            gumtreeWeb="https://www.gumtree.com/jobs/searchjobs/?Sector="+jobb2[j]+"&Hours"+tim1[k]+"LocationId="+citGMU[i]+"&radiallocation=1&countrycode=GB&sort=Date";
            reedWeb = "https://www.reed.co.uk/jobs/"+jobb[j]+"-jobs-in-"+City_job+"?"+tim2[k]+"=True&proximity=0";
            //reedWeb = "https://www.reed.co.uk/jobs/admin-secretarial-pa-jobs-in-"+City_job+"?"+tim2[k]+"=True&proximity=0";
            //reedWeb="https://www.reed.co.uk/jobs/engineering-jobs-in-leicester";
            //reedWeb="https://www.reed.co.uk/jobs/jobs-in-"+City_job+"?"+tim2[k]+"=True&proximity=0";




            CheckreedWeb2(tim2[k]);

            if(Title_job.equals("Accountancy")) {
                indeedWeb = "https://www.indeed.co.uk/jobs?q=Account+Assistant&sort=date&l="+City_job+"&radius=0&jt="+tim2[k];
            }
            else if(Title_job.equals("Sales"))
            {
                indeedWeb = "https://www.indeed.co.uk/jobs?q=Sales+Assistant&sort=date&l="+City_job+"&radius=0&jt="+tim2[k];
            }
            else if(Title_job.equals("Factory"))
            {
                indeedWeb = "https://www.indeed.co.uk/jobs?q=Warehouse+Operative&sort=date&l="+City_job+"&radius=0&jt="+tim2[k];
            }
            else if(Title_job.equals("Social Care"))
            {
                indeedWeb = "https://www.indeed.co.uk/jobs?q=Care+Assistant&sort=date&l="+City_job+"&radius=0&jt="+tim2[k];
            }
            else
            {
                indeedWeb=null;
            }
        }
        else if(!Title_job.equals("All Jobs"))
        {
            for(j=0; j<jobb.length; j++)
            {
                if(Title_job.equals(jobb[j]))
                {
                    break;
                }
            }
            gumtreeWeb="https://www.gumtree.com/jobs/searchjobs/?Sector="+jobb2[j]+"&LocationId="+citGMU[i]+"&radiallocation=1&countrycode=GB&sort=Date";
            reedWeb="https://www.reed.co.uk/jobs/"+jobb[j]+"-jobs-in-"+City_job+"?"+"proximity=0";


            CheckreedWeb();



            if(Time_job.equals("Accountancy")) {
                indeedWeb = "https://www.indeed.co.uk/jobs?q=Account+Assistant&sort=date&l="+City_job+"&radius=0";
            }
            else if(Title_job.equals("Sales"))
            {
                indeedWeb = "https://www.indeed.co.uk/jobs?q=Sales+Assistant&sort=date&l="+City_job+"&radius=0";
            }
            else if(Title_job.equals("Factory"))
            {
                indeedWeb = "https://www.indeed.co.uk/jobs?q=Warehouse+Operative&sort=date&l="+City_job+"&radius=0";
            }
            else if(Title_job.equals("Social Care"))
            {
                indeedWeb = "https://www.indeed.co.uk/jobs?q=Care+Assistant&sort=date&l="+City_job+"&radius=0";
            }
            else
            {
                indeedWeb=null;
            }

        }
        else if(!Time_job.equals("Part and Full time"))
        {
            for(k=0; k<tim.length; k++)
            {
                if(Time_job.equals(tim[k]))
                {
                    break;
                }
            }
            gumtreeWeb="https://www.gumtree.com/jobs/searchjobs/?Hours="+tim1[k]+"&LocationId="+citGMU[i]+"&radiallocation=1&countrycode=GB&sort=Date";
            reedWeb="https://www.reed.co.uk/jobs/jobs-in-"+City_job+"?"+tim2[k]+"=True&proximity=0";
            indeedWeb = "https://www.indeed.co.uk/jobs?l="+City_job+"&radius=0&sort=date&jt="+tim2[k];
        }
        else
        {
            reedWeb = "https://www.reed.co.uk/jobs/jobs-in-"+City_job+"?proximity=0";
            gumtreeWeb = "https://www.gumtree.com/jobs/searchjobs/?LocationId="+citGMU[i]+"&radiallocation=1&countrycode=GB&sort=Date";
            indeedWeb = "https://www.indeed.co.uk/jobs?l="+City_job+"&radius=0&sort=date";
        }

    }

    public void CheckreedWeb()
    {

        if(Title_job.equals("Admin and Secretarial"))
        {
            reedWeb = "https://www.reed.co.uk/jobs/admin-secretarial-pa-jobs-in-"+City_job+"?"+"proximity=0";
        }
        else if(Title_job.equals("Customer Service"))
        {
            reedWeb = "https://www.reed.co.uk/jobs/customer-service-jobs-in-"+City_job+"?"+"proximity=0";
        }
        else if(Title_job.equals("Social Care"))
        {
            reedWeb = "https://www.reed.co.uk/jobs/social-care-jobs-in-"+City_job+"?"+"proximity=0";
        }

    }

    public void CheckreedWeb2(String tim2)
    {
        if(Title_job.equals("Admin and Secretarial"))
        {
            reedWeb = "https://www.reed.co.uk/jobs/admin-secretarial-pa-jobs-in-"+City_job+"?"+tim2+"=True&proximity=0";
        }
        else if(Title_job.equals("Customer Service"))
        {
            reedWeb = "https://www.reed.co.uk/jobs/customer-service-jobs-in-"+City_job+"?"+tim2+"=True&proximity=0";
        }
        else if(Title_job.equals("Social Care"))
        {
            reedWeb = "https://www.reed.co.uk/jobs/social-care-jobs-in-"+City_job+"?"+tim2+"=True&proximity=0";
        }
    }




}

