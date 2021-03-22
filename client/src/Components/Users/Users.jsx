import { StyledUsers } from "./StyledUsers";
import Navbar from "../Navbar/Navbar";
import { Fragment, useEffect, useState } from "react";
import axios from "axios";
import TeamCard from "../Card/TeamCard";

export const Users = () => {
  const [users, setUsers] = useState([]);
  const [teamlessCount, setTeamlessCount] = useState(0);

  const getUsers = () => {
    const companyName = JSON.parse(localStorage.getItem("currentUser"))
      .userCompany.companyName;
    axios
      .get(`http://localhost:8080/company/${companyName}`)
      .then((res) => {
        console.log("users", res.data.users);
        setUsers(res.data.users.filter((user) => !user.isDeleted));
        setTeamlessCount(
          res.data.users.filter(
            (user) =>
              user.associatedTeam === null &&
              !user.isDeleted &&
              user.firstName !== null
          ).length
        );
      })
      .catch((err) => console.log(err));
  };

  console.log(JSON.parse(localStorage.getItem("currentUser")));

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
