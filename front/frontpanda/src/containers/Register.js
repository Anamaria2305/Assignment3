import React, {useState,setState} from 'react';
import './Register.css'
import axios from 'axios'
import { useNavigate } from 'react-router-dom';
function RegistrationForm() {
    let navigate = useNavigate();
    const [errorMessage, setMessage]= useState("");
    let handleSubmit = (event) => {
        event.preventDefault();
        var { uname, pass, addr} = document.forms[0];
        axios.post("http://localhost:8080/users/save",{username: uname.value, password: pass.value,  address: addr.value})
        .then(response =>{
            console.log(response);
             if(response.data!=null)
              navigate(-1);
            
        })
        .catch(({ response }) => { 
            setMessage("Acest nume de utilizator exista deja");
        })
      };

      return (
        <div>
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
                        <div className="input-container">
                            <label>Address </label>
                            <input type="text" name="addr"/>
                        
                        </div>
                        <div>
                            <label>{errorMessage} </label>
                        </div>
                        <div className="button-container">
                            <input type="submit" value="Register" />
                        </div>
                    </form>

                </div>
            </div>
      );  
}

export default RegistrationForm