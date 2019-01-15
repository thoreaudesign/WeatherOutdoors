package com.thoreaudesign.weatheroutdoors.aws;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;
import com.thoreaudesign.weatheroutdoors.Weather;

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
        switch(lambdaFunctionName)
        {
            case LambdaFunctions.DARKSKY:
                return weatherInterface.darksky(params[0]);

            case LambdaFunctions.METOCEAN:
                return weatherInterface.metocean(params[0]);

            case LambdaFunctions.STORMGLASS:
                return weatherInterface.stormglass(params[0]);

            case LambdaFunctions.STORMGLASS_ASTRO:
                return weatherInterface.stormglass_astro(params[0]);

            default:
                throw new Exception("Tried to call unsupported lambda function (AWS).");
        }
    }
}
