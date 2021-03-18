import styled from 'styled-components'

const StyledMemberPage = styled.div `
display: flex;
background: #f8f8f8;
.member-page-container {
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
.member-page-container .card {
  border: 1px solid red;
}
`
export default StyledMemberPage