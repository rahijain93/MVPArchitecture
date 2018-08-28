package com.example.rahi.demoarch.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Entity
@Generated("com.robohorse.robopojogenerator")
public class Posts implements Parcelable{

	@PrimaryKey
	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("body")
	private String body;

	@SerializedName("userId")
	private int userId;

	public Posts() {
	}

	protected Posts(Parcel in) {
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

	public static final Creator<Posts> CREATOR = new Creator<Posts>() {
		@Override
		public Posts createFromParcel(Parcel in) {
			return new Posts(in);
		}

		@Override
		public Posts[] newArray(int size) {
			return new Posts[size];
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