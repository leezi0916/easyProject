import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'
import styled from 'styled-components'
import { useUser } from '../context/UserContext';
import useUserStore from '../store/useUserStore';

const Wrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh; /* 화면 전체 높이 */
`;

const FormContainer = styled.div`
  max-width: 400px;
  margin: 50px auto;
  padding: 30px;
  background-color: #f4f4f4;
  border-radius: 15px;
  box-shadow: 0 4px 12px rgba(0,0,0,0.1);
`;

const Input = styled.input`
  width: 100%;
  padding: 10px;
  margin-top: 15px;
  font-size: 16px;
  border-radius: 8px;
  border: 1px solid #ccc;
  box-sizing: border-box;

  &:focus {
    outline: none;
    border-color: #4CAF50;
  }
`;

const Label = styled.label`
  display: block;
  margin-top: 20px;
  font-size: 14px;
  font-weight: bold;
`;

const SubmitButton = styled.button`
  width: 100%;
  padding: 12px;
  margin-top: 30px;
  font-size: 18px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 10px;
  cursor: pointer;

  &:hover {
    background-color: #45a049;
  }
`;

const UserRegistration = () => {
    // const { addUser } = useUser();
    const {addUser} = useUserStore();
    const [name, setName] = useState('');
    const [age, setAge] = useState('');
    const [isOnline, setIsOnline] = useState(false);
    const [photo, setPhoto] = useState('');
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();  // 새로고침 방지
        if (!name || !age) {
            alert("모든 필드를 입력해주세요.");
            return;
        }

        const newUser = {
            id: Date.now(),
            name,
            age: parseInt(age),
            isOnline,
            photo: photo || 'https://i.pinimg.com/236x/45/88/71/458871857eda2c72e1aa63fcea2a1392.jpg'
        };

        addUser(newUser);
        setName('');
        setAge('');
        setIsOnline(false);
        setPhoto('');
        navigate('/');
    };

    return (
        <Wrapper>
            <FormContainer>
                <Input
                    placeholder="이름"
                    value={name}
                    onChange={e => setName(e.target.value)}
                />
                <Input
                    type="number"
                    placeholder="나이"
                    value={age}
                    onChange={e => setAge(e.target.value)}
                />
                <Label>
                    <input
                        type="checkbox"
                        checked={isOnline}
                        onChange={e => setIsOnline(e.target.checked)}
                    />
                    &nbsp;온라인 여부
                </Label>
                <Input
                    placeholder="사진 URL을 추가하지 않으면 기본이미지"
                    value={photo}
                    onChange={e => setPhoto(e.target.value)}
                />
                <SubmitButton onClick={handleSubmit}>등록</SubmitButton>
            </FormContainer>
        </Wrapper>
    );
};

export default UserRegistration;
