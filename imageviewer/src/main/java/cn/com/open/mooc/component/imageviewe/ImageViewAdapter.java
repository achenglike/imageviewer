package cn.com.open.mooc.component.imageviewe;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.OnScaleChangedListener;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.HashSet;

import cn.com.open.mooc.component.imageviewe.ImageLoader.ImageLoader;
import cn.com.open.mooc.component.imageviewe.adapter.RecyclingPagerAdapter;
import cn.com.open.mooc.component.imageviewe.adapter.ViewHolder;

/**
 * Created by like on 2017/7/18.
 */

public class ImageViewAdapter extends RecyclingPagerAdapter<ImageViewAdapter.ImageViewHolder> {

    private Context context;
    private ImageViewer.DataSet<?> dataSet;
    private HashSet<ImageViewHolder> holders;
    private ImageLoader imageLoader;
    private boolean isZoomingAllowed;

    ImageViewAdapter(Context context, ImageViewer.DataSet<?> dataSet,
                     ImageLoader imageLoader, boolean isZoomingAllowed) {
        this.context = context;
        this.dataSet = dataSet;
        this.holders = new HashSet<>();
        this.imageLoader = imageLoader;
        this.isZoomingAllowed = isZoomingAllowed;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PhotoView photoView = new PhotoView(context);
        photoView.setEnabled(isZoomingAllowed);

        ImageViewHolder holder = new ImageViewHolder(photoView);
        holders.add(holder);

        return holder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return dataSet.getData().size();
    }


    boolean isScaled(int index) {
        for (ImageViewHolder holder : holders) {
            if (holder.position == index) {
                return holder.isScaled();
            }
        }
        return false;
    }

    void resetScale(int index) {
        for (ImageViewHolder holder : holders) {
            if (holder.position == index) {
                holder.resetScale();
                break;
            }
        }
    }

    String getUrl(int index) {
        return dataSet.format(index);
    }


    class ImageViewHolder extends ViewHolder implements OnScaleChangedListener {

        private int position = -1;
        private PhotoView photoView;

        ImageViewHolder(View itemView) {
            super(itemView);
            photoView = (PhotoView) itemView;
        }

        void bind(int position) {
            this.position = position;
            if (imageLoader != null) {
                if (dataSet.drawableResource(position)) {
                    imageLoader.display(photoView, (Integer) dataSet.getOriginData(position));
                } else {
                    imageLoader.display(photoView, dataSet.format(position));
                }
            }
            photoView.setOnScaleChangeListener(this);
        }

        private boolean isScaled() {
            return photoView.getScale() > 1.0f;
        }

        @Override
        public void onScaleChange(float scaleFactor, float focusX, float focusY) {
        }

        void resetScale() {
            photoView.setScale(1.0f, true);
        }

    }
}
