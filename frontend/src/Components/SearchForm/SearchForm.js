import React from 'react';
import { Component, useState } from 'react'
import { Link } from 'react-router-dom'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'
import { addToken, addUser } from '../../Redux/actionCreators'
import { baseUrl } from '../../Shared/baseUrl'
import axios from 'axios'
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

        var restaurant = "<ul>";
        this.state.restaurants.map((d) =>{

        restaurant += "<div>";
        restaurant += "<img src='/images/" + d.restaurantId + ".jpg' alt = 'title' height = '250px' width = 'auto'/>";
        restaurant += "<h3>" + d.name + "</h3>";
        restaurant += "<p>" + "Rating: " + d.rating + "</p>" 
        restaurant += "<p>" + d.type + "</p>" 
        restaurant += "<p>" + d.zipCode + " " + d.city + "</p>"
        restaurant += "<p>" + d.description + "</p>";
        restaurant += "<p>" + "Hours: " + d.hoursOfOperation + "</p>"
        restaurant += "<p>" + d.open + "</p>" 
        restaurant += "<p>" + "Phone: " + d.phoneNumber + "</p>" 



        restaurant += "<hr>";
        });
        restaurant += "</ul>";
        document.getElementById('results').innerHTML = restaurant
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
                    <h1 class='header' >Search Restaurants</h1>
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
                    <div id="results">

                    </div>
                </div>

            );
        }
        else {

            console.log(this.state.restaurants)

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



                        <div id = 'results'>
                        </div>
                        <div>
                            <Link to="/SearchForm">
                                <button renderAs="button">
                                    <span>Back to Search</span>
                                </button>
                            </Link>
                        </div>

                    </div>
                </div>
            )
        }

    }
}

export default SearchForm;