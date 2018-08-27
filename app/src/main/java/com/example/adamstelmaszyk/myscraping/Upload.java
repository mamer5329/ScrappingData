package com.example.adamstelmaszyk.myscraping;

public class Upload {

    private String mName;
    private String mWebUrl;
    private Integer imgUrl;
    private String imgWebFinder;
    private String mDescription;

    public Upload()
    {

    }

    public Upload(String mName, String mWebUrl, Integer imgUrl, String imgWebFinder, String mDescription)
    {
        this.mName = mName;
        this.mWebUrl = mWebUrl;
        this.imgUrl = imgUrl;
        this.imgWebFinder = imgWebFinder;
        this.mDescription = mDescription;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmWebUrl() {
        return mWebUrl;
    }

    public void setmWebUrl(String mWebUrl) {
        this.mWebUrl = mWebUrl;
    }

    public Integer getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(Integer imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgWebFinder() {
        return imgWebFinder;
    }

    public void setImgWebFinder(String imgWebFinder) {
        this.imgWebFinder = imgWebFinder;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
