import { useState } from 'react'
import './App.css'
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom'
import styled from 'styled-components';
import UserList from './pages/UserList';
import UserDetail from './pages/UserDetail';
import UserRegistration from './pages/UserRegistration';
import NotFound from './pages/NotFound';
import { UserProvider } from './context/UserContext';


const AppContainer = styled.div`
  min-height: 100vh;
  width: 100%;
  display: flex;
  flex-direction: column;
`;

function App() {

  return (
    <BrowserRouter>
      <UserProvider>
        <AppContainer>
          <nav>
            <Link to="/" className="nav-link">HOME</Link>
          </nav>
          
          <Routes>
            <Route path="/" element={<UserList />} />
            <Route path="/user/:id" element={<UserDetail />} />
            <Route path="/user" element={<UserRegistration />} />
            <Route path="*" element={<NotFound />} />
          </Routes>
        </AppContainer>
      </UserProvider>
    </BrowserRouter>
  );
}

export default App
