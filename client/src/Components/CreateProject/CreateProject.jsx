
import React from 'react'

import { useState } from "react";
import { NavLink, useHistory } from 'react-router-dom';
import { InspectP, Styledmain, Form, Input, Button, Select } from './StyledCreateProject';
import dummyData from '../../DummyData.json'

const CreateProject = () => {
    const history = useHistory();

    const initialFormError = {
        isError: false,
        message: '',
        field: ''
    }

    const [formError, updateFormError] = useState(initialFormError)
    const resetError = () => updateFormError(initialFormError)

    const formIsValid = () => {
        if (!form.name.value || !form.description.value
        ) {
            updateFormError({
                ...formError, isError: true, message: 'All fields are required'
            })
            return false
        }
        return true
    }

    const handleFormSubmitt = (e) => {
        if (formIsValid()) {
            e.preventDefault();
            console.log(form.name)
            console.log(form.description)
            console.log(selectTeam.team)
            history.push("/createprojectsuccess")
            //{
              //  axios
                //  .post("http://localhost:8080/project", form)
                 // .then((res) => {
                  //  localStorage.setItem("currentProject", JSON.stringify(res.data));
                   // history.push("/createprojectsuccess");
                 // })
                 // .catch((err) => console.log(err));
             // }
            
        }
    }

    const [selectTeam, UpdateTeam] = useState(
       ''
    )

    const [form, updateForm] = useState({
        name: {
            value: '',
            placeholder: 'project name',
            type: 'text'
        },

        description: {
            value: '',
            placeholder: 'project description',
            type: 'text'
        },

        




    })



    return (
        <InspectP>

            <Styledmain >

                <Form onSubmit={handleFormSubmitt}>
                    <h1 style={{ margin: '20px', color: 'blue' }} >Create Project</h1>

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
                            <option value='' defaultValue disabled >choose a team</option>
                        {dummyData.data[2].teams.map((team) => (
                            
                            <option key={`${team.name}-${team.id}`} name={team.name}  >{team.name}</option>
                        ))}

                    </Select>




                    
                        <Button type="submit" onClick={handleFormSubmitt} >Create</Button>
                   

                    {formError.isError && !formError.field ? (
                        <p style={{ color: 'red', textSizeAdjust: '25' }}>{formError.message}</p>
                    ) : (
                            ''
                        )}

                </Form>
            </Styledmain>
        </InspectP>
    )
}

export default CreateProject
