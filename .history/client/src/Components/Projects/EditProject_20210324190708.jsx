import React,{useState,useEffect} from 'react'
import axios from 'axios';
import { Form, InspectP, Styledmain,Input, Button,Select} from '../CreateProject/StyledCreateProject';
import {EditProjectInput} from './StyledProjects'
export default function EditProject(props) {
    // console.log(this.queryParams)
    console.log(props.location.state.name)
//  console.log(props.match.params)

const initialFormError = {
    isError: false,
    message: '',
    firld: ''
}
const [selectTeam, UpdateTeam] = useState(
    ''
 )
 const [form, updateForm] = useState({
    name: {
        value: "",
        placeholder: 'Project Name',
        onChange:()=>{updateForm({...form,name:{value: data.name},description:{value:data.description}})},
        type: 'text'
    },

    description: {
        value: "",
        placeholder: `Project Description`,
        type: 'text'
    },
    

    




})
 
const [data,setData] = useState({})
useEffect(() => {
    const fetchData = async () => {
      const result = await axios(
        `http://localhost:8080/project/${props.location.state.name}`,
      );
 
      setData( await result.data);
      console.log(result.data)
    
    };
    
    fetchData();
    
  }, []);
 


  console.log(data)
  console.log(data.name)



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

const handleFormSubmit = ()=>{

}
const handleChange = ()=>{

}



 

    return (
        <div>
            <InspectP>
                <Styledmain>
                    <Form>
                    <h1 style={{ margin: '20px', color: 'blue' }} >Edit Project</h1>
                  <Input value = {data.name} type = "text" onChange  = {handleChange} />
                  <Input value = {data.description} type ='text' onChange={handleChange}/>
                  
                  
                    <Select
                        value={selectTeam}
                        onChange={(e) => {
                            
                            const team = e.target.value;
                            console.log(team)
                            UpdateTeam({
                               team
                            });
                        }}>
                            {/* <option value='' defaultValue disabled >choose a team</option>
                        {dummyData.data[2].teams.map((team) => ( */}
                            
                            {/* // <option key={`${team.name}-${team.id}`} name={team.name}  >{team.name}</option> */}
                        ))}

                    </Select>




                    
                        <Button type="submit" onClick={handleFormSubmit} >Update</Button>

                    </Form>
                </Styledmain>
            </InspectP>
            
        </div>
    )
}
