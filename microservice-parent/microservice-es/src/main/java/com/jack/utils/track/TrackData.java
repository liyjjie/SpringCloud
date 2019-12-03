package com.jack.utils.track;

/**
 * @author ：liyongjie
 * @ClassName ：TrackData
 * @date ： 2019-11-28 09:47
 * @modified By：
 */
public class TrackData {
    private String trackNo;
    private String parentTrackNo;

    public TrackData() {
    }

    public TrackData(String trackNo) {
        this.trackNo = trackNo;
    }

    public TrackData(String trackNo, String parentTrackNo) {
        this.trackNo = trackNo;
        this.parentTrackNo = parentTrackNo;
    }

    public String getTrackNo() {
        return this.trackNo;
    }

    public void setTrackNo(String trackNo) {
        this.trackNo = trackNo;
    }

    public String getParentTrackNo() {
        return this.parentTrackNo;
    }

    public void setParentTrackNo(String parentTrackNo) {
        this.parentTrackNo = parentTrackNo;
    }
}

