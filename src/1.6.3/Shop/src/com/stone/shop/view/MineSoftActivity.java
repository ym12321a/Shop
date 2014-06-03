package com.stone.shop.view;

import com.stone.shop.R;
import com.stone.shop.adapter.MineListAdapter;
import com.stone.shop.adapter.MineSoftAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 软件相关
 * @date 2014-5-21 
 * @author Stone
 */
public class MineSoftActivity extends Activity implements OnItemClickListener{
	
	
	private String[] softItemNames = {"意见反馈", "检查更新", "使用协议", "关于我们"};
	private String[] softItemContents = {"", "", "", ""};
	private ListView lvMineSoft;
	
	private MineSoftAdapter softListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_soft);
		
		initView();
	}
	
	private void initView() {
		lvMineSoft = (ListView) findViewById(R.id.lv_mine_soft);
		softListAdapter = new MineSoftAdapter(this, softItemNames, softItemContents);
		lvMineSoft.setAdapter(softListAdapter);
		lvMineSoft.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (position) {
		case 0: 
			Intent toFeedBack = new Intent(MineSoftActivity.this, FeedBackActivity.class);
			startActivity(toFeedBack);
			break;
		case 1:
			toast("已经是最新版本");
			break;
		case 2:
			break;
		case 3:
			Intent toAboutSoft = new Intent(MineSoftActivity.this, AboutActivity.class);
			startActivity(toAboutSoft);
			break;

		default:
			break;
		}
		
	}
	
	public void clickSoftBack(View v) {
		finish();
	}
	
	private void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}
}
