package org.zhl.date;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.stream.IntStream;

public class DateTest {

    @Test
    void testDayOfWeek() {

        final Calendar instance = Calendar.getInstance();
        instance.set(Calendar.DAY_OF_WEEK, 1);
        instance.set(Calendar.HOUR, 8);
        instance.set(Calendar.MINUTE, 30);

        final Calendar instance1 = Calendar.getInstance();
        instance1.set(Calendar.DAY_OF_WEEK, 1);
        instance.set(Calendar.HOUR, 8);
        instance.set(Calendar.MINUTE, 31);

        System.out.println(instance.compareTo(instance1));

        final String displayName = instance.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.CHINA);
        System.out.println(displayName);
    }

    @Test
    void testCa() {

        final int[] holdArray = new int[7];

        for (int i = 7; i > 0; i--) {
            hash(i, holdArray, 7);
        }

        System.out.println(Arrays.toString(holdArray));

    }

    private void hash(final int value, final int[] holdArray, final int firstDayOfWeek) {
        holdArray[value % firstDayOfWeek] = value;
    }

    @Test
    void testDate() {

        final int[] data = IntStream.range(1, 8).toArray();

        // 计算偏移量
        final int offset = 8 - 6;

        final int[] target = new int[7];

        for (int i = 0; i < data.length; i++) {
            final int i1 = (i + offset) % (data.length);
            target[i1] = data[i];
        }

        System.out.println(Arrays.toString(data));
        System.out.println(Arrays.toString(target));
    }

    @Test
    void testDa() {
        final int[] data = IntStream.range(1, 8).toArray();

        // 计算偏移量
        final int offset = 8 - 6;

        de(data, offset);

        System.out.println(Arrays.toString(data));
    }

    private void de(final int[] data, final int offset) {
        ArrayUtils.reverse(data);
        ArrayUtils.reverse(data, 0, offset);
        ArrayUtils.reverse(data, offset, data.length);
    }

}
