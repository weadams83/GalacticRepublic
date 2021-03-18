
import "./App.css";
import { Login } from "./Components/Login/Login";
import { Route, Switch } from "react-router-dom";
import { CompanyPage } from "./Screens/CompanyPage/CompanyPage";
import { MemberPage } from "./Screens/MemberPage/MemberPage";
import Navbar from './Components/Navbar/Navbar'; 
import  SignUp  from "./Components/SignUp/SignUp";

function App() {
  return (
    <div className="App">
        <Navbar />
      {/* <Button name="Submit"></Button>  
      <Card name="Team 1"></Card>   */}
        {/* <SignUp /> */}
    
      <Switch>
        <Route path="/member" component={MemberPage} />
        <Route path="/company" component={CompanyPage} />
        <Route path="/SignUp" component={SignUp} />
        <Route path="/" component={Login} />
      </Switch>

    </div>
  );
}

export default App;

