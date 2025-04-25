import { useState } from 'react'
import './App.css'
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom'
import UserList from './pages/UserList'
import UserDetail from './pages/UserDetail'
import UserRegistration from './pages/UserRegistration'
import NotFound from './pages/NotFound'

function App() {
  const [userData, setUserData] = useState([
    {
      id: 1,
      photo: "https://i.pinimg.com/236x/ff/c6/77/ffc677a9a36b0baec309d8ba3514fa01.jpg",
      name: "이지묵",
      age: 12,
      isOnline: true
    },
    {
      id: 2,
      photo: "https://i.pinimg.com/736x/fb/c4/82/fbc4827aa2fbffe857b06354c95e3ae3.jpg",
      name: "김지묵",
      age: 19,
      isOnline: true
    },
    {
      id: 3,
      photo: "https://i.pinimg.com/736x/aa/fe/47/aafe477f00612f904a84c224521ad19c.jpg",
      name: "박지묵",
      age: 21,
      isOnline: false
    },
    {
      id: 4,
      photo: "https://i.pinimg.com/736x/d6/5f/21/d65f21928518b391ecb4eb85a1932f4f.jpg",
      name: "최지묵",
      age: 25,
      isOnline: false
    },
    {
      id: 5,
      photo: "https://i.pinimg.com/736x/d0/8c/14/d08c14ed55d5ac1429e6ccf7fe403ad4.jpg",
      name: "정지묵",
      age: 30,
      isOnline: true
    }
  ]);

  const handleAddUser = (newUser) => {
    setUserData(prev => [...prev, newUser]);
  };

  const handleDeleteUser = (id) => {
    setUserData(prev => prev.filter(user => user.id !== parseInt(id)));
  };

  return (
    <div className="app-container">
      <BrowserRouter>
        <nav>
          <Link to="/" className="nav-link">HOME</Link>
        </nav>
        <Routes>
          <Route path="/" element={<UserList userData={userData} />} />
          <Route path="/user/:id" element={<UserDetail userData={userData} onDeleteUser={handleDeleteUser}/>} />
          <Route path="/user" element={<UserRegistration onAddUser={handleAddUser} />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </BrowserRouter>
    </div>
  )
}

export default App
