import React from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";
const Wrapper = styled.div`
    width: 100%;
    height: 100vh;
    display: flex;
    justify-content: center;
`
const ButtonContainer = styled.div`
    height: 500px;
    width: 500px;
    background: #dfdfdf;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 20px;

`
const LinkButton = styled(Link)`
    font-size: 24px;
    font-weight: 500;
    background: white;
    padding: 10px;
    border-radius: 8px;
    text-align: center;
`
const Home = () => {
  return (
    <Wrapper>
      <ButtonContainer>
        <LinkButton to={"/user"}>회원가입</LinkButton>
        <LinkButton to={"/user/:id"}>마이페이지</LinkButton>
      </ButtonContainer>
    </Wrapper>
  );
};

export default Home;
