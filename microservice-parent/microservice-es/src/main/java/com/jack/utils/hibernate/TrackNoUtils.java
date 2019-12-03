package com.jack.utils.hibernate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jack.utils.log.LogData;
import com.jack.utils.log.LogMessage;
import com.jack.utils.track.TrackData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：liyongjie
 * @ClassName ：TrackNoUtils
 * @date ： 2019-11-28 09:54
 * @modified By：
 */
public class TrackNoUtils {

    private static final Logger logger = LoggerFactory.getLogger(TrackNoUtils.class);
    private static ThreadLocal<TrackData> instance = new ThreadLocal();
    public static final TrackData EMPTY = new TrackData();
    private static ObjectMapper objectMapper = new ObjectMapper();

    public TrackNoUtils() {
    }

    public static void setTrack(TrackData trackNo) {
        instance.set(trackNo);
    }

    public static TrackData getTrack() {
        TrackData trackData = (TrackData)instance.get();
        return trackData == null ? EMPTY : trackData;
    }

    public static String getTrackNo() {
        return getTrack().getTrackNo();
    }

    public static String getParentTrackNo() {
        return getTrack().getParentTrackNo();
    }

    public static String toJson() {
        try {
            return objectMapper.writeValueAsString(getTrack());
        } catch (JsonProcessingException var1) {
            logger.error((new LogData(getTrack(), LogMessage.UNEXPECTED_ERROR.getCode(), LogMessage.UNEXPECTED_ERROR.getName(), "TrackNoUtils toJson Fail", var1)).toJson());
            return "";
        }
    }
}
