import React, { Component, useEffect, useState } from 'react';
import { useParams, useNavigate, Link} from 'react-router-dom';
import { send } from 'emailjs-com';
import axios from 'axios'
import "./Restaurant.css";
import NavbarU from './NavbarU';
function Cart() {
    let navigate = useNavigate();
    const [errorMessage, setMessage]= useState("");
    const [errorMessage2, setMessage2]= useState("");
    const [data, setData] = useState(["No data"]);
    const [data2, setData2] = useState(["No data"]);
    const [data3, setData3] = useState([]);
    const [data4, setData4] = useState(0);

    const [toSend, setToSend] = useState({
        email: '',
        toptri:'3',
        price2:'none',
        details:'None'
      });

    function handleSubmit(event) {
        event.preventDefault();
        var { uname} = document.forms[0];
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
            
            setMessage("Enter a valid Restaurant");
            
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
const emailregex= /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
const [isValid, setIsValid] = useState(false);
const [message, setMessage3] = useState('');

var emailMsg
const validateEmail = (event) => {
    const email2 = event.target.value;
    if (emailregex.test(email2)) {
      setIsValid(true);
      setMessage3('Your email looks good!');
      var arr2=[]
      for(var i=0;i<data3.length;i++){
        arr2.push([data3[i].name,data3[i].price])
        
    }
        var helper=arr2.toString().replace(/,(.*?(?:,|$))/g, ':$1')
        var helper2=helper.replace(/,/g, ' lei, ');
      emailMsg="Order contains: "+ helper2+" lei.";  
      const price22="Total price of " + data4.toString()+" lei"
      setToSend({ ...toSend,["email"]:event.target.value,["toptri"]:emailMsg,["price2"]:price22});
    } else {
      setIsValid(false);
      setMessage3('Please enter a valid email!');
    }
  };
const addCart = (foods)=>{
    let cart = [...data3];
    if(cart.indexOf(foods)==-1)
       { cart.push(foods);
        setData3(cart);
        setData4(data4+foods.price)
    }
    else
       { setData3(cart);}

    
};

function handleSubmit2(event) {
    event.preventDefault();
    var { uid,emailf} = document.forms[1];
    const params = new URLSearchParams({
        user: uid.value
      }).toString();
      var arr=[] 
    for(var i=0;i<data3.length;i++){
        arr.push(data3[i].id)
    }
    const params2 = new URLSearchParams({
        foods: arr
      }).toString();
     
//     const url =
//    "http://localhost:8080/orders/save?" + params + "&" + params2;
//     axios.post(url,{status:"Pending"})
//     .then(response =>{
    
//       setData(response.data);
       
//     })
//     .catch(({ response }) => { 
//         setMessage("Enter a valid Id");
        
//     })
     
      console.log(toSend.toptri)
      console.log(toSend.email)
      console.log(toSend.details)
    send(
        'service_g7d7lnn',
        'template_1gsr2el',
        toSend,
        'zALaxG8cUyuakRc5k'
      )
        .then((response) => {
          console.log('SUCCESS!', response.status, response.text);
        })
        .catch((err) => {
          console.log('FAILED...', err);
        });
  }

  const handleChange = (e) => {
    setToSend({ ...toSend, [e.target.name]: e.target.value });
  };
    return (
        <div>
            <NavbarU />
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
            <div>
            <h1>{data.name} </h1>
             
            <table className="myAwesomeTable">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Category</th>
                        <th>Add To Cart</th>
                      
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
                            <td className="myclass2" > <button className="myclass2"  onClick={()=>addCart(food)}>Add</button></td>
                            
                        </tr>
                    ))
                    }
                </tbody>
            </table>
            </div>
            <h1>Cos</h1>
            <table className="myAwesomeTable">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Category</th>
                        <th>Add To Cart</th>
                      
                    </tr>
                </thead>
                <tbody>
                    {data3.map( (food)=>(
                        <tr>
                            <td>{food.id}</td>
                            <td>{food.name}</td>
                            <td>{food.description}</td>
                            <td>{food.price}</td>
                             {tryhard(food.category)} 
                            <td className="myclass2" > <button className="myclass2"  onClick={()=>addCart(food)}>Add</button></td>
                            
                        </tr>
                    ))
                    }
                </tbody>
                <h3>Calculated amount: {data4} lei.</h3>
               
            </table>


            <div className="form">
    <form onSubmit= {handleSubmit2}>
        <div className="input-container">
            <label>Your user id </label>
            <input type="number" name="uid" required />
          
            
        </div>
        <div>
              <label>E-mail for invoice </label>
            <input type="text" name='emailf'  required  onChange={validateEmail} />
        </div>
        <div>
              <label>Special details </label>
            <input type="text" name='details'  onChange={handleChange} />
        </div>
        <div>
            <label>{errorMessage} </label>
        </div>
        <div>
            <label>{message} </label>
        </div>
        <div className="button-container">
            <input className="myclass" type="submit" value="Place Order" />
        </div>
    </form>

    </div>

            


        </div>
      );
}

export default Cart;