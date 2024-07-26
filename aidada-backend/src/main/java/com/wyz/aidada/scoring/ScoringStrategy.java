package com.wyz.aidada.scoring;

import com.wyz.aidada.model.entity.App;
import com.wyz.aidada.model.entity.UserAnswer;

import java.util.List;

/**
 * @author 吴彦焯
 * @version v1.4.0
 * @date 2024/7/26
 */
public interface ScoringStrategy {

    /**
     * 执行评分
     *
     * @param choices
     * @param app
     * @return
     * @throws Exception
     */
    UserAnswer doScore(List<String> choices, App app) throws Exception;
}

