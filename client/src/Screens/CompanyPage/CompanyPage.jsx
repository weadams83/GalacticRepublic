import axios from "axios";
import {store} from "../../index"
import { Fragment } from "react";
import Card from "../../Components/Card/Card";
import Navbar from "../../Components/Navbar/Navbar";
import { StyledCompanyPage } from "./StyledCompanyPage";
import { useState, useEffect } from "react";
const dummyData = require("../../DummyData.json");



export const CompanyPage = () => {

  const [companies, setCompanies] =useState([])
  console.log(store.getState())


  const [companies, setCompanies] = useState([]);
  const getCompanies = () => {
    axios.get("http://localhost:8080/company").then((res) => {
      const data = res.data;  
      setCompanies(data);
    });
  };
  useEffect(() => {
    getCompanies();
  }, []);
  

  return (
    <Fragment>
      <Navbar />
      <StyledCompanyPage className="company-page">
        <div className="company-page-container">
          <div className="title">
            <h2>Teams</h2>
          </div>
          <div className="card-container">
            {/* {dummyData.data[2].teams.map((team) => (
              <Card
                className="card"
                name={team.name}
                key={`${team.name}-${team.id}`}
              />
            ))} */}

              <form>
            <select >
            <option>Select a company</option>
            {companies.map((company) => (
              <option key={`${company.companyName}`}>
                {company.companyName}
              </option>
            ))}
          </select>
              </form>







          </div>
        </div>
      </StyledCompanyPage>
    </Fragment>
  );
};
