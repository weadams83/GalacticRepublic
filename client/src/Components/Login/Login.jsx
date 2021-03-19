import $ from "jquery";
import { useState } from "react";
import { useHistory } from "react-router";
import {NavLink} from "react-router-dom"

import '../SignUp/SignUp'
import '../SignUp/CompanySignUp'
import { StyledLogin, SignUpB, CompanySignUpB, SignUpForm } from "./StyledLogin";

import { CompanyPage } from "../../Screens/CompanyPage/CompanyPage";
import "../SignUp/SignUp";

const dummyData = require("../../DummyData.json");

const initialMemberForm = {
  username: "",
  password: "",
};

const initialCompanyForm = {
  companyUsername: "",
  password: "",
};

export const Login = () => {
  const [currentUser, setCurrentUser] = useState("");
  const [memberFormValues, setMemberFormValues] = useState(initialMemberForm);
  const [companyFormValues, setCompanyFormValues] = useState(
    initialCompanyForm
  );

  const history = useHistory();

  const handleChange = (e) => {
    if (e.target.placeholder === "Company Username") {
      setCompanyFormValues({
        ...companyFormValues,
        companyUsername: e.target.value,
      });
    }
    if (e.target.id === "company-password") {
      setCompanyFormValues({ ...companyFormValues, password: e.target.value });
    }
    if (e.target.placeholder === "Username") {
      setMemberFormValues({ ...memberFormValues, username: e.target.value });
    }
    if (e.target.id === "member-password") {
      setMemberFormValues({ ...memberFormValues, password: e.target.value });
    }
  };
  const handleSubmit = (e) => {
    console.log(e.target.role.value);
    e.preventDefault();
    if (e.target.role.value === "company") {
      if (
        dummyData.data[0].user.filter(
          (user) => user.username === companyFormValues.companyUsername
        ) &&
        dummyData.data[0].user.filter(
          (user) => user.password === companyFormValues.password
        )
      ) {
        setCurrentUser(companyFormValues.companyUsername);
        history.push("/company");
      }
    }
    if (e.target.role.value === "member") {
      if (
        dummyData.data[0].user.filter(
          (user) => user.username === memberFormValues.username
        ) &&
        dummyData.data[0].user.filter(
          (user) => user.password === memberFormValues.password
        )
      ) {
        setCurrentUser(memberFormValues.username);
        history.push("/member");
      }
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
              value={memberFormValues.username}
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
              value={companyFormValues.companyUsername}
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

        <NavLink to="./SignUp">
          <SignUpB type="submit">SignUp</SignUpB>
        </NavLink>

      </div>
    </StyledLogin>
  );
};
