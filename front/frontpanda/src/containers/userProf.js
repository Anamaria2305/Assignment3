import React, { Component, useEffect, useState } from 'react';
import { useParams, useNavigate, Link} from 'react-router-dom';
import axios from 'axios'
import NavbarU from './NavbarU';
import {useLocation} from 'react-router-dom';
function UserProf() {
    const userProf = useLocation();


    return (
        <div>
            <NavbarU />
            <h1> User Profile</h1>
            <h2> My id is </h2>
            <div>{userProf.state.id}</div>
            <h2> Welcome back </h2>
            <div>{userProf.state.username}</div>
        </div>
      );
}

export default UserProf;