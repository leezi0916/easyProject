import React from 'react'
import styled from 'styled-components'
import UserCard from '../components/UserCard'
import { useNavigate } from 'react-router-dom'
import { useCallback } from 'react';
import { useUser } from '../context/UserContext';

const Container = styled.div`
    width: 100%;
    display: flex;
    flex-wrap: wrap;
    margin-left: 45px;
    gap: 16px;
    border-radius: 15px;
`

const RegisterButton = styled.button`
    margin: 16px;
    padding: 10px 20px;
    font-size: 16px;
    border: none;
    border-radius: 10px;
    background-color: #4CAF50;
    color: white;
    cursor: pointer;
    width: 200px;
    &:hover {
        background-color: #45a049;
    }
`

const InputContainer = styled.div`
    width: 100%;
    display: flex;
    justify-content: end;
    margin-top: 20px;
`
const UserList = () => {
  const { users } = useUser();
  const navigate = useNavigate();

  
  return (
    <div>
      <InputContainer>
        <RegisterButton onClick={() => navigate('/user')}>
          유저 등록
        </RegisterButton>
      </InputContainer>

      <Container>
        {users.map((user) => (
          <UserCard key={user.id} user={user} />
        ))}
      </Container>

    </div>
  );
};

export default UserList