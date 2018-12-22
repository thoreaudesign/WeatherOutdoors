package com.thoreaudesign.weatheroutdoors;

import android.view.View;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;

public class RequestTemplate implements View.OnClickListener
{
    private Weather currentActivity;
    private LambdaInvokerFactory factory;
    String lambdaFunctionName;

    public RequestTemplate(Weather currentActivity, LambdaInvokerFactory factory, String lambdaFunctionName)
    {
        this.currentActivity = currentActivity;
        this.factory = factory;
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

    public Object getLambdaResponse(WeatherInterface weatherInterface,  String lambdaFunctionName, RequestParams... params) throws Exception
    {
        switch(lambdaFunctionName)
        {
            case LambdaFunctions.DARKSKY:
                return weatherInterface.darksky(params[0]);

            case LambdaFunctions.METOCEAN:
                return weatherInterface.metocean(params[0]);

            case LambdaFunctions.STORMGLASS:
                return weatherInterface.stormglass(params[0]);

            default:
                throw new Exception("Tried to call unsupported lambda function (AWS).");
        }
    }

    @Override
    public void onClick(View view)
    {
        RequestParams data = new RequestParams("-32", "14");

        AsyncRequest request = new AsyncRequest(RequestTemplate.this, RequestTemplate.this.lambdaFunctionName);

        request.execute(data);
    }
}
