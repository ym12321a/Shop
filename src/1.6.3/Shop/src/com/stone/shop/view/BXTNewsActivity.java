package com.stone.shop.view;

import com.stone.shop.R;
import com.stone.shop.model.BXTNews;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 教学类-博学堂-讲座详情界面
 * @date 2014-5-10
 * @author Stone
 */
public class BXTNewsActivity extends Activity {
	
	private static final String TAG = "BXTNewsActivity"; 
	
	private BXTNews news;
	private TextView tvBXTNewsTitle;
	private TextView tvBXTNewsTopic;
	private TextView tvBXTNewsSpeaker;
	private TextView tvBXTNewsTime;
	private TextView tvBXTNewsLoc;
	private TextView tvBXTNewsHolder1;
	private TextView tvBXTNewsHolder2;
	private TextView tvBXTNewsPoints;
	private TextView tvBXTNewsSpeakerInfo;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bxt_news);
		
		initView();
	}
	
	private void initView() {
		
		tvBXTNewsTitle = (TextView) findViewById(R.id.tv_bxt_news_title);
		tvBXTNewsTopic = (TextView) findViewById(R.id.tv_bxt_news_topic);
		tvBXTNewsSpeaker = (TextView) findViewById(R.id.tv_bxt_news_speaker);
		tvBXTNewsTime = (TextView) findViewById(R.id.tv_bxt_news_time);
		tvBXTNewsLoc = (TextView) findViewById(R.id.tv_bxt_news_loc);
		tvBXTNewsHolder1 = (TextView) findViewById(R.id.tv_bxt_news_holder1);
		tvBXTNewsHolder2 = (TextView) findViewById(R.id.tv_bxt_news_holder2);
		tvBXTNewsPoints = (TextView) findViewById(R.id.tv_bxt_news_point);
		tvBXTNewsSpeakerInfo = (TextView) findViewById(R.id.tv_bxt_news_speaker_info);
		
		tvBXTNewsTitle.setText(getIntent().getStringExtra("title"));
		tvBXTNewsTopic.setText(getIntent().getStringExtra("topic"));
		tvBXTNewsSpeaker.setText(getIntent().getStringExtra("speaker"));
		tvBXTNewsTime.setText(getIntent().getStringExtra("time"));
		tvBXTNewsLoc.setText(getIntent().getStringExtra("location"));
		tvBXTNewsHolder1.setText(getIntent().getStringExtra("holder1"));
		tvBXTNewsHolder2.setText(getIntent().getStringExtra("holder2"));
		tvBXTNewsPoints.setText(getIntent().getStringExtra("points"));
		tvBXTNewsSpeakerInfo.setText(getIntent().getStringExtra("speakerinfo"));
		
	}
	
	

}
