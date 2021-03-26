import $ from "jquery";
import { useState } from "react";
import { useHistory } from "react-router";
import { NavLink } from "react-router-dom";

import "../SignUp/SignUp";
import "../SignUp/CompanySignUp";
import {
  StyledLogin,
  SignUpB,
  CompanySignUpB,
  SignUpForm,
} from "./StyledLogin";

import "../SignUp/SignUp";

import axios from "axios";
import { useDispatch } from "react-redux";
import { saveUser } from "../../store/loginReducer";

const dummyData = require("../../DummyData.json");

const initialMemberForm = {
  userName: "",
  password: "",
};

const initialCompanyForm = {
  userName: "",
  password: "",
};

export const Login = () => {


  const dispatch = useDispatch()
  dispatch(saveUser())



  const dispatch = useDispatch();
  dispatch(saveUser());

  const [memberFormValues, setMemberFormValues] = useState(initialMemberForm);
  const [companyFormValues, setCompanyFormValues] = useState(
    initialCompanyForm
  );

  const history = useHistory();

  const handleChange = (e) => {
    if (e.target.placeholder === "Company Username") {
      setCompanyFormValues({
        ...companyFormValues,
        userName: e.target.value,
      });
    }
    if (e.target.id === "company-password") {
      setCompanyFormValues({ ...companyFormValues, password: e.target.value });
    }
    if (e.target.placeholder === "Username") {
      setMemberFormValues({ ...memberFormValues, userName: e.target.value });
    }
    if (e.target.id === "member-password") {
      setMemberFormValues({ ...memberFormValues, password: e.target.value });
    }
  };
  const handleSelect = (e) => {
    if (e.target.value === "default") {
      $(".login-container form .member-select").addClass("hide");
      $(".login-container form .company-select").addClass("hide");
      $(".button").addClass("hide");
    }
    if (e.target.value === "company") {
      $(".login-container form .member-select").addClass("hide");
      $(".login-container form .company-select").toggleClass("hide");
      $(".button").removeClass("hide");
    }
    if (e.target.value === "member") {
      $(".login-container form .company-select").addClass("hide");
      $(".login-container form .member-select").toggleClass("hide");
      $(".button").removeClass("hide");
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (e.target.role.value === "company") {
      if (
        dummyData.data[0].user.filter(
          (user) => user.userName === companyFormValues.userName
        ) &&
        dummyData.data[0].user.filter(
          (user) => user.password === companyFormValues.password
        )
      ) {
        axios
          .post("http://localhost:8080/user/login", companyFormValues)
          .then((res) => {


            dispatch(saveUser(res.data.firstName, res.data.lastName, res.data.userName))
            history.push("/company");

            localStorage.setItem("currentUser", JSON.stringify(res.data));
            history.push("/users");


            dispatch(
              saveUser(
                res.data.firstName,
                res.data.lastName,
                res.data.userName,
                res.data.userCompany,
                res.data.userRole,
                res.data.projects,
                res.data.associatedTeam,
                res.data.isDeleted,
                res.data.newUser
              )
            );
            history.push("/users");

          })
          .catch((err) => console.log(err));
      }
    }
    if (e.target.role.value === "member") {
      if (
        dummyData.data[0].user.filter(
          (user) => user.userName === memberFormValues.userName
        ) &&
        dummyData.data[0].user.filter(
          (user) => user.password === memberFormValues.password
        )
      ) {
        axios
          .post("http://localhost:8080/user/login", memberFormValues)
          .then((res) => {


            dispatch(saveUser(res.data.firstName, res.data.lastName, res.data.userName, res.data.userCompany))

            localStorage.setItem("currentUser", JSON.stringify(res.data));


            dispatch(
              saveUser(
                res.data.firstName,
                res.data.lastName,
                res.data.userName,
                res.data.userCompany,
                res.data.userRole,
                res.data.projects,
                res.data.associatedTeam,
                res.data.isDeleted,
                res.data.newUser
              )
            );

            history.push("/member");
          })
          .catch((err) => console.log(err));
      }
    }
  };
  return (
    <StyledLogin className="login">
      <div className="login-container">
        <h1>Login</h1>
        <form onSubmit={handleSubmit}>
          <select onChange={handleSelect} name="role" id="role">
            <option value="default">Choose your role</option>
            <option value="company">Company</option>
            <option value="member">Team Member</option>
          </select>
          <div className="member-select hide">
            <input
              onChange={handleChange}
              value={memberFormValues.userName}
              placeholder="Username"
              type="text"
            />
            <input
              id="member-password"
              onChange={handleChange}
              value={memberFormValues.password}
              placeholder="Password"
              type="password"
            />
          </div>
          <div className="company-select hide">
            <input
              onChange={handleChange}
              value={companyFormValues.userName}
              placeholder="Company Username"
              type="text"
            />
            <input
              id="company-password"
              onChange={handleChange}
              value={companyFormValues.password}
              placeholder="Password"
              type="password"
            />
          </div>
          <div className="button hide">
            <button type="submit">Login</button>
          </div>
        </form>

        <SignUpForm>
          <NavLink to="/SignUp">
            <SignUpB type="submit">Sign Up as a Member</SignUpB>
          </NavLink>
          <NavLink to="/CompanySignUp">
            <CompanySignUpB type="submit">Sign Up as a Company</CompanySignUpB>
          </NavLink>
        </SignUpForm>
      </div>
    </StyledLogin>
  );
};
