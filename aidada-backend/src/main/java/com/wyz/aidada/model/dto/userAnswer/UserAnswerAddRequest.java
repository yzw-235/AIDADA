package com.wyz.aidada.model.dto.userAnswer;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 创建用户答案请求
 *
 * @author
 * @from  
 */
@Data
public class UserAnswerAddRequest implements Serializable {


    /**
     * 应用 id
     */
    private Long appId;
    /**
     * 用户答案（JSON 数组）
     */
    private List<String> choices;
    private static final long serialVersionUID = 1L;
}