package com.wyz.aidada.model.dto.question;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 更新题目请求
 *
 * @author
 * @from  
 */
@Data
public class QuestionUpdateRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 内容
     */
    private List<QuestionContentDto> questionContent;

    private static final long serialVersionUID = 1L;
}