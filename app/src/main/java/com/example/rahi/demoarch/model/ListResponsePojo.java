package com.example.rahi.demoarch.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ListResponsePojo implements Parcelable{

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("body")
	private String body;

	@SerializedName("userId")
	private int userId;

	protected ListResponsePojo(Parcel in) {
		id = in.readInt();
		title = in.readString();
		body = in.readString();
		userId = in.readInt();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(title);
		dest.writeString(body);
		dest.writeInt(userId);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<ListResponsePojo> CREATOR = new Creator<ListResponsePojo>() {
		@Override
		public ListResponsePojo createFromParcel(Parcel in) {
			return new ListResponsePojo(in);
		}

		@Override
		public ListResponsePojo[] newArray(int size) {
			return new ListResponsePojo[size];
		}
	};

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setBody(String body){
		this.body = body;
	}

	public String getBody(){
		return body;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	@Override
 	public String toString(){
		return 
			"{" +
			"id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			",body = '" + body + '\'' + 
			",userId = '" + userId + '\'' + 
			"}";
		}



}