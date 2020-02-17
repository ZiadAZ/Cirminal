package com.example.cirminal;

import java.util.Date;
import java.util.UUID;

public class Crime {
    private UUID mId;
    private  String mTitle;
    private Date mDate;
    private boolean mSolved;
    private String mSuspect;

    public void setmSuspect(String mSuspect) {
        this.mSuspect = mSuspect;
    }

    public String getmSuspect() {
        return mSuspect;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }
    public Crime() {
        this(UUID.randomUUID());
    }

    public Crime(UUID uuid) {
        mId=uuid;
        mDate=new Date();
    }
    public String getPhotoFilename() {
        return "IMG_" + getmId().toString() + ".jpg";
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

    public UUID getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public Date getmDate() {
        return mDate;
    }

    public boolean ismSolved() {
        return mSolved;
    }
}
