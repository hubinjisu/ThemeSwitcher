package com.android.hubin.themeswitcher.colorUi.util;


import android.bluetooth.BluetoothGattCharacteristic;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.hubin.themeswitcher.colorUi.ColorUiInterface;


/**
 * Created by chengli on 15/6/8.
 */
public class ViewAttributeUtil
{

    public static int getAttributeValue(AttributeSet attr, int paramInt)
    {
        int value = -1;
        int count = attr.getAttributeCount();
        for (int i = 0; i < count; i++)
        {
            if (attr.getAttributeNameResource(i) == paramInt)
            {
                String str = attr.getAttributeValue(i);
                if (null != str && str.startsWith("?"))
                {
                    value = Integer.valueOf(str.substring(1, str.length())).intValue();
                    return value;
                }
            }
        }
        return value;
    }

    public static int getBackgroundAttibute(AttributeSet attr)
    {
        return getAttributeValue(attr, android.R.attr.background);
    }

    public static int getCheckMarkAttribute(AttributeSet attr)
    {
        return getAttributeValue(attr, android.R.attr.checkMark);
    }

    public static int getSrcAttribute(AttributeSet attr)
    {
        return getAttributeValue(attr, android.R.attr.src);
    }

    public static int getDrawableLeftAttribute(AttributeSet attr)
    {
        return getAttributeValue(attr, android.R.attr.drawableLeft);
    }

    public static int getDrawableRightAttribute(AttributeSet attr)
    {
        return getAttributeValue(attr, android.R.attr.drawableRight);
    }

    public static int getDrawableTopAttribute(AttributeSet attr)
    {
        return getAttributeValue(attr, android.R.attr.drawableTop);
    }

    public static int getDrawableBottomAttribute(AttributeSet attr)
    {
        return getAttributeValue(attr, android.R.attr.drawableBottom);
    }

    public static int getTextApperanceAttribute(AttributeSet attr)
    {
        return getAttributeValue(attr, android.R.attr.textAppearance);
    }

    public static int getDividerAttribute(AttributeSet attr)
    {
        return getAttributeValue(attr, android.R.attr.divider);
    }

    public static int getTextColorAttribute(AttributeSet attr)
    {
        return getAttributeValue(attr, android.R.attr.textColor);
    }

    public static void applyBackgroundDrawable(ColorUiInterface ci, Resources.Theme theme, int paramInt)
    {
        TypedArray ta = theme.obtainStyledAttributes(new int[]{paramInt});
        Drawable drawable = ta.getDrawable(0);
        if (null != ci)
        {
            (ci.getView()).setBackgroundDrawable(drawable);
        }
        ta.recycle();
    }

    public static void applyImageDrawable(ColorUiInterface ci, Resources.Theme theme, int paramInt)
    {
        TypedArray ta = theme.obtainStyledAttributes(new int[]{paramInt});
        Drawable drawable = ta.getDrawable(0);
        if (null != ci && ci instanceof ImageView)
        {
            ((ImageView) ci.getView()).setImageDrawable(drawable);
        }
        ta.recycle();
    }

    public static void applyBoundDrawable(ColorUiInterface ci, Resources.Theme theme, int leftParamInt, int rightParamInt, int topParamInt, int bottomParamInt)
    {
        TypedArray ta = theme.obtainStyledAttributes(new int[]{leftParamInt});
        if (null != ci && ci instanceof TextView)
        {
            ((TextView) ci.getView()).setCompoundDrawables(getDrawable(ta, theme, leftParamInt), getDrawable(ta, theme, topParamInt), getDrawable(ta, theme, rightParamInt), getDrawable(ta, theme, bottomParamInt));
        }
        ta.recycle();
    }

    private static Drawable getDrawable(TypedArray ta, Resources.Theme theme, int paramInt)
    {
        if (paramInt > 0)
        {
            Drawable drawable = ta.getDrawable(0);
            if (drawable !=null)
            {

                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            }
            return drawable;
        }
        return null;
    }

    public static void applyTextAppearance(ColorUiInterface ci, Resources.Theme theme, int paramInt)
    {
        TypedArray ta = theme.obtainStyledAttributes(new int[]{paramInt});
        int resourceId = ta.getResourceId(0, 0);
        if (null != ci && ci instanceof TextView)
        {
            ((TextView) ci.getView()).setTextAppearance(ci.getView().getContext(), resourceId);
        }
        ta.recycle();
    }

    public static void applyTextColor(ColorUiInterface ci, Resources.Theme theme, int paramInt)
    {
        TypedArray ta = theme.obtainStyledAttributes(new int[]{paramInt});
        int resourceId = ta.getColor(0, 0);
        if (null != ci && ci instanceof TextView)
        {
            ((TextView) ci.getView()).setTextColor(resourceId);
        }
        ta.recycle();
    }

}
