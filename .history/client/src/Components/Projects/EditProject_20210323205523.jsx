import React,{useEffect, useState} from 'react'
import axios from "axios";

export default function EditProject(props) {
    // console.log(this.queryParams)
    console.log(props.location.state.name)
//  console.log(props.match.params)
const [url,setUrl] = useState(`http://localhost:8080/project/Hunt`)
const [data,setData] = useState({})

useEffect( async ()=>{
    const result = await axios(url);
    setUrl(result.data);
})
    
    return (
        <div>
            <h1>Edit Project</h1>
            
        </div>
    )
}
