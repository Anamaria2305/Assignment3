import React, { Component, useEffect, useState } from 'react';
import { useParams, useNavigate, Link} from 'react-router-dom';
import axios from 'axios'
import "./Restaurant.css";
import Navbar from './Navbar';
function Menu() {
    let navigate = useNavigate();
    const [errorMessage, setMessage]= useState("");
    const [data, setData] = useState(["No data"]);
    const [data2, setData2] = useState(["No data"]);
    const [data3, setData3] = useState(["No data"]);
    function handleSubmit(event) {
        event.preventDefault();
        var { uname} = document.forms[0];
        setData3(uname.value);
        const params = new URLSearchParams({
            restaurant: uname.value
          }).toString();
        const url =
       "http://localhost:8080/menu/restaurant?" + params;
        axios.post(url,{})
        .then(response =>{
        
          setData(response.data);
          setData2(response.data["food"]);
           
        })
        .catch(({ response }) => { 
            if(typeof  response=="undefined")
            setMessage("Succes");

            else
            setMessage("Failed");
            
        })
      }

function tryhard(Object){
    
    if (typeof Object === 'undefined') 

    return  <td>
        
    </td>
    else
    return  <td>{
                                
    Object.name                
    }</td>
    
}
const onClick2 = ()=>{
    var sTable = document.getElementById('tab').innerHTML;

    var style = "<style>";
    style = style + "table {width: 100%;font: 17px Calibri;}";
    style = style + "table, th, td {border: solid 1px #DDD; border-collapse: collapse;";
    style = style + "padding: 2px 3px;text-align: center;}";
    style = style + "</style>";

    var win = window.open('', '', 'height=700,width=700');

    win.document.write('<html><head>');
    win.document.write('<title>Menu with name '+data.name+' of restaurant ' + data3 + '</title>');  
    win.document.write(style);         
    win.document.write('</head>');
    win.document.write('<body>');
    win.document.write(sTable);        
    win.document.write('</body></html>');

    win.document.close(); 	// CLOSE THE CURRENT WINDOW.

    win.print();    // PRINT THE CONTENTS.

};
    return (
        <div>
            <Navbar /> 
            <h1>{data.name} </h1>
            <div id="tab">   
            <table className="myAwesomeTable">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Category</th>
                      
                    </tr>
                </thead>
                <tbody>
                    {data2.map( (food)=>(
                        <tr>
                            <td>{food.id}</td>
                            <td>{food.name}</td>
                            <td>{food.description}</td>
                            <td>{food.price}</td>
                            {tryhard(food.category)}
                            
                        </tr>
                    ))
                    }
                </tbody>
            </table>
            </div>

            <div className="form">
    <form onSubmit= {handleSubmit}>
        <div className="input-container">
            <label>Restaurant Name </label>
            <input type="text" name="uname" required />
            
        </div>
        <div>
            <label>{errorMessage} </label>
        </div>
        <div className="button-container">
            <input type="submit" value="See Menu" />
        </div>
    </form>

    
    </div>
    <input  type='button' value='Export to Pdf'  onClick={()=>onClick2()}/>
        </div>
      );
}

export default Menu;