import React from 'react'
import styled from 'styled-components';

const Table = styled.table`
    width: 400px; 
    color: black;
    border: 1px solid #dddddd;
    border-radius: 15px;

`
const Photo = styled.img`
    border-radius: 15px;
    width: 100%;
    height: 400px;
`

const Td = styled.td`
    color: black;
    padding: 12px;
    border: 1px solid #dddddd;
    border-radius: 10px;
    background-color: #deefff;
    
`
const ProfileCard = ({ profile }) => {
    return (
        <Table>
            <tbody>
                <tr>
                    <Td colSpan="1">
                        <Photo src={profile.photo} alt="프로필 이미지" />
                    </Td>
                </tr>
                <tr>
                    <Td>{profile.name}</Td>
                </tr>
                <tr>
                    <Td>{profile.age}</Td>
                </tr>
                <tr>
                    <Td>{profile.isOnline ? "🟢 온라인 상태입니다." : "🔴 오프라인 상태입니다."}</Td>
                </tr>
            </tbody>

        </Table>

    )
}

export default ProfileCard