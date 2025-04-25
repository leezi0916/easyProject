import React from 'react'
import styled from 'styled-components';

const Table = styled.table`
    width: 350px;
    border-collapse: separate;
    border-spacing: 0;
    background-color: #f9f9f9;
    border-radius: 20px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    overflow: hidden;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
`;

const Photo = styled.img`
    width: 100%;
    height: 250px;
    object-fit: cover;
    border-bottom: 1px solid #e0e0e0;
    border-radius: 15px;
`;

const Td = styled.td`
    padding: 16px 20px;
    font-size: 16px;
    font-weight: 500;
    text-align: center;
    color: #333;
    background-color: #fff;
    border: 1px solid #e1e1e1;
    
`;
const UserCard = React.memo(({ user }) => {
    return (
        <Table>
            <tbody>
                <tr>
                    <Td colSpan="1">
                        <Photo src={user.photo} alt="프로필 이미지" />
                    </Td>
                </tr>
                <tr>
                    <Td>{user.name}</Td>
                </tr>
                <tr>
                    <Td>{user.age}</Td>
                </tr>
                <tr>
                    <Td>{user.isOnline ? "🟢 온라인 상태입니다." : "🔴 오프라인 상태입니다."}</Td>
                </tr>
            </tbody>

        </Table>
    );
});

export default UserCard