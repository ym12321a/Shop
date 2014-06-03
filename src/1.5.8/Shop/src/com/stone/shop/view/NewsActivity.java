package com.stone.shop.view;

import com.stone.shop.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 新闻内容显示界面
 * @date 2014-5-8
 * @author Stone
 */
public class NewsActivity extends Activity {

	private static String TAG = "NewsActivity";
	
	private TextView tvNewsTitle;
	private TextView tvNewsAuthor;
	private TextView tvNewsTime;
	private TextView tvNewsContent;
	
	private String newsTitle;
	private String newsAuthor;
	private String newsTime;
	private String newsContent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		
		getIntentData();
		initView();
	}
	
	//获取Intent中传入的新闻数据
	private void getIntentData() {
		newsTitle = getIntent().getStringExtra("NewsTitle");
		newsAuthor = getIntent().getStringExtra("NewsAuthor");
		newsTime = getIntent().getStringExtra("NewsTime");
		newsContent = getIntent().getStringExtra("NewsContent");
		
		newsTitle = splitString(newsTitle); 	//拆分字符串, 将新闻标题设置为  "】" 后面的内容
	}
	
	private String  splitString(String str) {
		String[] strs = null;
		if(str.equals("")){
			return "";
		} else if ( !(str.contains("【") || str.contains("】")) ) {
			return str;
		}
		strs = str.split("】");
		return strs[1];
	}
	
	private void initView() {
		tvNewsTitle = (TextView) findViewById(R.id.tv_news_title);
		tvNewsAuthor = (TextView) findViewById(R.id.tv_news_author);
		tvNewsTime = (TextView) findViewById(R.id.tv_news_time);
		tvNewsContent = (TextView) findViewById(R.id.tv_news_content);
		
		tvNewsTitle.setText(newsTitle);
		tvNewsAuthor.setText("作者: "+newsAuthor);
		tvNewsTime.setText("发布日期 : "+newsTime);
		tvNewsContent.setText(newsContent);
	}
	
	

}
