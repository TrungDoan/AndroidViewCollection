/**
 * 
 */
package com.trungkent.androidviewcollection.adapter;

import java.util.List;

import com.trungkent.androidviewcollection.R;
import com.trungkent.androidviewcollection.model.ViewItem;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Trung Doan
 * 
 */
public class ViewAdapter extends ArrayAdapter<ViewItem> {

	private List<ViewItem> mDataList;
	private Context mContext;
	private LayoutInflater mInflater;
	private ViewItem mItem;
	private Resources mResources;
	private String mPackageName;

	public ViewAdapter(Context context, int textViewResourceId,
			List<ViewItem> objects) {
		super(context, textViewResourceId, objects);
		mDataList = objects;
		mContext = context;
		mInflater = ((Activity) mContext).getLayoutInflater();
		mResources = mContext.getResources();
		mPackageName = mContext.getPackageName();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDataList == null ? 0 : mDataList.size();
	}

	@Override
	public ViewItem getItem(int position) {
		// TODO Auto-generated method stub
		return mDataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.itm_card_view, parent, false);
			holder.mImgPreview = (ImageView) convertView
					.findViewById(R.id.imgPreview);
			holder.mViewName = (TextView) convertView
					.findViewById(R.id.txvViewName);
			holder.mAuthor = (TextView) convertView
					.findViewById(R.id.txvAuthor);
			holder.mDescription = (TextView) convertView
					.findViewById(R.id.txvDescription);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		mItem = mDataList.get(position);

		holder.mImgPreview.setImageResource(mResources.getIdentifier(
				mItem.imgPreview1, "drawable", mPackageName));
		holder.mViewName.setText(mItem.viewName);
		holder.mAuthor.setText(mItem.author);
		holder.mDescription.setText(mItem.description);
		
		return convertView;
	}

	static class ViewHolder {
		ImageView mImgPreview;
		TextView mViewName;
		TextView mAuthor;
		TextView mDescription;
	}
}
