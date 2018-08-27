package com.example.adamstelmaszyk.myscraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DownloadPhoto {


    private Document doc;
    private List<String> PhotoList;

    public DownloadPhoto(){
        PhotoList = new ArrayList<String>();
    }

    public void download(List<String> Photolink) throws IOException {
        for(int i=0; i<Photolink.size(); i++)
        {
            String link = Photolink.get(i);
            doc = Jsoup.connect(link).get();

            Element imgs = doc.select("div[class=thumb-frame]").select("a[class=js-search-result-thumbnail non-js-link]").select("img[src]").first();



            PhotoList.add(imgs.attr("src"));
        }
    }


    public List<String> getPhotoList() {
        return PhotoList;
    }

    public void setPhotoList(List<String> photoList) {
        PhotoList = photoList;
    }
}
