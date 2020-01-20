package com.thoreaudesign.weatheroutdoors.aws;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;

public class RequestTemplate
{
    private LambdaInvokerFactory factory;

    public RequestTemplate(LambdaInvokerFactory paramLambdaInvokerFactory)
    {
        this.factory = paramLambdaInvokerFactory;
    }

    public LambdaInvokerFactory getLambdaFactory()
    {
        return this.factory;
    }

    public Object getLambdaResponse(WeatherInterface paramWeatherInterface, RequestParams... paramVarArgs) throws Exception
    {
        return paramWeatherInterface.weatheroutdoors(paramVarArgs[0]);
    }
}
