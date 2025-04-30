import React , {useState} from 'react'
import styled from 'styled-components'
import UserCard from '../components/UserCard'
import { useNavigate } from 'react-router-dom'
import { useCallback } from 'react';
import { useUser } from '../context/UserContext';
import useUserStore from '../store/useUserStore';

const Container = styled.div`
    width: 100%;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
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
    justify-content: space-;
    align-items: center;
  
`
const SearchContainer = styled.div`
display: flex;
justify-content: end;
  width: 70%;
`

const ButtonContainer = styled.div`
  width: 30%;
`
const SearchInput = styled.input`
  width: 60%;
  padding: 10px;
  margin-left: 10px;
  border: 1px solid #e0e0e0;
  border-radius: 4px;
  text-align: center;
  font-size: 16px;
`

const UserList = () => {
  // const { users } = useUser();
  const { users } = useUserStore();
  const navigate = useNavigate();
  const [searchTerm, setSearchTerm] = useState('');

  const filteredUsers = users.filter(user => 
    user.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div>
      <InputContainer>
      <SearchContainer>
      <SearchInput 
        type="text" 
        placeholder='사용자 검색...'
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}  
      />
      </SearchContainer>
      <ButtonContainer>
      <RegisterButton onClick={() => navigate('/user')}>
          유저 등록
        </RegisterButton>
      </ButtonContainer>
        
      </InputContainer>

      <Container>
        {filteredUsers.map((user) => (
          <UserCard key={user.id} user={user} />
        ))}
      </Container>

    </div>
  );
};

export default UserList