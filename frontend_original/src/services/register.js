import axios from "axios";
const registerUrl = `http://localhost:8080/emp/register`;
const register = async (details) => {
  // Send the login credential data to the loginBaseUrl API endpoint as an HTTP POST request
  // Note the async-await

  let employeeDetails = {
    first_name: details.firstName,
    last_name: details.lastName,
    email: details.email,
    title: details.title,
    photo_path: details.photo,
    password: details.password,
    empDepartment: {
      departmentID: Number(details.departmentId),
    },
  };

  const response = await axios.post(registerUrl, employeeDetails);
  // console.log(response.data)
  // Return .data field of the response object which would contain the logged in user object
  // And the user state would now be updated by the handleLogin() method
  return response.data;
};

// Export the method as an object so that it can be accessible outside this file as a service
const exportObject = { register };
export default exportObject;
