import Card from "../Card/Card";
import { StyledUsers } from "./StyledUsers";
import dummyData from "../../DummyData.json";
import Navbar from "../Navbar/Navbar";
import { Fragment, useEffect, useState } from "react";
import axios from "axios";
import TeamCard from "../Card/TeamCard";

export const Users = () => {
  const [users, setUsers] = useState([]);
  const [teamlessCount, setTeamlessCount] = useState(0);

  const getUsers = () => {
    axios
      .get("http://localhost:8080/user")
      .then((res) => {
        setUsers(res.data.filter((user) => !user.isDeleted));
        setTeamlessCount(
          res.data.filter(
            (user) => user.associatedTeam === null && !user.isDeleted
          ).length
        );
      })
      .catch((err) => console.log(err));
  };

  useEffect(() => {
    getUsers();
  }, []);

  return (
    <Fragment>
      <Navbar />
      <StyledUsers>
        <div className="users-container">
          <div className="title">
            <h2>
              Users
              {teamlessCount > 0 ? (
                <sup className="notification">{teamlessCount}</sup>
              ) : null}
            </h2>
          </div>
          <div className="card-container">
            {users.map((user) => (
              <TeamCard
                key={`${user.userName}-${user.lastName}`}
                name={`${user.firstName} ${user.lastName}`}
                team={user.associatedTeam?.teamName}
                userName={user.userName}
                getUsers={getUsers}
              />
            ))}
          </div>
        </div>
      </StyledUsers>
    </Fragment>
  );
};
