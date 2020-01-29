package com.thoreaudesign.weatheroutdoors;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thoreaudesign.weatheroutdoors.serialization.Darksky.DatumHourly;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;

public class ForecastExpandableListAdapter extends BaseExpandableListAdapter
{

    private Context context;
    private List<String> expandableListTitle;
    private LinkedHashMap<String, List<DatumHourly>> expandableListData;

    public ForecastExpandableListAdapter(Context context, List<String> expandableListTitle,
                                       LinkedHashMap<String, List<DatumHourly>> expandableListData) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListData = expandableListData;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListData.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        // Get object from ForecastData
        final DatumHourly expandedListData = (DatumHourly) getChild(listPosition, expandedListPosition);

        // Inflate view from layout if null
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.forecast_list_child, null);
        }

        // Get LinearLayout from forecast_list_child.xml
        LinearLayout forecastListChildLayout = convertView.findViewById(R.id.forecastListChildLayout);

        // Iterate over TextViews in LinearLayout
        for (int i = 0; i < forecastListChildLayout.getChildCount(); i++)
        {
            // Grab view
            View currentView = forecastListChildLayout.getChildAt(i);
            Log.v("Current View: " + currentView.toString());

            if(currentView instanceof TextView)
            {
                // get the @+id string for the view
                String currentViewIdAsString =
                    currentView.getResources().getResourceEntryName(currentView.getId());
                Log.v("Current View ID String: " + currentViewIdAsString);

                Field[] fields = expandedListData.getClass().getDeclaredFields();

                for (Field field : fields)
                {
                    String fieldName = field.getName();
                    Log.v("Current Field: " + fieldName);

                    if(fieldName.equals(currentViewIdAsString))
                    {
                        String methodNameRoot = fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
                        String methodName = "get" + methodNameRoot;
                        Log.v("Method Name: " + methodName);

                        try
                        {
                            Method getProperty = DatumHourly.class.getMethod(methodName, (Class<?>[]) null);

                            String propertyValue = getProperty.invoke(expandedListData).toString();
                            Log.v("Property value: " + propertyValue);

                            ((TextView)currentView).setText(propertyValue);
                        }
                        catch (NoSuchMethodException e)
                        {
                            Log.e("Class DatumHourly does not contain method named '" + methodName + "().'");
                        }
                        catch (IllegalAccessException | InvocationTargetException ite)
                        {
                            Log.e("Failed to invoke method '" + methodName + "().'");
                        }
                    }
                }
            }
        }

        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListData.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.forecast_list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.forecast_list_group);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}