package com.network.request;

import android.os.AsyncTask;

/**
 * Created by HeroDoc on 2018/2/26.
 */

public abstract class Task<Params, Progress, Result> extends AsyncTask<Params, Progress, Result>{
    protected Exception e=null;
}