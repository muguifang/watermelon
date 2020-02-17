package com.muguifang.po;

import java.util.Date;

public class TMusic {
    private Integer id;

    private String musicname;

    private String musicphoto;

    private String musicplay;

    private String mvplay;

    private String recommend;

    private Integer status;

    private Integer typeId;

    private Integer playnum;

    private Date issuedate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMusicname() {
        return musicname;
    }

    public void setMusicname(String musicname) {
        this.musicname = musicname;
    }

    public String getMusicphoto() {
        return musicphoto;
    }

    public void setMusicphoto(String musicphoto) {
        this.musicphoto = musicphoto;
    }

    public String getMusicplay() {
        return musicplay;
    }

    public void setMusicplay(String musicplay) {
        this.musicplay = musicplay;
    }

    public String getMvplay() {
        return mvplay;
    }

    public void setMvplay(String mvplay) {
        this.mvplay = mvplay;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getPlaynum() {
        return playnum;
    }

    public void setPlaynum(Integer playnum) {
        this.playnum = playnum;
    }

    public Date getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(Date issuedate) {
        this.issuedate = issuedate;
    }
}