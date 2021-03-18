import { Fragment } from "react";
import Card from "../../Components/Card/Card";
import Navbar from "../../Components/Navbar/Navbar";
import { StyledCompanyPage } from "./StyledCompanyPage";
const dummyData = require("../../DummyData.json");

export const CompanyPage = () => {
  return (
    <Fragment>
      <Navbar />
      <StyledCompanyPage className="company-page">
        <div className="company-page-container">
          <div className="title">
            <h2>Company Page</h2>
          </div>
          <div className="card-container">
            {dummyData.data[2].teams.map((team) => (
              <Card
                className="card"
                name={team.name}
                key={`${team.name}-${team.id}`}
              />
            ))}
          </div>
        </div>
      </StyledCompanyPage>
    </Fragment>
  );
};
