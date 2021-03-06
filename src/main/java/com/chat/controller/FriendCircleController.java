package com.chat.controller;

import com.chat.common.constant.Constant;
import com.chat.common.result.ResultResponse;
import com.chat.pojo.FriendCircle;
import com.chat.pojo.FriendCircleComment;
import com.chat.pojo.resp.CommentResp;
import com.chat.pojo.resp.FriendCircleResp;
import com.chat.service.FriendCircleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 朋友圈相关接口
 * @author
 * @date 2020/2/12
 */
@RestController
@RequestMapping(value = Constant.API_PATH+"/friendCircle",method = RequestMethod.POST)
public class FriendCircleController {

    @Autowired
    private FriendCircleService friendCircleService;

    /**
     * 发布朋友圈
     * @param friendCircle
     * @return
     */
    @RequestMapping("/publishFriendCircle")
    @ResponseBody
    public ResponseEntity<ResultResponse> publishFriendCircle(@RequestBody FriendCircle friendCircle) throws Exception{
        return ResponseEntity.ok(friendCircleService.publishFriendCircle(friendCircle));
    }

    /**
     * 读取朋友圈
     * @return
     */
    @RequestMapping("/friendCircles")
    @ResponseBody
    public ResponseEntity<ResultResponse<List<FriendCircleResp>>> friendCircles(@RequestBody FriendCircle friendCircle){
        return ResponseEntity.ok(friendCircleService.friendCircles(friendCircle));
    }

    /**
     * 评论 回复  点赞
     * @return
     */
    @RequestMapping("/comments")
    @ResponseBody
    public ResponseEntity<ResultResponse<CommentResp>> comments(@RequestBody FriendCircleComment friendCircleComment){
        return ResponseEntity.ok(friendCircleService.comments(friendCircleComment));
    }

    /**
     * 删除自己的评论
     * @param friendCircleComment
     * @return
     */
    @RequestMapping("/deleteComment")
    @ResponseBody
    public ResponseEntity<ResultResponse> deleteComment(@RequestBody FriendCircleComment friendCircleComment){
        return ResponseEntity.ok(friendCircleService.deleteComment(friendCircleComment));
    }
}
