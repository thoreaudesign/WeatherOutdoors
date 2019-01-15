package com.thoreaudesign.weatheroutdoors.aws;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunction;

public interface WeatherInterface
{
    @LambdaFunction
    Object stormglass(RequestParams requestParams);

    @LambdaFunction
    Object stormglass_astro(RequestParams requestParams);

    @LambdaFunction
    Object metocean(RequestParams requestParams);

    @LambdaFunction
    Object darksky(RequestParams requestParams);
}
