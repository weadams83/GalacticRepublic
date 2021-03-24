import { StyledForm, StyledUsers } from "./StyledUsers";
import Navbar from "../Navbar/Navbar";
import { Fragment, useCallback, useEffect, useState } from "react";
import axios from "axios";
import { UserCard } from "../Card/UserCard";
import { store } from "../../index";
import Button from "../Button/Button";

const initialTeamForm = {
  teamName: "",
  teamDescription: "",
};

export const Users = () => {
  const [usersWithoutRole, setUsersWithoutRole] = useState([]);
  const [allUsers, setAllUsers] = useState([]);
  const [teamlessCount, setTeamlessCount] = useState(0);
  const [teamForm, setTeamForm] = useState(initialTeamForm);

  const companyName = store.getState().userCompany.companyName;

  const createTeam = () => {
    const postBody = {
      teamName: teamForm.teamName,
      teamDescription: teamForm.teamDescription,
      parentCompany: store.getState().userCompany,
    };
    axios
      .post("http://localhost:8080/team/create", postBody)
      .then((res) => console.log(res))
      .catch((err) => console.log(err));
  };

  const getUsersFromCompany = useCallback(() => {
    axios
      .get(`http://localhost:8080/company/${companyName}`)
      .then((res) => {
        setUsersWithoutRole(res.data.users.filter((user) => !user.isDeleted));
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
  }, [companyName]);

  const getUsersWithRole = useCallback(() => {
    axios
      .get("http://localhost:8080/user")
      .then((res) => {
        setAllUsers(
          res.data.filter(
            (user) =>
              !user.isDeleted &&
              user.associatedTeam !== null &&
              user.userCompany.companyName === companyName
          )
        );
      })
      .catch((err) => console.log(err));
  }, [companyName]);

  const allUsersHaveTeams = (
    <StyledUsers>
      <div className="title">
        <h2>Users</h2>
        <p>All users are a on a team</p>
      </div>
      <div className="users-container">
        <div className="card-container">
          {allUsers.map((user) => (
            <UserCard
              key={`${user?.userName}-${user?.lastName}`}
              name={`${user?.firstName} ${user?.lastName}`}
              team={user?.associatedTeam?.teamName}
            />
          ))}
        </div>
      </div>
    </StyledUsers>
  );

  const addTeamToUser = (
    <StyledUsers>
      <div className="title">
        <h2>
          Users
          {teamlessCount > 0 ? (
            <sup className="notification">{teamlessCount}</sup>
          ) : (
            ""
          )}
        </h2>
        <p>Assign Users to a team</p>
      </div>
      <div className="users-container">
        <div className="left">
          <div className="card-container">
            {usersWithoutRole.map((user) => (
              <UserCard
                key={`${user?.userName}-${user?.lastName}`}
                name={`${user?.firstName} ${user?.lastName}`}
                team={user?.associatedTeam?.teamName}
                userName={user?.userName}
                getUsersFromCompany={getUsersFromCompany}
                getUsersWithRole={getUsersWithRole}
              />
            ))}
          </div>
        </div>
        <div className="right">
          <div className="card-container">
            {allUsers.map((user) => (
              <UserCard
                key={`${user?.userName}-${user?.lastName}`}
                name={`${user?.firstName} ${user?.lastName}`}
                team={user?.associatedTeam?.teamName}
                userName={user?.userName}
              />
            ))}
          </div>
        </div>
      </div>
    </StyledUsers>
  );

  const handleTeamSubmit = (event) => {
    event.preventDefault();
    createTeam();
    setTeamForm(initialTeamForm);
  };

  useEffect(() => {
    getUsersFromCompany();
    getUsersWithRole();
  }, [getUsersFromCompany, getUsersWithRole]);

  return (
    <Fragment>
      <Navbar />
      {usersWithoutRole.length === 0 ? allUsersHaveTeams : addTeamToUser}
      <div className="form-container">
        <StyledForm className="form" onSubmit={handleTeamSubmit}>
          <input
            onChange={(e) =>
              setTeamForm({ ...teamForm, teamName: e.target.value })
            }
            value={teamForm.teamName}
            placeholder="team name"
            type="text"
          />
          <input
            onChange={(e) =>
              setTeamForm({ ...teamForm, teamDescription: e.target.value })
            }
            value={teamForm.teamDescription}
            placeholder="team description"
            type="text"
          />
          <Button type="submit" name="+" />
        </StyledForm>
      </div>
    </Fragment>
  );
};
