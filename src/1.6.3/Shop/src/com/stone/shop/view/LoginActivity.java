package com.stone.shop.view;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.InsertListener;

import com.stone.shop.R;
import com.stone.shop.model.User;
import com.stone.util.Util;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQAuth;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

/**
 * 登陆界面
 * 
 * @date 2014-4-24
 * @author Stone
 */
public class LoginActivity extends Activity implements OnClickListener{

	private static final String TAG = "LoginActicity";

	private Button btnLogin;
	private Button btnReg;
	private EditText etUsername;
	private EditText etPassword;

	private String username;
	private String password;
	
	private static final String APP_ID = "101080318";
	private UserInfo mInfo;
	private TextView mUserInfo;  
    private ImageView mUserLogo;  
    private ImageView mNewLoginButton;  
    private TextView backInfo;  
    
    //QQ登陆
//  private Tencent mTencent;
//	private QQAuth mQQAuth; 
    Handler mHandler = new Handler() {  
    	  
        @Override  
        public void handleMessage(Message msg) {  
            if (msg.what == 0) {  
                mUserInfo.setVisibility(android.view.View.VISIBLE);  
                mUserInfo.setText(msg.getData().getString("nickname"));  
            } else if (msg.what == 1) {  
                Bitmap bitmap = (Bitmap) msg.obj;  
                mUserLogo.setImageBitmap(bitmap);  
                mUserLogo.setVisibility(android.view.View.VISIBLE);  
            }  
        }  
    };  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化 Bmob SDK
		// 使用时请将第二个参数Application ID替换成你在Bmob服务器端创建的Application ID
		Bmob.initialize(this, "999848e5d36a83ae049281de8b8ae1a5");
		setContentView(R.layout.activity_login);
		
		//QQ登陆, 获取实例
//		mQQAuth = QQAuth.createInstance(APP_ID, this.getApplicationContext()); 
//		mTencent = Tencent.createInstance(APP_ID, this.getApplicationContext());

		btnLogin = (Button) findViewById(R.id.btn_login);
		btnReg = (Button) findViewById(R.id.btn_register);

		etUsername = (EditText) findViewById(R.id.et_username);
		etPassword = (EditText) findViewById(R.id.et_password);

		btnLogin.setOnClickListener(this);
		btnReg.setOnClickListener(this);
		
		mUserInfo = (TextView) findViewById(R.id.user_nickname);  
        mUserLogo = (ImageView) findViewById(R.id.user_logo);  
        mNewLoginButton = (ImageView) findViewById(R.id.new_login_btn);  
        mNewLoginButton.setOnClickListener(this);  
        backInfo = (TextView) findViewById(R.id.user_callback);
        
        getUserInfo();

	}
	
	private void getUserInfo() {
		SharedPreferences sp = getSharedPreferences("UserInfo", 0);
		etUsername.setText(sp.getString("username", null));
		etPassword.setText(sp.getString("password", null));
	}
	
	//保存用户的登陆记录
	private void saveUserInfo(String username, String password) {
		SharedPreferences sp = getSharedPreferences("UserInfo", 0);
		Editor editor = sp.edit();
		editor.putString("username", username);
		editor.putString("password", password);
		editor.commit();
	}
	
//	private void updateUserInfo() {  
//        if (mQQAuth != null && mQQAuth.isSessionValid()) {  
//            IUiListener listener = new IUiListener() {  
//                @Override  
//                public void onError(UiError e) {  
//                	Toast.makeText(LoginActivity.this, "授权失败", 1000).show();
//                }  
//  
//                @Override  
//                public void onComplete(final Object response) {  
//                	Toast.makeText(LoginActivity.this, "授权成功", 1000).show();
//                    JSONObject json = (JSONObject) response;  
//                    // 昵称  
//                    Message msg = new Message();  
//                    String nickname = null;  
//                    try {  
//                        nickname = ((JSONObject) response)  
//                                .getString("nickname");  
//                    } catch (JSONException e) {  
//                        e.printStackTrace();  
//                    }  
//                    msg.getData().putString("nickname", nickname);  
//                    msg.what = 0;  
//                    mHandler.sendMessage(msg);  
//                    // 头像  
//                    String path;  
//                    try {  
//                        path = json.getString("figureurl_qq_2");  
//                        MyImgThread imgThread = new MyImgThread(path);  
//                        Thread thread = new Thread(imgThread);  
//                        thread.start();  
//                    } catch (JSONException e1) {  
//                        // TODO Auto-generated catch block  
//                        e1.printStackTrace();  
//                    }  
//                }  
//  
//                @Override  
//                public void onCancel() {  
//                	Toast.makeText(LoginActivity.this, "取消授权", 1000).show();
//  
//                }  
//            };  
//            // MainActivity.mTencent.requestAsync(Constants.GRAPH_SIMPLE_USER_INFO,  
//            // null,  
//            // Constants.HTTP_GET, requestListener, null);  
//            mInfo = new UserInfo(this, mQQAuth.getQQToken());  
//            mInfo.getUserInfo(listener);  
//  
//        } else {  
//            // mUserInfo.setText("");  
//            // mUserInfo.setVisibility(android.view.View.GONE);  
//            // mUserLogo.setVisibility(android.view.View.GONE);  
//        }  
//    }  
//  
//    /**  
//     * 开启线程 获取头像  
//     */  
//    class MyImgThread implements Runnable {  
//        private String imgPath;  
//        private Bitmap bitmap;  
//  
//        public MyImgThread(String imgpath) {  
//            this.imgPath = imgpath;  
//        }  
//  
//        @Override  
//        public void run() {  
//            // TODO Auto-generated method stub  
//            bitmap = getImgBitmap(imgPath);  
//            Message msg = new Message();  
//            msg.obj = bitmap;  
//            msg.what = 1;  
//            mHandler.sendMessage(msg);  
//        }  
//    }  
//  
//    /**  
//     * 根据头像的url 获取bitmap  
//     */  
//    public Bitmap getImgBitmap(String imageUri) {  
//        // 显示网络上的图片  
//        Bitmap bitmap = null;  
//        HttpURLConnection conn = null;  
//        InputStream is = null;  
//        try {  
//            URL myFileUrl = new URL(imageUri);  
//            conn = (HttpURLConnection) myFileUrl.openConnection();  
//            conn.setDoInput(true);  
//            conn.connect();  
//  
//            is = conn.getInputStream();  
//            bitmap = BitmapFactory.decodeStream(is);  
//            is.close();  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//            return null;  
//        } finally {  
//            try {  
//                conn.disconnect();  
//                is.close();  
//                is.reset();  
//            } catch (IOException e) {  
//                e.printStackTrace();  
//            }  
//        }  
//        return bitmap;  
//    }  
//  
//    public void onClickLogin() {  
//        // 登录  
//        if (!mQQAuth.isSessionValid()) {  
//            // 实例化回调接口  
//            IUiListener listener = new BaseUiListener() {  
//                @Override  
//                protected void doComplete(JSONObject values) {  
//                    updateUserInfo();  
//                    // updateLoginButton();  
//                    if (mQQAuth != null) {  
//                        /////mNewLoginButton.setTextColor(Color.BLUE);  
//                        //////mNewLoginButton.setText("登录");  
//                    }  
//                }  
//            };  
//            // "all": 所有权限，listener: 回调的实例  
//            // mQQAuth.login(this, "all", listener);  
//  
//            // 这版本登录是使用的这种方式，后面的几个参数是啥意思 我也没查到  
//            mTencent.loginWithOEM(this, "all", listener, "10000144",  
//                    "10000144", "xxxx");  
//        } else {  
//            // 注销登录  
//            mQQAuth.logout(this);  
//            updateUserInfo();  
//  
//            // updateLoginButton();  
//            /////mNewLoginButton.setTextColor(Color.RED);  
//            /////mNewLoginButton.setText("退出帐号");  
//        }  
//    }  
//  
//    /**  
//     * 调用SDK封装好的借口，需要传入回调的实例 会返回服务器的消息  
//     */  
//    private class BaseUiListener implements IUiListener {  
//        /**  
//         * 成功  
//         */  
//        @Override  
//        public void onComplete(Object response) {  
//            backInfo.setText(response.toString());  
//            doComplete((JSONObject) response);  
//        }  
//  
//        /**  
//         * 处理返回的消息 比如把json转换为对象什么的  
//         *   
//         * @param values  
//         */  
//        protected void doComplete(JSONObject values) {  
//  
//        }  
//  
//        @Override  
//        public void onError(UiError e) {  
//            Toast.makeText(LoginActivity.this, e.toString(), 1000).show();  
//        }  
//  
//        @Override  
//        public void onCancel() {  
//            Toast.makeText(LoginActivity.this, "cancel", 1000).show();  
//        }  
//    }  
	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 登陆
		case R.id.btn_login:
			username = etUsername.getText().toString();
			password = etPassword.getText().toString();
			
			if( !Util.isNetworkConnected(this) ){
				toast("亲, 木有网络 ( ⊙ o ⊙ ) ");
			} else if (username.equals("") || password.equals("")) {
				toast("亲, 请输入小菜账号和密码");
				break;
			} else {
				User bu2 = new User();
				bu2.setUsername(username);
				bu2.setPassword(password);
				bu2.login(this, new InsertListener() {
					@Override
					public void onSuccess() {
						toast("亲, 小菜来罗~");
						//保存用户信息
						saveUserInfo(username, password);
						// 跳转到主页
						Intent toHome = new Intent(LoginActivity.this,
								BaseActivity.class);
						startActivity(toHome);
						finish();
					}

					@Override
					public void onFailure(String msg) {
						toast("亲, 用户名或密码错误");
					}
				});
			}
			break;

		case R.id.btn_register:
			Intent toReg = new Intent(LoginActivity.this,
					RegisterActivity.class);
			startActivity(toReg);
			break;
//		case R.id.new_login_btn:
//			onClickLogin();
//			break;
		default:
			break;

		}
	}

	public void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}

}
