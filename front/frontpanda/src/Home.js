import React from "react";
import { useNavigate,Link,useHistory } from "react-router-dom";
import logo from './fp.png';
import './App.css';
import {Component} from "react";
import { Button } from 'react-native';


class Home extends Component{

  constructor(props) {
    super(props);
    this.state = {
      data: [],
  }

  }
  componentDidMount() {

    // GET request using fetch with error handling
    fetch('http://localhost:8080/users/all', )
        .then(async response => {
          const data = await response.json();

          // check for error response
          if (!response.ok) {
            // get error message from body or default to response statusText
            const error = (data && data.message) || response.statusText;
            return Promise.reject(error);
          }
          console.log(data[0].username + " " + data[0].password + " " + data[0].address);
        })
        .catch(error => {
          this.setState({ errorMessage: error.toString() });
          console.error('There was an error!', error);
        });
  }

  render() {
  return (
      
    <>
      <div className="App" >
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <a
                className="App-link"
                // href="https://reactjs.org"
                target="_blank"
                rel="noopener noreferrer"
                
            >   <h1>Food Panda
            
            </h1></a>
            <div className='content' style={{ display: "flex" , justifyContent: "space-around"}}>
                <Link to="/login" className="btn">Log In Admin</Link>
                <Link to="/loginU" className="btn">Log In User</Link>
                <Link to="/register" className="btn">Register User</Link>
            </div>
            
          </header>
        </div>
    </>
 );
}
}

export default Home;