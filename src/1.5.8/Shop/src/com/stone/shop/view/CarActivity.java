package com.stone.shop.view;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.stone.shop.R;

/**
 * 购物车主界面
 * @date  2014-4-24
 * @author Stone
 */
public class CarActivity extends Activity {
	
	private static final String TAG = "CarActivity";
	
	private static final String URL_WSQ = "http://wx.wsq.qq.com/231782938";  
	private WebView wsqWebView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_car);
		
		wsqWebView = (WebView) findViewById(R.id.wv_wsq);
		
		// -----------------------------------------------------------------
		wsqWebView.getSettings().setJavaScriptEnabled(true);	// 设置使用够执行JS脚本
		wsqWebView.getSettings().setBuiltInZoomControls(true);	// 设置使支持缩放
		// wsqWebView.getSettings().setDefaultFontSize(5);
		wsqWebView.loadUrl(URL_WSQ);
		wsqWebView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view,
					String url) {
				view.loadUrl(url);// 使用当前WebView处理跳转
				return true;// true表示此事件在此处被处理，不需要再广播
			}

			@Override
			// 转向错误时的处理
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				Toast.makeText(CarActivity.this,
						"Oh no! " + description, Toast.LENGTH_SHORT)
						.show();
			}
		});
		// ------------------------------------------------
		
	}

}
