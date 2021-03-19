import StyledMemberPage from './StyledMemberPage';
import NavBar from '../../Components/Navbar/Navbar';
import Card from '../../Components/Card/Card';
import { Fragment } from "react";
import Button from '../../Components/Button/Button';
const dummyData = require("../../DummyData.json");


//test points!
const newMember = false;
//TODO: get the 'team' field from the user data


const filterUser =(dummyData.data[0].user.filter((u => u.username==='cooksys')))


const groupId = (filterUser[0].group_id)
console.log(groupId)

const newMemberTag = (groupId ==='undefined')? true : false; 
console.log(newMemberTag)



export const MemberPage = () => {
  if (newMemberTag === true) {
    return (
      <div>
        <h2>MemberPage</h2>
        <p>Your account was successfully created!</p>
        <p>Your company has been notified.</p>
      </div>

    );
  }
// const projectUsers = dummyData.data[0].user.filter(
//   (project) => project.users.length ===1
// )

// const filterUser= dummyData.data[0].filter(user=> user.username==='gmoney')


  const getProjectsArray = () =>{
 let projectsArray = [];
 filterUser.forEach((u) =>{
   u.projects.forEach((project)=> {
     projectsArray.push(project)
   })
 })
return projectsArray
}

  return (
  <Fragment>
    <NavBar/>
<StyledMemberPage className='member-page'>
  <div className='member-page-container'>
    <div className='title'>
      <h2>Member Page</h2>
    </div>
    <div className='card-container'>
      {getProjectsArray().map((project)=>
      (<Card
        className='card'
        name={project}
        key= {`${project}`}
        />
        )
      )}
    </div>
  </div>
   
   <Button>Create (todo)</Button>
  
   </StyledMemberPage>
   </Fragment>
  )



}

