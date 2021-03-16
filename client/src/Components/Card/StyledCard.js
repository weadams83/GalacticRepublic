import styled from 'styled-components';

export const StyledCard = styled.div`
background: lightgray;
padding: 2px;
color: black;
transition: all .4s ease;
&:hover{
    transform: size(1.10);
}
border-radius: 2px;
.editButton{
    position: absolute;
    float: left;
}
.viewButton{
    position: absolute;
    float: right;
}
`