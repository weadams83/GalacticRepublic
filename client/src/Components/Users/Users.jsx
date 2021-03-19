import Card from "../Card/Card";
import { StyledUsers } from "./StyledUsers";
import dummyData from "../../DummyData.json";
import Navbar from "../Navbar/Navbar";
import { Fragment } from "react";

export const Users = () => {
  return (
    <Fragment>
      <Navbar />
      <StyledUsers>
        <div className="users-container">
          <div className="title">
            <h2>Users</h2>
          </div>
          <div className="card-container">
            {dummyData.data[0].user.map((user) => (
              <Card
                key={`${user.username}-${user.id}`}
                name={`${user.firstname} ${user.lastname} \n ${user.username}`}
              />
            ))}
          </div>
        </div>
      </StyledUsers>
    </Fragment>
  );
};
