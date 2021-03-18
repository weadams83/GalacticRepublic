import styled from "styled-components";

export const StyledLogin = styled.div`
  height: 100vh;
  background: #f8f8f8;
  display: grid;
  place-items: center;
  .login-container {
    padding: 100px;
    text-align: center;
    background-color: white;
    border-radius: 2px;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
  }
  .hide {
    display: none;
  }
`;


export const SignUpB = styled.button`
display: inline-block;
justify-content: center;
margin: 50px 0px -10px 0px;
padding: 5px 5px 5px 5px ;
border-radius: 5px;
border: 1px solid #ccc;
`
