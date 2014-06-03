package com.stone.shop.view.old;

import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

import com.stone.shop.R;
import com.stone.shop.model.User;
import com.stone.shop.view.AboutActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 个人中心主界面
 * @date  2014-4-24
 * @author Stone
 */
public class OldMineActivity extends Activity implements OnClickListener{
	
	private static final String TAG = "OldMineActivity" ;
	
	private Button btnAbout;
	private Button btnContact;
	private Button btnJoin;			
	
	private TextView tvUserName; 	//当前用户的用户名
	private TextView tvSchool;		//学校
	private TextView tvCademy;		//学院
	private TextView tvDorPart;		//所在区		西区
	private TextView tvDorNum;		//宿舍楼号	19栋
	
	private TextView  tvMe;			//我
	private TextView tvXiaoCai;		//小菜
	private TextView tvBowl;		//饭碗
	private User user = new User();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mine_old);
		
		BmobUser user = BmobUser.getCurrentUser(this);
		Log.i(TAG, "BmobUser.userName = " + user.getUsername());
		getCurUser(user.getUsername());
		
		initView();
	}
	
	public void initView() {
		
		tvUserName = (TextView) findViewById(R.id.tv_username);
		tvSchool = (TextView) findViewById(R.id.tv_school);
		tvCademy = (TextView) findViewById(R.id.tv_cademy);
		tvDorPart = (TextView) findViewById(R.id.tv_dorPart);
		tvDorNum = (TextView) findViewById(R.id.tv_dorNum);
		
		btnAbout = (Button) findViewById(R.id.btn_about);
		btnContact = (Button) findViewById(R.id.btn_contact);
		btnJoin = (Button) findViewById(R.id.btn_share);
		
		tvUserName = (TextView) findViewById(R.id.tv_username);
		tvMe = (TextView) findViewById(R.id.tv_me);
		tvXiaoCai = (TextView) findViewById(R.id.tv_xiaocai);
		tvBowl = (TextView) findViewById(R.id.tv_bowl);
		
		btnAbout.setOnClickListener(this);
		btnContact.setOnClickListener(this);
		btnJoin.setOnClickListener(this);
		
		tvMe.setOnClickListener(this);
		tvXiaoCai.setOnClickListener(this);
		tvBowl.setOnClickListener(this);
	}
	
	//获得当前用户User类型
	public void getCurUser(String username) {
		BmobQuery<User> query = new BmobQuery<User>();
		query.addWhereEqualTo("username", username);
		query.findObjects(this, new FindListener<User>() {
		    @Override
		    public void onSuccess(List<User> object) {
		        if(object.size()==1)
		        	user = object.get(0);
		        else 
					user = null;
		        loadData();
		        //toast("查询到：" + object.size());
		    }
		   
			@Override
			public void onError(int arg0, String arg1) {
				toast("呜呜, 获取小菜的信息失败了.");
			}
		});
	}
	
	//加载查询到的用户数据
	public void loadData() {
		//toast("加载数据中...");
		tvUserName.setText(user.getUsername());
		tvSchool.setText(user.getSchool());
		tvCademy.setText(user.getCademy());
		tvDorPart.setText(user.getDorPart());
		tvDorNum.setText(user.getDorNum());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_share:
			Intent toShare = new Intent(Intent.ACTION_SEND);
			toShare.setType("text/plain");
			toShare.putExtra(Intent.EXTRA_SUBJECT, "分享");
			toShare.putExtra(Intent.EXTRA_TEXT, "校园小菜-HBUT版" +"\n" + "针对湖工大的校园小菜测试版上线了，赶紧下载体验吧"
					+ "http://xiaocai.bmob.cn");
			startActivity(Intent.createChooser(toShare, "分享到"));
			break;
		case R.id.tv_me:
			startAnim(R.id.tv_me);
			break;
		case R.id.tv_xiaocai:
			startAnim(R.id.tv_xiaocai);
			break;
		case R.id.tv_bowl:
			startAnim(R.id.tv_bowl);
			break;
		default:
			Intent toAbout = new Intent(OldMineActivity.this, AboutActivity.class);
			startActivity(toAbout);
			break;
		}
	}
	
	//点击文字动画
	private void startAnim(int id){
		Animation scale = AnimationUtils.loadAnimation(this, R.anim.shake);
		if(id == R.id.tv_me)
			tvMe.startAnimation(scale);
		else if(id == R.id.tv_xiaocai)
			tvXiaoCai.startAnimation(scale);
		else if(id == R.id.tv_bowl)
			tvBowl.startAnimation(scale);
		else { }
	}
	
	private void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}
	


}
