
import React from 'react'

import { useState } from "react";
import { NavLink } from 'react-router-dom';
import { InspectP, Styledmain, Form, Input, Button, Select } from './StyledCreateProject';
import dummyData from '../../DummyData.json'

const CreateProject = () => {

    const initialFormError = {
        isError: false,
        message: '',
        firld: ''
    }

    const [formError, updateFormError] = useState(initialFormError)
    const resetError = () => updateFormError(initialFormError)

    const formIsValid = () => {
        if (!form.titleofproject.value || !form.projectdiscription.value
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
        }
    }

    const [selectTeam, UpdateTeam] = useState("Select")

    const [form, updateForm] = useState({
        titleofproject: {
            value: '',
            placeholder: 'project name',
            type: 'text'
        },

        projectdiscription: {
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
                                resetError()
                            }
                            }
                        />
                    ))}

                    <Select
                        value={selectTeam}
                        onChange={(e) => {
                            const selectedTeam = e.target.value;
                            UpdateTeam(selectedTeam);
                        }}>
                        {dummyData.data[2].teams.map((team) => (
                            <option key={`${team.name}-${team.id}`} name={team.name} >{team.name}</option>
                        ))}

                    </Select>




                    <NavLink to='./member'>
                        <Button type="submit" onClick={handleFormSubmitt} >Create</Button>
                    </NavLink>

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
