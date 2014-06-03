package com.stone.shop.model;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;

/**
 * 用户实体类
 * @date 2014-4-24
 * @author Stone
 */
public class User extends BmobUser {

	// 父类中已经存在的属性
	// private String id;
	// private String username;
	// private String password;
	// private String email;
	// private String regTime;

	private String sex;  		// 性别
	private String phone; 		// 电话
	private String qq; 			// QQ
	private String school; 		// 学校
	private String cademy; 		// 学院
	private String dorPart; 	// 校区
	private String dorNum; 		// 寝室号
	private String state; 		// 状态
	//private BmobFile picUser; 	// 头像

	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQQ() {
		return qq;
	}

	public void setQQ(String qq) {
		this.qq = qq;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getCademy() {
		return cademy;
	}

	public void setCademy(String cademy) {
		this.cademy = cademy;
	}

	public String getDorPart() {
		return dorPart;
	}

	public void setDorPart(String dorPart) {
		this.dorPart = dorPart;
	}

	public String getDorNum() {
		return dorNum;
	}

	public void setDorNum(String dorNum) {
		this.dorNum = dorNum;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
