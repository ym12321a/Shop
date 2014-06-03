package com.stone.shop.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.stone.shop.R;
import com.stone.shop.adapter.ShopListAdapter;
import com.stone.shop.model.Shop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 某一分类下的所有店铺页面
 * @author Stone
 * @date  2014-4-26 
 */
public class ShopAllActivity extends Activity implements OnItemClickListener{
	
	private static final String TAG = "ShopAllActivity" ; 

	private TextView tvTitle;
	private ListView lvShopAllList;
	private ShopListAdapter shopListAdapter;
	
	//记录从ShopActivity中传过来的当前点击项的类型
	private String type;
	private List<Shop> shopList = new ArrayList<Shop>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop_all);
		
		//得到从上级Activity中传入的Type数据
		type = getIntent().getStringExtra("type");
		
		//获取商店数据
		getShopsDate();
		
		initView();
		
	}
	
	public void initView() {
		//设置标题
		tvTitle = (TextView) findViewById(R.id.tv_title);
		tvTitle.setText(getIntent().getStringExtra("title"));
		
		lvShopAllList = (ListView) findViewById(R.id.lv_shop_all);
		shopListAdapter = new ShopListAdapter(this, (ArrayList<Shop>) shopList, type);
		lvShopAllList.setAdapter(shopListAdapter);
		lvShopAllList.setOnItemClickListener(this);
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		//toast("点击了： " + position);
		//将当前点击的Shop对象传递给下一个Activity
		Intent toShopItem = new Intent(ShopAllActivity.this, ShopItemActivity.class);
		Bundle bundle = new Bundle();  
        bundle.putSerializable("shop", shopList.get(position) );  
        bundle.putString("shopID", shopList.get(position).getObjectId()); //商铺的ID需要单独传递,否则获取到的是null
        Log.i(TAG, ">>发出>>" + "shopID: "+shopList.get(position).getObjectId()+" shopName: "+shopList.get(position).getName());
        toShopItem.putExtras(bundle);
		startActivity(toShopItem);
	}
	
	
	private void getShopsDate() {
		BmobQuery<Shop> query = new BmobQuery<Shop>();
		query.order("-updatedAt");
		Shop shop = new Shop();
		shop.setType(type);
		query.addWhereEqualTo("type", shop.getType());    // 查询当前类型的所有店铺
		query.findObjects(this, new FindListener<Shop>() {
			
		    @Override
		    public void onSuccess(List<Shop> object) {
		        //toast("查询成功. 共计" + object.size());
		    	if(object.size()==0)
		    		toast("亲, 还没开张, 耐心等待吧");
		        shopList = object;
		        // 通知Adapter数据更新
		        shopListAdapter.refresh((ArrayList<Shop>) shopList);
		        shopListAdapter.notifyDataSetChanged();

		    }
		    @Override
		    public void onError(String msg) {
		        toast("查询失败:"+msg);
		    }
			
		});
	}
	
	
	private void toast(String toast) { 
		Toast.makeText(this, toast,  Toast.LENGTH_SHORT).show();
	};
	
}
