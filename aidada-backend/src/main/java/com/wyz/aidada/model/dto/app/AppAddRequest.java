package com.wyz.aidada.model.dto.app;

import java.io.Serializable;

import lombok.Data;

/**
 * 创建应用请求
 *
 * @author
 * @from  
 */
@Data
public class AppAddRequest implements Serializable {



    /**
     * 应用名
     */
    private String appName;

    /**
     * 应用描述
     */
    private String appDesc;

    /**
     * 应用图标
     */
    private String appIcon;

    /**
     * 应用类型（0-得分类，1-测评类）
     */
    private Byte appType;

    /**
     * 评分策略（0-自定义，1-AI）
     */
    private Byte scoringStrategy;



    private static final long serialVersionUID = 1L;
}