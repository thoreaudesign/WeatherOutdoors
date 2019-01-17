package com.thoreaudesign.weatheroutdoors.aws;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;
import com.thoreaudesign.weatheroutdoors.*;

public class RequestTemplate
{
    private Weather currentActivity;
    private LambdaInvokerFactory factory;
    private String lambdaFunctionName;
    private RequestParams params;

    public RequestTemplate(Weather currentActivity, LambdaInvokerFactory factory, String lambdaFunctionName, RequestParams params)
    {
        this.params = params;
        this.factory = factory;
        this.currentActivity = currentActivity;
        this.lambdaFunctionName = lambdaFunctionName;
    }

    public LambdaInvokerFactory getLambdaFactory()
    {
        return this.factory;
    }

    public Weather getCurrentActivity()
    {
        return currentActivity;
    }

    public Object getLambdaResponse(WeatherInterface weatherInterface, String lambdaFunctionName, RequestParams... params) throws Exception
    {
        Log.v("Returning lambda response for " + lambdaFunctionName + ".\n");

        switch(lambdaFunctionName)
        {
            case LambdaFunctions.darksky:
                return weatherInterface.darksky(params[0]);

            case LambdaFunctions.metocean:
                return weatherInterface.metocean(params[0]);

            case LambdaFunctions.stormglass:
                return weatherInterface.stormglass(params[0]);

            case LambdaFunctions.stormglass_astro:
                return weatherInterface.stormglass_astro(params[0]);

            default:
                throw new Exception("Tried to call unsupported lambda function (AWS).");
        }
    }
}
