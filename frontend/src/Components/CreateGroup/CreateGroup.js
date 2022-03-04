import { Component, useState} from 'react'
import {Link} from 'react-router-dom'
import {connect} from 'react-redux'
import {withRouter} from 'react-router-dom'
import {addToken, addUser} from '../../Redux/actionCreators'
import {baseUrl} from '../../Shared/baseUrl'
import axios from 'axios'


// const [eventName, setEventName] = useState("");
// const [location, setLocation] = useState("");
// const [date, setDate] = useState("");
// const [time, setTime] = useState("");
// const [userId, setUserId] = useState("");

// const handleSubmit = () => {
//     const data = {
//         "hostId": 3, //TODO: set to 3 for now, should be whoever is logged in
//         "eventName" : eventName,
//         "date": date,
//         "time": time,
//         "hasEnded": false,
//         "location": location}
//         axios.post(baseUrl + "/create_group", data)
// }

const CreateGroup = (props) => {
    return(

        <div>
        <h1>Invite your friends!</h1>
        {/* <h1>Event Details:</h1>
            <label class="sr-only">Event Name</label>
            <input
                type="text"
                id="event_name"
                name="event_name"
                class="form-control"
                placeholder="event_name"
                onChange={(e) => setEventName(e.target.value)}
                required
            />

            <label class="sr-only">City or Zip Code</label>
            <input
                type="text"
                id="event_name"
                name="event"
                class="form-control"
                placeholder="Username"
                onChange={(e) => setUsername(e.target.value)}
                required
            />

            <h1>Voting Expires:</h1>
            <label class="sr-only">Date</label>
            <input
                type="text"
                id="event_name"
                name="event"
                class="form-control"
                placeholder="Username"
                onChange={(e) => setUsername(e.target.value)}
                required
            />

            <label class="sr-only">Time</label>
            <input
                type="text"
                id="event_name"
                name="event"
                class="form-control"
                placeholder="Username"
                onChange={(e) => setUsername(e.target.value)}
                required
            /> */}
            </div>
    )
}

export default CreateGroup;