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
import Restaurants from '../Restaurants/Restaurants';
import RestaurantList from '../Restaurants/RestaurantList';


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

        const restaurantNames = this.state.restaurants.map((d) =>
            <li key={d.name}>{d.name}</li>

        );
        const restaurantDescription = this.state.restaurants.map((d) =>
            <li key={d.description}>{d.description}</li>

        );

        document.getElementById('results').innerHTML = restaurantNames
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
            // var string = "";
            // const results = [];
            // for (var i=0; i < this.state.restaurants.length; i++){
            //         results.push(this.state.restaurants[i].name);
            // }
            const restaurantNames = this.state.restaurants.map((d) =>
                <li key={d.name}>{d.name} {d.description}</li>

                //    const restaurantOptions = this.state.restaurants.map((d) => 
                //        <RestaurantList />
                //     );

            );
            console.log(this.state.restaurants)
            const restaurantDescription = this.state.restaurants.map((d) =>
                <li key={d.description}></li>

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



                        <div>
                            <div className={classes.restaurantCard}>
                                <Restaurants />
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
                </div>
            )
        }

    }
}

export default SearchForm;