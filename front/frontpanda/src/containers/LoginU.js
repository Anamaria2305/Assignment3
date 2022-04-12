import React, { useState } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import "./LoginU.css";
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios'
export default function LoginU() {
  let navigate = useNavigate();
  const [errorMessage, setMessage]= useState("");
  
  function handleSubmit(event) {
  
    event.preventDefault();
    var { uname, pass} = document.forms[0];
    axios.post("http://localhost:8080/users/login",{username: uname.value, password: pass.value})
    .then(response =>{
      console.log(response.data.id);
      if(response.data.id!=null)
      navigate('/userprof',{state:{id:response.data.id,username:response.data.username}});
      else
      throw Error('could not fetch the data for that resource');
    })
    .catch(({ response }) => { 
        
        setMessage("Nu exista acest cont");
    })

  }

  return (
    <div className="form">
        <form onSubmit= {handleSubmit}>
            <div className="input-container">
                <label>Username </label>
                <input type="text" name="uname" required />
                
            </div>
            <div className="input-container">
                <label>Password </label>
                <input type="password" name="pass" required />
            
            </div>
            <div>
                <label>{errorMessage} </label>
            </div>
            <div className="button-container">
                <input type="submit" />
            </div>
        </form>
        <div><Link to="/register">Register</Link></div>
        </div>
  );
}