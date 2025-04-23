import { useState } from 'react'
import './App.css'
import JavaScript from './components/JavaScript'
import Products from './components/Products'
import Style from './components/Style'
import Hello from './components/Hello'
import Heading from './components/Heading'
import VideoList from './components/VideoList'
import LifecycleText from './components/LifecycleText'
import Comment from './components/Comment';
import CommentList from './components/CommentList'
import UseStateTest from './components/useState/UseStateTest'
import SignUp from './components/useState/SignUp'
import LandingPage from './components/useState/LandingPage'
import UseRefTest from './components/useRef/UseRefTest'
import UseRefScroll from './components/useRef/UseRefScroll'

// const videoData = [{
//   sumbnail:"https://i.ytimg.com/an_webp/ugR9MOkqK_g/mqdefault_6s.webp?du=3000&sqp=CKi9h8AG&rs=AOn4CLCHATRFJmBeirLG2dsfaKqEhEvgGw",
//   title: "빵빵이와 옥지의 진솔한 대화(물리)",
//   logo: "https://yt3.ggpht.com/wYRkjS6E0mMZ-np2jNwjVaCNzQMpxs1VkdQ_p25oe0aaSj0awd7f9xRUcrwI6rVOQE7kjZQ6l4A=s48-c-k-c0x00ffffff-no-rj",
//   channelName: "빵빵이의 일상",
//   views: '8.3만',
//   date: "2시간 전",
// },{
//   sumbnail:"https://i.ytimg.com/an_webp/ugR9MOkqK_g/mqdefault_6s.webp?du=3000&sqp=CKi9h8AG&rs=AOn4CLCHATRFJmBeirLG2dsfaKqEhEvgGw",
//   title: "빵빵이와 옥지의 진솔한 대화(물리)",
//   logo: "https://yt3.ggpht.com/wYRkjS6E0mMZ-np2jNwjVaCNzQMpxs1VkdQ_p25oe0aaSj0awd7f9xRUcrwI6rVOQE7kjZQ6l4A=s48-c-k-c0x00ffffff-no-rj",
//   channelName: "빵빵이의 일상",
//   views: '8.3만',
//   date: "2시간 전",
// },{
//   sumbnail:"https://i.ytimg.com/an_webp/ugR9MOkqK_g/mqdefault_6s.webp?du=3000&sqp=CKi9h8AG&rs=AOn4CLCHATRFJmBeirLG2dsfaKqEhEvgGw",
//   title: "빵빵이와 옥지의 진솔한 대화(물리)",
//   logo: "https://yt3.ggpht.com/wYRkjS6E0mMZ-np2jNwjVaCNzQMpxs1VkdQ_p25oe0aaSj0awd7f9xRUcrwI6rVOQE7kjZQ6l4A=s48-c-k-c0x00ffffff-no-rj",
//   channelName: "빵빵이의 일상",
//   views: '8.3만',
//   date: "2시간 전",
// },{
//   sumbnail:"https://i.ytimg.com/an_webp/ugR9MOkqK_g/mqdefault_6s.webp?du=3000&sqp=CKi9h8AG&rs=AOn4CLCHATRFJmBeirLG2dsfaKqEhEvgGw",
//   title: "빵빵이와 옥지의 진솔한 대화(물리)",
//   logo: "https://yt3.ggpht.com/wYRkjS6E0mMZ-np2jNwjVaCNzQMpxs1VkdQ_p25oe0aaSj0awd7f9xRUcrwI6rVOQE7kjZQ6l4A=s48-c-k-c0x00ffffff-no-rj",
//   channelName: "빵빵이의 일상",
//   views: '8.3만',
//   date: "2시간 전",
// },{
//   sumbnail:"https://i.ytimg.com/an_webp/ugR9MOkqK_g/mqdefault_6s.webp?du=3000&sqp=CKi9h8AG&rs=AOn4CLCHATRFJmBeirLG2dsfaKqEhEvgGw",
//   title: "빵빵이와 옥지의 진솔한 대화(물리)",
//   logo: "https://yt3.ggpht.com/wYRkjS6E0mMZ-np2jNwjVaCNzQMpxs1VkdQ_p25oe0aaSj0awd7f9xRUcrwI6rVOQE7kjZQ6l4A=s48-c-k-c0x00ffffff-no-rj",
//   channelName: "빵빵이의 일상",
//   views: '8.3만',
//   date: "2시간 전",
// },{
//   sumbnail:"https://i.ytimg.com/an_webp/ugR9MOkqK_g/mqdefault_6s.webp?du=3000&sqp=CKi9h8AG&rs=AOn4CLCHATRFJmBeirLG2dsfaKqEhEvgGw",
//   title: "빵빵이와 옥지의 진솔한 대화(물리)",
//   logo: "https://yt3.ggpht.com/wYRkjS6E0mMZ-np2jNwjVaCNzQMpxs1VkdQ_p25oe0aaSj0awd7f9xRUcrwI6rVOQE7kjZQ6l4A=s48-c-k-c0x00ffffff-no-rj",
//   channelName: "빵빵이의 일상",
//   views: '8.3만',
//   date: "2시간 전",
// },{
//   sumbnail:"https://i.ytimg.com/an_webp/ugR9MOkqK_g/mqdefault_6s.webp?du=3000&sqp=CKi9h8AG&rs=AOn4CLCHATRFJmBeirLG2dsfaKqEhEvgGw",
//   title: "빵빵이와 옥지의 진솔한 대화(물리)",
//   logo: "https://yt3.ggpht.com/wYRkjS6E0mMZ-np2jNwjVaCNzQMpxs1VkdQ_p25oe0aaSj0awd7f9xRUcrwI6rVOQE7kjZQ6l4A=s48-c-k-c0x00ffffff-no-rj",
//   channelName: "빵빵이의 일상",
//   views: '8.3만',
//   date: "2시간 전",
// },{
//   sumbnail:"https://i.ytimg.com/an_webp/-lbqhtDyfe4/mqdefault_6s.webp?du=3000&sqp=CJ60h8AG&rs=AOn4CLAtde60hz-u8zCIWVaYD1tZRsB5fQ",
//   title: "빵빵이의 황금벚꽃",
//   logo: "https://yt3.ggpht.com/wYRkjS6E0mMZ-np2jNwjVaCNzQMpxs1VkdQ_p25oe0aaSj0awd7f9xRUcrwI6rVOQE7kjZQ6l4A=s48-c-k-c0x00ffffff-no-rj",
//   channelName: "빵빵이의 일상",
//   views: '2만',
//   date: "4시간 전",
// }]

function App() {
  return (
    <>
      {/* <JavaScript/> */}
      {/* <Style /> */}
      {/* <Products /> */}
       {/* <Heading type="h2"/>
      <Heading/>
      <Heading>
        무엇을 도와드릴까요?
      </Heading>
      <Hello /> */}
      {/* <VideoList videos={videoData}/> */}
      {/* {isButton && <LifecycleText/>}
      <button onClick={toggleButton}>count없애기</button> */}

      {/* <Comment message={"안녕하세요"}/> */}
      {/* <CommentList /> */}
        {/* <UseStateTest /> */}
      {/* <SignUp />  */}
      {/* <LandingPage /> */}
      {/* <UseRefTest /> */}
      <UseRefScroll />
    </>
  )
}

export default App