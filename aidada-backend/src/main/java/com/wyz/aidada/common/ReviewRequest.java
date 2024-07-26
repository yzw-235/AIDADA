package com.wyz.aidada.common;

import java.io.Serializable;

import lombok.Data;

/**
 * 审核请求
 * @author 吴彦焯
 * @version v1.4.0
 * @date 2024/7/26
 */

@Data
public class ReviewRequest implements Serializable {
    /**
     * id
     */
    private Long id;
    /**
     * 审核状态 0-待审核，1-通过， 2-拒绝
     */
    private Integer reviewStatus;
    /**
     * 审核信息
     */
    private String reviewMessage;

    private static final long serialVersionUID = 1L;
}
