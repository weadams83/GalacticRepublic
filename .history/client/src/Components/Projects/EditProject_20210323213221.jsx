import React,{useState,useEffect} from 'react'
import axios from 'axios';
import { Form, InspectP, Styledmain,Input, } from '../CreateProject/StyledCreateProject';

export default function EditProject(props) {
    // console.log(this.queryParams)
    console.log(props.location.state.name)
//  console.log(props.match.params)

const [data,setData] = useState({})
useEffect(() => {
    const fetchData = async () => {
      const result = await axios(
        'http://localhost:8080/project/Hunt',
      );
 
      setData(result.data);
      console.log(data)
    };
 
    fetchData();
  }, []);
  const [form, updateForm] = useState({
    name: {
        value: '',
        placeholder: 'project name',
        type: 'text'
    },

    discription: {
        value: '',
        placeholder: 'project description',
        type: 'text'
    },

    




})
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
const initialFormError = {
        isError: false,
        message: '',
        firld: ''
    }



 

    return (
        <div>
            <InspectP>
                <Styledmain>
                    <Form>
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

                    </Form>
                </Styledmain>
            </InspectP>
            
        </div>
    )
}
