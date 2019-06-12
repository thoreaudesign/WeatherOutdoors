package com.thoreaudesign.weatheroutdoors;

public class Log
{
    public static void v(String message)
    {
        android.util.Log.v(getTag(), message);
    }

    public static void d(String message)
    {
        android.util.Log.d(getTag(), message);
    }

    public static void i(String message)
    {
        android.util.Log.i(getTag(), message);
    }

    public static void w(String message)
    {
        android.util.Log.w(getTag(), message);
    }

    public static void e(String message)
    {
        android.util.Log.e(getTag(), message);
    }

    public static String getTag()
    {
        String prefix = "[WeatherOutdoors] ";
        Throwable t = new Throwable();

        String className = t.getStackTrace()[2].getClassName();

        String[] packageNames = className.split("\\.");

        if (packageNames.length > 0)
        {
            return prefix + packageNames[packageNames.length - 1] + "::" + t.getStackTrace()[2].getMethodName() + "()";
        }
        else
        {
            return prefix + className + "::" + t.getStackTrace()[2].getMethodName() + "()";
        }
    }
}
