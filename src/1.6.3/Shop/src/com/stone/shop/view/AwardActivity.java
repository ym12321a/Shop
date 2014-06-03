package com.stone.shop.view;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.stone.date.MessageDef;
import com.stone.shop.R;
import com.stone.shop.model.LuckyUser;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 每日一抽页面
 * @date 2014-5-18
 * @author Stone
 */
public class AwardActivity extends Activity {
	
	private EditText etAwardNew;
	private EditText etAwardOld;
	
	private String awardNew;
	private String awardOld;
	
	private Handler mHandler = new Handler() {  
		  @Override  
		  public void handleMessage(Message msg) {  
		      switch (msg.what) {
				case 0:
					initView();
					break;
				default:
					break;
				}
		  }
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_award);
		
		initData();
	}
	
	private void initView() {
		etAwardNew = (EditText) findViewById(R.id.et_award_new);
		etAwardOld = (EditText) findViewById(R.id.et_award_old);
		
		etAwardNew.setText(awardNew);
		etAwardOld.setText(awardOld);
	}
	
	private void initData() {
		BmobQuery<LuckyUser> query = new BmobQuery<LuckyUser>();
		query.order("-updateAt");
		query.findObjects(this, new FindListener<LuckyUser>() {
			
			@Override
			public void onSuccess(List<LuckyUser> list) {
				awardNew = list.get(0).getUsername()+"      " +list.get(0).getAward();
				awardOld = list.get(1).getUsername()+"      " +list.get(1).getAward();
				Message msg = new Message();
				msg.what = 0;
				mHandler.sendMessage(msg);
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				toast("获取中奖名单失败");
			}
		});
	}
	
	private void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}
	

}
