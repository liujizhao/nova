package com.exper.nova.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;
public class VerticalPageSeekBar extends SeekBar {
	private int mPageCount = 0;
	private OnSeekBarPageChangeListener mBarPageChangeListener;
	
    public VerticalPageSeekBar(Context context) {
        super(context);
    }

    public VerticalPageSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    
	public VerticalPageSeekBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		super.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (mBarPageChangeListener != null) 
					mBarPageChangeListener.setSeekBarPageChanged( getProgressIsPageIndex(progress, getMax()));
			}
		});
		
		setMax(1000);
	}
	
	public interface OnSeekBarPageChangeListener {
		public abstract void setSeekBarPageChanged(int page);
	}
	
	public void setSeekBarPageChangeListener(OnSeekBarPageChangeListener listener) {
		mBarPageChangeListener = listener;
	}
	
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    protected void onDraw(Canvas c) {
        c.rotate(-90);
        c.translate(-getHeight(),0);

        super.onDraw(c);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
            	int i=0;
            	i=getMax() - (int) (getMax() * event.getY() / getHeight());
                setProgress(i);
                onSizeChanged(getWidth(), getHeight(), 0, 0);
                break;

            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }
	
	public void setPagesCount(int count) {
		mPageCount = count;
	}
	
	private int getProgressIsPageIndex(int progress, int max) {
		if (mPageCount < 2) {
			if (progress == 0) return 0;
			else return 1;
		}
		if (progress == 0) return 0;
		int progressSizeEveryPage = getMax()/(mPageCount-1);
		for (int i = 1; i < mPageCount; i++) {
			if (progress <= (i)*progressSizeEveryPage && progress > ((i-1)*progressSizeEveryPage)) {
				if (progress > (2*i-1)*progressSizeEveryPage/2 ) return i;
				else return i-1;
			}
		}
		return mPageCount-1;
	}

	public void setProgressSpecialPage(int pageIndex, int count) {
		if (count <= 1) {
			setProgress(0);
			return;
		}
		
		mPageCount = count;
		if (pageIndex < 0 || pageIndex >= mPageCount) return;

		int progressSizeEveryPage = getMax()/(mPageCount-1);
		
		setProgress( progressSizeEveryPage*pageIndex);
        onSizeChanged(getWidth(), getHeight(), 0, 0);
	}
}
