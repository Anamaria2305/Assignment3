import React, { Component, useEffect, useState } from 'react';
import { useParams, useNavigate, Link} from 'react-router-dom';
import axios from 'axios'
import Navbar from './Navbar';
import {useLocation} from 'react-router-dom';
function AdminProf() {
    const adminPro = useLocation();


    return (
        <div>
            <Navbar />
            <h1> Admin Profile</h1>
            <h2> My id is</h2>
            <div>{adminPro.state.id}</div>
        </div>
      );
}

export default AdminProf;