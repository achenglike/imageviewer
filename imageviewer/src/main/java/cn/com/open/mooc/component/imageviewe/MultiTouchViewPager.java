package cn.com.open.mooc.component.imageviewe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/*
 * Created by Alexander Krol (troy379) on 29.08.16.
 */
class MultiTouchViewPager extends ViewPager {

    private boolean isDisallowIntercept, isScrolled = true;

    public MultiTouchViewPager(Context context) {
        super(context);
        setScrollStateListener();
    }

    public MultiTouchViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setScrollStateListener();
    }

    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        isDisallowIntercept = disallowIntercept;
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent ev) {
        if (ev.getPointerCount() > 1 && isDisallowIntercept) {
            requestDisallowInterceptTouchEvent(false);
            boolean handled = super.dispatchTouchEvent(ev);
            requestDisallowInterceptTouchEvent(true);
            return handled;
        } else {
            return super.dispatchTouchEvent(ev);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getPointerCount() > 1) {
            return false;
        } else {
            try {
                return super.onInterceptTouchEvent(ev);
            } catch (IllegalArgumentException ex) {
                return false;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    public boolean isScrolled() {
        return isScrolled;
    }

    private void setScrollStateListener() {
        addOnPageChangeListener(new SimpleOnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int state) {
                isScrolled = state == SCROLL_STATE_IDLE;
            }
        });
    }
}