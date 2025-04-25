import React from 'react'
import { useParams } from 'react-router-dom'
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom'
const Container = styled.div`
    height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: #f0f4f8;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
`

const UserContainer = styled.div`
    display: flex;
    gap: 40px;
    padding: 40px;
    background-color: white;
    border-radius: 20px;
    box-shadow: 0 8px 24px rgba(0, 0, 0, 0.1);
`

const PhotoContainer = styled.div`
    flex-shrink: 0;
    border-radius: 15px;
    overflow: hidden;
    width: 300px;
    height: 400px;
    box-shadow: 0 6px 16px rgba(0, 0, 0, 0.15);
`

const Photo = styled.img`
    width: 100%;
    height: 100%;
    object-fit: cover;
`

const Table = styled.table`
    width: 100%; 
    border-collapse: collapse;
    font-size: 18px;
`

const Td = styled.td`
    padding: 16px;
    border-radius: 10px;
    font-weight: 500;
    text-align: left;
    &:first-child {
        width: 120px;
        font-weight: bold;
    }
  
`


const ButtonContainer = styled.div`
    margin-top: 30px;
    display: flex;
    gap: 20px;
    justify-content: center;
`

const ActionButton = styled.button`
    padding: 12px 24px;
    font-size: 16px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 10px;
    cursor: pointer;
    transition: background-color 0.3s;
    
    &:hover {
        background-color: #0056b3;
    }
`

const UserDetail = ({ userData , onDeleteUser}) => {
    const { id } = useParams();
   const navigate = useNavigate(); // useNavigate 사용

    const user = userData.find(user => user.id === parseInt(id));


    if (!user) {
        return <div>해당 유저를 찾을 수 없습니다.</div>;
    }

    const handleDelete = () => {
        onDeleteUser(parseInt(id));  
        navigate('/'); 
    };
    return (

        <>
        <Container>
            <div>
                <UserContainer>
                    <PhotoContainer>
                        <Photo src={user.photo} alt="프로필 이미지" />
                    </PhotoContainer>
                    <Table>
                        <tbody>
                            <tr>
                                <Td>이름:</Td>
                                <Td>{user.name}</Td>
                            </tr>
                            <tr>
                                <Td>나이:</Td> 
                                <Td>{user.age}</Td>
                            </tr>
                            <tr>
                                <Td>온라인 여부:</Td>
                                <Td>{user.isOnline ? '🟢 온라인' : '🔴 오프라인'}</Td>
                            </tr>
                        </tbody>
                    </Table>
                </UserContainer>
                <ButtonContainer>
                    <ActionButton onClick={handleDelete}>삭제</ActionButton>
                    <ActionButton onClick={() => navigate(-1)}>뒤로가기</ActionButton>
                </ButtonContainer>
            </div>
        </Container>
    </>

    )
}

export default UserDetail