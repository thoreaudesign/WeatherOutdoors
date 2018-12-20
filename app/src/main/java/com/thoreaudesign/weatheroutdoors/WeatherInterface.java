package com.thoreaudesign.weatheroutdoors;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunction;

public interface WeatherInterface {
    @LambdaFunction
    String marine_stormglass(WeatherInput weatherInput);
}
