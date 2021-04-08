package com.sabeeldev.galleryapp.Model;

import java.util.ArrayList;

public class Images {

    private int total;
    private ArrayList<hits> hits;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<Images.hits> getHits() {
        return hits;
    }

    public void setHits(ArrayList<Images.hits> hits) {
        this.hits = hits;
    }

    public static class hits {
        private String largeImageURL, previewURL, tags;
private boolean selected;


        public hits(String largeImageURL, String previewURL, String tags, boolean selected) {
            this.largeImageURL = largeImageURL;
            this.previewURL = previewURL;
            this.tags = tags;
            this.selected = selected;
        }

        public boolean getSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public String getLargeImageURL() {
            return largeImageURL;
        }

        public void setLargeImageURL(String largeImageURL) {
            this.largeImageURL = largeImageURL;
        }

        public String getPreviewURL() {
            return previewURL;
        }

        public void setPreviewURL(String previewURL) {
            this.previewURL = previewURL;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }
    }
}
