package com.trungkent.androidviewcollection;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.trungkent.androidviewcollection.adapter.ViewAdapter;
import com.trungkent.androidviewcollection.model.ViewItem;
import com.trungkent.androidviewcollection.parse.DataParser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView mLtvMain;
	private InputStream mDataStream;
	private List<ViewItem> mDataList;
	private ViewAdapter mAdapter;
	private Intent mIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try {
			mDataStream = getAssets().open("data.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mDataList = DataParser.getInstance().getDataList(mDataStream);
		mAdapter = new ViewAdapter(this, R.layout.itm_card_view, mDataList);
		mLtvMain = (ListView) findViewById(R.id.ltvMain);
		mLtvMain.setAdapter(mAdapter);
		mLtvMain.setOnItemClickListener(onItemClickListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	OnItemClickListener onItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			ViewItem item = mDataList.get(position);
			
			Bundle mBundle = new Bundle();
			mBundle.putSerializable("VIEW_OBJECT", item);
			
			mIntent = new Intent(getApplicationContext(),
					ViewDetailsActivity.class);
			mIntent.putExtras(mBundle);
			startActivity(mIntent);
		}
	};

}
