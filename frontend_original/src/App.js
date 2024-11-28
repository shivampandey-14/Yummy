import "./App.css";
import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import RegistrationForm from "./components/registration";
import Login from "./components/login";
import CourseForm from "./components/courseRegistration";
import registerService from "./services/register";
import { confirmAlert } from "react-confirm-alert";

function App() {
  const [userData, setUserData] = useState(null);

  const handleRegister = async (details) => {
    try {
      const userObject = await registerService.register(details);

      if (!userObject) {
        setUserData(null);
        alertBox("Email id already exists");
        return;
      }
      setUserData(userObject);
      window.localStorage.setItem("loggedInUser", JSON.stringify(userObject));
      // return userData;
      return true; 
    } catch (exception) {
      alertBox("Choose correct department ");
      return false;
    }
  };

  const alertBox = (message) => {
    confirmAlert({
      title: "Notification",
      message: message,
      buttons: [
        {
          label: "Ok",
          onClick: () => {
            const currList = window.localStorage.getItem("currentList");
            const courseList = window.localStorage.getItem("courseList");
            if (currList || courseList?.length === 2) {
              window.localStorage.removeItem("loggedInUser");
              window.localStorage.removeItem("courseList");
            }
          },
        },
      ],
    });
  };

  const handleSubmit = () => {
    const currList = window.localStorage.getItem("currentList");
    const courseList = window.localStorage.getItem("courseList");
    //setSelectedList(currList)
    console.log(courseList.length);

    if (courseList.length === 2) {
      alertBox("No course available");
      setUserData(null);
    } else if (currList === null) {
      alertBox("You have not selected any course");
      // setIsSubmit(false)
    } else {
      alertBox("Course(s) have been added successfully!");
      // window.localStorage.removeItem('currentList')
      // window.localStorage.removeItem('loggedInUser')
      setUserData(null);
      // setIsSubmit(true)
    }
  };

  useEffect(() => {
    const loggedInUser = window.localStorage.getItem("loggedInUser");
    if (loggedInUser) setUserData(JSON.parse(loggedInUser));
  }, []);

  return (
    <Router>
      <div className="App">
        <Routes>
          {/* Login Page */}
          <Route
            path="/login"
            element={<Login onLogin={(user) => setUserData(user)} />}
          />

          {/* Registration Page */}
          <Route
            path="/register"
            element={
              userData ? (
                <RegistrationForm startRegister={handleRegister} />
              ) : (
                <Navigate to="/login" />
              )
            }
          />

          {/* Course Registration */}
          <Route
            path="/courses"
            element={
              userData ? (
                <>
                <CourseForm userData={userData} />
                <div>
                  <button onClick={handleSubmit}>SUBMIT</button>
                </div>
                </>
              ) : (
                <Navigate to="/login" />
              )
            }
          />

          {/* Default Route */}
          <Route path="*" element={<Navigate to="/login" />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
