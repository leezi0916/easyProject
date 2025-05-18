import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";
const Nav = styled.nav`
  background-color: #171a21;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
  display: flex;
  gap: 20px;
  align-items: center;
  justify-content: center;
  position: sticky;
  top: 0;
  z-index: 1000;
  width: 100%;
  height: 100px;

  a {
    text-decoration: none;
    font-weight: 600;
    transition:
      color 0.3s,
      border-bottom 0.3s;
    padding-bottom: 4px;
    font-size: 32px;
    display: flex;
    align-items: center;
    gap: 10px;
    color: #e5e5e5;
  }
`;

const Header = () => {
  return (
    <Nav>
      <Link to="/" className="nav-link">
        Home
      </Link>
    </Nav>
  );
};

export default Header;

