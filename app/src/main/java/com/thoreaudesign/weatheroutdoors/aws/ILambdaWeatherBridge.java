package com.thoreaudesign.weatheroutdoors.aws;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunction;

public interface ILambdaWeatherBridge
{
    @LambdaFunction
    Object weatheroutdoors(RequestParams paramRequestParams);
}
