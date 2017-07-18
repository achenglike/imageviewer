package cn.com.open.mooc.component.imageviewe.ImageLoader;

import android.support.annotation.DrawableRes;

import com.github.chrisbanes.photoview.PhotoView;

/**
 * Created by like on 2017/7/18.
 */

public interface ImageLoader {

    void display(PhotoView photoView, String location);

    void display(PhotoView photoView, @DrawableRes int resId);
}
