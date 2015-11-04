package com.example.digitaslbi;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

	class viewholder {
		TextView Species;
		TextView family;
		TextView IUCN;
		TextView year;
		TextView notes;
		ImageView image;
		RelativeLayout rLayout;
	}

	private List<Animal_Feature_class> allElementDetails;

	private LayoutInflater mInflater;

	public ListAdapter(Context context, List<Animal_Feature_class> allDetails) {
		super();
		allElementDetails = allDetails;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		int size = allElementDetails.size();
		return size;
	}

	@Override
	public Object getItem(int position) {
		return allElementDetails.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		viewholder holder = new viewholder();
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.holder_list, null, false);
			holder.Species = (TextView) convertView.findViewById(R.id.tv_Name1);
			holder.family = (TextView) convertView.findViewById(R.id.tv_Name2);
			holder.IUCN = (TextView) convertView.findViewById(R.id.tv_Name3);
			holder.year = (TextView) convertView.findViewById(R.id.tv_Name4);
			holder.image = (ImageView) convertView.findViewById(R.id.iv_img);
			holder.rLayout = (RelativeLayout) convertView
					.findViewById(R.id.relativeLayout);

			convertView.setTag(holder);

		} else {
			holder = (viewholder) convertView.getTag();

		}

		holder.Species.setText(allElementDetails.get(position).getSpecies());
		holder.family.setText(allElementDetails.get(position).getFamily());
		holder.IUCN.setText(allElementDetails.get(position).getIUCN());
		holder.year.setText(allElementDetails.get(position).getYear());
		
		holder.image.setImageBitmap(allElementDetails.get(position).getPicture());
		// holder.rLayout.setBackground(allElementDetails.get(position).getPhoto());

		// holder.image.setBackgroundResource(R.drawable.logo);

		return convertView;
	}

}
