import { useDispatch } from "react-redux";
import { Link } from "react-router-dom";
import { removeUser } from "../../store/loginReducer";
import { NavbarStyles } from "./StyledNavbar";

const Navbar = () => {
  const dispatch = useDispatch();
  const logout = () => {
    dispatch(removeUser());
  };
  return (
    <div>
      <NavbarStyles>
        <Link to="/users">Users</Link>
        <Link to="/projects">Projects</Link>
        <Link to="/company">Teams</Link>
        <Link to="/profile">Profile</Link>
        <Link onClick={logout} to="/">
          Logout
        </Link>
      </NavbarStyles>
    </div>
  );
};

export default Navbar;
