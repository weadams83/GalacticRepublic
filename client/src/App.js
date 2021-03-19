import "./App.css";
import { Login } from "./Components/Login/Login";
import { Route, Switch } from "react-router-dom";
import { CompanyPage } from "./Screens/CompanyPage/CompanyPage";
import { MemberPage } from "./Screens/MemberPage/MemberPage";
import  SignUp  from "./Components/SignUp/SignUp";
import { Users } from "./Components/Users/Users";
import { Projects } from "./Components/Projects/Projects";
import CreateProject from "./Components/CreateProject/CreateProject";

import CompanySignUp from "./Components/SignUp/CompanySignUp";

function App() {
  return (
    <div className="App">
      <Switch>
        <Route path="/createproject" component={CreateProject}/>
        <Route path="/member" component={MemberPage} />
        <Route path="/company" component={CompanyPage} />
        <Route path="/SignUp" component={SignUp} />
        <Route path="/CompanySignUp" component={CompanySignUp} />
        <Route path="/users" component={Users} />
        <Route path="/projects" component={Projects} />
        <Route path="/" component={Login} />
        
      </Switch>
    </div>
  );
}

export default App;
