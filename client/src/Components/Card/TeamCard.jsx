import axios from "axios";
import { Fragment, useEffect, useState } from "react";
import Button from "../Button/Button";
import { StyledCard } from "./StyledCard";

import { credentials } from "../../utils/Credentials";

const TeamCard = (props) => {
  const [teams, setTeams] = useState([]);
  const [teamName, setTeamName] = useState("");
  const patchBody = {
    credentials,
    teamName,
  };

  const getTeams = () => {
    axios.get("http://localhost:8080/team").then((res) => {
      setTeams(res.data);
    });
  };

  const handleClick = (e) => {
    console.log(props.userName, patchBody);
    e.preventDefault();
    axios
      .patch(`http://localhost:8080/user/${props.userName}/team`, patchBody)
      .then((res) => {
        props.getUsers();
        setTeamName("");
      });
  };

  useEffect(() => {
    getTeams();
  }, []);

  const teamForm = (
    <Fragment>
      <form onSubmit={handleClick}>
        <select onChange={(e) => setTeamName(e.target.value)}>
          <option>Assign a team...</option>
          {teams.map((team) => (
            <option key={`${team.teamName}`} >{team.teamName}</option>
          ))}
        </select>
        <button name={`${props.userName}`}>+</button>
      </form>
    </Fragment>
  );
  return (
    <StyledCard>
      <h3>{props.name}</h3>
      {props.team !== undefined ? <p>{props.team}</p> : teamForm}
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

export default TeamCard;
