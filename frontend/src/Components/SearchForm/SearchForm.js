import React from 'react';
import { Component } from 'react';
import { Form, FormGroup, FormControl, FormLabel, Button } from 'react-bootstrap';

class SearchForm extends Component {

    constructor(props) {
        super(props);

        this.state= {
            cityOrZip: ""
        }
    };

    render() {

        return (
            <div>
                <Form>
                    <FormGroup controlId='formSearchBar'>
                        <FormLabel>City/Zip Code</FormLabel>
                        <FormControl type='text' placeholder='Enter City or Zip Code' />
                    </FormGroup>
                    <Button variant='primary' type='submit'>
                        Search
                    </Button>
                </Form>
            </div>

        );
    }
}

export default SearchForm;