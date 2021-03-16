
import './App.css';
import Navbar from './Components/Navbar'; 
import Button from'./Components/Button';

import { Login } from "./Components/Login/Login";
function App() {
  return (
    <div className="App">   
        <Navbar>
        <Login />
          <Button></Button>
        </Navbar>
    </div>
  );
}

export default App;
