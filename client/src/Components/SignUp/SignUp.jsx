
import React from 'react'
import axios from "axios";

// import { useHistory } from "react-router";
import { useState, useEffect } from "react";
import { InspectP, Styledmain, Form, Input, Button, Select } from './StyledSignUp';



const SignUp = () => {
    const [selectCompanyName, updateCompanyName] = useState([])



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

  const getTeams = () =>{

    axios.get('http://localhost:8080/company')
    .then((res) => {
        // console.log(res.data[0]["companyName"]);
        const data = res.data

        updateCompanyName(data)
        console.log(selectCompanyName)

    })
  }

  const addCompany = (e) => {

    // const data = {
    //         "credentials":{
    //             "userName":"JP Lobster",
    //             "password":"Money"
    //         },
    //         "companyName":"d"
       
    // }
    console.log(form)
    
    // axios.post('http://localhost:8080/user/{username}/company, )
    // .then((res) => {       
    // console.log(res.data)

    //   history.push("/company");
    // })
  }

  

    // const history = useHistory();
    const handleFormSubmitt = (e) => {
        e.preventDefault();
        // if (formIsValid()) {

        axios.post('http://localhost:8080/user', form)
            .then((res) => {
                // console.log(res.data[0]["companyName"]);
                const data = res.data
                    console.log(data)
                // const r = (res.data)
                // r.forEach(function(entry){
                //    const bi = (entry["companyName"])
                //        console.log(bi)
                //        return bi
                // })


                updateCompanyName(data)
                console.log(selectCompanyName)

                //   history.push("/company");
            })

        //     .then(data => data.json())
        // .then(response => console.log(response))
        // .catch(error => console.log(error))
        //     .catch((err) => console.log(err));
        // }


        // axios.post('http://localhost:8080/company', form)
        // .then((res) => {       
        // console.log(res.data)

        // //   history.push("/company");
        // })


        //after user is created 
        // axios.post('http://localhost:8080/user/{username}/company , form)
        // .then((res) => {       
        // console.log(res.data)

        // //   history.push("/company");
        // })




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


    // const [selectCompanyName, UpdateCompanyName] = useState("")
    useEffect(() => {
        getTeams()
    }, [])


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

                    {/* <Select
                        value={selectCompanyName}
                        onChange={(e) => {
                            const selectedCompany = e.target.value;
                            UpdateCompanyName(selectedCompany);
                        }}
                    >
                        <option value= "Company A">Company A </option>
                        <option value="Company B">Company B</option>
                        <option value="Company C">Company C</option>
                    </Select> */}

                    <Select onChange={addCompany}> 
                    {selectCompanyName.map(team=>(
                        <option>{team.companyName}</option>
                    ))}
                    </Select>



                    <Button type="submit" onClick={handleFormSubmitt}>Sign Up</Button>

                    {formError.isError && !formError.field ? (
                        <p style={{ color: 'red', textSizeAdjust: '25' }}>{formError.message}</p>
                    ) : (
                        ''
                    )}


                    {/* <Select options = {options} /> */}




                </Form>
            </Styledmain>
        </InspectP>
    )
}

export default SignUp


