import React,{useState,useEffect} from 'react'
import axios from 'axios';
import { InspectP, Styledmain } from '../CreateProject/StyledCreateProject';

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
 

    return (
        <div>
            <InspectP>
                <Styledmain>
                    <Form
                </Styledmain>
            </InspectP>
            
        </div>
    )
}
