import "./App.css";
import { Login } from "./Components/Login/Login";
import { Route, Switch } from "react-router-dom";
import { CompanyPage } from "./Screens/CompanyPage/CompanyPage";
import { MemberPage } from "./Screens/MemberPage/MemberPage";
import Navbar from './Components/Navbar/Navbar'; 

function App() {
  return (
    <div className="App">
      <Navbar />
      <Switch>
        <Route path="/member" component={MemberPage} />
        <Route path="/company" component={CompanyPage} />
        <Route path="/" component={Login} />
      </Switch>
    </div>
  );
}

export default App;
