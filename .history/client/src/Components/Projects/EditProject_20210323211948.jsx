import React,{useState,useEffect} from 'react'
import axios from 'axios';

export default function EditProject(props) {
    // console.log(this.queryParams)
    console.log(props.location.state.name)
//  console.log(props.match.params)

const [data,setData] = useState({})
useEffect(() => {
    const fetchData = async () => {
      const result = await axios(
        'http',
      );
 
      setData(result.data);
    };
 
    fetchData();
  }, []);
 

    return (
        <div>
            <h1>Edit Project</h1>
            
        </div>
    )
}
