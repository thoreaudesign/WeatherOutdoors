package com.thoreaudesign.weatheroutdoors.aws;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.thoreaudesign.weatheroutdoors.Log;

public class AsyncRequest extends AsyncTask<RequestParams, Integer, Object>
{
    private String cacheDir;

    private Listener listener;

    private ProgressBar progress;

    private RequestTemplate requestTemplate;

    public AsyncRequest(RequestTemplate paramRequestTemplate, String paramString, ProgressBar paramProgressBar)
    {
        this.requestTemplate = paramRequestTemplate;
        this.cacheDir = paramString;
        this.progress = paramProgressBar;
    }

    private RequestTemplate getRequestTemplate()
    {
        return this.requestTemplate;
    }

    protected Object doInBackground(RequestParams... params)
    {
        ILambdaWeatherBridge weatherInterface = (ILambdaWeatherBridge) getRequestTemplate().getLambdaFactory().build(ILambdaWeatherBridge.class);

        try
        {
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
}
