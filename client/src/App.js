import "./App.css";
import { Login } from "./Components/Login/Login";
import { Route, Switch } from "react-router-dom";
import { CompanyPage } from "./Screens/CompanyPage/CompanyPage";
import { MemberPage } from "./Screens/MemberPage/MemberPage";
import  SignUp  from "./Components/SignUp/SignUp";

function App() {
  return (
    <div className="App">
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
