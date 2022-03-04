import React from 'react';
import { Component } from 'react';
import { Form, FormGroup, FormControl, FormLabel, Button } from 'react-bootstrap';
import axios from 'axios';
import {useState} from 'react'
import {Link} from 'react-router-dom'
import { baseUrl } from '../../Shared/baseUrl';

class SearchForm extends Component {

    constructor(props) {
        super(props);

        this.state= {
            location: ""
        }
    };

    handleSearch() {
        const data = {location : this.state.location}
        axios.get(baseUrl + "/find_restaurants", data)
    }


    handleInputChange = (event) => {
        event.preventDefault()
        this.setState({
            [event.target.location]: event.target.value
        })
    }


    render() {


        return (
            <div>
                <Form>
                    <FormGroup controlId='formSearchBar'>
                        <FormLabel>City/Zip Code</FormLabel>

                        <FormControl
                        type='text'
                        name="location"
                        placeholder='Enter City or Zip Code'
                        onChange={this.handleInputChange}
                        required
                        
                        
                        />
                    </FormGroup>
                    <Button variant='primary' type='submit' onClick={this.handleSearch}>
                        Search
                    </Button>
                </Form>
            </div>

        );
    }
}

export default SearchForm;