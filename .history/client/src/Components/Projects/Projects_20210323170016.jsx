import { StyledProjects } from "./StyledProjects";
import dummyData from "../../DummyData.json";
// import Card from "../Card/Card";
import { Fragment } from "react";
import Navbar from "../Navbar/Navbar";
import { StyledCard } from "../Card/StyledCard";
import Button from "../Button/Button";
import { BrowserRouter as Router, Route, Link } from 'react-router-dom'
import EditProject from "./EditProject";


export const Projects = () => {
  return (
    <Fragment>
      <Navbar />
      <Router>
        <Route path="/project/edit" component={EditProject}/>
      <StyledProjects>
        <div className="projects-container">
          <div className="title">
            <h2>Projects</h2>
          </div>
          <div className="card-container">
            {dummyData.data[3].projects.map((project) => (
              <StyledCard key={`${project.name}-${project.id}`} name={project.name}>{project.name}
              <div className="buttons">
              <div className="button">
         <Link to <Button name="Edit"></Button>
        </div>
              <div className="button">
          <Button name="View"></Button>
        </div>
              </div>
                
              </StyledCard>
            ))}
            </div>
        </div>
        
      </StyledProjects>
      </Router>
    </Fragment>
  );
};
