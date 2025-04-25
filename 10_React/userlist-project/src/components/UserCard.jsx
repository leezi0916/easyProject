import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';

const Card = styled(Link)`
  display: block;
  width: 350px;
  background-color: #f9f9f9;
  border-radius: 20px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  text-decoration: none;
  color: inherit;
  transition: all 0.3s;
  &:hover {
    transform: translateY(-5px);
  }
`;

const Photo = styled.img`
  width: 100%;
  height: 300px;
  object-fit: cover;
`;

const Content = styled.div`
  padding: 16px 20px;
`;

const Name = styled.h2`
  font-size: 22px;
  margin-bottom: 8px;
  color: #333;
`;

const Info = styled.p`
  margin: 6px 0;
  font-size: 16px;
  color: #555;
`;

const Status = styled.span`
  color: ${props => props.$isOnline ? 'green' : 'red'};
`;

const UserCard = ({ user }) => {
  return (
    <Card to={`/user/${user.id}`}>
      <Photo src={user.photo} alt="유저 사진" />
      <Content>
        <Name>{user.name}</Name>
        <Info>나이: {user.age}세</Info>
        <Info>
          상태: <Status $isOnline={user.isOnline}>
            {user.isOnline ? "온라인 🟢 " : "오프라인 🔴 "}
          </Status>
        </Info>
      </Content>
    </Card>
  );
};

export default UserCard;
