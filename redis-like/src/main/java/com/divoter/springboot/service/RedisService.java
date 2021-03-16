package com.divoter.springboot.service;

import entity.LikedCount;
import entity.UserLike;

import java.util.List;

/**
 * redis 操作接口类
 */
public interface RedisService {
    /**
     * 业务分析：
     * 1.用 Redis 存储两种数据，一种是记录点赞人、被点赞人、点赞状态的数据，
     * 另一种是每个用户被点赞了多少次，做个简单的计数。
     * 2.由于需要记录点赞人和被点赞人，还有点赞状态（点赞、取消点赞），
     * 还要固定时间间隔取出 Redis 中所有点赞数据，分析了下 Redis 数据格式中 Hash 最合适。
     * 3.因为 Hash 里的数据都是存在一个键里，可以通过这个键很方便的把所有的点赞数据都取出。
     * 这个键里面的数据还可以存成键值对的形式，方便存入点赞人、被点赞人和点赞状态。
     * 4.设点赞人的 id 为 likedPostId，被点赞人的 id 为 likedUserId ，
     * 点赞时状态为 1，取消点赞状态为 0。
     * 将点赞人 id 和被点赞人 id 作为键，两个 id 中间用 :: 隔开，点赞状态作为值。
     * 5.所以如果用户点赞，存储的键为：likedUserId::likedPostId，对应的值为 1 。
     * 取消点赞，存储的键为：likedUserId::likedPostId，对应的值为 0 。
     * 取数据时把键用 :: 切开就得到了两个 id，也很方便。
     *
     */

    /**
     * 点赞操作
     * @param likedPostId 点赞人
     * @param likedUserId 被点赞人
     */
    void saveLike2Redis(String likedPostId,String likedUserId);

    /**
     * 取消点赞 操作
     * @param likedPostId 点赞人
     * @param likeUserId 被点赞人
     */
    void saveUnlike2Redis(String likedPostId, String likeUserId);

    /**
     * 删除一条点赞
     * @param likedPostId 点赞人
     * @param likeUserId 被点赞人
     */
    void deleteLikedFromRedis(String likedPostId, String likeUserId);


    /**
     * 用户点赞数加一
     * @param likeUserId 被点赞人
     */
    void incrementLikedCount(String likeUserId);

    /**
     * 用户数点赞减一
     * @param likeUserId 被点赞人
     */
    void decrementLikeCount(String likeUserId);

    /**
     * 获取所有的点赞列表
     * @return 点赞列表
     */
    List<UserLike> getUserLikeFromRedis();

    /**
     * 获取所有的点赞统计
     * @return
     */
    List<LikedCount> getLikedCountFromRedis();

}
