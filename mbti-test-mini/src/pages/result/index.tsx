import { Component, PropsWithChildren } from 'react'
import { View, Image} from '@tarojs/components'
import { AtButton } from 'taro-ui'
import Taro from '@tarojs/taro'
import './index.scss'
import headerBg from "../../assets/headerBg.jpg"
import GlobalFooter from "../../components/GlobalFooter/index"
import questionResult from "../../data/question_results.json";
import questions from "../../data/questions.json";
import { getBestQuestionResult } from '../../utils/bizUtils'

export default class Index extends Component<PropsWithChildren> {
  componentDidMount() { }

  componentWillUnmount() { }

  componentDidShow() { }

  componentDidHide() { }

  render() {
    const answerList = Taro.getStorageSync("answerList");
    if (!answerList || answerList.length === 0) {
     Taro.showToast({
      title: "答案为空",
      icon: "error",
      duration: 3000
     }) 
    }

    const result = getBestQuestionResult(answerList,questions, questionResult);
    return (
      <View className='resultPage'>

        <View className='at-article__h1 title'>
          {result.resultName}
        </View>
        <View className='at-article__h2 subTitle'>
          {result.resultDesc}
        </View>
        <AtButton type='primary' circle className='enterBtn' onClick={() => Taro.reLaunch({
          url:"/pages/index/index"
        })}
        >返回主页</AtButton>
        <Image src={headerBg} className='headerBg' ></Image>
        <GlobalFooter />
      </View>
    )
  }
}
