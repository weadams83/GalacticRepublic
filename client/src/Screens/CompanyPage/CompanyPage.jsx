import Card from "../../Components/Card/Card";
import { StyledCompanyPage } from "./StyledCompanyPage";
const dummyData = require("../../DummyData.json");


export const CompanyPage = () => {
  return (
    <StyledCompanyPage className="company-page">
      <div className="company-page-container">
        <h2>Company Page</h2>
        {dummyData.data[2].teams.map(team => (
          <Card name={team.name} key={`${team.name}-${team.id}`} />
  ))}
        <Card />
      </div>
    </StyledCompanyPage>
  );
};
