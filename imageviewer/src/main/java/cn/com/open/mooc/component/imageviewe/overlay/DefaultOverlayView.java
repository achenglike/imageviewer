package cn.com.open.mooc.component.imageviewe.overlay;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.util.Locale;

import cn.com.open.mooc.component.imageviewe.R;
import cn.com.open.mooc.component.imageviewe.progress.CircleProgressView;


/**
 * Created by like on 2017/7/18.
 */

public class DefaultOverlayView extends RelativeLayout {
    TextView tvPage;
    Button btSave;
    CircleProgressView circleProgressView;
    int currentPage;
    SaveImageListener saveImageListener;

    public DefaultOverlayView(Context context) {
        super(context);
        init();
    }

    public DefaultOverlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DefaultOverlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CircleProgressView getCircleProgressView() {
        return circleProgressView;
    }

    private void init() {
        View view = inflate(getContext(), R.layout.overlay_default_layout, this);
        tvPage = (TextView) view.findViewById(R.id.tv_page);
        btSave = (Button) view.findViewById(R.id.bt_save);
        circleProgressView = (CircleProgressView) view.findViewById(R.id.circle_progress_view);
        btSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saveImageListener != null)
                    saveImageListener.save(currentPage);
            }
        });
    }

    public void setPages(int current, int pageAll) {
        if (pageAll > 1) {
            tvPage.setVisibility(VISIBLE);
            currentPage = current;
            String str = String.format(Locale.US, "%d/%d", current+1, pageAll);
            tvPage.setText(str);
        }
    }

    private void setSaveImageListener(SaveImageListener saveImageListener) {
        if (saveImageListener != null)
            btSave.setVisibility(VISIBLE);
        this.saveImageListener = saveImageListener;
    }

    public interface SaveImageListener {
        void save(int position);
    }

    public static DefaultOverlayView createOverlayView(Context context, int current, int pageAll, SaveImageListener saveImageListener) {
        DefaultOverlayView view = new DefaultOverlayView(context);
        view.setPages(current, pageAll);
        view.setSaveImageListener(saveImageListener);
        return view;
    }
}
