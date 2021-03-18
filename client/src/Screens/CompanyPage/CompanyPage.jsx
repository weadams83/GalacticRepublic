import { Fragment } from "react";
import Card from "../../Components/Card/Card";
import Navbar from "../../Components/Navbar/Navbar";
import {TeamComponent} from '../../Components/TeamComponent';
import { StyledCompanyPage } from "./StyledCompanyPage";
const dummyData = require("../../DummyData.json");

export const CompanyPage = () => {
  return (
    <Fragment>
      <Navbar />
      <StyledCompanyPage className="company-page">
        <TeamComponent />
      </StyledCompanyPage>
    </Fragment>
  );
};
