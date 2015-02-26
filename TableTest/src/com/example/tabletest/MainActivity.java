package com.example.tabletest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tabletest.HorizontalScrollView.OnScrollListener;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TableAdapter adapter = new TableAdapter(this);
		
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.addHeaderView(adapter.getHeaderView(listView));
		
		listView.setAdapter(adapter);
	}
	
	public static class TableAdapter extends BaseAdapter {
		
		private static final String TAG = "TableAdapter";
		
		private Context mContext;
		
		private String[] mHeader;
		
		private List<String[]> mData;
		
		private int mCurrentScroll;
		
		private int[] mColResources = {
			R.id.textView1,
			R.id.textView2,	
			R.id.textView3,	
			R.id.textView4,	
			R.id.textView5,	
			R.id.textView6,	
			R.id.textView7,	
			R.id.textView8,	
			R.id.textView9,	
			R.id.textView10,	
			R.id.textView11,	
			R.id.textView12,	
			R.id.textView13,	
			R.id.textView14,	
			R.id.textView15,	
			R.id.textView16,	
			R.id.textView17,	
			R.id.textView18	
		};

		public TableAdapter(Context context) {
			super();
			mContext = context;
			
			mData = new ArrayList<String[]>();
			
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.fl_insurance_sample)));
				
				String line = reader.readLine();
				mHeader = line.split(",");
				
				while ((line = reader.readLine()) != null) {
					mData.add(line.split(","));
				}
				
			} catch (NotFoundException e) {
				Log.d(TAG, "error reading data", e);
			} catch (IOException e) {
				Log.d(TAG, "error reading data", e);
			}
			
		}

		@Override
		public int getCount() {
			return mData.size();
		}

		@Override
		public Object getItem(int position) {
			return mData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			HorizontalScrollView view = null;

			if (convertView == null){
				LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
				view = (HorizontalScrollView) inflater.inflate(R.layout.list_item_table_row, parent, false);
				
				view.setOnScrollListener(new OnScrollListener() {
					
					@Override
					public void onScrollChanged(View scrollView, int scrollX) {

						mCurrentScroll = scrollX;
						ListView listView = (ListView) scrollView.getParent();
						if (listView == null) return;
						
						for (int i = 0; i < listView.getChildCount(); i++) {
							View child = listView.getChildAt(i);
							if (child instanceof HorizontalScrollView && child != scrollView) {
								HorizontalScrollView scrollView2 = (HorizontalScrollView) child;
								if (scrollView2.getScrollX() != mCurrentScroll) {
									scrollView2.setScrollX(mCurrentScroll);
								}
							}
						}
					}
				});
				
			} else {
				view = (HorizontalScrollView) convertView;
			}
			
			view.setScrollX(mCurrentScroll);
			
			String[] data = mData.get(position);
			
			for (int i = 0; i < mColResources.length; i++) {
				TextView col = (TextView) view.findViewById(mColResources[i]);
				col.setText(data[i]);
			}

			return view;
		}
		
		public View getHeaderView(ViewGroup parent) {
			
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
			HorizontalScrollView view = (HorizontalScrollView) inflater.inflate(R.layout.list_item_table_row, parent, false);

			for (int i = 0; i < mColResources.length; i++) {
				TextView col = (TextView) view.findViewById(mColResources[i]);
				col.setText(mHeader[i]);
			}

			view.setOnScrollListener(new OnScrollListener() {
				
				@Override
				public void onScrollChanged(View scrollView, int scrollX) {

					mCurrentScroll = scrollX;
					ListView listView = (ListView) scrollView.getParent();
					if (listView == null) return;
					
					for (int i = 0; i < listView.getChildCount(); i++) {
						View child = listView.getChildAt(i);
						if (child instanceof HorizontalScrollView && child != scrollView) {
							HorizontalScrollView scrollView2 = (HorizontalScrollView) child;
							if (scrollView2.getScrollX() != mCurrentScroll) {
								scrollView2.setScrollX(mCurrentScroll);
							}
						}
					}
				}
			});

			return view;
		}
		
	}
	
}
