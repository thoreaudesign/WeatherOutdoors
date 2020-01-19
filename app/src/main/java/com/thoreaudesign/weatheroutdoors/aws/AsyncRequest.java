package com.thoreaudesign.weatheroutdoors.aws;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunctionException;
import com.thoreaudesign.weatheroutdoors.Log;

public class AsyncRequest extends AsyncTask<RequestParams, Integer, Object>
{
    private RequestTemplate requestTemplate;
    private String cacheDir;
    private ProgressBar progress;

    public interface Listener
    {
        void onTaskResult(Object response);
    }

    private Listener listener;

    public void setListener(Listener listener)
    {
        this.listener = listener;
    }

    public AsyncRequest(RequestTemplate requestTemplate, String cacheDir, ProgressBar progress)
    {
        this.requestTemplate = requestTemplate;
        this.cacheDir = cacheDir;
        this.progress = progress;
    }

    private RequestTemplate getRequestTemplate()
    {
        return this.requestTemplate;
    }


    @Override
    protected Object doInBackground(RequestParams... params)
    {
        WeatherInterface weatherInterface =
            this.getRequestTemplate().getLambdaFactory().build(WeatherInterface.class);

        try
        {
            return this.getRequestTemplate().getLambdaResponse(weatherInterface, params);
        }
        catch (LambdaFunctionException lfe)
        {
            String exception = lfe.getMessage();
            Log.d(exception);
            Log.v(lfe.getStackTrace().toString());
            return exception;
        }
        catch (Exception e)
        {
            Log.e(e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(Object response)
    {
        if(listener != null)
        {
            listener.onTaskResult(response);
        }

        progress.setVisibility(View.GONE);

    }

    @Override
    protected void onPreExecute()
    {
        progress.setVisibility(View.VISIBLE);
    }
}
