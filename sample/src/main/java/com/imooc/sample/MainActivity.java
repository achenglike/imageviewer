package com.imooc.sample;

import android.os.Environment;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;

import cn.com.open.mooc.component.imageviewe.ImageLoader.ImageLoader;
import cn.com.open.mooc.component.imageviewe.ImageViewer;
import cn.com.open.mooc.component.imageviewe.overlay.DefaultOverlayView;

public class MainActivity extends AppCompatActivity {

    static List<String> urls;
    static {
        urls = new ArrayList<>();
        urls.add("http://wx1.sinaimg.cn/wap720/90eb2137ly1fhntweb5vxj20ku0bqtbs.jpg");
        urls.add("http://wx1.sinaimg.cn/wap720/90eb2137ly1fhn0qxkstmj20ku0bqn4i.jpg");
        urls.add("http://wx3.sinaimg.cn/wap720/90eb2137ly1fhn1ve5htrj20hq0a8dzz.jpg");
        urls.add("http://wx3.sinaimg.cn/wap720/90eb2137ly1fhnuvf1kryj21c00u041s.jpg");
    }

    static List<Integer> drawbles;

    static {
        drawbles = new ArrayList<>();
        drawbles.add(R.mipmap.ic_launcher);
        drawbles.add(R.mipmap.ic_launcher);
        drawbles.add(R.mipmap.ic_launcher);
        drawbles.add(R.mipmap.ic_launcher);
    }

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(new ImageAdapter(urls));
        recyclerView.addOnItemTouchListener(new RVItemClickListener(recyclerView) {
            @Override
            void onItemClick(int position) {
                final DefaultOverlayView overlayView = DefaultOverlayView.createOverlayView(MainActivity.this, position, urls.size(), new DefaultOverlayView.SaveImageListener() {
                    @Override
                    public void save(int position) {
                        Utils.savePicture(getApplicationContext(), getApplication().getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath(), urls.get(position));
                    }
                });

                new ImageViewer.Builder(MainActivity.this, urls)
                        .setImageLoader(new ImageLoader() {
                            @Override
                            public void display(PhotoView photoView, String location) {
                                Glide.with(getApplicationContext()).load(location).into(photoView);
                            }

                            @Override
                            public void display(PhotoView photoView, @DrawableRes int resId) {
                                Glide.with(getApplicationContext()).load(resId).into(photoView);
                            }
                        })
                        .setStartPosition(position)
                        .setOverlayView(overlayView)
                        .setImageChangeListener(new ImageViewer.OnImageChangeListener() {
                            @Override
                            public void onImageChange(int position) {
                                overlayView.setPages(position, urls.size());
                            }
                        })
                        .show();
            }
        });
    }

    static class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.Holder> {

        List<String> url;

        public ImageAdapter(List<String> url) {
            if (url == null)
                url = new ArrayList<>();
            this.url = url;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            ImageView imageView = (ImageView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            return new Holder(imageView);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            Glide.with(holder.itemView.getContext()).load(getItem(position)).into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return url.size();
        }

        private String getItem(int position) {
            return url.get(position);
        }

        class Holder extends RecyclerView.ViewHolder {
            ImageView imageView;
            public Holder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView;
            }
        }
    }
}
