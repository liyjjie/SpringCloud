package com.jack.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：CollectsUtils
 * @date ： 2019-10-22 14:16
 * @modified By：
 */
public class CollectsUtils {

    //将list按blockSize大小等分，最后多余的单独一份
    public static <T> List<List<T>> subList(List<T> list, int blockSize) {
        List<List<T>> lists = new ArrayList<List<T>>();
        if (list != null && blockSize > 0) {
            int listSize = list.size();
            if (listSize <= blockSize) {
                lists.add(list);
                return lists;
            }
            int batchSize = listSize / blockSize;
            int remain = listSize % blockSize;
            for (int i = 0; i < batchSize; i++) {
                int fromIndex = i * blockSize;
                int toIndex = fromIndex + blockSize;
                lists.add(list.subList(fromIndex, toIndex));
            }
            if (remain > 0) {
                lists.add(list.subList(listSize - remain, listSize));
            }
        }
        return lists;
    }
}
