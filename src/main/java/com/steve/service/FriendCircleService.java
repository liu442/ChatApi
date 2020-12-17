package com.steve.service;

import com.github.pagehelper.PageHelper;
import com.steve.common.enums.SystemEnum;
import com.steve.common.result.ResultResponse;
import com.steve.conf.system.SystemConfig;
import com.steve.mapper.FriendCircleCommentMapper;
import com.steve.mapper.FriendCircleMapper;
import com.steve.mapper.UserMapper;
import com.steve.pojo.FriendCircle;
import com.steve.pojo.FriendCircleComment;
import com.steve.pojo.User;
import com.steve.pojo.resp.CommentResp;
import com.steve.pojo.resp.FriendCircleResp;
import com.steve.utils.systemUtil.UUIDUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Steve
 * @date 2020/2/12
 */
@Service
public class FriendCircleService extends BaseService {
    private static Logger logger = LoggerFactory.getLogger(FriendCircleService.class);

    @Autowired
    private FriendCircleMapper friendCircleMapper;

    @Autowired
    private FriendCircleCommentMapper friendCircleCommentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SystemConfig systemConfig;

    /**
     * 发布朋友圈
     * @param friendCircle
     * @return
     */
    public ResultResponse publishFriendCircle(FriendCircle friendCircle){
        try {
            friendCircle.setId(UUIDUtils.getUUID32());
            friendCircle.setUserId(getCurrentUser().getId());
            String images = StringUtils.strip(friendCircle.getFiles().toString(),"[]").replaceAll(" ","");
            friendCircle.setImage(images);
            friendCircle.setCreateTime(new Date());
            logger.info("新增朋友圈:"+friendCircle.toString());
            friendCircleMapper.insert(friendCircle);
            return ResultResponse.ok();
        }catch (Exception e){
            logger.info("新增朋友圈:{},异常信息:{}",friendCircle.toString(),e);
            return ResultResponse.fail();
        }
    }

    /**
     * 读取朋友圈 (包含点赞和评论回复列表)
     * @param friendCircle
     * @return
     */
    public ResultResponse<List<FriendCircleResp>> friendCircles(FriendCircle friendCircle){
        try {
            friendCircle.setUserId(getCurrentUser().getId());
            PageHelper.startPage(friendCircle.getPage(),friendCircle.getPageSize());
            logger.info("查询朋友圈:"+friendCircle.toString());
            List<FriendCircleResp> friendCircles = friendCircleMapper.friendCircles(getCurrentUser().getId());
            for (FriendCircleResp friendCircleResp : friendCircles) {
                FriendCircleComment comment=new FriendCircleComment();
                comment.setCircleId(friendCircleResp.getId());
//                评论和回复列表
                List<CommentResp> commentResps=friendCircleCommentMapper.getComments(comment);
                friendCircleResp.setComments(commentResps);
                //点赞列表
                List<CommentResp> like=friendCircleCommentMapper.getLikes(comment);
                for (CommentResp commentResp:like){
                    //表示当前登录人点赞了
                    if(getCurrentUser().getId().equals(commentResp.getCommentId())){
                        friendCircleResp.setCurrentUserIsLike(SystemEnum.LIKE.getKey());
                    }
                }
                friendCircleResp.setLike(like);
                //配置图片路径
                if(friendCircleResp.getImage()!= null && friendCircleResp.getImage().length()>0){
                    String imageList[] = friendCircleResp.getImage().split(",");
                    List<String> images = new ArrayList<>();
                    for(int i=0;i<imageList.length;i++){
                        images.add(systemConfig.getOssUrl()+imageList[i]);
                    }
                    String image = StringUtils.strip(images.toString(),"[]");
                    friendCircleResp.setImage(image);
                }
            }
            return ResultResponse.ok(friendCircles);
        }catch (Exception e){
            logger.info("查询朋友圈:{},异常信息:{}",friendCircle.toString(),e);
            return ResultResponse.fail();
        }
    }

    /**
     * 评论点赞和回复
     * @param friendCircleComment
     * @return
     */
    public ResultResponse comments(FriendCircleComment friendCircleComment){
        try {
            CommentResp commentResp=new CommentResp();
            friendCircleComment.setId(UUIDUtils.getUUID32());
            //评论者的id
            friendCircleComment.setCommentId(getCurrentUser().getId());
            friendCircleComment.setCreateTime(new Date());
            PropertyUtils.copyProperties(commentResp,friendCircleComment);
            //查询评论者 和回复人的信息
            User userComment = userMapper.selectByPrimaryKey(friendCircleComment.getCommentId());
            commentResp.setNickName(userComment.getNickname());
            commentResp.setHeadImage(userComment.getFaceImage());
            //如果是点赞
            if(friendCircleComment.getType()==SystemEnum.SYS_LIKE.getKey()){
                logger.info("点赞朋友圈:"+friendCircleComment.toString());
                //先查询是否已经点赞过了  如果点赞过了则取消点赞(删除该条点赞数据)
                FriendCircleComment f = new FriendCircleComment();
                f.setCircleId(friendCircleComment.getCircleId());
                f.setCommentId(friendCircleComment.getCommentId());
                f.setType(SystemEnum.SYS_LIKE.getKey());
                FriendCircleComment f_res=friendCircleCommentMapper.selectOne(f);
                if(f_res==null){
                    commentResp.setIsShowLike(SystemEnum.LIKE.getKey());
                    //没有点赞过则点赞
                    friendCircleCommentMapper.insert(friendCircleComment);
                }else{
                    commentResp.setIsShowLike(SystemEnum.NOLIKE.getKey());
                    //点赞过则取消
                    friendCircleCommentMapper.deleteByPrimaryKey(f_res.getId());
                }
            }else{

                if(friendCircleComment.getReplyId()!=null && !"".equals(friendCircleComment.getReplyId())){
                    User userReply = userMapper.selectByPrimaryKey(friendCircleComment.getCommentId());
                    commentResp.setReplyNickName(userReply.getNickname());
                }
                logger.info("评论和回复朋友圈:"+friendCircleComment.toString());
                friendCircleCommentMapper.insert(friendCircleComment);
            }
            //返回这条评论在前端插入值
            return ResultResponse.ok(commentResp);
        }catch (Exception e){
            logger.info("评论点赞和回复朋友圈:{},异常信息:{}",friendCircleComment.toString(),e);
            return ResultResponse.fail();
        }
    }


    /**
     * 删除自己的评论
     * @param friendCircleComment
     * @return
     */
    public ResultResponse deleteComment(FriendCircleComment friendCircleComment){
        try {
            logger.info("删除朋友圈评论:"+friendCircleComment.toString());
            // 先判断这条评论是不是自己评论的
            FriendCircleComment isMineComment = friendCircleCommentMapper.selectByPrimaryKey(friendCircleComment.getId());
            if(isMineComment==null){
                return ResultResponse.fail("评论不存在!");
            }
            if(!isMineComment.getCommentId().equals(getCurrentUser().getId())){
                return ResultResponse.fail("不能删除别人的评论!");
            }
            friendCircleCommentMapper.deleteByPrimaryKey(friendCircleComment.getId());
            return ResultResponse.ok();
        }catch (Exception e){
            logger.info("删除朋友圈评论:{},异常信息:{}",friendCircleComment.toString(),e);
            return ResultResponse.fail();
        }
    }
}
