package com.mindtech.utilityclass.utility;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ListView;

public class ExpandableHeightListView extends ListView
{

    boolean expanded = false;

    public ExpandableHeightListView(Context context, AttributeSet attrs,
                                    int defStyle)
    {
        super(context, attrs, defStyle);
    }

    public ExpandableHeightListView(Context context)
    {
        super(context);
    }

    public ExpandableHeightListView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }



    public boolean isExpanded()
    {
        return expanded;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {

//        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
//                MeasureSpec.AT_MOST);

//
//        super.onMeasure(widthMeasureSpec, expandSpec);


//        int heightMeasureSpec_custom = MeasureSpec.makeMeasureSpec(
//                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec_custom);
//        ViewGroup.LayoutParams params = getLayoutParams();
//        params.height = getMeasuredHeight();

        
        if (isExpanded())
        {

            int expandSpec = MeasureSpec.makeMeasureSpec(MEASURED_SIZE_MASK,
                    MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);

            ViewGroup.LayoutParams params = getLayoutParams();
            params.height = getMeasuredHeight();
            //setLayoutParams(params);
        }
        else
        {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }



//        int heightSpec;
//
//        if (getLayoutParams().height == LayoutParams.WRAP_CONTENT) {
//            // The great Android "hackatlon", the love, the magic.
//            // The two leftmost bits in the height measure spec have
//            // a special meaning, hence we can't use them to describe height.
//            heightSpec = MeasureSpec.makeMeasureSpec(
//                    Integer.MAX_VALUE >>1, MeasureSpec.AT_MOST);
//        }
//        else {
//            // Any other height should be respected as is.
//            heightSpec = heightMeasureSpec;
//        }
//
//        super.onMeasure(widthMeasureSpec, heightSpec);



    }

    public void setExpanded(boolean expanded)
    {
        this.expanded = expanded;
    }
}
