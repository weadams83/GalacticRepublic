import React from 'react'
import axios from "axios";
import { store } from "../../index"
// import { Fragment } from "react";
// import Card from "../../Components/Card/Card";
// import Navbar from "../../Components/Navbar/Navbar";
// import { StyledCompanyPage } from "./StyledCompanyPage";
// import { useState, useEffect } from "react";
// import { NavLink } from "react-router-dom";
import { useDispatch } from "react-redux";
// import { saveUser } from "../../store/loginReducer";
import { InspectP, Styledmain, Form, Input, Button} from '../CreateProject/StyledCreateProject';
import { useState } from "react";
import { saveUser } from "../../store/loginReducer";
import {credentials} from "../../utils/Credentials"

 
const initialCredentialsForm = {
    userName: "",
    password: "",
  };


const CreateTeam = () => {

    const [form, updateForm] = useState({
        teamName: {
        value: "",
        placeholder: "Team Name",
        type: "text",
      },
  
      teamDescription: {
        value: "",
        placeholder: "Team Description",
        type: "text",
      },
    });
    const initialFormError = {
      isError: false,
      message: "",
      field: "",
    };
    const [formError, updateFormError] = useState(initialFormError);
    const resetError = () => updateFormError(initialFormError);
    const formIsValid = () => {
      if (
        !form.teamName.value ||
        !form.teamDescription.value
      ) {
        updateFormError({
          ...formError,
          isError: true,
          message: "All fields are required",
        });
        return false;
      }
      return true;
    };


    // const dispatch = useDispatch();
    const user = store.getState();
    console.log(user.userName)
    console.log(user.password)
    user.password = "";
    // const u=user.userName;
    // const p=user.password;
   const [credentials, setCredentials] = useState(initialCredentialsForm);
 

    const handleFormSubmitt = (e) => {
      e.preventDefault();
     if(formIsValid()){
      const postInfo = {
        credentials: {
            userName: credentials.userName,
            password: credentials.password,
          },
            team:{
                teamName:form.teamName.value,
                teamDescription:form.teamDescription.value
            }};      

      axios
        .post("http://localhost:8080/user", postInfo)
        .then((res) => console.log(res))
        .catch((err) => console.log(err))
    };
  
  }
    // useEffect(() => {
    //   getCompanies();
    // }, []);
      
    return (
      <InspectP>
        <Styledmain>
          <Form onSubmit={handleFormSubmitt}>
            <h1 style={{ margin: "20px", color: "blue" }}>Create Team</h1>
            {Object.entries(form).map(([key, props]) => (
              <Input
                key={key}
                {...props}
                onChange={(event) => {
                  updateForm({
                    ...form,
                    [key]: { ...props, value: event.target.value },
                  });
                  resetError();
                }}
              />
            ))}
  
            <Button type="submit" onClick={handleFormSubmitt}>
              Create Teams
            </Button>
  
            {formError.isError && !formError.field ? (
              <p style={{ color: "red", textSizeAdjust: "25" }}>
                {formError.message}
              </p>
            ) : (
              ""
            )}
  
            
          </Form>
        </Styledmain>
      </InspectP>
    )
}

export default CreateTeam

