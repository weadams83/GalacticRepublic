//Action

const SAVE_USER = "SAVE_USER";

const initialUserState = {
  associatedTeam: null,
  firstName: "",
  isDeleted: false,
  lastName: "",
  associatedTeam: null,
  newUser: true,
  projects: [],
  userCompany: {},
  userName: "",
  userRole: {},
};



export const saveUser = (firstName, lastName, userName, userCompany) => ({
  type: SAVE_USER,
  firstName,
  lastName,
  userName,
  userCompany
})

export const loginReducer = (state = initialUserState, action) => {
  switch (action.type) {
    case SAVE_USER:
      return {
        ...state,
        firstName: action.firstName,
        lastName: action.lastName,
        userName: action.userName,
        userCompany: action.userCompany
        // {
        //   ...action.userCompany,
        //   companyName: action.userCompany.companyName,
        //   companyDescription: action.userCompany.companyDescription
        // }
      };
    default:
      return state;
  }
};
