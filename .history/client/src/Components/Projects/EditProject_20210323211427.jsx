import React,{useState} from 'react'

export default function EditProject(props) {
    // console.log(this.queryParams)
    console.log(props.location.state.name)
//  console.log(props.match.params)

const [data,setData] = useState({})

let fetchData = async () =>{
    let response = await fetch(url);
    let json = await response.json()
      setData(json);
    console.log(data)
    
}
fetchData()
    
    
    return (
        <div>
            <h1>Edit Project</h1>
            
        </div>
    )
}
