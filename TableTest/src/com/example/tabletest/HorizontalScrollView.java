package com.example.tabletest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class HorizontalScrollView extends android.widget.HorizontalScrollView {
	
	private OnScrollListener mListener;

	public HorizontalScrollView(Context context) {
		super(context);
	}

	public HorizontalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public HorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public void setOnScrollListener(OnScrollListener listener) {
		mListener = listener;
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if (mListener != null) {
			mListener.onScrollChanged(this, l);
		}
	}

	public interface OnScrollListener {
		public void onScrollChanged(View view, int scrollX);
	}
}
