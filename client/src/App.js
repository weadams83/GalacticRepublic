
import './App.css';

import Button from './Components/Button/Button';
import Card from './Components/Card/Card';



import { Login } from "./Components/Login/Login";
function App() {
  return (
    <div className="App">
      <Button name="Submit"></Button>  
      <Card name="Team 1"></Card>  
        <Login />

    </div>
  );
}

export default App;
