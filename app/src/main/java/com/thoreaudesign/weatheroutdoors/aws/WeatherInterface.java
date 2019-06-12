package com.thoreaudesign.weatheroutdoors.aws;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunction;

public interface WeatherInterface
{
    @LambdaFunction
    Object weatheroutdoors(RequestParams requestParams);
}
