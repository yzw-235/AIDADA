package com.wyz.aidada.scoring;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wyz.aidada.model.dto.question.QuestionContentDto;
import com.wyz.aidada.model.entity.App;
import com.wyz.aidada.model.entity.Question;
import com.wyz.aidada.model.entity.ScoringResult;
import com.wyz.aidada.model.entity.UserAnswer;
import com.wyz.aidada.model.vo.QuestionVO;
import com.wyz.aidada.service.QuestionService;
import com.wyz.aidada.service.ScoringResultService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import cn.hutool.json.JSONUtil;

/**
 * @author 吴彦焯
 * @version v1.4.0
 * @date 2024/7/26
 */
@ScoringStrategyConfig(appType = 0, scoringStrategy = 0)
public class CustomScoreScoringStrategy implements ScoringStrategy{
    @Resource
    private QuestionService questionService;

    @Resource
    private ScoringResultService scoringResultService;
    @Override
    public UserAnswer doScore(List<String> choices, App app) throws Exception {

        // 1. 根据id查询到题目和题目结果信息
        long appId = app.getId();
        Question question = questionService.getOne(
                Wrappers.lambdaQuery(Question.class)
                        .eq(Question::getAppId, app.getId()));

        List<ScoringResult> scoringResultList = scoringResultService.list(
                Wrappers.lambdaQuery(ScoringResult.class)
                        .eq(ScoringResult::getAppId, appId)
                        .orderByDesc(ScoringResult::getResultScoreRange)
        );

        // 2. 统计用户的得分
        int totalScore = 0;
        QuestionVO questionVO = QuestionVO.objToVo(question);
        List<QuestionContentDto> questionContentDtoList = questionVO.getQuestionContent();
        for (QuestionContentDto questionContentDto : questionContentDtoList) {
            for (String answer : choices) {
                for (QuestionContentDto.Option option : questionContentDto.getOptions()) {
                    if (option.getKey().equals(answer)) {
                        int score = Optional.of(option.getScore()).orElse(0);
                        totalScore += score;
                    }
                }
            }
        }



        // 3. 遍历每种评分结果，计算哪种结果的评分更高

        ScoringResult maxScoreResult = scoringResultList.get(0);

        for (ScoringResult result : scoringResultList) {
            if (totalScore >= result.getResultScoreRange()) {
                maxScoreResult = result;
                break;
            }
        }
        // 4. 构造返回值，填值
        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setAppId(appId);
        userAnswer.setAppType(app.getAppType());
        userAnswer.setScoringStrategy(app.getScoringStrategy());
        userAnswer.setChoices(JSONUtil.toJsonStr(choices));
        userAnswer.setResultId(maxScoreResult.getId());
        userAnswer.setResultName(maxScoreResult.getResultName());
        userAnswer.setResultDesc(maxScoreResult.getResultDesc());
        userAnswer.setResultPicture(maxScoreResult.getResultPicture());
        userAnswer.setResultScore(totalScore);
        return userAnswer;
    }
}
