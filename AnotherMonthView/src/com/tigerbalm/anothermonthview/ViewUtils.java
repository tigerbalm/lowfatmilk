package com.tigerbalm.anothermonthview;

import com.tigerbalm.anothermonthview.exception.NotImpletmentedException;

import android.content.Context;
import android.text.format.Time;

public class ViewUtils {
	public static final int MONDAY_BEFORE_JULIAN_EPOCH = Time.EPOCH_JULIAN_DAY - 3;
	
	/**
     * Takes a number of weeks since the epoch and calculates the Julian day of
     * the Monday for that week.
     *
     * This assumes that the week containing the {@link Time#EPOCH_JULIAN_DAY}
     * is considered week 0. It returns the Julian day for the Monday
     * {@code week} weeks after the Monday of the week containing the epoch.
     *
     * @param week Number of weeks since the epoch
     * @return The julian day for the Monday of the given week since the epoch
     */
    public static int getJulianMondayFromWeeksSinceEpoch(int week) {
        return MONDAY_BEFORE_JULIAN_EPOCH + week * 7;
    }

	public static String formatDateRange(Context context, Long millis,
			Long millis2, int formatShowDate) {
		throw new NotImpletmentedException("Not yet implemented!!");
	}

}
