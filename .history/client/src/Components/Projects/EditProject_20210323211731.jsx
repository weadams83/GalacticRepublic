import React,{useState} from 'react'

export default function EditProject(props) {
    // console.log(this.queryParams)
    console.log(props.location.state.name)
//  console.log(props.match.params)

const [data,setData] = useState({})

useEffect( async () => { const result = await axios('http://localhost:8080/project/Hunt',); 
setData(result.data);}
fetchData)
    
    
    return (
        <div>
            <h1>Edit Project</h1>
            
        </div>
    )
}
