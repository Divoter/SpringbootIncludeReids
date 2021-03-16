package util;

public class RedisLikeUtil {

    public static final String MAP_KEY_USER_LIKED = "map_key_user_liked";

    public static final String MAP_KEY_USER_LIKED_COUNT = "map_key_user_liked_count";

    /**
     * 拼接被点赞用户的id和点赞用户的id作为key.
     * e.g. 111::222
     * @param likedPostId 点赞用户id
     * @param likeUserId 被点赞用户id
     * @return
     */
    public static String getUserLiekdKey(String likedPostId, String likeUserId){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(likedPostId).append("::").append(likedPostId);
        return  stringBuilder.toString();
    }
}
