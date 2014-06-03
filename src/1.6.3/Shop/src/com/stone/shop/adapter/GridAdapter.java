package com.stone.shop.adapter;

import android.R.integer;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stone.date.TypeDef;
import com.stone.shop.R;

/**
 * 生活-- 网格布局(ImageView+TextView)适配器
 * 
 * @date 2014-4-24
 * @author Stone
 */
public class GridAdapter extends BaseAdapter {

	private Context mContext;
	private int mIndex = 0; // 代表当前需要适配页面中第几个GridView

	//学习小菜
	public static String[] mSchoolTexts = TypeDef.typeSonList1;
	private int[] mSchoolImages = { R.drawable.ic_8, R.drawable.ic_8,
			R.drawable.ic_8, R.drawable.ic_8, R.drawable.ic_8 };
	
	//吃饭小菜
	public static String[] mFoodTexts = TypeDef.typeSonList2;
	private int[] mFoodImages = { R.drawable.ic_4, R.drawable.ic_4,
			R.drawable.ic_4, R.drawable.ic_4, R.drawable.ic_4 };

	//购物小菜
	public static String[] mGiftTexts = TypeDef.typeSonList3;
	private int[] mGiftImages = { R.drawable.ic_7, R.drawable.ic_7,
			R.drawable.ic_7, R.drawable.ic_7, R.drawable.ic_7, 
			R.drawable.ic_7, R.drawable.ic_7};

	//疯狂小菜
	public static String[] mOutTexts = TypeDef.typeSonList4;
	private int[] mOutImages = { R.drawable.ic_3, R.drawable.ic_3,
			R.drawable.ic_3, R.drawable.ic_3, R.drawable.ic_3, 
			R.drawable.ic_3, R.drawable.ic_3  };

	public GridAdapter(Context context, int index) {
		mContext = context;
		mIndex = index;
	}

	@Override
	public int getCount() {
		int count = 0;
		switch (mIndex) {
		case 0:
			count = mFoodImages.length;
			break;
		case 1:
			count = mGiftImages.length;
			break;
		case 2:
			count = mOutImages.length;
			break;
		case 3:
			count = mSchoolImages.length;
			break;
		default:
			break;
		}
		return count;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = View.inflate(mContext, R.layout.shop_grid_item, null);
		// RelativeLayout rl = (RelativeLayout)
		// view.findViewById(R.id.relaGrid);

		ImageView image = (ImageView) view.findViewById(R.id.img_chooseImage);
		TextView text = (TextView) view.findViewById(R.id.tv_chooseText);
		switch (mIndex) {
		case 0:
			image.setImageResource(mFoodImages[position]);
			text.setText(mFoodTexts[position]);
			break;
		case 1:
			image.setImageResource(mGiftImages[position]);
			text.setText(mGiftTexts[position]);
			break;
		case 2:
			image.setImageResource(mOutImages[position]);
			text.setText(mOutTexts[position]);
			break;
		case 3:
			image.setImageResource(mSchoolImages[position]);
			text.setText(mSchoolTexts[position]);
			break;
		default:
			break;
		}

		return view;
	}

}
