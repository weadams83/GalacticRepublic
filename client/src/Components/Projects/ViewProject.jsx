import React, { useState, useEffect } from "react";
import { InspectP, Styledmain } from "../CreateProject/StyledCreateProject";
import { Form } from "../SignUp/StyledSignUp";
import axios from "axios";

export default function ViewProject(props) {
  const [data, setData] = useState({name:"",description:"",company:"",user:{userName:""},team:{teamName:"",teamMembers:[]}});
  console.log(props.location.state);

  useEffect(() => {
    const fetchData = async () => {
      const result = await axios(
        `http://localhost:8080/project/${props.location.state.name}`
      );

      setData(result.data);

      //   console.log(result.data);
      //   setProjectName(data.name);
      //   // inputEl.current.value = data.name
      //   setProjectDesc(data.description);
    };

    fetchData();
  }, [setData]);

//   data.team == null ? "":data.team;
 const setValues = ()=>{

   let dataTeam=data.team == null || data.team == undefined ? "":data.team;
}

  return (
    <div>
      <InspectP>
        <Styledmain>
          <Form>
            <p>Name:{data.name}</p>
            <p>Description:{data.description}</p>
            <p>Company:{data.company}</p>
            <p>Creator: {data.user.userName}</p>
            <p>Team: {data.team.teamName}</p>
            {/* {data.team.teamMembers.map()} */}
          </Form>
        </Styledmain>
      </InspectP>
    </div>
  );
}
