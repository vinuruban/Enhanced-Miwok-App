package com.example.android.miwok;

public class Word {
    private int hasImage;
    private String miwokTranslation;
    private String defaultTranslation;
    private int imageResourceId;
    private int mediaResourceId;

    public String getMiwokTranslation() {
        return miwokTranslation;
    }

    public String getDefaultTranslation() {
        return defaultTranslation;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getMediaResourceId() {
        return mediaResourceId;
    }

    //    Constructor for PhrasesActivity since no images are needed
    public Word(String miwokTranslation, String defaultTranslation, int mediaResourceId) {
        this.miwokTranslation = miwokTranslation;
        this.defaultTranslation = defaultTranslation;
        hasImage = 0;
        this.mediaResourceId = mediaResourceId;
    }

//    Constructor for all the other Activities since images are needed
    public Word(String miwokTranslation, String defaultTranslation, int imageResourceId, int mediaResourceId) {
        this.miwokTranslation = miwokTranslation;
        this.defaultTranslation = defaultTranslation;
        this.imageResourceId = imageResourceId;
        hasImage = 1;
        this.mediaResourceId = mediaResourceId;
    }

    public boolean hasImage() {
        return hasImage == 1;
    }
}
