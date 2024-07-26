package com.wyz.aidada.model.dto.question;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 创建题目请求
 *
 * @author
 * @from  
 */
@Data
public class QuestionAddRequest implements Serializable {


    /**
     * 题目内容（json格式）
     */
    private List<QuestionContentDto> questionContent;

    /**
     * 应用 id
     */
    private Long appId;



    private static final long serialVersionUID = 1L;
}