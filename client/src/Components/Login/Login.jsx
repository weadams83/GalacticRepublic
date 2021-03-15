import $ from "jquery";
import { StyledLogin } from "./StyledLogin";

export const Login = () => {

  const handleSelect = (e) => {
    if (e.target.value === "company") {
      $(".login-container form .company-select").toggleClass("hide");
    }
  };

  return (
    <StyledLogin className="login">
      <div className="login-container">
        <h1>Login</h1>
        <form>
          <select onChange={handleSelect} name="role" id="role">
            <option value="default">Choose your role</option>
            <option value="company">Company</option>
            <option value="member">Team Member</option>
          </select>
          <div className="member-select hide">
            <input placeholder="First Name" type="text" />
            <input placeholder="Last Name" type="text" />
            <input placeholder="Username" type="text" />
            <input placeholder="Password" type="password" />
          </div>
          <div className="company-select hide">
            <input placeholder="Company Name" type="text" />
            <input placeholder="Description of your company..." type="text" />
            <input placeholder="Password" type="password" />
          </div>
        </form>
      </div>
    </StyledLogin>
  );
};
