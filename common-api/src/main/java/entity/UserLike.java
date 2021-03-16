package entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projectenum.LikedStatusEnum;

/**
 * 点赞信息数据实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLike {

    private Integer id;
    /**
     * 点赞人
     */
    private String likedPostId;
    /**
     * 被点赞人
     */
    private String likeUserId;

    /**
     * 状态（点赞or未点赞）
     * 默认未点赞
     */
    private Integer status = LikedStatusEnum.UNLIKE.getCode();

    public UserLike(String likedPostId, String likeUserId, Integer status) {
        this.likedPostId = likedPostId;
        this.likeUserId = likeUserId;
        this.status = status;
    }
}
