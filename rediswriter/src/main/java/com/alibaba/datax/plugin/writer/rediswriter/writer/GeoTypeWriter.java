package com.alibaba.datax.plugin.writer.rediswriter.writer;

import com.alibaba.datax.common.element.Record;
import com.alibaba.datax.common.exception.CommonErrorCode;
import com.alibaba.datax.common.exception.DataXException;
import com.alibaba.datax.common.plugin.RecordReceiver;
import com.alibaba.datax.common.util.Configuration;
import com.alibaba.datax.plugin.writer.rediswriter.Constant;
import com.alibaba.datax.plugin.writer.rediswriter.Key;
import com.alibaba.datax.plugin.writer.rediswriter.RedisWriteAbstract;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class GeoTypeWriter extends RedisWriteAbstract {

    List<Configuration> geoFieldIndexs;

    public GeoTypeWriter(Configuration configuration) {
        super(configuration);
    }

    @Override
    public void checkAndGetParams() {
        super.checkAndGetParams();
        Configuration detailConfig = configuration.getConfiguration(Key.CONFIG);
        geoFieldIndexs = detailConfig.getListConfiguration(Key.COLVALUE);
    }

    @Override
    public void addToPipLine(RecordReceiver lineReceiver) {
        Record record;

        while ((record = lineReceiver.getFromReader()) != null) {
            String redisKey;

            Double longitude = null;
            Double latitude = null;
            String member = null;

            if (null != keyIndex) {
                String key = record.getColumn(keyIndex).asString();
                redisKey = keyPreffix + key + keySuffix;
            } else {
                redisKey = keyPreffix + strKey + keySuffix;
            }

            for (Configuration geoFieldIndex : geoFieldIndexs) {
                String filed = geoFieldIndex.getString(Key.COL_NAME);
                Integer index = geoFieldIndex.getInt(Key.COL_INDEX);

                switch (filed) {
                    case Constant.GEO_NAME_LONGITUDE:
                        longitude = record.getColumn(index).asDouble();
                        break;
                    case Constant.GEO_NAME_LATITUDE:
                        latitude = record.getColumn(index).asDouble();
                        break;
                    case Constant.GEO_NAME_MEMBER:
                        member = record.getColumn(index).asString();
                        break;
                }
            }

            if (longitude == null || latitude == null || StringUtils.isBlank(member)) {
                throw DataXException.asDataXException(CommonErrorCode.CONFIG_ERROR, "rediswriter经纬度数据获取配置错误.");
            }

            pipelined.geoadd(redisKey, longitude, latitude, member);
            pipelined.expire(redisKey, expire);
            records++;

        }
    }


}
