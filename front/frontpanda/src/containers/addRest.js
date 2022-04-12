import React, { Component, useEffect, useState } from 'react';
import { useParams, useNavigate, Link} from 'react-router-dom';
import axios from 'axios'
import Navbar from './Navbar';
import {useLocation} from 'react-router-dom';

function AddRest() {
    
    let navigate = useNavigate();
    const [errorMessage, setMessage]= useState("");
    function handleSubmit(event) {
        event.preventDefault();
        var { uname, pass,zone,aid} = document.forms[0];
        const params = new URLSearchParams({
            admin_id: aid.value
          }).toString();
        const url =
       "http://localhost:8080/restaurants/save?" + params;
        axios.post(url,{ name: uname.value, location: pass.value,zones: zone.value })
        .then(response =>{
        
          if(response.data.id==null)
          throw Error('could not fetch the data for that resource');
        })
        .catch(({ response }) => { 
            if(typeof  response=="undefined")
            setMessage("Succes");

            else
            setMessage("Enter a valid Id");
            
        })
      }
    return (
        <div>
            <Navbar />
        <div className="form">
        <form onSubmit= {handleSubmit}>
            <div className="input-container">
                <label>Name of Restaurant </label>
                <input type="text" name="uname" required />
                
            </div>
            <div className="input-container">
                <label>Location </label>
                <input type="text" name="pass" required />
            
            </div>
            <div className="input-container">
                <label>Zones </label>
                <input type="text" name="zone" required />
            
            </div>
            <div className="input-container">
                <label>Admin Id </label>
                <input type="number" name="aid" required />
            
            </div>
            <div>
                <label>{errorMessage} </label>
            </div>
            <div className="button-container">
                <input type="submit" value="Add" />
            </div>
        </form>
        </div></div>
      );
}

export default AddRest;