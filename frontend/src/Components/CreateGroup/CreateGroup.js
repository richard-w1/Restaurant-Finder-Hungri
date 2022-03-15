import { Component, useState } from 'react'
import { Link } from 'react-router-dom'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import { addToken, addUser } from '../../Redux/actionCreators'
import { baseUrl } from '../../Shared/baseUrl'
import axios from 'axios'
import { StaticRouter } from 'react-router-dom'
import { Token } from '../../Redux/token'
import Card from '../UI/Card'
import classes from './CreateGroup.module.css'


export default class CreateGroup extends Component {

    constructor(props) {
        super(props);

        console.log(this.props);

        this.state = {
            eventName: '',
            endDate: '',
            endTime: '',
            location: '',
            hostId: ''
        }
    }

    handleSubmit = () => {
        const data = {
            eventName: this.state.eventName,
            date: this.state.endDate,
            time: this.state.endTime,
            location: this.state.location,
            token: this.props.token
        }
        console.log(data)
        axios.post(baseUrl + "/create_group", data)
            .then(
                (response) => {
                    document.getElementById('message').innerHTML = "Invitation successfully created.";
                }
            )
            .catch(
                (error) => {
                    console.error(error.response.data.message);
                    document.getElementById('message').innerHTML = error.response.data.message;
                }
            );
    }

    render() {
        return (

            <div class='input'>

                <h1 class='header' >Invite your friends!</h1>
                <p className={classes.tagline}>They are Hungri Too</p>
                <img className={classes.image} src="/images/FriendsBanner.png" alt="Friends, Family, Food" />
                <div className={classes.inviteCard}>
                        <h1 className={classes.text}>Event Details:</h1>
                        <label class="sr-only">Event Name</label>
                        <input
                            type="text"
                            id="eventName"
                            name="eventName"
                            class="form-control"
                            placeholder="Name of event"
                            onChange={(e) => this.state.eventName = (e.target.value)}
                            required
                        />

                        <label class="sr-only">City or Zip Code</label>
                        <input
                            type="text"
                            id="location"
                            name="location"
                            class="form-control"
                            placeholder="City or Zip Code"
                            onChange={(e) => this.state.location = (e.target.value)}
                            required
                        />
                </div>

                
                <div className={classes.inviteCard}>
                    <h1 className={classes.text}>Voting Expires:</h1>
                    <label class="sr-only">Date</label>
                    <input
                        type="date"
                        id="setEndDate"
                        name="setEndDate"
                        class="form-control"
                        placeholder="Date"
                        onChange={(e) => this.state.endDate = (e.target.value)}
                        required
                    />

                    <label class="sr-only">Time</label>
                    <input
                        type="time"
                        id="endTime"
                        name="endTime"
                        class="form-control"
                        placeholder="Time"
                        onChange={(e) => this.state.endTime = (e.target.value)}
                        required
                    />
                    <button type="submit" onClick={this.handleSubmit}>Create Invitation</button>
                    <div id='message'></div>
                </div>

            </div>
        )
    }
}

// export default CreateGroup;