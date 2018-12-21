package com.thoreaudesign.weatheroutdoors;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunction;

import org.json.JSONObject;

public interface WeatherInterface {

    @LambdaFunction
    JSONObject MarineStormglass(MarineRequest marineRequest);
}
