package com.jack.utils.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jack.utils.track.TrackData;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * @author ：liyongjie
 * @ClassName ：LogData
 * @date ： 2019-11-28 09:46
 * @modified By：
 */
public class LogData {

    private TrackData track;
    private String code;
    private String name;
    private String summary;
    private String errorMsg;

    public LogData(TrackData track, String code, String name, String summary) {
        this.track = track;
        this.code = code;
        this.name = name;
        this.summary = summary;
    }

    public LogData(TrackData track, String code, String name, Exception e) {
        this.track = track;
        this.code = code;
        this.name = name;
        this.errorMsg = ExceptionUtils.getStackTrace(e);
    }

    public LogData(TrackData track, String code, String name, String summary, Exception e) {
        this.track = track;
        this.code = code;
        this.name = name;
        this.summary = summary;
        this.errorMsg = ExceptionUtils.getStackTrace(e);
    }

    public TrackData getTrack() {
        return this.track;
    }

    public void setTrack(TrackData track) {
        this.track = track;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException var3) {
            throw new RuntimeException(var3);
        }
    }
}
