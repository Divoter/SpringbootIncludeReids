package com.divoter.springboot.service.impl;

import com.divoter.springboot.service.RedisService;
import entity.LikedCount;
import entity.UserLike;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import projectenum.LikedStatusEnum;
import util.RedisLikeUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void saveLike2Redis(String likedPostId, String likedUserId) {
        redisTemplate.opsForHash().put(
                RedisLikeUtil.MAP_KEY_USER_LIKED,
                RedisLikeUtil.getUserLiekdKey(likedPostId, likedUserId),
                LikedStatusEnum.LIKE.getCode()
        );
    }

    @Override
    public void saveUnlike2Redis(String likedPostId, String likeUserId) {
        redisTemplate.opsForHash().put(
                RedisLikeUtil.MAP_KEY_USER_LIKED,
                RedisLikeUtil.getUserLiekdKey(likedPostId, likeUserId),
                LikedStatusEnum.UNLIKE.getCode()
        );
    }

    @Override
    public void deleteLikedFromRedis(String likedPostId, String likeUserId) {
        redisTemplate.opsForHash().delete(
                RedisLikeUtil.MAP_KEY_USER_LIKED,
                RedisLikeUtil.getUserLiekdKey(likedPostId, likeUserId)
        );
    }

    @Override
    public void incrementLikedCount(String likeUserId) {
        redisTemplate.opsForHash().increment(
                RedisLikeUtil.MAP_KEY_USER_LIKED_COUNT,
                likeUserId,
                1
        );
    }

    @Override
    public void decrementLikeCount(String likeUserId) {
        redisTemplate.opsForHash().increment(
                RedisLikeUtil.MAP_KEY_USER_LIKED_COUNT,
                likeUserId,
                -1
        );
    }

    @Override
    public List<UserLike> getUserLikeFromRedis() {
        Cursor<Map.Entry<Object,Object>> scan
                = redisTemplate.opsForHash().scan(RedisLikeUtil.MAP_KEY_USER_LIKED, ScanOptions.NONE);
        ArrayList<UserLike> list = new ArrayList<>();
        while (scan.hasNext()){
            Map.Entry<Object, Object> entry = scan.next();
            String key = (String) entry.getKey();
            String[] split = key.split("::");
            //组装userLike 并放入list
            list.add(new UserLike(split[0],split[1],(Integer)entry.getValue()));
        }
        return list;
    }

    @Override
    public List<LikedCount> getLikedCountFromRedis() {
        Cursor<Map.Entry<Object,Object>> scan
                = redisTemplate.opsForHash().scan(RedisLikeUtil.MAP_KEY_USER_LIKED_COUNT, ScanOptions.NONE);
        ArrayList<LikedCount> list = new ArrayList<>();
        while (scan.hasNext()) {
            Map.Entry<Object, Object> entry = scan.next();
            list.add(new LikedCount((String)entry.getKey(),(Integer)entry.getKey()));
        }
        return list;
    }
}
