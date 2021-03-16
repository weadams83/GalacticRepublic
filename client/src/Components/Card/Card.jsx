
import Button from '../Button/Button';
import {StyledCard} from './StyledCard';

const Card = props => {
    return(
    <StyledCard>
        <p>
            {props.name}
        </p>
            <div className = "editButton">
                <Button name="Edit"></Button>
            </div>
            <div className = "viewButton">
                <Button name="View"></Button>
            </div>
    </StyledCard>
    )

}


export default Card
