import React, { Component, useEffect, useState } from 'react';
import { useParams, useNavigate, Link} from 'react-router-dom';
import axios from 'axios'
import Navbar from './Navbar';
import {useLocation} from 'react-router-dom';
import "./Restaurant.css";
function Status() {

    let navigate = useNavigate();
  const [errorMessage, setMessage]= useState("");
  const [data, setData] = useState(["No data"]);
  function handleSubmit(event) {
    event.preventDefault();
    var { uname} = document.forms[0];
    const params = new URLSearchParams({
        order_id: uname.value
      }).toString();
    const url =
   "http://localhost:8080/orders/declined?" + params;
    axios.post(url,{})
    .then(response =>{
    
      setData(response.data);
       
    })
    .catch(({ response }) => { 
      setMessage("Enter a valid Id");
        
    })
  }

  
  function handleSubmit2(event) {
    event.preventDefault();
    event.preventDefault();
    var { ida} = document.forms[1];
    const params = new URLSearchParams({
        order_id: ida.value
      }).toString();
    const url =
   "http://localhost:8080/orders/status?" + params;
    axios.post(url,{})
    .then(response =>{
    
      setData(response.data);
       
    })
    .catch(({ response }) => { 
        if(typeof  response=="undefined")
        setMessage("Succes");

        else
        setMessage("Failed");
        
    })
  }

  return (
      <div> <Navbar />
    <div className="form">
    <form onSubmit= {handleSubmit}>
        
        <div>
            <label className="formm">Id   of   Order   you   want   to   Decline </label>
        
        </div>
        <div className="input-container">
            <label>Order Id To Decline  </label>
            <input type="number" name="uname" required />
            
        </div>
        <div>
            <label>{errorMessage} </label>
        </div>
        <div className="button-container">
            <input type="submit" value="Decline order"/>
        </div>
    </form>
    </div>

    <div className="form">
    <form onSubmit= {handleSubmit2}>
        
        <div>
            <label className="formm">Id   of   Order to   Accept/Change status </label>
        
        </div>
        <div className="input-container">
            <label>Order Id To Accept/Change  </label>
            <input type="number" name="ida" required />
            
        </div>
        <div>
            <label>{errorMessage} </label>
        </div>
        <div className="button-container">
            <input type="submit" value="Accept/change status"/>
        </div>
    </form>
    </div>

    
    </div>
  );
}

export default Status;