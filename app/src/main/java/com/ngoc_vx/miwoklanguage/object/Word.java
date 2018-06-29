package com.ngoc_vx.miwoklanguage.object;

public class Word {
    /** Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE_PROVIDED = -1;

    private int mImageResouceId = NO_IMAGE_PROVIDED;
    private String mMiwokTranslation;
    private String mDefaultTranslation;

    public Word(String defaultTranslation, String miwokTranslation) {
        this.mMiwokTranslation = miwokTranslation;
        this.mDefaultTranslation = defaultTranslation;
        //this(NO_IMAGE_PROVIDED, defaultTranslation, miwokTranslation);
    }

    public Word(int imageResouceId, String defaultTranslation, String miwokTranslation) {
        this.mImageResouceId = imageResouceId;
        this.mMiwokTranslation = miwokTranslation;
        this.mDefaultTranslation = defaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public void setMiwokTranslation(String miwokTranslation) {
        this.mMiwokTranslation = miwokTranslation;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public void setDefaultTranslation(String defaultTranslation) {
        this.mDefaultTranslation = defaultTranslation;
    }

    public int getImageResouceId() {
        return mImageResouceId;
    }

    public boolean hasImage() {
        return this.mImageResouceId != NO_IMAGE_PROVIDED;
    }
}
