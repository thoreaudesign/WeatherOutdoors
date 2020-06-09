package com.thoreaudesign.weatheroutdoors.aws;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.thoreaudesign.weatheroutdoors.Log;

import org.json.JSONObject;

public class AsyncRequest extends AsyncTask<RequestParams, Integer, Object>
{
    private Listener listener;

    private ProgressBar progress;

    private RequestTemplate requestTemplate;

    public AsyncRequest(RequestTemplate paramRequestTemplate, ProgressBar paramProgressBar)
    {
        this.requestTemplate = paramRequestTemplate;
        this.progress = paramProgressBar;
    }

    private RequestTemplate getRequestTemplate()
    {
        return this.requestTemplate;
    }

    protected Object doInBackground(RequestParams... params)
    {
        ILambdaWeatherBridge weatherInterface = getRequestTemplate().getLambdaFactory().build(ILambdaWeatherBridge.class);

        try
        {
            Log.d("Querying AWS Lambda for weather data...");
            return getRequestTemplate().getLambdaResponse(weatherInterface, params);
        }
        catch (Exception e)
        {
            Log.v(e.getStackTrace().toString());
            return e.getMessage();
        }
    }

    protected void onPostExecute(Object paramObject)
    {
        Log.v("AsyncTask completed.");
        Listener listener = this.listener;

        if (listener != null)
        {
            listener.onTaskResult(paramObject);
        }
        this.progress.setVisibility(View.GONE);
    }

    protected void onPreExecute()
    {
        this.progress.setVisibility(View.VISIBLE);
    }

    public void setListener(Listener paramListener)
    {
        this.listener = paramListener;
    }

    public interface Listener
    {
        void onTaskResult(Object param1Object);
    }

    public boolean isResponseValid(String response)
    {
        boolean responseValid = false;

        try
        {
            JSONObject responseJson = new JSONObject(response);
            ServiceName[] serviceNames = ServiceName.values();

            for(int i = 0; i < serviceNames.length; i++)
            {
                ServiceName serviceName = serviceNames[i];

                if (responseJson.has(serviceName.toLower()))
                {
                    responseValid = true;
                }
                else
                {
                    Log.v("Lambda function returned invalid data from service '" + serviceName.toLower() + ".'");
                }
            }
        }
        catch (Exception exception)
        {
            Log.e(exception.getMessage());
            Log.v(response);
        }

        return responseValid;
    }
}
