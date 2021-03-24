import { Fragment, useState } from "react";
import Navbar from "../Navbar/Navbar";
import { StyledProfile } from "./StyledProfile";
import { store } from "../../index";
import Button from "../Button/Button";
import $ from "jquery";
import axios from "axios";

const initialFormState = store.getState();
const initialCredentialsForm = {
  userName: "",
  password: "",
};

export const Profile = () => {
  const [editFormValues, setEditFormValues] = useState(initialFormState);
  const [showCancel, setShowCancel] = useState(false);
  const [credentials, setCredentials] = useState(initialCredentialsForm);

  console.log(editFormValues)
  console.log(credentials)

  const user = store.getState();
  const handleClick = () => {
    $(".show").toggleClass("hide");
    showCancel ? setShowCancel(false) : setShowCancel(true);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const patchData = {
      credentials: {
        userName: credentials.userName,
        password: credentials.password,
      },
      newData: {
        userName: editFormValues.userName,

        firstName: editFormValues.firstName,

        lastName: editFormValues.lastName,

        password: editFormValues.password,
      },
    };

    axios
      .patch(`http://localhost:8080/user/${user.userName}`, patchData)
      .then((res) => console.log(res))
      .catch((err) => console.log(err));
    // handleClick();
  };

  return (
    <Fragment>
      <Navbar />
      <StyledProfile>
        <form onSubmit={handleSubmit} className="profile-container">
          <h1>Profile</h1>
          <div className="user">
            <div className="pair">
              <p>Username</p>
              <p className="show">{user.userName}</p>
              <input
                className="hide show"
                value={editFormValues.userName}
                onChange={(event) =>
                  setEditFormValues({
                    ...editFormValues,
                    userName: event.target.value,
                  })
                }
                placeholder="new username"
                type="text"
              />
            </div>
            <div className="pair">
              <p>First Name</p>
              <p className="show">{user.firstName}</p>
              <input
                className="hide show"
                value={editFormValues.firstName}
                onChange={(event) =>
                  setEditFormValues({
                    ...editFormValues,
                    firstName: event.target.value,
                  })
                }
                placeholder="new first name"
                type="text"
              />
            </div>
            <div className="pair">
              <p>Last Name</p>
              <p className="show">{user.lastName}</p>
              <input
                className="hide show"
                value={editFormValues.lastName}
                onChange={(event) =>
                  setEditFormValues({
                    ...editFormValues,
                    lastName: event.target.value,
                  })
                }
                placeholder="new last name"
                type="text"
              />
            </div>
            <div className="pair show hide">
              <p>Enter your new password</p>
              <input
                value={editFormValues.password}
                onChange={(event) =>
                  setEditFormValues({
                    ...editFormValues,
                    password: event.target.value,
                  })
                }
                placeholder="new password"
                type="text"
              />
            </div>
          </div>

          <p className="show hide">
            Enter your credentials to save your changes
          </p>
          <input
            className="show hide"
            onChange={(e) =>
              setCredentials({ ...credentials, userName: e.target.value })
            }
            value={credentials.userName}
            placeholder="username"
            name="userName"
            type="text"
          />
          <input
            className="show hide"
            onChange={(e) =>
              setCredentials({ ...credentials, password: e.target.value })
            }
            value={credentials.password}
            placeholder="password"
            name="password"
            type="text"
          />

          <button type="submit" className="show hide">
            Save
          </button>
          <button onClick={(e) => handleClick(e)}>
            {showCancel ? "Cancel" : "Edit"}
          </button>
          <div className="company">
            <div className="title">
              <h4>Company</h4>
            </div>
            <div className="pair">
              <p>Name</p>
              <p>{user.userCompany.companyName}</p>
            </div>
            <div className="pair">
              <p>Description</p>
              <p>{user.userCompany.companyDescription}</p>
            </div>
          </div>
          <div className="team">
            <div className="title">
              <h4>Team</h4>
            </div>
            <div className="pair">
              <p>Name</p>
              <p>{user.associatedTeam.teamName}</p>
            </div>
            <div className="pair">
              <p>Description</p>
              <p>{user.associatedTeam.teamDescription}</p>
            </div>
          </div>
          <div className="projects">
            <h4 className="title">Projects</h4>
            {user.projects.map((project) => (
              <div key={project.name}>
                <div className="pair">
                  <p>Name</p>
                  <p>{project.name}</p>
                </div>
                <div className="pair">
                  <p>Description</p>
                  <p>{project.description}</p>
                </div>
              </div>
            ))}
          </div>
        </form>
      </StyledProfile>
    </Fragment>
  );
};
