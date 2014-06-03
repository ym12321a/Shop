package com.stone.shop.adapter;

import java.util.Iterator;
import java.util.List;

import com.stone.shop.R;
import com.stone.shop.model.BXTNews;
import com.stone.shop.model.Good;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * 教学类-博学堂-讲座列表适配器
 * 
 * @date 2014-5-10
 * @author Stone
 */
public class BXTListAdapter extends BaseAdapter {

	private Context mContext;
	private List<BXTNews> mNewsList; // 商品列表信息
	private LayoutInflater mInflater = null;

	public BXTListAdapter(Context context, List<BXTNews> newsList) {
		mContext = context;
		mNewsList = newsList;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mNewsList.size();
	}

	@Override
	public Object getItem(int position) {
		return mNewsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// 刷新列表中的数据
	public void refresh(List<BXTNews> list) {
		Log.i("BXTNewsAdapter", "Adapter刷新数据");
		mNewsList = list;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BXTNewsHolder newsHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.bxt_list_item, null);
			newsHolder = new BXTNewsHolder();
			newsHolder.tvBXTNewsTitle = (TextView) convertView
					.findViewById(R.id.tv_bxt_news_item_title);
			convertView.setTag(newsHolder);
		} else {
			newsHolder = (BXTNewsHolder) convertView.getTag();
		}
		newsHolder.tvBXTNewsTitle.setText(mNewsList.get(position).getTitle());
		return convertView;
	}

}
