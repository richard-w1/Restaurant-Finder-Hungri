import React from 'react';
import { Component, useState } from 'react'
import { Link } from 'react-router-dom'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import { addToken, addUser } from '../../Redux/actionCreators'
import { baseUrl } from '../../Shared/baseUrl'
import axios from 'axios'
import SearchResults from '../SearchResults/SearchResults';
import '../../style.css'
import Card from '../UI/Card'
import classes from './SearchForm.module.css'


class SearchForm extends Component {

    constructor(props) {
        super(props);
        this.displaySearch = true;
        this.state = {
            location: '',
            restaurants: ''
        }
        this.handleInputChange = this.handleInputChange.bind(this);
    };

    handleSearch = async () => {
        this.displaySearch = false;
        const data = { location: this.state.location }
        console.log(data);
        var response = axios.get(baseUrl + "/find_restaurants/" + data.location)
        console.log(response);
        this.setState({
            location: this.state.location,
            restaurants: (await response).data.restaurants
        })

    }


    handleInputChange = (event) => {
        event.preventDefault()
        this.setState({
            [event.target.name]: event.target.value
        })
    }


    render() {
        if (this.displaySearch) {
            return (
                <div class="input">
                    <h1>Search Restaurants</h1>
                    <label class="sr-only">Enter City or Zip Code</label>
                    <input
                        type='text'
                        id="location"
                        name="location"
                        class="form-control"
                        v-model="user.username"
                        placeholder='Enter City or Zip Code'
                        onChange={this.handleInputChange}
                        required
                    />
                    <button type='submit' onClick={this.handleSearch}>Search</button>

                </div>

            );
        }
        else {
            // var string = "";
            // const results = [];
            // for (var i=0; i < this.state.restaurants.length; i++){
            //         results.push(this.state.restaurants[i].name);
            // }
            const restaurantNames = this.state.restaurants.map((d) =>
                <li key={d.name}>{d.name}</li>
            );

            return (
                <div>
                    <div class="input">
                        <h1>Search Restaurants</h1>
                        <label class="sr-only">Enter City or Zip Code</label>
                        <input
                            type='text'
                            id="location"
                            name="location"
                            class="form-control"
                            v-model="user.username"
                            placeholder='Enter City or Zip Code'
                            onChange={this.handleInputChange}
                            required
                        />
                        <button type='submit' onClick={this.handleSearch}>Search</button>

                    </div>

                    <div>
                        <div className={classes.restaurantCard}>
                            <Card>
                                <ul>{restaurantNames}</ul>
                            </Card>
                        </div>
                    </div>
                    <div>
                        <Link to="/SearchForm">
                            <button renderAs="button">
                                <span>Back to Search</span>
                            </button>
                        </Link>
                    </div>


                </div>
            )
        }

    }
}

export default SearchForm;