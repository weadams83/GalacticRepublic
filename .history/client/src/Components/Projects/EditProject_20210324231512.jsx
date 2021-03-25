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
//   console.log(props.location.state.name);
  //  console.log(props.match.params)

  const initialFormError = {
    isError: false,
    message: "",
    firld: "",
  };
  const [selectTeam, UpdateTeam] = useState("");

  //   const inputEl = useRef(null)

  const [data, setData] = useState({ name: "", description: "" });
  
  useEffect(() => {
    const fetchData = async () => {
      const result = await axios(
        `http://localhost:8080/project/${props.location.state.name}`
      );

      setData( result.data);
     
      //   console.log(result.data);
    //   setProjectName(data.name);
    //   // inputEl.current.value = data.name
    //   setProjectDesc(data.description);
    };

    fetchData();
  }, [setData]);

  useEffect(() => {})


  const [values ,setValues] = useState({ projectName:"",projectDesc:""})

  useEffect(()=>{
      setValues((state)=>({...state,projectName:data.name}))
  },[data.name])
  useEffect(()=>{
      setValues((state)=>({...state,projectDesc:data.description}))
  },[data.description])



  //   console.log(data);
//   console.log(data.name);

  // const [formError, updateFormError] = useState(initialFormError)
  // const resetError = () => updateFormError(initialFormError)

  // const formIsValid = () => {
  //     if (!form.name.value || !form.description.value
  //     ) {
  //         updateFormError({
  //             ...formError, isError: true, message: 'All fields are required'
  //         })
  //         return false
  //     }
  //     return true
  // }

  const [deleted, isDeleted] = useState(false)
//   setProjectDesc(data.name)
  const handleFormSubmit = () => {};
  const handleChange = (event) => {
   
  };
  const deleteProject = ()=>{
      
      
  }

  return (
    <div>
      <InspectP>
        <Styledmain>
          <Form>
            <h1 style={{ margin: "20px", color: "blue" }}>Edit Project</h1>
            <Input
              value={values.projectName}
              type="text"
              onChange={handleChange}
              placeholder="Project Name"
            />
            <Input
              value={values.projectDesc}
              type="text"
              placeholder="Project Description"
              onChange={handleChange}
            />

            <Select
              value={selectTeam}
              onChange={(e) => {
                const team = e.target.value;
                console.log(team);
                UpdateTeam({
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
            <Button type="submit" onClick={deleteProject}>
              Delete
            </Button>
          </Form>
        </Styledmain>
      </InspectP>
    </div>
  );
}
