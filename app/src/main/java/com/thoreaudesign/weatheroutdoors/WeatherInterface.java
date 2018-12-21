package com.thoreaudesign.weatheroutdoors;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunction;

import org.json.JSONObject;

public interface WeatherInterface {

    @LambdaFunction
    Object Stormglass(MarineRequest marineRequest);

    @LambdaFunction
    Object MetOcean(MarineRequest marineRequest);
}
