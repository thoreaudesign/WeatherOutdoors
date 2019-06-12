package com.thoreaudesign.weatheroutdoors.aws;

import android.os.AsyncTask;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunctionException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoreaudesign.weatheroutdoors.Cache;
import com.thoreaudesign.weatheroutdoors.Log;

public class AsyncRequest extends AsyncTask<RequestParams, Void, String>
{
    private String functionName;
    private RequestTemplate requestTemplate;
    private Cache cache;

    public AsyncRequest(RequestTemplate requestTemplate, String functionName, Cache cache)
    {
        this.requestTemplate = requestTemplate;
        this.functionName = functionName;
        this.cache = cache;
    }

    private RequestTemplate getRequestTemplate()
    {
        return this.requestTemplate;
    }

    @Override
    protected String doInBackground(RequestParams... params)
    {
        WeatherInterface weatherInterface =
            this.getRequestTemplate().getLambdaFactory().build(WeatherInterface.class);

        try
        {
            String functionName = AsyncRequest.this.functionName;

            Log.v("Submitting request for " + functionName + ".");

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Object response = this.getRequestTemplate().getLambdaResponse(weatherInterface, functionName, params);

            String data = gson.toJson(response);

            Log.v("Successfully obtained data from AWS Lambda '" + functionName + ".'");

            this.cache.setData(data);

            return data;
        }
        catch (LambdaFunctionException lfe)
        {
            Log.v("Failed to invoke AWS Lambda function '" + this.functionName + ".'");
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
    protected void onPostExecute(String data)
    {
        this.cache.setData(data);
        this.cache.write();
    }
}
