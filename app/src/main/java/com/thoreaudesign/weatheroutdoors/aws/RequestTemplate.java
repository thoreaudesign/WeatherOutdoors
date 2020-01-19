package com.thoreaudesign.weatheroutdoors.aws;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;

public class RequestTemplate
{
    private LambdaInvokerFactory factory;

    public RequestTemplate(LambdaInvokerFactory factory)
    {
        this.factory = factory;
    }

    public LambdaInvokerFactory getLambdaFactory()
    {
        return this.factory;
    }

    public Object getLambdaResponse(WeatherInterface weatherInterface, RequestParams... params) throws Exception
    {
        return weatherInterface.weatheroutdoors(params[0]);
    }
}
