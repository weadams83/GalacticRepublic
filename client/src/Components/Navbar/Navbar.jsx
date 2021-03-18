import { Link } from "react-router-dom";
import { NavbarStyles } from "./StyledNavbar";

const Navbar = () => {
  return (
    <div>
      <NavbarStyles>
        <Link to="/users">Users</Link>
        <Link to="/projects">Projects</Link>
        <Link to="/company">Teams</Link>
        <input type="text" placeholder="Search" />
      </NavbarStyles>
    </div>
  );
};

export default Navbar;
