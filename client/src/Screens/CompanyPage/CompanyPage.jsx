import axios from "axios";
import { store } from "../../index"
import { Fragment } from "react";
import Card from "../../Components/Card/Card";
import Navbar from "../../Components/Navbar/Navbar";
import { StyledCompanyPage } from "./StyledCompanyPage";
import { useState, useEffect } from "react";
import { NavLink } from "react-router-dom";
const dummyData = require("../../DummyData.json");


export const CompanyPage = () => {

  const user = store.getState();

  const [companies, setCompanies] = useState([]);
  // const [teamMembers, setTeamMembers] = useState([])
  const getCompanies = () => {
    axios.get(`http://localhost:8080/company/${user.userCompany.companyName}`).then((res) => {
      const data = res.data;
      setCompanies(data.teams);
      //  console.log(data.teams)
      // setTeamMembers(data.teams.teamName)
      // const n =(data.teams.map((item)=> item.teamMembers))
        // console.log(n)
        // setTeamMembers(n)
    });
  };

  useEffect(() => {
    getCompanies();
  }, []);

    


  // const usertwo = store.getState();
  // console.log(usertwo)
  
    const handleFormSubmitt = (e) => {
    const deleteTeam = () => {
    //   {
    //     "userName":"Michael Scarn",
    //     "password": "Friendship"
    // }
    axios.delete(`http://localhost:8080/company/${user.userCompany.companyName}`).then((res) => {
      const data = res.data;
      setCompanies(data.teams);
      //  console.log(data.teams)
      // setTeamMembers(data.teams.teamName)
      // const n =(data.teams.map((item)=> item.teamMembers))
      //   console.log(n)
    });
    }
  }

  return (
    <Fragment>
      <Navbar />
      <StyledCompanyPage className="company-page">
        <div className="company-page-container">
          <div className="title">
            <h2>Teams</h2>
          </div>
          <div className="card-container">
            {companies.map((team) => (
              <Card
                className="card"
                name={team.teamName}
                key={`${team.teamName}-${team.teamDescription}`}
              />
            ))}
          </div>

              
          {/* {teamMembers.map((member) => (
              <Card
                className="card"
                name={member.userName}
                key={`${member.userName}-${member.firstName}`}
              />
            ))} */}


              
          <select >
            <option>Select a Team</option>
            {companies.map((team) => (
              <option key={`${team.teamName}`}>
                {team.teamName}
              </option>
            ))}
          </select>
          <button  type="submit" onClick={handleFormSubmitt}>
           Delete Team
          </button>








        <NavLink to="./createTeam">
          <button type="submit">Create Team</button>
        </NavLink>





        </div>
      </StyledCompanyPage>
    </Fragment>
  );
};
