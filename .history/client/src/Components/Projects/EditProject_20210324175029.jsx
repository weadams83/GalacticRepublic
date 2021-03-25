import React,{useState,useEffect} from 'react'
import axios from 'axios';
import { Form, InspectP, Styledmain,Input, Button,Select} from '../CreateProject/StyledCreateProject';

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
 
const [data,setData] = useState({})
useEffect(() => {
    const fetchData = async () => {
      const result = await axios(
        'http://localhost:8080/project/Hunt',
      );
 
      setData(result.data);
      console.log(result.data)
    //   updateForm({discription:data.description})
    };
 
    fetchData();
  }, []);
  const [form, updateForm] = useState({
    name: {
        value: "",
        placeholder: `${data?.name}`,
        type: 'text'
    },

    discription: {
        value: data.description,
        placeholder: 'project description',
        type: 'text'
    },

    




})
  console.log(data)
  console.log(data.name)


const [formError, updateFormError] = useState(initialFormError)
const resetError = () => updateFormError(initialFormError)

const formIsValid = () => {
    if (!form.name.value || !form.discription.value
    ) {
        updateFormError({
            ...formError, isError: true, message: 'All fields are required'
        })
        return false
    }
    return true
}

const handleFormSubmit = ()=>{

}



 

    return (
        <div>
            <InspectP>
                <Styledmain>
                    <Form>
                    <h1 style={{ margin: '20px', color: 'blue' }} >Edit Project</h1>
                    {Object.entries(form).map(([key, props]) => (
                        <Input key={key}
                            {...props}
                            onChange={event => {
                                updateForm({
                                    ...form,
                                    [key]: { ...props, value: event.target.value }
                                })
                                console.log(event.target.value)
                                resetError()
                            }
                            

                            }
                            
                        />
                    ))}
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
