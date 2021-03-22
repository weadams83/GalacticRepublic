
import React from 'react'
import axios from "axios";

// import { useHistory } from "react-router";
import { useState } from "react";
import { InspectP, Styledmain, Form, Input, Button, Select } from './StyledSignUp';




const SignUp = () => {

    const initialFormError = {
        isError: false,
        message: '',
        field: ''
    }

    const [formError, updateFormError] = useState(initialFormError)
    const resetError = () => updateFormError(initialFormError)

    const formIsValid = () => {
        if (!form.firstName.value || !form.lastName.value
            || !form.username.value || !form.password.value) {
            updateFormError({
                ...formError, isError: true, message: 'All fields are required'
            })
            return false
        }
        return true
    }
    
 
    // const history = useHistory();
    const handleFormSubmitt = (e) => {
        e.preventDefault();
        // if (formIsValid()) {

            axios.get('http://localhost:8080/company')
            .then((res) => {       
            console.log(res.data[0]["companyName"]);

            //   history.push("/company");
            })
                
        //     .then(data => data.json())
        // .then(response => console.log(response))
        // .catch(error => console.log(error))
        //     .catch((err) => console.log(err));
        // }
        axios.post('http://localhost:8080/company', form)
        .then((res) => {       
        console.log(res.data)

        //   history.push("/company");
        })

    }
  
 // history.push("/member");
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

    const [selectCompanyName, UpdateCompanyName] = useState("Select")

    return (

        <InspectP>
 
            <Styledmain >

                <Form onSubmit={handleFormSubmitt}>
                    <h1 style={{ margin: '20px', color: 'blue' }} >Team</h1>

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
                        value={selectCompanyName}
                        onChange={(e) => {
                            const selectedCompany = e.target.value;
                            UpdateCompanyName(selectedCompany);
                        }}
                    >
                        <option value="Company A" >Company A</option>
                        <option value="Company B">Company B</option>
                        <option value="Company C">Company C</option>
                    </Select>

                    <Button type="submit" onClick={handleFormSubmitt}>Sign Up</Button>

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

export default SignUp


