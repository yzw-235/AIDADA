import React from 'react'
import { View } from '@tarojs/components'
import { AtRadio, AtButton } from 'taro-ui'
import "taro-ui/dist/style/components/radio.scss";
import "taro-ui/dist/style/components/icon.scss";
import Taro from '@tarojs/taro';
import './index.scss';
import GlobalFooter from "../../components/GlobalFooter/index";
import questions from "../../data/questions.json";

export default () => {

  // 当前题目
  const [currentQuz, setCurrentQuz ] = React.useState(questions[0])
  
  const questionOptions = currentQuz.options.map(e => ({
    label: `${e.key}.${e.value}`, value: e.key
  }))
  // 当前题目序号（从1开始）
  const [current, setCurrent] = React.useState<number>(1)
  const [currentAnswer, setCurrentAnswer] = React.useState<string>();
  const [answerList] = React.useState<string[]>([]);
  function handleNextClick(event) {
    setCurrent((prev) => prev + 1);
  }
  function handlePrevClick(event) {
    setCurrent((prev) => prev - 1);
  }
  function handleClickAnswer(value) {
    setCurrentAnswer(value);
    answerList[current - 1] = value;
  }
  React.useEffect(()=>{
    setCurrentQuz((prev)=> questions[current - 1])
    setCurrentAnswer((prev) => answerList[current - 1])
  }, [current])
  return (
    <View className='doQuestionPage'>

      <View className='at-article__h2 title'>{current}、{currentQuz.title}</View>
      <View className='options-wrapper'>
        <AtRadio
          options={questionOptions}
          value={currentAnswer}
          onClick={handleClickAnswer}
        />
      </View>


      {
        current < (questions.length ) && <AtButton onClick={handleNextClick} type='primary' disabled={!currentAnswer} circle className='controlBtn'>下一题</AtButton>
      }
      {

        current == (questions.length) && <AtButton type='primary' circle className='controlBtn' onClick={() => {
          Taro.setStorageSync("answerList", answerList);
          Taro.navigateTo({
          url:"/pages/result/index"
        })}
      }
        >查看结果</AtButton>
      }
      {
        current > 1 && <AtButton onClick={handlePrevClick} circle className='controlBtn'>上一题</AtButton>
      }
      <GlobalFooter />
    </View>
  )

}
