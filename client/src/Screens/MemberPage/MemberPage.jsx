import StyledMemberPage from './StyledMemberPage';
import NavBar from '../../Components/Navbar/Navbar';
import Card from '../../Components/Card/Card'
import Button from '../../Components/Button/Button'

//test points!
const newMember = false;
//TODO: make function to check if the login is a new member or not

const amountOfCards = [1,2,3,4,5];
//TODO: find out the amount of projects the user is in




export const MemberPage = () => {
  if (newMember === true) {
    return (
      <div>
        <h2>MemberPage</h2>
        <p>Your account was successfully created!</p>
        <p>Your company has been notified.</p>
      </div>

    );
  }

  return (
<StyledMemberPage>
  <NavBar></NavBar>
   <div>
     {amountOfCards.map((object, i) => {
     return <Card obj={object} key = {i}/>
     })}

   </div>
   <Button>Create (todo)</Button>
  
   </StyledMemberPage>
  )



}

