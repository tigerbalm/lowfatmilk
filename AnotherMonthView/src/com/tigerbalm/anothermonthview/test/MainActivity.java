package com.tigerbalm.anothermonthview.test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import com.tigerbalm.anothermonthview.R;
import com.tigerbalm.anothermonthview.SimpleWeekView;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Calendar cal = Calendar.getInstance(Locale.getDefault());
        TextView tv = (TextView) findViewById(R.id.hello);
        tv.setBackgroundColor(Color.CYAN);
        
		SimpleWeekView swv = (SimpleWeekView) findViewById(R.id.one_week);
		
		HashMap<String, Integer> drawingParams = new HashMap<String, Integer>();
		drawingParams.put(SimpleWeekView.VIEW_PARAMS_HEIGHT, 0);
                //(parent.getHeight() - WEEK_7_OVERHANG_HEIGHT) / mNumWeeks);
        drawingParams.put(SimpleWeekView.VIEW_PARAMS_SELECTED_DAY, -1); //selectedDay);
        drawingParams.put(SimpleWeekView.VIEW_PARAMS_SHOW_WK_NUM, 1); //mShowWeekNumber ? 1 : 0);
        drawingParams.put(SimpleWeekView.VIEW_PARAMS_WEEK_START, cal.getFirstDayOfWeek() - 1); //mFirstDayOfWeek);
        drawingParams.put(SimpleWeekView.VIEW_PARAMS_NUM_DAYS, 7); //mDaysPerWeek);
        drawingParams.put(SimpleWeekView.VIEW_PARAMS_WEEK, 0); //position);
        drawingParams.put(SimpleWeekView.VIEW_PARAMS_FOCUS_MONTH, Calendar.JANUARY); //mFocusMonth);
        
        swv.setWeekParams(drawingParams, cal.getTimeZone().getID()); //mSelectedDay.timezone);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
