/**
 * 
 */
package com.trungkent.androidviewcollection;

import com.trungkent.androidviewcollection.model.ViewItem;

import br.com.dina.ui.model.BasicItem;
import br.com.dina.ui.widget.UIButton;
import br.com.dina.ui.widget.UIButton.ClickListener;
import br.com.dina.ui.widget.UITableView;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

/**
 * @author Trung Doan
 * 
 */
public class ViewDetailsActivity extends Activity {

	private UITableView mUITableView;
	private ViewItem mViewItem;
	private Bundle mBundle;
	private UIButton mBtnViewDemo;
	private Intent mIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_details);
		mBundle = getIntent().getExtras();
		mViewItem = (ViewItem) mBundle.getSerializable("VIEW_OBJECT");

		mUITableView = (UITableView) findViewById(R.id.tableView);
		mUITableView.setClickListener(new TableClickListener());
		populateTable();

		mBtnViewDemo = (UIButton) findViewById(R.id.btnViewDemo);
		mBtnViewDemo.addClickListener(new ButtonViewDemoClickListener());
	}

	private void populateTable() {
		mUITableView.addBasicItem(new BasicItem("Introduce", mViewItem.description,
				false));
		mUITableView.addBasicItem(new BasicItem("API level", mViewItem.osLevel,
				false));
		mUITableView.addBasicItem(new BasicItem("Author", mViewItem.author,
				false));
		mUITableView.addBasicItem(new BasicItem("Link", mViewItem.link,
				true));
		mUITableView.addBasicItem(new BasicItem("License", mViewItem.license,
				false));
		mUITableView.commit();
	}

	private class ButtonViewDemoClickListener implements ClickListener {

		@Override
		public void onClick(View view) {
			if (mViewItem.className != null
					&& !"".equalsIgnoreCase(mViewItem.className)) {
				try {
					mIntent = new Intent(getApplicationContext(),
							Class.forName(mViewItem.className));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				startActivity(mIntent);
			}
		}
	}
	
	class TableClickListener implements br.com.dina.ui.widget.UITableView.ClickListener {

		@Override
		public void onClick(int index) {
			switch (index) {
			case 3:
				mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mViewItem.link));
				startActivity(mIntent);
				break;

			default:
				break;
			}
		}
		
	}
}
