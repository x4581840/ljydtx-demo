package com.demo.work;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListOperateUtils {
    public static <T> void addList(List<T> s, int index, T value) {
        if (s == null) {
            return;
        }

        int size = s.size();

        if (size > index) {
            s.add(index, value);
            return;
        }

        if (size == index) {
            s.add(value);
            return;
        }

        if (s instanceof ArrayList) {
            ArrayList<T> as = (ArrayList<T>)s;
            as.ensureCapacity(index + 1);
        }

        for (int i = s.size(); i < index; i++) {
            s.add(null);
        }
        s.add(value);
    }

    public static <T> void setList(List<T> s, int index, T value) {
        if (s == null) {
            return;
        }

        int size = s.size();

        if (size > index) {
            s.set(index, value);
            return;
        }

        if (size == index) {
            s.add(value);
            return;
        }

        if (s instanceof ArrayList) {
            ArrayList<T> as = (ArrayList<T>)s;
            as.ensureCapacity(index + 1);
        }

        for (int i = s.size(); i < index; i++) {
            s.add(null);
        }
        s.add(value);
    }

    public static <T> void deleteList(List<T> s, int index) {
        if (s != null && s.size() > index) {
            s.remove(index);
        }
    }

    public static <T> T getList(List<T> s, int index) {
        if (s != null && s.size() > index) {
            return s.get(index);
        }
        return null;
    }

    public static <T> List<List<T>> split(List<T> s, int length) {
        if (length < 1) {
            length = 1;
        }
        int start = 0;
        int size = s.size();
        int end = size <= length ? size : length;

        List<List<T>> ret = new LinkedList<>();

        for (; end - start > 0; start += length, end += length, end = size <= end ? size : end) {
            ret.add(s.subList(start, end));
        }

        return ret;
    }

    /**
     * 尽量均分为nums份左右，不保证是nums份
     */
    public static <T> List<List<T>> splitAverage(List<T> s, int nums) {
        if (nums < 1) {
            nums = 1;
        }
        List<List<T>> ret = new LinkedList<>();
        int size = s.size();
        int processorLen = size / nums;
        int mod = size % nums;
        int average = mod * 100 / nums;

        int count = 0;
        if (processorLen == 0 || processorLen == 1 && mod == 0) {
            for (int i = 0; i < size; i++) {
                ret.add(s.subList(i, i + 1));
            }
        } else {
            int lastEndIndex = 0;
            while (lastEndIndex < size) {
                int startIndex = lastEndIndex;
                int endIndex = startIndex + processorLen;
                count += average;
                if (count >= 100) {
                    endIndex++;
                    count = 0;
                }
                if (endIndex > size) {
                    endIndex = size;
                }
                lastEndIndex = endIndex;
                ret.add(s.subList(startIndex, endIndex));
            }
        }
        return ret;
    }
}
