package com.example.dell.driverapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;



public class singleton {

    private RequestQueue r;
    private static singleton singleton_ins;
    private static Context mctx;


    public singleton(Context context){
        mctx=context;
        r=getRequestQueu();
    }


    public static synchronized singleton getSingleton_ins(Context context){
        if(singleton_ins==null)
            singleton_ins=new singleton(context);
        return  singleton_ins;

    }

    public RequestQueue getRequestQueu(){
        if(r==null)
            r= Volley.newRequestQueue(mctx.getApplicationContext());
        return r;
    }


    public <T>void addtorequestqueue(Request<T> request){
        r.add(request);
    }
}
