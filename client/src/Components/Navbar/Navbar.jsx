import styled from 'styled-components';



const Navbar = () => {
    return(
    <div>
        <nav>
            <a href = 'users'>Users</a>
            <a href = 'projects'>Projects</a>
            <a href = 'teams'>Teams</a>
            <input type = "text">Search</input>
        </nav>
    </div>
    )
}

export default Navbar