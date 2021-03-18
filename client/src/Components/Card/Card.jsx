import Button from "../Button/Button";
import { StyledCard } from "./StyledCard";

const Card = (props) => {
  return (
    <StyledCard>
      <p>{props.name}</p>
      <div className="buttons">
        <div className="button">
          <Button name="Edit"></Button>
        </div>
        <div className="button">
          <Button name="View"></Button>
        </div>
      </div>
    </StyledCard>
  );
};

export default Card;
