import styled from "styled-components"

export const StyledUsers = styled.div`
  display: flex;
  background: #f8f8f8;
  .title h2 {
    width: 100%;
  }
  .notification {
    border: 1px solid red;
    padding: 0 6px;
    border-radius: 100px;
    width: 30px;
    background: red;
    color: white;
  }
  .users-container {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  .card-container {
    display: flex;
    flex-wrap: wrap;
    width: 100%;
  }
  
`