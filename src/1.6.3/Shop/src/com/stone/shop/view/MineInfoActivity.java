package com.stone.shop.view;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

import com.stone.date.MessageDef;
import com.stone.shop.R;
import com.stone.shop.model.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 个人资料卡
 * @date 2014-5-21 
 * @author Stone
 */
public class MineInfoActivity extends Activity {
	
	private TextView tvUsername;
	private TextView tvSchool;
	private TextView tvCademy;
	private TextView tvDorPart;
	private TextView tvDorNum;
	private TextView tvPhone;
	private TextView tvQQ;
	
	private User curUser = new User();
	
	private Handler mHandler = new Handler() {  
		  @Override  
		  public void handleMessage(Message msg) {  
		      switch (msg.what) {
				case MessageDef.MINE_INFO_FINISH_FIND_USER:
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
		setContentView(R.layout.activity_mine_info);
		
		getCurUser();
	}
	
	private void initView() {
		tvUsername = (TextView) findViewById(R.id.tv_mineinfo_username);
		tvSchool = (TextView) findViewById(R.id.tv_mineinfo_school);
		tvCademy = (TextView) findViewById(R.id.tv_mineinfo_cademy);
		tvDorPart = (TextView) findViewById(R.id.tv_mineinfo_dorpart);
		tvDorNum = (TextView) findViewById(R.id.tv_mineinfo_dornum);
		tvPhone = (TextView) findViewById(R.id.tv_mineinfo_phone);
		tvQQ = (TextView) findViewById(R.id.tv_mineinfo_qq);
		
		tvUsername.setText(curUser.getUsername());
		tvSchool.setText(curUser.getSchool());
		tvCademy.setText(curUser.getCademy());
		tvDorPart.setText(curUser.getDorPart());
		tvDorNum.setText(curUser.getDorNum());
		tvPhone.setText(curUser.getPhone());
		tvQQ.setText(curUser.getQQ());
		
	}
	
	private void getCurUser() {
		BmobUser bmobUser = BmobUser.getCurrentUser(this);
		BmobQuery<User> query = new BmobQuery<User>();
		query.addWhereEqualTo("objectId", bmobUser.getObjectId());
		query.findObjects(this, new FindListener<User>() {
			
			@Override
			public void onSuccess(List<User> object) {
				curUser = object.get(0);
				Message msg = new Message();
				msg.what = MessageDef.MINE_INFO_FINISH_FIND_USER;
				mHandler.sendMessage(msg);
			}

			@Override
			public void onError(int arg0, String arg1) {
				toast("亲， 获取当前用户失败");
			}
		});
		
	}
	
	public void clickEdit(View v) {
		Intent toEditMineInfo = new Intent(MineInfoActivity.this, MineInfoEditActivity.class);
//		Bundle bundle = new Bundle();
//		bundle.putString("username", curUser.getUsername());
//		bundle.putString("school", curUser.getSchool());
//		bundle.putString("cademy", curUser.getCademy());
//		bundle.putString("dorpart", curUser.getDorPart());
//		bundle.putString("dornum", curUser.getDorNum());
//		bundle.putString("phone", curUser.getPhone());
//		bundle.putString("qq", curUser.getQQ());
//		toEditMineInfo.putExtras(bundle);
		startActivity(toEditMineInfo);
	}
	
	public void clickBack(View v) {
		finish();
	}
	
	private void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}
	
	
}
