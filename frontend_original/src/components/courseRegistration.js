import React, { useState, setState, useEffect } from "react";
import { api } from "../util/api";
import "../style.css";
import { confirmAlert } from "react-confirm-alert"; // Import
import "react-confirm-alert/src/react-confirm-alert.css"; // Import css

const CourseForm = ({ userData }) => {
  const [courseList, setCourseList] = useState([
    {
      building: "",
      course_id: {
        capacity: 0,
        course_code: "",
        credits: 0,
        name: "",
        term: 0,
        year: 0,
      },
      course_schedule_id: 0,
      day: "",
      room: "",
      time: "",
    },
  ]);
  const [index, setIndex] = useState(-1);
  const [open, setOpen] = useState(false);
  const [selectedList, setSelectedList] = useState([]);
  const [listDisplay, setListDisplay] = useState(false);
  useEffect(() => {
    // let user_id = Number(userData.department.department_id)

    const fetchData = async () => {
      const response = await fetch(
        `http://localhost:8080/courseSchedule/${Number(
          userData.employee_id
        )}/get`
      );
      const newData = await response.json();
      console.log(newData);
      setCourseList(newData);
      window.localStorage.setItem("courseList", JSON.stringify(newData));
    };
    fetchData();
  }, [userData]);

  const handleAddEmployee = async (index) => {
    let course_id = courseList[index].course_id.course_id;

    const response = await fetch(
      `http://localhost:8080/course/${Number(
        userData.employee_id
      )}/add/${Number(course_id)}`
    );
    const newData = await response.json();
    console.log(newData);
    setCourseList(newData);
  };

  const selectHandleChange = (event) => {
    setIndex(event.target.value);
  };

  useEffect(() => {
    const currentList = window.localStorage.getItem("currentList");
    if (currentList) {
      setSelectedList(JSON.parse(currentList));
      setListDisplay(true);
    }
  }, []);

  const alertBox = () => {
    confirmAlert({
      title: "Confirm to submit",
      message: "Are you sure to select this course ?",
      buttons: [
        {
          label: "Yes",
          onClick: () => {
            setOpen(false);
            // console.log(courseList[index])

            if (index === -1) return;
            {
              handleAddEmployee(index);
            }
            const curr = selectedList;
            curr.push(courseList[index]);
            setSelectedList(curr);
            window.localStorage.setItem(
              "currentList",
              JSON.stringify(selectedList)
            );
          },
        },
        {
          label: "No",
          onClick: () => setOpen(false),
        },
      ],
    });
  };

  const selectCourse = () => {
    setOpen(true);
    alertBox();
  };

  return (
    // {
    // <div>
    //     `hello`
    // </div>
    // }
    // {

    <div className="course">
      <h3>Registered Successfully!</h3>
      <p>Select courses from the list </p>
      <select className="form-control" onChange={selectHandleChange}>
        <option value="">Choose course</option>
        {courseList.map((c, i) => (
          <option value={i} key={c.course_schedule_id}>
            {c.day} {"-"} {c.course_id.name} {":"} {c.building} {"-"} {c.time}
          </option>
        ))}
      </select>
      <button onClick={selectCourse}> ADD </button>

      {selectedList.map((c, i) => (
        <div>
          <ul>
            <li>
              {c.day} - {c.course_id.name} - {c.building} - {c.time}
            </li>
          </ul>
        </div>
        // <option value={i} key={c.course_schedule_id} >{c.day} {"-"} {c.course_id.name} {':'} {c.building} {'-'} {c.time}</option>
      ))}
      
    </div>
  );
};

export default CourseForm;
