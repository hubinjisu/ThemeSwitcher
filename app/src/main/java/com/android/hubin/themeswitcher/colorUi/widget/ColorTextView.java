package com.android.hubin.themeswitcher.colorUi.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.hubin.themeswitcher.colorUi.ColorUiInterface;
import com.android.hubin.themeswitcher.colorUi.util.ViewAttributeUtil;


/**
 * Created by chengli on 15/6/8.
 */
public class ColorTextView extends TextView implements ColorUiInterface {

    private int attr_drawable = -1;
    private int attr_textAppearance = -1;
    private int attr_textColor = -1;
    private int attr_drawable_left = -1;
    private int attr_drawable_right = -1;
    private int attr_drawable_top = -1;
    private int attr_drawable_bottom = -1;

    public ColorTextView(Context context) {
        super(context);
    }

    public ColorTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attr_textAppearance = ViewAttributeUtil.getTextApperanceAttribute(attrs);
        this.attr_drawable = ViewAttributeUtil.getBackgroundAttibute(attrs);
        this.attr_textColor = ViewAttributeUtil.getTextColorAttribute(attrs);
        this.attr_drawable_left = ViewAttributeUtil.getDrawableLeftAttribute(attrs);
        this.attr_drawable_right = ViewAttributeUtil.getDrawableRightAttribute(attrs);
        this.attr_drawable_top = ViewAttributeUtil.getDrawableTopAttribute(attrs);
        this.attr_drawable_bottom = ViewAttributeUtil.getDrawableBottomAttribute(attrs);
    }

    public ColorTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attr_textAppearance = ViewAttributeUtil.getTextApperanceAttribute(attrs);
        this.attr_drawable = ViewAttributeUtil.getBackgroundAttibute(attrs);
        this.attr_textColor = ViewAttributeUtil.getTextColorAttribute(attrs);
        this.attr_drawable_left = ViewAttributeUtil.getDrawableLeftAttribute(attrs);
        this.attr_drawable_right = ViewAttributeUtil.getDrawableRightAttribute(attrs);
        this.attr_drawable_top = ViewAttributeUtil.getDrawableTopAttribute(attrs);
        this.attr_drawable_bottom = ViewAttributeUtil.getDrawableBottomAttribute(attrs);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setTheme(Resources.Theme themeId) {
        Log.d("COLOR", "id = " + getId());
        if (attr_drawable != -1) {
            ViewAttributeUtil.applyBackgroundDrawable(this, themeId, attr_drawable);
        }
        if (attr_textAppearance != -1) {
            ViewAttributeUtil.applyTextAppearance(this, themeId, attr_textAppearance);
        }
        if (attr_textColor != -1) {
            ViewAttributeUtil.applyTextColor(this, themeId, attr_textColor);
        }
        if (attr_drawable_left != -1 || attr_drawable_right != -1 || attr_drawable_top != -1 || attr_drawable_bottom != -1) {
            ViewAttributeUtil.applyBoundDrawable(this, themeId, attr_drawable_left, attr_drawable_right, attr_drawable_top, attr_drawable_bottom);
        }
    }
}
