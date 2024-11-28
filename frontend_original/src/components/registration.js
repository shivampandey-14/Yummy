import React, { useState, setState, useEffect } from "react";

import { useNavigate } from "react-router-dom";
import './RegistrationForm.css';

const RegistrationForm = ({ startRegister }) => {
  // Defining hooks
  const [title, setTitle] = useState(null);
  const [firstName, setFirstName] = useState(null);
  const [lastName, setLastName] = useState(null);
  const [email, setEmail] = useState(null);
  const [password, setPassword] = useState(null);
  const [photo, setPhoto] = useState("hello");
  const [departmentList, setDepartmentList] = useState([{ name: "", id: "" }]);
  const [departmentId, setDepartmentId] = useState(null);
  // const [isRegister, setIsRegister] = useState(true);
  // const [src, setSrc] = useState("");

  const navigate = useNavigate();

  // Intial Loading
  useEffect(() => {
    const fetchData = async () => {
      const response = await fetch(`http://localhost:8080/department/get`);
      const newData = await response.json();
      setDepartmentList(newData);
      // console.log(newData);
    };
    fetchData();
  }, []);

  // modify boxes on writing into them
  const handleInputChange = (e) => {
    const { id, value } = e.target;

    if (id === "Title") {
      setTitle(value);
    }
    if (id === "firstName") {
      setFirstName(value);
    }
    if (id === "lastName") {
      setLastName(value);
    }
    if (id === "email") {
      setEmail(value);
    }
    if (id === "password") {
      setPassword(value);
    }
    // if(id === "photo"){
    //     setPhoto(value);
    // }
    if (id === "departmentid") {
      setDepartmentId(value);
    }
  };
  

  // On clicking submit this will trigger
  const handleRegister = async (event) => {
    // console.log(title, firstName, lastName, email, password, photo);
    event.preventDefault();
    // console.log(photo)
    const details = {
      title,
      firstName,
      lastName,
      email,
      password,
      departmentId,
      photo,
    };

    // if (startRegister(details)) {
    //     setIsRegister(false);
    // }
    const success = await startRegister(details);
    if(success)
      navigate("/courses");
  };

  // on selecting depatment from dropdown this will change
  const selectHandleChange = (event) => {
    setDepartmentId(event.target.value);
  };

  // modify image
  const fileChangedHandler = (event) => {
    // setPhoto(event.target.files[0])
    // console.log(event.target.files[0])
    let file = event.target.files[0];

    let reader = new FileReader();
    // console.log("next");
    let base64String = "";
    reader.onload = function () {
      base64String = reader.result;
      // console.log(base64String)
      // setPhoto(base64String)
    };

    reader.readAsDataURL(file);
  };

  return (
    <form onSubmit={handleRegister} id="register-form">
      <div className="form">
        <div className="form-body">
          <div className="title">
            <label className="form__label" for="Title">
              Title{" "}
            </label>
            <input
              className="form__input"
              type="text"
              value={title}
              onChange={(e) => handleInputChange(e)}
              id="Title"
              placeholder="Title"
            />
          </div>

          <div className="firstName">
            <label className="form__label" for="firstName">
              First Name{" "}
            </label>
            <input
              className="form__input"
              type="text"
              value={firstName}
              onChange={(e) => handleInputChange(e)}
              id="firstName"
              placeholder="First Name"
            />
          </div>

          <div className="lastname">
            <label className="form__label" for="lastName">
              Last Name{" "}
            </label>
            <input
              type="text"
              name=""
              id="lastName"
              value={lastName}
              className="form__input"
              onChange={(e) => handleInputChange(e)}
              placeholder="Last Name"
            />
          </div>

          <div className="department">
            <label className="form__label" for="lastName">
              Department{" "}
            </label>
            <select
              className="form-control"
              value={departmentId}
              onChange={selectHandleChange}
            >
              <option value="">Choose Department</option>

              {departmentList.map((department) => (
                <option
                  value={department.departmentID}
                  key={department.departmentID}
                >
                  {department.departmentName}
                </option>
              ))}
            </select>
          </div>

          <div className="email">
            <label className="form__label" for="email">
              Email{" "}
            </label>
            <input
              type="email"
              id="email"
              className="form__input"
              value={email}
              onChange={(e) => handleInputChange(e)}
              placeholder="Email"
              required
            />
          </div>
          <div className="password">
            <label className="form__label" for="password">
              Password{" "}
            </label>
            <input
              className="form__input"
              type="password"
              id="password"
              value={password}
              onChange={(e) => handleInputChange(e)}
              placeholder="Password"
              required
            />
          </div>
          {/*<div className="photo">*/}
          {/*    <label className="form__label" for="photo"> Photo </label>*/}
          {/*    <input className="form__input" type="text" id="photo" value={photo} onChange = {(e) => handleInputChange(e)} placeholder="Photo" required/>*/}
          {/*</div>*/}
          <div>
            <label className="form__label" for="photo">
              {" "}
              Choose Profile Photo{" "}
            </label>
            <input
              type="file"
              onChange={fileChangedHandler}
              accept="image/*"
              required
            />
            {/*<button onClick={uploadHandler}>Upload</button>*/}
          </div>
        </div>
        <div className="footer">
          {
            <button type="submit" className="btn">
              REGISTER
            </button>
          }
        </div>
      </div>
    </form>

    // <Form>
    //     <Form.Group className="mb-3" controlId="formBasicEmail">
    //         <Form.Label>Email address</Form.Label>
    //         <Form.Control type="email" placeholder="Enter email" />
    //         <Form.Text className="text-muted">
    //             We'll never share your email with anyone else.
    //         </Form.Text>
    //     </Form.Group>
    //
    //     <Form.Group className="mb-3" controlId="formBasicPassword">
    //         <Form.Label>Password</Form.Label>
    //         <Form.Control type="password" placeholder="Password" />
    //     </Form.Group>
    //     <Form.Group className="mb-3" controlId="formBasicCheckbox">
    //         <Form.Check type="checkbox" label="Check me out" />
    //     </Form.Group>
    //     <Button variant="primary" type="submit">
    //         Submit
    //     </Button>
    // </Form>
  );
};

export default RegistrationForm;
