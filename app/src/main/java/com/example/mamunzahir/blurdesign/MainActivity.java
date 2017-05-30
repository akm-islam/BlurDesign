package com.example.mamunzahir.blurdesign;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.BlurMaskFilter;
//https://android--examples.blogspot.com/2015/11/how-to-use-blurmaskfilter-in-android.html

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private Resources mResources;
    private RelativeLayout mRelativeLayout;
    private TextView mTextView;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the application context
        mContext = getApplicationContext();

        // Get the Resources
        mResources = getResources();

        // Get the widgets reference from XML layout
        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl);
        mTextView = (TextView) findViewById(R.id.tv);
        mRadioGroup = (RadioGroup) findViewById(R.id.rg);

        // Set a checked change listener for RadioGroup
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_none) {
                    // If no blur is checked
                    // Set the TextView layer type
                    mTextView.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
                    // Clear any previous MaskFilter
                    mTextView.getPaint().setMaskFilter(null);
                }
                if(i == R.id.rb_inner){
                    // If inner blur checked
                    applyBlurMaskFilter(mTextView, BlurMaskFilter.Blur.INNER);
                }
                if(i == R.id.rb_normal){
                    // If normal blur checked
                    applyBlurMaskFilter(mTextView, BlurMaskFilter.Blur.NORMAL);
                }
                if(i == R.id.rb_outer){
                    // If outer blur checked
                    applyBlurMaskFilter(mTextView, BlurMaskFilter.Blur.OUTER);
                }
                if(i == R.id.rb_solid){
                    // If solid blur checked
                    applyBlurMaskFilter(mTextView, BlurMaskFilter.Blur.SOLID);
                }
            }
        });
    }

    // Custom method to apply BlurMaskFilter to a TextView text
    protected void applyBlurMaskFilter(TextView tv, BlurMaskFilter.Blur style){
        /*
            MaskFilter
                Known Direct Subclasses
                    BlurMaskFilter, EmbossMaskFilter

                MaskFilter is the base class for object that perform transformations on an
                alpha-channel mask before drawing it. A subclass of MaskFilter may be installed
                into a Paint. Blur and emboss are implemented as subclasses of MaskFilter.

        */
        /*
            BlurMaskFilter
                This takes a mask, and blurs its edge by the specified radius. Whether or or not to
                include the original mask, and whether the blur goes outside, inside, or straddles,
                the original mask's border, is controlled by the Blur enum.
        */
        /*
            public BlurMaskFilter (float radius, BlurMaskFilter.Blur style)
                Create a blur maskfilter.

            Parameters
                radius : The radius to extend the blur from the original mask. Must be > 0.
                style : The Blur to use
            Returns
                The new blur maskfilter
        */
        /*
            BlurMaskFilter.Blur
                INNER : Blur inside the border, draw nothing outside.
                NORMAL : Blur inside and outside the original border.
                OUTER : Draw nothing inside the border, blur outside.
                SOLID : Draw solid inside the border, blur outside.
        */
        /*
            public float getTextSize ()
                Returns the size (in pixels) of the default text size in this TextView.
        */

        // Define the blur effect radius
        float radius = tv.getTextSize()/10;

        // Initialize a new BlurMaskFilter instance
        BlurMaskFilter filter = new BlurMaskFilter(radius,style);

        /*
            public void setLayerType (int layerType, Paint paint)
                Specifies the type of layer backing this view. The layer can be LAYER_TYPE_NONE,
                LAYER_TYPE_SOFTWARE or LAYER_TYPE_HARDWARE.

                A layer is associated with an optional Paint instance that controls how the
                layer is composed on screen.

            Parameters
                layerType : The type of layer to use with this view, must be one of
                    LAYER_TYPE_NONE, LAYER_TYPE_SOFTWARE or LAYER_TYPE_HARDWARE
                paint : The paint used to compose the layer. This argument is optional and
                    can be null. It is ignored when the layer type is LAYER_TYPE_NONE
        */
        /*
            public static final int LAYER_TYPE_SOFTWARE
                Indicates that the view has a software layer. A software layer is backed by
                a bitmap and causes the view to be rendered using Android's software rendering
                pipeline, even if hardware acceleration is enabled.
        */

        // Set the TextView layer type
        tv.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        /*
            public MaskFilter setMaskFilter (MaskFilter maskfilter)
                Set or clear the maskfilter object.

                Pass null to clear any previous maskfilter. As a convenience, the parameter
                passed is also returned.

            Parameters
                maskfilter : May be null. The maskfilter to be installed in the paint
            Returns
                maskfilter
        */

        // Finally, apply the blur effect on TextView text
        tv.getPaint().setMaskFilter(filter);
    }
}