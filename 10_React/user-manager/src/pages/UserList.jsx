import React from 'react'
import styled from 'styled-components'
import UserCard from '../components/UserCard'
import { useNavigate } from 'react-router-dom'
import { useCallback } from 'react';

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

const ButtonContainer = styled.div`
    width: 100%;
    display: flex;
    justify-content: end;
    margin-top: 20px;
`
const UserList = ({ userData }) => {
    const navigate = useNavigate();

    const handleUserClick = useCallback((id) => {
        navigate(`/user/${id}`);
    }, [navigate]);

    return (
        <div>
            <ButtonContainer>
                <RegisterButton onClick={() => navigate('/user')}>
                    유저 등록
                </RegisterButton>
            </ButtonContainer>

            <Container>
                {userData.map((user) => (
                    <div key={user.id} onClick={() => handleUserClick(user.id)} style={{ cursor: 'pointer' }}>
                        <UserCard user={user} />
                    </div>
                ))}
            </Container>
        </div>
    );
};

export default UserList