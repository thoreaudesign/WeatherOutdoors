package com.thoreaudesign.weatheroutdoors.aws;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;
import com.thoreaudesign.weatheroutdoors.Log;

public class LambdaManager
{

    public interface LambdaListener
    {
        public void onDataLoaded(String data, String functionName);
    }

    private LambdaListener listener;

    public LambdaManager()
    {
        this.listener = null;
    }

    public void setLambdaListener(LambdaListener listener)
    {
        this.listener = listener;
    }

    public void onCompletedRequest(LambdaInvokerFactory factory, RequestParams params, String functionName)
    {
        RequestTemplate requestTemplate = new RequestTemplate(factory);
        Log.d("Successfully created RequestTemplate object for AWS Lambda function '" + functionName + ".'");

        Log.d("Successfully submitted AsyncRequest for AWS Lambda function '" + functionName + ".'");

        listener.onDataLoaded("test", functionName);
    }
}
