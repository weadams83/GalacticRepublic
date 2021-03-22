
import { StyledButton } from './StyledButton';

const Button = props => {
return(
    <div className = "button">
        <StyledButton>
            {props.name}
        </StyledButton>
    </div>
)
}
//edit button to get to page to actually edit projects


export default Button
