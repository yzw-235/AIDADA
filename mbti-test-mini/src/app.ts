import { Component, PropsWithChildren } from 'react'
import "taro-ui/dist/style/components/article.scss" // 按需引入
import "taro-ui/dist/style/components/button.scss";
import './app.scss'

class App extends Component<PropsWithChildren>  {

  componentDidMount () {}

  componentDidShow () {}

  componentDidHide () {}

  // this.props.children 是将要会渲染的页面
  render () {
    return this.props.children
  }
}

export default App
