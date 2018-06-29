package com.ngoc_vx.miwoklanguage.object;

public class Word {
    /**
     * Constant value that represents no image was provided for this word
     */
    private static final int NO_IMAGE_PROVIDED = -1;

    // Image resource Id for the word
    private int mImageResouceId = NO_IMAGE_PROVIDED;
    // Miwok translation for the word
    private String mMiwokTranslation;
    // Default translation for the miwok word
    private String mDefaultTranslation;
    // Audio resource id for the word
    private int mAudioResourceId;

    public Word(String defaultTranslation, String miwokTranslation, int audioResourceId) {
        this.mMiwokTranslation = miwokTranslation;
        this.mDefaultTranslation = defaultTranslation;
        this.mAudioResourceId = audioResourceId;
        //this(NO_IMAGE_PROVIDED, defaultTranslation, miwokTranslation);
    }

    public Word(int imageResouceId, String defaultTranslation, String miwokTranslation, int audioResourceId) {
        this.mImageResouceId = imageResouceId;
        this.mMiwokTranslation = miwokTranslation;
        this.mDefaultTranslation = defaultTranslation;
        this.mAudioResourceId = audioResourceId;
    }

    /**
     * Get the Miwok translation of the word.
     */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public void setMiwokTranslation(String miwokTranslation) {
        this.mMiwokTranslation = miwokTranslation;
    }

    /**
     * Get the default translation of the word.
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public void setDefaultTranslation(String defaultTranslation) {
        this.mDefaultTranslation = defaultTranslation;
    }

    /**
     * Return the image resource ID of the word.
     */
    public int getImageResouceId() {
        return mImageResouceId;
    }

    /**
     * Returns whether or not there is an image for this word.
     */
    public boolean hasImage() {
        return this.mImageResouceId != NO_IMAGE_PROVIDED;
    }

    /**
     * Return the audio resource ID of the word.
     */
    public int getAudioResourceId() {
        return mAudioResourceId;
    }
}
