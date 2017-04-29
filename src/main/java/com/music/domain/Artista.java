package com.music.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="Artista")
public class Artista implements Serializable {
	@Id
	@Column(name="Id")
	private Integer id;
	 
	@Column(name="name")
	private String name;
	
	@Column(name="followers")
	private Integer followers;
	
	@Column(name="img")
	private String img;
	
	@Column(name="froms")
	private String from;
	
	@Column(name="url")
	private String url;
	
	@Column(name="tracks")
	private String tracks;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getFollowers() {
		return followers;
	}
	public void setFollowers(Integer followers) {
		this.followers = followers;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTracks() {
		return tracks;
	}
	public void setTracks(String tracks) {
		this.tracks = tracks;
	}
	@Override
	public String toString() {
		return "Artista [name=" + name + ", followers=" + followers + ", id="
				+ id + ", img=" + img + ", from=" + from + ", url=" + url
				+ ", tracks=" + tracks + "]";
	}
	
}
