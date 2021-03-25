import React, { useState, useEffect, useRef } from "react";
import axios from "axios";
import {
  Form,
  InspectP,
  Styledmain,
  Input,
  Button,
  Select,
} from "../CreateProject/StyledCreateProject";
import { EditProjectInput } from "./StyledProjects";
export default function EditProject(props) {
  // console.log(this.queryParams)
  console.log(props.location.state.name);
  //  console.log(props.match.params)

 const[data, setData] = useState({})
 const[selectTeam,updateTeam]= useState('')
 const[values,setValues]=({ projectName:"",projectDesc:""})

 useEffect(() => {
     fetch(`http://localhost:8080/project/${props.location.state.name}`).then(response=>response.json()).then(({data:values})=>{
         
     })
     }, [])
  return (
    <div>
      <InspectP>
        <Styledmain>
          <Form>
            <h1 style={{ margin: "20px", color: "blue" }}>Edit Project</h1>
            <Input
              value={projectName}
              type="text"
              onChange={handleChange}
              placeholder="Project Name"
            />
            <Input
              value={projectDesc}
              type="text"
              placeholder="Project Description"
              onChange={handleChange}
            />

            <Select
              value={selectTeam}
              onChange={(e) => {
                const team = e.target.value;
                console.log(team);
                updateTeam({
                  team,
                });
              }}
            >
              {/* <option value='' defaultValue disabled >choose a team</option>
                        {dummyData.data[2].teams.map((team) => ( */}
              {/* // <option key={`${team.name}-${team.id}`} name={team.name}  >{team.name}</option> */}
              ))
            </Select>

            <Button type="submit" onClick={handleFormSubmit}>
              Update
            </Button>
          </Form>
        </Styledmain>
      </InspectP>
    </div>
  );
}
