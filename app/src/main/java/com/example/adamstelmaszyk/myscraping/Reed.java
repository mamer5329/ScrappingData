package com.example.adamstelmaszyk.myscraping;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reed {
    private Document doc;
    private List<String> headersList;
    private List<String> links;
    private List<String> descriptions;

    public Reed(String nameofWeb) throws IOException
    {
        doc = Jsoup.connect(nameofWeb).get();
        headersList = new ArrayList<String>();
        links = new ArrayList<String>();
        descriptions = new ArrayList<String>();
    }

    public void download()
    {

        Elements headss = doc.select("h3[class=title]").select("a[title]");
        Elements linkss = doc.select("h3[class=title]").select("a[href]");
        Elements descriptionn = doc.select( "div[class=description hidden-xs]");

        List<String> head = new ArrayList<String>();
        List<String> link = new ArrayList<String>();
        List<String> description = new ArrayList<String>();


        for(Element el:headss)
        {
            head.add(el.text().toString());
        }

        for(Element el:linkss)
        {
            link.add("https://www.reed.co.uk"+el.attr("href").toString());
        }
        for(Element el:descriptionn)
        {
            description.add(el.text().toString());
        }
        //do if we have less than 10 jobs
        for(int i=2; i<7;i++)
        {
            headersList.add(head.get(i));
            links.add(link.get(i));
            descriptions.add(description.get(i));
        }
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public List<String> getHeadersList() {
        return headersList;
    }

    public List<String> getLinks() {
        return links;
    }
}
