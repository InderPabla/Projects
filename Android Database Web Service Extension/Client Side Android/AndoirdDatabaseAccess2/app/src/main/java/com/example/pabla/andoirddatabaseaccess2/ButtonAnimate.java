package com.example.pabla.andoirddatabaseaccess2;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.widget.Button;

/**
 * ButtonAnimate handel's simple button animation.
 * @author INDERPREET PABLA
 */
public class ButtonAnimate extends Activity
{
    private View view;
    private Button button;
    private Thread thread;

    private boolean running = false;

    private Drawable old;
    private ColorDrawable color;

    private float interval;

    /**
     * Constructor to set view, color and interval time with given parameters.
     * @param view View of the button to animate.
     * @param colorToChange Simple color to change the color of the button to.
     * @param interval The time it takes to change the color from initial color of the button to the
     *                 given colorToChange
     */
    public ButtonAnimate(View view, int colorToChange, float interval)
    {
        this.view = view;
        old = ((Button)this.view).getBackground();

        color = new ColorDrawable(colorToChange);
        button = (Button)view;
        this.interval = interval;
    }

    /**
     * If thread is not running the button begins animation by creating a new thread. The thread
     * process follows the following stages.
     * 1. Thread Start.
     * 2. Change color to different color using runOnUiThread.
     * 3. Change color back to initial color using runOnUiThread.
     * 4. Stop Thread.
     * Both stages 2 and 3 take the same amount of time which is equal to the interval in milliseconds.
     */
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
