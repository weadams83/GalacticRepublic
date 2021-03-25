import { StyledProjects } from "./StyledProjects";
import dummyData from "../../DummyData.json";
// import Card from "../Card/Card";
import { Fragment } from "react";
import Navbar from "../Navbar/Navbar";
import { StyledCard } from "../Card/StyledCard";
import Button from "../Button/Button";
import { Link } from 'react-router-dom'
// import EditProject from './EditProject'
import React, { useState,useEffect } from 'react'
import axios from "axios";




export const Projects = () => {
  const [data,setData] = useState({})
useEffect(() => {
    const fetchData = async () => {
      const result = await axios(
        'http://localhost:8080/project/',
      );
 
      setData(result.data);
      console.log(result.data)
    // updateForm({...form,name:{value: data.name},description:{value:data.description}})
    };
 
    fetchData();
  }, []);
  return (
    <Fragment>
      <Navbar />
      <StyledProjects>
        <div className="projects-container">
          <div className="title">
            <h2>Projects</h2>
          </div>
          <div className="card-container">
            {dummyData.data.projects.map((project) => (
              <StyledCard key={`${project.name}-${project.id}`} name={project.name}>{project.name}
              <div className="buttons">
              <div className="button">
         <Link  to={{
  pathname: `projects/edit/${project.id}`,
  state: {
    name: project.name
  }
}}> <Button name="Edit"></Button></Link>
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
    </Fragment>
  );
};
