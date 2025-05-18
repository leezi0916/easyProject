import { useState } from 'react';
import './App.css';
import JavaScript from './components/JavaScript';
import Products from './components/Products';
import Style from './components/Style';
import Hello from './components/Hello';
import Heading from './components/Heading';
import VideoList from './components/VideoList';
import LifecycleText from './components/LifecycleText';
import Comment from './components/Comment';
import CommentList from './components/CommentList';
import UseStateTest from './components/useState/UseStateTest';
import SignUp from './components/useState/SignUp';
import LandingPage from './components/useState/LandingPage';
import UseRefTest from './components/useRef/UseRefTest';
import UseRefScroll from './components/useRef/UseRefScroll';
import UseMemoTest from './components/useMemo/UseMemoTest';
import UseCallbackTest from './components/useCallback/UseCallbackTest';
import UseEffectTest from './components/useEffect/useEffectTest';
import EffectView from './components/useEffect/EffectView';
import BlackOrWhite from './components/useContext/BlackOrWhite';
import MyForm from './components/customHook/MyForm';
import ToggleBox from './components/customHook/ToggleBox';
import { UserProvider } from './components/useContext/UserContext';
import Header from './components/useContext/Header';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Profile from './pages/Profile'; // Profile 컴포넌트 import
import Home from './pages/Home';
import About from './pages/About';
import NotFound from './pages/NotFound';
import styled, { ThemeProvider } from 'styled-components';
import CounterDisplay from './components/CounterDisplay';
import CounterControlls from './components/CounterControlls';
import TodoList from './components/TodoList';
import HomePage from './pages/HomePage';
import PostListPage from './pages/PostListPage';
import IconButtons from './components/IconButtons';
import GlobalStyle from './GlobalStyle';
import { darkTheme, lightTheme } from './theme';
import ThemeBox from './components/ThemeBox';
import { toast, ToastContainer } from 'react-toastify';
import { performToast } from './utils/performToast';
import SimpleForm from './components/SimpleForm';
import LoaderDemo from './components/LoaderDemo';
import TodoList1 from './components/TodoList1';
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
// }]

const AppContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  width: 100vw;
  padding: 24px;
  text-align: center;
  transition: all 0.3s;
`;
const Section = styled.section`
  width: 100%;
  max-width: 800px;
  margin: 0 auto;
  padding: 18px;
  border-radius: 8px;
  margin-bottom: 20px;
`;

// toast.success('요청에 성공하였습니다.');
// toast.error('요청에 실패하였습니다.');
// toast.warning('요청이 올바르지 않습니다.');
performToast({ msg: '요청에 성공하였습니다.', type: 'success' });

setTimeout(() => {
  performToast({ msg: '요청에 성공하였습니다.', type: 'success' });
}, 1000);

setTimeout(() => {
  performToast({ msg: '요청에 실패하였습니다.', type: 'error' });
}, 2000);

setTimeout(() => {
  performToast({ msg: '요청이 올바르지 않습니다.', type: 'warn' });
}, 3000);

function App() {
  const [isDark, setIsDark] = useState(false);
  const toggleTheme = () => setIsDark(!isDark);

  const apiUrl = import.meta.env.VITE_API_URL;
  console.log(apiUrl);
  return (
    <>
      {/* <JavaScript /> */}
      {/* <Style /> */}
      {/* <Products /> */}

      {/* <Heading type="h2" /> */}
      {/* <Heading /> */}
      {/* <Heading>무엇을 도와드릴까요?</Heading> */}
      {/* <Hello /> */}
      {/* <VideoList videos={videoData} /> */}

      {/* <LifecycleText /> */}
      {/* <Comment message={"안녕하세요"} /> */}
      {/* <CommentList /> */}
      {/* <UseStateTest /> */}
      {/* <SignUp /> */}
      {/* <LandingPage /> */}

      {/* <UseRefTest /> */}
      {/* <UseRefScroll /> */}
      {/* <UseMemoTest /> */}
      {/* <UseCallbackTest /> */}
      {/* <EffectView /> */}

      {/* <BlackOrWhite /> */}
      {/* <MyForm /> */}
      {/* <ToggleBox /> */}
      {/* <UserProvider>
        <Header />
      </UserProvider> */}

      {/* Link : a태그와 동일한 역할을 하지만 react-router-dom을 활용해 spa방식으로 자연스럽게 화면 전환 */}
      {/* <BrowserRouter>
        <nav style={{ marginBottom: 12 }}>

          <Link to="/" style={{ marginRight: 12 }}>홈</Link>
          <Link to="/about" style={{ marginRight: 12 }}>소개</Link>
          <Link to="/profile/최지원">프로필</Link>
        </nav>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/about/" element={<About />} />
          <Route path="/profile/:username" element={<Profile />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </BrowserRouter> */}

      {/* <AppContainer>
        <Section>
          <h2>Zustand 전역 상태 관리</h2>
          <CounterDisplay/>
          <CounterControlls/>
        </Section>
        <Section>
          <h2>zustand TodoList</h2>
          <TodoList/>
        </Section>
      </AppContainer> */}

      {/* <BrowserRouter>
        <Routes>
          <Route path ='/' element={<HomePage/>}/>
          <Route path ='/posts' element={<PostListPage/>}/>
        </Routes>
      </BrowserRouter> */}

      {/* <IconButtons></IconButtons> */}
      {/* <ThemeProvider theme={isDark ? darkTheme : lightTheme}>
        <GlobalStyle />
        <ThemeBox onToggleTheme={toggleTheme} />
      </ThemeProvider> */}
      {/* <ToastContainer /> */}
      {/* <SimpleForm /> */}
      {/* <LoaderDemo /> */}
      <TodoList1 />
    </>
  );
}

export default App;
