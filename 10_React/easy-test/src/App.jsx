// import './App.css'
import { BrowserRouter , Routes, Route} from "react-router-dom";
import "./Reset.css";
import styled from "styled-components";
import Header from "./components/Header";
import Home from "./pages/Home";
import UserDetail from "./pages/UserDetail";
import UserAdd from "./pages/UserAdd";
import NotFound from "./pages/NotFound";
const AppContainer = styled.div`
  min-height: 100vh;
  width: 100%;
  display: flex;
  flex-direction: column;
  box-sizing: border-box;
`;

function App() {
  return (
    <>
      <BrowserRouter>
        <AppContainer>
          <Header/>
          <Routes>
            <Route path="/" element={<Home/>}/>
            <Route path="/user/:id" element={<UserDetail/>}/>
            <Route path="/user" element={<UserAdd/>}/>
            <Route path="*" element={<NotFound/>}/>
          </Routes>
        </AppContainer>
      </BrowserRouter>
    </>
  );
}

export default App;
