import React from 'react'
import axios from "axios";

// import { useHistory } from "react-router";
import { useState } from "react";
import { InspectP, Styledmain, Form, Input, Button } from './StyledSignUp';


const CompanySignUp = () => {
    const initialFormError = {
        isError: false,
        message: '',
        field: ''
    }

    const [formError, updateFormError] = useState(initialFormError)
    const resetError = () => updateFormError(initialFormError)

    const formIsValid = () => {
        if (!form.companyname.value || !form.description.value
            || !form.username.value || !form.password.value) {
            updateFormError({
                ...formError, isError: true, message: 'All fields are required'
            })
            return false
        }
        return true
    }

    // const userRole = {
    //     roleTitle: "company",
    //   }

    // const history = useHistory();
    const handleFormSubmitt = (e) => {
        if (formIsValid()) {
            // @CrossOrigin(origins = "http://localhost:4200", 
          axios.get('http://localhost:8080/company',{
            mode: 'no-cors' // 'cors' by default
          })
            .then((res) => {
            console.log(res.data);
            //   history.push("/company");
            })
        //     .then(data => data.json())
        // .then(response => console.log(response))
        // .catch(error => console.log(error))
            // .catch((err) => console.log(err));
        }  
    }

    

    const [form, updateForm] = useState({
        companyname: {
            value: '',
            placeholder: 'Company Name',
            type: 'text'
        },

        description: {
            value: '',
            placeholder: 'Company Description',
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


    return (
        <InspectP>

            <Styledmain >

                <Form onSubmit={handleFormSubmitt}>
                    <h1 style={{ margin: '20px', color: 'blue' }} >Company</h1>

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



export default CompanySignUp
