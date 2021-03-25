import React,{useState} from 'react'

export default function EditProject(props) {
    // console.log(this.queryParams)
    console.log(props.location.name)
//  console.log(props.match.params)
const [url,setUrl] = useState('http://localhost:8080/api/projects/')
    
    
    return (
        <div>
            <h1>Edit Project</h1>
            
        </div>
    )
}
