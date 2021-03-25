import { StyledProjects } from "./StyledProjects";
import dummyData from "../../DummyData.json";
// import Card from "../Card/Card";
import { Fragment } from "react";
import Navbar from "../Navbar/Navbar";

export const Projects = () => {
  return (
    <Fragment>
      <Navbar />
      <StyledCard>
        <div className="projects-container">
          <div className="title">
            <h2>Projects</h2>
          </div>
          <div className="card-container">
            {dummyData.data[3].projects.map((project) => (
               {key={`${project.name}-${project.id}`} name={project.name} 
            ))}
          </div>
        </div>
      </StyledCard>
    </Fragment>
  );
};
