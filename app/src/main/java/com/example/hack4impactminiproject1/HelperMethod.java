package com.example.hack4impactminiproject1;

public class HelperMethod {

    public static boolean isContain(String[] a, String s)
    {
        for (int i=0; i<a.length; i++)
        {
            if (a[i].equals(s))
            {
                return true;
            }
        }
        return false;
    }
}
