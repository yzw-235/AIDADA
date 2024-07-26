package com.wyz.aidada.model.dto.question;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 编辑题目请求
 *
 * @author
 * @from  
 */
@Data
public class QuestionEditRequest implements Serializable {

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