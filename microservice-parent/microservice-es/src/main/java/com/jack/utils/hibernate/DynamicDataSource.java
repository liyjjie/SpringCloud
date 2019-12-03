package com.jack.utils.hibernate;

import com.jack.utils.log.LogData;
import com.jack.utils.log.LogMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author ：liyongjie
 * @ClassName ：DynamicDataSource
 * @date ： 2019-11-28 09:53
 * @modified By：
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    public DynamicDataSource() {
    }

    protected Object determineCurrentLookupKey() {
        logger.debug((new LogData(TrackNoUtils.getTrack(), LogMessage.DYNAMIC_DS_DEBUG.getCode(), LogMessage.DYNAMIC_DS_DEBUG.getName(), "DynamicDataSource determineCurrentLookupKey,readOnly=" + HandleDataSource.getDataSource())).toJson());
        Boolean readOnly = HandleDataSource.getDataSource();
        if (readOnly == null) {
            return "write";
        } else {
            return readOnly ? "read" : "write";
        }
    }
}
