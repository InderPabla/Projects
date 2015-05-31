package com.example.pabla.andoirddatabaseaccess2;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.widget.Button;

/**
 * Created by PABLA on 27/05/2015.
 */
public class ButtonAnimate extends Activity
{
    View view;
    Button button;
    Thread thread;

    boolean running = false;

    Drawable old;
    ColorDrawable color;

    float interval;
    public ButtonAnimate(View view, int colorToChange, float interval)
    {
        this.view = view;
        old = ((Button)this.view).getBackground();

        color = new ColorDrawable(colorToChange);
        button = (Button)view;
        this.interval = interval;
    }

    public void animate()
    {
        if(running==false)
        {
            running = true;
            thread = new Thread()
            {
                @Override
                public void run()
                {
                    boolean start = true;
                    boolean latch = false;
                    float time = 0;
                    long startTime = System.nanoTime();

                    while(start)
                    {
                        if(time == 0f){
                            runOnUiThread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    ColorDrawable[] colorSet = {(ColorDrawable) old, color};
                                    TransitionDrawable trans = new TransitionDrawable(colorSet);
                                    button.setBackgroundDrawable(trans);
                                    trans.startTransition((int)interval);

                                }
                            });;

                        }
                        if(time >=(interval/1000f) && !latch)
                        {
                            latch = true;
                            runOnUiThread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    ColorDrawable[] colorSet = {color, (ColorDrawable) old};
                                    TransitionDrawable trans = new TransitionDrawable(colorSet);
                                    button.setBackgroundDrawable(trans);
                                    trans.startTransition((int)interval);

                                }
                            });
                        }
                        if(time >=(interval/1000f))
                        {
                            running = false;
                            start = false;
                            thread.interrupt();
                        }
                        time = System.nanoTime()/1000000000.0f-startTime/1000000000.0f;
                    }
                }
            };

            thread.start();
        }
    }

}
