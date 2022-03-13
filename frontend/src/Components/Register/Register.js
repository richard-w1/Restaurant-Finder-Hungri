import React from 'react';
import axios from 'axios';
import {useState} from 'react'
import {Link} from 'react-router-dom'
import { baseUrl } from '../../Shared/baseUrl';
import '../../style.css'
import Card from '../UI/Card'
import classes from './Register.module.css'


const Register = (props) => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");

    const handleSubmit = () => {

        document.getElementById('message').innerHTML = "";

        const data = {username: username, password: password, confirmPassword: confirmPassword, role: 'ROLE_USER'}
        if(password === confirmPassword){

            axios.post(baseUrl + "/register", data)
            .then(
                (response) => {
                    document.getElementById('message').innerHTML = "Account successfully created, you can now log in.";
                }
            )
            .catch(
                (error) => {
                    console.error(error.response.data.message);
                    document.getElementById('message').innerHTML = error.response.data.message;
                }
            );


        } else 
        {
            document.getElementById('message').innerHTML = "Make sure passwords match."
        }
    }

    return(
        <div class='body'> 
        <div>

            <div className={classes.registercard}>
            <Card>
            <h2>Create Account</h2>
            <label class="sr-only">Email</label>
            <input
                type="text"
                id="username"
                name="username"
                class="form-control"
                placeholder="Email"
                v-model="user.username"
                onChange={(e) => setUsername(e.target.value)}
                required
            />
            <label class="sr-only">Password</label>
            <input
                type="password"
                id="password"
                name="password"
                class="form-control"
                placeholder="Password"
                v-model="user.password"
                onChange={(e) => setPassword(e.target.value)}
                required
            />
           <div class = 'pw' >Please include one capital letter, one lower, one number, minimum of 8 characters</div>
            <input
                type="password"
                id="password-confirm"
                name="password-confirm"
                class="form-control"
                placeholder="Confirm Password"
                v-model="user.password"
                onChange={(e) => setConfirmPassword(e.target.value)}
                required
            />
            <button type="submit" onClick={handleSubmit}>Register</button>
            <div id = "message"></div>
            <Link to="/login">Have an account?</Link>

            </Card>
            </div>
        </div>
        </div>
    )
}

export default Register;