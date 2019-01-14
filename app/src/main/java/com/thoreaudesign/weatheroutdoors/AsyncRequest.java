package com.thoreaudesign.weatheroutdoors;

import android.os.AsyncTask;
import android.util.Log;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunctionException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AsyncRequest extends AsyncTask<RequestParams, Void, String>
{
    private String functionName;
    private RequestTemplate requestTemplate;

    public AsyncRequest(RequestTemplate requestTemplate, String functionName)
    {
        this.requestTemplate = requestTemplate;
        this.functionName = functionName;
    }

    private RequestTemplate getRequestTemplate()
    {
        return this.requestTemplate;
    }

    @Override
    protected String doInBackground(RequestParams... params)
    {
        WeatherInterface weatherInterface =
            AsyncRequest.this.getRequestTemplate().getLambdaFactory().build(WeatherInterface.class);

        try
        {
            String functionName = AsyncRequest.this.functionName;

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(AsyncRequest.this.getRequestTemplate().getLambdaResponse(weatherInterface, functionName, params));
        }
        catch (LambdaFunctionException lfe)
        {
            String error = "Failed to invoke MetOcean API...";
            Log.e("Error", error, lfe);
            return error;
        }
        catch (Exception e)
        {
            Log.e("Error", e.getMessage(), e);
            return e.getMessage();
        }
    }

    @Override
    protected void onPreExecute()
    {
        //Toast.makeText(this.getRequestTemplate().getCurrentActivity().getBaseContext(), "Loading weather data...", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPostExecute(String result)
    {
        CacheController cache = new CacheController(this.getRequestTemplate().getCurrentActivity());
        Log.i("AysncRequest",
        "Attempting to write weather data for service '" + this.functionName + "' to cache...");
        cache.write(this.functionName, result);
    }
}
