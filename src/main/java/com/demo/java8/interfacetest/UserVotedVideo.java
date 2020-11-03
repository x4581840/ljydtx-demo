package com.demo.java8.interfacetest;

import lombok.Data;

/**
 * @Description
 * @Author longjianyong
 * @Date 2019/9/17 4:52 PM
 * @Version 1.0
 **/
@Data
public class UserVotedVideo {

    public final static String COLLECTION_NAME = "user_voted_video";

    private String _id;
    private Integer userId;
    private String videoUUID; //视频uuid
    private String voteDate; //投票日期

    public UserVotedVideo(Integer userId, String videoUUID, String voteDate) {
        this.userId = userId;
        this.videoUUID = videoUUID;
        this.voteDate = voteDate;
    }

}
