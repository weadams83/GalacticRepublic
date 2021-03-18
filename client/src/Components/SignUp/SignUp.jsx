
import React from 'react'

import { useState } from "react";
import { NavLink } from 'react-router-dom';
import { InspectP, Styledmain, Form, Input, Button, Select } from './StyledSignUp';

const SignUp = () => {

    const initialFormError={
        isError: false,
        message: '',
        firld:''
    }

    const [formError, updateFormError]=useState(initialFormError)
    const resetError= () => updateFormError(initialFormError)

    const formIsValid = () =>{
        if(!form.firstName.value || !form.lastName.value 
            || !form.username.value || !form.password.value){
        updateFormError({
            ...formError, isError: true, message:'All fields are required'
        })
        return false
        }
        return true
    }

    const handleFormSubmitt= (e) =>{
        if(formIsValid()){
            e.preventDefault();
        }
    }

    const [form, updateForm] = useState({
        firstName: {
            value: '',
            placeholder: 'First Name',
            type: 'text'
        },

        lastName: {
            value: '',
            placeholder: 'Last Name',
            type: 'text'
        },

        username: {
            value: '',
            placeholder: 'Username',
            type: 'text',
            name: 'username'
        },

        password: {
            value: '',
            placeholder: 'Password',
            type: 'password'
        },
    })

    // const handleFormSubmitt = (e) => {
    //     e.preventDefault();
    // }
    const [selectCompanyName, UpdateCompanyName] = useState("Select")

    return (
        <InspectP>

            <Styledmain >

                <Form onSubmit={handleFormSubmitt}>
                    <h1 style={{ margin: '20px', color: 'blue' }} >Sign Up</h1>

                    {Object.entries(form).map(([key, props]) => (
                        <Input key={key}
                            {...props}
                            onChange={event =>{
                                updateForm({
                                    ...form,
                                    [key]: { ...props, value: event.target.value }
                                })
                                resetError() }
                        }
                        />
                    ))}


                    <Select
                        value={selectCompanyName}
                        onChange={(e) => {
                            const selectedCompany = e.target.value;
                            UpdateCompanyName(selectedCompany);
                        }}
                    >
                        <option value="Company A">Company A</option>
                        <option value="Company B">Company B</option>
                        <option value="Company C">Company C</option>
                    </Select>

                    <NavLink to='./Login'>
                        <Button type="submit" onClick={handleFormSubmitt} >Sign Up</Button>
                    </NavLink>

        {formError.isError && !formError.field ? (
        <p style={{color: 'red', textSizeAdjust:'25'}}>{formError.message}</p>
        ) : (
        ''
        )}


                </Form>


            </Styledmain>


        </InspectP>
    )
}

export default SignUp


