
import {NavbarStyles} from './StyledNavbar'

const Navbar = () => {
    return(
    <div>
        <NavbarStyles>
            <a href = 'users'>Users</a>
            <a href = 'projects'>Projects</a>
            <a href = 'teams'>Teams</a>
            <input type = "text" placeholder="Search"/>
        </NavbarStyles>
    </div>
    )
}


export default Navbar