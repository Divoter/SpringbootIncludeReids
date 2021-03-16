package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 点赞计数实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikedCount {

    /**
     * 被点赞人
     */
    private String userLikeId;

    /**
     * 点赞数量
     */
    private Integer count;

}
