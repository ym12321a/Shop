package com.stone.shop.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.stone.shop.R;
import com.stone.shop.model.User;
import com.stone.util.Util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.v3.listener.InsertListener;

/**
 * 注册界面
 * 
 * @date 2014-4-24
 * @author Stone
 */
public class RegisterActivity extends Activity implements OnClickListener {

	private static final String TAG = "RegisterActivity";

	private Button btnReg;
	private EditText etUsername;
	private EditText etPassword;
	private EditText etComfirmPsd;
	private EditText etPhone;

	private String username = null;
	private String password = null;
	private String comfirmPsd = null;
	private String phone = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);

		etUsername = (EditText) findViewById(R.id.et_username);
		etPassword = (EditText) findViewById(R.id.et_password);
		etComfirmPsd = (EditText) findViewById(R.id.et_comfirm_psd);
		etPhone = (EditText) findViewById(R.id.et_phone);

		btnReg = (Button) findViewById(R.id.btn_reg_now);
		btnReg.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_reg_now:
			username = etUsername.getText().toString();
			password = etPassword.getText().toString();
			comfirmPsd = etComfirmPsd.getText().toString();
			phone = etPhone.getText().toString();
			if(!Util.isNetworkConnected(this)) {
				toast("亲, 木有网络 ( ⊙ o ⊙ ) ");
			} else if (username.equals("") || password.equals("")
					|| comfirmPsd.equals("") || phone.equals("")) {
				toast("亲, 不填完整, 小菜不能拿到身份证, ~~~~(>_<)~~~~ ");
			} else if (!comfirmPsd.equals(password)) {
				toast("亲, 小菜说你手抖了下, 两次密码输入不一致");
			} else if(!Util.isPhoneNumberValid(phone)) {
				toast("亲, 请输入正确的手机号码");
			}else {
				// 开始提交注册信息
				User bu = new User();
				bu.setUsername(username);
				bu.setPassword(password);
				bu.setPhone(phone);
				bu.signUp(this, new InsertListener() {
					@Override
					public void onSuccess() {
						toast("亲, 小菜拿到身份证了, 一起登陆辽宁号去吧");
						Intent backLogin = new Intent(RegisterActivity.this,
								LoginActicity.class);
						startActivity(backLogin);
						RegisterActivity.this.finish();
					}

					@Override
					public void onFailure(String msg) {
						toast("亲, 被人捷足先登了, 换个名字吧.");
					}
				});
			}
			break;

		default:
			break;
		}
	}

	public void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	};

}
